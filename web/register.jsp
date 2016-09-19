<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login Register Templates</title>

        <link rel="stylesheet" href="bootstrap-3.3.7/css/bootstrap.css">
        <link rel="stylesheet" href="bootstrap-3.3.7/font-awesome/css/font-awesome.min.css">

    </head>

    <body>

	<jsp:include page="header.jsp"/>

	<!-- Top content -->

	<br>
	<div class="container">
		<div class="row">
			<div class="col-sm-3">
			</div>
			<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading panel-title">
						Register
					</div>
					<div class="panel-body panel-collapse">
						<c:if test="${requestScope.registrationErrors ne null}">
							<div class="well-sm label-danger">
								<c:out value="${requestScope.registrationErrors}"></c:out>
							</div>
							<br>
						</c:if>
						<div class="form-bottom">
							<form role="form" action="register" method="post" class="registration-form">
								<div class="form-group">
									<label class="sr-only" for="form-first-name">First name</label>
									<input type="text" name="firstName" placeholder="First Name" class="form-first-name form-control" id="form-first-name">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-last-name">Last name</label>
									<input type="text" name="lastName" placeholder="Last Name" class="form-last-name form-control" id="form-last-name">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-email">Email</label>
									<input type="text" name="email" placeholder="Email" class="form-email form-control" id="form-email">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">Password</label>
									<input type="password" name="password" placeholder="Password" class="form-password form-control" id="form-password1">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">Password</label>
									<input type="password" name="confirmPassword" placeholder="Confirm Password" class="form-password form-control" id="form-password2">
								</div>
								<button type="submit" class="btn-primary">Register</button>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
			</div>
		</div>
	</div>
    </body>
</html>