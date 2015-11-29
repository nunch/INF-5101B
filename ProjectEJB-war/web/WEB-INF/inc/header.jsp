<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="ressources.messages" var="bundle"/>

    
           <header>
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<div id="logo">
						<a href="<c:url value='/web/index'/>"><img src="/ProjectEJB-war/images/logo.png" title="<fmt:message bundle="${bundle}"  key="header.yourStore"/>" alt="<fmt:message bundle="${bundle}"  key="header.yourStore"/>" class="img-responsive"></a>
					</div>
				</div>
				<div class="col-sm-5"><div id="search" class="input-group" style="width:100%">
                                        <form id="formId" action="/ProjectEJB-war/web/search" method="post" style="float:left; width:70%">
					<input name="search" placeholder="<fmt:message bundle="${bundle}"  key="header.search"/>" class="form-control input-lg" type="text" style="display: inline;">
                                        <input type="submit" style="display: none;"/>
                                        </form>
                                        <button form="formId" type="submit" class="btn btn-default btn-lg"><i class="fa fa-search"></i></button>
				</div></div>
				<div class="col-sm-3"><div id="cart" class="btn-group btn-block">
					<button type="button" data-toggle="dropdown" data-loading-text="Loading..." class="btn btn-inverse btn-block btn-lg"><i class="fa fa-shopping-cart"></i> <span id="cart-total">0 item(s) - 0.00</span></button>
					
				</div></div>
			</div>
		</div>
	</header>