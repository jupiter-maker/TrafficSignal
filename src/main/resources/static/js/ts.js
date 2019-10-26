//校验路口表单数据
function validate_add_isform() {
    //1、拿到要校验的数据
    var isSd = $("#isSd_add_input").val();
    var regSd = /^[0-923]$/;
    //alert(regName.test(empName));
    if (!regSd.test(isSd)) {
        show_validate_msg("#isSd_add_input", "error", "时段数范围在0-23");
        return false;
    } else {
        show_validate_msg("#isSd_add_input", "success", "");
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
        url: "/is/checkIsName",
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
    //2、判断之前的ajax用户名校验是否成功
    if ($(e).attr("ajax_va") == "error") {
        return false;
    }
    //3、发送ajax请求保存员工
    //给form表单中的数据序列化为字符串
    $.ajax({
        url: "/is/save",
        type: "POST",
        data: $("#addIntersectionModal form").serialize(),
        success: function (result) {
            if (result.status == 200) {
                //alert(result.msg);
                //员工保存成功
                //1、关闭模态框
                $("#addIntersectionModal").modal("hide");
                //2、来到最后一页，显示最后一页数据
                //发送请求显示最后一页
                //to_page(totalRecord);
            } else {
                alert(result.msg);
                // //显示失败信息
                // //console.log(result);
                // if (undefined != result.extend.error.email) {
                //     //显示员工邮箱错误信息
                //     show_vaclidate_msg("#email_add_input", "error", result.extend.error.email);
                // }
                // if (undefined != result.extend.error.empName) {
                //     //显示员工错误信息
                //     show_vaclidate_msg("#empName_add_input", "error", result.extend.error.empName)
                // }
            }
        }
    });
}