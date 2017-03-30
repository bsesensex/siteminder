package net.codejava.spring.dao;
 
import java.util.List;

import net.codejava.spring.model.HibernateUser;
 
public interface UserDao {
    public List<HibernateUser> list();
    public HibernateUser findByUserId(String userid);
}