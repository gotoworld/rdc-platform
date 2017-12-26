/**
 * outgoinggroup详情对话框（可用于添加和修改对话框）
 */
var OutgoinggroupInfoDlg = {
    outgoinggroupInfoData: {},
    deptZtree: null,
    pNameZtree: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                }
            }
        }
    		,
    	gitlabGroupName: {
            validators: {
                notEmpty: {
                    message: 'group不能为空'
                }
            }
        }
         ,
         imType: {
    			validators: {
    				notEmpty: {
    					message: 'imType不能为空'
    				}
    			}
    		}
    	,
    	imUrl: {
            validators: {
                notEmpty: {
                    message: 'imUrl不能为空'
                }
            }
        }
    }
};


/**
 * 清除数据
 */
OutgoinggroupInfoDlg.clearData = function () {
    this.outgoinggroupInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OutgoinggroupInfoDlg.set = function (key, val) {
    this.outgoinggroupInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OutgoinggroupInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
OutgoinggroupInfoDlg.close = function () {
    parent.layer.close(window.parent.Role.layerIndex);
};


/**
 * 收集数据
 */
OutgoinggroupInfoDlg.collectData = function () {
    this.set('id').set('name').set('gitlabGroupName').set('description').set('imType').set('imUrl');
};


/**
 * 验证数据是否为空
 */
OutgoinggroupInfoDlg.validate = function () {
    $('#outgoinggroupInfoForm').data("bootstrapValidator").resetForm();
    $('#outgoinggroupInfoForm').bootstrapValidator('validate');
    return $("#outgoinggroupInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加outgoinggroup
 */
OutgoinggroupInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Devops.ctxPath + "/outgoinggroup/add", function (data) {
        Devops.success("添加成功!");
        window.parent.Role.table.refresh();
        OutgoinggroupInfoDlg.close();
    }, function (data) {
        Devops.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.outgoinggroupInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
OutgoinggroupInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Devops.ctxPath + "/outgoinggroup/edit", function (data) {
        Devops.success("修改成功!");
        window.parent.Role.table.refresh();
        OutgoinggroupInfoDlg.close();
    }, function (data) {
        Devops.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.outgoinggroupInfoData);
    ajax.start();
};

$(function () {
    Devops.initValidator("outgoinggroupInfoForm", OutgoinggroupInfoDlg.validateFields);

});
