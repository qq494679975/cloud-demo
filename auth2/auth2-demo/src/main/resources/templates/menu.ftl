<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta charset="utf-8">
    <title>OAuth Login</title>
    <script type="text/javascript" src="/js/jquery.min.js"  />
</head>
<body>
<h2 class="page-header">OAuth 5种模式</h2>
<div>
    <table class="table table-bordered">
        <tbody>
        <tr>
            <td>授权码模式</td>
        </tr>
        <tr>
            <td>密码模式</td>
            <td>账号:<input id="password_model_username"/></td>
            <td>密码:<input id="password_model_password"/></td>
            <td>
                <div id="password_model">获取accecc_token</div>
            </td>
            <td>
                <div id="password_model_return"></div>
            </td>
        </tr>
        <tr>
            <td>简化模式</td>
        </tr>
        <tr>
            <td>客户端模式</td>
        </tr>
        <tr>
            <td>刷新access_token</td>
        </tr>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    //    $(function () {
    //        //密码模式
    //        $("#password_model").click(function () {
    //            $.ajax({
    //                url: '/oauth/rest_token',
    //                type: 'POST', //GET
    //                async: true,    //或false,是否异步
    //                data: JSON.stringify({
    //                    "client_id": "mobile-client",
    //                    "grant_type": "password",
    //                    "username": "admin",
    //                    "password": "admin"
    //                }),
    //                dataType: 'json',
    //                success: function (data, textStatus, jqXHR) {
    //                    $("#password_model_return").text(data);
    //                }
    //            })
    //        })
    //    });
</script>
</body>
</html>