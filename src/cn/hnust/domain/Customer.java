package cn.hnust.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 客户的实体类
 * @author lw
 * 使用注解都是JPA规范，都要导入javax.persistance包下的
 * 
 */
@Entity
@Table(name="cst_customer")//建立当前类和数据库表对应关系
public class Customer implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id//表名当前字段是主键
	@Column(name="cust_id")//表明对应数据库的主键字段是cust_id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//指定主键的生成策略，自增
	private Long custId;
	
	@Column(name="cust_name")
	private String custName;
	
	@Column(name="cust_source")
	private String custSource;
	
	@Column(name="cust_industry")
	private String custIndustry;
	
	@Column(name="cust_level")
	private String custLevel;
	
	@Column(name="cust_address")
	private String custAddress;
	
	@Column(name="cust_phone")
	private String custPhone;
	
	/*
	 * 一对多关系映射，一的一方
	 * 主表实体类包含从表实体类的集合引用
	 */
	@OneToMany(targetEntity=LinkMan.class,mappedBy="customer",cascade=CascadeType.ALL,fetch=FetchType.LAZY)//targetEntity=LinkMan.class可不写
	private Set<LinkMan>  linkmans =  new HashSet<LinkMan>(0);
	
	public Set<LinkMan> getLinkmans() {
		return linkmans;
	}
	public void setLinkmans(Set<LinkMan> linkmans) {
		this.linkmans = linkmans;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustSource() {
		return custSource;
	}
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	public String getCustIndustry() {
		return custIndustry;
	}
	public void setCustIndustry(String custIndustry) {
		this.custIndustry = custIndustry;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custSource=" + custSource
				+ ", custIndustry=" + custIndustry + ", custLevel=" + custLevel + ", custAddress=" + custAddress
				+ ", custPhone=" + custPhone + "]";
	}
	
	

}
