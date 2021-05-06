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
			function openFrame(childIcon, childText, parentIcon, parentText, url) {
				$("#titleIcon").attr("class", childIcon);
				$("#titleText").text(childText);
				$("#parentIcon").attr("class", parentIcon);
				$("#parentText").text(parentText);
				$("#childText").text(childText);
				var height = ($("#content").parent("div").height()) * 0.87;
				url = "../" + url;
				var iframe = "<iframe src='" + url + "' style='width:100%; height:" + height + "px; border: 0px solid #FFF;'></iframe>"
				$("#content").html(iframe);
			}
			<!-- 由子页面调用父页面的模态框，需传入所需的参数：标题、url 地址、模态框宽度、模态框高度 -->
			function openModel(title, url, width, height) {
				<!-- 传入标题，由于标题还要传入图标，所以这里采用html -->
				$("#myModalLabel").html(title);
				var iframe = "<iframe src='" + url + "' style='width: 100%; height: 100%; border:0px solid #FFF;'></iframe>";
				<!-- 给模态框传入对应页面 -->
				$("#myModelBody").html(iframe);
				$("#myModelBody").css("height", height + "px");
				$(".modal-dialog").css("width", width + "px")
				$("#myModal").modal();
			}
			function closeModal() {
				$("#myModal").modal("hide");
			}

			// 默认打开审核通知页面
			$(function () {
				openFrame('fa fa-cloud', '审核通知', 'fa fa-cloud', '首页', 'first/exam')
			})
		</script>
	</head>
	<body class="hold-transition skin-blue sidebar-mini">
		<div class="wrapper">
			<header class="main-header">
				<a href="index2.html" class="logo">
					<span class="logo-mini"><b>A</b>LT</span>
					<span class="logo-lg"><b>Admin</b>LTE</span>
				</a>
				<nav class="navbar navbar-static-top">
					<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
						<span class="sr-only">Toggle navigation</span>
					</a>
					<div class="navbar-custom-menu">
						<ul class="nav navbar-nav">
							<li class="dropdown messages-menu">
								<a href="javascript:openFrame('fa fa-user', '我的信息', 'fa fa-user', '信息修改', 'user/myInfo')">
									<i class="fa fa-user"></i>
									${sessionScope.user.name}
								</a>
							</li>
							<li class="dropdown messages-menu">
								<a href="../user/logout">
									<i class="fa fa-sign-out"></i>
									退出
								</a>
							</li>
						</ul>
					</div>
				</nav>
			</header>
			<aside class="main-sidebar">
				<section class="sidebar">
					<div class="user-panel">
						<div class="pull-left image">
							<img src="../static/img/avatar04.png" class="img-circle" alt="User Image">
						</div>
						<div class="pull-left info">
							<p>Alexander Pierce</p>
							<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
						</div>
					</div>
					<ul class="sidebar-menu" data-widget="tree">
						<c:forEach var="menu" items="${menuList}">
							<li class="treeview">
								<a href="${menu.url}">
									<i class="${menu.icon}"></i>
									<span>${menu.menuName}</span>
									<span class="pull-right-container">
											<i class="fa fa-angle-left pull-right"></i>
										</span>
								</a>
								<ul class="treeview-menu">
									<c:forEach var="child" items="${menu.childMenus}">
										<li class="active">
											<!-- 点击打开在右边显示每个子页面 -->
											<!-- 传入当前页面图标，当前页面功能名称，父页面图标，父页面功能名称，url地址 -->
											<a href="javascript:openFrame('${child.icon}', '${child.menuName}', '${menu.icon}', '${menu.menuName}', '${child.url}')">
												<i class="${child.icon}"></i>
													${child.menuName}
											</a>
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</section>
			</aside>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						<!-- 当前页面图标 -->
						<i id="titleIcon"></i>
						<!-- 当前页面功能名称 -->
						<span id="titleText"></span>
						<small>Control panel</small>
					</h1>
					<ol class="breadcrumb">
						<li>
							<a href="#">
								<!-- 当前页面父页面图标 -->
								<i id="parentIcon"></i>
								<!-- 当前页面父页面功能 -->
								<span id="parentText"></span>
							</a>
						</li>
						<!-- 当前页面功能名称 -->
						<li id="childText" class="active"></li>
					</ol>
				</section>
				<section id="content" class="content">
					<!-- 存放链接页面 -->
				</section>
			</div>
			<footer class="main-footer">
				<div class="pull-right hidden-xs">
					<b>Version</b> 2.0.0
				</div>
				<strong>Copyright &copy; 2020-2021 <a href="https://adminlte.io">AdminLTE</a>.</strong> All rights
				reserved.
			</footer>
			<div class="control-sidebar-bg"></div>
		</div>

		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<!-- 模态框标题 -->
						<h4 class="modal-title" id="myModalLabel"></h4>
					</div>
					<div class="modal-body" id="myModelBody">
						<!-- 模态框内容 -->
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</body>
</html>
