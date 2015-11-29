<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<fmt:setBundle basename="ressources.messages" var="bundle"/>

<head>
	<c:import url="/WEB-INF/inc/head.jsp" />
	<title>My Shop</title>
</head>

<body class="common-home">

	<c:import url="/WEB-INF/inc/navtop.jsp" />

	<c:import url="/WEB-INF/inc/header.jsp" />

	<div class="container">
		<c:import url="/WEB-INF/inc/navbar.jsp" />
	</div>

	<div class="container">
		<ul class="breadcrumb">
			<li><a href="<c:url value='/web/index'/>"><i class="fa fa-home"></i></a></li>
			<li><a href="<c:url value='/web/login'/>">Account</a></li>
			<li><a href="<c:url value='/web/login'/>">Login</a></li>
      	</ul>
		<div class="row">
			<div class="col-sm-6">
				<div class="well">
					<h2>New Customer</h2>
					<p><strong>Register Account</strong></p>
					<p>By creating an account you will be able to shop faster, be up to date on an order's status, and keep track of the orders you have previously made.</p>
					<a href="<c:url value='/web/register'/>" class="btn btn-primary">Continue</a>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="well">
					<h2>Returning Customer</h2>
					<p><strong>I am a returning customer</strong></p>
					<form action="<c:url value='/web/login'/>" method="post" >
						<div class="form-group">
							<label class="control-label" for="input-email">E-Mail Address or User name</label>
							<input type="text" name="username" value="" placeholder="E-Mail Address or User name" id="input-email" class="form-control" />
						</div>
						<div class="form-group">
							<label class="control-label" for="input-password">Password</label>
							<input type="password" name="password" value="" placeholder="Password" id="input-password" class="form-control" />
							<a href="<c:url value='/web/forgotten'/>">Forgotten Password</a></div>
							<input type="submit" value="Login" class="btn btn-primary" />
							<input type="hidden" name="redirect" value="<c:url value='/web/account'/>" />
					</form>
				</div>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/inc/footer.jsp" />

</body>

</html>