package ru.stqa.pft.mantis.appmanager;

//import biz.futureware.mantis.rpc.soap.client.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.MantisUsers;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;

public class DBHelper {

  private final SessionFactory sessionFactory;

  public DBHelper() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            //./src/main/resources/Test.txt
            //.configure()
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public List<UserData> users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery("from UserData where access_level != '90'").list();
    session.getTransaction().commit();
    session.close();
    return (List<UserData>) result;
    //return new MantisUsers(result);
  }
}

