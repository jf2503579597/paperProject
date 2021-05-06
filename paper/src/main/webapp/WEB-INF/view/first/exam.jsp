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
				// 设定时间和欢迎语句
				setInterval(function () {
					var date = new Date();
					var time = "北京时间：" + date.getFullYear() + "年" +
						(date.getMonth() + 1) + "月" + (date.getDate() < 10 ? 0 + "" + date.getDate() : date.getDate()) + "日 "
						+ (date.getHours() < 10 ? 0 + "" + date.getHours() : date.getHours()) + ":" +
						(date.getMinutes() < 10 ? 0 + "" + date.getMinutes() : date.getMinutes()) + ":" +
						(date.getSeconds() < 10 ? 0 + "" + date.getSeconds() : date.getSeconds());
					$("#time").html(time);
					var hello = ""
					if (date.getHours() >= 6 && date.getHours() <= 12) {
						hello = "早上好"
					} else if (date.getHours() > 12 && date.getHours() <= 18) {
						hello = "下午好"
					} else {
						hello = "晚上好"
					}
					var gender = "${user.gender}" == "男" ? "先生" : "女士";
					$("#hello").html(hello + "," + "${user.name}" + gender)
					// 判断当前身份是教师还是管理员，给予其不同的审核通知
					if ("${user.authority.name}" == "教师") {
						// 教师登录
						// 查询该教师还有多少论文未被审核
						$.ajax({
							url: "teachExam",
							type: "post",
							dataType: "json",
							success: function (data) {
								// 返回结果为未被审核的条数
								$("#exam").html("您当前还有" + data + "篇论文未被审核！")
							}
						});
					} else {
						// 管理人员登录
						// 查询还有多少论文未被审核
						$.ajax({
							url: "adminExam",
							type: "post",
							dataType: "json",
							success: function (data) {
								// 返回结果为未被审核的条数
								$("#exam").html("您当前还有" + data + "篇论文需要审核！")
							}
						});
					}
				}, 1000);
			})
		</script>
	</head>
	<body>
		<div style="padding: 100px;text-align: center;">
			<h1 id="time"></h1>
			<h1 id="hello"></h1>
			<h1 id="exam"></h1>
		</div>
	</body>
</html>
