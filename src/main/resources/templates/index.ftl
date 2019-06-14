<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <title></title>
</head>
<body>
<form id="uploadForm" enctype="multipart/form-data">
    文件:<input id="file" type="file" name="file" multiple="multiple"/>
</form>
<button id="upload">上传文件</button>
</body>
<script type="text/javascript">
    $(function () {
        $("#upload").click(function () {
            var formData = new FormData($('#uploadForm')[0]);
            $.ajax({
                type: 'post',
                url: "/upload/setFileUpload",
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
            }).success(function (data) {
                if (data.code == 1000){
                    alert("上传成功");
                }else {
                    alert(data.message);
                }
            }).error(function () {
                alert("上传失败");
            });
        });
    });
</script>
</html>