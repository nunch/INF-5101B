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
		<div id="content" class="row">
			<div class="col-sm-12">
				<div id="slideshow0" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#slideshow0" data-slide-to="0" class="active"></li>
						<li data-target="#slideshow0" data-slide-to="1"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active"><img src="/ProjectEJB-war/images/MacBookAir-1140x380.jpg" alt=""></div>
						<div class="item"><img src="/ProjectEJB-war/images/iPhone6-1140x380.jpg" alt=""></div>
					</div>

					<!-- Controls -->
					<a class="left carousel-control" href="#slideshow0" role="button" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#slideshow0" role="button" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>
		</div>

		<h3><fmt:message bundle="${bundle}"  key="category.featured" /></h3>
		<div class="row product-layout">
				<c:forEach var="bean" items="${product}" >
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="product-thumb transition">
                                    <div class="image"><a href='<c:url value='/web/product/${bean.id}'/>'><img src="${bean.image}" alt="${bean.name}" title="${bean.name}" class="img-responsive"></a></div>
					<div class="caption">
						<h4><a href='<c:url value='/web/product/${bean.id}'/>'>${bean.name}</a></h4>
						<p>
							${bean.description}
						</p>
						<p class="price">
                                                    <fn:price lang="${lang}" price="${bean.price}"/>
						</p>
					</div>
					<div class="button-group">
						<button style="width: 100%" type="button" onclick="cart.add('${bean.id}');"><i class="fa fa-shopping-cart"></i> <span class="hidden-xs hidden-sm hidden-md"><fmt:message bundle="${bundle}" key="category.addToCart" /></span></button>
					</div>
				</div>
			</div>
				</c:forEach>
		</div><!-- /.product-layout -->
	</div>

	<c:import url="/WEB-INF/inc/footer.jsp" />

</body>
</html>