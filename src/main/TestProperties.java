package main;

import tools.Config;

public class TestProperties {

	public static void main(String[] args) {
		System.out.println("Properties truc : "+Config.getInstance().getProperty("truc"));

	}

}
