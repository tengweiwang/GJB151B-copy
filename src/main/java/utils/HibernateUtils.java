package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;

import java.util.List;

final public class HibernateUtils {
    private static SessionFactory sessionFactory=null;

    private static ThreadLocal<Session> threadLocal=new ThreadLocal<Session>();
    private HibernateUtils(){};
    static {
        System.out.println(">>>>>>>>> init impl");
        sessionFactory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        System.out.println(">>>>>>>>> init impl222");
    }


    public static Session openSession(){
        return sessionFactory.openSession();
    }

    public static Session getCurrentSession(){

        Session session=threadLocal.get();

        if(session==null){
            session=sessionFactory.openSession();

            threadLocal.set(session);
        }
        return session;


    }

    public static void closeCurrentSession(){

        Session s=getCurrentSession();

        if(s!=null&& s.isOpen() ){
            s.close();
            threadLocal.set(null);
        }
    }

    /**
     * 查找单一
     * @param hql
     * @param parameters
     * @return
     */
    public static Object uniqueQuery(String hql,String [] parameters){

        Session s = null;
        Object obj = null;

        try {

            s = openSession();

            Query query = s.createQuery(hql);
            //先判断是否有参数要绑定
            if(parameters != null && parameters.length > 0){
                for(int i=0 ; i < parameters.length; i++){
                    query.setParameter(i, parameters[i]);
                }
            }
            /**
             * 若使用query.getSingleResult()
             * 如果查询到的object为null 则会直接异常
             */
            obj = query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally{
            if(s!=null&&s.isOpen()){
                s.close();
            }

        }
        return obj;
    }


    public static List executeQuery(String hql,String [] parameters){

        Session s = null;
        List list = null;

        try {
            s = openSession();
            Query query = s.createQuery(hql);
            //先判断是否有参数要绑定
            if(parameters!=null&& parameters.length>0){
                for(int i=0;i<parameters.length;i++){
                    query.setParameter(i, parameters[i]);
                }
            }
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally{

            if(s!=null&&s.isOpen()){
                s.close();
            }

        }
        return list;
    }

}