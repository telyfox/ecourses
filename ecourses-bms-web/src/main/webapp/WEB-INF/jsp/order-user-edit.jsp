<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="orderUserEditForm" class="itemForm" method="post">
		<input type="hidden" name="orderId"/>
	    <table cellpadding="5">
	        <tr>
	            <td>收货人全名:</td>
	            <td><input class="easyui-textbox" type="text" name="receiverName" data-options="required:true" style="width: 100px;"></input></td>
	        </tr>
	        <tr>
	            <td>固定电话:</td>
	            <td><input class="easyui-textbox" type="text" validType='telNum' name="receiverPhone" data-options="required:false" style="width: 280px;"></input></td>
	        </tr>
	         <tr>
	            <td>移动电话:</td>
	            <td><input class="easyui-textbox" type="text" validType='phoneNum' name="receiverMobile" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>省份:</td>
	            <td><input class="easyui-textbox" type="text" name="receiverState" data-options="required:true" style="width: 100px;"></input></td>
	        </tr>
	        <tr>
	            <td>城市:</td>
	            <td><input class="easyui-textbox" type="text" name="receiverCity" data-options="required:true" style="width: 100px;"></input></td>
	        </tr>
	        <tr>
	            <td>区/县:</td>
	            <td><input class="easyui-textbox" type="text" name="receiverDistrict" data-options="required:true" style="width: 100px;"></input></td>
	        </tr>
	        <tr>
	            <td>收货地址:</td>
	            <td><input class="easyui-textbox" type="text" name="receiverAddress" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>邮政编码:</td>
	            <td><input class="easyui-textbox" type="text" name="receiverZip" data-options="required:false" style="width: 150px;"></input></td>
	        </tr>
	    </table>
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
		if(!$('#orderUserEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		$.post("/bms/order/user/update",$("#orderUserEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改成功!','info',function(){
					$("#orderUserEditWindow").window('close');
					$("#orderUserList").datagrid("reload");
				});
			}
		});
	}
</script>
