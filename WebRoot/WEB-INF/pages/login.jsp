<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   	<meta charset="UTF-8">
	<%@include file="/WEB-INF/pages/common/head-layui.jsp" %>
  </head>

 <body class="login-bg">
 <p class="center"></p>
    <div class="login layui-anim layui-anim-up">
        <div class="message">L-admin2.0-管理登录</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" >
            <input name="userName" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" value="admin">
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" style="background:#0dc316;" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>

    <script>
        $(function  () {
            layui.use('form', function(){
              var form = layui.form;
              // layer.msg('玩命卖萌中', function(){
              //   //关闭后的操作
              //   });
              //监听提交
              form.on('submit(login)', function(data) {
    					// alert(888)
    					// layer.msg(JSON.stringify(data.field),function(){
    					//   location.href='index.html'
    					//});
    					$.ajax({
    						url : "/admin/login/submit.do",
    						type:"POST",
    						async:false,
    						data:data.field,
    						dataType:"json",
    						success : function(result) {
    							if(result.code == 200){
    								window.location.href="/admin/center/index.do";
    							}else{
    								layer.msg(result.msg);
    							}
    						},
    						error:function(e){
    							console.info(e);
    						}
    					});
    					return false;
              });
            });
        })

        
    </script>

    
    <!-- 底部结束 -->
</body>
</html>
