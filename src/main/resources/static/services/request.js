function requestGet(url) {
    return axios.get(url)
        .then(function (response) {
            return response;
        })
        .catch(function (error) {
            console.log(error);
        });
}

function requestPost(url, data) {
    return axios.post(
        url,
        data,
    )
        .then(function (response) {
            return response;
        })
        .catch(function (error) {
            console.log(error);
        });
}

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