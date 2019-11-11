var totalRecord;  //总记录数
var pageNum; //当前页数
var search;  //查询条件
$(function () {
    search = $("#fa_name_search").val();
    to_page(1);

});


//改变路口搜索条件
function change_fa_search() {
    search = $("#fa_name_search").val();
    to_page(1);
}

//去首页
function to_page(pn) {
    //查询方案
    $.ajax({
        url: "/project/list",
        data: "pn=" + pn + "&search=" + search,
        type: "post",
        success: function (result) {
            //1、解析并显示路口数据
            build_fa_table(result);
            //2、解析并显示分页信息
            build_page_info(result);
            //3、显示导航条信息
            build_page_nav(result);
        }
    });

}

//解析显示路口信息
function build_fa_table(result) {
    $("#fa_table tbody").empty();

    var projects = result.data.list;
    $.each(projects, function (index, item) {

        var faSignTd = $("<td></td>").append("&raquo;");
        var isNameLink = $("<a></a>").append(item.faName).attr("href", "/project/info/" + item.id);
        var faNameTd = $("<td></td>").append(isNameLink);
        var faMethodTd = $("<td></td>").append(item.faMethod);

        var faXwcTd = $("<td></td>").append(item.faXwc+'s');
        var faZqcdTd = $("<td></td>").append(item.faZqcd+'s');
        //为编辑按钮添加自定义属性，id值
        var editBtn = $("<button onclick='open_fa_update_modal(this);'></button>").addClass(
            "btn btn-success btn-sm edit_btn").append(
            $("<span></span>").addClass(
                "glyphicon glyphicon-pencil")).append("编辑");
        editBtn.attr("edit_id", item.id);
        var delBtn = $("<button onclick='delete_single_fa(this);'></button>").addClass(
            "btn btn-danger btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append(
            "删除");
        //给删除按钮添加自定义属性
        delBtn.attr("delete_id", item.id);
        var btnTd = $("<td></td>").append(editBtn).append(" ").append(
            delBtn);
        //append方法执行完后返回原来的元素
        $("<tr></tr>").append(faSignTd).append(faNameTd).append(faMethodTd).append(
            faXwcTd).append(faZqcdTd).append(
            btnTd).appendTo("#fa_table tbody");
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


//单个删除
function delete_single_fa(e) {
    //1、弹出确认删除对话框
    var faName = $(e).parents("tr").find("td:eq(1)").text();
    if (confirm("确认删除【" + faName + "】吗？")) {
        $.ajax({
            url: "/project/" + $(e).attr("delete_id"),
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


//清除表单数据
function reset_form(e) {
    //重置表单内容
    $(e)[0].reset();
    //清空表单样式
    $(e).find("*").removeClass("has-error has-success");
    $(e).find(".help-block").text("");
}

//打开方案更新模态框
function open_fa_update_modal(e) {

    reset_form("#updateProjectModal form");
    $("#fa_id").val($(e).attr("edit_id"));
    getProject($("#fa_id").val());
    $("#updateProjectModal").modal({
        backdrop: "static"
    });
}

//查询并显示方案信息
function getProject(faId) {
    $.ajax({
        url: "/project/" + faId,
        type: "GET",
        success: function (result) {
            if (result.status == 200) {
                var project = result.data;
                $("#faName_update_input").val(project.faName);
                $("#faMethod_update_input").val(project.faMethod);
                $("#faXwc_update_input").val(project.faXwc);
                $("#faZqcd_update_input").val(project.faZqcd);
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

//校验方案表单数据
function validate_add_faform() {
    var faName = $("#faName_update_input").val();
    var faMethod = $("#faMethod_update_input").val();
    var faXwc = $("#faXwc_update_input").val();
    var faZqcd = $("#faZqcd_update_input").val();
    if (isEmpty(faName) || isEmpty(faMethod) || isEmpty(faXwc) || isEmpty(faZqcd)) {
        alert("所有选项为必填项");
        return false;
    }
    if (!isNumber(faXwc) || !isNumber(faZqcd)) {
        alert("相位差与周期长度应为数字！");
        return false;
    }
    return true;

}

//更新方案
function updateProject() {
    if (!validate_add_faform()) {
        return false;
    }
    //发送ajax请求保存方案
    //给form表单中的数据序列化为字符串
    $.ajax({
        url: "/project/save",
        type: "POST",
        data: $("#updateProjectModal form").serialize(),
        success: function (result) {
            if (result.status == 200) {
                //修改成功，跳转到当前页面
                //alert("更新成功！");
                //关闭模态框
                $("#updateProjectModal").modal("hide");
                to_page(pageNum);
            } else {
                alert(result.msg);
            }
        }
    });
}

//0-60正则匹配
function isNumber(str) {
    var regExp = /^[0-9]*$/;
    var result = regExp.test(str);
    return result;
}
