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
			function saveCollege() {
				var collegeName = $("#name").val();
				if (collegeName.length < 2) {
					alert("学院名称不得小于两位！");
					return;
				}
				$.ajax({
					url: "",
					type: "post",
					data: {
						"collegeName": collegeName,
					},
					dataType: "json",
					success: function (data) {
						if (data) {
							alert("添加成功！")
							parent.closeModal()
							parent.openFrame('fa fa-cloud', '学院信息维护', 'fa fa-cloud', '教师基本信息维护', 'teacher_infor/college')
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
						学院名称
					</td>
					<td>
						<input type="text" id="name" name="name" class="form-control" style="width: 300px;" placeholder="请填写学院名称">
					</td>
				</tr>

				<tr>
					<td colspan="2" style="text-align: right;">
						<button type="button" class="btn btn-success" onclick="saveCollege()">
							<i class="fa fa-save"></i>&nbsp;
							保存
						</button>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
