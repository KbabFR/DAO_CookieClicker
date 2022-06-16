package dao.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultRowSorter;

import dao.IDAO;
import dao.IUserDAO;
import model.User;

public class UserCsvDAO implements IUserDAO {
	
	private final Path userPath = Paths.get("/home/oandrade/tmp/user.csv");  
	private final String SEPARATOR = ";";
	private Map<Integer,User> users = new HashMap<>(); 
	
	private static UserCsvDAO INSTANCE = new UserCsvDAO();
	
	private UserCsvDAO() {
		loadUsers();
	}
	
	public static UserCsvDAO getInstance() {
		return INSTANCE;
	}
	

	@Override
	public void create(User user) throws Exception {
		Integer newId = 1;
		if (users.size()>0) {
			newId = Collections.max(users.keySet()) + 1;
		}
		user.setId(newId);
		users.put(newId, user);
		appendLastUser();
	}

	@Override
	public void delete(User user) {
		users.remove(user.getId());
		saveUsers();
	}

	@Override
	public void delete() {
		users.clear();
		saveUsers();
	}

	// Attention on ne peut pas créer 2 user avec le même login donc si la mise à jour concerne le login attention !
	@Override
	public void update(User user) {
//		users.put(user.getId(), user);
		saveUsers();
	}

	@Override
	public User find(int id) {
		return users.get(id);
	}

	@Override
	public List<User> findAll() {		
		return new ArrayList<>(users.values());
	}

	@Override
	public User findByLogin(String login) {
		Collection<User> usersList = users.values();
		User foundUser = null;
		for (User u : usersList) {
			if(login.equals(u.getLogin())) {
				foundUser = u;
			}
		}
		return foundUser;
	}

	/**
	 * Sauvegarde de la map dans le fichier
	 */
	private void saveUsers() {
		List<String> lines = new ArrayList<>();
		for (Map.Entry<Integer, User> entry : users.entrySet()) {
			User user = entry.getValue();
			lines.add(userToCsv(user));
		}		
		try {
			Files.write(userPath,lines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Ajout du dernier user de la map dans le fichier
	 */
	private void appendLastUser() {
		ArrayList<User> usersValues = new ArrayList(users.values()); 
		User lastUser = usersValues.get(usersValues.size()-1);
		ArrayList<String> lines = new ArrayList<>();
		lines.add(userToCsv(lastUser));
		try {
			if (Files.exists(userPath)) {
				Files.write(userPath,lines,StandardOpenOption.APPEND);
			} else {
				Files.write(userPath,lines);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadUsers() {
		if (Files.exists(userPath)){
			try {
				List<String> lines = Files.readAllLines(userPath);
				for (String line : lines) {
					User u = csvToUser(line);
					users.put(u.getId(), u);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Transformation d'un objet user en chaîne de caractère de type CSV (;)
	 * @param u
	 * @return
	 */
	private String userToCsv(User u) {
		StringBuilder sb = new StringBuilder();
		sb.append(u.getId()).append(SEPARATOR).append(u.getLogin()).append(SEPARATOR).append(u.getPwd()).append(SEPARATOR).append(u.getLastname())
		.append(SEPARATOR).append(u.getFirstname());
		return sb.toString();
	}
	/**
	 * Transformation d'une chaîne de caractère de type CSV (;) en objet user
	 * @param s
	 * @return
	 */
	private User csvToUser(String s) {
		String[] fields = s.split(SEPARATOR);
		User u = new User();
		u.setId(Integer.parseInt(fields[0]));
		u.setLogin(fields[1]);
		u.setPwd(fields[2]);
		u.setLastname(fields[3]);
		u.setFirstname(fields[4]);
		return u;
	}

}
