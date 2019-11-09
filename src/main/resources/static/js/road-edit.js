var totalRecord;  //总记录数
var pageNum; //当前页数
var search;  //查询条件
var action; //查询操作
$(function () {

    search = $("#dl_name_search").val();
    action = "dlSearch";
    to_page(1);

});

//改变路口搜索条件
function change_dl_search() {
    search = $("#dl_name_search").val();
    //alert(search);
    action = "dlSearch";
    to_page(1);
}

//去首页
function to_page(pn) {
    $("#check_all").prop("checked", false);
    $.ajax({
        url: "/road/list",
        data: "pn=" + pn + "&search=" + search,
        type: "post",
        success: function (result) {
            if(result.status==200){
                //console.log(result);
                //1、解析并显示路口数据
                build_dl_table(result);
                //2、解析并显示分页信息
                build_page_info(result);
                //3、显示导航条信息
                build_page_nav(result);
            }
        }
    });


}

//解析显示道路信息
function build_dl_table(result) {
    //清空table表格
    $("#dl_table tbody").empty();
    // alert(result.data.list);
    // console.log(result.data.list);
    var roads = result.data.list;
    //console.log(roads);
    $.each(roads, function (index, item) {
        //console.log(item);
        var checkBoxTd = $("<td></td>").append(
            $("<input type='checkbox' class='check_item' onclick='check_all_boolean();'/>").attr("delete_id", item.id)
        );
        //var isIdTd = $("<td></td>").append(item.id);
        var dlIdTd = $("<td></td>").append("&raquo;");
        var dlNameDdTd = $("<td></td>").append(item.dlName);
        var dlDdNameTd = $("<td></td>").append(item.dlDdName);
        var dlRespTd = $("<td></td>").append(item.dlResp);
        var dlRespPhoneTd = $("<td></td>").append(item.dlRespPhone);
        var dlDescTd = $("<td></td>").append(item.dlDesc);

        //为编辑按钮添加自定义属性，id值
        var editBtn = $("<button onclick='open_dl_update_model(this);'></button>").addClass(
            "btn btn-success btn-sm edit_btn").append(
            $("<span></span>").addClass(
                "glyphicon glyphicon-pencil")).append("编辑");
        editBtn.attr("edit_id", item.id);
        var delBtn = $("<button onclick='delete_single_dl(this);'></button>").addClass(
            "btn btn-danger btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append(
            "删除");
        //给删除按钮添加自定义属性
        delBtn.attr("delete_id", item.id);
        var btnTd = $("<td></td>").append(editBtn).append(" ").append(
            delBtn);
        //append方法执行完后返回原来的元素
        $("<tr></tr>").append(checkBoxTd).append(dlIdTd).append(dlNameDdTd).append(
            dlDdNameTd).append(dlRespTd).append(dlRespPhoneTd).append(
            dlDescTd).append(btnTd).appendTo("#dl_table tbody");
        //alert("添加成功");
    });
}

//解析显示分页信息
function build_page_info(result) {
    //清空信息条
    $("#page_info_area").empty();
    $("#page_info_area").append(
        "当前第" + result.data.pageNum + "页，总"
        + result.data.pages + "页，总"
        + result.data.total + "条记录");
    totalRecord = result.data.total;
    pageNum = result.data.pageNum;
}

//解析显示分页条
function build_page_nav(result) {
    //清空分页条
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页"));
    var prePage = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (!result.data.hasPreviousPage) {
        firstPageLi.addClass("disabled");
        prePage.addClass("disabled");
    } else {
        //为元素添加单击翻页事件
        firstPageLi.click(function () {
            to_page(1);
        });
        prePage.click(function () {
            to_page(result.data.pageNum - 1);
        });
    }
    var nextPage = $("<li></li>")
        .append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页"));
    if (!result.data.hasNextPage) {
        nextPage.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPage.click(function () {
            to_page(result.data.pageNum + 1);
        });
        lastPageLi.click(function () {
            to_page(result.data.pages);
        });
    }
    //构造首页和前一页
    ul.append(firstPageLi).append(prePage);
    $.each(result.data.navigatepageNums, function (index, item) {

        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.data.pageNum == item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            to_page(item);
        });
        ul.append(numLi);
    });
    //构造后一页和末页
    ul.append(nextPage).append(lastPageLi);
    ul.appendTo();
    var navEle = $("<nav></nav>").attr("aria-label", "Page navigation")
        .append(ul);
    navEle.appendTo("#page_nav_area");
}

