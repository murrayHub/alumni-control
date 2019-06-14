<#assign ctx=request.contextPath/>
<meta charset="UTF-8">
<link href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" rel="icon"
      type="image/x-icon">
<script>
    var ctx = '${ctx!}';
</script>
<link rel="stylesheet" href="${ctx}/static/css/semantic.min.css"/>
<link rel="stylesheet" href="${ctx}/static/css/swiper.min.css">
<link rel="stylesheet" href="${ctx}/static/js/swiper/swiper.min.css">
<link rel="stylesheet" href="${ctx}/static/css/header.css">
<link rel="stylesheet" href="${ctx}/static/css/login.css">
<link rel="stylesheet" href="${ctx}/static/css/style.css"/>
<script src="${ctx}/static/js/vendor/jquery.js"></script>
<script src="${ctx}/static/js/vendor/semantic.min.js"></script>
<script src="${ctx}/static/services/api.js"></script>
<script src="${ctx}/static/services/request.js"></script>
<#if (Request.env)?exists && Request.env=='prod'>
<#--  开发环境  -->
<script src="${ctx}/static/js/vue.min.js"></script>
<#else >
<script src="${ctx}/static/js/vue.js"></script>
</#if>
<#--  线上环境  -->
<#--  <script src="${ctx}/static/js/vue.min.js"></script>  -->
<script src="${ctx}/static/js/axios.min.js"></script>
<script src="${ctx}/static/js/utils.js"></script>
<script src="${ctx}/static/js/vendor/swiper.min.js"></script>
<script src="${ctx}/static/js/swiper/swiper.min.js"></script>
<script src="${ctx}/static/element-ui/index.js"></script>
<link rel="stylesheet" href="${ctx}/static/element-ui/index.css" />
<script src="${ctx}/static/element-ui/en.js"></script>
<!-- index.less是最大的入口less文件，所有的模块less文件都在这里引入。必须在less.js之上 -->
<link rel="stylesheet/less" type="text/css" href="${ctx}/static/css/index.less" />
<script src="${ctx}/static/js/less.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/number-precision.js" type="text/javascript"></script>
<script>
  ELEMENT.locale(ELEMENT.lang.en)
</script>

<#if Request.commonParam?exists>
    <script defer="true" src="${Request.commonParam.customerServiceLink}?userid=${Request.commonParam.userId}"></script>
</#if>


