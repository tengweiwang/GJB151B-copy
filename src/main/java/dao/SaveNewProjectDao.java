package dao;

import entity.ManageSysDevelopEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

/**
 * Created by ddgdd on 2018/10/12 0012 17:17
 */
public class SaveNewProjectDao {

    private static class SaveNewProjectDaoHolder {
        private final static SaveNewProjectDao instance = new SaveNewProjectDao();
    }

    public static SaveNewProjectDao getInstance() {
        return SaveNewProjectDaoHolder.instance;
    }

    public String saveNewProject(ManageSysDevelopEntity manSysDev) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
//            session.update(manSysDev);
            session.clear();
            session.merge(manSysDev);
            tx.commit();
        } catch (Exception e) {
            return "error";
        }

        return "success";
    }

    public ManageSysDevelopEntity findProjectById(int id){
        Session session = HibernateUtils.getCurrentSession();
        ManageSysDevelopEntity manageSysDevelopEntity = null;
        try {
            session.beginTransaction();
            session.clear();
            String sql = "select manSysDev from ManageSysDevelopEntity manSysDev where devId=:id";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            session.getTransaction().commit();
            manageSysDevelopEntity = (ManageSysDevelopEntity)query.uniqueResult();
//            session.getTransaction().commit();
//            session.close();
        } catch (Exception e) {
            return null;
        }

        return manageSysDevelopEntity;
    }
}
