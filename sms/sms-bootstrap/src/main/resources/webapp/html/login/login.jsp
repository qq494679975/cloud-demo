<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>登录</title>
    <link href="/common/css/bootstrap/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="/common/css/bootstrap/font-awesome.min.css?v=4.3.0" rel="stylesheet">

    <link href="/common/css/bootstrap/animate.min.css" rel="stylesheet">
    <link href="/common/css/bootstrap/style.min.css?v=3.0.0" rel="stylesheet">

</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name">Login</h1>

        </div>
        <h3>欢迎</h3>

        <form class="m-t" role="form"  id="loginForm" onsubmit="return login()">
            <div class="form-group">
                <input id="username" name="username" type="text" class="form-control" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input id="password" name="password"  type="password" class="form-control" placeholder="密码" required="">
            </div>
            <button id="loginBtn" type="submit" class="btn btn-primary block full-width m-b">登 录</button>

            <p class="text-muted text-center">
                <a href="login.html#"><small>忘记密码了？</small></a> |
                <a href="register.html">注册一个新账号</a>
            </p>

        </form>
    </div>
</div>

<!-- 全局js -->
<script src="/common/js/jquery/jquery-2.1.1.min.js"></script>
<script src="/common/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" >
    function login(){
        var username=$("#username").val();
        var password=$("#password").val();
        $.ajax({
            type: "GET",
            url: "/login",
            data: {
                "username":username,
                "password":password
            },
            dataType: "json",
            success: function(data){
                if(data.status==200){
                    window.location.href='/index/index.jsp?u='+data.user.id;
                }else{
                    alert("账号或密码错误")
                }
            }
        });
        return false;
    }
</script>

</body>

</html>