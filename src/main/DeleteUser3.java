package main;

import dao.IUserDAO;
import dao.impl.DAOFactory;
import model.User;

/**
 * Hello world!
 *
 */
public class DeleteUser3 {
	
	public static void main(String[] args) {
        IUserDAO userDao = DAOFactory.getUserDao();
		User user3 = userDao.find(3);
		// delete du user 3
		if(user3!=null) {
			userDao.delete(user3);
			System.out.println("User 3 supprimé");
		}
	}
}
