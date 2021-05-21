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
		$(function () {
			// 获取自动增长的 card
			$("#card").val("${card}")
			// 获取职称信息
			$.ajax({
				url: "getProfessor",
				type: "post",
				dataType: "json",
				success: function (data) {
					var options = "<option value=0>请选择职称信息</option>"
					for (var i = 0; i < data.length; i++) {
						options = options + "<option value=" + data[i].id + ">" + data[i].professorName + "</option>"
					}
					$("#professor").html(options);
				}
			});
			// 获取学院信息
			$.ajax({
				url: "getCollege",
				type: "post",
				dataType: "json",
				success: function (data) {
					var options = "<option value=0>请选择学院信息</option>"
					for (var i = 0; i < data.length; i++) {
						options = options + "<option value=" + data[i].id + ">" + data[i].collName + "</option>"
					}
					$("#college").html(options);
				}
			});
		})
		// 当学院信息选中时再获取该学院的专业信息
		function majorFun() {
			var collId = $("#college").val();
			if (collId == 0) {
				$("#major").html("<option value=-1>请先选择学院信息</option>")
				return;
			}
			// 获取专业信息
			$.ajax({
				url: "getMajor",
				type: "post",
				data: {
					"collId":collId
				},
				dataType: "json",
				success: function (data) {
					var options = "<option value=0>请选择专业信息</option>"
					for (var i = 0; i < data.length; i++) {
						options = options + "<option value=" + data[i].id + ">" + data[i].majorName + "</option>"
					}
					$("#major").html(options);
				}
			});
		}
		// 提交
		function subClock() {
			// 获取不可能为空的数据
			var card = $("#card").val();
			var gender = $("#gender").val();
			var authority = $("#authority").val();
			// 首先获取姓名、生日、职称、学院信息、专业信息是否为空
			var name = $("#name").val();
			var birthday = $("#birthday").val();
			var password = $("#password").val();
			var professor = $("#professor").val();      // 不能为 0
			var college = $("#college").val();          // 不能为 0
			var major = $("#major").val();              // 不能为 0 和 -1
			if (password == "" || password.length < 4) {
				alert("密码长度不能小于四位！")
				return;
			}
			switch ("") {
				case name:
					alert("姓名不能为空！");
					return;
				case birthday:
					alert("生日不能为空！");
					return;
			}
			switch ('0') {
				case professor:
					alert("职称信息不能为空！");
					return;
				case college:
					alert("学院信息不能为空！");
					return;
				case major:
					alert("专业信息不能为空！");
					return;
			}
			// 信息都输入成功，异步交互进行保存用户信息
			$.ajax({
				url: "addTeacher",
				type: "post",
				data: {
					"card": card,
					"password": password,
					"name": name,
					"gender": gender,
					"birthday": birthday,
					"professor": professor,
					"college": college,
					"major": major,
					"authority": authority
				},
				dataType: "json",
				success: function (data) {
					if (data) {
						alert("添加成功")
						// 刷新当前页面
						location.reload();
					} else {
						alert("添加失败！")
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
				<h3 class="box-title">请在如下页面添加教师</h3>
			</div>
			<form class="form-horizontal">
				<div class="box-body">
					<div class="form-group">
						<label for="card" class="col-sm-1 control-label">工号</label>

						<div class="col-sm-4">
							<input type="text" class="form-control" id="card" disabled>
						</div>

						<label for="password" class="col-sm-1 control-label">密码</label>

						<div class="col-sm-4">
							<input type="text" class="form-control" id="password" >
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-1 control-label">姓名</label>

						<div class="col-sm-4">
							<input type="text" class="form-control" id="name" >
						</div>

						<label for="gender" class="col-sm-1 control-label">性别</label>

						<div class="col-sm-4">
							<select class="form-control" id="gender">
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>

					</div>
					<div class="form-group">
						<label for="birthday" class="col-sm-1 control-label">生日</label>

						<div class="col-sm-4">
							<input type="date" class="form-control" id="birthday">
						</div>

						<label for="professor" class="col-sm-1 control-label">职称</label>

						<div class="col-sm-4">
							<select class="form-control" id="professor">
								<!-- 职称 -->
							</select>
						</div>

					</div>
					<div class="form-group">
						<label for="college" class="col-sm-1 control-label">学院</label>

						<div class="col-sm-4">
							<select class="form-control" id="college" onchange="majorFun()">
								<!-- 学院 -->
							</select>
						</div>

						<label for="major" class="col-sm-1 control-label">专业</label>

						<div class="col-sm-4">
							<select class="form-control" id="major" onchange="majorTest()">
								<!-- 专业 -->
								<option value="-1">请先选择学院信息</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="authority" class="col-sm-1 control-label">权限</label>

						<div class="col-sm-4">
							<select class="form-control" id="authority">
								<!-- 权限 -->
								<option value="2">科研秘书</option>
								<option value="3">教师</option>
							</select>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<button type="button" onclick="subClock()" class="btn btn-info">添加</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
