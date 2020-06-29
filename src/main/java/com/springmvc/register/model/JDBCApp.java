package com.springmvc.register.model;

import com.springmvc.register.dao.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JDBCApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appCxt=new AnnotationConfigApplicationContext(JdbcConfigurattion.class);
        UserDao userDao =appCxt.getBean(UserDao.class);
        System.out.println(userDao.findAll());
        System.out.println(userDao.findById("auror"));
    }
}
