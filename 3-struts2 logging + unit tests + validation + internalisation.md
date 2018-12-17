# Utilisation de Log4j 
 - Gestion efficace des logs
 - plusieurs niveau de log: trace, debug, info, warning, error
 - plusieurs output: console, fichier, envoi sur un serveur, ...
 - archivage, log tournant, ...

1. Ajout de la dépendence maven
		<dependencies>
		  <dependency>
		    <groupId>org.apache.logging.log4j</groupId>
		    <artifactId>log4j-api</artifactId>
		    <version>2.11.1</version>
		  </dependency>
		  <dependency>
		    <groupId>org.apache.logging.log4j</groupId>
		    <artifactId>log4j-core</artifactId>
		    <version>2.11.1</version>
		  </dependency>
		</dependencies>
2. Configuration log4j2 

Créer le fichier src/main/resources/log4j2.xml

		<?xml version="1.0" encoding="UTF-8"?>
		<Configuration status="WARN">
			<Appenders>
				<Console name="Console" target="SYSTEM_OUT">
					<PatternLayout
						pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
				</Console>

				<File name="File" fileName="app.log" immediateFlush="false"
					append="false">
					<PatternLayout
						pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
				</File>
				
			</Appenders>


			<Loggers>
				<Root level="info">
					<AppenderRef ref="File" />
					<AppenderRef ref="Console" />
				</Root>
			</Loggers>
		</Configuration>

3. Code java pour tester

		// Import 
		import org.apache.logging.log4j.LogManager;
		import org.apache.logging.log4j.Logger;



		final Logger logger = LogManager.getLogger("HelloWorld");
		logger.info("Hello world");

# Test unitaires

Maven:
		<dependency>
		    <groupId>junit</groupId>
		    <artifactId>junit</artifactId>
		    <version>4.12</version>
		    <scope>test</scope>
		</dependency>

Dans src/test/java

		package com.isge;

		import org.junit.Assert;
		import org.junit.Test;

		public class HelloTest {

				@Test
				public void should_get_2_when_adding_1_and_1() {
					int result = 1 + 1;
					Assert.assertEquals(result, 2);
				}
		}


# Validation
- Etendre ActionSupport pour chaque Action
- Surcharger validate()

# Internalisation
Ajout de fichier de properties = <ActionName>.properties dans le même classpath

-- HelloWord.java

		 /**
		 * @author LEE
		 */
		package com.isge;

		import com.opensymphony.xwork2.ActionSupport;

		public class HelloWorld extends ActionSupport {

		    public String execute() throws Exception {
		        setMessage(getText(MESSAGE));
		        return SUCCESS;
		    }

		    /**
		     * Provide default value for Message property.
		     */
		    public static final String MESSAGE = "HelloWorld.message";

		    /**
		     * Field for Message property.
		     */
		    private String message;

		    /**
		     * Return Message property.
		     *
		     * @return Message property
		     */
		    public String getMessage() {
		        return message;
		    }
		    /**
		     * Set Message property.
		     *
		     * @param message Text to display on HelloWorld page.
		     */
		    public void setMessage(String message) {
		        this.message = message;
		    }
		}
-- Properties
	1.HelloWorld_en.properties
	    HelloWorld.message=Good Morning!  

	2.HelloWorld_hi.properties
	    HelloWorld.message=Suprabhat! 

-- Page JSP: inclure le paramètre request_locale

  				 <s:url id="url" action="HelloWorld">
                    <s:param name="request_locale">in</s:param>
                </s:url>