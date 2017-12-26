/**
 * 网址管理初始化
 */
var Website = {
    id: "WebsiteTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Website.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Website.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Devops.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Website.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加网址
 */
Website.openAddWebsite = function () {
    var index = layer.open({
        type: 2,
        title: '添加网址',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Devops.ctxPath + '/website/website_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看网址详情
 */
Website.openWebsiteDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '网址详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Devops.ctxPath + '/website/website_update/' + Website.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除网址
 */
Website.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Devops.ctxPath + "/website/delete", function (data) {
            Devops.success("删除成功!");
            Website.table.refresh();
        }, function (data) {
            Devops.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("websiteId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询网址列表
 */
Website.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Website.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Website.initColumn();
    var table = new BSTable(Website.id, "/website/list", defaultColunms);
    table.setPaginationType("client");
    Website.table = table.init();
});
