<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="login-box" class="login-box visible widget-box no-border">

	<div class="widget-body">
	 <div class="widget-main">
		<h4 class="header blue lighter bigger"><i class="ace-icon fa fa-coffee green"></i> Please Enter Your Information</h4>
		
		<div class="space-6"></div>
		<c:if test="${error==true}">
			<div class="alert alert-danger alert-dismissable">
	  			<a class="close" data-dismiss="alert" href="#" aria-hidden="true">&times;</a>
	  			<c:if test="${not empty errorMessage}">
	  				${errorMessage}	
	  			</c:if>
			</div>
		</c:if>
		<form  method="post" action="/j_spring_security_check">
			 <fieldset>
				<label class="block clearfix">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control" placeholder="Username" name="j_username" />
						<i class="icon-user"></i>
					</span>
				</label>
				
				<label class="block clearfix">
					<span class="block input-icon input-icon-right">
						<input type="password" class="form-control" placeholder="Password" name="j_password" />
						<i class="icon-lock"></i>
					</span>
				</label>
				
				<div class="space"></div>
				
				<div class="clearfix">
					<label class="inline">
						<input type="checkbox" class="ace" name="remember-me" /><span class="lbl"> Remember Me</span>
					</label>
					<button type="button" class="width-35 pull-right btn btn-sm btn-primary">
						<i class="ace-icon fa fa-key"></i>
						<span class="bigger-110">Login</span>
					</button>
				</div>
				
				<div class="space-4"></div>
				
			  </fieldset>
		</form>
		

		<!-- <div class="social-or-login center">
			<span class="bigger-110">Or Login Using</span>
		</div>
		<div class="social-login center">
			<a class="btn btn-primary"><i class="icon-facebook"></i></a>
			<a class="btn btn-info"><i class="icon-twitter"></i></a>
			<a class="btn btn-danger"><i class="icon-google-plus"></i></a>
		</div> -->
		
	 </div><!-- /.widget-main -->
	
	
	 <div class="toolbar clearfix">
		<div>
			<a href="#" data-target="#forgot-box" class="forgot-password-link">
				<i class="ace-icon fa fa-arrow-left"></i>
				I forgot my password
			</a>
		</div>
		<div>
			<a href="#" data-target="#signup-box" class="user-signup-link">
				I want to register
				<i class="ace-icon fa fa-arrow-right"></i>
			</a>
		</div>
	 </div>
	</div><!-- /.widget-body -->

</div><!-- /.login-box -->