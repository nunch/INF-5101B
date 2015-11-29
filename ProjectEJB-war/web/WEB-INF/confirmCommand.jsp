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
            <div class="row">
			<div class="col-sm-12">
                            <h1><fmt:message bundle="${bundle}" key="confirmCommand.adress"/></h1>
				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<td class="text-center"><fmt:message bundle="${bundle}" key="confirmCommand.adressAdress"/></td>
								<td class="text-left"><fmt:message bundle="${bundle}" key="confirmCommand.city"/></td>
								<td class="text-left"><fmt:message bundle="${bundle}" key="confirmCommand.country"/></td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="text-center">
									${adress.adress}
								</td>
								<td class="text-left">
									${adress.cityName}
								</td>
								<td class="text-left">
                                                                    ${adress.country}
                                                                </td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
                        <div class="col-sm-12">
                            <h1><fmt:message bundle="${bundle}" key="confirmCommand.card"/></h1>
				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<td class="text-center"><fmt:message bundle="${bundle}" key="confirmCommand.cardName"/></td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="text-center">
									${card.name}
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
            </div>
            <form action="<c:url value='/web/confirmedCommand'/>" method="post">
                <input type="hidden" name="cardId" value="${card.id}"/>
                <input type="hidden" name="adressId" value="${adress.id}"/>
                
                <div class="buttons">
                    <div class="pull-left">
                        <a href="<c:url value='/web/index'/>" class="btn btn-default"><fmt:message bundle="${bundle}"  key="confirmCommand.continueShopping" /></a>
                    </div>
                    <div class="pull-right">
                        <input type="submit" value="<fmt:message bundle="${bundle}"  key="confirmCommand.continue" />" class="btn btn-primary">
                    </div>
                </div>
            </form>
        </div>
        
	<c:import url="/WEB-INF/inc/footer.jsp" />
</body>

</html
