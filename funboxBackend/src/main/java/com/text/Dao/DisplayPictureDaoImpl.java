package com.text.Dao;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.text.model.DisplayPicture;
@Repository
@Transactional
public class DisplayPictureDaoImpl implements DisplayPictureDao {
	  @Autowired
	  private SessionFactory sessionFactory; 
	public void saveDisplayPicture(DisplayPicture displayPicture) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(displayPicture);
	}

	public DisplayPicture getDisplayPicture(String email) {
		Session session=sessionFactory.getCurrentSession();
		DisplayPicture displayPicture=(DisplayPicture)session.get(DisplayPicture.class, email);
		
		return displayPicture;
	}
	
		

} 