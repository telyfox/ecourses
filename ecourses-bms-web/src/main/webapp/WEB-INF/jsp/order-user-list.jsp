<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="orderUserList" title="订单用户信息列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/bms/order/user/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'orderId',width:100">订单号</th>
            <th data-options="field:'receiverName',width:100">收货人全名</th>
            <th data-options="field:'receiverPhone',width:100">固定电话</th>
            <th data-options="field:'receiverMobile',width:100">移动电话</th>
            <th data-options="field:'receiverState',width:100">省份</th>
            <th data-options="field:'receiverCity',width:100">城市</th>
            <th data-options="field:'receiverDistrict',width:100">区/县</th>
            <th data-options="field:'receiverAddress',width:200">收货地址</th>
            <th data-options="field:'receiverZip',width:100">邮政编码</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="orderUserEditWindow" class="easyui-window" title="编辑订单用户信息" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/order-user-edit'" style="width:80%;height:80%;padding:10px;">
</div>

<script>
    function getSelectionsIds(){
    	var orderUserList = $("#orderUserList");
    	var sels = orderUserList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].orderId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一项信息才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一项!');
        		return ;
        	}
        	
        	$("#orderUserEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#orderUserList").datagrid("getSelections")[0];
        			$("#orderUserEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除订单号为 '+ids+' 的信息吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/bms/order/user/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除成功!',undefined,function(){
            					$("#orderUserList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>