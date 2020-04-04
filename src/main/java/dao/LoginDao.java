package dao;

import entity.UserLogin;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;


/**
 * Created by ddgdd on 2018/8/23 0023 11:40
 */
public class LoginDao {

    public LoginDao() {
    }

    /*
    定义一个私有的内部类，在第一次用这个嵌套类时，会创建一个实例。而类型为LoginDaoHolder的类，只有在LoginDao.getInstance()中调用，
    由于私有的属性，他人无法使用LoginDaoHolder，不调用LoginDao.getInstance()就不会创建实例。
    优点：达到了lazy loading的效果，即按需创建实例。
     */
    private static class LoginDaoHolder {
        private final static LoginDao instance = new LoginDao();
    }

    public static LoginDao getInstance() {
        return LoginDaoHolder.instance;
    }

    public UserLogin checkUser(String userName){
        Session session = HibernateUtils.getCurrentSession();
        UserLogin userLogin = null;
//        Transaction transaction = null;

        try {
            Transaction tx = session.beginTransaction();
            String sql = "select new entity.UserLogin(u.userName, u.userPassword, u.userLevel, u.userId) from UserInfoEntity u where userName=:userName";
            Query query = session.createQuery(sql);
            query.setParameter("userName", userName);

            userLogin = (UserLogin)query.uniqueResult();
            tx.commit();

            return userLogin;
//            transaction = session.beginTransaction();
//            UserInfoEntity userInfoEntity = new UserInfoEntity();
//            userInfoEntity.setUserName("test");
//            userInfoEntity.setUserPassword("test");
//            userInfoEntity.setUserLevel(2);
//            session.save(userInfoEntity);
//            System.out.println("保存成功");
//
//            transaction.commit();
        } catch (Exception e) {
            return null;
        }
    }
}
