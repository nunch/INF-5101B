<%@ page trimDirectiveWhitespaces="true" %>
<%@page import="java.util.HashMap"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="/WEB-INF/tlds/tag.tld" prefix="xs" %>
<fmt:setBundle basename="ressources.messages" var="bundle"/>
		<nav id="menu" class="navbar">
			<div class="navbar-header"><span id="category" class="visible-xs"></span>
				<button type="button" class="btn btn-navbar navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"><i class="fa fa-bars"></i></button>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
                                    <c:forEach var="cat" items="${categories}">
                                        <c:set var="idd" value="${cat.id}"/>
                                        <c:set var="truc" value="${false}"/>
                                        <c:forEach var="sub_cat" items="${map_categories}">
                                            <c:if test="${sub_cat.value==idd}">
                                                <c:set var="truc" value="${true}"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:choose>
                                            <c:when test="${truc}">
					<li class="dropdown">
                                                <a href="<c:url value='/web/category/${cat.nameFr}'/>" class="dropdown-toggle" data-toggle="dropdown"><xs:catName lang="${language}" cat="${cat}"/></a>
						<div style="" class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
                                                                    <c:forEach var="sub_cat" items="${map_categories}">
                                                                        <c:if test="${sub_cat.value==idd}">
                                                                            <li><a href="<c:url value='/web/category/${cat.nameFr}/${sub_cat.key.nameFr}'/>">
                                                                                    <xs:catName lang="${language}" cat="${sub_cat.key}"/>
                                                                                </a></li>
                                                                        </c:if>
                                                                       
                                                                    </c:forEach>
                                                                </ul>
							</div>
						</div>
					</li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a href="<c:url value='/web/category/${cat.nameFr}'/>"><xs:catName lang="${language}" cat="${cat}"/></a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
				</ul>
			</div>
		</nav>
