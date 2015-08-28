package UtilPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



import model.Mytwitter;
import model.TwitterUser2;


public class DBUtil {
	
private static final EntityManagerFactory emf =
Persistence.createEntityManagerFactory("Bullhorn3");


 public static EntityManagerFactory getEmFactory()
 {  
	 return emf; 
	 } 

 public static void insert(Mytwitter user) {
	 EntityManager em = DBUtil.getEmFactory().createEntityManager();
	 EntityTransaction trans = em.getTransaction();
	 trans.begin(); 
	 try {
		 em.persist(user);
		 trans.commit();
	 } 
	 catch (Exception e) {
		 System.out.println(e);
		 trans.rollback();
	 } finally {
	 em.close();
	 }
	 }
 
 public static void insert2(TwitterUser2 user) {
	 EntityManager em = DBUtil.getEmFactory().createEntityManager();
	 EntityTransaction trans = em.getTransaction();
	 trans.begin(); 
	 try {
		 em.persist(user);
		 trans.commit();
	 } 
	 catch (Exception e) {
		 System.out.println(e);
		 trans.rollback();
	 } finally {
	 em.close();
	 }
	 }
 
}

