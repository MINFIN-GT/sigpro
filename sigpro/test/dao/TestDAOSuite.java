package dao;
import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import utilities.CHibernateSession;
import utilities.TestSetUp;

@RunWith(Suite.class)
@SuiteClasses({ EstadoTablaDAOTest.class, PermisoDAOTest.class })
public class TestDAOSuite {
	private static Configuration config;
    private static SessionFactory factory;	
   

	@BeforeClass
	public static void setUp() throws Exception {
		config = new Configuration();
        config.configure(new File("src/hibernate_tmp.cfg.xml"));
        factory = config.buildSessionFactory();
        TestSetUp.setTestState();
        CHibernateSession.changeEnvinroment(factory);
   	}

	
}
