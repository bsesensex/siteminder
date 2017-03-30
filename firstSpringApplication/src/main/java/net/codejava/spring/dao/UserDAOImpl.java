package net.codejava.spring.dao;
 
import java.util.List;
 
import net.codejava.spring.model.HibernateUser;
 
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
 
public class UserDAOImpl     implements UserDao {
    private SessionFactory sessionFactory;
 
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    @Override
    @Transactional
    public List<HibernateUser> list() {
        @SuppressWarnings("unchecked")
        List<HibernateUser> listUser = (List<HibernateUser>) sessionFactory.getCurrentSession()
                .createCriteria(HibernateUser.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listUser;
    }
    @Override
    @Transactional
	public HibernateUser findByUserId(String userid){
    return	 (HibernateUser) sessionFactory.getCurrentSession().get(HibernateUser.class,Integer.parseInt(userid)); 
    /*	List<HibernateUser> useres= (List<HibernateUser>) sessionFactory.getCurrentSession()
    		        .createCriteria(HibernateUser.class)
    		        .add(Restrictions.eq("user_id", Integer.parseInt(userid)) )
    		        .list();
    	return useres.get(0);*/
	}
 
}