package controller;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import model.User;
import service.UserService;

public class HomeController {
	private static HomeController INSTANCE = new HomeController();
	private HomeController() {};
	public static HomeController getInstance() {
		return INSTANCE;
	}
	
	private Document login;
	private Document pwd;
	private Document lastname;
	private Document firstname;	
	
	public void save() {
		User user = new User();
		try {
			user.setLogin(login.getText(0, login.getLength()));
			user.setPwd(pwd.getText(0, pwd.getLength()));
			user.setLastname(lastname.getText(0, lastname.getLength()));
			user.setFirstname(firstname.getText(0, firstname.getLength()));
			UserService.getInstance().createUser(user);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public void cancel() {
		try {
			login.remove(0, login.getLength());
			pwd.remove(0, pwd.getLength());
			lastname.remove(0, lastname.getLength());
			firstname.remove(0, firstname.getLength());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setLogin(Document login) {
		this.login = login;
	}
	public void setPwd(Document pwd) {
		this.pwd = pwd;
	}
	public void setLastname(Document lastname) {
		this.lastname = lastname;
	}
	public void setFirstname(Document firstname) {
		this.firstname = firstname;
	}
	
	

}
