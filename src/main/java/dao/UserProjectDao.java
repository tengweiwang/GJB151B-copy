package dao;

import entity.UserProject;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtils;
/**
 * 从用户表中获取每个用户的所有项目的项目Itemid
 */

public class UserProjectDao {
    public UserProjectDao(){}

    private static class UserProjectDaoHolder{
        private final static UserProjectDao instance = new UserProjectDao();
    }

    public static UserProjectDao getInstance(){return UserProjectDaoHolder.instance;}

    public UserProject FindUser(String userName){
        Session session = HibernateUtils.getCurrentSession();
        UserProject up = null;
        try{
            session.beginTransaction();
            String sql = "select new entity.UserProject(u.userName, u.userNew, u.userProofread, u.userAudit, u.userAuthorize) from UserInfoEntity u where userName=:userName";
            Query query = session.createQuery(sql);
            query.setParameter("userName", userName);
            session.getTransaction().commit();
            up = (UserProject) query.uniqueResult();

            return up;
        }catch (Exception e) {
            return null;
        }
    }
}
