/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.List;

/**
 *
 * @author oandrade
 * @param <T>
 */
public interface IDAO<T> {
  public void create(T obj) throws Exception;
  public void delete(T obj);
  public void delete();
  public void update(T obj);
  public T find(int id);
  public List<T> findAll();
}
