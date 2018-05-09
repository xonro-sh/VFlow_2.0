var userId;
var userInfoForm;
$(function () {
    toastr.options = {
        closeButton: true,
        positionClass: "toast-bottom-full-width"
    };
    userId = getUrlParameter("id");
    var userInfo = getUserInfoByUserId(userId);
    console.log(userInfo);
    userInfoForm = new Vue({
        el: "#user_info_form",
        data: {
            userId: userInfo.userId,
            tenantId: userInfo.tenantId,
            tel: userInfo.tel,
            mobile: userInfo.mobile,
            idCardName: userInfo.idCardName,
            idCardNo: userInfo.idCardNo,
            birthDate: userInfo.birthDate,
            sex: userInfo.sex,
            nation: userInfo.nation,
            idCardAddress: userInfo.idCardAddress,
            address: userInfo.address,
            politicalStatus: userInfo.politicalStatus,
            maritalStatus: userInfo.maritalStatus,
            education: userInfo.education,
            university: userInfo.university,
            major: userInfo.major,
            graduationTime: userInfo.graduationTime,
            remark: userInfo.remark
        },
        methods: {
            save: function () {
                saveUserInfo(JSON.stringify(this.$data));
            }
        }
    });
});

function saveUserInfo(data) {
    $.ajax({
        url: "../../user/info_save",
        type: "post",
        dataType: "json",
        contentType:"application/json",
        data: data
        ,async: false,
        success: function (data) {
            console.log(data);
            if ('ok' in data&&!data.ok){
                toastr.warning("保存失败，错误信息"+data.msg, {timeOut: 2000});
            } else {
                toastr.success("保存成功！", {timeOut: 2000});
            }
        },
        error : function (data) {
            toastr.warning("保存失败，错误信息"+data.msg, {timeOut: 2000});
        }
    });
}