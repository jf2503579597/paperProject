<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Title</title>
		<link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../static/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../static/css/AdminLTE.min.css">
		<link rel="stylesheet" type="text/css" href="../static/css/jquery.dataTables.min.css">
		<script type="application/javascript" src="../static/js/jquery-3.5.1.min.js"></script>
		<script type="application/javascript" src="../static/js/bootstrap.min.js"></script>
		<script type="application/javascript" src="../static/js/adminlte.min.js"></script>
		<script type="application/javascript" src="../static/js/jquery.dataTables.min.js"></script>
		<script type="application/javascript">
			$(function () {
				$("#myTable").DataTable({
					language: {
						// 将表格显示改为中文
						url: "../static/i18n/datatables-zh.json"
					},
					// 关闭搜索框
					searching:false,
					// 关闭排序
					ordering:false,
					// 设定表格数据通过异步获取
					serverSide: true,
					ajax: {
						url: "subPage",
						type: "post",
						data: function (d) {
							// 获得对应的设置信息
							var tableSetings = $("#myTable").dataTable().fnSettings();
							// 删除多余请求参数
							for(var key in d) {
								if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){
									//以columns开头的参数删除
									delete d[key];
								}
							}

							// 获得开始的下标
							var start = parseInt(tableSetings._iDisplayStart);
							// 获得每页显示数量
							var size = parseInt(tableSetings._iDisplayLength);
							// 根据开始的下标和每页显示数量获得页码
							var pageNum = start / size + 1;
							var pageSize = size;
							// 扩展请求时候的数据，重点在于pageNum和pageSize
							var params = {
								// 获得表格对象后，
								"pageNum": pageNum,
								// 每页显示数量
								"pageSize": pageSize
							}
							// 将新增的查询数据扩展到请求参数上
							$.extend(d, params); //给d扩展参数
						},
						// 设置回传参数为 json
						dataType: "json",
						// 过滤数据
						dataFilter: function(data) {
							data = JSON.parse(data);
							var returnData = {};
							returnData.draw = data.draw;
							returnData.recordsTotal = data.totalCount;
							returnData.recordsFiltered = data.totalCount;
							returnData.data = data.list;
							return JSON.stringify(returnData);
						}
					},
					// 根据查询结果定义每个字段的信息
					columnDefs: [
						{
							targets: 0,
							// 如下参数中的 row 即为 异步获取的 对象
							data: function (row, type, val, meta) {
								// 在序号位置
								var tableSetings = $("#myTable").dataTable().fnSettings();
								// 当前页开始开始下标，从 0 开始
								var start = parseInt(tableSetings._iDisplayStart);
								// 每页显示数量
								var size = parseInt(tableSetings._iDisplayLength);
								// 根据开始下标和每页显示数量计算当前页
								var page = start / size + 1;
								// 获得当前条目下标
								var index = parseInt(meta.row);
								return (page - 1) * size + index + 1;
							}
						}, {
							targets: 1,
							data: function (row, type, val, meta) {
								// 用户姓名
								// row 就是该行的数据对象
								return row.subName;
							}
						}, {
							targets: 2,
							data: function (row, type, val, meta) {
								var deleteBtn = "<a href='javascript:openDeleteModel(" + row.id + ")' class='btn btn-danger btn-xs'><i class='fa fa-remove'></i>&nbsp;删除</a>"
								return deleteBtn;
							}
						}
					]
				});
			});
			function openAddModel() {
				var title = "<i class = 'fa fa-plus'></i>&nbsp; 添加学科"
				var url = "../paper_infor/addSubject";
				var width = 650;
				var height = 200;
				parent.openModel(title, url, width, height)
			}
			function openDeleteModel(id) {
				var flag = confirm("确定删除该学科？删除后该学科所有论文一并删除！");
				if (flag) {
					$.ajax({
						url: "deleteSubject",
						data: {
							"id": id
						},
						type: "post",
						dataType: "json",
						success: function (data) {
							if (data) {
								alert("删除成功！")
								location.reload()
							} else {
								alert("删除失败！")
							}
						}
					});
				}
			}
		</script>
	</head>
	<body>
		<div class="container" style="margin: 0px 5px; height: 100%; padding: 10px">
			<div class="row">
				<div class="col-md-12">
					<a href="javascript:openAddModel()" class="btn btn-success">
						<i class="fa fa-plus"></i>&nbsp;
						添加学科
					</a>
				</div>
			</div>
			<div class="row" style="margin: 5px 20px">
				<div class="col-md-12">
					<table id="myTable" class="table-cell table-hover">
						<thead>
						<tr>
							<th>序号</th>
							<th>学科名称</th>
							<th>操作</th>
						</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
