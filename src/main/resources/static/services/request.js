function requestGetPathVariable(url) {
    return axios({
        method: 'get',
        url: url,
        headers: {'Authorization': localStorage.getItem('Authorization')}
    }).then(function (response) {
        return response;
    })
        .catch(function (error) {
            console.log(error);
        });
}

function requestPost(url, data) {
    return axios.post(
        url,
        data
    )
        .then(function (response) {
            return response;
        })
        .catch(function (error) {
            console.log(error);
        });
}
// 带token令牌请求
function requestHeaderPost(url, data) {
    return axios({
        method: 'post',
        url: url,
        data: data,
        headers: {'Authorization': localStorage.getItem('Authorization')}
    }).then(function (response) {
            return response;
        })
        .catch(function (error) {
            console.log(error);
        });
}
// 带token令牌请求
function requestHeaderGet(url) {
    return axios({
        method: 'get',
        url: url,
        headers: {'Authorization': localStorage.getItem('Authorization')}
    }).then(function (response) {
            return response;
        })
        .catch(function (error) {
            console.log(error);
        });
}