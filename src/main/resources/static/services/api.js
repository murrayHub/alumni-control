
// 国家列表
function navigateHome() {
    let url = '/root/get-data'
    return requestHeaderGet(url)
}
function getAlumniInfos(data) {
    var url = '/audit/get-alumni-infos';
    return requestHeaderPost(url, data)
}

  //暴露行为
//   window.detailModule = { getGoodsDetail, goodsSpec }
// })(window)
