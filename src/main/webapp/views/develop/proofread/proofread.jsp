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
    <base href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/"/>
    <link rel="stylesheet" href="repack/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="repack/css/front.css">
    <link rel="stylesheet" href="statics/css/develop/new/new.css">

</head>
<body class="front-body">
<div class="front-inner">
    <%--<s:include value="../_nav.jsp?act=1"/>--%>
    <s:include value="../_nav.jsp?">
        <s:param name="userName">userName</s:param>
        <s:param name="act">${param.devStatus}</s:param>
    </s:include>
    <script>console.log('${param.devStatus}'+"头部的param.devStatus=${param.devStatus}");</script>
    <div class="container">
        <!--<div class="row">-->
        <div id="new3" class="panel panel-default front-panel">
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">任务基本信息</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td style="width: 20% ">任务名称</td>
                        <td><input type="text" class="form-control radius" readonly value=${param.devName}></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">分系统设备/名称</td>
                        <td>
                            <select class="form-control" id="subsys_eqp" readonly disabled="disabled">
                                <option value="0" selected >分系统</option>
                                <option value="1" >设备</option>
                            </select>
                        </td>
                    </tr>
                    <!--选择分系统时显示-->
                    <tbody id="subsys">
                    <tr>
                        <td style="width: 20%">分系统名称</td>
                        <td><input type="text" class="form-control radius" id="subsys_name" readonly></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">型号</td>
                        <td><input type="text" class="form-control radius" id="subsys_model" readonly></td>
                    </tr>
                    </tbody>
                    <tbody id="eqp" class="hidden">
                    <tr>
                        <td style="width: 20%">设备名称</td>
                        <td><input type="text" class="form-control radius" id="eqp_name" readonly></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">型号</td>
                        <td><input type="text" class="form-control radius" id="eqp_model" readonly></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">串号</td>
                        <td><input type="text" class="form-control radius" id="eqp_num" readonly></td>
                    </tr>
                    </tbody>
                    <tr>
                        <td style="width: 20%">承制单位</td>
                        <td><input type="text" class="form-control radius" id="supplier" readonly></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">预定使用平台（一级平台）</td>
                        <td>
                            <select id="primary_platform" class="form-control" readonly disabled="disabled">
                                <option value="1" selected>水面舰船</option>
                                <option value="2">潜艇</option>
                                <option value="3">陆军飞机</option>
                                <option value="4">海军飞机</option>
                                <option value="5">空军飞机</option>
                                <option value="6">空间系统</option>
                                <option value="7">陆军地面</option>
                                <option value="8">海军地面</option>
                                <option value="9">空军地面</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">预定使用平台（二级平台）</td>
                        <td>
                            <select id="secondary_platform" class="form-control" readonly disabled="disabled">
                                <option selected value="11">海军舰船甲板上</option>
                                <option value="12">陆军舰船甲板上</option>
                                <option value="13">海军舰船水下外部</option>
                                <option value="14">陆军舰船水下外部</option>
                                <option value="15">海军金属舰船甲板下</option>
                                <option value="16">陆军金属舰船甲板下</option>
                                <option value="17">海军非金属舰船甲板下（包括航母机库内设备）</option>
                                <option value="18">陆军非金属舰船甲板下</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="panel-body">
                <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 10px;">
                    <button class="btn btn-primary" onclick="nextPage()">下一页</button>
                </div>
            </div>
        </div>
        <div id="new4" class="panel panel-default front-panel hidden">
            <div>
            </div>

            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">分系统/设备基本信息</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td style="width: 20% ">分系统/设备名称</td>
                        <td><input type="text" class="form-control radius" id="subsys_eqp_name4" readonly></td>
                    </tr>
                    <tr>
                        <td style="width: 20% ">型号</td>
                        <td><input type="text" class="form-control radius" id="subsys_eqp_model4" readonly></td>
                    </tr>
                    <tr>
                        <td style="width: 20% ">串号</td>
                        <td><input type="text" class="form-control radius" id="subsys_eqp_num4" readonly></td>
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
                        <td>带地线</td>
                    </tr>
                    <tr>
                        <td>
                            <select class="form-control" id="attribute" readonly disabled="disabled">
                                <option value="1" selected>用频</option>
                                <option value="0">非用频</option>
                            </select>
                        </td>
                        <td>
                            <select class="form-control" id="key_equipment" readonly disabled="disabled">
                                <option value="1" selected>是</option>
                                <option value="0">否</option>
                            </select>
                        </td>
                        <td>
                            <select class="form-control" id="install_mode" readonly disabled="disabled">

                                <option value="desktop" selected>台式</option>
                                <option value="frame">机架安装</option>
                                <option value="board">甲板固定</option>
                                <option value="hang">壁挂</option>
                                <option value="hold">手持</option>
                                <option value="no_hold">非手持</option>
                            </select>
                        </td>
                        <td>
                            <select class="form-control" id="ground_line" readonly disabled="disabled">
                                <option value="1" selected>是</option>
                                <option value="0">否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">特殊设备</td>
                        <td>互联端口</td>
                        <td>对低频信号敏感</td>
                    </tr>
                    <tr style="width: 100%">
                        <td colspan="2" style="width: 50%">

                            <select class="form-control" id="special_equipment" readonly disabled="disabled">
                                <option value="0" selected>无</option>
                                <option value="1">（水面舰船和潜艇平台可选）工作频率低于100kHz，灵敏度优于1V</option>
                                <option value="2">（陆军地面可选）扫雷和探雷的机动车辆</option>
                                <option value="3">（海军飞机可选）反潜机</option>
                            </select>
                        </td>
                        <td style="width: 25%">
                            <select class="form-control" id="interconnected_port" readonly disabled="disabled">
                                <option value="1" selected>有</option>
                                <option value="0">无</option>
                            </select>
                        </td>
                        <td style="width: 25%">
                            <select class="form-control" id="low_frequency_sensitive" readonly disabled="disabled">
                                <option value="1" selected>是</option>
                                <option value="0">否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">安装平台特性</td>
                    </tr>
                    <tr>
                        <td colspan="2">EMP加固措施</td>
                        <td colspan="2">易受静电威胁</td>
                    </tr>
                    <tr>
                        <td colspan="2">

                            <select class="form-control" id="emp_reinforce" readonly disabled="disabled">
                                <option value="1" selected>有</option>
                                <option value="0">无</option>
                            </select>
                        </td>
                        <td colspan="2">
                            <select class="form-control" id="static_electricity" readonly disabled="disabled">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">电源端口</td>
                    </tr>
                    <tr>
                        <td>
                            <select id="power_port" class="form-control" readonly disabled="disabled">
                                <option value="input_port" selected>外部电源输入端口</option>
                                <option value="other_port">其他电源端口</option>
                                <option value="no_port">无电源端口</option>
                            </select>
                        </td>
                        <td>
                            <select id="power_supply" class="form-control" readonly disabled="disabled">
                                <option value="platform_main" selected>平台主电源供电</option>
                                <option value="no_platform_main">非平台主电源供电</option>
                            </select>
                        </td>
                        <td>
                            <select id="voltage" class="form-control" readonly disabled="disabled">
                                <option value="direct_current" selected>直流</option>
                                <option value="alternate_current50">交流50Hz</option>
                                <option value="alternate_current400">交流400Hz</option>
                            </select>
                        </td>
                        <td>
                            <select id="voltage_num" class="form-control" readonly disabled="disabled">
                                <option value="smaller_than" selected><= 28V</option>
                                <option value="bigger_than">> 28V</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">天线端口</td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <select id="antenna_removal" class="form-control" readonly disabled="disabled">
                                <option value="none">无天线端口</option>
                                <option value="removal" selected>天线可拆卸</option>
                                <option value="no_removal">天线不可拆卸</option>
                            </select>
                        </td>
                        <td>
                            <select id="receive_launch" class="form-control" readonly disabled="disabled">
                                <option value="receive" selected>接收</option>
                                <option value="launch">发射</option>
                                <option value="receive_and_launch">收/发</option>
                            </select>
                        </td>
                        <td>
                            <select id="anolitude_modulation" class="form-control" readonly disabled="disabled">
                                <option value="am" selected>调幅</option>
                                <option value="no_am">非调幅</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="panel-body">
                <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 10px;">
                    <button class="btn btn-primary" onclick="prePage()">上一页</button>
                    <button class="btn btn-primary" onclick="nextPage()">下一页</button>
                </div>
            </div>
        </div>
        <div id="new5" class="panel panel-default front-panel hidden">
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">分系统/设备基本信息</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td style="width: 20% ">分系统/设备名称</td>
                        <td><input type="text" class="form-control radius" id="subsys_eqp_name5" readonly></td>
                    </tr>
                    <tr>
                        <td style="width: 20% ">型号</td>
                        <td><input type="text" class="form-control radius" id="subsys_eqp_model5" readonly></td>
                    </tr>
                    <tr>
                        <td style="width: 20% ">串号</td>
                        <td><input type="text" class="form-control radius" id="subsys_eqp_num5" readonly></td>
                    </tr>
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">可选用频方式</span>
            </div>
            <div id="opt_1">
                <div class="panel-body">
                    <br>
                    <table class="table">
                        <tr>
                            <td style="width: 20%">用频方式</td>
                            <td colspan="4">
                                <select class="form-control opt_freq_mode" readonly disabled="disabled">
                                    <option value="1" selected>固定</option>
                                    <option value="2">连续可调</option>
                                    <option value="3">其他</option>
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
                                <input type="text" class="form-control radius opt_freq_range" id="text" readonly
                                       value="1">
                            </td>
                            <td>
                                <input type="text" class="form-control radius opt_freq_low" readonly>
                            </td>
                            <td>
                                <input type="text" class="form-control radius opt_freq_mid" readonly>
                            </td>
                            <td>
                                <input type="text" class="form-control radius opt_freq_high" readonly>
                            </td>
                            <td>
                                <input type="text" class="form-control radius opt_freq_points" value="固定多频点时填写" readonly>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 20%">工作方式</td>
                            <td colspan="4">
                                <select class="form-control opt_work_style" readonly disabled="disabled">
                                    <option value="2" selected>收</option>
                                    <option value="1">发</option>
                                    <option value="3">收发共用</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 20%">安装方式</td>
                            <td colspan="4">
                                <select class="form-control opt_install_mode" readonly disabled="disabled">
                                    <option value="1" selected>可拆卸</option>
                                    <option value="2">不可拆卸</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 20%">最大发射平均功率dB（W）</td>
                            <td colspan="4"><input type="text" class="form-control radius opt_ave_pow_transmit_max" readonly>
                            </td>

                        </tr>
                        <tr>
                            <td style="width: 20%">调制方式<br>
                                <input id="decrease" type=button onclick="decrease(this)" style="border:none" value="-" readonly>
                                <input style="width:25px;border:none;outline:none;text-align:center"
                                       class="opt_modulation_mode_num"
                                       type=text name=amount readonly value="1">
                                <input id="increase" type=button onclick="increase(this)" style="border:none" value="+" readonly>
                            </td>
                            <td colspan="4">
                                <div class="opt_modulation_mode_1"><input type="text" id="way"
                                                                          class="form-control radius opt_modulation_mode_1" readonly>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 20%">端口名称或ID</td>
                            <td colspan="4"><input type="text" class="form-control radius opt_port_name" readonly></td>
                        </tr>
                    </table>

                </div>
                <%--<div class="panel-body">--%>
                    <%--<div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 10px;">--%>
                        <%--<button class="btn btn-primary" onclick="deleteFrequency(this);">删除</button>--%>
                        <%--<button class="btn btn-primary" onclick="addFrequency();" id="add">新建</button>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>

            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">跳频</span>
            </div>
            <div id="FH_low" class="panel-body">
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
                            <input id="FH_low_freq_range" type="text" class="form-control radius" readonly value="低频段">
                        </td>
                        <td>
                            <input id="FH_low_freq_low" type="text" class="form-control radius" readonly>
                        </td>
                        <td>
                            <input id="FH_low_freq_high" type="text" class="form-control radius" readonly>
                        </td>
                        <td>
                            <input id="FH_low_freq_points" type="text" class="form-control radius" value="固定多频点时填写" readonly>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">工作方式</td>
                        <td colspan="4">
                            <select id="FH_low_work_style" class="form-control" readonly disabled="disabled">
                                <option value="2" selected>收</option>
                                <option value="1">发</option>
                                <option value="3">收发共用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">安装方式</td>
                        <td colspan="4">
                            <select id="FH_low_install_mode" class="form-control" readonly disabled="disabled">
                                <option value="1">可拆卸</option>
                                <option value="2">不可拆卸</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">最大发射平均功率dB（W）</td>
                        <td colspan="4"><input id="FH_low_ave_pow_transmit_max" type="text" class="form-control radius" readonly>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">端口名称或ID</td>
                        <td colspan="4"><input id="FH_low_port_name" type="text" class="form-control radius" readonly></td>
                    </tr>
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">跳频</span>
            </div>
            <div id="FH_mid" class="panel-body">
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
                            <input id="FH_mid_freq_range" type="text" class="form-control radius" readonly value="中频段">
                        </td>
                        <td>
                            <input id="FH_mid_freq_low" type="text" class="form-control radius" readonly>
                        </td>
                        <td>
                            <input id="FH_mid_freq_high" type="text" class="form-control radius" readonly>
                        </td>
                        <td>
                            <input id="FH_mid_freq_points" type="text" class="form-control radius" value="固定多频点时填写" readonly>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">工作方式</td>
                        <td colspan="4">
                            <select id="FH_mid_work_style" class="form-control" readonly disabled="disabled">
                                <option value="2" selected>收</option>
                                <option value="1">发</option>
                                <option value="3">收发共用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">安装方式</td>
                        <td colspan="4">
                            <select id="FH_mid_install_mode" class="form-control" readonly disabled="disabled">
                                <option value="1">可拆卸</option>
                                <option value="2">不可拆卸</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">最大发射平均功率dB（W）</td>
                        <td colspan="4"><input id="FH_mid_ave_pow_transmit_max" type="text" class="form-control radius" readonly>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">端口名称或ID</td>
                        <td colspan="4"><input id="FH_mid_port_name" type="text" class="form-control radius" readonly></td>
                    </tr>
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">跳频</span>
            </div>
            <div id="FH_high" class="panel-body">
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
                            <input id="FH_high_freq_range" type="text" class="form-control radius" readonly value="高频段">
                        </td>
                        <td>
                            <input id="FH_high_freq_low" type="text" class="form-control radius" readonly>
                        </td>
                        <td>
                            <input id="FH_high_freq_high" type="text" class="form-control radius" readonly>
                        </td>
                        <td>
                            <input id="FH_high_freq_points" type="text" class="form-control radius" value="固定多频点时填写" readonly>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">工作方式</td>
                        <td colspan="4">
                            <select id="FH_high_work_style" class="form-control" readonly disabled="disabled">
                                <option value="2" selected>收</option>
                                <option value="1">发</option>
                                <option value="3">收发共用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">安装方式</td>
                        <td colspan="4">
                            <select id="FH_high_install_mode" class="form-control" readonly disabled="disabled">
                                <option value="1">可拆卸</option>
                                <option value="2">不可拆卸</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">最大发射平均功率dB（W）</td>
                        <td colspan="4"><input id="FH_high_ave_pow_transmit_max" type="text"
                                               class="form-control radius" readonly></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">端口名称或ID</td>
                        <td colspan="4"><input id="FH_high_port_name" type="text" class="form-control radius" readonly></td>
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
                            <input id="DSSS_trans_rate_max" type="text" class="form-control radius" readonly>
                        </td>
                        <td colspan="2" style="width: 50%">
                            <input id="DSSS_freq_points" type="text" class="form-control radius" readonly>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">工作方式</td>
                        <td colspan="4">
                            <select id="DSSS_work_style" class="form-control" readonly disabled="disabled">
                                <option value="2" selected>收</option>
                                <option value="1">发</option>
                                <option value="3">收发共用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">安装方式</td>
                        <td colspan="4">
                            <select id="DSSS_install_mode" class="form-control" readonly disabled="disabled">
                                <option value="1">可拆卸</option>
                                <option value="2">不可拆卸</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">最大发射平均功率dB（W）</td>
                        <td colspan="4"><input id="DSSS_ave_pow_transmit_max" type="text" class="form-control radius" readonly>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%">端口名称或ID</td>
                        <td colspan="4"><input id="DSSS_port_name" type="text" class="form-control radius" readonly></td>
                    </tr>
                </table>
            </div>
            <div class="panel-body">
                <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 10px;">
                    <button class="btn btn-primary" onclick="prePage()">上一页</button>
                    <button class="btn btn-primary" onclick="nextPage()">下一页</button>
                </div>
            </div>
        </div>
        <div id="new6" class="panel panel-default front-panel hidden">
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">分系统/设备基本信息</span>
            </div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td style="width: 20% ">分系统/设备名称</td>
                        <td><input type="text" class="form-control radius" id="subsys_eqp_name6" readonly></td>
                    </tr>
                    <tr>
                        <td style="width: 20% ">型号</td>
                        <td><input type="text" class="form-control radius" id="subsys_eqp_model6" readonly></td>
                    </tr>
                    <tr>
                        <td style="width: 20% ">串号</td>
                        <td><input type="text" class="form-control radius" id="subsys_eqp_num6" readonly></td>
                    </tr>
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">适用项目及限值调整</span>
            </div>
            <div class="panel-body">
                <table id="standard_subject_table" class="table" readonly disabled="disabled">
                    <tr style="width:100%">
                        <th style="width: 10%">项目名</th>
                        <th style="width: 15%">适用性</th>
                        <th style="width: 15%"></th>
                        <th style="width: 60%">剪裁理由</th>
                    </tr>
                </table>
                <table id="standard_subject_table_none" class="table">
                    <tr style="width:100%">
                        <td>无适用项目</td>
                    </tr>
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">合同项目</span>
            </div>
            <div class="panel-body">
                <table id="protocol_subject_table" class="table hidden">
                    <tr style="width:100%">
                        <th style="width: 10%">项目名</th>
                        <th style="width: 15%">适用性</th>
                        <th style="width: 15%">限值</th>
                        <th style="width: 60%">剪裁理由/规定允差</th>
                    </tr>
                </table>
                <table id="protocol_subject_table_none" class="table hidden">
                    <tr style="width:100%">
                        <td>无合同项目</td>
                    </tr>
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">可选项目</span>
            </div>
            <div class="panel-body">
                <table id="protocol_optional_table" class="table">
                    <tr style="width:100%">
                        <th style="width: 10%">项目名</th>
                        <th style="width: 15%">适用性</th>
                        <th style="width: 15%">限值</th>
                        <th style="width: 60%">剪裁理由/规定允差</th>
                    </tr>
                </table>
                <table id="protocol_optional_table_none" class="table hidden">
                    <tr style="width:100%">
                        <td>无可选项目</td>
                    </tr>
                </table>
            </div>
            <div class="panel-body">
                <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 10px;">
                    <button class="btn btn-primary" onclick="prePage()">上一页</button>
                </div>
            </div>
        </div>
        <%--<form class="form-horizontal">--%>
            <%--<div id="submit" class="form-group hidden">--%>
                <%--<div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 12px;">--%>
                    <%--<button type="button" class="btn btn-default" onclick="cancel()">取消</button>--%>
                    <%--|--%>
                    <%--<button type="button" class="btn btn-default" onclick="submitProject('draft')">保存草稿</button>--%>
                    <%--<button type="button" class="btn btn-primary" onclick="submitProject('save')">提交报告</button>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</form>--%>
        <!--</div>-->
        <div class="panel panel-default front-panel">
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">历史校对意见</span>
            </div>
            <div class="panel-body">
                <table class="table" id="advice_history_proofread">
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">历史审核意见</span>
            </div>
            <div class="panel-body">
                <table class="table" id="advice_history_audit">
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;">
                <span class="panel-title">历史批准意见</span>
            </div>
            <div class="panel-body">
                <table class="table" id="advice_history_authorize">
                </table>
            </div>
            <div class="panel-heading" style="text-align: center;" id="advice_title_div">
                <span class="panel-title" id = "advice_title">意见</span>
            </div>
            <div class="panel-body" id="advice_div" style="overflow-y: scroll">
                <textarea id="advice" type="text" class="form-control radius" placeholder='输入意见' style="height: 100px"></textarea>
                <style type="text/css">
                    textarea{
                        /* box-sizing: padding-box; */
                        width:8px;   // 控制垂直滚动条宽度
                        height:20px;
                        overflow-y:scroll;

                        /* demo only: */
                        /*padding:10px;*/

                        /*width:250px;*/

                        /*font-size:14px;*/
                        /*margin:50px auto;*/
                        display:block;
                        /*border-radius:10px;*/
                        /*border:6px solid #556677;*/
                    }
                    ::-webkit-scrollbar { /*血槽宽度*/
                        width:8px;   // 控制垂直滚动条宽度
                       height:20%; // 控制水平高度
                    }
                    ::-webkit-scrollbar-track { /*背景槽*/
                        /*background-color:#dd;*/
                        border-radius:6px
                    }
                    ::-webkit-scrollbar-thumb { /*拖动条*/
                        background-color: rgba(0, 0, 0, .3);
                        border-radius:6px;
                    }
                </style>
                <br>
                <br>
                <form class="form-horizontal">
                    <%--<div  class="form-group hidden">--%>
                    <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 12px;">
                        <button type="button" class="btn btn-default" onclick="cancelProofread()">取消</button>
                        <%--<button type="button" class="btn btn-default" onclick="draftProofread()">保存草稿</button>--%>
                        |
                        <button type="button" id = "fail" class="btn btn-primary" onclick="failProofread('${session.userLogin.userName}',${param.devStatus})">不通过</button>
                        <button type="button" id = "pass" class="btn btn-primary" onclick="passProofread('${session.userLogin.userName}',${param.devStatus})">通过</button>
                    </div>

                </form>
            </div>
        </div>
    </div>



    <div class="hidden">
        <a id="changeLimitValueModal" class="col-lg-1 btn btn-default" data-toggle="front-modal" data-size="modal-lg"
           data-href="views/develop/manage/changeLimitValues.jsp">
        </a>
    </div>

    <s:include value="../../_footer.jsp"/>
