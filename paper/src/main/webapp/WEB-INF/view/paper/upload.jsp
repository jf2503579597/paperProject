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
		<script type="application/javascript" src="../static/js/jquery.form.min.js"></script>
		<script type="application/javascript">
			$(function () {
				// 首先获取论文信息，找到期号，自增一传递过来
				$("#periodNoo").val("${sessionScope.periodNo}")
				// 异步查询期刊类别、学科、期刊级别、项目来源信息，并赋值给 session
				$.ajax({
					url: "getData",
					type: "post",
					dataType: "json",
					success: function (data) {
						// 获取的 data 就是一个 map 集合
						// 分别获取类别、学科、级别、来源集合
						var periodicalList = data.periodicalList;
						var subjectList = data.subjectList;
						var periodicalLevelList = data.periodicalLevelList;
						var projectSourceList = data.projectSourceList;
						// 打印期刊类别信息
						var options = "<option value=0>请选择期刊类别</option>"
						for (var i = 0; i < periodicalList.length; i++) {
							options = options + "<option value=" + periodicalList[i].id + ">" + periodicalList[i].periodName + "</option>"
						}
						$("#periodical").html(options);
						// 打印学科信息
						options = "<option value=0>请选择学科信息</option>"
						for (var i = 0; i < subjectList.length; i++) {
							options = options + "<option value=" + subjectList[i].id + ">" + subjectList[i].subName + "</option>"
						}
						$("#subject").html(options)
						// 打印期刊级别信息
						options = "<option value=0>请选择论文级别</option>"
						for (var i = 0; i < periodicalLevelList.length; i++) {
							options = options + "<option value=" + periodicalLevelList[i].id + ">" + periodicalLevelList[i].levelName + "</option>"
						}
						$("#periodLevel").html(options)
						// 打印项目来源信息
						options = "<option value=0>请选择项目来源</option>"
						for (var i = 0; i < projectSourceList.length; i++) {
							options = options + "<option value=" + projectSourceList[i].id + ">" + projectSourceList[i].sourceName + "</option>"
						}
						$("#source").html(options)
					}
				})
			})
			function subTest() {
				var title = $("#title").val()
				if (title.length < 2) {
					alert("标题不得小于两个字！")
					return;
				}
				if ($("#file").val() == "") {
					alert("当前未选择任何文件！")
					return;
				}
				var periodical = $("#periodical").val()
				var periodNo = $("#periodNo").val()
				var subject = $("#subject").val()
				var periodLevel = $("#periodLevel").val()
				var source = $("#source").val()
				switch ('0') {
					case periodical:
						alert("未选择期刊类别！");
						return;
					case subject:
						alert("未选择学科信息！")
						return;
					case periodLevel:
						alert("未选择论文级别！");
						return;
					case source:
						alert("未选择项目来源！")
						return;
				}
				$("#formSub").ajaxSubmit(
					function (data) {
						if (data) {
							alert("上传成功！")
							location.reload();
						} else {
							alert("上传失败")
						}
					}
				)
			}
 		</script>
	</head>
	<body>
		<div class="col-md-12">
			<div class="box box-info">
				<div class="box-header with-border">
					<h3 class="box-title">请在如下页面上传论文</h3>
				</div>
				<form class="form-horizontal" id="formSub" name="Form"  action="uploadFile" method="post" enctype="multipart/form-data">
					<div class="box-body">
						<div class="form-group">
							<label for="periodNoo" class="col-sm-1  control-label">期号</label>

							<div class="col-sm-4">
								<input type="text" class="form-control" id="periodNoo" name="periodNoo" readonly>
							</div>

							<label for="title" class="col-sm-1 control-label">标题</label>

							<div class="col-sm-4">
								<input type="text" class="form-control" id="title" name="title" >
							</div>
						</div>
						<div class="form-group">

							<label for="periodical" class="col-sm-1 control-label">期刊类别</label>

							<div class="col-sm-4">
								<select class="form-control" id="periodical" name="periodical">
									<!-- 期刊类别 -->
								</select>
							</div>

							<label for="subject" class="col-sm-1 control-label">学科</label>

							<div class="col-sm-4">
								<select class="form-control" id="subject" name="subject">
									<!-- 学科信息 -->
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="periodLevel" class="col-sm-1 control-label">期刊级别</label>

							<div class="col-sm-4">
								<select class="form-control" id="periodLevel" name="periodLevel">
									<!-- 期刊级别 -->
								</select>
							</div>

							<label for="source" class="col-sm-1 control-label">项目来源</label>

							<div class="col-sm-4">
								<select class="form-control" id="source" name="source">
									<!-- 项目来源 -->
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="file"  class="col-sm-1 control-label">上传论文</label>
							<div class="col-sm-9">
								<input type="file" id="file" name="file">
							</div>
						</div>
					</div>
					<div class="box-footer">
						<button type="button" onclick="subTest()" class="btn btn-info">上传</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
