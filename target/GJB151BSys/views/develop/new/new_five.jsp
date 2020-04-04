<%--
  Created by IntelliJ IDEA.
  User: ddgdd
  Date: 2018/8/20 0020
  Time: 13:48
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
                <span class="panel-title">可选用频方式</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td style="width: 20%">用频方式</td>
                        <td  colspan="4">
                            <select class="form-control">
                                <option>固定</option>
                                <option>连续可调</option>
                                <option>其他</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">频率特性</td>
                    </tr>
                    <tr>
                        <td>频段</td>
                        <td>最低频率(MHz)</td>
                        <td>中间频率(MHz)</td>
                        <td>最高频率(MHz)</td>
                        <td>频点数</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" class="form-control radius" readonly value = "1（自增）">
                        </td>
                        <td>
                            <input type="text" class="form-control radius">
                        </td>
                        <td>
                            <input type="text" class="form-control radius">
                        </td>
                        <td>
                            <input type="text" class="form-control radius">
                        </td>
                        <td>
                            <input type="text" class="form-control radius" value="固定多频点时填写">
                        </td>
                    </tr>
                    <tr >
                        <td style="width: 20%">工作方式</td>
                        <td colspan="4">
                            <select class="form-control">
                                <option>收</option>
                                <option>发</option>
                                <option>收发共用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">安装方式</td>
                        <td colspan="4">
                            <select class="form-control">
                                <option>可拆卸</option>
                                <option>不可拆卸</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">最大发射平均功率dB（W）</td>
                        <td  colspan="4"><input type="text" class="form-control radius" ></td>

                    </tr>
                    <tr>
                        <td style="width: 20%">调制方式</td>
                        <td  colspan="4"><input type="text" class="form-control radius"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">端口名称或ID</td>
                        <td  colspan="4"><input type="text" class="form-control radius"></td>
                    </tr>
                </table>
            </div>
            <div class="panel-body">
                <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 10px;">
                    <button class="btn btn-primary">删除</button>
                    <button class="btn btn-primary">新建</button>
                </div>
            </div>

            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">跳频</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td colspan="4">频率特性</td>
                    </tr>
                    <tr>
                        <td>频段</td>
                        <td>最低频率(MHz)</td>
                        <td>最高频率(MHz)</td>
                        <td>频点数</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" class="form-control radius" readonly value="低频段">
                        </td>
                        <td>
                            <input type="text" class="form-control radius">
                        </td>
                        <td>
                            <input type="text" class="form-control radius">
                        </td>
                        <td>
                            <input type="text" class="form-control radius" value="固定多频点时填写">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">工作方式</td>
                        <td  colspan="4">
                            <select class="form-control">
                                <option>收</option>
                                <option>发</option>
                                <option>收发共用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">安装方式</td>
                        <td  colspan="4">
                            <select class="form-control">
                                <option>可拆卸</option>
                                <option>不可拆卸</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">最大发射平均功率dB（W）</td>
                        <td  colspan="4"><input type="text" class="form-control radius"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">端口名称或ID</td>
                        <td  colspan="4"><input type="text" class="form-control radius"></td>
                    </tr>
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">跳频</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td colspan="4">频率特性</td>
                    </tr>
                    <tr>
                        <td>频段</td>
                        <td>最低频率(MHz)</td>
                        <td>最高频率(MHz)</td>
                        <td>频点数</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" class="form-control radius" readonly value="中频段">
                        </td>
                        <td>
                            <input type="text" class="form-control radius">
                        </td>
                        <td>
                            <input type="text" class="form-control radius">
                        </td>
                        <td>
                            <input type="text" class="form-control radius" value="固定多频点时填写">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">工作方式</td>
                        <td  colspan="4">
                            <select class="form-control">
                                <option>收</option>
                                <option>发</option>
                                <option>收发共用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">安装方式</td>
                        <td  colspan="4">
                            <select class="form-control">
                                <option>可拆卸</option>
                                <option>不可拆卸</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">最大发射平均功率dB（W）</td>
                        <td  colspan="4"><input type="text" class="form-control radius"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">端口名称或ID</td>
                        <td  colspan="4"><input type="text" class="form-control radius"></td>
                    </tr>
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">跳频</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td colspan="4">频率特性</td>
                    </tr>
                    <tr>
                        <td>频段</td>
                        <td>最低频率(MHz)</td>
                        <td>最高频率(MHz)</td>
                        <td>频点数</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" class="form-control radius" readonly value="高频段">
                        </td>
                        <td>
                            <input type="text" class="form-control radius">
                        </td>
                        <td>
                            <input type="text" class="form-control radius">
                        </td>
                        <td>
                            <input type="text" class="form-control radius" value="固定多频点时填写">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">工作方式</td>
                        <td  colspan="4">
                            <select class="form-control">
                                <option>收</option>
                                <option>发</option>
                                <option>收发共用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">安装方式</td>
                        <td  colspan="4">
                            <select class="form-control">
                                <option>可拆卸</option>
                                <option>不可拆卸</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">最大发射平均功率dB（W）</td>
                        <td  colspan="4"><input type="text" class="form-control radius"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">端口名称或ID</td>
                        <td  colspan="4"><input type="text" class="form-control radius"></td>
                    </tr>
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">直序扩频</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td colspan="4">频率特性</td>
                    </tr>
                    <tr>
                        <td colspan="2">最高传输速率（bit/s)</td>
                        <td colspan="2">频点数</td>
                    </tr>
                    <tr style="width: 100%">
                        <td colspan="2" style="width: 50%">
                            <input type="text" class="form-control radius">
                        </td>
                        <td colspan="2" style="width: 50%">
                            <input type="text" class="form-control radius">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">工作方式</td>
                        <td colspan="4">
                            <select class="form-control">
                                <option>收</option>
                                <option>发</option>
                                <option>收发共用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">安装方式</td>
                        <td colspan="4">
                            <select class="form-control">
                                <option>可拆卸</option>
                                <option>不可拆卸</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">最大发射平均功率dB（W）</td>
                        <td colspan="4"><input type="text" class="form-control radius"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">端口名称或ID</td>
                        <td colspan="4"><input type="text" class="form-control radius"></td>
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
<html>