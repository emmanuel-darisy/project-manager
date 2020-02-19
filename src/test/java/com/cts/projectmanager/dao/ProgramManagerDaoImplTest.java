package com.cts.projectmanager.dao;

import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.projectmanager.model.User;

@RunWith(SpringRunner.class)
public class ProgramManagerDaoImplTest {

	
	@InjectMocks
	private ProgramManagerDaoImpl programManagerDao;
	
	@MockBean
	EntityManager entityManager;
	
	@Mock
	private Session session;


    /*SessionFactory sf;
    SessionFactoryImplementor sfi;
    @Mock
    Session s;
    @Mock
    HibernateTemplate t;*/
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		/*
		 sf = mock(SessionFactory.class, withSettings().extraInterfaces(SessionFactoryImplementor.class));
	        sfi = (SessionFactoryImplementor) sf;
	        when(sf.openSession()).thenReturn(s);*/
	        
		
	}
	
	@Test
	public void testGetUser() throws Exception {
		User user = new User();
		user.setUserId(12);
		user.setEmployeeId(12);
		user.setFirstName("test");
		user.setLastName("test");
		when(entityManager.unwrap(Session.class)).thenReturn(session);
		/*when(entityManager.unwrap(Mockito.any())).thenReturn(session);*/
		
		programManagerDao.addOrUpdateUser(user);
	}

	

}
