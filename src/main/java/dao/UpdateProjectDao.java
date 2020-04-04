package dao;

import com.alibaba.fastjson.JSONArray;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtils;
import com.alibaba.fastjson.JSON;
import java.sql.Timestamp;

public class UpdateProjectDao {

    public UpdateProjectDao() {

    }

    private static class UpdateProjectDaoHolder {
        private final static UpdateProjectDao instance = new UpdateProjectDao();
    }

    public static UpdateProjectDao getInstance() {
        return UpdateProjectDaoHolder.instance;
    }


    public String changeProofreadStatus(int devId,int devStatus, Timestamp timestamp) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String sql = "update ManageSysDevelopEntity set devStatus =:devStatus, devProofreadTime =:devProofreadTime where devId =:devId";
            Query query = session.createQuery(sql);
            query.setParameter("devStatus", devStatus);
            query.setParameter("devProofreadTime", timestamp);
            query.setParameter("devId", devId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            return null;
        }

        return "success";
    }


    public String changeAuditStatus(int devId,int devStatus, Timestamp timestamp) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String sql = "update ManageSysDevelopEntity set devStatus =:devStatus, devAuditTime =:devAuditTime where devId =:devId";
            Query query = session.createQuery(sql);
            query.setParameter("devStatus", devStatus);
            query.setParameter("devAuditTime", timestamp);
            query.setParameter("devId", devId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            return null;
        }

        return "success";
    }


    public String changeAuthorizeStatus(int devId,int devStatus, Timestamp timestamp) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String sql = "update ManageSysDevelopEntity set devStatus =:devStatus, devAuthorizeTime =:devAuthorizeTime where devId =:devId";
            Query query = session.createQuery(sql);
            query.setParameter("devStatus", devStatus);
            query.setParameter("devAuthorizeTime", timestamp);
            query.setParameter("devId", devId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            return null;
        }

        return "success";
    }


    public String changeProjectProofreadAdvice(int devId,int devStatus, String devAdviceProofread, Timestamp timestamp) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String sql = "update ManageSysDevelopEntity set devStatus =:devStatus, devAdviceProofread =:devAdviceProofread, devProofreadTime =:devProofreadTime where devId =:devId";
            Query query = session.createQuery(sql);
            query.setParameter("devStatus", devStatus);
            query.setParameter("devAdviceProofread", devAdviceProofread);
            query.setParameter("devProofreadTime", timestamp);
            query.setParameter("devId", devId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            return null;
        }

        return "success";
    }


    public String changeProjectAuditAdvice(int devId,int devStatus, String devAdviceAudit, Timestamp timestamp) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String sql = "update ManageSysDevelopEntity set devStatus =:devStatus, devAdviceAudit =:devAdviceAudit, devAuditTime =:devAuditTime where devId =:devId";
            Query query = session.createQuery(sql);
            query.setParameter("devStatus", devStatus);
            query.setParameter("devAdviceAudit", devAdviceAudit);
            query.setParameter("devAuditTime", timestamp);
            query.setParameter("devId", devId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            return null;
        }

        return "success";
    }


    public String changeProjectAuthorizeAdvice(int devId,int devStatus, String devAdviceAuthorize, Timestamp timestamp) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String sql = "update ManageSysDevelopEntity set devStatus =:devStatus, devAdviceAuthorize =:devAdviceAuthorize, devAuthorizeTime =:devAuthorizeTime where devId =:devId";
            Query query = session.createQuery(sql);
            query.setParameter("devStatus", devStatus);
            query.setParameter("devAdviceAuthorize", devAdviceAuthorize);
            query.setParameter("devAuthorizeTime", timestamp);
            query.setParameter("devId", devId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            return null;
        }

        return "success";
    }


    public String saveDraft(int devId, String devAdviceProofread) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            System.out.println("***************************");
            System.out.println(devId+"*****"+devAdviceProofread);
            session.beginTransaction();
            String sql = "update ManageSysDevelopEntity set devAdviceProofread =:devAdviceProofread where devId =:devId";
            Query query = session.createQuery(sql);
            query.setParameter("devAdviceProofread", devAdviceProofread);
            query.setParameter("devId", devId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            System.out.println("就是我返回空指针啦！！");
            return null;
        }

        return "success";
    }


//    public String selectUserProject(String userName, String userList){
//        Session session = HibernateUtils.getCurrentSession();
//        String userStringList = "";
//        try{
//            session.beginTransaction();
//            String sqlFind = "select :userList from UserInfoEntity  where userName =:userName";
//            Query queryFind = session.createQuery(sqlFind);
//            queryFind.setParameter("userList", userList);
//            queryFind.setParameter("userName", userName);
//            userStringList = (String)queryFind.uniqueResult();
//            session.getTransaction().commit();
//
//        }catch (Exception e) {
//            System.out.println("Select返回null啦");
//            return null;
//        }
//
//        return userStringList;
//    }



    public String changeUserProofreadProject(String userName, String userStringList){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String sqlUpdate = "update UserInfoEntity set userProofread =:userStringList where userName =:userName";
            Query queryUpdate = session.createQuery(sqlUpdate);
            queryUpdate.setParameter("userStringList", userStringList);
            queryUpdate.setParameter("userName", userName);
            queryUpdate.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            System.out.println("change返回null啦");
            return null;
        }

        return "success";
    }


    public String changeUserAuditProject(String userName, String userStringList){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String sqlUpdate = "update UserInfoEntity set userAudit =:userStringList where userName =:userName";
            Query queryUpdate = session.createQuery(sqlUpdate);
            queryUpdate.setParameter("userStringList", userStringList);
            queryUpdate.setParameter("userName", userName);
            queryUpdate.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            System.out.println("change返回null啦");
            return null;
        }

        return "success";
    }


    public String changeUserAuthorizeProject(String userName, String userStringList){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String sqlUpdate = "update UserInfoEntity set userAuthorize =:userStringList where userName =:userName";
            Query queryUpdate = session.createQuery(sqlUpdate);
            queryUpdate.setParameter("userStringList", userStringList);
            queryUpdate.setParameter("userName", userName);
            queryUpdate.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            return null;
        }

        return "success";
    }


}
