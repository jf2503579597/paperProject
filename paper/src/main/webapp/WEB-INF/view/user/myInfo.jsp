<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
			var oldBirthday = "${user.birthday.time}";
			var oldName = "${user.name}"
			var oldGender = "${user.gender}"
			// 将一个毫秒时间转换为date
			function dateChange(date) {
				date = parseInt(date);
				date = new Date(date);
				var birthday = "";
				birthday += date.getFullYear()+"-";
				birthday += (date.getMonth()+1)<10?"0"+(date.getMonth()+1):(date.getMonth()+1);
				birthday += "-";
				birthday += date.getDate()<10?"0"+(date.getDate()):(date.getDate());
				birthday += "T";
				birthday += date.getHours()<10?"0"+(date.getHours()):(date.getHours());
				birthday += ":";
				birthday += date.getMinutes()<10?"0"+(date.getMinutes()):(date.getMinutes());
				birthday += ":00";
				return birthday;
			}
			// 给日期赋默认值
			$(function () {
				// 给日期赋默认值
				var birthday = dateChange(oldBirthday);
				$("#birthday").val(birthday);
			})
			function subClock() {
				// 检测三个数值是否发生变化
				var name = $("#name").val();
				var gender = $("#gender").val();
				var birthday = $("#birthday").val();
				var old = dateChange(oldBirthday);
				// 检测三个信息是否发生变化
				if (old == birthday && oldName == name && oldGender == gender) {
					alert("检测到您当前未修改信息，请确认后再提交！")
					return;
				}
				// 检测姓名是否为空
				if (name.length == 0) {
					alert("姓名不能为空！");
					return;
				}
				$.ajax({
					url: "update",
					type: "post",
					data: {
						"name": name,
						"gender": gender,
						"birthday": birthday
					},
					dataType: "json",
					success: function (data) {
						if (data) {
							alert("修改成功！")
							parent.location.reload();
						} else {
							alert("修改失败，请检测是否修改完毕！")
						}
					}
				});
			}
		</script>
	</head>
	<body>
	<div class="col-md-12">
		<div class="box box-info">
			<div class="box-header with-border">
				<h3 class="box-title">信息修改</h3>
			</div>
			<form class="form-horizontal">
				<div class="box-body">
					<div class="form-group">
						<label for="card" class="col-sm-1 control-label">工号</label>

						<div class="col-sm-4">
							<input type="text" class="form-control" id="card" value="${user.card}" disabled>
						</div>

						<label for="name" class="col-sm-1 control-label">姓名</label>

						<div class="col-sm-4">
							<input type="text" class="form-control" id="name" value="${user.name}">
						</div>
					</div>
					<div class="form-group">
						<label for="gender" class="col-sm-1 control-label">性别</label>

						<div class="col-sm-4">
							<select class="form-control" id="gender">
								<c:choose>
									<c:when test="${user.gender eq '男'}">
										<option value="男">男</option>
										<option value="女">女</option>
									</c:when>
									<c:otherwise>
										<option value="女">女</option>
										<option value="男">男</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>

						<label for="birthday" class="col-sm-1 control-label">生日</label>

						<div class="col-sm-4">
							<input type="datetime-local" class="form-control" id="birthday" value="${user.birthday}">
						</div>
					</div>
					<div class="form-group">
						<label for="professor" class="col-sm-1 control-label">职称</label>

						<div class="col-sm-4">
							<input type="text" class="form-control" id="professor" value="${user.professor.professorName}" disabled>
						</div>

						<label for="college" class="col-sm-1 control-label">学院</label>

						<div class="col-sm-4">
							<input type="text" class="form-control" id="college" value="${user.college.collName}" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="major" class="col-sm-1 control-label">专业</label>

						<div class="col-sm-4">
							<input type="text" class="form-control" id="major" value="${user.major.majorName}" disabled>
						</div>

						<label for="authority" class="col-sm-1 control-label">权限</label>

						<div class="col-sm-4">
							<input type="text" class="form-control" id="authority" value="${user.authority.name}" disabled>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<button type="button" onclick="subClock()" class="btn btn-info">修改</button>
				</div>
			</form>
		</div>
	</div>
	</body>
</html>
