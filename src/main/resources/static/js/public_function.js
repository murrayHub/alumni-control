// 判断字符串/数组/对象/不为空时返回true
function isNotNull(obj) {
    if (Array.isArray(obj) && obj.length !== 0) {
        return true;
    }
    if (obj instanceof Object) {
        if (Object.keys(obj).length !== 0) {
            return true;
        }
        return false;
    }
    return (
        typeof obj !== 'undefined' &&
        obj !== null &&
        (Array.isArray(obj) ? obj.length !== 0 : obj !== '')
    );
}