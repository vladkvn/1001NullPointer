<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
	<h1>${message}</h1>
	<form action="/auth" method="post">
		<input name="login"/>
		<input name="pass"/>
		<input type="submit"/>
	</form>
</body>
</html>