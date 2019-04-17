package com.cts.foodster.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.foodster.bean.Login;

@Repository
@Transactional
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	LoginDAO logindao;
	
	@SuppressWarnings("unchecked")
	public int Authenticate(Login login) {
		// TODO Auto-generated method stub
		String userId = login.getUserId();
		String password= login.getPassword();
		Login login2 = new Login();
		Login login1 = new Login();
		try {
			Session session= sessionFactory.getCurrentSession();
			
			String query1="from Login where userId=?";
			org.hibernate.query.Query<Login> query3 = null;
			query3= session.createQuery(query1);
			query3.setParameter(0,userId);
			login1=query3.getSingleResult();
			
		//	System.out.println("5344312");
			
			if(login1!=null)
			{
				//System.out.println("%#");
				String query = "from Login where userId=? and password=?";
				
				org.hibernate.query.Query<Login> query2 = null;
				query2 = session.createQuery(query);
				query2.setParameter(0, userId);
				query2.setParameter(1, password);
				login2= query2.getSingleResult();
				
				if(login2!=null)
					return 1;
				
				
			}
			
			else
				return 0;
				
			
			
			
			
		} catch (Exception e) {
			
		//	System.out.println("53");
			return 0;
				
		}
	//	return 0;
		return 0;
	}
public String registerAdmin(Login login) {
		String ans = null;
		try {
			if(logindao.getUser(login.getUserId()) == null ){
			sessionFactory.getCurrentSession().save(login);
			ans = "yes";
			}
			else
				ans = "no";
		} catch (Exception e) {
			ans = "e";
		}finally {
			return ans;
		}
		
		
		
		
	}
public Login getUser(String id) {
	// TODO Auto-generated method stub
	Login login2 = new Login();
	try {
	Session session= sessionFactory.getCurrentSession();
	String query = "from Login where userId=?";
	org.hibernate.query.Query<Login> query2 = null;
	query2 = session.createQuery(query);
	query2.setParameter(0, id);
	login2= query2.getSingleResult();
} catch (Exception e) {
		login2 = null;
}finally{
	return login2;
}
}
@Override
public Login getProfile(Login login) {
	// TODO Auto-generated method stub
	String userId = login.getUserId();
	String password= login.getPassword();
	Login login2 = new Login();
	
	try {
		
		Session session= sessionFactory.getCurrentSession();
		String query = "from Login where userId=? and password=?";
		org.hibernate.query.Query<Login> query2 = null;
		query2 = session.createQuery(query);
		query2.setParameter(0, userId);
		query2.setParameter(1, password);
		login2= query2.getSingleResult();
		
		if(login2!=null)
			return login2;
		
		
		
	} catch (Exception e) {
		
		return null; 
		// TODO: handle exception
	}
	return null;
}

}
