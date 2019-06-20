
// 国家列表
function navigateHome() {
    let url = '/root/get-data'
    return requestHeaderGet(url)
}
function getAlumniInfos(data) {
    var url = '/audit/get-alumni-infos';
    return requestHeaderPost(url, data)
}
function getAlumniInfo(data) {
    var url = '/audit/get-alumni-info';
    return requestHeaderPost(url, data)
}
function levelTwoIdentifyUpdate(data) {
    var url = '/audit/level-two-identify-update';
    return requestHeaderPost(url, data)
}
function levelTwoUpdateAudit(data) {
    var url = '/audit/level-two-update-audit';
    return requestHeaderPost(url, data)
}
function levelOneIdentifyUpdate(data) {
    var url = '/audit/level-one-identify-update';
    return requestHeaderPost(url, data)
}
function levelOneUpdateAudit(data) {
    var url = '/audit/level-one-update-audit';
    return requestHeaderPost(url, data)
}

  //暴露行为
//   window.detailModule = { getGoodsDetail, goodsSpec }
// })(window)
