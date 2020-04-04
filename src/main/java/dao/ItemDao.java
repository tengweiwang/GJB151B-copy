package dao;
import entity.ItemOperater;
import entity.ManageSysDevelopEntity;
import entity.UserInfoEntity;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDao extends BaseDao {
    private ItemDao() {
    }

    private static class ItemDaoHolder {
        private final static ItemDao instance = new ItemDao();
    }

    public static ItemDao getInstance() {
        return ItemDao.ItemDaoHolder.instance;
    }

    public List<ManageSysDevelopEntity> findAllItem() {
        try{
            String showList = "from ManageSysDevelopEntity";
            Session session = getSession();
            Query query = session.createQuery(showList);
            List<ManageSysDevelopEntity> list = query.list();
            session.close();
            return list;
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ItemOperater findOperater(String devItemId,ItemOperater itemOperater) {
        List<String> result;
        try{
            String showNew = "select userName from UserInfoEntity where userNew like ?";
            Session session = getSession();
            Query query = session.createQuery(showNew);
            query.setParameter(0,"%\""+devItemId+"\"%");
            result = query.list();
            String userNewResult = StringUtils.strip(result.toString(),"[]");
            itemOperater.setUserNew(userNewResult);
            session.close();

            String showProofread = "select userName from UserInfoEntity where userProofread like ?";
            session = getSession();
            query = session.createQuery(showProofread);
            query.setParameter(0,"%\""+devItemId+"\"%");
            result = query.list();
            String userProofreadResult = StringUtils.strip(result.toString(),"[]");
            itemOperater.setUserProofread(userProofreadResult);
            session.close();

            String showAudit = "select userName from UserInfoEntity where userAudit like ?";
            session = getSession();
            query = session.createQuery(showAudit);
            query.setParameter(0,"%\""+devItemId+"\"%");
            result = query.list();
            String userAuditResult = StringUtils.strip(result.toString(),"[]");
            itemOperater.setUserAudit(userAuditResult);
            session.close();

            String showAuthorize = "select userName from UserInfoEntity where userAuthorize like ?";
            session = getSession();
            query = session.createQuery(showAuthorize);
            query.setParameter(0,"%\""+devItemId+"\"%");
            result = query.list();
            String userAuthorizeResult = StringUtils.strip(result.toString(),"[]");
            itemOperater.setUserAuthorize(userAuthorizeResult);
            session.close();

            return itemOperater;
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }

    }
public ManageSysDevelopEntity checkItem(String devName){
        try{
            Session session = getSession();
            String sql = "from ManageSysDevelopEntity where devName=:devName";
            Query query = session.createQuery(sql);
            query.setParameter("devName", devName);
            ManageSysDevelopEntity s = (ManageSysDevelopEntity) query.uniqueResult();
            session.close();
            return s;
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
}
    public String addItem(ManageSysDevelopEntity s){
        try {
            Session session = getSession();
            Transaction trans = session.beginTransaction();
            session.save(s);
            session.flush();
            trans.commit();
            session.close();
            return(s.getDevItemid());
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String addOperater(String devItemid, String user_new, String user_proofread, String user_audit, String user_authorize){
        try {
            Session session = getSession();
            String sql = "from UserInfoEntity where userName=:userName";
            Query query = session.createQuery(sql);
            query.setParameter("userName", user_new);
            UserInfoEntity s = (UserInfoEntity) query.uniqueResult();
            session.close();
            if(s.getUserNew().equals("[]")){
                session = getSession();
                Transaction trans = session.beginTransaction();
                s.setUserNew("[\""+devItemid+"\"]");
                session.update(s);
                //session.flush();
                trans.commit();
                session.close();
            }
            else{
                session = getSession();
                String sql1="UPDATE user_info SET user_new = replace (user_new,']',',\""+devItemid+"\"]') where user_name=\""+user_new+"\"";
                SQLQuery sqlQuery=session.createSQLQuery(sql1);
                int num1=sqlQuery.executeUpdate();
                session.close();
            }

            session = getSession();
            sql = "from UserInfoEntity where userName=:userName";
            query = session.createQuery(sql);
            query.setParameter("userName", user_proofread);
            s = (UserInfoEntity) query.uniqueResult();
            session.close();
            if(s.getUserProofread().equals("[]")){
                session = getSession();
                Transaction trans = session.beginTransaction();
                s.setUserProofread("[\""+devItemid+"\"]");
                session.update(s);
                session.flush();
                trans.commit();
                session.close();
            }
            else{
                session = getSession();
                String sql2="UPDATE user_info SET user_proofread = replace (user_proofread,']',',\""+devItemid+"\"]') where user_name=\""+user_proofread+"\"";
                SQLQuery sqlQuery=session.createSQLQuery(sql2);
                int num2=sqlQuery.executeUpdate();
                session.close();
            }

            session = getSession();
            sql = "from UserInfoEntity where userName=:userName";
            query = session.createQuery(sql);
            query.setParameter("userName", user_audit);
            s = (UserInfoEntity) query.uniqueResult();
            session.close();
            if(s.getUserAudit().equals("[]")){
                session = getSession();
                Transaction trans = session.beginTransaction();
                s.setUserAudit("[\""+devItemid+"\"]");
                session.update(s);
                session.flush();
                trans.commit();
                session.close();
            }
            else{
                session = getSession();
                String sql2="UPDATE user_info SET user_audit = replace (user_audit,']',',\""+devItemid+"\"]') where user_name=\""+user_audit+"\"";
                SQLQuery sqlQuery=session.createSQLQuery(sql2);
                int num3=sqlQuery.executeUpdate();
                session.close();
            }

            session = getSession();
            sql = "from UserInfoEntity where userName=:userName";
            query = session.createQuery(sql);
            query.setParameter("userName", user_authorize);
            s = (UserInfoEntity) query.uniqueResult();
            session.close();
            if(s.getUserAuthorize().equals("[]")){
                session = getSession();
                Transaction trans = session.beginTransaction();
                s.setUserAuthorize("[\""+devItemid+"\"]");
                session.update(s);
                session.flush();
                trans.commit();
                session.close();
            }
            else{
                session = getSession();
                String sql2="UPDATE user_info SET user_authorize = replace (user_authorize,']',',\""+devItemid+"\"]') where user_name=\""+user_authorize+"\"";
                SQLQuery sqlQuery=session.createSQLQuery(sql2);
                int num4=sqlQuery.executeUpdate();
                session.close();
            }
            return "success";
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String deleteItem(String devName){
        try {
            Session session = getSession();
            String sql = "from ManageSysDevelopEntity where devName=:devName";
            Query query = session.createQuery(sql);
            query.setParameter("devName", devName);
            ManageSysDevelopEntity s = (ManageSysDevelopEntity) query.uniqueResult();
            session.close();
            session = getSession();
            Transaction ts = session.beginTransaction();
            session.delete(s);
            session.flush();
            ts.commit();
            session.close();
            return(s.getDevItemid());
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteItemInUser_new(String devItemid){
        try {
            Session session = getSession();
            String sql1="UPDATE user_info SET user_new = replace (user_new,',\""+devItemid+"\"','')";
            SQLQuery sqlQuery=session.createSQLQuery(sql1);
            int num1=sqlQuery.executeUpdate();
            session.close();
            session = getSession();
            String sql2="UPDATE user_info SET user_new = replace (user_new,'\""+devItemid+"\",','')";
            sqlQuery=session.createSQLQuery(sql2);
            int num2=sqlQuery.executeUpdate();
            session.close();
            session = getSession();
            String sql3="UPDATE user_info SET user_new = replace (user_new,'\""+devItemid+"\"','')";
            sqlQuery=session.createSQLQuery(sql3);
            int num3=sqlQuery.executeUpdate();
            session.close();
            return "success";
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String deleteItemInUser_proofread(String devItemid){
        try {
            Session session = getSession();
            String sql1="UPDATE user_info SET user_proofread = replace (user_proofread,',\""+devItemid+"\"','')";
            SQLQuery sqlQuery=session.createSQLQuery(sql1);
            int num1=sqlQuery.executeUpdate();
            session.close();
            session = getSession();
            String sql2="UPDATE user_info SET user_proofread = replace (user_proofread,'\""+devItemid+"\",','')";
            sqlQuery=session.createSQLQuery(sql2);
            int num2=sqlQuery.executeUpdate();
            session.close();
            session = getSession();
            String sql3="UPDATE user_info SET user_proofread = replace (user_proofread,'\""+devItemid+"\"','')";
            sqlQuery=session.createSQLQuery(sql3);
            int num3=sqlQuery.executeUpdate();
            session.close();
            return "success";

        }
        catch (HibernateException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String deleteItemInUser_audit(String devItemid){
        try {
            Session session = getSession();
            String sql1="UPDATE user_info SET user_audit = replace (user_audit,',\""+devItemid+"\"','')";
            SQLQuery sqlQuery=session.createSQLQuery(sql1);
            int num1=sqlQuery.executeUpdate();
            session.close();
            session = getSession();
            String sql2="UPDATE user_info SET user_audit = replace (user_audit,'\""+devItemid+"\",','')";
            sqlQuery=session.createSQLQuery(sql2);
            int num2=sqlQuery.executeUpdate();
            session.close();
            session = getSession();
            String sql3="UPDATE user_info SET user_audit = replace (user_audit,'\""+devItemid+"\"','')";
            sqlQuery=session.createSQLQuery(sql3);
            int num3=sqlQuery.executeUpdate();
            session.close();
            return "success";
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String deleteItemInUser_authorize(String devItemid){
        try {
            Session session = getSession();
            String sql1="UPDATE user_info SET user_authorize = replace (user_authorize,',\""+devItemid+"\"','')";
            SQLQuery sqlQuery=session.createSQLQuery(sql1);
            int num1=sqlQuery.executeUpdate();
            session.close();
            session = getSession();
            String sql2="UPDATE user_info SET user_authorize = replace (user_authorize,'\""+devItemid+"\",','')";
            sqlQuery=session.createSQLQuery(sql2);
            int num2=sqlQuery.executeUpdate();
            session.close();
            session = getSession();
            String sql3="UPDATE user_info SET user_authorize = replace (user_authorize,'\""+devItemid+"\"','')";
            sqlQuery=session.createSQLQuery(sql3);
            int num3=sqlQuery.executeUpdate();
            session.close();
            return "success";

        }
        catch (HibernateException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String updateItemName(String devItemid, String devName){
        try{
            Session session = getSession();
            Transaction trans = session.beginTransaction();
            String sql = "from ManageSysDevelopEntity where devItemid=:devItemid";
            Query query = session.createQuery(sql);
            query.setParameter("devItemid", devItemid);
            ManageSysDevelopEntity s = (ManageSysDevelopEntity) query.uniqueResult();
            s.setDevName(devName);
            session.update(s);
            session.flush();
            trans.commit();
            session.close();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
        return "success";
    }

    public ManageSysDevelopEntity getItemByDevItemId(String devItemid){
        try{
            Session session = getSession();
            String sql = "from ManageSysDevelopEntity where devItemid=:devItemid";
            Query query = session.createQuery(sql);
            query.setParameter("devItemid", devItemid);
            ManageSysDevelopEntity s = (ManageSysDevelopEntity) query.uniqueResult();
            session.close();
            return s;
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ManageSysDevelopEntity getItemByDevId(int devId){
        try{
            Session session = getSession();
            String sql = "from ManageSysDevelopEntity where devId=:devId";
            Query query = session.createQuery(sql);
            query.setParameter("devId", devId);
            ManageSysDevelopEntity s = (ManageSysDevelopEntity) query.uniqueResult();
            session.close();
            return s;
        }
        catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}
