package cn.hnust.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import cn.hnust.domain.Customer;
import cn.hnust.domain.LinkMan;
import cn.hnust.util.JPAUtil;

/**
 * JPA一对多测试
 * @author 龙伟
 * 2018年9月18日
 */
public class JPADemo2 {
	
	/*
	 * 创建一个客户和一个联系人
	 * 建立客户和联系人的双向关联关系
	 * 先保存客户，再保存联系人
	 */
	@Test
	public void test1() {
		//创建一个客户和一个联系人
		Customer c = new Customer();
		c.setCustName("JPA一对多测试test1");
		LinkMan lkm = new LinkMan();
		lkm.setLkmName("JPA一对多测试test1");
		//建立客户和联系人的双向关联关系
		c.getLinkmans().add(lkm);
		lkm.setCustomer(c);
		//获取EntityManager对象
		EntityManager em = JPAUtil.createEntityManager();
		//获取事务对象，并开启事务
		EntityTransaction tx  = em.getTransaction();
		tx.begin();
		//先保存客户，再保存联系人
		em.persist(c);
		em.persist(lkm);
		//提交事务
		tx.commit();
		//关闭资源
		em.close();
	}
	/*
	 * 更新操作
	 * 创建一个联系人
	 * 查询id为1的客户
	 * 为1这个客户分配该联系人
	 * 更新客户
	 */
	@Test
	public void test2() {
		//创建一个联系人		
		LinkMan lkm = new LinkMan();
		lkm.setLkmName("JPA一对多测试test2");
		//获取EntityManager对象
		EntityManager em = JPAUtil.createEntityManager();
		//获取事务对象，并开启事务
		EntityTransaction tx  = em.getTransaction();
		tx.begin();
		//查询id为1的客户
		Customer c = em.find(Customer.class, 1L);
		//为1这个客户分配该联系人(双向)
		c.getLinkmans().add(lkm);
		lkm.setCustomer(c);
		//更新客户
		em.merge(c);
		//提交事务
		tx.commit();
		//关闭资源
		em.close();
	}
	/*
	 * 删除
	 */
	@Test
	public void test3() {
		//获取EntityManager对象
		EntityManager em = JPAUtil.createEntityManager();
		//获取事务对象，并开启事务
		EntityTransaction tx  = em.getTransaction();
		tx.begin();
		//查询id为1的客户
		Customer c = em.find(Customer.class, 1L);
		em.remove(c);
		//提交事务
		tx.commit();
		//关闭资源
		em.close();
	}
}











