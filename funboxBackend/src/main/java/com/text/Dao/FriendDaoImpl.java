package com.text.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.text.model.Friend;
import com.text.model.User;
@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {
	@Autowired
	private SessionFactory sessionFactory;
	public List<User> getSuggestedUsers(String email) {
		Session session=sessionFactory.getCurrentSession();
	
		SQLQuery query=session.createSQLQuery("select * from users where email in"
				+ "(select email from user_S123 where email!=?"
						+ " minus "
						+"(select toId_email from friend where fromId_email=? " 
						+" union "
						+"select fromId_email from friend where toId_email=? "
						+ ")"
						+")");
		query.setString(0, email);
		query.setString(1, email);
		query.setString(2, email);
		query.addEntity(User.class);
		List<User> suggestedUsers=query.list();
		return suggestedUsers;
	}
	public void addFriend(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
        session.save(friend);
		
	}
	public List<Friend> pendingRequests(String email) {
		Session session=sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Friend where toId.email=? and status=?");
        query.setString(0,email);
        query.setCharacter(1,'P');
        List<Friend> pendingRequests=query.list();
		return pendingRequests;
	}
	public void upadateStatus(Friend friendRequest) {//friendRequest status is updated
		Session session=sessionFactory.getCurrentSession();
		if(friendRequest.getStatus()=='A')
			session.update(friendRequest);
		if(friendRequest.getStatus()=='D')
			session.delete(friendRequest);

		
		
	}
	public List<Friend> getAllFriends(String email) {
		Session session=sessionFactory.getCurrentSession();
		//select toId_email from friend where fromId_email=? and status='A'-SQL
		//Type of toId property is User
		Query query1=session.createQuery("select f.toId from Friend f where f.fromId.email=? and f.status=?");//HQL
		query1.setString(0, email);
		query1.setCharacter(1, 'A');
		List<Friend> friendsList1=query1.list();
		
		Query query2=session.createQuery("select f.fromId from Friend f where f.toId.email=? and f.status=?");
		query2.setString(0, email);
		query2.setCharacter(1, 'A');
		List<Friend> friendsList2=query2.list();
		
		friendsList2.addAll(friendsList1);
		
		return friendsList2;
	}

}