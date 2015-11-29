<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="ressources.messages" var="bundle"/>

	<nav id="top">
		<div class="container">
			<div class="pull-left">
			</div>
			<div id="top-links" class="nav pull-right">
				<ul class="list-inline">
					<li class="dropdown">
                                            <a href="<c:url value='/web/account'/>" title="My Account" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <span class="hidden-xs hidden-sm hidden-md"><fmt:message bundle="${bundle}"  key="navtop.myAccount"/></span> <span class="caret"></span></a>
						<ul class="dropdown-menu dropdown-menu-right">
                                                    <c:set var="truc" value="session"/>
                                                    <c:set var="truc2" value="user"/>
                                                    <c:set var="truc3" value="compagny"/>
                                                    <c:choose>
                                                        <c:when test="${user.username==truc}">
                                                            <li><a href="<c:url value='/faces/register.xhtml'/>"><fmt:message bundle="${bundle}"  key='navtop.register'/></a></li>
                                                            <li><a href="<c:url value='/faces/login.xhtml'/>"><fmt:message bundle="${bundle}"  key="navtop.login" /></a></li>
                                                        </c:when>
                                                        <c:when test="${user.type==truc2}">
                                                            <li><a href="<c:url value='/faces/listCards.xhtml'/>"><fmt:message bundle="${bundle}"  key="navtop.viewCards" /></a></li>
                                                            <li><a href="<c:url value='/faces/listAdresses.xhtml'/>"><fmt:message bundle="${bundle}"  key="navtop.viewAdresses" /></a></li>
                                                            <li><a href="<c:url value='/faces/listCommands.xhtml'/>"><fmt:message bundle="${bundle}"  key="navtop.viewCommands" /></a></li>
                                                            <li><a href="<c:url value='/web/logOff'/>"><fmt:message bundle="${bundle}"  key="navtop.logOff" /></a></li>
                                                        </c:when>
                                                        <c:when test="${user.type==truc3}">
                                                            <li><a href="<c:url value='/faces/listProducts.xhtml'/>"><fmt:message bundle="${bundle}"  key="navtop.viewProducts" /></a></li>
                                                            <li><a href="<c:url value='/web/logOff'/>"><fmt:message bundle="${bundle}"  key="navtop.logOff" /></a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li><a href="<c:url value='/faces/admin/index.xhtml'/>"><fmt:message bundle="${bundle}"  key="navtop.admin" /></a></li>
                                                            <li><a href="<c:url value='/web/logOff'/>"><fmt:message bundle="${bundle}"  key="navtop.logOff" /></a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    
							
                                                    
						</ul>
					</li>
			        <li><a href="<c:url value='/web/cart'/>" title="Shopping Cart"><i class="fa fa-shopping-cart"></i> <span class="hidden-xs hidden-sm hidden-md"><fmt:message bundle="${bundle}"  key="navtop.shoppingCart" /></span></a></li>
                                <li>
                                    <form action="<c:url value='/web/index'/>" method="post">
                                        <input type="hidden" name="language" value="fr"/>
                                        <input type='image' src='/ProjectEJB-war/images/frenchFlag.jpg' alt='submit'/> 
                                    </form>
                                </li>
                                <li>
                                    <form action="<c:url value='/web/index'/>" method="post">
                                        <input type="hidden" name="language" value="en"/>
                                        <input type='image' src='/ProjectEJB-war/images/englishFlag.png' alt='submit'/> 
                                    </form>
                                </li>
                                </ul>
			</div>
		</div>
	</nav>
