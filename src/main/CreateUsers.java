package main;

import dao.IUserDAO;
import dao.impl.DAOFactory;
import model.User;

/**
 * Hello world!
 *
 */
public class CreateUsers {
	
	public static void main(String[] args) {
		// Création de trois users
        IUserDAO userDao = DAOFactory.getUserDao();
		User user1 = new User();
		user1.setFirstname("Nathalie");
		user1.setLastname("Forget");
		user1.setLogin("nforget");
		user1.setPwd("pwd1");
		User user2 = new User();
		user2.setFirstname("Bill");
		user2.setLastname("Buffalo");
		user2.setLogin("bbuffalo");
		user2.setPwd("pwd2");
		User user3 = new User();
		user3.setFirstname("John");
		user3.setLastname("Doeuf");
		user3.setLogin("jdoeuf");
		user3.setPwd("pwd3");
		// on les persiste
        try {
			userDao.create(user1);
			System.out.println("User 1 créé avec l'id : " + user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			userDao.create(user2);
			System.out.println("User 2 créé avec l'id : " + user2.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			userDao.create(user3);
			System.out.println("User 3 créé avec l'id : " + user3.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
