package main;

import dao.IUserDAO;
import dao.impl.DAOFactory;
import model.User;

/**
 * Hello world!
 *
 */
public class UpdateUser2 {
	
	public static void main(String[] args) {
        IUserDAO userDao = DAOFactory.getUserDao();
		User user2 = userDao.find(2);
		// on fait une mise à jour du user 2
		if(user2!=null) {
			System.out.println("Lastname du user 2 avant mise à jour : " + user2.getLastname());
			user2.setLastname("Gates");
	        userDao.update(user2);
			System.out.println("Lastname du user 2 après mise à jour : " + user2.getLastname());
		}
	}
}
