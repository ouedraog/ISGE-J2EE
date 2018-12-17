# utilisation de la configuration par convention
Plus besoin du fichier struts.xml pour lier les actions à la vue. Cela se fera par la convention suivante:

- Action location by package naming conventions
- Result (JSP, FreeMarker, etc) location by naming conventions
- Class name to URL naming convention
- Package name to namespace convention
- SEO compliant URLs (i.e. my-action rather than MyAction)
- Action name overrides using annotations
- Interceptor overrides using annotations
- Namespace overrides using annotations
- XWork package overrides using annotations
- Default action and result handling (i.e. /products will try com.example.actions.Products as well as com.example.actions.products.Index)

doc: https://struts.apache.org/plugins/convention/
Exemple
com.example.actions.MainAction -> /main
com.example.actions.products.Display -> /products/display
com.example.struts.company.details.ShowCompanyDetailsAction -> /company/details/show-company-details

1. Dépendences maven
	
  		<dependency>
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-core</artifactId>
			<version>2.5.18</version>
		</dependency>
  		<dependency>
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-convention-plugin</artifactId>
			<version>2.5.18</version>
		</dependency>
2. s'assurer du nommage pour qu'en en allant sur /hello l'action HelloWorld soit invoqué
- Action:  com.isge.action.HelloAction 
- Déplacer hello.jsp dans ./WebContent/content
