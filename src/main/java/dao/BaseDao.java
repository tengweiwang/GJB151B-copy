package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseDao {
    public static SessionFactory sessionFactory;
    static {
        //初始化hibernate配置管理类Configuration
        Configuration config = new Configuration().configure();
        //通过Configuration创建Session的工厂类SessionFacotry
        sessionFactory = config.buildSessionFactory();
    }

    public Session getSession(){
        Session session = null;

        if(sessionFactory != null){
            session = sessionFactory.openSession();
        }
        return session;
    }
}

