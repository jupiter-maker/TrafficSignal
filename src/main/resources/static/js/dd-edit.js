$(function () {
    init_brigade_page();
});
//校验大队名
function checkDdName(e){
//发送ajax请求校验路口名是否可用
    var ddName = $(e).val();
    $.ajax({
        url: "/brigade/checkDdName",
        data: "ddName=" + ddName,
        type: "POST",
        success: function (result) {
            if (result.status == 200) {
                show_validate_msg("#ddName_add_input", "success", "");
                $("#dd_save_btn").attr("ajax_va", "success");
            } else {
                show_validate_msg("#ddName_add_input", "error", result.msg);
                $("#dd_save_btn").attr("ajax_va", "error");
            }
        }
    });
}
//初始化页面
function init_brigade_page() {
        $.ajax({
            url: "/brigade/all",
            type: "GET",
            success: function (result) {
                //1、解析并显示大队数据
                build_dd_table(result);
            }
        });
}
//解析显示大队信息
function build_dd_table(result) {
    //清空table表格
    $("#dd_table tbody").empty();

    var brigades = result.data;
    $.each(brigades, function (index, item) {

        var ddIdTd = $("<td></td>").append("&raquo;");
        var ddRespTd = $("<td></td>").append(item.ddResp);
        var ddNameLink = $("<a></a>").append(item.ddName).attr("href","/brigades/sts");
        var ddNameTd = $("<td></td>").append(ddNameLink);
        var ddRespPhoneTd = $("<td></td>").append(item.ddRespPhone);
        var ddRespAddressTd = $("<td></td>").append(item.ddRespAddress);
        //为编辑按钮添加自定义属性，id值
        var editBtn = $("<button onclick='open_dd_update_model(this);'></button>").addClass(
            "btn btn-success btn-sm edit_btn").append(
            $("<span></span>").addClass(
                "glyphicon glyphicon-pencil")).append("编辑");
        editBtn.attr("edit_id", item.id);
        var delBtn = $("<button onclick='delete_single_dd(this);'></button>").addClass(
            "btn btn-danger btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append(
            "删除");
        //给删除按钮添加自定义属性
        delBtn.attr("delete_id", item.id);
        var btnTd = $("<td></td>").append(editBtn).append(" ").append(
            delBtn);
        //append方法执行完后返回原来的元素
        $("<tr></tr>").append(ddIdTd).append(ddNameTd).append(ddRespTd).append(ddRespPhoneTd).append(
            ddRespAddressTd).append(btnTd).appendTo("#dd_table tbody");
    });
}




//单个删除
function delete_single_dd(e){
    //1、弹出确认删除对话框
    var isName = $(e).parents("tr").find("td:eq(1)").text();
    if(confirm("确认删除【"+isName+"】吗？对应所有道路及路口数据都将丢失！！！")){
        $.ajax({
            url:"/brigade/"+$(e).attr("delete_id"),
            type:"DELETE",
            success:function(result){
                if(result.status == 200){
                    //alert(result.msg);
                    init_brigade_page();
                }else{
                    alert(result.msg);
                }
            }
        });
    }
}

