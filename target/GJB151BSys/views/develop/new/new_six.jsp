<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/20 0020
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<head>
    <base href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/" />
    <!-- 可直接使用框架提供的在线CSS文件 -->
    <!-- 若要使用本地的文件，请注释掉下面两行标记 -->
    <%--<link rel="stylesheet" href="http://newfront.free4inno.com/bootstrap/css/bootstrap.min.css">--%>
    <%--<link rel="stylesheet" href="http://newfront.free4inno.com/css/front.css">--%>

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
                <span class="panel-title">适用项目及限值调整</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td><a>CE101</a></td>
                        <td><a>CE102</a></td>
                        <td><a>CE106</a></td>
                        <td><a>CE107</a></td>
                        <td><a>CS101</a></td>
                        <td><a>CS101</a></td>
                    </tr>
                    <tr>
                        <td><a>CS106</a></td>
                        <td><a>CS109</a></td>
                        <td><a>CS112</a></td>
                        <td><a>CS114</a></td>
                    </tr>
                </table>
                <table class="table">
                    <tr style="width: 100%">
                        <td style="width: 10%">删除项目</td>
                        <td style="width: 40%">
                            <select class="form-control" style="width: 60%">
                                <option>无可删除项目</option>
                                <option>假设可删除的有CE101</option>
                                <option>假设可删除的有CE102</option>
                                <option>假设可删除的有CE103</option>
                            </select>
                        </td>
                        <td style="width: 10%">恢复项目</td>
                        <td style="width: 40%">
                            <select class="form-control" style="width: 60%">
                                <option>无可恢复项目</option>
                                <option>假设可恢复的有RS101</option>
                                <option>假设可恢复的有RS103</option>
                                <option>假设可恢复的有RS105</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">合同项目</span>
            </div>
            <div class="panel-body">
                <div>
                    <p style="padding-left: 10px">标准合同项目</p>
                    <table class="table">
                        <tr style="width: 100%">
                            <td style="width: 10%">CE101</td>
                            <td style="width: 40%">
                                <select class="form-control" style="width: 60%">
                                    <option>适用</option>
                                    <option>不适用</option>
                                </select>
                            </td>
                            <td style="width: 10%">CE107</td>
                            <td style="width: 40%">
                                <select class="form-control" style="width: 60%">
                                    <option>适用</option>
                                    <option>不适用</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 10%">RE101</td>
                            <td style="width: 40%">
                                <select class="form-control" style="width: 60%">
                                    <option>适用</option>
                                    <option>不适用</option>
                                </select>
                            </td>
                            <td style="width: 10%">RS101</td>
                            <td style="width: 40%">
                                <select class="form-control" style="width: 60%">
                                    <option>适用</option>
                                    <option>不适用</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>
                <div>
                    <p style="padding-left: 10px">特殊处理项目</p>
                    <table class="table">
                        <tr style="width:100%">
                            <th style="width: 10%">项目名</th>
                            <th style="width: 10%">适用性</th>
                            <th style="width: 40%">限值</th>
                            <th style="width: 40%">规定限值</th>
                        </tr>
                        <tr>
                            <td>CS103</td>
                            <td>
                                <select class="form-control">
                                    <option>适用</option>
                                    <option>不适用</option>
                                </select>
                            </td>
                            <td>
                                <input type="text" class="form-control radius">
                            </td>
                            <td>
                                <input type="text" class="form-control radius">
                            </td>
                        </tr>
                        <tr>
                            <td>CS104</td>
                            <td>
                                <select class="form-control">
                                    <option>适用</option>
                                    <option>不适用</option>
                                </select>
                            </td>
                            <td>
                                <input type="text" class="form-control radius">
                            </td>
                            <td>
                                <input type="text" class="form-control radius">
                            </td>
                        </tr>
                        <tr>
                            <td>CS105</td>
                            <td>
                                <select class="form-control">
                                    <option>适用</option>
                                    <option>不适用</option>
                                </select>
                            </td>
                            <td>
                                <input type="text" class="form-control radius">
                            </td>
                            <td>
                                <input type="text" class="form-control radius">
                            </td>
                        </tr>
                    </table>
                </div>
                <div>
                    <p style="padding-left: 10px">新增项目</p>
                    <table class="table">
                        <tr style="width: 100%">
                            <td style="width: 10%">可新增项目</td>
                            <td style="width: 40%">
                                <select class="form-control" style="width: 60%">
                                    <option>无可新增项目</option>
                                    <option>假设可新增的有CE101</option>
                                    <option>假设可新增的有CE102</option>
                                    <option>假设可新增的有CE103</option>
                                </select>
                            </td>
                            <td style="width: 10%">删除新增项目</td>
                            <td style="width: 40%">
                                <select class="form-control" style="width: 60%">
                                    <option>无可删除项目</option>
                                    <option>假设可删除的有RS101</option>
                                    <option>假设可删除的有RS103</option>
                                    <option>假设可删除的有RS105</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <table class="table">
                        <tr>
                            <td><a>CE101</a></td>
                            <td><a>CE102</a></td>
                            <td><a>CE106</a></td>
                            <td><a>CE107</a></td>
                            <td><a>CS101</a></td>
                            <td><a>CS101</a></td>
                        </tr>
                    </table>
                </div>

            </div>
            <div class="panel-body">
                <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 10px;">
                    <button class="btn btn-primary">上一页</button>
                </div>
            </div>
        </div>
        <!--</div>-->
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 12px;">
                    <button class="btn btn-default">取消</button>
                    |
                    <button type="button" class="btn btn-default">保存草稿</button>
                    <button type="button" class="btn btn-primary">提交报告</button>
                </div>
            </div>
        </form>
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
