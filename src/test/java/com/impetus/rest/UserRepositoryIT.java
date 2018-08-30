package com.impetus.rest;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.impetus.rest.common.IntegrationTestProfileResolver;
import com.impetus.rest.domain.User;
import com.impetus.rest.repo.UserRepository;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DBTestConfig.class, webEnvironment = WebEnvironment.NONE)
@ActiveProfiles(resolver = IntegrationTestProfileResolver.class)
@Transactional
public class UserRepositoryIT {

	@Autowired
	private UserRepository fixture;

	@Test
	public void findAlll() {
		List<User> actual = fixture.findAll();
		Assert.assertNotNull(actual);
	}

	@Test
	public void testInsertAndFindOne() {
		insertUser();
		User userActual = this.fixture.findOne()
	}

	private User insertUser() {
		User user = new User();
		user.setName("testName1");
		user.setAge(25);
		this.fixture.saveAndFlush(user);
		return user;

	}

}
