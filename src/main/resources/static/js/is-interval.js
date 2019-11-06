//路口id
var isId;
$(function () {
    //解析显示时段信息
    isId = $("#sd_is_id_th").val();
    //alert(isId);
    init_interval_page(isId);
});
//判断字符是否为空
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}
//校验HH:mm
function formateCheck(str) {

    var re = /^(?:[01]\d|2[0-3])(?::[0-5]\d)$/;
    var result = re.test(str);
    return result;

}
function init_interval_page(id){
    $.ajax({
        url: "/interval/"+id,
        type: "GET",
        success: function (result) {
            //alert(result.msg);
            //解析并显示路口数据
            build_is_interval_table(result);

        }
    });
}
//解析显示时段信息
function build_is_interval_table(result) {
    //清空table表格
    $("#is_interval_table tbody").empty();
    var intervals = result.data;
    $.each(intervals, function (index, item) {
        var signTd = $("<td style='width:5%;'><span class='glyphicon glyphicon-time' aria-hidden='true'></span></td>");
        var sdNameTd = $("<td style='width:5%;'></td>").append(
            $("<span style='color:#2aabd2'></span>").html("时间段"+index)
        );
        var sdTd = $("<td style='width:18%;'></td>").html(item.sdStart+" - "+item.sdEnd);
        var sdFaTd = $("<td style='width:15%;'></td>").html(item.faName);
        var sdFaMethodTd = $("<td style='width:15%;'></td>").html(item.faMethod);
        var sdZxw = $("<td style='width:24%;'></td>").html(item.faZxw);
        //为编辑按钮添加自定义属性，id值
        // var editBtn = $("<a onclick='update_interval(this);'></a>").append(
        //     $("<span></span>").addClass("glyphicon glyphicon-pencil").attr("aria-hidden","true").append("编辑")
        // );
        //editBtn.attr("edit_id",item.id);
        var delBtn = $("<a onclick='delete_interval(this);'></a>").append(
            $("<span></span>").addClass("glyphicon glyphicon-scissors").attr("aria-hidden","true").append("删除")
        );
        delBtn.attr("delete_id",item.id);
        // var btnTd = $("<td style='width:18%;'></td>").append(editBtn).append(" ").append(
        //     delBtn);
        var btnTd = $("<td style='width:18%;'></td>").append(delBtn);
        //append方法执行完后返回原来的元素
        $("<tr class='sd-tr'></tr>").append(signTd).append(sdNameTd).append(sdTd).append(
            sdFaTd).append(sdFaMethodTd).append(sdZxw).append(btnTd).appendTo("#is_interval_table tbody");
    });
}
//将方案信息显示在下拉列表
function getProjtects(e){
    //清空之前下拉列表中数据
    $(e).empty();
    $.ajax({
        url:"/project/all",
        type:"GET",
        success:function(result){
            if(result.status == 200 ){
                //显示方案信息在下拉列表中
                $.each(result.data,function(index,item){
                    var optionEle = $("<option></option>").append(item.faName).attr("value",item.id);
                    optionEle.appendTo(e);
                });
                //初始化方案信息
                //alert("初始方案id"+result.data[0].id);
                $("#is_sd_fa_select").val([result.data[0].id]);
                //显示初始化信息
                is_sd_fa_info("#is_sd_fa_select");
            }else{
                alert(result.msg);
            }
        }
    });
}
//获取方案信息详情
function is_sd_fa_info(e){
    var fa_id = $(e).val();
    //alert(fa_id);
    $.ajax({
        url:"/project/"+fa_id,
        type:"GET",
        success:function(result){
            if(result.status == 200 ){
                //显示详细方案信息在指定列val()
                //alert(result.data.faMethod+"**"+result.data.faZxw);
                $(e).parents("tr").find("td:eq(4)").html(result.data.faMethod);
                $(e).parents("tr").find("td:eq(5)").html(result.data.faZxw);

            }else{
                alert(result.msg);
            }
        }
    });
}
//添加时段
function add_is_interval(e){
    //1、模态框中填写的表单数据提交给服务器进行保存
    //先对提交给服务器的数据进行校验
    if (!validate_add_is_interval(e)) {
        return false;
    }
    //alert($("#sd_is_id").val()+"**"+$("#is_sd_start").val()+"**"+$("#is_sd_end").val()+"**"+$("#is_sd_fa_select").val());
    //2、发送ajax请求保存时段,采用发送json数据的方式
    $.ajax({
        type: "POST",
        url: "/interval/save",
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        data: JSON.stringify({
            "sdIsId": $("#sd_is_id").val(),
            "sdStart": $("#is_sd_start").val(),
            "sdEnd": $("#is_sd_end").val(),
            "sdFaId":$("#is_sd_fa_select").val()
        }),
        success: function (result) {
            if (result.status == 200) {
                //alert("添加时段成功");
                init_interval_page(isId);
            }else {
                 alert(result.msg);
            }
        }

    });
}
//删除时段
function delete_interval(e){
    //弹出确认删除对话框
    var sdName = $(e).parents("tr").find("td:eq(2)").text();
    if(confirm("确认删除【"+sdName+"】吗？")){
        $.ajax({
            url:"/interval/"+$(e).attr("delete_id"),
            type:"DELETE",
            success:function(result){
                if(result.status == 200){
                    //alert(result.msg);
                    init_interval_page(isId);
                }else{
                    alert(result.msg);
                }
            }
        });
    }
}
//取消时段添加
function cancel_is_interval(){
    init_interval_page(isId);
}
//校验时间段表单数据
function validate_add_is_interval(e){
    //校验信息完整
    var start = $("#is_sd_start").val();
    var end = $("#is_sd_end").val();
    //alert(start+"***"+end);
    if(isEmpty(start) || isEmpty(end)){
        alert("请填写完整的时段信息");
        return false;
    }
    if(!formateCheck(start) || !formateCheck(end)){
        alert("请按照格式填写时段信息(HH:mm),英文格式");
        return false;
    }
    if(start>end || start == end){
        alert("请填写合理的时段信息");
       return false;
    }
    return true;
}
//打开时段添加模态框
function add_interval_modal(){
    var formLength = $(".add-is-sd-tr").length;
    if(formLength != 0){
        //alert("请填写信息");
        return false;
    }
    var sdNum = $(".sd-tr").length;
    //alert(sdNum);
    if(sdNum >= 24){
        alert("最多只能有24个时段");
        return false;
    }
    //alert("路口id:"+$("#sd_is_id_th").val());
    var sdIsId = $("<input type='hidden' id='sd_is_id'>").val($("#sd_is_id_th").val());
    //var addSdForm = $("<form></form>").addClass("form-horizontal");
    var addSdSignTd = $("<td style='width:5%;'></td>").append($("<span >&raquo;</span>"));
    var addSdNameTd = $("<td style='width:5%;'></td>").append(
                        $("<span style='color:#2aabd2'></span>").append("时间段"+sdNum)
                    );
    //时间段输出输入框
    var addSdStartSpan = $("<span></span>").addClass("input-group-addon").append("start");
    var addSdEndSpan = $("<span></span>").addClass("input-group-addon").append("end");
    var addSdStartInput = $("<input type='text' id='is_sd_start' placeholder='HH:mm' aria-describedby='sizing-addon2'>").addClass("form-control");
    var addSdEndInput = $("<input type='text' id='is_sd_end' placeholder='HH:mm' aria-describedby='sizing-addon2'>").addClass("form-control");
    var addSdIntervalDiv = $("<div></div>").addClass("input-group")
                                .append(sdIsId)
                                .append(addSdStartSpan)
                                .append(addSdStartInput)
                                .append(addSdEndSpan)
                                .append(addSdEndInput);
    var addSdIntervalTd = $("<td style='width:18%;'></td>").append(addSdIntervalDiv);
    var addSdProjectDiv =  $("<div></div>").addClass("form-group")
                               .append($("<select  name='sdFaId' id='is_sd_fa_select' onchange='is_sd_fa_info(this)'></select>").addClass("form-control form-input-select"));
    var addSdProjectTd = $("<td style='width:15%;'></td>").append(addSdProjectDiv);
    var addSdMethodTd = $("<td style='width:15%;'></td>");
    var addSdZxwTd = $("<td style='width:24%;'></td>");
    var addSdActionTd = $("<td style='width:18%;'></td>");
    //时段保存与取消按钮
    var createBtn = $("<button type='button' onclick='add_is_interval(this);'></button>").addClass(
        "btn btn-primary").append(
        $("<span></span>").addClass("glyphicon glyphicon-ok").attr("aria-hidden","true")
    );
    var cancelBtn = $("<button onclick='cancel_is_interval(this);'></button>").addClass(
        "btn btn-danger").append(
        $("<span></span>").addClass("glyphicon glyphicon-remove").attr("aria-hidden","true")
    );
    addSdActionTd.append(createBtn).append(" ").append(cancelBtn);
    var addSdTr = $("<tr class='add-is-sd-tr'></tr>");
    //append方法执行完后返回原来的元素
    addSdTr.append(addSdSignTd).append(addSdNameTd).append(addSdIntervalTd).append(
        addSdProjectTd).append(addSdMethodTd).append(addSdZxwTd).append(
        addSdActionTd).appendTo("#is_interval_table tbody");
    //$("#is_interval_table tbody").append(addSdForm);
    getProjtects("#is_sd_fa_select");
    //alert("添加完成");
}
