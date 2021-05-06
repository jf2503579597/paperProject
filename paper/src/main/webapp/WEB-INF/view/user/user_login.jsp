<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Title</title>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../static/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../static/css/AdminLTE.min.css">
		<script type="application/javascript" src="../static/js/jquery-3.5.1.min.js"></script>
		<script type="application/javascript" src="../static/js/bootstrap.min.js"></script>
		<script type="application/javascript">
			function login() {
				var card = $("#card").val();
				var password = $("#password").val();
				var auth = $("#authority").val();
				$.ajax({
					url:"login",
					type: "post",
					data: {
						"username":card,
						"password":password,
						"auth":auth
					},
					dataType:"json",
					success: function (data) {
						if (data) {
							location.href = "../menu/index";
						} else {
							alert("登录失败，请确认账号密码正确再次登录！")
						}
					}
				});
			}
			function doRegister() {
				alert("抱歉，该系统不支持注册！请找管理员登记！")
			}
		</script>
	</head>
	<body class="hold-transition login-page">
		<div class="login-box">
			<div class="login-logo">
				<a href="#"><b>科研论文</b>管理系统</a>
			</div>
			<div class="login-box-body">
				<p class="login-box-msg">请输入您的信息登录系统</p>
				<form>
					<div class="form-group has-feedback">
						<input type="text" id="card" name="username" class="form-control" placeholder="请输入您的工号">
						<span class="glyphicon glyphicon-phone form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" id="password" name="password" class="form-control" placeholder="请输入您的登录密码">
						<span class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<select id="authority" name="authority" class="form-control">
							<option value="1">管理员</option>
							<option value="2">科研秘书</option>
							<option value="3">教师</option>
						</select>
						<span class="glyphicon glyphicon-user form-control-feedback"></span>
					</div>
					<div class="row">
						<div class="col-xs-8">
							<div class="checkbox icheck">
								<label></label>
							</div>
						</div>
						<div class="col-xs-4">
							<button type="button" class="btn btn-primary btn-block btn-flat" onclick="login()">
								<i class="fa fa-sign-in"></i>&nbsp;
								登录
							</button>
						</div>
					</div>
				</form>
				<div class="social-auth-links text-center">
					<p>- OR -</p>
					<a href="javascript:doRegister()" class="btn btn-block btn-social btn-facebook btn-flat">
						<i class="fa fa-user-plus"></i>
						还没有账号？进行注册...
					</a>
				</div>
			</div>
		</div>
	</body>
</html>
