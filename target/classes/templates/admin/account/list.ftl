<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|用户管理-${title!""}</title>
<#include "/admin/common/header.ftl"/>
<style>
td{
	vertical-align:middle;
}
</style>
</head>

<body>
<div class="lyear-layout-web">
  <div class="lyear-layout-container">
    <!--左侧导航-->
    <aside class="lyear-layout-sidebar">

      <!-- logo -->
      <div id="logo" class="sidebar-header">
        <a href="index.html"><img src="/admin/images/logo-sidebar.png" title="${siteName!""}" alt="${siteName!""}" /></a>
      </div>
      <div class="lyear-layout-sidebar-scroll">
        <#include "/admin/common/left-menu.ftl"/>
      </div>

    </aside>
    <!--End 左侧导航-->

    <#include "/admin/common/header-menu.ftl"/>

    <!--页面主要内容-->
    <main class="lyear-layout-content">

      <div class="container-fluid">

        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-toolbar clearfix">
                <form class="pull-right search-bar" method="get" action="list" role="form">
                  <div class="input-group">
                    <div class="input-group-btn">
                      <button class="btn btn-default dropdown-toggle" id="search-btn" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded="false">
                      用户名 <span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu">
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="title">用户名</a> </li>
                      </ul>
                    </div>
                    <input type="text" class="form-control" value="${username!""}" name="username" placeholder="请输入用户名">
                  	<span class="input-group-btn">
                      <button class="btn btn-primary" type="submit">搜索</button>
                    </span>
                  </div>
                </form>
                <#include "/admin/common/third-menu.ftl"/>
              </div>
              <div class="card-body">

                <div class="table-responsive">
                  <table class="table table-bordered">
                    <thead>
                      <tr>
                        <th>
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" id="check-all"><span></span>
                          </label>
                        </th>
                        <th>头像</th>
                        <th>用户名</th>
                        <th>状态</th>
                        <th>类型</th>
                        <th>性别</th>
                        <th>手机</th>
                        <th>邮箱</th>
                        <th>个人简介</th>
                        <th>添加时间</th>
                      </tr>
                    </thead>
                    <tbody>
                      <#if pageBean.content?size gt 0>
                      <#list pageBean.content as user>
                      <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${user.id}"><span></span>
                          </label>
                        </td>
                        <td style="vertical-align:middle;">
                        	<#if user.headPic??>
                        		<#if user.headPic?length gt 0>
                        		<img src="/photo/view?filename=${user.headPic}" width="60px" height="60px">
                        		<#else>
                        		<img src="/admin/images/default-head.jpg" width="60px" height="60px">
                        		</#if>
                        	</#if>
                        </td>
                        <td style="vertical-align:middle;">${user.username}</td>
                        <td style="vertical-align:middle;">
                        	<#if user.status == 1>
                        	<font class="text-success">正常</font>
                        	<#else>
                        	<font class="text-warning">冻结</font>
                        	</#if>
                        </td>
                        <td style="vertical-align:middle;">
                          <#if user.type == "1">
                            <font class="text-success">管理员</font>
                          <#else>
                            <font class="text-warning">普通用户</font>
                          </#if>
                        </td>
                        <td style="vertical-align:middle;">
                        	<#if user.sex == 1>
                        	<font class="text-success">男</font>
                        	<#elseif user.sex == 2>
                        	<font class="text-info">女</font>
                        	<#else>
                        	<font class="text-warning">未知</font>
                        	</#if>
                        </td>
                        <td style="vertical-align:middle;" >${user.mobile!""}</td>
                        <td style="vertical-align:middle;" >${user.email!""}</td>
                        <td style="vertical-align:middle;" >${user.info!""}</td>
                        <td style="vertical-align:middle;" style="vertical-align:middle;"><font class="text-success">${user.createTime?string("yyyy-MM-dd HH:mm:ss")}</font></td>
                      </tr>
                    </#list>
                    <#else>
                    <tr align="center"><td colspan="9">这里空空如也！</td></tr>
					</#if>
                    </tbody>
                  </table>
                </div>
                <#if pageBean.total gt 0>
                <ul class="pagination ">
                  <#if pageBean.currentPage == 1>
                  <li class="disabled"><span>«</span></li>
                  <#else>
                  <li><a href="list?username=${username!""}&currentPage=1">«</a></li>
                  </#if>
                  <#list pageBean.currentShowPage as showPage>
                  <#if pageBean.currentPage == showPage>
                  <li class="active"><span>${showPage}</span></li>
                  <#else>
                  <li><a href="list?username=${username!""}&currentPage=${showPage}">${showPage}</a></li>
                  </#if>
                  </#list>
                  <#if pageBean.currentPage == pageBean.totalPage>
                  <li class="disabled"><span>»</span></li>
                  <#else>
                  <li><a href="list?username=${username!""}&currentPage=${pageBean.totalPage}">»</a></li>
                  </#if>
                  <li><span>共${pageBean.totalPage}页,${pageBean.total}条数据</span></li>
                </ul>
                </#if>
              </div>
            </div>
          </div>

        </div>

      </div>

    </main>
    <!--End 页面主要内容-->
  </div>
</div>
<#include "/admin/common/footer.ftl"/>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

});
function freezeAccount(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('请选择一条数据进行编辑！');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	edit(url,id,0);
}
function unFreezeAccount(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('请选择一条数据进行编辑！');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	edit(url,id,1);
}

//调用编辑方法
function edit(url,id,status){
	$.ajax({
		url:url,
		type:'POST',
		data:{id:id,status:status},
		dataType:'json',
		success:function(data){
			if(data.code == 0){
				showSuccessMsg('操作成功!',function(){
					window.location.reload();
				})
			}else{
				showErrorMsg(data.msg);
			}
		},
		error:function(data){
			alert('网络错误!');
		}
	});
}
</script>
</body>
</html>
