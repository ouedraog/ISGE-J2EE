1. Maven pom.xml

	<dependencies>
		<dependency>
			<!-- Struts -->
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-core</artifactId>
			<version>2.5.18</version>
		</dependency>
		<dependency>
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-convention-plugin</artifactId>
			<version>2.5.18</version>
		</dependency>

		<!-- Hibernate -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>5.3.7.Final</version>
		</dependency>

		<!-- H2 database driver -->
		<dependency>
		    <groupId>com.h2database</groupId>
		    <artifactId>h2</artifactId>
		    <version>1.4.197</version>
		</dependency>

		<!-- Servlet API -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.0.1</version>
			<scope>provided</scope>
		</dependency>
	</dependencies>

	2. Fichier web.xml

		<?xml version="1.0" encoding="UTF-8"?>
		<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee"
		         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
		         id="WebApp_ID" version="2.5">
		    <display-name>Struts2Hibernate</display-name>
		    <welcome-file-list>
		        <welcome-file>employee.jsp</welcome-file>
		    </welcome-file-list>
		    <filter>
		        <filter-name>struts2</filter-name>
		        <filter-class>org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter</filter-class>
		    </filter>
		    <filter-mapping>
		        <filter-name>struts2</filter-name>
		        <url-pattern>/*</url-pattern>
		    </filter-mapping>
		    <listener>
		        <listener-class>org.isge.listener.HibernateListener</listener-class>
		    </listener>
		</web-app>

	3. Hibernate listener
		package org.isge.listener;

		import java.net.URL;

		import javax.servlet.ServletContextEvent;
		import javax.servlet.ServletContextListener;

		import org.hibernate.SessionFactory;
		import org.hibernate.cfg.Configuration;

		public class HibernateListener implements ServletContextListener {
			private Configuration config;
			private SessionFactory factory;
			private static Class<HibernateListener> cls = HibernateListener.class;
			public static final String KEY_NANE = cls.getName();

			@Override
			public void contextDestroyed(ServletContextEvent arg) {
				// TODO Auto-generated method stub
			}

			@Override
			public void contextInitialized(ServletContextEvent arg) {
				try {
					URL url = HibernateListener.class.getResource("/hibernate.cfg.xml");
					config = new Configuration().configure(url);
					factory = config.buildSessionFactory();
					arg.getServletContext().setAttribute(KEY_NANE, factory);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}


	4. hibernate configuration
	 fichier src/main/resources/hibernate.cfg.xml

		 <?xml version="1.0" encoding="UTF-8"?>
		<!DOCTYPE hibernate-configuration PUBLIC
				"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
				"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
		<hibernate-configuration>
			<session-factory>
				<!-- Database connection settings -->
				<property name="connection.driver_class">org.h2.Driver</property>
				<property name="connection.url">jdbc:h2:database./test</property>
				<property name="connection.username">sa</property>
				<property name="connection.password" />
				<!-- SQL dialect -->
				<property name="dialect">org.hibernate.dialect.H2Dialect</property>
				<property name="show_sql">true</property>
				<property name="format_sql">true</property>
				<property name="hibernate.hbm2ddl.auto">create</property>
				<!-- Add mappings -->
				<mapping class="org.isge.model.Employee" />
			</session-factory>
		</hibernate-configuration>

		- Base persisté sur un fichier situé dans ./test
		- dialect: org.hibernate.dialect.H2Dialect
		- hibernate.hbm2ddl.auto: création automatique du schéma de la base



		5. Java: @Entity classes
		- Get sessionFactory
			SessionFactory sessionFactory = (SessionFactory) ServletActionContext.getServletContext()
				.getAttribute(HibernateListener.KEY_NANE)

		- DAO

		package org.isge.dao;

		import java.util.List;

		import org.hibernate.Session;
		import org.hibernate.SessionFactory;
		import org.isge.model.Employee;

		public class EmployeeDAOImpl implements EmployeeDAO {
			private SessionFactory sessionFactory;

			public EmployeeDAOImpl(SessionFactory sessionFactory) {
				this.sessionFactory = sessionFactory;
			}

			@Override
			public void save(Employee employee) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(employee);
				session.getTransaction().commit();

			}

			@Override
			public List<Employee> findAll() {
				Session session = sessionFactory.openSession();
				List<Employee> result = session.createQuery("from Employee").list();
				return result;
			}

			@Override
			public void remove(Employee employee) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.delete(employee);
				session.getTransaction().commit();

			}

		}

		6. Using Oracle 11

		### Maven:

		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc6</artifactId>
			<version>11.2.0</version>
		</dependency>

		### hibernage.cf.xml

			<?xml version="1.0" encoding="utf-8"?>
			<!DOCTYPE hibernate-configuration PUBLIC
			"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
			"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
			<hibernate-configuration>
			 <session-factory>
			  <property name="hibernate.connection.driver_class">oracle.jdbc.driver.OracleDriver</property>
			  <property name="hibernate.connection.url">jdbc:oracle:thin:@<ip>:<port>:my_db</property>
			  <property name="hibernate.connection.username">mkyong</property>
			  <property name="hibernate.connection.password">password</property>
			  <property name="hibernate.dialect">org.hibernate.dialect.Oracle10gDialect</property>
			  <property name="hibernate.default_schema">my_db</property>
			  <property name="show_sql">true</property>
			  <mapping resource="com/mkyong/user/DBUser.hbm.xml"></mapping>
			</session-factory>
			</hibernate-configuration
