package dao.impl;

import dao.IUserDAO;
import tools.Config;

public class DAOFactory {
	private final static String sDAOType = Config.getInstance().getProperty("daoType");
	private final static DAOType daoType = DAOType.CSV;
//	private final static DAOType daoType = DAOType.JDBC;
	
	public static IUserDAO getUserDao() {
//		switch (daoType) {
		switch (sDAOType) {
//		case CSV : 
		case "CSV" : 
			return UserCsvDAO.getInstance();
//		case JDBC:
		case "JDBC":
			return new UserDAO();
		default:
			throw new IllegalArgumentException("Unexpected value: " + daoType);
		}
	}

}
