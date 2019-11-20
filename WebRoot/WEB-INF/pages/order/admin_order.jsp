<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@include file="/WEB-INF/pages/common/head_layui.jsp" %>
  	<%@include file="/WEB-INF/pages/common/jsp_jstl.jsp" %>
  </head>
 <style>
 .x-cate tr{display:none;}
 .x-cate tr[fid='0']{display:block;}
 </style>
  <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
      <a class="layui-btn layui-btn-primary layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:38px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so layui-form-pane">
          <input class="layui-input" placeholder="分类名" name="cate_name">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon"></i>增加</button>
        </form>
      </div>
      <blockquote class="layui-elem-quote">每个tr 上有两个属性 cate-id='1' 当前分类id fid='0' 父级id ,顶级分类为 0，有子分类的前面加收缩图标<i class="layui-icon x-show" status='true'>&#xe623;</i></blockquote>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
      </xblock>
      <div class="x-cate">
      <table class="layui-table layui-form " id="category_list">
      </table>
      </div>
      
    </div>
    <style type="text/css">
      
    </style>
    <script type="text/html" id="titleTpl">
  
	
  {{#  if(d.categoryId == 11){ }}
	<i class="layui-icon x-show" status='true'>&#xe623;</i>{{d.categoryName}}
  {{#  } else { }}
    &nbsp;&nbsp;&nbsp;&nbsp;├{{d.categoryName}}
  {{#  } }}


</script>
    <script>
      layui.use(['form','table'], function(){
        var form = layui.form
        ,table = layui.table;
        
        
          table.render({
          
          	
          	
          	
          	
          
			elem : '#category_list',
			url : '/admin/center/system/category/list.do',
			toolbar: '#toolbar',
		    defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
		      title: '提示'
		      ,layEvent: 'LAYTABLE_TIPS'
		      ,icon: 'layui-icon-tips'
		    }],
			page : { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
				layout : [ 'limit', 'count', 'prev', 'page', 'next', 'skip' ], //自定义分页布局 //,curr: 5 //设定初始在第 5 页
				limit : 10,//每页显示的条数
				groups : 5, //步长
				first : '首页', //不显示首页
				last : '尾页', //不显示尾页
				prev :'上一页',
				next:'下一页'

			},
			rowStyle: function (row) {//index第几行，row当前行的值
			//debugger;
			console.log(row)
                    if (row.categoryId == 11){
                        return ' cate-id="1" fid="0" ';
                    }else{
                     return ' cate-id="2" fid="1" ';
                    }
                },
			cols : [ [
				 {checkbox: true, fixed: true},
				{
					field : 'id',
					title : 'ID',
					sort : true
				}
				, {
					field : 'categoryId',
					title : '分类ID',
					sort : true,
					templet: '#titleTpl'
				}
				, {
					field : 'categoryName',
					title : '分类名'
				}
				, {
					align:'center', toolbar: '#categoryBar',
					title : '操作'
				}

			] ]
			  ,id: 'catogoryReload'
		});
		
        
        
        
      });


    // 栏目多级显示效果
    $('.x-cate').on("click",".x-show",function () {
        if($(this).attr('status')=='true'){
            $(this).html('&#xe625;'); 
            $(this).attr('status','false');
            cateId = $(this).parents('tr').attr('cate-id');
            $("tbody tr[fid="+cateId+"]").css("display","block");
       }else{
            cateIds = [];
            $(this).html('&#xe623;');
            $(this).attr('status','true');
            cateId = $(this).parents('tr').attr('cate-id');
            getCateId(cateId);
            for (var i in cateIds) {
                $("tbody tr[cate-id="+cateIds[i]+"]").hide().find('.x-show').html('&#xe623;').attr('status','true');
            }
       }
    })
      

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }



      function delAll (argument) {

        var data = tableCheck.getData();
  
        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }
    </script>
  </body>

</html>