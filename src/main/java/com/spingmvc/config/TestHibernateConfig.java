package com.spingmvc.config;

import com.springmvc.register.model.HUser;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class TestHibernateConfig {

    //        Creating session Factory
    SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(HUser.class).buildSessionFactory();
    Transaction transaction;

    public static void main(String[] args) {
    TestHibernateConfig testHibernateConfig=new TestHibernateConfig();
        testHibernateConfig.createObject();
//        testHibernateConfig.readObject();
//        testHibernateConfig.query("milo");
//        testHibernateConfig.update("milano","kumar");
//        testHibernateConfig.delete("rahulo");
    }

    public void createObject(){
        transaction=null;
//                Creating session
        Session session=sessionFactory.getCurrentSession();
         transaction=session.beginTransaction();
//        Creating Object
        try{
            HUser user=
                    new HUser("vikram","Singh","rahul@gmail.com","rahul@1234","milano");
            session.save(user);
            transaction.commit();
        }catch(HibernateException e){
            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }

    private void readObject() {
        transaction=null;
        Session session=sessionFactory.getCurrentSession();
        transaction=session.beginTransaction();
        try {
            HUser user = session.get(HUser.class, 1);
            System.out.println(user);
            transaction.commit();
        }catch(HibernateException e){
            if(transaction!=null)transaction.rollback();
        }finally{
            session.close();
        }
    }

    public void update(String username,String lastname){
        transaction=null;
        Session session=sessionFactory.getCurrentSession();
        transaction=session.beginTransaction();
        try {
            HUser user = (HUser) session.createQuery("from HUser s where s.username='" + username + "'").list().get(0);
            user.setLastName(lastname);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction!=null)transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void delete(String username){
        transaction=null;
//                Creating session
        Session session=sessionFactory.getCurrentSession();
        transaction=session.beginTransaction();
//        Creating Object
        try{
            HUser user = (HUser) session.createQuery("from HUser s where s.username='" + username + "'").list().get(0);
            session.delete(user);
            transaction.commit();
        }catch(HibernateException e){
            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }



    public void query(String username){
        transaction=null;
        Session session=sessionFactory.getCurrentSession();
        transaction=session.beginTransaction();
        try {
            List<HUser> listOfUsers = session.createQuery("from HUser s where s.username='" + username + "'").list();
            System.out.println(listOfUsers.size()==0);
            transaction.commit();
        }catch(HibernateException e){
            if(transaction!=null)transaction.rollback();
            e.printStackTrace();
        }
    }

}
