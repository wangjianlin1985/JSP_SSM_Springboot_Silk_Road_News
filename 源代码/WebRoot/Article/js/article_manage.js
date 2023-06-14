var article_manage_tool = null; 
$(function () { 
	initArticleManageTool(); //建立Article管理对象
	article_manage_tool.init(); //如果需要通过下拉框查询，首先初始化下拉框的值
	$("#article_manage").datagrid({
		url : 'Article/list',
		fit : true,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		border : false,
		pagination : true,
		pageSize : 5,
		pageList : [5, 10, 15, 20, 25],
		pageNumber : 1,
		sortName : "articleId",
		sortOrder : "desc",
		toolbar : "#article_manage_tool",
		columns : [[
			{
				field : "title",
				title : "文章标题",
				width : 140,
			},
			{
				field : "articleClassObj",
				title : "文章类别",
				width : 140,
			},
			{
				field : "articlePhoto",
				title : "文章主图",
				width : "70px",
				height: "65px",
				formatter: function(val,row) {
					return "<img src='" + val + "' width='65px' height='55px' />";
				}
 			},
			{
				field : "author",
				title : "作者",
				width : 140,
			},
			{
				field : "comeFrom",
				title : "来源",
				width : 140,
			},
			{
				field : "addTime",
				title : "添加时间",
				width : 140,
			},
		]],
	});

	$("#articleEditDiv").dialog({
		title : "修改管理",
		top: "50px",
		width : 700,
		height : 515,
		modal : true,
		closed : true,
		iconCls : "icon-edit-new",
		buttons : [{
			text : "提交",
			iconCls : "icon-edit-new",
			handler : function () {
				if ($("#articleEditForm").form("validate")) {
					//验证表单 
					if(!$("#articleEditForm").form("validate")) {
						$.messager.alert("错误提示","你输入的信息还有错误！","warning");
					} else {
						$("#articleEditForm").form({
						    url:"Article/" + $("#article_articleId_edit").val() + "/update",
						    onSubmit: function(){
								if($("#articleEditForm").form("validate"))  {
				                	$.messager.progress({
										text : "正在提交数据中...",
									});
				                	return true;
				                } else { 
				                    return false; 
				                }
						    },
						    success:function(data){
						    	$.messager.progress("close");
						    	console.log(data);
			                	var obj = jQuery.parseJSON(data);
			                    if(obj.success){
			                        $.messager.alert("消息","信息修改成功！");
			                        $("#articleEditDiv").dialog("close");
			                        article_manage_tool.reload();
			                    }else{
			                        $.messager.alert("消息",obj.message);
			                    } 
						    }
						});
						//提交表单
						$("#articleEditForm").submit();
					}
				}
			},
		},{
			text : "取消",
			iconCls : "icon-redo",
			handler : function () {
				$("#articleEditDiv").dialog("close");
				$("#articleEditForm").form("reset"); 
			},
		}],
	});
});

function initArticleManageTool() {
	article_manage_tool = {
		init: function() {
			$.ajax({
				url : "ArticleClass/listAll",
				type : "post",
				success : function (data, response, status) {
					$("#articleClassObj_classId_query").combobox({ 
					    valueField:"classId",
					    textField:"className",
					    panelHeight: "200px",
				        editable: false, //不允许手动输入 
					});
					data.splice(0,0,{classId:0,className:"不限制"});
					$("#articleClassObj_classId_query").combobox("loadData",data); 
				}
			});
		},
		reload : function () {
			$("#article_manage").datagrid("reload");
		},
		redo : function () {
			$("#article_manage").datagrid("unselectAll");
		},
		search: function() {
			var queryParams = $("#article_manage").datagrid("options").queryParams;
			queryParams["title"] = $("#title").val();
			queryParams["articleClassObj.classId"] = $("#articleClassObj_classId_query").combobox("getValue");
			queryParams["addTime"] = $("#addTime").val();
			$("#article_manage").datagrid("options").queryParams=queryParams; 
			$("#article_manage").datagrid("load");
		},
		exportExcel: function() {
			$("#articleQueryForm").form({
			    url:"Article/OutToExcel",
			});
			//提交表单
			$("#articleQueryForm").submit();
		},
		remove : function () {
			var rows = $("#article_manage").datagrid("getSelections");
			if (rows.length > 0) {
				$.messager.confirm("确定操作", "您正在要删除所选的记录吗？", function (flag) {
					if (flag) {
						var articleIds = [];
						for (var i = 0; i < rows.length; i ++) {
							articleIds.push(rows[i].articleId);
						}
						$.ajax({
							type : "POST",
							url : "Article/deletes",
							data : {
								articleIds : articleIds.join(","),
							},
							beforeSend : function () {
								$("#article_manage").datagrid("loading");
							},
							success : function (data) {
								if (data.success) {
									$("#article_manage").datagrid("loaded");
									$("#article_manage").datagrid("load");
									$("#article_manage").datagrid("unselectAll");
									$.messager.show({
										title : "提示",
										msg : data.message
									});
								} else {
									$("#article_manage").datagrid("loaded");
									$("#article_manage").datagrid("load");
									$("#article_manage").datagrid("unselectAll");
									$.messager.alert("消息",data.message);
								}
							},
						});
					}
				});
			} else {
				$.messager.alert("提示", "请选择要删除的记录！", "info");
			}
		},
		edit : function () {
			var rows = $("#article_manage").datagrid("getSelections");
			if (rows.length > 1) {
				$.messager.alert("警告操作！", "编辑记录只能选定一条数据！", "warning");
			} else if (rows.length == 1) {
				$.ajax({
					url : "Article/" + rows[0].articleId +  "/update",
					type : "get",
					data : {
						//articleId : rows[0].articleId,
					},
					beforeSend : function () {
						$.messager.progress({
							text : "正在获取中...",
						});
					},
					success : function (article, response, status) {
						$.messager.progress("close");
						if (article) { 
							$("#articleEditDiv").dialog("open");
							$("#article_articleId_edit").val(article.articleId);
							$("#article_articleId_edit").validatebox({
								required : true,
								missingMessage : "请输入文章id",
								editable: false
							});
							$("#article_title_edit").val(article.title);
							$("#article_title_edit").validatebox({
								required : true,
								missingMessage : "请输入文章标题",
							});
							$("#article_articleClassObj_classId_edit").combobox({
								url:"ArticleClass/listAll",
							    valueField:"classId",
							    textField:"className",
							    panelHeight: "auto",
						        editable: false, //不允许手动输入 
						        onLoadSuccess: function () { //数据加载完毕事件
									$("#article_articleClassObj_classId_edit").combobox("select", article.articleClassObjPri);
									//var data = $("#article_articleClassObj_classId_edit").combobox("getData"); 
						            //if (data.length > 0) {
						                //$("#article_articleClassObj_classId_edit").combobox("select", data[0].classId);
						            //}
								}
							});
							$("#article_articlePhoto").val(article.articlePhoto);
							$("#article_articlePhotoImg").attr("src", article.articlePhoto);
							$("#article_content_edit").val(article.content);
							$("#article_content_edit").validatebox({
								required : true,
								missingMessage : "请输入文章内容",
							});
							$("#article_author_edit").val(article.author);
							$("#article_comeFrom_edit").val(article.comeFrom);
							$("#article_addTime_edit").val(article.addTime);
						} else {
							$.messager.alert("获取失败！", "未知错误导致失败，请重试！", "warning");
						}
					}
				});
			} else if (rows.length == 0) {
				$.messager.alert("警告操作！", "编辑记录至少选定一条数据！", "warning");
			}
		},
	};
}
