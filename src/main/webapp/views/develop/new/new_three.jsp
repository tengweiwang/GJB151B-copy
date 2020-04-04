<%--
  Created by IntelliJ IDEA.
  User: ddgdd
  Date: 2018/8/20 0020
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<head>
    <base href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/" />
    <!-- 可直接使用框架提供的在线CSS文件 -->
    <!-- 若要使用本地的文件，请注释掉下面两行标记 -->
    <!--<link rel="stylesheet" href="http://newfront.free4inno.com/bootstrap/css/bootstrap.min.css">-->
    <!--<link rel="stylesheet" href="http://newfront.free4inno.com/css/front.css">-->

    <!-- 也可以使用下载到本地的CSS文件，请去掉下面两行标记的注释。其中localpath为本地路径。 -->
    <!-- 若要使用在线文件，请注释掉下面两行标记 -->
    <link rel="stylesheet" href="statics/css/bootstrap.min.css">
    <link rel="stylesheet" href="statics/css/front.css">
    <link rel="stylesheet" href="statics/css/develop/new/new.css">
</head>
<body class="front-body">
<div class="front-inner">
    <s:include value="../_nav.jsp?act=new"/>
    <%--<nav class="navbar navbar-default navbar-fixed-top front-nav">--%>
        <%--<div class="container">--%>
            <%--<div>--%>
                <%--<!-- 品牌图片大小为150 * 30：宽度不定，高度固定30px -->--%>
                <%--<!--<div class="nav-brand"><a href="#"><img src="/bootstrap-front/dev/img/logo.png" alt="brand" class="img-responsive"/></a></div>-->--%>
                <%--<!-- -->--%>
                <%--<div class="nav-brand" style="font-size: 22px"><a href="#">GJB151B研制要求管理系统</a></div>--%>
            <%--</div>--%>
            <%--<div class="nav-collapse collapse" id="nav-collapse-demo">--%>
                <%--<ul class="nav navbar-nav">--%>
                    <%--<li><a href="#">项目</a></li>--%>
                    <%--<li class="front-active"><a href="#">编制</a></li>--%>
                    <%--<li><a href="#">校对</a></li>--%>
                    <%--<li><a href="#">审核</a></li>--%>
                    <%--<li><a href="#">批准</a></li>--%>
                <%--</ul>--%>
            <%--</div>--%>
            <%--<div class="nav-right">--%>
                <%--<ul class="nav navbar-nav">--%>
                    <%--<li><a href="#">登录</a></li>--%>
                    <%--<li><a href="#">注册</a></li>--%>
                <%--</ul>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</nav>--%>
    <div class="container">
        <!--<div class="row">-->
        <div class="panel panel-default front-panel">
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">任务基本信息</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td style="width: 20% ">任务名称</td>
                        <td><input type="text" class="form-control radius" readonly value="XXX电磁兼容测试(只读不可编辑）"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">分系统设备/名称</td>
                        <td>
                            <select class="form-control" id="subsys_eqp">
                                <option>分系统</option>
                                <option>设备</option>
                            </select>
                        </td>
                    </tr>
                    <!--选择分系统时显示-->
                    <tbody id="subsys">
                    <tr>
                        <td style="width: 20%">分系统名称</td>
                        <td><input type="text" class="form-control radius"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">型号</td>
                        <td><input type="text" class="form-control radius"></td>
                    </tr>
                    </tbody>
                    <tbody id="eqp" class="hidden">
                    <tr>
                        <td style="width: 20%">设备名称</td>
                        <td><input type="text" class="form-control radius"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">型号</td>
                        <td><input type="text" class="form-control radius"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">串号</td>
                        <td><input type="text" class="form-control radius"></td>
                    </tr>
                    </tbody>
                    <tr>
                        <td style="width: 20%">承制单位</td>
                        <td><input type="text" class="form-control radius"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">预定使用平台（一级平台）</td>
                        <td>
                            <select class="form-control">
                                <option>水面舰船</option>
                                <option>潜艇</option>
                                <option>陆军飞机</option>
                                <option>海军飞机</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">预定使用平台（二级平台）</td>
                        <td>
                            <select class="form-control">
                                <option>海军舰船甲板上</option>
                                <option>陆军舰船甲板上</option>
                                <option>海军水下外部</option>
                                <option>陆军水下外部</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="panel-body">
                <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 10px;">
                    <button class="btn btn-primary">上一页</button>
                    <button class="btn btn-primary">下一页</button>
                </div>
            </div>
        </div>

        <!--</div>-->
    </div>

    <s:include value="../../_footer.jsp"/>
</div>
<!-- 可直接使用框架提供的在线js文件 -->
<!-- 若要使用本地的文件，请注释掉下面三行标记 -->
<script src="http://newfront.free4inno.com/js/jquery/jquery.min.js"></script>
<script src="http://newfront.free4inno.com/bootstrap/js/bootstrap.min.js"></script>
<script src="http://newfront.free4inno.com/js/plugin/front.js"></script>
</body>
</html>
