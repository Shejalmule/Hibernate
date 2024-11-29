package com.crudOperation;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class CrudOperationDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//createRecord();
		//getRecord();
		//getAllRecord();
		updateRecord();
		
	
	}
	static void createRecord()
	{
		Configuration con=new Configuration();
		SessionFactory sf = con.configure("hibernate.cfg.xml").buildSessionFactory();
		Session sess = sf.getCurrentSession();
		Transaction trans = sess.beginTransaction();
		
		Country c1=new Country(1,"india","pune");
		Country c2=new Country(2,"America","LosAngels");
		Country c3=new Country(3,"Japan","Ruis");
		Country c4=new Country(4,"China","Howkinks");
//		
//		sess.merge(c1);
//		sess.merge(c2);
//		sess.merge(c3);
//		sess.merge(c4);
//		trans.commit();
			
	}
	
	static void getRecord()
	{
		Configuration con=new Configuration();
		SessionFactory sf = con.configure("hibernate.cfg.xml").buildSessionFactory();
		Session sess = sf.getCurrentSession();
		Transaction trans = sess.beginTransaction();
		
//		
//		Country result = sess.get(Country.class,10);
//		System.out.println(result);
//		System.out.println("end of program");
		
		Country result=sess.load(Country.class, 10);
		System.out.println(result);
		System.out.println("end of programm");
	}
	
	static void getAllRecord()
	{
		Configuration con=new Configuration();
		SessionFactory sf = con.configure("hibernate.cfg.xml").buildSessionFactory();
		Session sess = sf.getCurrentSession();
		Transaction trans = sess.beginTransaction();
		
		Criteria record = sess.createCriteria(Country.class);
//		List<Country> result = record.list();
//		System.out.println(result);
//		
		Criteria record1 = record.add(Restrictions.eq("name", "America"));
		List<Country> result = record.list();
		System.out.println(result);
	}
	
	static void updateRecord()
	{
		Configuration con=new Configuration();
		SessionFactory sf = con.configure("hibernate.cfg.xml").buildSessionFactory();
		Session sess = sf.getCurrentSession();
		Transaction trans = sess.beginTransaction();
		
		try
		{
			Country rec = sess.get(Country.class,1);
			if(rec!=null)
			{
				rec.setName("alex");
				rec.setCity("hyderabad");
				
				sess.update(rec);
				trans.commit();
				System.out.println("Record updated successfully: ");
            } else {
                System.out.println("No record found with ID: ");
            }
		 } 
		catch (Exception e) {
	            trans.rollback();
	            e.printStackTrace();
	        } 
		finally {
	            sess.close();
	        }
				
			}

}
@Entity
@Table(name = "Country_info") 
class Country
{
	@Id
	int id;
	String name;
	String city;
	
	Country()
	{
		
	}

	public Country(int id, String name, String city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", city=" + city + "]\n";
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
}
