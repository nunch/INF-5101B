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
            <c:choose>
                <c:when test="${sessionScope['user'].username.equals('session')}">
                    
                    <c:url value='/faces/login.xhtml' var="url"/>
                    <fmt:message bundle="${bundle}" key="chooseAdress.session" var="empt">
                        <fmt:param value="${url}"/>
                    </fmt:message>
                    <c:out value="${empt}" escapeXml="false"/>
                    
                </c:when>
                <c:when test="${empty adresses}">
                    <c:url value='/faces/listAdresses.xhtml' var="url"/>
                    <fmt:message bundle="${bundle}" key="chooseAdress.emptyAdresses" var="empt">
                        <fmt:param value="${url}"/>
                    </fmt:message>
                    <c:out value="${empt}" escapeXml="false"/>
                </c:when>
                <c:otherwise>
                    <form action="<c:url value='/web/chooseCard'/>" method="post">
                        <div class="row">
                            <div class="col-sm-12">
                                <h1><fmt:message bundle="${bundle}" key="chooseAdress.adresses"/></h1>
                                    <div class="table-responsive">
                                            <table class="table table-bordered">
                                                    <thead>
                                                            <tr>
                                                                    <td></td>
                                                                    <td class="text-center">
                                                                        <fmt:message bundle="${bundle}" key="chooseAdress.adress"/>
                                                                    </td>
                                                                    <td class="text-center">
                                                                        <fmt:message bundle="${bundle}" key="chooseAdress.city"/>
                                                                    </td>
                                                                    <td class="text-center">
                                                                        <fmt:message bundle="${bundle}" key="chooseAdress.country"/>
                                                                    </td>
                                                            </tr>
                                                    </thead>
                                                    <tbody>
                                                            <c:forEach items="${adresses}" var="adress">
                                                                <tr>
                                                                    <td class="text-center">
                                                                        <input type="radio" name="adressId" value="${adress.id}" checked="checked">
                                                                    </td>
                                                                    <td class="text-center">
                                                                        <c:out value="${adress.adress}"/>
                                                                    </td>
                                                                    <td class="text-center">
                                                                        <c:out value="${adress.cityName}"/>
                                                                    </td>
                                                                    <td class="text-center">
                                                                        <c:out value="${adress.country}"/>
                                                                    </td>
                                                                </tr>
                                                                
                                                            </c:forEach>
                                                    </tbody>
                                            </table>
                                    </div>
                            </div>
                        </div>
                        <div class="buttons">
                            <div class="pull-left">
                                <a href="<c:url value='/web/index'/>" class="btn btn-default"><fmt:message bundle="${bundle}"  key="chooseAdress.continueShopping" /></a>
                            </div>
                            <div class="pull-right">
                                <input type="submit" value="<fmt:message bundle="${bundle}"  key="chooseAdress.continue" />" class="btn btn-primary">
                            </div>
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
	</div>
        
	<c:import url="/WEB-INF/inc/footer.jsp" />

</body>

</html>
