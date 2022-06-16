package service;

public class LoginUser {

	public static void main(String[] args) {
		System.out.println("Test de login avec mauvais mot de passe :");
		try {
			UserService.getInstance().doLogin("oandrade", "mauvais mot de passe");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Test de login avec bon mot de passe :");
		try {
			UserService.getInstance().doLogin("oandrade", "bon mot de passe");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
