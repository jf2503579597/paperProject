<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Title</title>
		<link rel="stylesheet" href="static/css/bootstrap.min.css">
		<link rel="stylesheet" href="static/css/font-awesome.min.css">
		<link rel="stylesheet" href="static/css/ionicons.min.css">
		<link rel="stylesheet" href="static/css/AdminLTE.min.css">
		<link rel="stylesheet" href="static/css/skins/_all-skins.min.css">
		<script type="application/javascript" src="static/js/jquery-3.5.1.min.js"></script>
		<script type="application/javascript" src="static/jquery-ui/jquery-ui.min.js"></script>
		<script type="application/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="application/javascript" src="static/js/adminlte.min.js"></script>
		<script type="application/javascript" src="static/js/jquery.form.min.js"></script>
		<script type="application/javascript">
		</script>
	</head>
	<body>
		<form name="Form"  action="file/uploadFile" method="post" enctype="multipart/form-data">
			<h2>采用multipart提供的file.transfer方法上传文件</h2>
			<input value="123456" id="inTest" name="inTest">
			<input type="file" name="file" id="file">
			<input type="submit" value="upload"/><br>
			<select name="sele" id="sele">
				<option value="1">西安</option>
				<option value="2">宝鸡</option>
				<option value="3">咸阳</option>
			</select>
		</form>
		<a href="file/download">下载文件</a>
	</body>
</html>
