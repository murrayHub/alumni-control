
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
function levelOneUpdateFirstAudit(data) {
    var url = '/audit/level-one-update-first-audit';
    return requestHeaderPost(url, data)
}
function levelOneUpdateAudit(data) {
    var url = '/audit/level-one-update-audit';
    return requestHeaderPost(url, data)
}
function getLevelOneIdentifyInfoDetail(data) {
    var url = '/audit/get-level-one-identify-info-detail';
    return requestHeaderPost(url, data)
}
function getLevelOneIdentifyInfo(data) {
    var url = '/audit/get-level-one-identify-info';
    return requestHeaderPost(url, data)
}
function getColleges(data) {
    var url = '/base/get-colleges';
    return requestHeaderPost(url, data)
}
function getInstitute(data) {
    var url = '/base/get-institute';
    return requestHeaderPost(url, data)
}
function getProvinces() {
    var url = '/cache/get-provinces';
    return requestHeaderGet(url)
}
function getLabels() {
    var url = '/cache/get-labels';
    return requestHeaderGet(url)
}
function getCities(paramVal) {
    var url = '/cache/get-cities/'+paramVal;
    return requestGetPathVariable(url)
}
function getLevelOneIndustry() {
    var url = '/cache/get-levelOneIndustry';
    return requestHeaderGet(url)
}
function getLevelTwoIndustry(paramVal) {
    var url = '/cache/get-levelTwoIndustry/'+paramVal;
    return requestGetPathVariable(url)
}
function registerSubmit(data) {
    var url = '/base/register-submit';
    return requestHeaderPost(url, data)
}
function registerSpecSubmit(data) {
    var url = '/base/register-spec-submit';
    return requestHeaderPost(url, data)
}
function loginSubmit(data) {
    var url = '/base/login';
    return requestHeaderPost(url, data)
}

function alumniDetail(data) {
    var url = '/audit/alumni-manage-detail';
    return requestHeaderGet(url, data)
}
  //暴露行为
//   window.detailModule = { getGoodsDetail, goodsSpec }
// })(window)
