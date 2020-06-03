<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="userList" title="用户列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/user/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">用户ID</th>
            <th data-options="field:'username',width:200">用户名</th>
            <th data-options="field:'phone',width:100">手机号码</th>
            <th data-options="field:'email',width:100">E-Mail</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="userAddWindow" class="easyui-window" title="新增用户信息" data-options="modal:true,closed:true,iconCls:'icon-add',href:'/user-add'" style="width:80%;height:80%;padding:10px;">
</div>
<div id="userEditWindow" class="easyui-window" title="编辑用户信息" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/user-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var userList = $("#userList");
    	var sels = userList.datagrid("getSelections");
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
        	$("#userAddWindow").window({
        	}).window("open");
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个用户才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个用户!');
        		return ;
        	}
        	
        	$("#userEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#userList").datagrid("getSelections")[0];
        			
        			$("#userEditForm").form("load",data);
        			$("#password").textbox('setValue');
        			// 加载用户描述
        			/*
        			$.getJSON('/user/query/'+data.id,function(_data){
        				if(_data.status == 200){
        					//UM.getEditor('itemeEditDescEditor').setContent(_data.data.itemDesc, false);
        					itemEditEditor.html(_data.data.itemDesc);
        				}
        			});
        			*/
        			
        			
        			//加载课程规格
        			/*
        			$.getJSON('/user/query/'+data.id,function(_data){
        				if(_data && _data.status == 200 && _data.data && _data.data.paramData){
        					$("#itemeEditForm .params").show();
        					$("#itemeEditForm [name=itemParams]").val(_data.data.paramData);
        					$("#itemeEditForm [name=itemParamId]").val(_data.data.id);
        					
        				}
        			});
        			*/
        			
      
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中用户!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的用户吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/user/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除用户成功!',undefined,function(){
            					$("#userList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>