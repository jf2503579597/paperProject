<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Title</title>
		<link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../static/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../static/css/AdminLTE.min.css">
		<script type="application/javascript" src="../static/js/jquery-3.5.1.min.js"></script>
		<script type="application/javascript" src="../static/js/bootstrap.min.js"></script>
		<script type="application/javascript" src="../static/js/adminlte.min.js"></script>
		<script type="application/javascript" src="../static/js/jquery.validate.min.js"></script>
		<script type="application/javascript">
			$(function () {
				var d = "${userById.birthday}";
				var date = new Date(d);
				var time = date.getFullYear() + "年" +
					(date.getMonth() + 1) + "月" + (date.getDate() < 10 ? 0 + "" + date.getDate() : date.getDate()) + "日 "
				$("#time").text(time);
			})
		</script>
	</head>
	<body>
		<form id="saveForm" role="form">
			<table style="border-collapse: separate; border-spacing: 5px;
			 margin: 0px auto; font-family: 宋体; font-size: 20px;border-spacing: 20px 20px">
				<tr>
					<td style="text-align: right">
						工号：
					</td>
					<td>
						${userById.card}
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						姓名：
					</td>
					<td>
						${userById.name}
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						性别：
					</td>
					<td>
						${userById.gender}
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						出生日期：
					</td>
					<td id="time">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						学院：
					</td>
					<td>
						${userById.college.collName}
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						专业：
					</td>
					<td>
						${userById.major.majorName}
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						职称：
					</td>
					<td>
						${userById.professor.professorName}
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						身份：
					</td>
					<td>
						${userById.authority.name}
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
