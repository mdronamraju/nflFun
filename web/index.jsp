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
			<div class="col-sm-6">
				<div class="panel panel-primary">
					<div class="panel-heading panel-title">
						Login
					</div>
					<div class="panel-body panel-collapse">
						<c:if test="${requestScope.loginErrors ne null}">
							<div class="well-sm bg-danger label-danger">
								<c:out value="${requestScope.loginErrors}"></c:out>
							</div>
							<br>
						</c:if>
						<c:if test="${requestScope.registrationSuccess ne null}">
							<div class="well-sm bg-success label-success">
								<c:out value="${requestScope.registrationSuccess}"></c:out>
							</div>
							<br>
						</c:if>
						<div class="form-bottom">
							<form role="form" action="login" method="post" class="login-form">
								<div class="form-group">
									<label class="sr-only" for="form-username">Username</label>
									<input type="text" name="email" placeholder="EMail..." class="form-username form-control" id="form-username">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">Password</label>
									<input type="password" name="password" placeholder="Password..." class="form-password form-control" id="form-password">
								</div>
								<button class="btn-primary btn-block text"  style="font-size: 18px;" type="submit">Login</button>
							</form>
						</div>
					</div>
				</div>
				<a href="register.jsp"><button class="btn-warning btn-block text" style="font-size: 18px;" type="submit">Register</button></a>
			</div>
			<div class="col-sm-3">
			</div>
		</div>
	</div>
    </body>
</html>