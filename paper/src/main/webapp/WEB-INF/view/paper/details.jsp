<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
			var d = "${paperById.createTime}";
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
				标题：
			</td>
			<td>
				${paperById.title}
			</td>
		</tr>
		<tr>
			<td style="text-align: right">
				期号：
			</td>
			<td>
				${paperById.periodNo}
			</td>
		</tr>
		<tr>
			<td style="text-align: right">
				期刊类型：
			</td>
			<td>
				${paperById.periodical.periodName}
			</td>
		</tr>
		<tr>
			<td style="text-align: right">
				所属学科：
			</td>
			<td>
				${paperById.subject.subName}
			</td>
		</tr>
		<tr>
			<td style="text-align: right">
				期刊级别：
			</td>
			<td>
				${paperById.periodicalLevel.levelName}
			</td>
		</tr>
		<tr>
			<td style="text-align: right">
				项目来源：
			</td>
			<td>
				${paperById.projectSource.sourceName}
			</td>
		</tr>
		<tr>
			<td style="text-align: right">
				作者：
			</td>
			<td>
				${paperById.user.name}
			</td>
		</tr>
		<tr>
			<td style="text-align: right">
				作者专业：
			</td>
			<td>
				${paperById.user.major.majorName}
			</td>
		</tr>
		<tr>
			<td style="text-align: right">
				创建时间：
			</td>
			<td id="time">

			</td>
		</tr>
		<tr>
			<td style="text-align: right">
				状态：
			</td>
			<td>
				${paperById.exam}
			</td>
		</tr>
		<c:choose>
			<c:when test="${paperById.examFile != null}">
				<tr>
					<td style="text-align: right">
						审核失败理由：
					</td>
					<td>
						${paperById.examFile}
					</td>
				</tr>
			</c:when>
		</c:choose>

<%--		<tr>--%>
<%--			<td colspan="2" style="text-align: right;">--%>
<%--				<button type="button" class="btn btn-success" onclick="saveCollege()">--%>
<%--					<i class="fa fa-save"></i>&nbsp;--%>
<%--					保存--%>
<%--				</button>--%>
<%--			</td>--%>
<%--		</tr>--%>
	</table>
</form>
</body>
</html>
