<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="orderBmsList" title="订单信息列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/bms/order/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'orderId',width:100">订单号</th>
        	<th data-options="field:'itemId',width:100">课程ID</th>
        	<th data-options="field:'num',width:80">报名人数</th>
        	<th data-options="field:'title',width:150">课程名称</th>
        	<th data-options="field:'payment',width:100">实付金额</th>
        	<th data-options="field:'paymentType',width:100,align:'center',formatter:E3.formatPaymentType">支付类型</th>
        	<th data-options="field:'postFee',width:100">邮费</th>
        	<th data-options="field:'status',width:100,align:'center',formatter:E3.formatPaymentStatus">状态</th>
        	<th data-options="field:'createTime',width:150,align:'center',formatter:E3.formatDateTime">订单创建时间</th>
        	<th data-options="field:'updateTime',width:150,align:'center',formatter:E3.formatDateTime">订单更新时间</th>
        	<th data-options="field:'paymentTime',width:150,align:'center',formatter:E3.formatDateTime">付款时间</th>
        	<th data-options="field:'consignTime',width:150,align:'center',formatter:E3.formatDateTime">发送课程时间</th>
        	<th data-options="field:'endTime',width:150,align:'center',formatter:E3.formatDateTime">交易完成时间</th>
        	<th data-options="field:'closeTime',width:150,align:'center',formatter:E3.formatDateTime">交易关闭时间</th>
        	<th data-options="field:'shippingName',width:150">物流名称/云盘服务商</th>
        	<th data-options="field:'shippingCode',width:150">物流单号/云盘地址</th>
        	<th data-options="field:'userId',width:100">用户ID</th>
        	<th data-options="field:'buyerMessage',width:100">购课用户留言</th>
        	<th data-options="field:'buyerNick',width:100">购课用户昵称</th>
        	<th data-options="field:'buyerRate',width:100,align:'center',formatter:E3.formatBuyerRate">是否评价</th>

        </tr>
    </thead>
</table>
<div id="orderDeliveryWindow" class="easyui-window" title="发送课程" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/order-delivery'" style="width:80%;height:80%;padding:10px;">
</div>

<script>
    function getSelectionsIds(){
    	var orderBmsList = $("#orderBmsList");
    	var sels = orderBmsList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].orderId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
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
                	$.post("/bms/order/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除成功!',undefined,function(){
            					$("#orderBmsList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'发送课程',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一项才能发送!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一项!');
        		return ;
        	}
        	
        	$("#orderDeliveryWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#orderBmsList").datagrid("getSelections")[0];        			
        			$("#orderDeliveryForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'停止发送',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中!');
        		return ;
        	}
        	$.messager.confirm('确认','确定停止发送订单号为 '+ids+' 的订单吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/bms/order/stop/delivery",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','成功!',undefined,function(){
            					$("#orderBmsList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'完成交易',
        iconCls:'icon-ok',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中!');
        		return ;
        	}
        	$.messager.confirm('确认','确定订单号为 '+ids+' 的交易已完成？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/bms/order/complete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','成功!',undefined,function(){
            					$("#orderBmsList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'关闭交易',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中!');
        		return ;
        	}
        	$.messager.confirm('确认','确定关闭订单号为 '+ids+' 的订单吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/bms/order/close",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','成功!',undefined,function(){
            					$("#orderBmsList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>