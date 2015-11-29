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
			<div id="content" class="col-sm-12">
				<div class="row">
					<div class="col-sm-8">
						<ul class="thumbnails">
							<li>
								<a class="thumbnail" href="<c:out value="${bean.image}"></c:out>" title="<c:out value="${bean.name}"></c:out>">
									<img src="<c:out value="${bean.image}"></c:out>" title="<c:out value="${bean.name}"></c:out>" alt="<c:out value="${bean.name}"></c:out>" />
								</a>
							</li>
						</ul>
                                                    <h4><fmt:message bundle="${bundle}" key="product.desctription"/></h4>
						<div class="tab-content">
							<div class="tab-pane active" id="tab-description">
								<p><c:out value="${bean.description}"></c:out></p>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<h1><c:out value="${bean.name}"></c:out></h1>
						<ul class="list-unstyled">
							<li><fmt:message bundle="${bundle}" key="product.brand"/>: <c:out value="${compagny.username}"></c:out></li>
							<li><fmt:message bundle="${bundle}" key="product.productCode"/>: <c:out value="${bean.id}"></c:out></li>
							<li><fmt:message bundle="${bundle}" key="product.availability"/>: In Stock</li>
						</ul>
						<ul class="list-unstyled">
                                                    <li><h2><fn:price lang="${lang}" price="${bean.price}"/></h2></li>
							<hr>
							<li><button type="button" id="button-cart" data-loading-text="Loading..." class="btn btn-primary btn-lg btn-block" onclick="cart.add(${bean.id})"><fmt:message bundle="${bundle}" key="product.addToCart"/></button></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/inc/footer.jsp" />

</body>

</html>