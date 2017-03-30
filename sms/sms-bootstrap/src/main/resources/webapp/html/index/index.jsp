<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>H+ 后台主题UI框架 - 主页</title>

    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <!--[if lt IE 8]>
    <script>
        alert('H+已不支持IE6-8，请使用谷歌、火狐等浏览器\n或360、QQ等国产浏览器的极速模式浏览本页面！');
    </script>
    <![endif]-->
    <link href="/common/css/bootstrap/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="/common/css/bootstrap/font-awesome.min.css?v=4.3.0" rel="stylesheet">

    <link href="/common/css/bootstrap/animate.min.css" rel="stylesheet">
    <link href="/common/css/bootstrap/style.min.css?v=3.0.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span><img alt="image" class="img-circle" src="img/profile_small.jpg"/></span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">Beaut-zihan</strong></span>
                                <span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
                                </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a class="J_menuItem" href="form_avatar.html">修改头像</a>
                            </li>
                            <li><a class="J_menuItem" href="profile.html">个人资料</a>
                            </li>
                            <li><a class="J_menuItem" href="contacts.html">联系我们</a>
                            </li>
                            <li><a class="J_menuItem" href="mailbox.html">信箱</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="login.html">安全退出</a>
                            </li>
                        </ul>
                    </div>
                    <div class="logo-element">H+
                    </div>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-home"></i>
                        <span class="nav-label">主页</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="" data-index="0">主页示例一</a>
                        </li>
                    </ul>

                </li>


            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i
                        class="fa fa-bars"></i> </a>

                    <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                        <div class="form-group">
                            <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search"
                                   id="top-search">
                        </div>
                    </form>
                </div>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <button class="roll-nav roll-right dropdown J_tabClose"><span class="dropdown-toggle"
                                                                          data-toggle="dropdown">关闭操作<span
                    class="caret"></span></span>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </button>
            <a href="login.html" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="index_v1.html?v=3.0" frameborder="0"
                    data-id="index_v1.html" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">&copy; 2014-2015 <a href="/" target="_blank">zihan's blog</a>
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
    <!--右侧边栏开始-->
    <div id="right-sidebar">
        <div class="sidebar-container">

            <ul class="nav nav-tabs navs-3">

                <li class="active"><a data-toggle="tab" href="#tab-1">
                    通知
                </a>
                </li>
                <li><a data-toggle="tab" href="#tab-2">
                    项目进度
                </a>
                </li>
                <li class="">
                    <a data-toggle="tab" href="#tab-3">
                        <i class="fa fa-gear"></i>
                    </a>
                </li>
            </ul>

            <div class="tab-content">


                <div id="tab-1" class="tab-pane active">

                    <div class="sidebar-title">
                        <h3><i class="fa fa-comments-o"></i> 最新通知</h3>
                        <small><i class="fa fa-tim"></i> 您当前有10条未读信息</small>
                    </div>

                    <div>

                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="img/a1.jpg">

                                    <div class="m-t-xs">
                                        <i class="fa fa-star text-warning"></i>
                                        <i class="fa fa-star text-warning"></i>
                                    </div>
                                </div>
                                <div class="media-body">

                                    据天津日报报道：瑞海公司董事长于学伟，副董事长董社轩等10人在13日上午已被控制。
                                    <br>
                                    <small class="text-muted">今天 4:21</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="img/a2.jpg">
                                </div>
                                <div class="media-body">
                                    HCY48之音乐大魔王会员专属皮肤已上线，快来一键换装拥有他，宣告你对华晨宇的爱吧！
                                    <br>
                                    <small class="text-muted">昨天 2:45</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="img/a3.jpg">

                                    <div class="m-t-xs">
                                        <i class="fa fa-star text-warning"></i>
                                        <i class="fa fa-star text-warning"></i>
                                        <i class="fa fa-star text-warning"></i>
                                    </div>
                                </div>
                                <div class="media-body">
                                    写的好！与您分享
                                    <br>
                                    <small class="text-muted">昨天 1:10</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="img/a4.jpg">
                                </div>

                                <div class="media-body">
                                    国外极限小子的炼成！这还是亲生的吗！！
                                    <br>
                                    <small class="text-muted">昨天 8:37</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="img/a8.jpg">
                                </div>
                                <div class="media-body">

                                    一只流浪狗被收留后，为了减轻主人的负担，坚持自己觅食，甚至......有些东西，可能她比我们更懂。
                                    <br>
                                    <small class="text-muted">今天 4:21</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="img/a7.jpg">
                                </div>
                                <div class="media-body">
                                    这哥们的新视频又来了，创意杠杠滴，帅炸了！
                                    <br>
                                    <small class="text-muted">昨天 2:45</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="img/a3.jpg">

                                    <div class="m-t-xs">
                                        <i class="fa fa-star text-warning"></i>
                                        <i class="fa fa-star text-warning"></i>
                                        <i class="fa fa-star text-warning"></i>
                                    </div>
                                </div>
                                <div class="media-body">
                                    最近在补追此剧，特别喜欢这段表白。
                                    <br>
                                    <small class="text-muted">昨天 1:10</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="img/a4.jpg">
                                </div>
                                <div class="media-body">
                                    我发起了一个投票 【你认为下午大盘会翻红吗？】
                                    <br>
                                    <small class="text-muted">星期一 8:37</small>
                                </div>
                            </a>
                        </div>
                    </div>

                </div>

                <div id="tab-2" class="tab-pane">

                    <div class="sidebar-title">
                        <h3><i class="fa fa-cube"></i> 最新任务</h3>
                        <small><i class="fa fa-tim"></i> 您当前有14个任务，10个已完成</small>
                    </div>

                    <ul class="sidebar-list">
                        <li>
                            <a href="#">
                                <div class="small pull-right m-t-xs">9小时以后</div>
                                <h4>市场调研</h4> 按要求接收教材；

                                <div class="small">已完成： 22%</div>
                                <div class="progress progress-mini">
                                    <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                </div>
                                <div class="small text-muted m-t-xs">项目截止： 4:00 - 2015.10.01</div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="small pull-right m-t-xs">9小时以后</div>
                                <h4>可行性报告研究报上级批准 </h4> 编写目的编写本项目进度报告的目的在于更好的控制软件开发的时间,对团队成员的 开发进度作出一个合理的比对

                                <div class="small">已完成： 48%</div>
                                <div class="progress progress-mini">
                                    <div style="width: 48%;" class="progress-bar"></div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="small pull-right m-t-xs">9小时以后</div>
                                <h4>立项阶段</h4> 东风商用车公司 采购综合综合查询分析系统项目进度阶段性报告武汉斯迪克科技有限公司

                                <div class="small">已完成： 14%</div>
                                <div class="progress progress-mini">
                                    <div style="width: 14%;" class="progress-bar progress-bar-info"></div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-primary pull-right">NEW</span>
                                <h4>设计阶段</h4>
                                <!--<div class="small pull-right m-t-xs">9小时以后</div>-->
                                项目进度报告(Project Progress Report)
                                <div class="small">已完成： 22%</div>
                                <div class="small text-muted m-t-xs">项目截止： 4:00 - 2015.10.01</div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="small pull-right m-t-xs">9小时以后</div>
                                <h4>拆迁阶段</h4> 科研项目研究进展报告 项目编号: 项目名称: 项目负责人:

                                <div class="small">已完成： 22%</div>
                                <div class="progress progress-mini">
                                    <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                </div>
                                <div class="small text-muted m-t-xs">项目截止： 4:00 - 2015.10.01</div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="small pull-right m-t-xs">9小时以后</div>
                                <h4>建设阶段 </h4> 编写目的编写本项目进度报告的目的在于更好的控制软件开发的时间,对团队成员的 开发进度作出一个合理的比对

                                <div class="small">已完成： 48%</div>
                                <div class="progress progress-mini">
                                    <div style="width: 48%;" class="progress-bar"></div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="small pull-right m-t-xs">9小时以后</div>
                                <h4>获证开盘</h4> 编写目的编写本项目进度报告的目的在于更好的控制软件开发的时间,对团队成员的 开发进度作出一个合理的比对

                                <div class="small">已完成： 14%</div>
                                <div class="progress progress-mini">
                                    <div style="width: 14%;" class="progress-bar progress-bar-info"></div>
                                </div>
                            </a>
                        </li>

                    </ul>

                </div>

                <div id="tab-3" class="tab-pane">

                    <div class="sidebar-title">
                        <h3><i class="fa fa-gears"></i> 设置</h3>
                    </div>

                    <div class="setings-item">
                            <span>
                        显示通知
                    </span>

                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example">
                                <label class="onoffswitch-label" for="example">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="setings-item">
                            <span>
                        隐藏聊天窗口
                    </span>

                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" name="collapsemenu" checked class="onoffswitch-checkbox"
                                       id="example2">
                                <label class="onoffswitch-label" for="example2">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="setings-item">
                            <span>
                        清空历史记录
                    </span>

                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example3">
                                <label class="onoffswitch-label" for="example3">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="setings-item">
                            <span>
                        显示聊天窗口
                    </span>

                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example4">
                                <label class="onoffswitch-label" for="example4">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="setings-item">
                            <span>
                        显示在线用户
                    </span>

                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" checked name="collapsemenu" class="onoffswitch-checkbox"
                                       id="example5">
                                <label class="onoffswitch-label" for="example5">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="setings-item">
                            <span>
                        全局搜索
                    </span>

                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" checked name="collapsemenu" class="onoffswitch-checkbox"
                                       id="example6">
                                <label class="onoffswitch-label" for="example6">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="setings-item">
                            <span>
                        每日更新
                    </span>

                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example7">
                                <label class="onoffswitch-label" for="example7">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="sidebar-content">
                        <h4>设置</h4>

                        <div class="small">
                            你可以从这里设置一些常用选项，当然啦，这个只是个演示的示例。
                        </div>
                    </div>

                </div>
            </div>

        </div>


    </div>
    <!--右侧边栏结束-->
    <!--mini聊天窗口开始-->
    <div class="small-chat-box fadeInRight animated">

        <div class="heading" draggable="true">
            <small class="chat-date pull-right">
                2015.9.1
            </small>
            与 Beau-zihan 聊天中
        </div>

        <div class="content">

            <div class="left">
                <div class="author-name">
                    Beau-zihan
                    <small class="chat-date">
                        10:02
                    </small>
                </div>
                <div class="chat-message active">
                    你好
                </div>

            </div>
            <div class="right">
                <div class="author-name">
                    游客
                    <small class="chat-date">
                        11:24
                    </small>
                </div>
                <div class="chat-message">
                    你好，请问H+有帮助文档吗？
                </div>
            </div>
            <div class="left">
                <div class="author-name">
                    Beau-zihan
                    <small class="chat-date">
                        08:45
                    </small>
                </div>
                <div class="chat-message active">
                    有，购买的H+源码包中有帮助文档，位于docs文件夹下
                </div>
            </div>
            <div class="right">
                <div class="author-name">
                    游客
                    <small class="chat-date">
                        11:24
                    </small>
                </div>
                <div class="chat-message">
                    那除了帮助文档还提供什么样的服务？
                </div>
            </div>
            <div class="left">
                <div class="author-name">
                    Beau-zihan
                    <small class="chat-date">
                        08:45
                    </small>
                </div>
                <div class="chat-message active">
                    1.所有源码(未压缩、带注释版本)；
                    <br> 2.说明文档；
                    <br> 3.终身免费升级服务；
                    <br> 4.必要的技术支持；
                    <br> 5.付费二次开发服务；
                    <br> 6.授权许可；
                    <br> ……
                    <br>
                </div>
            </div>


        </div>
        <div class="form-chat">
            <div class="input-group input-group-sm">
                <input type="text" class="form-control"> <span class="input-group-btn"> <button
                    class="btn btn-primary" type="button">发送
            </button> </span>
            </div>
        </div>

    </div>
    <div id="small-chat">
        <span class="badge badge-warning pull-right">5</span>
        <a class="open-small-chat">
            <i class="fa fa-comments"></i>

        </a>
    </div>
    <!--mini聊天窗口结束-->
