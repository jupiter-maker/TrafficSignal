var totalRecord;  //总记录数
var pageNum; //当前页数
var brigadeId=1;//大队查询时的id
var search;  //查询条件
var action; //查询操作
$(function () {

    //判断当前是什么页面
    if($("#dd_search_select").length>0){
        //大队搜索页面
        //向搜索框内填充大队信息
        brigadeId = $("#dd_search_id").val();
        //alert("大队搜索页面id:"+brigadeId);
        getBrigades("#dd_search_select");
        search = brigadeId;
        action = "ddSearch";
        to_page(1);
    }
    if($("#is_name_search").length>0){
        //路口搜索页面
        //alert("路口搜索页面");
        //获取搜索框中的值
        search = $("#is_name_search").val();
        action = "isSearch";
        to_page(1);
    }

});
//改变大队搜索条件
function change_dd_search(e){
    search = $(e).val();
    action = "ddSearch";
    //alert(search);
    to_page(1);
}
//改变路口搜索条件
function change_is_search(){
    search = $("#is_name_search").val();
    //alert(search);
    action = "isSearch";
    to_page(1);
}
//去首页
function to_page(pn) {
    $("#check_all").prop("checked", false);
    //大队搜索
    if(action == "ddSearch"){
        $.ajax({
            url: "/intersection/list/brigade",
            data: "pn=" + pn + "&search="+search,
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
    }else if(action =="isSearch"){
        //路口搜索
        $.ajax({
            url: "/intersection/list/is",
            data: "pn=" + pn + "&search="+search,
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
        var checkBoxTd = $("<td></td>").append(
            $("<input type='checkbox' class='check_item' onclick='check_all_boolean();'/>").attr("delete_id",item.id)
        );
        //var isIdTd = $("<td></td>").append(item.id);
        var isIdTd = $("<td></td>").append("&raquo;");
        var isDdTd = $("<td></td>").append(item.isDdName);
        var isDlTd = $("<td></td>").append(item.isDl);
        var isNameLink = $("<a></a>").append(item.isName).attr("href","/intersection/info/"+item.id);
        var isNameTd = $("<td></td>").append(isNameLink);
        var isXhTd = $("<td></td>")
            .append(item.isXhName);
        //为编辑按钮添加自定义属性，id值
        var editBtn = $("<button onclick='open_is_update_model(this);'></button>").addClass(
            "btn btn-success btn-sm edit_btn").append(
            $("<span></span>").addClass(
                "glyphicon glyphicon-pencil")).append("编辑");
        editBtn.attr("edit_id", item.id);
        var delBtn = $("<button onclick='delete_single_is(this);'></button>").addClass(
            "btn btn-danger btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append(
            "删除");
        //给删除按钮添加自定义属性
        delBtn.attr("delete_id", item.id);
        var btnTd = $("<td></td>").append(editBtn).append(" ").append(
            delBtn);
        //append方法执行完后返回原来的元素
        $("<tr></tr>").append(checkBoxTd).append(isIdTd).append(isDdTd).append(
            isDlTd).append(isNameTd).append(isXhTd).append(
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
function check_all_boolean(){
    var flag = $(".check_item:checked").length == $(".check_item").length;
    $("#check_all").prop("checked",flag);
}
//全选全不选
function check_all(e) {
    //attr获取checked是undefined;
    //dom原生属性prop获取，attr获取自定义属性的值
    //alert($(this).prop("checked"));
    $(".check_item").prop("checked", $(e).prop("checked"));
}
//批量删除
function delete_checked_all(){
    //$(".check_item:checked")
    var isNames = "";
    var id_strs = "";
    $.each($(".check_item:checked"),function(){
        isNames += $(this).parents("tr").find("td:eq(4)").text()+",";
        id_strs += $(this).attr("delete_id")+"-";
    });
    isNames = isNames.substring(0,isNames.length-1);
    id_strs = id_strs.substring(0,id_strs.length-1);
    if(confirm("确认删除【"+isNames+"】吗？")){
        //发送ajax请求
        $.ajax({
            url:"/intersection/"+id_strs,
            type:"DELETE",
            success:function(result){
                if(result.status == 200){
                    //清除标题复选框
                    $("#check_all").prop("checked",false);
                    //回到当前页面
                    //alert(pageNum);
                    to_page(pageNum);
                }else{
                    alert(result.msg);
                }
            }
        });

    }
}
//单个删除
function delete_single_is(e){
    //1、弹出确认删除对话框
    var isName = $(e).parents("tr").find("td:eq(4)").text();
    if(confirm("确认删除【"+isName+"】吗？")){
        $.ajax({
            url:"/intersection/"+$(e).attr("delete_id"),
            type:"DELETE",
            success:function(result){
                if(result.status == 200){
                    //alert(result.msg);
                    to_page(pageNum);
                }else{
                    alert(result.msg);
                }
            }
        });
    }
}
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
                //初始化大队选择框id
                $(e).val([brigadeId]);

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
//打开路口添加模态框
function open_is_add_modal(){
    //清除表单数据（表单重置（表单中的数据，表单的样式））
    //$("#empAddModal form")[0].reset();
    reset_form("#addIntersectionModal form");
    //发送ajax请求，查出大队和信号机型号信息，显示在下拉列表中
    getBrigades("#isDd_select");
    getAnnunciators("#isXh_select");
    //弹出模态框
    $("#addIntersectionModal").modal({
        backdrop : "static"
    });

}
//打开路口更新模态框
function open_is_update_model(e){
    //alert("编辑路口信息"+":"+$(e).attr("edit_id"));
    //清除表单数据（表单重置（表单中的数据，表单的样式））
    //$("#empAddModal form")[0].reset();
    reset_form("#updateIntersectionModal form");
    //发送ajax请求，查出大队和信号机型号信息，显示在下拉列表中
    getBrigades("#isDd_update_select");
    getAnnunciators("#isXh_update_select");
    //查询并显示路口信息
    getIntersection($(e).attr("edit_id"));
    //把路口id赋值表单
    $("#is_update_id").val($(e).attr("edit_id"));
    $("#updateIntersectionModal").modal({
        backdrop : "static"
    });
}
//查询并显示路口信息
function getIntersection(id){
    $.ajax({
        url:"/intersection/"+id,
        type:"GET",
        success:function(result){
            if(result.status == 200){
                var isData = result.data;
                $("#isName_update_static").text(isData.isName);
                $("#isDd_update_select").val([isData.isDdId]);
                $("#isDl_update_input").val(isData.isDl);
                $("#isXh_update_select").val([isData.isXhId]);
                $("#isWh_update_input").val(isData.isWhName);
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
//校验路口表单数据
function validate_add_isform(e) {
    if(e == "create"){
        //创建路口校验
        //1、拿到要校验的数据
        var isName = $("#isName_add_input").val();
        var isDl = $("#isDl_add_input").val();
        var isWhName = $("#isWh_add_input").val();
        if (isEmpty(isName) || isEmpty(isDl) || isEmpty(isWhName)) {
            alert("所有选项为必填项");
            return false;
        }
        return true;
    }else{
        //更新路口校验
        //1、拿到要校验的数据
        var isDl = $("#isDl_update_input").val();
        var isWhName = $("#isWh_update_input").val();
        if (isEmpty(isDl) || isEmpty(isWhName)) {
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
    if (!validate_add_isform("create")) {
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
                //alert(result.msg);
                //员工保存成功
                //1、关闭模态框
                $("#addIntersectionModal").modal("hide");
                //2、来到最后一页，显示最后一页数据
                //发送请求显示最后一页
                to_page(totalRecord);
            } else {
                alert(result.msg);
            }
        }
    });
}
//更新路口
function updateIntersection(){
    //1、模态框中填写的表单数据提交给服务器进行保存
    //先对提交给服务器的数据进行校验
    if (!validate_add_isform("update")) {
        return false;
    }
    //3、发送ajax请求保存员工
    //给form表单中的数据序列化为字符串
    $.ajax({
        url: "/intersection/save",
        type: "POST",
        data: $("#updateIntersectionModal form").serialize(),
        success: function (result) {
            if (result.status == 200) {
                //alert(result.msg);
                //员工保存成功
                //1、关闭模态框
                $("#updateIntersectionModal").modal("hide");
                //2.发送请求显示本页面
                to_page(pageNum);
            } else {
                alert(result.msg);
            }
        }
    });
}