//判断字符是否为空
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}
$(function () {
    init_brigade_page();
});
function init_brigade_page(){
    $.ajax({
        url: "/brigade/list",
        type: "GET",
        success: function (result) {
            //alert(result.msg);
            //解析并显示路口数据
            build_brigade_table(result);

        }
    });
}
//解析显示时段信息
function build_brigade_table(result) {
    //清空table表格
    $("#dd-list-table tbody").empty();
    var brigades = result.data;
    var isNumCount = 0;
    var isSdNumCount = 0;
    var isFaNumCoun = 0;
    $.each(brigades, function (index, item) {
        // var ddNameTd = $("<td></td>").append(
        //     $("<a></a>").append(item.ddName).attr("href","/brigade/intersections?brigadeId="+item.id)
        // );
        var ddNameTd = $("<td></td>").append(
            item.ddName
        );
        isNumCount += item.isNum;
        isSdNumCount += item.isSdNum;
        isFaNumCoun += item.isFaNum;
        var isNumTd = $("<td></td>").html(item.isNum);
        var isSdNumTd = $("<td></td>").html(item.isSdNum);
        var isFaNumTd = $("<td></td>").html(item.isFaNum);
        var doubleXwTd = $("<td></td>").html(" ");
        var muchXwTd = $("<td></td>").html(" ");
        var lbNumTd = $("<td></td>").html(" ");
        var updateIsInfoNumTd = $("<td></td>").html(" ");
        var updateIsSdNumTd = $("<td></td>").html(" ");
        var isSelfNumTd = $("<td></td>").html(" ");
        $("<tr></tr>").append(ddNameTd).append(isNumTd).append(isSdNumTd).append(
            isFaNumTd).append(doubleXwTd).append(muchXwTd).append(lbNumTd).append(updateIsInfoNumTd).append(
            updateIsSdNumTd).append(isSelfNumTd).appendTo("#dd-list-table tbody");
    });
    var ddNameCountTd = $("<th></th>").append("合计");
    var isNumCountTd = $("<td></td>").html(isNumCount);
    var isSdNumCountTd = $("<td></td>").html(isSdNumCount);
    var isFaNumCountTd = $("<td></td>").html(isFaNumCoun);
    var doubleXwCountTd = $("<td></td>").html(" ");
    var muchXwCountTd = $("<td></td>").html(" ");
    var lbNumCountTd = $("<td></td>").html(" ");
    var updateIsInfoNumCountTd = $("<td></td>").html(" ");
    var updateIsSdNumCountTd = $("<td></td>").html(" ");
    var isSelfNumCountTd = $("<td></td>").html(" ");
    $("<tr></tr>").append(ddNameCountTd).append(isNumCountTd).append(isSdNumCountTd).append(
        isFaNumCountTd).append(doubleXwCountTd).append(muchXwCountTd).append(lbNumCountTd).append(updateIsInfoNumCountTd).append(
        updateIsSdNumCountTd).append(isSelfNumCountTd).appendTo("#dd-list-table tbody");
}