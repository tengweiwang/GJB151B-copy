package dao;


import entity.ManageSysPdfEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @Author: fcupup 875894948@qq.com
 * @Data: Created on 2:51 PM 2019/7/18
 * @Modified By:
 */
public class GeneratePdfDao extends BaseDao {
    public GeneratePdfDao() {
    }

    /*
    定义一个私有的内部类，在第一次用这个嵌套类时，会创建一个实例。而类型为LoginDaoHolder的类，只有在LoginDao.getInstance()中调用，
    由于私有的属性，他人无法使用LoginDaoHolder，不调用LoginDao.getInstance()就不会创建实例。
    优点：达到了lazy loading的效果，即按需创建实例。
     */
    private static class GeneratePdfDaoHolder {
        private final static GeneratePdfDao instance = new GeneratePdfDao();
    }

    public static GeneratePdfDao getInstance() {
        return GeneratePdfDaoHolder.instance;
    }

    //添加一行数据
    public void addData(ManageSysPdfEntity manageSysPdfEntity) {
        try {
            Session session = getSession();
            Transaction trans = session.beginTransaction();
            session.save(manageSysPdfEntity);
            session.flush();
            trans.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //得到最后一行数据
    public ManageSysPdfEntity getLastData() {
        String sql = "from ManageSysPdfEntity where id = (select max(id) from ManageSysPdfEntity)";
        Session session = getSession();
        Transaction trans = session.beginTransaction();
        Query query = session.createQuery(sql);
        ManageSysPdfEntity manageSysPdfEntity = (ManageSysPdfEntity) query.uniqueResult();
        session.close();
        return manageSysPdfEntity;
    }

    //得到所有数据
    public List<ManageSysPdfEntity> getAllData() {
        String sql = "from ManageSysPdfEntity";
        Session session = getSession();
        Query query = session.createQuery(sql);
        List<ManageSysPdfEntity> pdfEntityArrayList = query.list();
        session.close();
        return pdfEntityArrayList;
    }

    //依据title模糊匹配pdf行数据
    public ManageSysPdfEntity getDataByTitle(String title) {
        String sql = "from ManageSysPdfEntity where title like :title";
        Session session = getSession();
        Query query = session.createQuery(sql);
        query.setParameter("title", "%" + title + "%");
        ManageSysPdfEntity manageSysPdfEntity = (ManageSysPdfEntity) query.uniqueResult();
        session.close();
        return manageSysPdfEntity;
    }

    //依据title得到此title所在页码
    public Integer getPageNumberByTitle(String title) {
        ManageSysPdfEntity manageSysPdfEntity = getDataByTitle(title);
        int id = manageSysPdfEntity.getId() - 1;
        String sql = "from ManageSysPdfEntity where id =:id";
        Session session = getSession();
        Query query = session.createQuery(sql);
        query.setParameter("id", id);
        ManageSysPdfEntity res = (ManageSysPdfEntity) query.uniqueResult();
        return res.getEndPageNumber() + 1;
    }

    //清空数据表数据
    public void deleteAllData() {
        String sql = "delete from ManageSysPdfEntity";
        Session session = getSession();
        Transaction trans = session.beginTransaction();
        Query query = session.createQuery(sql);
        query.executeUpdate();
        trans.commit();
        session.close();
    }
}
