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
				// 首先设定选择的学院信息
				$.ajax({
					url: "getCollegeList",
					type: "post",
					dataType: "json",
					success: function (data) {
						var options = "<option value=0>请选择学院信息</option>"
						for (var i = 0; i < data.length; i++) {
							options = options + "<option value=" + data[i].id + ">" + data[i].collName + "</option>"
						}
						$("#college").html(options);
					}
				})
			})
			function saveMajor() {
				var majorName = $("#name").val();
				var id = $("#college").val();
				if (majorName.length < 2) {
					alert("学院名称不得小于两位！");
					return;
				}
				if (id == 0) {
					alert("未选择所属学院！")
					return;
				}
				$.ajax({
					url: "",
					type: "post",
					data: {
						"majorName": majorName,
						"id": id
					},
					dataType: "json",
					success: function (data) {
						if (data) {
							alert("添加成功！")
							parent.closeModal()
							parent.openFrame('fa fa-cloud', '专业信息维护', 'fa fa-cloud', '教师基本信息维护', 'teacher_infor/major')
						} else {
							alert("添加失败！")
						}
					}
				})
			}
		</script>
	</head>
	<body>
		<form id="saveForm" role="form">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tr>
					<td>
						<i class="fa fa-key"></i>&nbsp;
						专业名称
					</td>
					<td>
						<input type="text" id="name" name="name" class="form-control" style="width: 300px;" placeholder="请填写专业名称">
					</td>
				</tr>
				<tr>
					<td>
						<i class="fa fa-key"></i>&nbsp;
						所属学院
					</td>
					<td>
						<select class="form-control" id="college">
						</select>
					</td>
				</tr>

				<tr>
					<td colspan="2" style="text-align: right;">
						<button type="button" class="btn btn-success" onclick="saveMajor()">
							<i class="fa fa-save"></i>&nbsp;
							保存
						</button>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
