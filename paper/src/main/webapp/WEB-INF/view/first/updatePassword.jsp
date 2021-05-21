<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Title</title>
		<link rel="stylesheet" href="../static/css/bootstrap.min.css">
		<link rel="stylesheet" href="../static/css/font-awesome.min.css">
		<link rel="stylesheet" href="../static/css/ionicons.min.css">
		<link rel="stylesheet" href="../static/css/AdminLTE.min.css">
		<link rel="stylesheet" href="../static/css/skins/_all-skins.min.css">
		<script type="application/javascript" src="../static/js/jquery-3.5.1.min.js"></script>
		<script type="application/javascript" src="../static/jquery-ui/jquery-ui.min.js"></script>
		<script type="application/javascript" src="../static/js/bootstrap.min.js"></script>
		<script type="application/javascript" src="../static/js/adminlte.min.js"></script>
		<script type="application/javascript">
			$(function () {
				$("#lookPassword").mouseover(function () {
					$("#oldPassword, #newPassword, #newPassword1").attr('type', 'text');
				}).mouseout(function () {
					$("#oldPassword, #newPassword, #newPassword1").attr('type', 'password');
				})
			});
			function update() {
				var oldPassword = $("#oldPassword").val();
				var newPassword = $("#newPassword").val();
				var newPassword1 = $("#newPassword1").val();
				if (newPassword != newPassword1) {
					alert("两次新密码不同，请确认后再次提交！");
					return;
				}
				if ((oldPassword.trim()).length < 4 || (newPassword1.trim()).length < 4) {
					alert("密码长度不能小于4位")
					return;
				}
				if (newPassword == oldPassword) {
					alert("新密码不能与旧密码相同，请确认后再次提交！");
					return;
				}
				$.ajax({
					url:"updatePassword",
					type: "post",
					data: {
						"oldPassword": oldPassword,
						"newPassword": newPassword
					},
					dataType: "json",
					success: function (data) {
						if (data) {
							alert("密码修改成功，请重新登录！")
							parent.location.href = "../user/logout"
						} else {
							alert("旧密码输入错误，请核对！")
						}
					}
				});

			}
		</script>
	</head>
	<body>
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">请在下面修改您的密码</h3>
				</div>
				<form class="col-md-8" style="transform: translate(100px, 30px)">
					<div class="box-body">
						<div class="form-group">
							<label for="oldPassword">旧密码</label>
							<input type="password" class="form-control" id="oldPassword">
						</div>
						<div class="form-group">
							<label for="newPassword">新密码</label>
							<input type="password" class="form-control" id="newPassword" >
						</div>
						<div class="form-group">
							<label for="newPassword1">确认新密码</label>
							<input type="password" class="form-control" id="newPassword1" >
						</div>
						<div class="button">
							<label>
								<button  id="lookPassword" type="button">显示密码</button>
							</label>
						</div>
					</div>

					<div class="box-footer">
						<button type="button" class="btn btn-primary" onclick="update()">提交</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
