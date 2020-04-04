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
    <script type="text/javascript" src="repack/js/jquery/jquery.min.js"></script>
    <link rel="stylesheet" href="repack/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="repack/css/front.css">
    <link rel="stylesheet" href="statics/css/develop/new/new.css">
</head>
<body class="front-body">
<div class="front-inner">
    <%--<s:include value="../_nav.jsp?act=new"/>--%>
        <s:include value="../_nav.jsp?">
            <s:param name="userName">userName</s:param>
            <s:param name="act">${param.devStatus}</s:param>
        </s:include>
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
                        <td style="width: 20%">任务来源</td>
                        <td><input type="text" class="form-control radius" id="subsys_source" ></input></td>
                    </tr>
                    <tr>
                    <td style="width: 20%">编制依据和引用文件</td>
                    <td><input type="text" class="form-control radius" id="subsys_com_ref" ></input></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">被试品数量及技术状态要求</td>
                        <td><input type="text" class="form-control radius" id="subsys_quantity" ></input></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">试验环境与条件要求</td>
                        <td><input type="text" class="form-control radius" id="subsys_environment" ></input></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">分系统/设备</td>
                        <td>
                            <select class="form-control" id="subsys_eqp">
                                <option value="0" selected>分系统</option>
                                <option value="1">设备</option>
                            </select>
                        </td>
                    </tr>
                    <!--选择分系统时显示-->
                    <tbody id="subsys">
                    <tr>
                        <td style="width: 20%">分系统名称</td>
                        <td><input type="text" class="form-control radius" id="subsys_name"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">型号</td>
                        <td><input type="text" class="form-control radius" id="subsys_model"></td>
                    </tr>
                    </tbody>
                    <tbody id="eqp" class="hidden">
                    <tr>
                        <td style="width: 20%">设备名称</td>
                        <td><input type="text" class="form-control radius" id="eqp_name"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">型号</td>
                        <td><input type="text" class="form-control radius" id="eqp_model"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">串号</td>
                        <td><input type="text" class="form-control radius" id="eqp_num"></td>
                    </tr>
                    </tbody>
                    <tr>
                        <td style="width: 20%">承制单位</td>
                        <td><input type="text" class="form-control radius" id="supplier"></td>
                    </tr>
                    <tr>
                        <td style="width: 20%">预定使用平台（一级平台）</td>
                        <td>
                            <select id="primary_platform" class="form-control">
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
                            <select id="secondary_platform" class="form-control">
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
                            <select class="form-control" id="attribute">
                                <option value="1" selected>用频</option>
                                <option value="0">非用频</option>
                            </select>
                        </td>
                        <td>
                            <select class="form-control" id="key_equipment">
                                <option value="1" selected>是</option>
                                <option value="0">否</option>
                            </select>
                        </td>
                        <td>
                            <select class="form-control" id="install_mode">

                                <option value="desktop" selected>台式</option>
                                <option value="frame">机架安装</option>
                                <option value="board">甲板固定</option>
                                <option value="hang">壁挂</option>
                                <option value="hold">手持</option>
                                <option value="no_hold">非手持</option>
                            </select>
                        </td>
                        <td>
                            <select class="form-control" id="ground_line">
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

                            <select class="form-control" id="special_equipment">
                                <option value="0" selected>无</option>
                                <option value="1">（水面舰船和潜艇平台可选）工作频率低于100kHz，灵敏度优于1V</option>
                                <option value="2">（陆军地面可选）扫雷和探雷的机动车辆</option>
                                <option value="3">（海军飞机可选）反潜机</option>
                            </select>
                        </td>
                        <td style="width: 25%">
                            <select class="form-control" id="interconnected_port">
                                <option value="1" selected>有</option>
                                <option value="0">无</option>
                            </select>
                        </td>
                        <td style="width: 25%">
                            <select class="form-control" id="low_frequency_sensitive">
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

                            <select class="form-control" id="emp_reinforce">
                                <option value="1" selected>有</option>
                                <option value="0">无</option>
                            </select>
                        </td>
                        <td colspan="2">
                            <select class="form-control" id="static_electricity">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td colspan="4">电源端口</td>--%>
                    <%--</tr>--%>

                    <%--天线端口--%>
                    <%--<tr id="antenna_name">--%>
                        <%--<td colspan="4">天线端口</td>--%>
                    <%--</tr>--%>
                    <%--<tr id="antenna_attribute">--%>
                        <%--<td colspan="2">--%>
                            <%--<select id="antenna_removal" class="form-control">--%>
                                <%--<option value="none">无天线端口</option>--%>
                                <%--<option value="removal" selected>天线可拆卸</option>--%>
                                <%--<option value="no_removal">天线不可拆卸</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<select id="receive_launch" class="form-control">--%>
                                <%--<option value="receive" selected>接收</option>--%>
                                <%--<option value="launch" >发射</option>--%>
                                <%--<option value="receive_and_launch">收/发</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<select id="anolitude_modulation" class="form-control">--%>
                                <%--<option value="am" selected>调幅</option>--%>
                                <%--<option value="no_am">非调幅</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>

                </table>
            </div>
        <div id="pow_1">
            <div class="panel-body">
                <table class="table" style="margin-bottom: 0px">
                    <tr>
                        <td id="powerNumb">电源类型1</td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <input id="power_name1" name="power_name" type="text" class="form-control radius opt_ave_pow_transmit_max" placeholder="电源端口名称">
                        </td>
                        <td>
                            <select id="power_port1" class="form-control" name="power_port">
                                <option value="input_port" selected>外部电源输入端口</option>
                                <option value="other_port">其他电源端口</option>
                                <option value="no_port">无电源端口</option>
                            </select>
                        </td>
                        <td>
                            <select id="power_supply1" class="form-control" name="power_supply">
                                <option value="platform_main" selected>固态电源</option>
                                <option value="no_platform_main">非固态电源</option>
                            </select>
                        </td>
                        <td>
                            <select id="voltage1" class="form-control" name="voltage">
                                <option value="direct_current" selected>直流</option>
                                <option value="alternate_current50">交流50Hz</option>
                                <option value="alternate_current400">交流400Hz</option>
                            </select>
                        </td>
                        <td>
                            <select id="voltage_num1" class="form-control" name="voltage_num">
                                <option value="smaller_than" selected><= 28V</option>
                                <option value="bigger_than">> 28V</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="panel-body" >
                <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 10px;" id="delete_new_power">
                    <button class="btn btn-primary" onclick="deletePower(this);">删除</button>
                    <button class="btn btn-primary" onclick="addPower();" id="add_power">新建</button>
                </div>
            </div>
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

            <%--用频方式选择--%>
            <%--<div class="panel-heading" style="text-align: center;">--%>
                <%--<span class="panel-title">用频方式选择</span>--%>
            <%--</div>--%>
            <%--<div id="fre_select">--%>
                <%--<div class="panel-body">--%>
                    <%--<br>--%>
                    <%--<table class="table">--%>
                        <%--<tr>--%>
                            <%--<td style="width: 20%">用频方式选择</td>--%>
                            <%--<td colspan="4">--%>
                                <%--<select class="form-control opt_freq_mode" id="fre_select_option">--%>
                                    <%--<option value="1" selected>可选用频方式</option>--%>
                                    <%--<option value="2">跳频</option>--%>
                                    <%--<option value="3">直序扩频</option>--%>
                                <%--</select>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                    <%--</table>--%>
                <%--</div>--%>
            <%--</div>--%>


            <div class="panel-heading" style="text-align: center;" id="fre_title">
                <span class="panel-title">可选用频方式</span>
            </div>
            <div id="opt_1">
                <div class="panel-body">
                    <br>
                    <table class="table">
                        <tr>
                            <td style="width: 20%">天线端口名称或ID</td>
                            <td colspan="4"><input type="text" class="form-control radius opt_port_name"></td>
                        </tr>
                        <tr>
                            <td style="width: 20%">用频方式选择</td>
                            <td colspan="4">
                                <select class="form-control opt_fre_select_option" id="opt_fre_select_option">
                                    <option value="1" selected>可选用频方式</option>
                                    <option value="2">跳频</option>
                                    <option value="3">直序扩频</option>
                                    <option value="4">其他</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 20%">频率特性</td>
                            <td colspan="4">
                                <select class="form-control opt_freq_mode">
                                    <option value="1" selected>固定</option>
                                    <option value="2">连续可调</option>
                                    <option value="3">其他</option>
                                </select>
                            </td>
                        </tr>
                        <%--<tr>--%>
                            <%--<td colspan="4">频率特性</td>--%>
                        <%--</tr>--%>
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
                                <input type="text" class="form-control radius opt_freq_low">
                            </td>
                            <td>
                                <input type="text" class="form-control radius opt_freq_mid">
                            </td>
                            <td>
                                <input type="text" class="form-control radius opt_freq_high">
                            </td>
                            <td>
                                <input type="text" class="form-control radius opt_freq_points" placeholder="固定多频点时填写">
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 20%">工作方式</td>
                            <td colspan="4">
                                <select class="form-control opt_work_style">
                                    <option value="2" selected>收</option>
                                    <option value="1">发</option>
                                    <option value="3">收发共用</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 20%">安装方式</td>
                            <td colspan="4">
                                <select class="form-control opt_install_mode">
                                    <option value="1" selected>可拆卸</option>
                                    <option value="2">不可拆卸</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 20%">连接方式</td>
                            <td colspan="4">
                                <select class="form-control opt_connect_mode" id="opt_connect_mode_1" name="opt_connect_mode">
                                    <option value="1" selected>同轴</option>
                                    <option value="2">波导</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 20%" class="opt_cutoff_fre_name hidden" id="opt_cutoff_fre_name_1" name="opt_cutoff_fre_name">波导截止频率（MHz）</td>
                            <td colspan="4"><input type="text" class="form-control radius opt_wave_guide_transmit_max hidden" id="opt_wave_guide_transmit_max_1" name="opt_wave_guide_transmit_max">
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 20%">最大发射平均功率dB（W）</td>
                            <td colspan="4"><input type="text" class="form-control radius opt_ave_pow_transmit_max">
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 20%">调制方式</td>
                            <td colspan="4">
                                <select class="form-control opt_modulation_mode">
                                    <option value="1" selected>调幅</option>
                                    <option value="2">调频</option>
                                    <option value="3">其他</option>
                                </select>
                            </td>
                        </tr>
                        <%--<tr>--%>
                            <%--<td style="width: 20%">调制方式<br>--%>
                                <%--<input id="decrease" type=button onclick="decrease(this)" style="border:none" value="-">--%>
                                <%--<input style="width:25px;border:none;outline:none;text-align:center"--%>
                                       <%--class="opt_modulation_mode_num"--%>
                                       <%--type=text name=amount readonly value="1">--%>
                                <%--<input id="increase" type=button onclick="increase(this)" style="border:none" value="+">--%>
                            <%--</td>--%>
                            <%--<td colspan="4">--%>
                                <%--<div class="opt_modulation_mode_1"><input type="text" id="way"--%>
                                                                          <%--class="form-control radius opt_modulation_mode_1">--%>
                                <%--</div>--%>
                            <%--</td>--%>
                        <%--</tr>--%>

                        <tr>
                            <td style="width: 20%" >固定频点（MHz）</td>
                            <td colspan="4"><input type="text" class="form-control radius opt_fix_trans_point" placeholder="跳频时填写">
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 20%" class="trans_name " id="trans_name_1" name="trans_name">传输速率</td>
                            <td colspan="4"><input type="text" class="form-control radius opt_trans_speed " id="opt_trans_speed_1" name="opt_trans_speed" placeholder="直序扩频时填写">
                            </td>
                        </tr>

                        <%--<tr>--%>
                            <%--<td style="width: 20%">端口名称或ID</td>--%>
                            <%--<td colspan="4"><input type="text" class="form-control radius opt_port_name"></td>--%>
                        <%--</tr>--%>
                    </table>

                </div>
                <div class="panel-body">
                    <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 10px;" id="delete_new">
                        <button class="btn btn-primary" onclick="deleteFrequency(this);">删除</button>
                        <button class="btn btn-primary" onclick="addFrequency();" id="add">新建</button>
                    </div>
                </div>
            </div>

            <%--//跳频--%>
            <%--<div class="panel-heading hidden" style="text-align: center;" id="FH_low_title">--%>
                <%--<span class="panel-title">跳频</span>--%>
            <%--</div>--%>
            <%--<div id="FH_low" class="panel-body hidden">--%>
                <%--<table class="table">--%>
                    <%--<tr>--%>
                        <%--<td colspan="4">频率特性</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>频段</td>--%>
                        <%--<td>最低频率(MHz)</td>--%>
                        <%--<td>最高频率(MHz)</td>--%>
                        <%--<td>频点数</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<input id="FH_low_freq_range" type="text" class="form-control radius" readonly value="低频段">--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input id="FH_low_freq_low" type="text" class="form-control radius">--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input id="FH_low_freq_high" type="text" class="form-control radius">--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input id="FH_low_freq_points" type="text" class="form-control radius" placeholder="固定多频点时填写">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">工作方式</td>--%>
                        <%--<td colspan="4">--%>
                            <%--<select id="FH_low_work_style" class="form-control">--%>
                                <%--<option value="2" selected>收</option>--%>
                                <%--<option value="1">发</option>--%>
                                <%--<option value="3">收发共用</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">安装方式</td>--%>
                        <%--<td colspan="4">--%>
                            <%--<select id="FH_low_install_mode" class="form-control">--%>
                                <%--<option value="1">可拆卸</option>--%>
                                <%--<option value="2">不可拆卸</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">最大发射平均功率dB（W）</td>--%>
                        <%--<td colspan="4"><input id="FH_low_ave_pow_transmit_max" type="text" class="form-control radius">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">端口名称或ID</td>--%>
                        <%--<td colspan="4"><input id="FH_low_port_name" type="text" class="form-control radius"></td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</div>--%>
            <%--<div class="panel-heading hidden" style="text-align: center;" id="FH_mid_title">--%>
                <%--<span class="panel-title">跳频</span>--%>
            <%--</div>--%>
            <%--<div id="FH_mid" class="panel-body hidden">--%>
                <%--<table class="table">--%>
                    <%--<tr>--%>
                        <%--<td colspan="4">频率特性</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>频段</td>--%>
                        <%--<td>最低频率(MHz)</td>--%>
                        <%--<td>最高频率(MHz)</td>--%>
                        <%--<td>频点数</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<input id="FH_mid_freq_range" type="text" class="form-control radius" readonly value="中频段">--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input id="FH_mid_freq_low" type="text" class="form-control radius">--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input id="FH_mid_freq_high" type="text" class="form-control radius">--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input id="FH_mid_freq_points" type="text" class="form-control radius" placeholder="固定多频点时填写">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">工作方式</td>--%>
                        <%--<td colspan="4">--%>
                            <%--<select id="FH_mid_work_style" class="form-control">--%>
                                <%--<option value="2" selected>收</option>--%>
                                <%--<option value="1">发</option>--%>
                                <%--<option value="3">收发共用</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">安装方式</td>--%>
                        <%--<td colspan="4">--%>
                            <%--<select id="FH_mid_install_mode" class="form-control">--%>
                                <%--<option value="1">可拆卸</option>--%>
                                <%--<option value="2">不可拆卸</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">最大发射平均功率dB（W）</td>--%>
                        <%--<td colspan="4"><input id="FH_mid_ave_pow_transmit_max" type="text" class="form-control radius">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">端口名称或ID</td>--%>
                        <%--<td colspan="4"><input id="FH_mid_port_name" type="text" class="form-control radius"></td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</div>--%>
            <%--<div class="panel-heading hidden" style="text-align: center;" id="FH_high_title">--%>
                <%--<span class="panel-title">跳频</span>--%>
            <%--</div>--%>
            <%--<div id="FH_high" class="panel-body hidden">--%>
                <%--<table class="table">--%>
                    <%--<tr>--%>
                        <%--<td colspan="4">频率特性</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>频段</td>--%>
                        <%--<td>最低频率(MHz)</td>--%>
                        <%--<td>最高频率(MHz)</td>--%>
                        <%--<td>频点数</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<input id="FH_high_freq_range" type="text" class="form-control radius" readonly value="高频段">--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input id="FH_high_freq_low" type="text" class="form-control radius">--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input id="FH_high_freq_high" type="text" class="form-control radius">--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input id="FH_high_freq_points" type="text" class="form-control radius" placeholder="固定多频点时填写">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">工作方式</td>--%>
                        <%--<td colspan="4">--%>
                            <%--<select id="FH_high_work_style" class="form-control">--%>
                                <%--<option value="2" selected>收</option>--%>
                                <%--<option value="1">发</option>--%>
                                <%--<option value="3">收发共用</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">安装方式</td>--%>
                        <%--<td colspan="4">--%>
                            <%--<select id="FH_high_install_mode" class="form-control">--%>
                                <%--<option value="1">可拆卸</option>--%>
                                <%--<option value="2">不可拆卸</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">最大发射平均功率dB（W）</td>--%>
                        <%--<td colspan="4"><input id="FH_high_ave_pow_transmit_max" type="text"--%>
                                               <%--class="form-control radius"></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">端口名称或ID</td>--%>
                        <%--<td colspan="4"><input id="FH_high_port_name" type="text" class="form-control radius"></td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</div>--%>

            <%--//直序扩频--%>
            <%--<div class="panel-heading hidden" style="text-align: center;" id="DSSS_title">--%>
                <%--<span class="panel-title">直序扩频</span>--%>
            <%--</div>--%>
            <%--<div class="panel-body hidden" id="DSSS">--%>
                <%--<table class="table">--%>
                    <%--<tr>--%>
                        <%--<td colspan="4">频率特性</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td colspan="2">最高传输速率（bit/s)</td>--%>
                        <%--<td colspan="2">频点数</td>--%>
                    <%--</tr>--%>
                    <%--<tr style="width: 100%">--%>
                        <%--<td colspan="2" style="width: 50%">--%>
                            <%--<input id="DSSS_trans_rate_max" type="text" class="form-control radius">--%>
                        <%--</td>--%>
                        <%--<td colspan="2" style="width: 50%">--%>
                            <%--<input id="DSSS_freq_points" type="text" class="form-control radius">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">工作方式</td>--%>
                        <%--<td colspan="4">--%>
                            <%--<select id="DSSS_work_style" class="form-control">--%>
                                <%--<option value="2" selected>收</option>--%>
                                <%--<option value="1">发</option>--%>
                                <%--<option value="3">收发共用</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">安装方式</td>--%>
                        <%--<td colspan="4">--%>
                            <%--<select id="DSSS_install_mode" class="form-control">--%>
                                <%--<option value="1">可拆卸</option>--%>
                                <%--<option value="2">不可拆卸</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">最大发射平均功率dB（W）</td>--%>
                        <%--<td colspan="4"><input id="DSSS_ave_pow_transmit_max" type="text" class="form-control radius">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="width: 20%">端口名称或ID</td>--%>
                        <%--<td colspan="4"><input id="DSSS_port_name" type="text" class="form-control radius"></td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</div>--%>
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
                <table id="standard_subject_table" class="table">
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
                <span class="panel-title">剩余项目</span>
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
        <form class="form-horizontal">
            <div id="submit" class="form-group hidden">
                <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 12px;" id="cancel_draft_save">
                    <button type="button" class="btn btn-default" onclick="cancel()">取消</button>
                    |
                    <button type="button" class="btn btn-default" onclick="submitProject('draft','${session.userLogin.userName}','${session.userLogin.userId}')">保存草稿</button>
                    <button type="button" class="btn btn-primary" onclick="submitProject('save','${session.userLogin.userName}','${session.userLogin.userId}')">提交报告</button>
                </div>
            </div>
        </form>
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
            <br>
            <br>
            <form class="form-horizontal">
                <div class="col-lg-offset-8 col-lg-4 text-right" style="padding-right: 12px;">
                    <button type="button" class="btn btn-default" onclick="cancelProofread()">取消</button>
                    |
                    <button type="button" id = "fail" class="btn btn-primary" onclick="failProofread('${session.userLogin.userName}',${param.devStatus}, ${session.userLogin.userId})">不通过</button>
                    <button type="button" id = "pass" class="btn btn-primary" onclick="passProofread('${session.userLogin.userName}',${param.devStatus}, ${session.userLogin.userId})">通过</button>
                </div>
            </form>
        </div>
    </div>
    </div>



    <div class="hidden">
        <a id="changeLimitValueModal" class="col-lg-1 btn btn-default" data-toggle="front-modal" data-size="modal-lg"
           data-href="views/develop/manage/changeLimitValues.jsp?devStatus=${param.devStatus}&Status=${param.Status}">
        </a>
    </div>

    <s:include value="../../_footer.jsp"/>
