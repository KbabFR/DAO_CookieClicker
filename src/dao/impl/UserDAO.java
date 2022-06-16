/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import dao.IUserDAO;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oandrade
 */
public class UserDAO implements IUserDAO {

    Statement st = null;
    PreparedStatement ps = null;
    private final static String CREATE_USER_SQL = "INSERT INTO \"USER\" (login,pwd,lastname,firstname) VALUES (?,?,?,?)";
    private final static String FIND_USER_SQL = "SELECT login,pwd,lastname,firstname FROM \"USER\" WHERE id=?";
    private final static String FIND_USER_BY_LOGIN_AND_PWD_SQL = "SELECT id,lastname,firstname FROM \"USER\" WHERE login=? AND pwd=?";
    private final static String FIND_USERS_SQL = "SELECT id,login,pwd,lastname,firstname FROM \"USER\"";
    private final static String DELETE_USERS_SQL = "DELETE FROM \"USER\"";
    private final static String DELETE_USER_SQL = "DELETE FROM \"USER\" WHERE id=?";
    private final static String UPDATE_USER_SQL = "UPDATE \"USER\" SET login=?, pwd=?, lastname=?, firstname=? WHERE id=?";

    @Override
    public void create(User user) {
        try {
//            ps = Singleton.getInstance().getConnection().prepareStatement(CREATE_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPwd());
            ps.setString(3, user.getLastname());
            ps.setString(4, user.getFirstname());
            ps.execute();
            //ne pas oublier de position la clé sur l'objet
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no generated key obtained.");
            }
        } catch (SQLException ex) {
            System.out.println("Insertion impossible de : " + user.toString());
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(User user) {
        try {
//            ps = Singleton.getInstance().getConnection().prepareStatement(DELETE_USER_SQL);
            ps.setInt(1, user.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Suppression impossible de : " + user.toString());
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete() {
        try {
//            ps = Singleton.getInstance().getConnection().prepareStatement(DELETE_USERS_SQL);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Impossible de vider la table user");
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(User user) {
        try {
//            ps = Singleton.getInstance().getConnection().prepareStatement(UPDATE_USER_SQL);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPwd());
            ps.setString(3, user.getLastname());
            ps.setString(4, user.getFirstname());
            ps.setInt(5, user.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Mise à jour impossible de : " + user.toString());
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public User find(int id) {
        User user = null;
        
        try {
//            Statement st = Singleton.getInstance().getConnection().createStatement();
            st.executeQuery("select .... from usr where id="+id);


        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

/*        try {
            ps = Singleton.getInstance().getConnection().prepareStatement(FIND_USER_SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            } else {
                throw new SQLException("Impossible de trouver le user ayant pour id : " + id);
            }
        } catch (SQLException ex) {
            System.out.println("Impossible de trouver le  user ayant pour id : " + id);
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = null;
        try {
//            ps = Singleton.getInstance().getConnection().prepareStatement(FIND_USER_SQL);
            ResultSet rs = ps.executeQuery();
            users = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setPwd(rs.getString(3));
                user.setLastname(rs.getString(4));
                user.setFirstname(rs.getString(5));
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Problème pour récupérer la liste des users");
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }

	@Override
	public User findByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