//清除表单数据
function reset_form(e){
    //重置表单内容
    $(e)[0].reset();
    //清空表单样式
    $(e).find("*").removeClass("has-error has-success");
    $(e).find(".help-block").text("");
}
//打开路口添加模态框
function open_dd_add_modal(){
    //清除表单数据（表单重置（表单中的数据，表单的样式））
    //$("#empAddModal form")[0].reset();
    reset_form("#addBrigadeModal form");
    //弹出模态框
    $("#addBrigadeModal").modal({
        backdrop : "static"
    });

}
//打开路口更新模态框
function open_dd_update_model(e){
    //alert("编辑路口信息"+":"+$(e).attr("edit_id"));
    //清除表单数据（表单重置（表单中的数据，表单的样式））
    //$("#empAddModal form")[0].reset();
    reset_form("#updateBrigadeModal form");
    getBrigade($(e).attr("edit_id"));
    //把路口id赋值表单
    $("#dd_update_id").val($(e).attr("edit_id"));
    $("#updateBrigadeModal").modal({
        backdrop : "static"
    });
}
//查询并显示大队信息
function getBrigade(id){
    $.ajax({
        url:"/brigade/"+id,
        type:"GET",
        success:function(result){
            if(result.status == 200){
                var ddData = result.data;
                $("#ddName_update_static").text(ddData.ddName);
                $("#ddResp_update_input").val(ddData.ddResp);
                $("#ddRespPhone_update_input").val(ddData.ddRespPhone);
                $("#ddRespAddress_update_input").val(ddData.ddRespAddress);
            }else{
                alert(result.msg);
            }
        }
    });
}
//判断字符是否为空
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}
//校验大队表单数据
function validate_add_ddform(e) {
    if(e == "create"){
        //创建大队校验
        //1、拿到要校验的数据
        var ddName = $("#ddName_add_input").val();
        var ddResp = $("#ddResp_add_input").val();
        var ddRespPhone = $("#ddRespPhone_add_input").val();
        var ddRespAddress = $("#ddRespAddress_add_input").val();
        //校验非空
        if (isEmpty(ddName) || isEmpty(ddResp) || isEmpty(ddRespPhone) || isEmpty(ddRespAddress)) {
            alert("所有选项为必填项");
            return false;
        }
        return true;
    }else{
        //更新大队校验
        //1、拿到要校验的数据
        var ddResp = $("#ddResp_update_input").val();
        var ddRespPhone = $("#ddRespPhone_update_input").val();
        var ddRespAddress = $("#ddRespAddress_update_input").val();
        if (isEmpty(ddResp) || isEmpty(ddRespPhone) || isEmpty(ddRespAddress)) {
            alert("所有选项为必填项");
            return false;
        }
        return true;
    }

}
//显示校验状态
function show_validate_msg(ele, status, msg) {
    //清除当前元素校验状态
    $(ele).parent().removeClass("has-success has-error");
    $(ele).next("span").text("");
    if ("success" == status) {
        $(ele).parent().addClass("has-success");
        $(ele).next("span").text(msg);
    } else if ("error" == status) {
        //alert("路口名重复");
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
}


//添加路口
function addBrigade(e) {
    //1、模态框中填写的表单数据提交给服务器进行保存
    //先对提交给服务器的数据进行校验
    if (!validate_add_ddform("create")) {
        return false;
    }
    //2、判断之前的ajax路口名校验是否成功
    if ($(e).attr("ajax_va") == "error") {
        return false;
    }
    //3、发送ajax请求保存员工
    //给form表单中的数据序列化为字符串
    $.ajax({
        url: "/brigade/save",
        type: "POST",
        data: $("#addBrigadeModal form").serialize(),
        success: function (result) {
            if (result.status == 200) {
                //alert(result.msg);
                //员工保存成功
                //1、关闭模态框
                $("#addBrigadeModal").modal("hide");
                //刷新页面
                init_brigade_page();
            } else {
                alert(result.msg);
            }
        }
    });
}
//更新路口
function updateBrigade(){
    //1、模态框中填写的表单数据提交给服务器进行保存
    //先对提交给服务器的数据进行校验
    if (!validate_add_ddform("update")) {
        return false;
    }
    //3、发送ajax请求保存员工
    //给form表单中的数据序列化为字符串
    $.ajax({
        url: "/brigade/save",
        type: "POST",
        data: $("#updateBrigadeModal form").serialize(),
        success: function (result) {
            if (result.status == 200) {
                //alert(result.msg);
                //员工保存成功
                //1、关闭模态框
                $("#updateBrigadeModal").modal("hide");
                //2.发送请求显示本页面
                init_brigade_page();
            } else {
                alert(result.msg);
            }
        }
    });
}