</div>
<!-- 可直接使用框架提供的在线js文件 -->
<!-- 若要使用本地的文件，请注释掉下面三行标记 -->
<script type="text/javascript" src="repack/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="repack/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="repack/js/plugin/front.js"></script>
<script type="text/javascript" src="js/develop/new.js"></script>
<script type="text/javascript" src="js/develop/modify.js"></script>
<script type="text/javascript" src="js/develop/project.js"></script>
<script type="text/javascript" src="js/develop/manage.js"></script>
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
    Status = 0;
    var params = getParams();
    devName = params['devName'];
    devId = params['devId'];
    devStatus = params['devStatus'];
    Status = params['Status'];

    //填充页面意见框标题等内容
    adviceTitle(devStatus,Status);
    //记录项目历史意见信息
    var devAdviceProofread = "";
    var devAdviceAudit = "";
    var devAdviceAuthorize = "";
    var devItemid = "";

    // if (devStatus == 4) {
    //     debugger
    //     showProjectInfo(devId);
    // }
    // $("#ttest").val("0");
    //任务头部信息textarea文本框随内容变化而变化
    if(devStatus == 0 || devStatus == 4) {
        $('textarea').each(function () {
            this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
        }).on('input', function () {
            this.style.height = 'auto';
            this.style.height = (this.scrollHeight) + 'px';
        });
    }

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
                //在项目阶段非编制情况下，恢复项目历史信息
                if(devStatus != 0){
                    fillInfo(data.data);
                }
                //记录并展示历史校对/审核/批准意见
                devAdviceProofread=data.data.devAdviceProofread;
                devAdviceAudit=data.data.devAdviceAudit;
                devAdviceAuthorize=data.data.devAdviceAuthorize;
                devItemid=data.data.devItemid;
                console.log("devAdviceProofread是"+devAdviceProofread);
                historyAdviceProofread(JSON.parse(devAdviceProofread));
                historyAdviceAudit(JSON.parse(devAdviceAudit));
                historyAdviceAuthorize(JSON.parse(devAdviceAuthorize));
            }
        },
        error: function (errorMsg){
            // 请求失败
            console.log("请求失败");
            console.log(errorMsg);
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: "请求失败"});
        }
    });

</script>
</body>
</html>
