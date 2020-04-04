<%--
  Created by IntelliJ IDEA.
  User: ddgdd
  Date: 2018/8/20 0020
  Time: 13:25
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
    <div class="container">
        <!--<div class="row">-->
        <div class="panel panel-default front-panel">
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">分系统/设备基本信息</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td style="width: 20% ">分系统/设备名称</td>
                        <td><input type="text" class="form-control radius" readonly value="XXX分系统(自动生成只读不可编辑）"></td>
                    </tr>
                    <tr>
                        <td style="width: 20% ">型号</td>
                        <td><input type="text" class="form-control radius" readonly value="型号信息(自动生成只读不可编辑）"></td>
                    </tr>
                    <tr>
                        <td style="width: 20% ">串号</td>
                        <td><input type="text" class="form-control radius" readonly value="串号信息(自动生成只读不可编辑）"></td>
                    </tr>
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">分系统/设备特性</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td>设备属性</td>
                        <td>任务关键设备</td>
                        <td>安装方式</td>
                        <td>地线</td>
                    </tr>
                    <tr>
                        <td>
                            <select class="form-control">
                                <option>用频</option>
                                <option>非用频</option>
                            </select>
                        </td>
                        <td>
                            <select class="form-control">
                                <option>是</option>
                                <option>否</option>
                            </select>
                        </td>
                        <td>
                            <select class="form-control">
                                <option>台式</option>
                                <option>机架安装</option>
                                <option>甲板固定</option>
                                <option>壁挂</option>
                                <option>手持</option>
                            </select>
                        </td>
                        <td>
                            <select class="form-control">
                                <option>有</option>
                                <option>无</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">特殊设备</td>
                        <td colspan="2">供电方式</td>
                    </tr>
                    <tr style="width: 100%">
                        <td colspan="2" style="width: 50%">
                            <select class="form-control">
                                <option>无</option>
                                <option>（水面舰船和潜艇平台可选）工作频率低于100kHz，灵敏度优于1V</option>
                                <option>（陆军地面可选）扫雷和探雷的机动车辆</option>
                                <option>（海军飞机可选）反潜机</option>
                            </select>
                        </td>
                        <td colspan="2" style="width: 50%">
                            <select class="form-control">
                                <option>直流</option>
                                <option>交流50Hz</option>
                                <option>交流400Hz</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">安装平台特性</td>
                    </tr>
                    <tr>
                        <td colspan="2">EMP加固措施</td>
                        <td colspan="2">静电威胁</td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <select class="form-control">
                                <option>有</option>
                                <optison>无</optison>
                            </select>
                        </td>
                        <td colspan="2">
                            <select class="form-control">
                                <option>是</option>
                                <option>否</option>
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
