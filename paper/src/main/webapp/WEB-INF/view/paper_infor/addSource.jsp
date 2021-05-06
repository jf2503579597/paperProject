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
			function saveSource() {
				var sourceName = $("#name").val();
				if (sourceName.length < 2) {
					alert("项目来源名称不得小于两位！");
					return;
				}
				$.ajax({
					url: "",
					type: "post",
					data: {
						"sourceName": sourceName
					},
					dataType: "json",
					success: function (data) {
						if (data) {
							alert("添加成功！")
							parent.closeModal()
							parent.openFrame('fa fa-cloud', '项目来源', 'fa fa-cloud', '论文基本信息维护', 'paper_infor/project_source')
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
						项目来源
					</td>
					<td>
						<input type="text" id="name" name="name" class="form-control" style="width: 300px;" placeholder="请填写项目来源名称">
					</td>
				</tr>

				<tr>
					<td colspan="2" style="text-align: right;">
						<button type="button" class="btn btn-success" onclick="saveSource()">
							<i class="fa fa-save"></i>&nbsp;
							保存
						</button>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