</div>

<!-- 全局js -->
<script src="/common/js/jquery/jquery-2.1.1.min.js"></script>
<script src="/common/js/bootstrap/bootstrap.min.js"></script>
<script src="/common/js/jquery/jquery.metisMenu.js"></script>
<script src="/common/js/jquery/jquery.slimscroll.min.js"></script>
<script src="/common/js/layer/layer.min.js"></script>
<script src="/common/js/util/util.js"></script>

<!-- 自定义js -->
<script src="/index/hplus.min.js?v=3.0.0"></script>
<script type="text/javascript" src="/index/contabs.min.js"></script>

<!-- 第三方插件 -->
<script src="/common/js/pace/pace.min.js"></script>
<div class="theme-config">
    <div class="theme-config-box">
        <div class="spin-icon">
            <i class="fa fa-cog fa-spin"></i>
        </div>
        <div class="skin-setttings">
            <div class="title">主题设置</div>
            <div class="setings-item">
                <span>
                        收起左侧菜单
                    </span>

                <div class="switch">
                    <div class="onoffswitch">
                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                        <label class="onoffswitch-label" for="collapsemenu">
                            <span class="onoffswitch-inner"></span>
                            <span class="onoffswitch-switch"></span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="setings-item">
                <span>
                        固定顶部
                    </span>

                <div class="switch">
                    <div class="onoffswitch">
                        <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                        <label class="onoffswitch-label" for="fixednavbar">
                            <span class="onoffswitch-inner"></span>
                            <span class="onoffswitch-switch"></span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="setings-item">
                <span>
                        固定宽度
                    </span>

                <div class="switch">
                    <div class="onoffswitch">
                        <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                        <label class="onoffswitch-label" for="boxedlayout">
                            <span class="onoffswitch-inner"></span>
                            <span class="onoffswitch-switch"></span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="title">皮肤选择</div>
            <div class="setings-item default-skin">
                <span class="skin-name ">
                         <a href="#" class="s-skin-0">
                             默认皮肤
                         </a>
                    </span>
            </div>
            <div class="setings-item blue-skin">
                <span class="skin-name ">
                        <a href="#" class="s-skin-1">
                            蓝色主题
                        </a>
                    </span>
            </div>
            <div class="setings-item yellow-skin">
                <span class="skin-name ">
                        <a href="#" class="s-skin-3">
                            黄色/紫色主题
                        </a>
                    </span>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#fixednavbar").click(function () {
        if ($("#fixednavbar").is(":checked")) {
            $(".navbar-static-top").removeClass("navbar-static-top").addClass("navbar-fixed-top");
            $("body").removeClass("boxed-layout");
            $("body").addClass("fixed-nav");
            $("#boxedlayout").prop("checked", false);
            if (localStorageSupport) {
                localStorage.setItem("boxedlayout", "off")
            }
            if (localStorageSupport) {
                localStorage.setItem("fixednavbar", "on")
            }
        } else {
            $(".navbar-fixed-top").removeClass("navbar-fixed-top").addClass("navbar-static-top");
            $("body").removeClass("fixed-nav");
            if (localStorageSupport) {
                localStorage.setItem("fixednavbar", "off")
            }
        }
    });
    $("#collapsemenu").click(function () {
        if ($("#collapsemenu").is(":checked")) {
            $("body").addClass("mini-navbar");
            SmoothlyMenu();
            if (localStorageSupport) {
                localStorage.setItem("collapse_menu", "on")
            }
        } else {
            $("body").removeClass("mini-navbar");
            SmoothlyMenu();
            if (localStorageSupport) {
                localStorage.setItem("collapse_menu", "off")
            }
        }
    });
    $("#boxedlayout").click(function () {
        if ($("#boxedlayout").is(":checked")) {
            $("body").addClass("boxed-layout");
            $("#fixednavbar").prop("checked", false);
            $(".navbar-fixed-top").removeClass("navbar-fixed-top").addClass("navbar-static-top");
            $("body").removeClass("fixed-nav");
            if (localStorageSupport) {
                localStorage.setItem("fixednavbar", "off")
            }
            if (localStorageSupport) {
                localStorage.setItem("boxedlayout", "on")
            }
        } else {
            $("body").removeClass("boxed-layout");
            if (localStorageSupport) {
                localStorage.setItem("boxedlayout", "off")
            }
        }
    });
    $(".spin-icon").click(function () {
        $(".theme-config-box").toggleClass("show")
    });
    $(".s-skin-0").click(function () {
        $("body").removeClass("skin-1");
        $("body").removeClass("skin-2");
        $("body").removeClass("skin-3")
    });
    $(".s-skin-1").click(function () {
        $("body").removeClass("skin-2");
        $("body").removeClass("skin-3");
        $("body").addClass("skin-1")
    });
    $(".s-skin-3").click(function () {
        $("body").removeClass("skin-1");
        $("body").removeClass("skin-2");
        $("body").addClass("skin-3")
    });
    if (localStorageSupport) {
        var collapse = localStorage.getItem("collapse_menu");
        var fixednavbar = localStorage.getItem("fixednavbar");
        var boxedlayout = localStorage.getItem("boxedlayout");
        if (collapse == "on") {
            $("#collapsemenu").prop("checked", "checked")
        }
        if (fixednavbar == "on") {
            $("#fixednavbar").prop("checked", "checked")
        }
        if (boxedlayout == "on") {
            $("#boxedlayout").prop("checked", "checked")
        }
    }

    //获取请求中的参数
    var userId=UrlParm.parm("u");
    //根据用户id初始化菜单
    $.ajax({
        type: "GET",
        url: "ajax/Handler.ashx?M=" + Math.random(),
        data: "username=" + $("#TxtUserName").val().toString() + "&pwd=" + $("#TxtPassword").val().toString(),
        success: function (data) {
            if (data == "1") {
                location.href = "index.aspx";
                return true;
            }
            else {
                alert("请确认您输入的用户名或密码输入是否正确！");
                $("#TxtUserName").val("");
                $("#TxtPassword").val("");
                $("#TxtUserName").focus();
                return false;
            }
        }

    })


