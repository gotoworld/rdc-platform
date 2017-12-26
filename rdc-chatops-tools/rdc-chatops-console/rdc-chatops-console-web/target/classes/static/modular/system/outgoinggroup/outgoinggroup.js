/**
 * 角色管理的单例
 */
var Outgoinggroup = {
    id: "outgoinggroupTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Outgoinggroup.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: false},
        {title: 'Group', field: 'gitlabGroupName', align: 'center', valign: 'middle', sortable: true},
        {title: 'IM类型', field: 'imType', align: 'center', valign: 'middle', sortable: true},
        {title: '外发URL', field: 'imUrl', align: 'center', valign: 'middle', sortable: false},
        {title: '说明', field: 'description', align: 'center', valign: 'middle', sortable: false}
        ]
    return columns;
};


/**
 * 检查是否选中
 */
Outgoinggroup.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Devops.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Outgoinggroup.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加管理员
 */
Outgoinggroup.openAddOutgoinggroup = function () {
    var index = layer.open({
        type: 2,
        title: '添加角色',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Devops.ctxPath + '/outgoinggroup/outgoinggroup_add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 */
Outgoinggroup.openChangeOutgoinggroup = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改角色',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Devops.ctxPath + '/outgoinggroup/outgoinggroup_edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除角色
 */
Outgoinggroup.delOutgoinggroup = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Devops.ctxPath + "/outgoinggroup/remove", function () {
                Devops.success("删除成功!");
                Outgoinggroup.table.refresh();
            }, function (data) {
                Devops.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("outgoinggroupId", Outgoinggroup.seItem.id);
            ajax.start();
        };

        Devops.confirm("是否删除角色 " + Outgoinggroup.seItem.name + "?",operation);
    }
};

/**
 * 搜索角色
 */
Outgoinggroup.search = function () {
    var queryData = {};
    queryData['outgoinggroupName'] = $("#outgoinggroupName").val();
    Outgoinggroup.table.refresh({query: queryData});
}

$(function () {
    var defaultColunms = Outgoinggroup.initColumn();
    var table = new BSTable(Outgoinggroup.id, "/outgoinggroup/list", defaultColunms);
    table.setPaginationType("server");
//    table.setPaginationType("client");
    table.init();
    Outgoinggroup.table = table;
});
