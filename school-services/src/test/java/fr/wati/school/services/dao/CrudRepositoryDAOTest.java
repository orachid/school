/**
 * 
 */
package fr.wati.school.services.dao;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.wati.school.dao.UsersRepository;
import fr.wati.school.entities.bean.Users;

/**
 * @author Rachid Ouattara
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/school-dao-test-context.xml" })
public class CrudRepositoryDAOTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private UsersRepository utilisateurRepository;

	@Autowired
	private SessionFactory sessionFactory;

	@Before
	public void setUp() {
		Users users = new Users();
		users.setUsername("login");
		users.setPassword("ffff");
		sessionFactory.getCurrentSession().save(users);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().close();
	}

	@Test
	public void test() throws InterruptedException {
//		Utilisateur utilisateur = new Utilisateur();
//		utilisateur.setLogin("login");
//		utilisateur.setPassword("ffff");
//		utilisateurRepository.save(utilisateur);
//		utilisateurRepository.flush();

		// fail("Not yet implemented");
	}

	@After
	public void after() {
//		DatabaseManagerSwing.main(new String[] {});
//		while (true) {
//
//		}
	}
}
