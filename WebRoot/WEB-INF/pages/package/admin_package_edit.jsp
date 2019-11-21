<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="/WEB-INF/pages/common/head_layui.jsp" %>
  	<%@include file="/WEB-INF/pages/common/jsp_jstl.jsp" %>
  </head>
   <body>
    <div class="x-body">
        <form class="layui-form">
          <input type="hidden" id="id" name="id"  value="${entity.id }" /> 
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                  <span class="x-red">*</span>分类ID
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="packageId" name="packageId"  value="${entity.packageId }" lay-verify="required|number"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
           		       数字
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>套餐名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="packageName" name="packageName" value="${entity.packageName }" lay-verify="required|packageName"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  1到50个字符
              </div>
          </div>
          
       <%--    <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>执行步骤
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="step" name="step" value="${entity.step }" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div> --%>
          
          
          <div class="layui-form-item layui-form-text">
		    <label class="layui-form-label"> <span class="x-red">*</span>执行步骤</label>
		    <div class="layui-input-block">
		      <textarea name="desc" placeholder="请输入内容" class="layui-textarea" lay-verify="required"></textarea>
		    </div>
		  </div>
          
          
          
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>图片
              </label>
              <div class="layui-input-inline">
                <%--   <input type="text" id="imageUrl" name="imageUrl" value="${entity.imageUrl }" lay-verify="required"
                  autocomplete="off" class="layui-input"> --%>
                  <input type="file" id="fileId" >
          </div>
             
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="save" lay-submit="">
                  保存
              </button>
          </div>
      </form>
    </div>
    <script>
    
 
        layui.use(['form','layer'], function(){
           $ = layui.jquery;
          var form = layui.form
          ,layer = layui.layer;
          //自定义验证规则
          form.verify({
            packageName: function(value){
              if(value.length > 50){
                return '请输入1到50个字符!';
              }
            }
          });
          
        //保存
        
        
        form.on('submit(save)', function(obj) {
        	var formData = new FormData($("#uploadForm")[0])  //创建一个forData 
   			 formData.append('img', $('#pic_img')[0].files[0]) //把file添加进去  name命名为img
        
        
    			$.ajax({
    				url : '/admin/center/package/save.do',
    				type : "POST",
    				data : {
    					id:$("#id").val(),
    					rightRule:$("#rightRule").val(),
    					rightName:$("#rightName").val(),
    					rightCategoryId:$("#rightCategoryId").val()
    				},
    				dataType : "json",
    				success : function(data) {
    						if (data.code == 200) { //这个是从后台取回来的状态值
								layer.msg(data.msg, {icon : 6,time : 500
								}, function() {
									// 获得frame索引
									var index = parent.layer.getFrameIndex(window.name);
									//关闭当前frame
									parent.layer.close(index);
									//刷新列表
									window.parent.location.reload();
								});
								
							} else {
								layer.msg(data.msg, {
									icon : 2,
									time : 500
								});
							}
    					},
    				error : function(e) {
    					console.log(e);
    					layer.msg("系统异常，稍后再试!", {
    						icon : 2,
    						time : 1000
    					});
    				}
    			});
    
    			return false;
    		});
        
        
        
        
        
        
        
        
        
        
        
        
        //crup_save(form,'save','/admin/center/package/save.do');
          
        });
    </script>
  </body>

</html>