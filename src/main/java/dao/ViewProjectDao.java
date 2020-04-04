package dao;


import entity.ViewProject;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * 从项目表中获取每个用户的所有项目的项目完整信息
 */
public class ViewProjectDao {
    public ViewProjectDao() {
    }

    private static class ViewProjectDaoHolder {
        private final static ViewProjectDao instance = new ViewProjectDao();
    }

    public static ViewProjectDao getInstance() {
        return ViewProjectDaoHolder.instance;
    }


    public List<ViewProject> viewAll(List<String> ids){
        Session session = HibernateUtils.getCurrentSession();

        try {
            if(ids != null && ids.size()!=0){
            String sql = "select new entity.ViewProject(devName, devStatus, devItemid, devId) from ManageSysDevelopEntity WHERE devItemid IN (:ids)";
            session.beginTransaction();
            Query query = session.createQuery(sql);
            query.setParameterList("ids", ids);
            session.getTransaction().commit();
            List<ViewProject> projects = query.list();
            return projects;}else {
                List<ViewProject> nullList = new ArrayList<>();
                return nullList;
            }

        }catch (Exception e) {
            return null;

        }
    }
}
