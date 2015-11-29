<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="/WEB-INF/tlds/tag_1.tld" prefix="fn" %>
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
		<div class="row">
			<div class="col-sm-12">
                            <h1><fmt:message bundle="${bundle}" key="viewCommand.myCommand"/></h1>
				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
                                                            <td class="text-center"><fmt:message bundle="${bundle}" key="viewCommand.commandNumber"/></td>
                                                            <td class="text-center"><fmt:message bundle="${bundle}" key="viewCommand.adressAdress"/></td>
                                                            <td class="text-center"><fmt:message bundle="${bundle}" key="viewCommand.adressCity"/></td>
                                                            <td class="text-center"><fmt:message bundle="${bundle}" key="viewCommand.adressCountry"/></td>
							</tr>
						</thead>
						<tbody id="tbody">
                                                        <tr>
								<td class="text-center">
                                                                    ${command.id}
								</td>
								<td class="text-center">
                                                                    ${command.adressId.adress}
								</td>
								<td class="text-center">
                                                                    ${command.adressId.cityName}
								</td>
								<td class="text-center">
                                                                    ${command.adressId.country}
								</td>
							</tr>
                                                        <tr>
                                                            <td class="text-center">
                                                                <fmt:message bundle="${bundle}" key="viewCommand.card"/>
                                                            </td>
                                                            <td class="text-center" colspan="3">
                                                                ${command.cardId.name}
                                                            </td>
                                                        </tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
                                                            
        <div class="container">
            <div class="row">
			<div class="col-sm-12">
                            <h1><fmt:message bundle="${bundle}" key="viewCommand.myProducts"/></h1>
					<div class="table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr>
									<td class="text-center"><fmt:message bundle="${bundle}"  key="viewCommand.image" /></td>
									<td class="text-center"><fmt:message bundle="${bundle}"  key="viewCommand.productName" /></td> 
									<td class="text-center"><fmt:message bundle="${bundle}"  key="viewCommand.quantity" /></td>
									<td class="text-center"><fmt:message bundle="${bundle}"  key="viewCommand.unitPrice" /></td>
									<td class="text-center"><fmt:message bundle="${bundle}"  key="viewCommand.totalProduct" /></td>
								</tr>
							</thead>
                                                            <c:forEach var="bean" items="${products}" >
                                                                <tr>
									<td class="text-center" style='vertical-align: middle'>
                                                                            <img src="${bean.product.image}" alt="${bean.product.name}" title="${bean.product.name}" />
									</td>
									<td class="text-center" style='vertical-align: middle'>
                                                                            ${bean.product.name}
									</td>
									<td class="text-center" style='vertical-align: middle'>
                                                                            ${bean.quantity}
									</td>
                                                                        <td class="text-center" style='vertical-align: middle'><fn:price price="${bean.product.price}" lang="${lang}"/></td>
                                                                        <td class="text-center" style='vertical-align: middle'><fn:price price="${bean.product.price*bean.quantity}" lang="${lang}"/></td>
								</tr>
                                                            </c:forEach>
							</tbody>
								
						</table>
					</div>
			</div>
		</div>
                                                               
                <div class="row">
			<div class="col-sm-4 col-sm-offset-8">
				<table class="table table-bordered">
					<tr>
						<td class="text-center"><strong><fmt:message bundle="${bundle}"  key="viewCommand.subTotal" /></strong></td>
                                                <td class="text-center"><fn:price lang="${lang}" price="${total*0.8}"/></td>
					</tr>
					<tr>
						<td class="text-center"><strong><fmt:message bundle="${bundle}"  key="viewCommand.VAT" /> (20%):</strong></td>
                                                <td class="text-center"><fn:price lang="${lang}" price="${total*0.2}"/></td>
					</tr>
					<tr>
						<td class="text-center"><strong><fmt:message bundle="${bundle}"  key="viewCommand.total" /> :</strong></td>
                                                <td class="text-center"><fn:price lang="${lang}" price="${total}"/></td>
					</tr>
				</table>
			</div>
		</div>
        </div>

	<c:import url="/WEB-INF/inc/footer.jsp" />

</body>

</html>