var totalRecord;
var pageNum;
$(function() {
    //去首页
    to_page(1);
});
function to_page(pn) {
    $("#check_all").prop("checked",false);
    $.ajax({
        url : "/intersection/list",
        data : "pn=" + pn,
        type : "post",
        success : function(result) {
            alert(result.data.list);
            //1、解析并显示员工数据
            build_is_table(result);
            //2、解析并显示分页信息
            build_page_info(result);
            //3、显示导航条信息
            build_page_nav(result);
        }
    });
}
//解析显示路口信息
function build_is_table(result) {
    //清空table表格
    $("#is_table tbody").empty();
    var intersections = result.data.list;
    $.each(intersections, function(index, item) {
        var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
        var isIdTd = $("<td></td>").append(item.id);
        var isDdTd = $("<td></td>").append(item.isDdName);
        var isDlTd = $("<td></td>").append(item.isDl);
        var isNameTd = $("<td></td>").append(item.isName);
        var isXhTd = $("<td></td>")
            .append(item.isXhName);
        //为编辑按钮添加自定义属性，id值
        var editBtn = $("<button></button>").addClass(
            "btn btn-success btn-sm edit_btn").append(
            $("<span></span>").addClass(
                "glyphicon glyphicon-pencil")).append("编辑");
        editBtn.attr("edit_id",item.id);
        var delBtn = $("<button></button>").addClass(
            "btn btn-danger btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append(
            "删除");
        //给删除按钮添加自定义属性
        delBtn.attr("delete_id",item.id);
        var btnTd = $("<td></td>").append(editBtn).append(" ").append(
            delBtn);
        //append方法执行完后返回原来的元素
        $("<tr></tr>").append(checkBoxTd).append(empIdTd).append(empNameTd).append(
            genderTd).append(emailTd).append(deptNameTd).append(
            btnTd).appendTo("#is_table tbody");
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
    totalRecord = result.total;
    pageNum = result.pageNum;
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
        firstPageLi.click(function() {
            to_page(1);
        });
        prePage.click(function() {
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
        nextPage.click(function() {
            to_page(result.data.pageNum + 1);
        });
        lastPageLi.click(function() {
            to_page(result.data.pages);
        });
    }
    //构造首页和前一页
    ul.append(firstPageLi).append(prePage);
    $.each(result.data.navigatepageNums, function(index,item) {

        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.data.pageNum == item) {
            numLi.addClass("active");
        }
        numLi.click(function() {
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