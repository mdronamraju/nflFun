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
	<div class="container">
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading panel-title">
						Login
					</div>
					<div class="panel-body panel-collapse">
						<div class="form-bottom">
							<form role="form" action="login" method="post" class="login-form">
								<div class="form-group">
									<label class="sr-only" for="form-username">Username</label>
									<input type="text" name="form-username" placeholder="Username..." class="form-username form-control" id="form-username">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">Password</label>
									<input type="password" name="form-password" placeholder="Password..." class="form-password form-control" id="form-password">
								</div>
								<button type="submit" class="btn">Login</button>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading panel-title">
						Register
					</div>
					<div class="panel-body panel-collapse">
						<div class="form-bottom">
							<form role="form" action="addUser" method="post" class="registration-form">
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
									<input type="text" name="password" placeholder="Password" class="form-password form-control" id="form-password1">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">Password</label>
									<input type="text" name="confirmPassword" placeholder="Confirm Password" class="form-password form-control" id="form-password2">
								</div>
								<button type="submit" class="btn">Register</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    </body>
</html>