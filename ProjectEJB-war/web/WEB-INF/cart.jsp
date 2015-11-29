<%@page import="Entity.Product"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>

<%!
    public double round(double value, int places) {
    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
}
%>


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
		<ul class="breadcrumb">
			<li><a href="<c:url value='/web/index'/>"><i class="fa fa-home"></i></a></li>
			<li><a href="<c:url value='/web/cart'/>"><fmt:message bundle="${bundle}"  key="cart.shoppingCart" /></a></li>
      	</ul>
		<div class="row">
			<div class="col-sm-12">
				<h1><fmt:message bundle="${bundle}"  key="cart.shoppingCart" /></h1>
				<form action="<c:url value='/web/cart'/>" method="post">
					<div class="table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr>
									<td class="text-center"><fmt:message bundle="${bundle}"  key="cart.image" /></td>
									<td class="text-left"><fmt:message bundle="${bundle}"  key="cart.productName" /></td> 
									<td class="text-left"><fmt:message bundle="${bundle}"  key="cart.quantity" /></td>
									<td class="text-right"><fmt:message bundle="${bundle}"  key="cart.unitPrice" /></td>
									<td class="text-right"><fmt:message bundle="${bundle}"  key="cart.totalProduct" /></td>
								</tr>
							</thead>
                                                        <% int iii=0;double total=0;%>
                                                            <c:forEach var="bean" items="${product}" >
                                                                <%iii++;%>
                                                            <c:set var="tot" value="0" scope="page"/>
							<tbody id="div-bean<%=iii%>">
                                                                <tr>
									<td class="text-center">
                                                                            <a href="#"><img src="${bean.image}" alt="${bean.name}" title="${bean.name}" /></a>
									</td>
									<td class="text-left">
										<a href="#">${bean.name}</a>
									</td>
									<td class="text-left">
										<div class="input-group btn-block" style="max-width: 200px;">
											<input type="text" id="input-bean<%=iii%>" name='input-bean<%=iii%>' value="${map[bean.name]}" size="1" class="form-control" />
											<span class="input-group-btn">
                                                                                            <button type="submit" hidden="hidden"/>
                                                                                            <button type="button" data-toggle="tooltip" title="Update" class="btn btn-primary" onclick="cart.update(${bean.id},<%=iii%>);"><i class="fa fa-refresh"></i></button>
                                                                                            <button type="button" data-toggle="tooltip" title="Remove" class="btn btn-danger" onclick="cart.remove(${bean.id},<%=iii%>);"><i class="fa fa-times-circle"></i></button>
											</span>
										</div>
									</td>
                                                                        <td class="text-right"><fn:price price="${bean.price}" lang="${lang}"/></td>
                                                                        <td class="text-right" id="total-bean<%=iii%>"><fn:price price="${bean.price*map[bean.name]}" lang="${lang}"/></td>
								</tr>
                                                                <%
                                                                List<Product> l =(List<Product>) request.getAttribute("product");
                                                                HashMap<String,Integer> map = (HashMap<String,Integer>) request.getAttribute("map");
                                                                Product  p = l.get(iii-1);
                                                                total+=p.getPrice().doubleValue()*map.get(p.getName());
                                                                %>
                                                                
							</tbody>
                                                            </c:forEach>
								
						</table>
					</div>
				</form>
			</div>
		</div>
		<br />
                <%
                    request.setAttribute("price", total);
                %>
		<div class="row">
			<div class="col-sm-4 col-sm-offset-8">
				<table class="table table-bordered">
					<tr>
						<td class="text-right"><strong><fmt:message bundle="${bundle}"  key="cart.subTotal" /></strong></td>
                                                <td class="text-right" id="cart-Sub-Total"><fn:price lang="${lang}" price="${price*0.8}"/></td>
					</tr>
					<tr>
						<td class="text-right"><strong><fmt:message bundle="${bundle}"  key="cart.VAT" /> (20%):</strong></td>
                                                <td class="text-right" id="cart-VAT"><fn:price lang="${lang}" price="${price*0.2}"/></td>
					</tr>
					<tr>
						<td class="text-right"><strong><fmt:message bundle="${bundle}"  key="cart.total" /> :</strong></td>
                                                <td class="text-right" id="cart-Total"><fn:price lang="${lang}" price="${price}"/></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="buttons">
			<div class="pull-left">
				<a href="<c:url value='/web/index'/>" class="btn btn-default"><fmt:message bundle="${bundle}"  key="cart.continueShopping" /></a>
			</div>
			<div class="pull-right">
				<a href="<c:url value='/web/chooseAdress'/>" class="btn btn-primary"><fmt:message bundle="${bundle}"  key="cart.checkout" /></a>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/inc/footer.jsp" />

</body>

</html>