package cn.hnust.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Test;

import cn.hnust.domain.Customer;
import cn.hnust.util.JPAUtil;

/**
 * JPACRUD测试
 * @author 龙伟
 * 2018年9月18日
 */
public class JPADemo1 {
	
	/*
	 * 保存
	 */
	@Test
	public void test1() {
		//创建客户对象
		Customer c = new Customer();
		c.setCustName("JPA测试test1");
		//1.获取EntityManager对象
		EntityManager em = JPAUtil.createEntityManager();
		//2.获取事务对象，并开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.执行保存操作
		em.persist(c);
		//4.提交事务
		tx.commit();
		//5.关闭资源
		em.close();
	}
	/*
	 * 查询一个实体  立即加载
	 */
	@Test
	public void test2() {
		//1.获取EntityManager对象
		EntityManager em = JPAUtil.createEntityManager();
		//2.获取事务对象，并开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.执行查询操作
		Customer c = em.find(Customer.class, 1L);//find立即加载
		System.out.println(c);
		//4.提交事务
		tx.commit();
		//5.关闭资源
		em.close();
	}
	/*
	 * 查询一个实体  延迟加载
	 */
	@Test
	public void test3() {
		//1.获取EntityManager对象
		EntityManager em = JPAUtil.createEntityManager();
		//2.获取事务对象，并开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.执行查询操作
		Customer c = em.getReference(Customer.class, 1L);//getReference延迟加载
		System.out.println(c);
		//4.提交事务
		tx.commit();
		//5.关闭资源
		em.close();
	}
	/*
	 * 更新
	 */
	@Test
	public void test4() {
		//1.获取EntityManager对象
		EntityManager em = JPAUtil.createEntityManager();
		//2.获取事务对象，并开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.执行查询操作
		Customer c = em.find(Customer.class, 1L);
		c.setCustAddress("长沙");
		//4.提交事务
		tx.commit();
		//5.关闭资源
		em.close();
	}
	/*
	 * 更新另外的操作
	 * merge是合并，（两个实体合并）
	 */
	@Test
	public void test5() {
		//1.获取EntityManager对象
		EntityManager em = JPAUtil.createEntityManager();
		//2.获取事务对象，并开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.执行查询操作
		Customer c = em.find(Customer.class, 1L);
		c.setCustAddress("湘潭");
		em.merge(c);
		//4.提交事务
		tx.commit();
		//5.关闭资源
		em.close();
	}
	/*
	 * 删除
	 */
	@Test
	public void test6() {
		//1.获取EntityManager对象
		EntityManager em = JPAUtil.createEntityManager();
		//2.获取事务对象，并开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.执行查询操作,然后再删除
		Customer c = em.getReference(Customer.class, 1L);//getReference延迟加载
		em.remove(c);
		//4.提交事务
		tx.commit();
		//5.关闭资源
		em.close();
	}
	/*
	 * 查询所有
	 * 涉及对象是
	 * 		JPA的Query
	 * 如何获取：
	 * 		EntityMananger的createQuery(String jpql)
	 * 参数含义：
	 * 		jpql:Java Persistence Query Language
	 * 		写法和HQL很相似，也是把表名换成类名，把字段名换成属性名称
	 * 		它在写查询所有时，不能直接用      from 实体类
	 * 		需要select关键字    select c from  Customer  c
	 */
	@Test
	public void test7() {
		//1.获取EntityManager对象
		EntityManager em = JPAUtil.createEntityManager();
		//2.获取事务对象，并开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.获取JPA的查询对象Query
		//Query query = em.createQuery( "select c from  Customer  c");
		Query query = em.createQuery( "select c from  Customer  c where custName like ? and custLevel=?");
		//给占位符赋值，从1开始
		query.setParameter(1, "%长%");
		query.setParameter(2, "6");
		List list = query.getResultList();
		for(Object o: list) {
			System.out.println(o);
		}
		//4.提交事务
		tx.commit();
		//5.关闭资源
		em.close();
	}
}
