package dao;

import entity.UserInfoEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.MD5;
import java.util.ArrayList;
import java.util.List;

public class AdminDao extends BaseDao{
    public AdminDao() {
    }

    /*
    定义一个私有的内部类，在第一次用这个嵌套类时，会创建一个实例。而类型为LoginDaoHolder的类，只有在LoginDao.getInstance()中调用，
    由于私有的属性，他人无法使用LoginDaoHolder，不调用LoginDao.getInstance()就不会创建实例。
    优点：达到了lazy loading的效果，即按需创建实例。
     */
    private static class AdminDaoHolder {
        private final static AdminDao instance = new AdminDao();
    }

    public static AdminDao getInstance() {
        return AdminDaoHolder.instance;
    }


    public List<UserInfoEntity> findAll() {
        String showList = "from UserInfoEntity";
        ArrayList<UserInfoEntity> list = new ArrayList<>();
        Session session = getSession();
        Query query = session.createQuery(showList);
        list = (ArrayList<UserInfoEntity>) query.list();
        session.close();
        return list;
    }

    public String addUser(UserInfoEntity s){
        try {
            Session session = getSession();
            Transaction trans = session.beginTransaction();
            session.save(s);
            session.flush();
            trans.commit();
            session.close();
            return(s.getUserName());
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateUser(int userId,String userName,int userLevel,String userRemark){
        try {
            Session session = getSession();
            Transaction trans = session.beginTransaction();
            UserInfoEntity s=session.get(UserInfoEntity.class, userId);
            s.setUserName(userName);
            s.setUserLevel(userLevel);
            s.setUserRemark(userRemark);
            session.update(s);
            session.flush();
            trans.commit();
            session.close();
            return(s.getUserName());
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteUser(int userId){
        try {
            Session session = getSession();
            Transaction ts = session.beginTransaction();
            UserInfoEntity s=session.get(UserInfoEntity.class, userId);
            session.delete(s);
            session.flush();
            ts.commit();
            session.close();
            return(s.getUserName());

        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String resetPassword(int userId, String userPassword){
        try {
            Session session = getSession();
            Transaction ts = session.beginTransaction();
            UserInfoEntity s=session.get(UserInfoEntity.class, userId);
            s.setUserPassword(MD5.md5(userPassword));
            session.update(s);
            session.flush();
            ts.commit();
            session.close();
            return(s.getUserName());
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

}
