/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.User;

/**
 *
 * @author oandrade
 */
public interface IUserDAO extends IDAO<User>{
	
	public User findByLogin(String login);
    
}
