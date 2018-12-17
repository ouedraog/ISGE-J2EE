# Prerequis
JAVA, Eclipse, Maven, Tomcat

# Sruts2 hello world avec Maven

1. Créer un projet Web app dynamic et convertir en projet maven (clic droit -> configure)
	a. Click sur new File -> Dynamic Web Project 
	b. Entrer le nom du project “HelloStruts” et click sur suivant
	c. Dans la page suivante (définition du classpath):  supprimer src et ajouter
		- src/main/java             (contient les sources .java)
		- src/main/resources        (contient les fichiers de ressources)
		- src/test/java             (contient les sources .java pour les tests)
		- src/test/resources        (contients les fichier de resources pour les tests)

	d. Dans la dernière page cocher sur generate web.xml
	e. Une fois le projet créé, click droit sur le projet → configure → Convert to maven project pour avoir un projet maven. 
	Un fichier pom.xml sera créé à la racine du projet. 
	Il contient la description maven du projet pour gérer les dépendances et le build.


2. Ajouter les librairies Struts
	Editer le fichier ./pom.xml et ajouter
		<dependency>
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-core</artifactId>
			<version>2.5.18</version>
		</dependency>

3. Configurer web.xml (dans WEB-INF) pour déléguer le traitement des requête http à Struts
	Le fichier web.xml contient les instructions pour le déploiement de l'application sur le serveur J2EE

	Remplacer le fichier ./WebContent/WEB-INF/web.xml par 

		<?xml version="1.0" encoding="UTF-8"?>
		<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xmlns="http://java.sun.com/xml/ns/javaee"
			xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
			id="WebApp_ID" version="3.0">
			<display-name>struts-hello</display-name>
			<filter>
				<filter-name>struts2</filter-name>
				<filter-class>org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter</filter-class>
			</filter>

			<filter-mapping>
				<filter-name>struts2</filter-name>
				<url-pattern>/*</url-pattern>
			</filter-mapping>
		</web-app>

4. Ajout d'une action HelloWorldAction qui va afficher retourner une page affichant Hello world
		Copier et coller dans le fichier ./src/main/java

		package com.isge.action;

		import com.opensymphony.xwork2.ActionSupport;

		public class HelloAction extends ActionSupport{
			// contient le message à afficher
			private String message;

			// Une action doit définir la méthode execute qui retourne un string permettant de définir la page à afficher
			@Override
			public String execute() throws Exception {
				setMessage("Hello world");;
				return SUCCESS;
			}
			
			public void setMessage(String message) {
				this.message = message;
			}
			public String getMessage() {
				return message;
			}
		}

		## ActionSupport

		Contient des fonctions utilitaires pour gérer les problématiques liés à la création d'actions:
		Action, Validation, Centralisation des messages, internalisation

5. Créer une page JSP pour afficher le message
	fichier ./WebContent/hello.jsp

		<%@ page language="java" contentType="text/html; charset=UTF-8"
			pageEncoding="UTF-8"%>
		<%@ taglib prefix="s" uri="/struts-tags"%>
		<!DOCTYPE html>
		<html>
		<head>
		<meta charset="UTF-8">
		<title>Hello</title>
		</head>
		<body>
			<h1>
				Welcome
				<s:property value="message" />
			</h1>
		</body>
		</html>

		## A noter
			- l'utilisation de la tag lib struts (<%@ taglib prefix="s" uri="/struts-tags"%>)
			- l'utilisation de la tag property pour afficher la propriété "message" défini dans l'action

6. Ajout du fichier struts.xml permettant de définir les pages à afficher par les actions
		fichier ./src/main/resources/struts.xml

		<?xml version="1.0" encoding="UTF-8"?>
		<!DOCTYPE struts PUBLIC
		    "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
		    "http://struts.apache.org/dtds/struts-2.0.dtd">
		 
		<struts>
		    <constant name="struts.devMode" value="true" />
		    <package name="main" extends="struts-default">

				<!-- L'url hello sera traité par l'action HelloAction, la page retourné est hello.jsp -->
		        <action name="hello" class="com.isge.action.HelloAction">
		            <result name="success">/hello.jsp</result>
		        </action>
		    </package>
		 
		</struts>
7. Exécution sur le serveur
	a. Clicker sur Run On server et choisissez Tomcat 7. En tappant l'url http://localhost:8080/struts-hello/hello la page hello world doit d'afficher

