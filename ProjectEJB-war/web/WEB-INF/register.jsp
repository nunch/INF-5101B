<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="ressources.messages" var="bundle"/>
<!DOCTYPE html>
<html lang="fr">

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
			<li><a href="<c:url value='/web/register'/>">Register</a></li>
      	</ul>
		<div class="row">
			<div id="content" class="col-sm-12">
				<h1>Register Account</h1>
				<p>If you already have an account with us, please login at the <a href="<c:url value='/web/login'/>">login page</a>.</p>
				<form action="<c:url value='/web/register'/>" method="post" class="form-horizontal">
					<fieldset id="account"><legend>Your Personal Details 
                                                <c:if test="${not empty problem1}">
                                                    <span style="color: red">${problem1}</span>
                                                </c:if>
                                            </legend>
						<div class="form-group required" style="display: none;">
							<label class="col-sm-2 control-label">Customer Group</label>
							<div class="col-sm-10">
								<div class="radio">
									<label>
										<input type="radio" name="customer_group_id" value="1" checked="checked" /> Default
									</label>
								</div>
							</div>
						</div>
						<div class="form-group required">
							<label class="col-sm-2 control-label" for="input-firstname">First Name</label>
							<div class="col-sm-10">
								<input type="text" name="firstname" value="" placeholder="First Name" id="input-firstname" class="form-control" />
							</div>
						</div>
						<div class="form-group required">
							<label class="col-sm-2 control-label" for="input-lastname">Last Name</label>
							<div class="col-sm-10">
								<input type="text" name="lastname" value="" placeholder="Last Name" id="input-lastname" class="form-control" />
							</div>
						</div>
						<div class="form-group required">
							<label class="col-sm-2 control-label" for="input-email">E-Mail</label>
							<div class="col-sm-10">
								<input type="email" name="email" value="" placeholder="E-Mail" id="input-email" class="form-control" />
							</div>
						</div>
						<div class="form-group required">
							<label class="col-sm-2 control-label" for="input-username">User name</label>
							<div class="col-sm-10">
								<input type="text" name="username" value="" placeholder="User name" id="input-username" class="form-control" />
							</div>
						</div>
					</fieldset>
					<fieldset><legend>Your Password 
                                                <c:if test="${not empty problem2}">
                                                    <span style="color: red">${problem2}</span>
                                                </c:if></legend>
						<div class="form-group required">
							<label class="col-sm-2 control-label" for="input-password">Password</label>
							<div class="col-sm-10">
								<input type="password" name="password" value="" placeholder="Password" id="input-password" class="form-control" />
							</div>
						</div>
						<div class="form-group required">
							<label class="col-sm-2 control-label" for="input-confirm">Password Confirm</label>
							<div class="col-sm-10">
								<input type="password" name="confirm" value="" placeholder="Password Confirm" id="input-confirm" class="form-control" />
							</div>
						</div>
					</fieldset>
	                <div class="buttons">
						<div class="pull-right">
							<input type="submit" value="Continue" class="btn btn-primary" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/inc/footer.jsp" />

</body>

</html>