</script>
<style>
    .fixed-nav .slimScrollDiv #side-menu {
        padding-bottom: 60px;
    }
</style>
<div id="HUABAN_WIDGETS">
    <div class="HUABAN-f-button" style="display: none;">采集</div>
    <style>#HUABAN_WIDGETS {
        font-family: "helvetica neue", arial, sans-serif;
        color: #444;
        font-size: 14px;
    }

    #HUABAN_WIDGETS * {
        box-sizing: content-box;
    }

    #HUABAN_WIDGETS .HUABAN-main {
        position: fixed;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        background: #e5e5e5;
        background: rgba(229, 229, 229, .95);
        max-height: 100%;
        overflow: hidden;
        z-index: 9999999999999;
    }

    #HUABAN_WIDGETS a img {
        border: 0;
    }

    #HUABAN_WIDGETS .HUABAN-header {
        height: 50px;
        background: white;
        box-shadow: 0 0 4px rgba(0, 0, 0, .2);
        width: 100%;
        left: 0;
        top: 0;
        position: absolute;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-inner {
        margin: 0 auto;
        position: relative;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-close {
        width: 60px;
        height: 50px;
        border-left: 1px solid #ddd;
        position: absolute;
        right: 0;
        top: 0;
        background: url(//huaban.com/img/widgets/btn_close.png) 20px 14px no-repeat;
        cursor: pointer;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-close:hover {
        background-position: 20px -26px;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-close:active {
        background-position: 20px -66px;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-logo {
        display: block;
        position: absolute;
        top: 12px;
    }

    #HUABAN_WIDGETS .HUABAN-waterfall-holder {
        position: relative;
        overflow-y: auto;
        height: 100%;
    }

    #HUABAN_WIDGETS .HUABAN-waterfall {
        position: relative;
        margin-top: 50px;
    }

    #HUABAN_WIDGETS .HUABAN-waterfall .HUABAN-empty {
        position: absolute;
        left: 50%;
        top: 30px;
        height: 36px;
        line-height: 36px;
        width: 216px;
        text-align: left;
        margin-left: -128px;
        color: #777;
        background: url(//huaban.com/img/widgets/icon_notice.png) 12px 8px no-repeat white;
        padding-left: 40px;
        font-size: 15px;
    }

    #HUABAN_WIDGETS .HUABAN-btn {
        display: inline-block;
        border-radius: 2px;
        font-size: 14px;
        padding: 0 12px;
        height: 30px;
        line-height: 30px;
        cursor: pointer;
        text-decoration: none;
        white-space: nowrap;
        -moz-user-select: none;
        -webkit-user-select: none;
        user-select: none;
        text-align: center;
        background: #D53939;
        color: white;
    }

    #HUABAN_WIDGETS .HUABAN-btn:hover {
        background: #E54646;
    }

    #HUABAN_WIDGETS .HUABAN-btn:active {
        background: #C52424;
    }

    #HUABAN_WIDGETS .HUABAN-wbtn {
        background: #EDEDED;
        color: #444;
    }

    #HUABAN_WIDGETS .HUABAN-wbtn:hover {
        background: #F2F2F2;
    }

    #HUABAN_WIDGETS .HUABAN-wbtn:active {
        background: #DDD;
    }

    #HUABAN_WIDGETS .HUABAN-f-button {
        position: absolute;
        display: none;
        z-index: 9999999999998;
        box-shadow: 0 0 0 2px rgba(255, 255, 255, .2);
        background: #aaa;
        background: rgba(0, 0, 0, .3);
        color: white;
        cursor: pointer;
        padding: 0 12px;
        height: 30px;
        line-height: 30px;
        border-radius: 2px;
        font-size: 14px
    }

    #HUABAN_WIDGETS .HUABAN-f-button:hover {
        background-color: #999;
        background-color: rgba(0, 0, 0, .5);
    }

    #HUABAN_WIDGETS .HUABAN-f-button:active {
        background-color: rgba(0, 0, 0, .6);
    }

    #HUABAN_WIDGETS .HUABAN-red-normal-icon-button {
        width: 36px;
        height: 24px;
        border: 0px;
        line-height: 24px;
        padding-left: 24px;
        padding-right: 0px;
        text-align: left;
        background: url(//huaban.com/img/widgets/widget_icons.png) 0 -200px no-repeat;
        box-shadow: none !important;
        font-size: 14px;
        background-color: transparent !important;
    }

    #HUABAN_WIDGETS .HUABAN-red-normal-icon-button:hover {
        background-position: -130px -200px;
    }

    #HUABAN_WIDGETS .HUABAN-red-normal-icon-button:active {
        background-position: -260px -200px;
    }

    #HUABAN_WIDGETS .HUABAN-red-large-icon-button {
        width: 80px;
        height: 24px;
        border: 0px;
        line-height: 24px;
        padding-left: 24px;
        padding-right: 0px;
        text-align: left;
        background: url(//huaban.com/img/widgets/widget_icons.png) 0 -150px no-repeat;
        box-shadow: none !important;
        font-size: 14px;
        background-color: transparent !important;
    }

    #HUABAN_WIDGETS .HUABAN-red-large-icon-button:hover {
        background-position: -130px -150px;
    }

    #HUABAN_WIDGETS .HUABAN-red-large-icon-button:active {
        background-position: -260px -150px;
    }

    #HUABAN_WIDGETS .HUABAN-red-small-icon-button {
        width: 30px;
        height: 21px;
        border: 0px;
        line-height: 21px;
        padding-left: 20px;
        padding-right: 0px;
        text-align: left;
        background: url(//huaban.com/img/widgets/widget_icons.png) 0 -250px no-repeat;
        box-shadow: none !important;
        font-size: 12px;
        background-color: transparent !important;
    }

    #HUABAN_WIDGETS .HUABAN-red-small-icon-button:hover {
        background-position: -130px -250px;
    }

    #HUABAN_WIDGETS .HUABAN-red-small-icon-button:active {
        background-position: -260px -250px;
    }

    #HUABAN_WIDGETS .HUABAN-white-normal-icon-button {
        width: 36px;
        height: 24px;
        border: 0px;
        line-height: 24px;
        padding-left: 24px;
        padding-right: 0px;
        text-align: left;
        background: url(//huaban.com/img/widgets/widget_icons.png) 0 -500px no-repeat;
        box-shadow: none !important;
        color: #444;
        font-size: 14px;
        background-color: transparent !important;
    }

    #HUABAN_WIDGETS .HUABAN-white-normal-icon-button:hover {
        background-position: -130px -500px;
    }

    #HUABAN_WIDGETS .HUABAN-white-normal-icon-button:active {
        background-position: -260px -500px;
    }

    #HUABAN_WIDGETS .HUABAN-white-large-icon-button {
        width: 80px;
        height: 24px;
        border: 0px;
        line-height: 24px;
        padding-left: 24px;
        padding-right: 0px;
        text-align: left;
        background: url(//huaban.com/img/widgets/widget_icons.png) 0 -450px no-repeat;
        box-shadow: none !important;
        color: #444;
        font-size: 14px;
        background-color: transparent !important;
    }

    #HUABAN_WIDGETS .HUABAN-white-large-icon-button:hover {
        background-position: -130px -450px;
    }

    #HUABAN_WIDGETS .HUABAN-white-large-icon-button:active {
        background-position: -260px -450px;
    }

    #HUABAN_WIDGETS .HUABAN-white-small-icon-button {
        width: 30px;
        height: 21px;
        border: 0px;
        line-height: 21px;
        padding-left: 20px;
        padding-right: 0px;
        text-align: left;
        background: url(//huaban.com/img/widgets/widget_icons.png) 0 -550px no-repeat;
        box-shadow: none !important;
        color: #444;
        font-size: 12px;
        background-color: transparent !important;
    }

    #HUABAN_WIDGETS .HUABAN-white-small-icon-button:hover {
        background-position: -130px -550px;
    }

    #HUABAN_WIDGETS .HUABAN-white-small-icon-button:active {
        background-position: -260px -550px;
    }

    #HUABAN_WIDGETS .HUABAN-cell {
        width: 236px;
        position: absolute;
        background: white;
        box-shadow: 0 1px 3px rgba(0, 0, 0, .3);
        transition: left .3s ease-in-out, top .3s linear;
    }

    #HUABAN_WIDGETS .HUABAN-cell .HUABAN-img-holder {
        overflow: hidden;
        position: relative;
    }

    #HUABAN_WIDGETS .HUABAN-cell .HUABAN-img-holder:hover img.HUABAN-cell-img {
        opacity: .8
    }

    #HUABAN_WIDGETS .HUABAN-cell .HUABAN-video-icon {
        width: 72px;
        height: 62px;
        position: absolute;
        left: 50%;
        top: 50%;
        margin: -31px auto auto -36px;
        background: url(//huaban.com/img/widgets/media_video.png) 0 0 no-repeat;
        display: none;
    }

    #HUABAN_WIDGETS .HUABAN-cell.HUABAN-video .HUABAN-video-icon {
        display: block;
    }

    #HUABAN_WIDGETS .HUABAN-cell .HUABAN-over {
        display: none;
    }

    #HUABAN_WIDGETS .HUABAN-cell:hover .HUABAN-over {
        display: block;
    }

    #HUABAN_WIDGETS .HUABAN-cell .HUABAN-over .HUABAN-btn {
        width: 60px;
        height: 34px;
        padding: 0;
        position: absolute;
        left: 50%;
        top: 50%;
        margin: -18px 0 0 -31px;
        line-height: 34px;
        box-shadow: 0 0 0 2px rgba(255, 255, 255, .2);
        font-size: 16px;
    }

    #HUABAN_WIDGETS .HUABAN-cell.HUABAN-long .HUABAN-img-holder {
        height: 600px;
    }

    #HUABAN_WIDGETS .HUABAN-cell.HUABAN-long .HUABAN-img-holder:after {
        content: "";
        display: block;
        position: absolute;
        width: 236px;
        height: 12px;
        left: 0;
        bottom: 0;
        background: url(//huaban.com/img/widgets/long_image_shadow_2.png) repeat-x 4px top;
    }

    #HUABAN_WIDGETS .HUABAN-cell img {
        width: 236px;
        display: block;
    }

    #HUABAN_WIDGETS .HUABAN-cell .HUABAN-size {
        margin: 8px 16px;
        font-size: 12px;
        color: #999
    }

    #HUABAN_WIDGETS .HUABAN-cell .HUABAN-description {
        display: block;
        width: 202px;
        margin: 0 6px 6px;
        padding: 6px 10px;
        border: 0;
        resize: none;
        outline: 0;
        border: 1px solid transparent;
        line-height: 18px;
        font-size: 13px;
        overflow: hidden;
        word-wrap: break-word;
        background: url(//huaban.com/img/widgets/icon_edit.png) 500px center no-repeat;
    }

    #HUABAN_WIDGETS .HUABAN-cell:hover .HUABAN-description {
        background-color: #fff9e0;
        background-position: right top;
    }

    #HUABAN_WIDGETS .HUABAN-cell .HUABAN-description:focus {
        background-color: #F9F9F9;
        background-position: 500px center;
    }

    #HUABAN_WIDGETS .HUABAN-cell .HUABAN-select-btn {
        width: 34px;
        height: 34px;
        background: url(//huaban.com/img/widgets/checkbox.png) 0 0 no-repeat;
        position: absolute;
        right: 5px;
        top: 5px;
        cursor: pointer;
    }

    #HUABAN_WIDGETS .HUABAN-cell .HUABAN-pinned-label {
        position: absolute;
        left: 0;
        top: 10px;
        height: 24px;
        line-height: 24px;
        padding: 0 10px;
        background: #CE0000;
        background: rgba(200, 0, 0, 0.9);
        color: white;
        font-size: 12px;
        display: none;
    }

    #HUABAN_WIDGETS .HUABAN-cell.HUABAN-pinned .HUABAN-pinned-label {
        display: block;
    }

    #HUABAN_WIDGETS .HUABAN-selected .HUABAN-select-btn {
        background-position: 0 -40px;
    }

    #HUABAN_WIDGETS .HUABAN-multi .HUABAN-cell .HUABAN-img-holder {
        cursor: pointer;
    }

    #HUABAN_WIDGETS .HUABAN-multi .HUABAN-cell .HUABAN-cell-pin-btn {
        display: none;
    }

    #HUABAN_WIDGETS .HUABAN-multi .HUABAN-cell .HUABAN-over {
        display: block;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-multi-buttons {
        position: absolute;
        top: 10px;
        left: 0;
        display: none;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-multi-buttons .HUABAN-btn {
        margin-right: 10px;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-multi-noti {
        display: none;
        height: 50px;
        line-height: 50px;
        text-align: center;
        font-size: 16px;
        color: #999;
        font-weight: bold;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-multi-noti span {
        font-weight: normal;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-multi-noti i {
        font-style: normal;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-notice {
        padding: 0 10px;
        height: 30px;
        line-height: 30px;
        position: absolute;
        left: 50%;
        top: 10px;
        margin-left: -83px;
        background: #fff9e2;
        text-align: center;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-notice i {
        display: inline-block;
        width: 18px;
        height: 18px;
        background: url(//huaban.com/img/widgets/icon_notice.png) 0 0 no-repeat;
        vertical-align: top;
        margin: 6px 6px 0 0;
    }

    #HUABAN_WIDGETS .HUABAN-switcher {
        height: 50px;
        width: 100px;
        position: relative;
    }

    #HUABAN_WIDGETS .HUABAN-switcher .HUABAN-title {
        position: absolute;
        right: 75px;
        top: 13px;
        color: #999;
        white-space: nowrap;
        line-height: 24px;
        opacity: 0;
        visibility: hidden;
    }

    #HUABAN_WIDGETS .HUABAN-switcher:hover .HUABAN-title {
        visibility: visible;
        opacity: 1;
        -webkit-transition: opacity .2s linear;
        -webkit-transition-delay: .2s;
        transition: opacity .2s linear;
        transition-delay: .2s;
    }

    #HUABAN_WIDGETS .HUABAN-switcher .HUABAN-bar {
        width: 40px;
        height: 24px;
        background: #EB595F;
        border-radius: 12px;
        color: white;
        position: absolute;
        right: 0;
        top: 13px;
        cursor: pointer;
        font-size: 14px;
        -webkit-transition: all .2s linear;
        transition: all .2s linear;
    }

    #HUABAN_WIDGETS .HUABAN-switcher:hover .HUABAN-bar {
        width: 64px;
    }

    #HUABAN_WIDGETS .HUABAN-switcher.HUABAN-on .HUABAN-bar {
        background: #7DD100;
    }

    #HUABAN_WIDGETS .HUABAN-switcher .HUABAN-bar .HUABAN-round {
        width: 20px;
        height: 20px;
        background: white;
        border-radius: 50%;
        position: absolute;
        left: 2px;
        top: 2px;
        -webkit-transition: left .2s linear;
        box-shadow: 0px 0px 3px rgba(0, 0, 0, 0.15);
        transition: left .2s linear;
        box-shadow: 0px 0px 3px rgba(0, 0, 0, 0.15);
    }

    #HUABAN_WIDGETS .HUABAN-switcher.HUABAN-on .HUABAN-bar .HUABAN-round {
        left: 17px;
    }

    #HUABAN_WIDGETS .HUABAN-switcher.HUABAN-on:hover .HUABAN-bar .HUABAN-round {
        left: 41px;
    }

    #HUABAN_WIDGETS .HUABAN-switcher .HUABAN-bar .HUABAN-text-1 {
        height: 24px;
        line-height: 24px;
        position: absolute;
        right: 17px;
        top: 0;
        opacity: 0;
        visibility: hidden;
        -webkit-transition: all .2s linear;
        transition: all .2s linear;
    }

    #HUABAN_WIDGETS .HUABAN-switcher:hover .HUABAN-bar .HUABAN-text-1 {
        right: 9px;
        opacity: 1;
        visibility: visible;
    }

    #HUABAN_WIDGETS .HUABAN-switcher.HUABAN-on:hover .HUABAN-bar .HUABAN-text-1 {
        right: 17px;
        opacity: 0;
        visibility: hidden;
    }

    #HUABAN_WIDGETS .HUABAN-switcher .HUABAN-bar .HUABAN-text-2 {
        height: 24px;
        line-height: 24px;
        position: absolute;
        left: 17px;
        top: 0;
        opacity: 0;
        visibility: hidden;
        -webkit-transition: all .2s linear;
        transition: all .2s linear;
    }

    #HUABAN_WIDGETS .HUABAN-switcher:hover .HUABAN-bar .HUABAN-text-2 {
        left: 17px;
        opacity: 0;
        visibility: hidden;
    }

    #HUABAN_WIDGETS .HUABAN-switcher.HUABAN-on:hover .HUABAN-bar .HUABAN-text-2 {
        left: 9px;
        opacity: 1;
        visibility: visible;
    }

    #HUABAN_WIDGETS .HUABAN-header .HUABAN-switcher {
        position: absolute;
        right: 0;
        top: 0;
    }

    <!--
    [if IE

    6
    ]
    >
    #HUABAN_WIDGETS .HUABAN-red-normal-icon-button, .HUABAN-red-large-icon-button, .HUABAN-red-small-icon-button, .HUABAN-white-normal-icon-button, .HUABAN-white-large-icon-button, .HUABAN-white-small-icon-button {
        background-image: url({{imgBase}}/widget_icons_ie6.png) < ! [ endif ] --></style>
</div>
</body>

</html>