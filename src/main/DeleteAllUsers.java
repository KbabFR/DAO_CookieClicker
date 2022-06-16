package main;

import dao.IUserDAO;
import dao.impl.DAOFactory;
import model.User;

/**
 * Hello world!
 *
 */
public class DeleteAllUsers {
	
	public static void main(String[] args) {
        IUserDAO userDao = DAOFactory.getUserDao();
        userDao.delete();
		System.out.println("Les users ont été supprimés");
	}
}
