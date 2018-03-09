/**
 * Created by user on 2018/1/25.
 */
// 根据参数名称获取value
function getUrlParameter(paramKey) {
    var sURLVariables, i, sParameterName, sPageURL = window.location.search.substring(1);
    console.log(sPageURL)
    if (sPageURL) {
        sURLVariables = sPageURL.split("&");
        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split("=");
            if (sParameterName[0] === paramKey) return decodeURI(sParameterName[1],"utf-8")
        }
    }
}