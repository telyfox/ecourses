<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="adminList" title="管理员列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/admin/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">管理员ID</th>
            <th data-options="field:'username',width:200">管理员用户名</th>
            <th data-options="field:'phone',width:100">手机号码</th>
            <th data-options="field:'email',width:100">E-Mail</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="adminAddWindow" class="easyui-window" title="新增管理员信息" data-options="modal:true,closed:true,iconCls:'icon-add',href:'/admin-add'" style="width:80%;height:80%;padding:10px;">
</div>
<div id="adminEditWindow" class="easyui-window" title="编辑管理员信息" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/admin-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#adminList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$("#adminAddWindow").window({
        	}).window("open");
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个管理员才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个管理员!');
        		return ;
        	}
        	
        	$("#adminEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#adminList").datagrid("getSelections")[0];        			
        			$("#adminEditForm").form("load",data);
        			$("#password").textbox('setValue');
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中管理员!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的管理员吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/admin/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除管理员成功!',undefined,function(){
            					$("#adminList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>