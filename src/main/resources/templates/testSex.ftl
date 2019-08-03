<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include 'head.ftl' >
<head>
    <meta charset="UTF-8">
    <title>alumni-control</title>
</head>
<body>
<div id="RegisterSpecial" class="layout_container">
    <div class="md-example-child md-example-child-radio md-example-child-radio-0">
        <md-radio name="0" v-model="checked" label="单选项1" />
        <md-radio name="1" v-model="checked" label="单选项2" />
    </div>
</div>

</body>
</html>
<script src="${ctx}/static/modules/public_template.js"></script>
<script>
    const vm = new Vue({
        el: '#RegisterSpecial',
        components: {
        },
        data: function(){
            return {
                checked: '0',
            }
        },
        mounted: function () {
        },
        methods: {
        },
});

</script>
