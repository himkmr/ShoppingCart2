package UtilPackage;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Product;
import model.ProductCart;
import model.ProductComment;
import model.ProductOrderHistory;
import model.ProductPaymentHistory;
import model.ProductUser;
import model.ProductUserCredit;



public class DBUtil {
	
private static final EntityManagerFactory emf =
Persistence.createEntityManagerFactory("Shopping_Cart");


 public static EntityManagerFactory getEmFactory()
 {  
	 return emf; 
} 
 public static void insert(ProductUser user) {
	 EntityManager em = DBUtil.getEmFactory().createEntityManager();
	 EntityTransaction trans = em.getTransaction();
	 trans.begin(); 
	 try {
	 em.persist(user);
	 trans.commit();
	 } catch (Exception e) {
	 System.out.println(e);
	 trans.rollback();
	 } finally {
	 em.close();
	 }
	 }
 
 public static void insert_in_Cart(ProductCart user) {
	 EntityManager em = DBUtil.getEmFactory().createEntityManager();
	 EntityTransaction trans = em.getTransaction();
	 trans.begin(); 
	 try {
	 em.persist(user);
	 trans.commit();
	 } catch (Exception e) {
	 System.out.println(e);
	 trans.rollback();
	 } finally {
	 em.close();
	 }
	 }
 
 public static void insert_in_user(ProductUserCredit user) {
	 EntityManager em = DBUtil.getEmFactory().createEntityManager();
	 EntityTransaction trans = em.getTransaction();
	 trans.begin(); 
	 try {
	 em.persist(user);
	 trans.commit();
	 } catch (Exception e) {
	 System.out.println(e);
	 trans.rollback();
	 } finally {
	 em.close();
	 }
	 }
 
 public static void insert_in_Cart(ProductOrderHistory user) {
	 EntityManager em = DBUtil.getEmFactory().createEntityManager();
	 EntityTransaction trans = em.getTransaction();
	 trans.begin(); 
	 try {
	 em.persist(user);
	 trans.commit();
	 } catch (Exception e) {
	 System.out.println(e);
	 trans.rollback();
	 } finally {
	 em.close();
	 }
	 }
 
 public static void insert_in_Cart(ProductPaymentHistory user) {
	 EntityManager em = DBUtil.getEmFactory().createEntityManager();
	 EntityTransaction trans = em.getTransaction();
	 trans.begin(); 
	 try {
	 em.persist(user);
	 trans.commit();
	 } catch (Exception e) {
	 System.out.println(e);
	 trans.rollback();
	 } finally {
	 em.close();
	 }
	 }
 
 
 public static void insert_in_Comments(ProductComment user) {
	 EntityManager em = DBUtil.getEmFactory().createEntityManager();
	 EntityTransaction trans = em.getTransaction();
	 trans.begin(); 
	 try {
	 em.persist(user);
	 trans.commit();
	 } catch (Exception e) {
	 System.out.println(e);
	 trans.rollback();
	 } finally {
	 em.close();
	 }
	 }
public static void delete_cart(String username) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String q = "delete from ProductCart p where p.username='"+username+"'";

		 EntityTransaction trans = em.getTransaction();
		 trans.begin(); 
		 try {
			 em.createQuery(q).executeUpdate();
			 trans.commit();
		 } catch (Exception e) {
		 System.out.println(e);
		 trans.rollback();
		 } finally {
		 em.close();
		 }
	
}
public static void delete_item(String id, String username) {
	EntityManager em = DBUtil.getEmFactory().createEntityManager();
	String q = "delete from ProductCart p where p.productId="+id+" and p.username='"+username+"'" ;

	 EntityTransaction trans = em.getTransaction();
	 trans.begin(); 
	 try {
		 em.createQuery(q).executeUpdate();
		 trans.commit();
	 } catch (Exception e) {
	 System.out.println(e);
	 trans.rollback();
	 } finally {
	 em.close();
	 }

}

public static void update_ser_credit(ProductUserCredit user) {
EntityManager em = DBUtil.getEmFactory().createEntityManager();
EntityTransaction trans = em.getTransaction();
trans.begin(); 
try {
em.merge(user);
trans.commit();
} catch (Exception e) {
System.out.println(e);
trans.rollback();
} finally {
em.close();
}
}


}
