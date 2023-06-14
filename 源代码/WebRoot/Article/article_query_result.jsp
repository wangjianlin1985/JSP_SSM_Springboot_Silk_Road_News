<%@ page language="java"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/article.css" /> 

<div id="article_manage"></div>
<div id="article_manage_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="article_manage_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="article_manage_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="article_manage_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="article_manage_tool.redo();">取消选择</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="article_manage_tool.exportExcel();">导出到excel</a>
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
		<form id="articleQueryForm" method="post">
			文章标题：<input type="text" class="textbox" id="title" name="title" style="width:110px" />
			文章类别：<input class="textbox" type="text" id="articleClassObj_classId_query" name="articleClassObj.classId" style="width: auto"/>
			添加时间：<input type="text" class="textbox" id="addTime" name="addTime" style="width:110px" />
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="article_manage_tool.search();">查询</a>
		</form>	
	</div>
</div>

<div id="articleEditDiv">
	<form id="articleEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">文章id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="article_articleId_edit" name="article.articleId" style="width:200px" />
			</span>
		</div>
		<div>
			<span class="label">文章标题:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="article_title_edit" name="article.title" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">文章类别:</span>
			<span class="inputControl">
				<input class="textbox"  id="article_articleClassObj_classId_edit" name="article.articleClassObj.classId" style="width: auto"/>
			</span>
		</div>
		<div>
			<span class="label">文章主图:</span>
			<span class="inputControl">
				<img id="article_articlePhotoImg" width="200px" border="0px"/><br/>
    			<input type="hidden" id="article_articlePhoto" name="article.articlePhoto"/>
				<input id="articlePhotoFile" name="articlePhotoFile" type="file" size="50" />
			</span>
		</div>
		<div>
			<span class="label">文章内容:</span>
			<span class="inputControl">
				<textarea id="article_content_edit" name="article.content" rows="8" cols="60"></textarea>

			</span>

		</div>
		<div>
			<span class="label">作者:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="article_author_edit" name="article.author" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">来源:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="article_comeFrom_edit" name="article.comeFrom" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">添加时间:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="article_addTime_edit" name="article.addTime" style="width:200px" />

			</span>

		</div>
	</form>
</div>
<script type="text/javascript" src="Article/js/article_manage.js"></script> 