//校验选择状态
function check_all_boolean() {
    var flag = $(".check_item:checked").length == $(".check_item").length;
    $("#check_all").prop("checked", flag);
}

//全选全不选
function check_all(e) {
    //attr获取checked是undefined;
    //dom原生属性prop获取，attr获取自定义属性的值
    //alert($(this).prop("checked"));
    $(".check_item").prop("checked", $(e).prop("checked"));
}

//批量删除
function delete_checked_all() {
    //$(".check_item:checked")
    var isNames = "";
    var id_strs = "";
    $.each($(".check_item:checked"), function () {
        isNames += $(this).parents("tr").find("td:eq(4)").text() + ",";
        id_strs += $(this).attr("delete_id") + "-";
    });
    isNames = isNames.substring(0, isNames.length - 1);
    id_strs = id_strs.substring(0, id_strs.length - 1);
    if (confirm("确认删除【" + isNames + "】吗？")) {
        //发送ajax请求
        $.ajax({
            url: "/road/" + id_strs,
            type: "DELETE",
            success: function (result) {
                if (result.status == 200) {
                    //清除标题复选框
                    $("#check_all").prop("checked", false);
                    //回到当前页面
                    //alert(pageNum);
                    to_page(pageNum);
                } else {
                    alert(result.msg);
                }
            }
        });

    }
}

//单个删除
function delete_single_dl(e) {
    //1、弹出确认删除对话框
    var isName = $(e).parents("tr").find("td:eq(4)").text();
    if (confirm("确认删除【" + isName + "】吗？")) {
        $.ajax({
            url: "/road/" + $(e).attr("delete_id"),
            type: "DELETE",
            success: function (result) {
                if (result.status == 200) {
                    //alert(result.msg);
                    to_page(pageNum);
                } else {
                    alert(result.msg);
                }
            }
        });
    }
}

//将大队信息显示在下拉列表
function getBrigades(e, dlData) {
    //清空之前下拉列表中数据
    $(e).empty();
    $.ajax({
        url: "/brigade/all",
        type: "GET",
        success: function (result) {
            if (result.status == 200) {
                //显示大队信息在下拉列表中
                $.each(result.data, function (index, item) {
                    var optionEle = $("<option></option>").append(item.ddName).attr("value", item.id);
                    optionEle.appendTo(e);
                });
                if (!isEmpty(dlData)) {
                    //给大队选项赋初值
                    $(e).val([dlData.dlDdId]);
                }
            } else {
                alert(result.msg);
            }
        }
    });
}

//校验路口名是否重复
function checkDlName(e) {
    //发送ajax请求校验路口名是否可用
    var dlName = e.value;
    $.ajax({
        url: "/road/checkDlName",
        data: "dlName=" + dlName,
        type: "POST",
        success: function (result) {
            if (result.status == 200) {
                show_validate_msg("#dlName_add_input", "success", "");
                $("#dl_save_btn").attr("ajax_va", "success");
            } else {
                show_validate_msg("#dlName_add_input", "error", result.msg);
                $("#dl_save_btn").attr("ajax_va", "error");
            }
        }
    });
}


//清除表单数据
function reset_form(e) {
    //重置表单内容
    $(e)[0].reset();
    //清空表单样式
    $(e).find("*").removeClass("has-error has-success");
    $(e).find(".help-block").text("");
}

