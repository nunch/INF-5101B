<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="ressources.messages" var="bundle"/>
	<footer>
		<div class="container">
                    <p><fmt:message bundle="${bundle}"  key="footer.powered"/></p> 
			<!-- <fmt:message bundle="${bundle}"  key="message.powered" /> -->
		</div>
	</footer>
