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
									<label class="sr-only">First name</label>
									<input type="text" name="firstName" placeholder="First Name" id="form-first-name" class="form-first-name form-control">
								</div>
								<div class="form-group">
									<label class="sr-only" >Last name</label>
									<input type="text" name="lastName" placeholder="Last Name" id="form-last-name" class="form-last-name form-control">
								</div>
								<div class="form-group">
									<label class="sr-only">Email</label>
									<input type="text" name="email" placeholder="Email" id="form-email" class="form-email form-control">
								</div>
								<div class="form-group">
									<label class="sr-only">Password</label>
									<input type="password" name="password" placeholder="Password" id="form-password1" class="form-password form-control">
								</div>
								<div class="form-group">
									<label class="sr-only">Password</label>
									<input type="password" name="confirmPassword" placeholder="Confirm Password" id="form-password2" class="form-password form-control">
								</div>
								<div class="form-group">
									<label class="sr-only">Points</label>
									<input type="text" name="totalPoints" placeholder="100 Total Points" disabled id="totalPoints" class="form-totalPoints form-control">
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