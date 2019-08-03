<#assign ctx=request.contextPath/>
<meta charset="UTF-8">
<script>
    var ctx = '${ctx!}';
    // Mand-mobile  全局变量引入
    window['mand-mobile'];
    // 为避免浏览器兼容问题引起的点击问题, 推荐引入FastClick
    if ('addEventListener' in document && 'ontouchstart' in window) {
        FastClick.prototype.focus = function (targetElement) {
            targetElement.focus()
        }
        document.addEventListener('DOMContentLoaded', function () {
            FastClick.attach(document.body)
        }, false)
    }
</script>
<link rel="stylesheet" href="${ctx}/static/js/swiper/swiper.min.css">
<script src="${ctx}/static/js/vendor/jquery.js"></script>
<script src="${ctx}/static/js/vendor/semantic.min.js"></script>
<script src="${ctx}/static/services/api.js"></script>
<script src="${ctx}/static/services/request.js"></script>
<script src="${ctx}/static/js/public_function.js"></script>
<#if (Request.env)?exists && Request.env=='prod'>
<#--  开发环境  -->
<script src="${ctx}/static/js/vue.min.js"></script>
<#else >
<#--  线上环境  -->
<script src="${ctx}/static/js/vue.js"></script>
</#if>
<script src="${ctx}/static/js/axios.min.js"></script>
<script src="${ctx}/static/js/utils.js"></script>
<script src="${ctx}/static/js/vendor/swiper.min.js"></script>
<script src="${ctx}/static/js/swiper/swiper.min.js"></script>
<#--  Element-UI  -->
<script src="${ctx}/static/element-ui/index.js"></script>
<link rel="stylesheet" href="${ctx}/static/element-ui/index.css" />
<script src="${ctx}/static/element-ui/en.js"></script>
<script src="${ctx}/static/element-ui/zh.js"></script>
<#--  Mand-mobile  -->
<script src="${ctx}/static/mand-mobile/fastclick.js"></script>
<script src="${ctx}/static/mand-mobile/mand-mobile.esm.js"></script>
<script src="${ctx}/static/mand-mobile/mand-mobile.umd.js"></script>
<script src="${ctx}/static/mand-mobile/vee-validate.js"></script>
<link rel="stylesheet" href="${ctx}/static/mand-mobile/mand-mobile.variable.css" />
<link rel="stylesheet" href="${ctx}/static/mand-mobile/mand-mobile.css" />
<!-- 为标准化浏览器元素的样式，推荐引入Normalize.css -->
<link rel="stylesheet" href="${ctx}/static/mand-mobile/normalize.css" />
<!-- index.less是最大的入口less文件，所有的模块less文件都在这里引入。必须在less.js之上 -->
<link rel="stylesheet/less" type="text/css" href="${ctx}/static/css/index.less" />
<script src="${ctx}/static/js/less.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/number-precision.js" type="text/javascript"></script>
<!-- Element-UI在线开发文档： https://element.eleme.cn/2.0/#/zh-CN/component/layout -->
<script>
    ELEMENT.locale(ELEMENT.lang.zhCN);
</script>


