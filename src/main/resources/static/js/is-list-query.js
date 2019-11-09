var totalRecord;  //总记录数
var pageNum; //当前页数
var search;  //查询条件
var action; //查询操作
$(function () {

    //路口搜索页面
    //alert("路口搜索页面");
    //获取搜索框中的值
    search = $("#is_name_search").val();
    action = "isSearch";
    to_page(1);

});

//改变大队搜索条件
function change_dd_search(e) {
    search = $(e).val();
    action = "ddSearch";
    //alert(search);
    to_page(1);
}

//改变路口搜索条件
function change_is_search() {
    search = $("#is_name_search").val();
    //alert(search);
    action = "isSearch";
    to_page(1);
}

//去首页
function to_page(pn) {
    $("#check_all").prop("checked", false);
    //大队搜索
    if (action == "ddSearch") {
        $.ajax({
            url: "/intersection/list/brigade",
            data: "pn=" + pn + "&search=" + search,
            type: "post",
            success: function (result) {
                //1、解析并显示路口数据
                build_is_table(result);
                //2、解析并显示分页信息
                build_page_info(result);
                //3、显示导航条信息
                build_page_nav(result);
            }
        });
    } else if (action == "isSearch") {
        //路口搜索
        $.ajax({
            url: "/intersection/list/is",
            data: "pn=" + pn + "&search=" + search,
            type: "post",
            success: function (result) {
                //1、解析并显示路口数据
                build_is_table(result);
                //2、解析并显示分页信息
                build_page_info(result);
                //3、显示导航条信息
                build_page_nav(result);
            }
        });
    }
}

//解析显示路口信息
function build_is_table(result) {
    //清空table表格
    $("#is_table tbody").empty();
    // alert(result.data.list);
    // console.log(result.data.list);
    var intersections = result.data.list;
    $.each(intersections, function (index, item) {
        //var isIdTd = $("<td></td>").append(item.id);
        var isIdTd = $("<td></td>").append("&raquo;");
        var isDdTd = $("<td></td>").append(item.isDdName);
        var isDlTd = $("<td></td>").append(item.isDlName);
        var isNameLink = $("<a></a>").append(item.isName).attr("href", "/intersection/info/" + item.id);
        var isNameTd = $("<td></td>").append(isNameLink);
        var isXhTd = $("<td></td>")
            .append(item.isXhName);
        //append方法执行完后返回原来的元素
        $("<tr></tr>").append(isIdTd).append(isDdTd).append(
            isDlTd).append(isNameTd).append(isXhTd).appendTo("#is_table tbody");
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

//判断字符是否为空
function isEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}
