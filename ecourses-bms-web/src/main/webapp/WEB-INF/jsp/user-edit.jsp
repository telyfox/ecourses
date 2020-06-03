<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="userEditForm" class="itemForm" method="post">
		
	    <table cellpadding="5">
	    <!-- 
	    <tr>
	            <td>ID:</td>
	            <td><input class="easyui-textbox" type="text" name="id" data-options="required:true" style="width: 280px;"></input></td>
	    </tr>
	     -->
	        <input type="hidden" name="id"/>
	        <tr>
	            <td>用户名:</td>
	            <td><input class="easyui-textbox" type="text" name="username" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>密码:</td>
	            <td><input class="easyui-textbox" type="text" name="password" id="password" data-options="required:false" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>手机号码:</td>
	            <td><input class="easyui-textbox" type="text" validType='phoneNum' name="phone" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	         <tr>
	            <td>E-Mail:</td>
	            <td><input class="easyui-textbox" type="text" validType='email' name="email" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        
	        
	    </table>
	    <!-- 
	    
	    <input type="hidden" name="created"/>
	     -->
	    
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	$.extend($.fn.validatebox.defaults.rules, {     
	    phoneNum: { //验证手机号    
	        validator: function(value, param){  
	         return /^1[3-8]+\d{9}$/.test(value);  
	        },     
	        message: '请输入正确的手机号码。'    
	    },  
	     
	    telNum:{ //既验证手机号，又验证座机号  
	      validator: function(value, param){  
	          return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\d3)|(\d{3}\-))?(1[358]\d{9})$)/.test(value);  
	         },     
	         message: '请输入正确的电话号码。'  
	    }    
	});  
    
	
	function submitForm(){
		if(!$('#userEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		$.post("/user/update",$("#userEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改用户成功!','info',function(){
					$("#userEditWindow").window('close');
					$("#userList").datagrid("reload");
				});
			}
		});
	}
	
</script>