//打开道路更新模态框
function open_dl_update_model(e) {

    reset_form("#updateRoadModal form");
    //查询并显示路口信息
    getRoad($(e).attr("edit_id"));
    //把路口id赋值表单
    $("#dl_update_id").val($(e).attr("edit_id"));
    $("#updateRoadModal").modal({
        backdrop: "static"
    });
}

//查询并显示道路信息
function getRoad(id) {
    $.ajax({
        url: "/road/" + id,
        type: "post",
        success: function (result) {
            if (result.status == 200) {
                //回显信息
                var dlData = result.data;
                //给下拉列表赋值大队选项
                getBrigades("#dlDd_update_select", dlData);

                $("#dlName_update_static").text(dlData.dlName);
                $("#dlResp_update_input").val(dlData.dlResp);
                $("#dlRespPhone_update_input").val(dlData.dlRespPhone);
                $("#dlDesc_update_input").val(dlData.dlDesc);
            } else {
                alert(result.msg);
            }
        }
    });
}


//判断字符是否为空
function isEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}

//校验路口表单数据
function validate_add_dlform(e) {
    if (e == "create") {
        //创建路口校验
        //1、拿到要校验的数据
        var dlName = $("#dlName_add_input").val();
        var dlResp = $("#dlResp_add_input").val();
        var dlRespPhone = $("#dlRespPhone_add_input").val();
        var dlDesc = $("#dlDesc_add_input").val();
        if (isEmpty(dlName) || isEmpty(dlResp) || isEmpty(dlRespPhone) || isEmpty(dlDesc)) {
            alert("所有选项为必填项");
            return false;
        }
        return true;
    } else {
        //更新路口校验
        //1、拿到要校验的数据
        var dlResp = $("#dlResp_update_input").val();
        var dlRespPhone = $("#dlRespPhone_update_input").val();
        var dlDesc = $("#dlDesc_update_input").val();
        if (isEmpty(dlResp) || isEmpty(dlRespPhone) || isEmpty(dlDesc)) {
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


//更新路口
function updateRoad() {
    //1、模态框中填写的表单数据提交给服务器进行保存
    //先对提交给服务器的数据进行校验
    if (!validate_add_dlform("update")) {
        return false;
    }
    //3、发送ajax请求保存员工
    //给form表单中的数据序列化为字符串
    $.ajax({
        url: "/road/save",
        type: "POST",
        data: $("#updateRoadModal form").serialize(),
        success: function (result) {
            if (result.status == 200) {
                //alert(result.msg);
                //员工保存成功
                //1、关闭模态框
                $("#updateRoadModal").modal("hide");
                //2.发送请求显示本页面
                to_page(pageNum);
            } else {
                alert(result.msg);
            }
        }
    });
}
//增加道路
function addRoad(e) {
    //1、模态框中填写的表单数据提交给服务器进行保存
    //先对提交给服务器的数据进行校验
    if (!validate_add_dlform("create")) {
        return false;
    }
    //2、判断之前的ajax路口名校验是否成功
    if ($(e).attr("ajax_va") == "error") {
        return false;
    }
    //3、发送ajax请求保存员工
    //给form表单中的数据序列化为字符串
    $.ajax({
        url: "/road/save",
        type: "POST",
        data: $("#addRoadModal form").serialize(),
        success: function (result) {
            if (result.status == 200) {
                //alert(result.msg);
                //员工保存成功
                //1、关闭模态框
                $("#addRoadModal").modal("hide");
                //2.发送请求显示本页面
                to_page(totalRecord);
            } else {
                alert(result.msg);
            }
        }
    });
}
//打开道路添加模态框
function open_dl_add_modal() {

    //$("#empAddModal form")[0].reset();
    reset_form("#addRoadModal form");
    //发送ajax请求，查出大队和信号机型号信息，显示在下拉列表中
    getBrigades("#dlDd_select",null);
    //弹出模态框
    $("#addRoadModal").modal({
        backdrop: "static"
    });

}