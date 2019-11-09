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

        //var isIdTd = $("<td></td>").append(item.id);
        var dlIdTd = $("<td></td>").append("&raquo;");
        var dlNameDdTd = $("<td></td>").append(item.dlName);
        var dlDdNameTd = $("<td></td>").append(item.dlDdName);
        var dlRespTd = $("<td></td>").append(item.dlResp);
        var dlRespPhoneTd = $("<td></td>").append(item.dlRespPhone);
        var dlDescTd = $("<td></td>").append(item.dlDesc);


        //append方法执行完后返回原来的元素
        $("<tr></tr>").append(dlIdTd).append(dlNameDdTd).append(
            dlDdNameTd).append(dlRespTd).append(dlRespPhoneTd).append(
            dlDescTd).appendTo("#dl_table tbody");
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














