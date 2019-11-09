<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>QR Code Generation</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="icon"
	href="<c:url value="/resources/images/sudarshan-logo.png"/>" />
<link rel="shortcut icon"
	href="<c:url value="/resources/images/ico/favicon.ico"/>"
	type="image/x-icon">

<style type="text/css">
.page-title {
	font-family: 'Open Sans', sans-serif;
	font-style: normal;
	font-weight: 600;
	font-size: 24px;
	color: #4C4C4C;
}

.mt-1 {
	margin-top: 1rem !important;
}

.mb-2, .my-2 {
	margin-bottom: 0.5rem !important;
}
</style>

</head>

<body>
	<div id="fullpage">
		<div class="header">

			<%@include file="../views/common/welcome-header.jspf"%>

		</div>

		<div>
			<%@include file="../views/common/message.jspf"%>
		</div>


		<div class="container">
			<div class="row">
				<div class="col-6 mt-4">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">

							<li class="breadcrumb-item" style="color: blue;">Basic
								Example of QR Code Generation</li>
						</ol>
					</nav>
				</div>

				<div class="col-1"></div>
			</div>
		</div>


		<div class="container">
			<div class="row">

				<div class="col-7">
					<div class="page-title">QR Code Generation</div>
				</div>

			</div>
		</div>

		<div class="container">
			<div class="row">

				<form:form
					action="${pageContext.request.contextPath}/generateQrCode"
					method="post" modelAttribute="generateQrCode">

					<div id="fileDnDiv" class="col-12 row" style="margin-top: 25px;">

						<div class="col-12">
							<div class="border-bottom mt-1 mb-2"></div>
						</div>


						<div class="col-sm-6">

							<label for="content">Enter Content</label>
							<textarea id="content" name="content" required="required"
								class="form-control required"></textarea>

						</div>

						<div class="col-sm-12">
							<div class="border-bottom mt-1 mb-2"></div>
						</div>

						<div class="col-sm-12">
							<button type="submit" class="btn btn-info">Submit</button>
							<button type="reset" class="btn btn-danger">Cancel</button>
							<c:if test="${isEnableQrImg!=null && isEnableQrImg}">
							 <a href="${pageContext.request.contextPath}/" class="btn btn-info">Go Back</a>
							</c:if>
						</div>

					</div>

				</form:form>


			</div>

		</div>


		<c:if test="${isEnableQrImg!=null && isEnableQrImg}">

			<div class="container" style="margin-top: 40px;">
				<div class="row">

					<div class="col-7">
						<div class="page-title">Generated QR Code</div>
					</div>

					<div>
						<p>Scan the QR Image from QR Code Scanner mobile app to view
							the details.</p>
						<img src="data:image/jpg;base64,${generateQrCode.base64Image}" />
					</div>

				</div>
			</div>
		</c:if>

	</div>


	<div id="star-body-form">
		<div class="col-lg-12 col-sm-12 col-xs-12 copyrights">
			<%@include file="../views/login-footer.jsp"%>
		</div>
	</div>
</body>
</html>