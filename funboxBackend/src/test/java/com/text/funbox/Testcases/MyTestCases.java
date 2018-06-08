package com.text.funbox.Testcases;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.text.Dao.UserDao;
import com.text.model.User;

public class MyTestCases {

	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static UserDao userDao;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.text");
		context.refresh();

		// get the productDAO from context

		userDao = (UserDao) context.getBean("userDao");

	}

	@Ignore
	@Test
	public void createUserTestCase() {
		User user = new User();
		user.setEmail("sravankumar@abc.com");
		user.setPassword("kumar");
		user.setFirstname("sravan");
		user.setLastname("kadi");
		user.setPhonenumber("7989330914");

		userDao.registerUser(user);

		assertEquals(user.getEmail(), user.getEmail());

	}

	@Test
	public void uniqueEmailIdTest() {

		User user = new User();
		user.setEmail("sravan@abc.com");
		boolean status = userDao.isEmailUnique(user.getEmail());
		System.out.println("Email Is Unique");
		assertEquals("unique email id failure", false, status);
	}

	@Test
	public void getUserDetails() {

		String userEmail = "sravan@abc.com";

		User user = new User();

		user = userDao.getUser(userEmail);
		System.out.println("\n First Name " + user.getFirstname());
		System.out.println("\n Last Name " + user.getLastname());
		System.out.println("\n Phone Number" + user.getPhonenumber());
		assertEquals(userEmail, userDao.getUser(userEmail));

	}

}
