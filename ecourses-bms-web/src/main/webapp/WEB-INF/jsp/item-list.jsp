<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="课程列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/item/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">课程ID</th>
            <th data-options="field:'title',width:200">课程标题</th>
            <th data-options="field:'cid',width:100">叶子类目</th>
            <th data-options="field:'sellPoint',width:100">卖点</th>
            <th data-options="field:'price',width:70,align:'right',formatter:E3.formatPrice">价格</th>
            <th data-options="field:'num',width:70,align:'right'">报名限额</th>
            <th data-options="field:'barcode',width:100">条形码</th>
            <th data-options="field:'status',width:60,align:'center',formatter:E3.formatItemStatus">状态</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑课程" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/item-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#itemList");
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
        	$(".tree-title:contains('新增课程')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个课程才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个课程!');
        		return ;
        	}
        	
        	$("#itemEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#itemList").datagrid("getSelections")[0];
        			data.priceView = E3.formatPrice(data.price);
        			$("#itemeEditForm").form("load",data);
	
        			// 加载课程描述
        			/*
        			$.getJSON('/item/query/item/desc/'+data.id,function(result){
        				
        				if(result.status == 200){
        					//UM.getEditor('itemeEditDescEditor').setContent(_data.data.itemDesc, false);
        					itemEditEditor.html(result.data.itemDesc);
        					alert("你好，我是一个警告框！");

        					
        				}
        			});
        			*/
        			$.getJSON('/item/query/item/desc/'+data.id,function(result){
        					itemEditEditor.html(result.itemDesc);
        			});  
        			
        			//加载课程规格
        			$.getJSON('/item/param/query/itemcatid/'+data.cid,function(result){
        				//if(result && result.paramData){
        					$("#itemeEditForm .params").show();
        					$("#itemeEditForm [name=itemParams]").val(result.paramData);
        					$("#itemeEditForm [name=itemParamId]").val(result.id);
        					
        					//回显课程规格
        					 var paramData = JSON.parse(result.paramData);
        					
        					 var html = "<ul>";
        					 for(var i in paramData){
        						 var pd = paramData[i];
        						 html+="<li><table>";
        						 html+="<tr><td colspan=\"2\" class=\"group\">"+pd.group+"</td></tr>";
        						 
        						 for(var j in pd.params){
        							 var ps = pd.params[j];
        							 html+="<tr><td class=\"param\"><span>"+ps.k+"</span>: </td><td><input autocomplete=\"off\" type=\"text\" value='"+ps.v+"'/></td></tr>";
        						 }
        						 
        						 html+="</li></table>";
        					 }
        					 html+= "</ul>";
        					 $("#itemeEditForm .params td").eq(1).html(html);
        				//}
        			});
        			
        			E3.init({
        				"pics" : data.image,
        				"cid" : data.cid,
        				fun:function(node){
        					E3.changeItemParam(node, "itemeEditForm");
        				}
        			});
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中课程!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的课程吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/item/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除课程成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },'-',{
        text:'下架',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中课程!');
        		return ;
        	}
        	$.messager.confirm('确认','确定下架ID为 '+ids+' 的课程吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/item/instock",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','下架课程成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'上架',
        iconCls:'icon-ok',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中课程!');
        		return ;
        	}
        	$.messager.confirm('确认','确定上架ID为 '+ids+' 的课程吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/item/reshelf",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','上架课程成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>