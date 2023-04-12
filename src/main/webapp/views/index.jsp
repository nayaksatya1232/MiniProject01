<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Report</title>
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-lg bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand text-white m-auto fw-bold" href="#">Insurance
				Report Generator</a>
		</div>
	</nav>
	<div class="container p-5 mt-5"
		style="background-color: rgb(235, 236, 237)">
		<div class="row">
			<div class="col">
				<form:form class="row g-3" action="search" method="post"
					modelAttribute="search">
					<div class="col-md-4">
						<form:label path="" for="inputState" class="form-label">Plan Name</form:label>
						<form:select path="planName" id="inputState" class="form-select">
							<form:option value="">--Select--</form:option>
							<form:options items="${plans}" />
						</form:select>
					</div>
					<div class="col-md-4">
						<form:label path="" for="inputState" class="form-label">Plan Status</form:label>
						<form:select path="planStatus" id="inputState" class="form-select">
							<form:option value="">--Select--</form:option>
							<form:options items="${statuses}" />
						</form:select>
					</div>
					<div class="col-md-4">
						<form:label path="" for="inputState" class="form-label">Gender</form:label>
						<form:select path="gender" id="inputState" class="form-select">
							<form:option value="">--Select--</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Female">Female</form:option>
						</form:select>
					</div>
					<div class="col-md-4">
						<form:label path="" for="inputEmail4" class="form-label">Plan Start
							Date</form:label>
						<form:input path="planStartDate" type="date" class="form-control"
							id="inputEmail4"></form:input>
					</div>
					<div class="col-md-4">
						<form:label path="" for="inputEmail4" class="form-label">Plan End Date</form:label>
						<form:input path="planEndDate" type="date" class="form-control"
							id="inputEmail4"></form:input>
					</div>
					<div class="col-md-4 mt-5 ">
						<button type="submit" class="btn btn-primary">Search</button>
						<a href="/" type="reset" class="btn btn-primary">Reset</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<div class="container p-2 mt-2"
		style="background-color: rgb(235, 236, 237)">
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">id</th>
					<th scope="col">Name</th>
					<th scope="col">PlanName</th>
					<th scope="col">PlanStatus</th>
					<th scope="col">StartDate</th>
					<th scope="col">EndDate</th>
					<th scope="col">BenifitAmount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${records}" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td>${record.citizenName}</td>
						<td>${record.planName}</td>
						<td>${record.planStatus}</td>
						<td>${record.planStartDate}</td>
						<td>${record.planEndDate}</td>
						<td>${record.benifitAmt}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty records}">
					<tr>
						<td colspan="7" class="text-center bg-secondary">--No Records Found--</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="container p-3 mt-2"
		style="background-color: rgb(235, 236, 237)">
		<div class="row">
			<div class="col-4 m-auto">
				<a href="/pdf" class="btn btn-primary">PDF</a>
				<a href="/excel" class="btn btn-primary ml-2">EXCEL</a>
			</div>
		</div>
	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>