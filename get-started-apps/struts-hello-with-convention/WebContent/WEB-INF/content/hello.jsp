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
	<s:if test="name != null" >
		<h1>Bienvenu ${name}</h1>
	</s:if>
	<s:else>
		<h3>Veuillez entrer votre nom</h3>
		<s:form>
			<s:textfield name="name"></s:textfield>
			<s:submit value="Valider"></s:submit>
		</s:form>
	</s:else>
</body>
</html>