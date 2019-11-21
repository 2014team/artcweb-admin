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
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>执行步骤
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="step" name="step" value="${entity.step }" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>图片
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="imageUrl" name="imageUrl" value="${entity.imageUrl }" lay-verify="required"
                  autocomplete="off" class="layui-input">
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
        crup_save(form,'save','/admin/center/package/save.do');
          
        });
    </script>
  </body>

</html>