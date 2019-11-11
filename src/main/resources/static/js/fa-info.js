$(function(){
    //初始化相位信息
    init_phase_page($("#fa_id").val())
});
//解析显示相位信息
function init_phase_page(id) {
    $.ajax({
        url: "/phase/list",
        type: "GET",
        data: "faId=" + id,
        success: function (result) {
            if (result.status == 200) {
                //alert(result.msg);
                //解析并显示方案相位数据
                build_fa_phase_table(result);
            }

        }
    });
}
//解析显示相位信息
function build_fa_phase_table(result) {
    //清空table表格
    $("#fa_phase_table tbody").empty();
    var phases = result.data;
    $.each(phases, function (index, item) {
        var signTd = $("<td ></td>").append(
            $("<span></span>").html(index)
        );
        var xwNameTd = $("<td ></td>").html(item.xwName);
        var xwGreenTd = $("<td ></td>").html(item.xwGreen);
        var xwYellowTd = $("<td ></td>").html(item.xwYellow);
        var xwRedTd = $("<td ></td>").html(item.xwRed);
        var xwVeGreenTd = $("<td ></td>").html(item.xwVehicleGreen);
        var xwVeRedTd = $("<td ></td>").html(item.xwVehicleRed);
        var xwWaGreenTd = $("<td ></td>").html(item.xwWalkerGreen);
        var xwWaRedTd = $("<td ></td>").html(item.xwWalkerRed);
        var delBtn = $("<a onclick='delete_phase(this);'></a>").append(
            $("<span></span>").addClass("glyphicon glyphicon-scissors").attr("aria-hidden", "true").append("删除")
        );
        delBtn.attr("delete_id", item.id);
        var editBtn = $("<a onclick='edit_fa_zxw(this);'></a>").append(
            $("<span></span>").addClass("glyphicon glyphicon-scissors").attr("aria-hidden", "true").append("设为主相位")
        );
        editBtn.attr("edit_id", item.id);
        var btnTd = $("<td style='width:18%;'></td>").append(delBtn).append(" ").append(editBtn);
        //append方法执行完后返回原来的元素
        $("<tr class='xw-tr'></tr>").append(signTd).append(xwNameTd).append(xwGreenTd).append(
            xwYellowTd).append(xwRedTd).append(xwVeGreenTd).append(xwVeRedTd)
            .append(xwWaGreenTd).append(xwWaRedTd).append(btnTd).appendTo("#fa_phase_table tbody");
    });
}
//删除相位
function delete_phase(e) {
    //弹出确认删除对话框
    var xwName = $(e).parents("tr").find("td:eq(1)").text();
    if (confirm("确认删除【" + xwName + "】吗？")) {
        $.ajax({
            url: "/phase/" + $(e).attr("delete_id"),
            type: "DELETE",
            success: function (result) {
                if (result.status == 200) {
                    //alert(result.msg);
                    init_phase_page($("#fa_id").val());
                } else {
                    alert(result.msg);
                }
            }
        });
    }
}
//打开相位添加模态框
function add_phase_modal() {
    var formLength = $(".add-fa-xw-tr").length;
    if (formLength != 0) {
        //alert("请填写信息");
        return false;
    }
    var xwNum = $(".xw-tr").length;
    var xwNumTd = $("<td width='5%'></td>").html(xwNum);
    var xwNameDiv = $("<div class='input-group'></div>").append(
        $("<span class=\"input-group-addon\">\n" +
            "        <span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span>\n" +
            "        </span>")
    ).append(
        $("<input type=\"text\" id=\"xwName\"  class=\"form-control\" placeholder=\"(s)\" aria-describedby=\"basic-addon1\">\n").attr("value",xwNum+"-")
    );
    var xwNameTd = $("<td width='18%'></td>").append(xwNameDiv);
    var xwGreenTd = $("<td width=\"10%\">\n" +
        "                    <div class=\"input-group\">\n" +
        "                        <span class=\"input-group-addon\">\n" +
        "                            <span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span>\n" +
        "                        </span>\n" +
        "                        <input type=\"text\" value=\"0\" id=\"xwGreen\" class=\"form-control\" placeholder=\"(s)\" aria-describedby=\"basic-addon1\">\n" +
        "                    </div>\n" +
        "                </td>");
    var xwYellowTd = $("<td width=\"10%\">\n" +
        "                    <div class=\"input-group\">\n" +
        "                        <span class=\"input-group-addon\">\n" +
        "                            <span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span>\n" +
        "                        </span>\n" +
        "                        <input type=\"text\" value=\"0\" id=\"xwYellow\" class=\"form-control\" placeholder=\"(s)\" aria-describedby=\"basic-addon1\">\n" +
        "                    </div>\n" +
        "                </td>");
    var xwRedTd = $("<td width=\"10%\">\n" +
        "                    <div class=\"input-group\">\n" +
        "                        <span class=\"input-group-addon\">\n" +
        "                            <span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span>\n" +
        "                        </span>\n" +
        "                        <input type=\"text\" value=\"0\" id=\"xwRed\" class=\"form-control\" placeholder=\"(s)\" aria-describedby=\"basic-addon1\">\n" +
        "                    </div>\n" +
        "                </td>");
    var xwVehicleGreenTd = $("<td width=\"10%\">\n" +
        "                    <div class=\"input-group\">\n" +
        "                        <span class=\"input-group-addon\">\n" +
        "                            <span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span>\n" +
        "                        </span>\n" +
        "                        <input type=\"text\" value=\"0\" id=\"xwVehicleGreen\" class=\"form-control\" placeholder=\"(s)\" aria-describedby=\"basic-addon1\">\n" +
        "                    </div>\n" +
        "                </td>");
    var xwVehicleRedTd = $("<td width=\"10%\">\n" +
        "                    <div class=\"input-group\">\n" +
        "                        <span class=\"input-group-addon\">\n" +
        "                            <span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span>\n" +
        "                        </span>\n" +
        "                        <input type=\"text\" value=\"0\" id=\"xwVehicleRed\" class=\"form-control\" placeholder=\"(s)\" aria-describedby=\"basic-addon1\">\n" +
        "                    </div>\n" +
        "                </td>");
    var xwWalkerGreenTd = $("<td width=\"10%\">\n" +
        "                    <div class=\"input-group\">\n" +
        "                        <span class=\"input-group-addon\">\n" +
        "                            <span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span>\n" +
        "                        </span>\n" +
        "                        <input type=\"text\" value=\"0\" id=\"xwWalkerGreen\" class=\"form-control\" placeholder=\"(s)\" aria-describedby=\"basic-addon1\">\n" +
        "                    </div>\n" +
        "                </td>");
    var xwWalkerRed = $("<td width=\"10%\">\n" +
        "                    <div class=\"input-group\">\n" +
        "                        <span class=\"input-group-addon\">\n" +
        "                            <span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span>\n" +
        "                        </span>\n" +
        "                        <input type=\"text\" value=\"0\" id=\"xwWalkerRed\" class=\"form-control\" placeholder=\"(s)\" aria-describedby=\"basic-addon1\">\n" +
        "                    </div>\n" +
        "                </td>");
    var addSdActionTd = $("<td style='width:7%;'></td>");
    //相位保存与取消按钮
    var createBtn = $("<button type='button' onclick='add_fa_xw(this);'></button>").addClass(
        "btn btn-primary").append(
        $("<span></span>").addClass("glyphicon glyphicon-ok").attr("aria-hidden", "true")
    );
    var cancelBtn = $("<button onclick='cancel_fa_xw(this);'></button>").addClass(
        "btn btn-danger").append(
        $("<span></span>").addClass("glyphicon glyphicon-remove").attr("aria-hidden", "true")
    );
    addSdActionTd.append(createBtn).append(" ").append(cancelBtn);

    var addSdTr = $("<tr class='add-fa-xw-tr'></tr>");
    //append方法执行完后返回原来的元素
    addSdTr.append(xwNumTd).append(xwNameTd).append(xwGreenTd).append(
        xwYellowTd).append(xwRedTd).append(xwVehicleGreenTd).append(
        xwVehicleRedTd).append(
        xwWalkerGreenTd).append(
        xwWalkerRed).append(
        addSdActionTd).appendTo("#fa_phase_table tbody");
    //$("#is_interval_table tbody").append(addSdForm);

}
//增加相位
function add_fa_xw(e) {
    //1、模态框中填写的表单数据提交给服务器进行保存
    //先对提交给服务器的数据进行校验
    if (!validate_add_fa_xw()) {
        return false;
    }
    //alert($("#sd_is_id").val()+"**"+$("#is_sd_start").val()+"**"+$("#is_sd_end").val()+"**"+$("#is_sd_fa_select").val());
    //2、发送ajax请求保存时段,采用发送json数据的方式
    $.ajax({
        type: "POST",
        url: "/phase/save",
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        data: JSON.stringify({
            "xwFaId": $("#fa_id").val(),
            "xwName": $("#xwName").val(),
            "xwGreen": $("#xwGreen").val(),
            "xwYellow": $("#xwYellow").val(),
            "xwRed": $("#xwRed").val(),
            "xwVehicleGreen": $("#xwVehicleGreen").val(),
            "xwVehicleRed": $("#xwVehicleRed").val(),
            "xwWalkerGreen": $("#xwWalkerGreen").val(),
            "xwWalkerRed": $("#xwWalkerRed").val()
        }),
        success: function (result) {
            if (result.status == 200) {
                alert("添加相位成功");
                init_phase_page($("#fa_id").val());
            } else {
                alert(result.msg);
            }
        }

    });
}
//取消操作
function cancel_fa_xw() {
    init_phase_page($("#fa_id").val());
}
//校验相位输入表单
function validate_add_fa_xw() {
    var xwName = $("#xwName").val();
    var xwGreen = $("#xwGreen").val();
    var xwYellow = $("#xwYellow").val();
    var xwRed = $("#xwRed").val();
    var xwVehicleGreen = $("#xwVehicleGreen").val();
    var xwVehicleRed = $("#xwVehicleRed").val();
    var xwWalkerGreen = $("#xwWalkerGreen").val();
    var xwWalkerRed = $("#xwWalkerRed").val();
    if (isEmpty(xwName) || isEmpty(xwGreen) || isEmpty(xwYellow)
        || isEmpty(xwRed) || isEmpty(xwVehicleGreen) || isEmpty(xwVehicleRed)
        || isEmpty(xwWalkerGreen) || isEmpty(xwWalkerRed)) {
        alert("所有选项为必填项！");
        return false;
    }
    if (!isNumber(xwGreen) || !isNumber(xwYellow)
        || !isNumber(xwRed) || !isNumber(xwVehicleGreen) || !isNumber(xwVehicleRed)
        || !isNumber(xwWalkerGreen) || !isNumber(xwWalkerRed)) {
        alert("请填写数字(s)!");
        return false;
    }
    return true;

}
//将该相位设置为主相位
function edit_fa_zxw(e) {
    //弹出确认设置对话框
    var xwName = $(e).parents("tr").find("td:eq(1)").text();
    if (confirm("确认将【" + xwName + "】设置为该方案主相位吗？")) {
        $.ajax({
            url: "/project/setZxw",
            type: "post",
            data: "faId=" + $("#fa_id").val() + "&faZxwId=" + $(e).attr("edit_id"),
            success: function (result) {
                if (result.status == 200) {
                    alert("设置主相位成功！");
                    init_phase_page($("#fa_id").val());
                    update_project_info($("#fa_id").val());
                } else {
                    alert(result.msg);
                }
            }
        });
    }
}
//根据方案id更新信息div模块
function update_project_info(faId){
    $.ajax({
        url: "/project/" + faId,
        type: "GET",
        success: function (result) {
            if (result.status == 200) {
                var project = result.data;
                $("#fa_modified").text(moment(project.faModified).format("YYYY-MM-DD HH-mm"));
                $("#fa_method").text(project.faMethod);
                $("#fa_zxw_name").text(project.faZxwName);
            }
        }
    });
}
//删除相位
function delete_phase(e) {
    //弹出确认删除对话框
    var xwName = $(e).parents("tr").find("td:eq(1)").text();
    if (confirm("确认删除【" + xwName + "】吗？")) {
        $.ajax({
            url: "/phase/" + $(e).attr("delete_id"),
            type: "DELETE",
            success: function (result) {
                if (result.status == 200) {
                    //alert(result.msg);
                    init_phase_page($("#fa_id").val());
                    update_project_info($("#fa_id").val());
                } else {
                    alert(result.msg);
                }
            }
        });
    }
}

//判断字符是否为空
function isEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}
//0-60正则匹配
function isNumber(str) {
    var regExp = /^[0-9]*$/;
    var result = regExp.test(str);
    return result;
}
