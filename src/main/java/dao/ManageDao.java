package dao;

import entity.UserLogin;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtils;

/**
 * Created by ddgdd on 2018/8/28 0028 14:59
 */
public class ManageDao {

    public ManageDao() {

    }

    private static class ManageDaoHolder {
        private final static ManageDao instance = new ManageDao();
    }

    public static ManageDao getInstance() {
        return ManageDaoHolder.instance;
    }

    public UserLogin checkUser(String userName) {
        return LoginDao.getInstance().checkUser(userName);
    }

    public boolean changePassword(String userName, String newPassword) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String sql = "update UserInfoEntity set userPassword =:newPassword where userName =:userName";
            Query query = session.createQuery(sql);
            query.setParameter("newPassword", newPassword);
            query.setParameter("userName", userName);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            return false;
        }

        return true;
    }
}
