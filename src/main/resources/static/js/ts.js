//将大队信息显示在下拉列表
function getBrigades(e){
    //清空之前下拉列表中数据
    $(e).empty();
    $.ajax({
        url:"/brigade/all",
        type:"GET",
        success:function(result){
            if(result.status == 200 ){
                //显示部门信息在下拉列表中
                $.each(result.data,function(index,item){
                    var optionEle = $("<option></option>").append(item.ddName).attr("value",item.id);
                    optionEle.appendTo(e);
                });
            }else{
                alert(result.msg);
            }
        }
    });
}
//将信号机型号信息显示在下拉列表
function getAnnunciators(e){
    //清空之前下拉列表中数据
    $(e).empty();
    $.ajax({
        url:"/annunciator/all",
        type:"GET",
        success:function(result){
            if(result.status == 200 ){
                //显示部门信息在下拉列表中
                $.each(result.data,function(index,item){
                    var optionEle = $("<option></option>").append(item.xhName).attr("value",item.id);
                    optionEle.appendTo(e);
                });
            }else{
                alert(result.msg);
            }
        }
    });
}
//清除表单数据
function reset_form(e){
    //重置表单内容
    $(e)[0].reset();
    //清空表单样式
    $(e).find("*").removeClass("has-error has-success");
    $(e).find(".help-block").text("");
}
//打开模态框
function open_is_add_modal(){
    //清除表单数据（表单重置（表单中的数据，表单的样式））
    //$("#empAddModal form")[0].reset();
    reset_form("#addIntersectionModal form");
    //发送ajax请求，查出大队和信号机型号信息，显示在下拉列表中
    getBrigades("#is_dd_select");
    getAnnunciators("#is_xh_select");
    //弹出模态框
    $("#addIntersectionModal").modal({
        backdrop : "static"
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
//校验路口表单数据
function validate_add_isform() {
    //1、拿到要校验的数据
    var isName = $("#isName_add_input").val();
    var isDl = $("#isDl_add_input").val();
    var isWhName = $("#isWh_add_input").val();
    if (isEmpty(isName) || isEmpty(isDl) || isEmpty(isWhName)) {
        alert("所有选项为必填项");
        return false;
    }
    return true;
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
        alert("路口名重复");
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
}

//校验路口名是否重复
function checkIsName(e) {
    //发送ajax请求校验路口名是否可用
    var isName = e.value;
    $.ajax({
        url: "/intersection/checkIsName",
        data: "isName=" + isName,
        type: "POST",
        success: function (result) {
            if (result.status == 200) {
                show_validate_msg("#isName_add_input", "success", "");
                $("#is_save_btn").attr("ajax_va", "success");
            } else {
                show_validate_msg("#isName_add_input", "error", result.msg);
                $("#is_save_btn").attr("ajax_va", "error");
            }
        }
    });
}
//添加路口
function addIntersection(e) {
    //1、模态框中填写的表单数据提交给服务器进行保存
    //先对提交给服务器的数据进行校验
    if (!validate_add_isform()) {
        return false;
    }
    //2、判断之前的ajax路口名校验是否成功
    if ($(e).attr("ajax_va") == "error") {
        return false;
    }
    //3、发送ajax请求保存员工
    //给form表单中的数据序列化为字符串
    $.ajax({
        url: "/intersection/save",
        type: "POST",
        data: $("#addIntersectionModal form").serialize(),
        success: function (result) {
            if (result.status == 200) {
                alert(result.msg);
                //员工保存成功
                //1、关闭模态框
                $("#addIntersectionModal").modal("hide");
                //2、来到最后一页，显示最后一页数据
                //发送请求显示最后一页
                //to_page(totalRecord);
            } else {
                alert(result.msg);

            }
        }
    });
}