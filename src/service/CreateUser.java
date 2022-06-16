package service;

import model.User;

public class CreateUser {

	public static void main(String[] args) {
		User u = new User();
		u.setFirstname("Olivier");
		u.setLastname("ANDRADE SANCHEZ");
		u.setLogin("oandrade");
		u.setPwd("bon mot de passe");
		try {
			UserService.getInstance().createUser(u);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
