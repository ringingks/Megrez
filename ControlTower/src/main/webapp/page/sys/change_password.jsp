<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CHANGE YOUR PASSWORD</title>
    <script src="../../depot/SDK/layui/layui.js"></script>
    <link rel="stylesheet" href="../../depot/SDK/layui/css/layui.source.css" media="all">
    <style>
        .form-label{
            width: 210px !important;
            text-align: left !important;
        }
        .form-input{
            width: 310px !important;
        }
        .form-position{
            padding-left: 20px;
        }
    </style>
</head>
<body>

<ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px" data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
    <legend>CHANGE YOUR PASSWORD</legend>
</fieldset>

<form class="layui-form layui-form-pane form-position" action="">

    <div class="layui-form-item">
        <label class="layui-form-label form-label">USERNAME</label>
        <div class="layui-input-inline form-input">
            <input type="text" name="username" lay-verify="required" placeholder='<%=request.getAttribute("username")%>' autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label form-label">CURRENT PASSWORD</label>
        <div class="layui-input-inline form-input">
            <input type="password" name="password" lay-verify="pass" autocomplete="off" placeholder="" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label form-label">NEW PASSWORD</label>
        <div class="layui-input-inline form-input">
            <input type="password" name="newPassword" lay-verify="pass" placeholder="" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">Please fill in 6 to 12 ciphers </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label form-label">REPEAT NEW PASSWORD</label>
        <div class="layui-input-inline form-input">
            <input type="password" name="repeat_new_password" lay-verify="pass" placeholder="" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">Please fill in 6 to 12 ciphers </div>
    </div>

</form>


</body>
</html>