package com.crudOperation;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OperationsDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//addData();
		//getData();
		//getAllData();
		deleteData();
	}

	static void addData()
	{

		Configuration con=new Configuration();
		 SessionFactory sf = con.configure("hibernate.cfg.xml").buildSessionFactory();
		 Session sess = sf.getCurrentSession();
		 Transaction trans = sess.beginTransaction();
		 
		 Country1 c1=new Country1(101,"India","Amravati","N");
		 Country1 c2=new Country1(102,"America","LosAngeles","N");
		 Country1 c3=new Country1(103,"Japan","Tokyo","N");
		 Country1 c4=new Country1(104,"China","Nashir","N");
		 
		 sess.merge(c1);
		 sess.merge(c2);
		 sess.merge(c3);
		 sess.merge(c4); 
		 
		 trans.commit();
		 System.out.println("end of programm");
	}
	
	static void getData()
	{

		Configuration con=new Configuration();
		 SessionFactory sf = con.configure("hibernate.cfg.xml").buildSessionFactory();
		 Session sess = sf.getCurrentSession();
		 Transaction trans = sess.beginTransaction();
		 
		 Country1 obj1 = sess.get(Country1.class, 110);
		 System.out.println(obj1);
		 
		 Country1 obj2 = sess.load(Country1.class, 110);
		 System.out.println(obj2);		 
   }
	static void getAllData()
	{
		Configuration con=new Configuration();
		 SessionFactory sf = con.configure("hibernate.cfg.xml").buildSessionFactory();
		 Session sess = sf.getCurrentSession();
		 Transaction trans = sess.beginTransaction();
		 
		 Criteria obj1 = sess.createCriteria(Country1.class);
		 List<Country1> l1=obj1.list();
		 System.out.println(l1);
	}
	static void deleteData()
	{

		Configuration con=new Configuration();
		 SessionFactory sf = con.configure("hibernate.cfg.xml").buildSessionFactory();
		 Session sess = sf.getCurrentSession();
		 Transaction trans = sess.beginTransaction();
		 
	     Country1 obj1 = sess.get(Country1.class, 104);	 
	     obj1.setDeleted("Y");
   }
}
@Entity
@Table(name = "country_all_info")
class Country1
{
	@Id
	int id;
	String name;
	String city;
	@Column(name = "deleted",columnDefinition = "varchar(4) default 'N'") 
	String deleted="N";
	
	public Country1() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public Country1(int id, String name, String city, String deleted) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Country1 [id=" + id + ", name=" + name + ", city=" + city + ", deleted=" + deleted + "]\n";
	}	
}