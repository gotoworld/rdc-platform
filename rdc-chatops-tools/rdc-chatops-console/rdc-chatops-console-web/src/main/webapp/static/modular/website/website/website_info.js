/**
 * 初始化网址详情对话框
 */
var WebsiteInfoDlg = {
    websiteInfoData : {}
};

/**
 * 清除数据
 */
WebsiteInfoDlg.clearData = function() {
    this.websiteInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WebsiteInfoDlg.set = function(key, val) {
    this.websiteInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WebsiteInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WebsiteInfoDlg.close = function() {
    parent.layer.close(window.parent.Website.layerIndex);
}

/**
 * 收集数据
 */
WebsiteInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
WebsiteInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Devops.ctxPath + "/website/add", function(data){
        Devops.success("添加成功!");
        window.parent.Website.table.refresh();
        WebsiteInfoDlg.close();
    },function(data){
        Devops.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.websiteInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WebsiteInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Devops.ctxPath + "/website/update", function(data){
        Devops.success("修改成功!");
        window.parent.Website.table.refresh();
        WebsiteInfoDlg.close();
    },function(data){
        Devops.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.websiteInfoData);
    ajax.start();
}

$(function() {

});
