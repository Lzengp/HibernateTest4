package cn.hnust.test;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import cn.hnust.domain.Customer;
import cn.hnust.domain.LinkMan;
import cn.hnust.util.JPAUtil;

/**
 * JPA延迟加载与立即加载
 * 	它可以使用对象导航的方式
 * @author 龙伟
 * 2018年9月18日
 */
public class JPADemo3 {
	
	/*
	 * 根据客户查询客户下的联系人(延迟加载)
	 * 在主表实体类设置fetch=FetchType.EAGER便会变成立即加载
	 */
	@Test
	public void test1() {
		EntityManager em = JPAUtil.createEntityManager();
		EntityTransaction tx  = em.getTransaction();
		tx.begin();
		//查询id为1的客户
		Customer c = em.find(Customer.class, 1L);
		System.out.println(c);
		Set<LinkMan> linkmans = c.getLinkmans();
		System.out.println(linkmans);
		
		tx.commit();
		em.close();
	}

	/*
	 * 根据联系人查询所属的客户(立即加载)
	 * 在主表实体类设置fetch=FetchType.LAZY便会变成延迟加载
	 */
	@Test
	public void test2() {
		EntityManager em = JPAUtil.createEntityManager();
		EntityTransaction tx  = em.getTransaction();
		tx.begin();
		//查询id为1的联系人
		LinkMan linkmans = em.find(LinkMan.class, 1L);
		System.out.println(linkmans);
		//根据联系人得到所属的客户
		Customer c  = linkmans.getCustomer();
		System.out.println(c);
		
		tx.commit();
		em.close();
	}
}











