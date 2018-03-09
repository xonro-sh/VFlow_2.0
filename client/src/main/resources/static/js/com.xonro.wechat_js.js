/**
 * Created by Alex on 2018/1/9.
 */
/**
 * 微信公众平台相关js
 * Created by louie on 2017-11-16.
 */

// function getOauthorToken() {
//     var oauthorToken;
//     var userCode = getUrlParameter("code");
//     $.ajax({
//         url: "../wechat/snsToken/" + userCode,
//         type: "post",
//         dataType: "json",
//         async: false,
//         success: function (res) {
//             oauthorToken = res;
//         },
//         error: function (res) {
//             console.log(res);
//         }
//     });
//     return oauthorToken;
// }

/**
 * 获取微信js-sdk认证签名
 * @type {{}}
 */
var js_signature = {};
$.ajax({
    url: "../wechat/jsSignature/",
    data: {
        rquestPage: encodeURIComponent(this.window.location)
    },
    type: "POST",
    async: false,
    dataType: "json",
    success: function (res) {
        js_signature = res;
        console.log( js_signature);
    },
    error: function (res) {
        console.log("error");
    }
});

/**
 * 配置微信环境，获取js-sdk使用授权
 */
wx.config({
    debug: true,
    appId: js_signature.appId,
    timestamp: js_signature.timestamp,
    nonceStr: js_signature.nonceStr,
    signature: js_signature.signature,
    jsApiList: [
        'checkJsApi',
        'openLocation',
        'getLocation',"onMenuShareTimeline", "onMenuShareAppMessage", "onMenuShareQQ", "onMenuShareWeibo", "onMenuShareQZone", "chooseWXPay"
    ]
});

wx.ready(function () {
    console.log("wechat config is ok");
    wx.checkJsApi({
        jsApiList: [
            'getLocation',
            'onMenuShareTimeline',
            'onMenuShareAppMessage'
        ],
        success: function (res) {
            // alert(JSON.stringify(res));
        }
    });
});

wx.error(function () {
    console.log("wechat confige error");
});