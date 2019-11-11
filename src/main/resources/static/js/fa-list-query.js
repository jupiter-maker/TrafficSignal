var totalRecord;  //总记录数
var pageNum; //当前页数
var search;  //查询条件
$(function () {
    search = $("#fa_name_search").val();
    to_page(1);

});

//改变方案搜索条件
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
    //清空table表格
    $("#fa_table tbody").empty();
    // alert(result.data.list);
    // console.log(result.data.list);
    var projects = result.data.list;
    $.each(projects, function (index, item) {

        var faSignTd = $("<td></td>").append("&raquo;");
        var faNameTd = $("<td></td>").append(
            $("<a></a>").attr("href","/project/info/"+item.id).append(item.faName)
        );
        var faMethodTd = $("<td></td>").append(item.faMethod);
        var xwNumTd = $("<td></td>").append(item.xwNum);
        var faZxwNameTd = $("<td></td>")
            .append(item.faZxwName);
        var faXwcTd = $("<td></td>").append(item.faXwc+'s');
        var faZqcdTd = $("<td></td>").append(item.faZqcd+'s');

        $("<tr></tr>").append(faSignTd).append(faNameTd).append(
            faMethodTd).append(xwNumTd)
            .append(faZxwNameTd)
            .append(faXwcTd)
            .append(faZqcdTd)
            .appendTo("#fa_table tbody");
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
