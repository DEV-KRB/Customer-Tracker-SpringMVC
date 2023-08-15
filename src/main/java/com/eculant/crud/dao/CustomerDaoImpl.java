	package com.eculant.crud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eculant.crud.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//create a query
		Query<Customer> theQuery=currentSession.createQuery("from Customer", Customer.class);
		
		//execute query
		List<Customer> customers=theQuery.getResultList();
		
		//return  the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//save the customer
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//retrieve from database using primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//delete the customer
		Query theQuery=
				currentSession.createQuery("delete from customer where id=:id");
		theQuery.setParameter("id", theId);
		theQuery.executeUpdate();
	}

}
