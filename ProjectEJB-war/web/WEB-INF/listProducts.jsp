<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
				<h1>My Product(s)</h1>
				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<td class="text-center">Image</td>
								<td class="text-left">Product Name</td>
								<td class="text-left">Category</td>
								<td class="text-left">Delete</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${products}" var="product">
							<tr id="tr-${product.id}">
								<td class="text-center">
									<img src="${product.image}" alt="iMac" title="iMac" class="img-thumbnail" />
								</td>
								<td class="text-left">
									${product.name}
								</td>
								<td class="text-left">${product_category[product]}</td>
								<td class="text-center">
									<button type="button" data-toggle="tooltip" title="Remove" class="btn btn-danger" onclick="product.remove(${product.id});"><i class="fa fa-times-circle"></i></button>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<br />
		<div class="row">
                    		<form action="<c:url value='/web/addProduct' />" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
					<fieldset><legend style="color: #ff9900;">Informations</legend>
						<div class="form-group has-feedback">
							<label for="input-name" class="col-sm-2 control-label">Name</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="name" id="input-name" placeholder="Name" value="">
								<span class="glyphicon glyphicon-asterisk form-control-feedback" style="color: red"></span>
							</div>
						</div>
						<div class="form-group has-feedback">
							<label for="input-desc" class="col-sm-2 control-label">Description</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="description" id="input-desc" placeholder="Description" value="">
								<span class="glyphicon glyphicon-asterisk form-control-feedback" style="color: red"></span>
							</div>
						</div>
						<div class="form-group has-feedback">
							<label for="input-price" class="col-sm-2 control-label">Price</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="price" id="input-price" placeholder="Price" value="">
								<span class="glyphicon glyphicon-asterisk form-control-feedback" style="color: red"></span>
							</div>
						</div>
						<div class="form-group has-feedback">
							<label for="input-category" class="col-sm-2 control-label">Category</label>
							<div class="col-sm-10">
								<select name="category" id="input-category" class="form-control">
									<c:forEach items="${categoriess}" var="category">
										<option value="<c:out value='${category.id}' />">
											<c:out value="${category.nameFr}" />
										</option>
									</c:forEach>
								</select>
								<span class="glyphicon glyphicon-asterisk form-control-feedback" style="color: red"></span>
							</div>
						</div>
						<div class="form-group has-feedback">
							<label for="input-file" class="col-sm-2 control-label">Photo</label>
							<div class="col-sm-10">
								<input type="file" class="form-control" name="file" id="input-file" placeholder="Image JPG">
								<span class="glyphicon glyphicon-asterisk form-control-feedback" style="color: red"></span>
							</div>
						</div>
					</fieldset>
					<p class="text-right">
						<small>Fields marked with an asterisk (<span class="glyphicon glyphicon-asterisk" style="color: red"></span>) are mandatory.</small>
					</p>
					<div class="form-group" style="margin-bottom: 35px">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">Add</button>
						</div>
					</div>
				</form>
		</div>
	</div>

	<c:import url="/WEB-INF/inc/footer.jsp" />

	<script type="text/javascript">
		// Card remove function
		var product = {
			'remove': function(key) {
				$.ajax({
					url: '<c:url value="/web/removeProduct" />',
					type: 'post',
					data: 'id=' + key,
					dataType: 'json',
					beforeSend: function() {
						$('#cart > button').button('loading');
					},
					success: function(json) {
						$("#tr-"+key).remove();
					}
				});
			}
		};
	</script>

</body>

</html>