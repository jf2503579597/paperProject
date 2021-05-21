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
				onchangeTest();
				// 首先给几个选项框赋值
				// 异步查询期刊类别、学科、期刊级别、项目来源、学院、专业、职称信息
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
						var collegeList = data.collegeList;
						var professorList = data.professorList
						// 打印期刊类别信息
						var options = "<option value=0>根据期刊类型查询</option>"
						for (var i = 0; i < periodicalList.length; i++) {
							options = options + "<option value=" + periodicalList[i].id + ">" + periodicalList[i].periodName + "</option>"
						}
						$("#periodical").html(options);
						// 打印学科信息
						options = "<option value=0>根据学科查询</option>"
						for (var i = 0; i < subjectList.length; i++) {
							options = options + "<option value=" + subjectList[i].id + ">" + subjectList[i].subName + "</option>"
						}
						$("#subject").html(options)
						// 打印期刊级别信息
						options = "<option value=0>根据期刊级别查询</option>"
						for (var i = 0; i < periodicalLevelList.length; i++) {
							options = options + "<option value=" + periodicalLevelList[i].id + ">" + periodicalLevelList[i].levelName + "</option>"
						}
						$("#periodicalLevel").html(options)
						// 打印项目来源信息
						options = "<option value=0>根据项目来源查询</option>"
						for (var i = 0; i < projectSourceList.length; i++) {
							options = options + "<option value=" + projectSourceList[i].id + ">" + projectSourceList[i].sourceName + "</option>"
						}
						$("#projectSource").html(options)

						// 打印学院信息
						options = "<option value=0>根据学院信息查询</option>"
						for (var i = 0; i < collegeList.length; i++) {
							options = options + "<option value=" + collegeList[i].id + ">" + collegeList[i].collName + "</option>"
						}
						$("#college").html(options)

						// 打印学院信息信息
						options = "<option value=-1>请先选择学院信息</option>"
						$("#major").html(options)

						// 打印职称信息
						options = "<option value=0>根据教师职称信息查询</option>"
						for (var i = 0; i < professorList.length; i++) {
							options = options + "<option value=" + professorList[i].id + ">" + professorList[i].professorName + "</option>"
						}
						$("#professor").html(options)
					}
				})
			});
			// 下载函数
			function download(id) {
				location.href = "download?id=" + id;
			}
			// 删除函数
			function openDeleteModel(id) {
				var flag = confirm("确定删除该论文？");
				if (flag) {
					$.ajax({
						url: "deletePaper",
						type: "post",
						data: {
							"id": id
						},
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
			// 详情函数
			function openDetailsModel(id) {
				var title = "<i class = 'fa fa-lastfm'></i>&nbsp; 论文详情"
				var url = "../paper/details?id=" + id;
				var width = 650;
				var height = 500;
				parent.openModel(title, url, width, height)
			}

			/**
			 * 当选项框发生改变，论文信息相应的也发生改变
			 */
			function onchangeTest() {
				var periodical = $("#periodical").val();
				var subject = $("#subject").val();
				var periodicalLevel = $("#periodicalLevel").val();
				var projectSource = $("#projectSource").val();
				var college = $("#college").val();
				var major = $("#major").val();
				var professor = $("#professor").val();
				var name = $("#name").val();
				$("#myTable").dataTable().fnDestroy();
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
						url: "paperPage",
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
								"pageSize": pageSize,
								// 是否审核页面
								"isCheck": false,
								// 期刊类别
								"periodical": periodical,
								// 学科
								"subject": subject,
								// 期刊级别
								"periodicalLevel": periodicalLevel,
								// 项目来源
								"projectSource": projectSource,
								// 学院信息
								"college": college,
								// 专业信息
								"major":major,
								// 职称信息
								"professor":professor,
								// 姓名模糊查询
								"name": name
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
								// 论文标题
								// row 就是该行的数据对象
								var download = "<a href='javascript:download(" + row.id + ")'>" + row.title + "</a>"
								return download;
							}
						}, {
							targets: 2,
							data: function (row, type, val, meta) {
								// 期号
								if (row.periodNo == null) {
									return null;
								}
								return row.periodNo;
							}
						}, {
							targets: 3,
							data: function (row, type, val, meta) {
								// 作者姓名
								if (row.user == null) {
									return null;
								}
								return row.user.name;
							}
						},{
							targets: 4,
							data: function (row, type, val, meta) {
								var deleteBtn = "<a href='javascript:openDeleteModel(" + row.id + ")' class='btn btn-danger btn-xs'><i class='fa fa-remove'></i>&nbsp;删除</a>";
								var detailsBtn = "<a href='javascript:openDetailsModel(" + row.id + ")' class='btn btn-success btn-xs'><i class='fa fa-hand-peace-o'></i>&nbsp;详情</a>";
								if (row.user != null && ${sessionScope.user.authority.id} != 1) {
									return detailsBtn;
								}
								return detailsBtn + "&nbsp;" + deleteBtn;
							}
						}
					]
				});
			}

			function onchangeCollege() {
				onchangeTest();
				var collId = $("#college").val();
				if (collId == 0) {
					$("#major").html("<option value=-1>请先选择学院信息</option>")
					return;
				}
				// 获取专业信息
				$.ajax({
					url: "../teacher/getMajor",
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
		</script>
	</head>
	<body>
		<div class="container" style="margin: 0px 5px; height: 100%; padding: 10px; background-size: 20px">
		  <div class="row">
			  <div class="col-lg-12">
				<div class="form-group">
					<div class="col-sm-3">
						<select class="form-control" id="periodical" onchange="onchangeTest()">
							<!-- 学院 -->
						</select>
					</div>
					<div class="col-sm-3">
						<select class="form-control" id="subject" onchange="onchangeTest()">
						</select>
					</div>
					<div class="col-sm-3">
						<select class="form-control" id="periodicalLevel" onchange="onchangeTest()">
						</select>
					</div>
					<div class="col-sm-3">
						<select class="form-control" id="projectSource" onchange="onchangeTest()">
							<!-- 专业 -->
						</select>
					</div>
				</div>
			  </div>
			  <div class="col-lg-12" style="margin: 5px 0px 0px 0px">
				  <div class="form-group">
					  <div class="col-sm-3">
						  <select class="form-control" id="college" onchange="onchangeCollege()">
							  <!-- 学院 -->
						  </select>
					  </div>
					  <div class="col-sm-3">
						  <select class="form-control" id="major" onchange="onchangeTest()">
							  <!-- 专业 -->
						  </select>
					  </div>
					  <div class="col-sm-3">
						  <select class="form-control" id="professor" onchange="onchangeTest()">
							  <!-- 职称 -->
						  </select>
					  </div>
					  <div class="col-sm-3">
						  <input type="text" id="name" class="form-control" placeholder="输入作者姓名查询" onblur="onchangeTest()"/>
					  </div>
				  </div>
			  </div>
		  </div>
		  <div class="row" style="margin: 5px 20px">
			  <div class="col-lg-12">
				  <table id="myTable" class="table-cell table-hover" >
					  <thead>
						  <tr>
							  <th>序号</th>
							  <th>标题</th>
							  <th>期号</th>
							  <th>作者</th>
							  <th>操作</th>
						  </tr>
					  </thead>
				  </table>
			  </div>
		  </div>
		</div>
	</body>
</html>