</div>
<!-- 可直接使用框架提供的在线js文件 -->
<!-- 若要使用本地的文件，请注释掉下面三行标记 -->
<script type="text/javascript" src="repack/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="repack/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="repack/js/plugin/front.js"></script>
<%--<script type="text/javascript" src="js/develop/new.js"></script>--%>
<script type="text/javascript" src="js/develop/modify.js"></script>
<script type="text/javascript" src="js/develop/project.js"></script>
<script type="text/javascript" src="js/develop/manage.js"></script>
<script type="text/javascript" src="js/develop/proofread.js"></script>
<script>
    //记录标准限值对应的图编号以及对应的描述信息
    imgDescription = "";
    imgNum = "";
    project = "";
    imgSrc = "";
    imgMinX = "";
    imgMaxX = "";
    imgMinY = "";
    imgMaxY = "";
    imgAxisX = "";
    imgAxisY = "";

    //文字类型的限值修改
    textDescription = "";
    textNum = "";

    //标准限值有两张图
    imgNum1 = "";
    imgNum2 = "";
    picOneInfo = null;
    picTwoInfo = null;

    //已点击过修改限值的map
    stdValuesChangeMap = {};
    stdTextChangeMap = {};
    stdBiPicChangeMap = {};

    //分系统或项目信息
    subsysOrEqpName = '';
    subsysOrEqpModel = '';
    subsysOrEqpNum = '';

    devId = -1;
    devStatus = -1;
    devName = -1;
    var params = getParams();
    devName = params['devName'];
    devId = params['devId'];
    devStatus = params['devStatus'];
    if(devStatus == 1){
        $("#advice_title_div").show();
        $("#advice_div").show();
        $("#advice").attr("placeholder","输入校对意见");
        document.getElementById("advice_title").innerHTML="校对意见";
        document.getElementById("pass").innerHTML="校对通过";
        document.getElementById("fail").innerHTML="校对不通过";
    }else if(devStatus == 2){
        $("#advice_title_div").show();
        $("#advice_div").show();
        $("#advice").attr("placeholder","输入审核意见");
        document.getElementById("advice_title").innerHTML="审核意见";
        document.getElementById("pass").innerHTML="审核通过";
        document.getElementById("fail").innerHTML="审核不通过";
    }else if(devStatus == 3){
        $("#advice_title_div").show();
        $("#advice_div").show();
        $("#advice").attr("placeholder","输入批准意见");
        document.getElementById("advice_title").innerHTML="批准意见";
        document.getElementById("pass").innerHTML="批准通过";
        document.getElementById("fail").innerHTML="批准不通过";
    }else{
        $("#advice_title_div").hide();
        $("#advice_div").hide();
    }
    var devAdviceProofread = "";
    var devAdviceAudit = "";
    var devAdviceAuthorize = "";
    var devItemid = "";

    // showProjectInfo(2);
    // $.fillTipBox({type: 'warning',icon: 'glyphicon-exclamation-sign', content: "校对结果提交成功"});

    $.ajax({
        type: "get", // 请求类型（get/post）
        url: "/GJB151BSys/modify/findProjectById",
        data: {"devId":devId},
        async: true, // 是否异步
        dataType: "json", // 设置数据类型
        success: function (data){
            console.log("请求成功");
            if(data.status === 'error')  {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
            }else {
                fillInfo(data.data);
                devAdviceProofread=data.data.devAdviceProofread;
                devAdviceAudit=data.data.devAdviceAudit;
                devAdviceAuthorize=data.data.devAdviceAuthorize;
                devItemid=data.data.devItemid;
                console.log("这是页面的devitemid"+devItemid);
                console.log("devAdviceProofread是"+devAdviceProofread);
                // var test1 = "[\"这是第一条历史数据库校对意见\",\"这是第二条历史数据库校对意见\"]";
                // console.log(JSON.parse(devAdviceProofread));
                historyAdviceProofread(JSON.parse(devAdviceProofread));
                historyAdviceAudit(JSON.parse(devAdviceAudit));
                historyAdviceAuthorize(JSON.parse(devAdviceAuthorize));
            }
        },
        error: function (errorMsg){
            // 请求失败
            console.log("请求失败");
        }
    });


    //测试
    $("#subsys_eqp").val("1");
    var dataObj = new Date();
    console.log(dataObj);
    var test1 = "#这是测试的话哟～";
    console.log(test1.substr(0,2));
    console.log(test1.substring(2));
    var userName = '${sessionScope.userLogin.userName}';
    console.log("页面的username"+userName);
    // $("#advice").attr("placeholder","输入审核意见");
    // document.getElementById("advice_title").innerHTML="审核意见";
    // $("#eqp_name").attr("readonly","readonly");



    //调整校对意见输入框的大小
    // var textarea = document.querySelector('textarea');
    // textarea.addEventListener('keydown', autosize);
    // function autosize(){
    //     var el = this;
    //     setTimeout(function(){
    //         el.style.cssText = 'height:auto; padding:0';
    //         // for box-sizing other than "content-box" use:
    //         // el.style.cssText = '-moz-box-sizing:content-box';
    //         el.style.cssText = 'height:' + el.scrollHeight + 'px';
    //     },0);
    // }
</script>
</body>
</html>
