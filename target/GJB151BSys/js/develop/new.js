page_num = 3;
console.log($("#primary_platform").val());
console.log($("#secondary_platform").val());
var conditionMap = {
    'direct_current': 1, 'alternate_current50': 2, 'alternate_current400': 3,
    'smaller_than': 1, 'bigger_than': 2,
    'receive': 2, 'launch': 1, 'receive_and_launch': 3,
    'removal': 1, 'no_removal': 2
};

var power_port_map = {"no_port": 0, "input_port": 1, "other_port": 2};
var power_supply_map = {"none": -1, "no_platform_main": 0, "platform_main": 1};
var voltage_map = {"none": -1, "direct_current": 1, "alternate_current50": 2, "alternate_current400": 3};
var voltage_num_map = {"none": -1, "smaller_than": 1, "bigger_than": 2};
var special_equipment_map = {"0": 0, "1": 1, "2": 2, "3": 3};
var antenna_removal_map = {"no_antenna": 0, "removal": 1, "no_removal": 2};
var receive_launch_map = {"none": -1, "launch": 1, "receive": 2, "receive_and_launch": 3};
var anolitude_modulation_map = {"none": -1, "am": 1, "no_am": 2};
var static_electricity_map = {"0": 0, "1": 1};
var key_equipment_map = {"0": 0, "1": 1};
var install_mode_map = {"desktop": 1, "frame": 2, "board": 3, "hang": 4, "hold": 5, "no_hold": 6};

var install_mode_reverse_map = {"1": 'desktop', "2": 'frame', "3": 'board', "4": 'hang', "5": 'hold', "6": 'no_hold'};
var power_port_reverse_map = {"0": 'no_port', "1": 'input_port', "2": 'other_port'};
var power_supply_reverse_map = {"-1": 'none', "0": 'no_platform_main', "1": 'platform_main'};
var voltage_reverse_map = {
    "-1": 'none',
    "1": 'direct_current',
    "2": 'alternate_current50',
    "3": 'alternate_current400'
};
var voltage_num_reverse_map = {"-1": 'none', "1": 'smaller_than', "2": 'bigger_than'};
var antenna_removal_reverse_map = {"0": 'no_antenna', "1": 'removal', "2": 'no_removal'};
var receive_launch_reverse_map = {"-1": 'none', "1": 'launch', "2": 'receive', "3": 'receive_and_launch'};
var anolitude_modulation_reverse_map = {"-1": 'none', "1": 'am', "2": 'no_am'};
var fre_select_map = {"1":1, "2":2, "3":3};


var standard_list = [];
var protocol_list = [];
var extra_list = [];

//1 文字 2 双图 3 单曲线图 4 单曲线图+文字 5 单折线图+文字 6 单折线图 7 特殊处理的项目
stdLimitMap = {
    'CE101': {'project_id': 'CE101', 'type': 6, 'limit_value': [{},{},{}], 'limit_value_current': [{},{},{}]},
    'CE102': {'project_id': 'CE102', 'type': 6, 'limit_value': {}, 'limit_value_current': {}},
    'CE106': {'project_id': 'CE106', 'type': 1, 'limit_value': {}, 'limit_value_current': {}},
    'CE107': {'project_id': 'CE107', 'type': 1, 'limit_value': [{},{}], 'limit_value_current': [{},{}]},
    'CS101': {'project_id': 'CS101', 'type': 2, 'limit_value': [{},{}], 'limit_value_current': [{},{}]},
    'CS102': {'project_id': 'CS102', 'type': 1, 'limit_value': {}, 'limit_value_current': {}},
    'CS103': {'project_id': 'CS103', 'type': 7, 'limit_value': {}, 'limit_value_current': {}},
    'CS104': {'project_id': 'CS104', 'type': 7, 'limit_value': {}, 'limit_value_current': {}},
    'CS105': {'project_id': 'CS105', 'type': 7, 'limit_value': {}, 'limit_value_current': {}},
    'CS106': {'project_id': 'CS106', 'type': 1, 'limit_value': {}, 'limit_value_current': {}},
    'CS109': {'project_id': 'CS109', 'type': 6, 'limit_value': {}, 'limit_value_current': {}},
    'CS112': {'project_id': 'CS112', 'type': 1, 'limit_value': {}, 'limit_value_current': {}},
    'CS114': {'project_id': 'CS114', 'type': 6, 'limit_value': {}, 'limit_value_current': {}},
    'CS115': {'project_id': 'CS115', 'type': 4, 'limit_value': {}, 'limit_value_current': {}},
    'CS116': {'project_id': 'CS116', 'type': 5, 'limit_value': {}, 'limit_value_current': {}},
    'RE101': {'project_id': 'RE101', 'type': 6, 'limit_value': {}, 'limit_value_current': {}},
    'RE102': {'project_id': 'RE102', 'type': 6, 'limit_value': {}, 'limit_value_current': {}},
    'RE103': {'project_id': 'RE103', 'type': 1, 'limit_value': {}, 'limit_value_current': {}},
    'RS101': {'project_id': 'RS101', 'type': 6, 'limit_value': {}, 'limit_value_current': {}},
    'RS103': {'project_id': 'RS103', 'type': 6, 'limit_value': {}, 'limit_value_current': {}},
    'RS105': {'project_id': 'RS105', 'type': 3, 'limit_value': {}, 'limit_value_current': {}},
};

stdTextList = ['CE106', 'CE107', 'CS102', 'CS106', 'RE103', 'CS112'];  //限值类型为文字的项目表
stdBiPicList = ['CS101'];  //限值类型为双图的项目表
stdCurveList = ['RS105'];  //限值类型为单曲线图的项目表
stdCurveTextList = ['CS115'];  //限值类型为单曲线图+文字的项目表
stdLineTextList = ['CS116'];  //限值类型为单折线图+文字的项目表
stdLineList = ['CE101', 'CE102', 'CS109', 'CS114', 'RE101', 'RE102', 'RS101', 'RS103'];  //限值类型为单折线图的项目表
stdSpecialList = ['CS103', 'CS104', 'CS105'];  //特殊处理限值录入的三个项目

stdLimitNameMap = {
    "8" : "适用于水面舰船和潜艇的CE101限值（DC）",
    "9" : "适用于水面舰船和潜艇的CE101限值（50Hz）",
    "10": "适用于水面舰船和潜艇的CE101限值（400Hz）",
    "11_1": "适用于海军ASW飞机，陆军飞机（包括机场维护工作区）和空间系统的CE101限值（受试设备额定电源电压（AC和DC）>28V )",
    "11_2": "适用于海军ASW飞机，陆军飞机（包括机场维护工作区）和空间系统的CE101限值（受试设备额定电源电压（AC和DC）<=28V )",
    "14": "CE102限值（AC和DC）",
    "CE106_a": "EUT天线端口传导发射不应超过以下限值（接收）",
    "CE106_b_c": "EUT天线端口传导发射不应超过以下限值（发射）",
    "CE106_a_b_c": "EUT天线端口传导发射不应超过以下限值（收/发）",
    "CE107_1": "随手动或自动开关操作而产生的开关瞬态传导发射不应超过下列值（交流电源线）",
    "CE107_2": "随手动或自动开关操作而产生的开关瞬态传导发射不应超过下列值（直流电源线）",
    "21_1" : "CS101电压限值（受试设备额定电源电压 >28V）",
    "21_2" : "CS101电压限值（受试设备额定电源电压 <=28V）",
    "22"   : "CS101功率限值",
    "CS102": "CS102限值",
    "CS106": "CS106限值",
    "37" : "CS109限值",
    "CS112_10_11_A" : "限值（A类EUT）",
    "CS112_10_11_B" : "限值（B类EUT）",
    "39_1" : "CS114校验限值",
    "39_2" : "CS114校验限值",
    "39_3" : "CS114校验限值",
    "39_4" : "CS114校验限值",
    "39_5" : "CS114校验限值",
    "39_6" : "CS114校验限值",
    "39_7" : "CS114校验限值",
    "39_8" : "CS114校验限值",
    "39_9" : "CS114校验限值",
    "39_10" : "CS114校验限值",
    "39_11" : "CS114校验限值",
    "39_12" : "CS114校验限值",
    "39_13" : "CS114校验限值",
    "44" : "CS115波形",
    "CS115" : "CS115限值",
    "48" : "CS116限值",
    "CS116" : "CS116限值",
    "51" : "适用于陆军的RE101限值",
    "52" : "适用于海军的RE101限值",
    "55_1" : "适用于水面舰船的RE102限值（甲板下）",
    "55_2" : "适用于水面舰船的RE102限值（甲板上）",
    "56_1" : "适用于潜艇的RE102限值（压力舱内）",
    "56_2" : "适用于潜艇的RE102限值（压力舱外）",
    "57_1" : "适用于飞机和空间系统的RE102限值（固定翼飞机内部（首尾间距≧25m））",
    "57_2" : "适用于飞机和空间系统的RE102限值（固定翼飞机内部（首尾间距<25m））",
    "57_3" : "适用于飞机和空间系统的RE102限值（固定翼飞机外部（2MHz～18GHz）和直升机）",
    "58_1" : "适用于地面的RE102限值（海军（固定的）和空军）",
    "58_2" : "适用于地面的RE102限值（海军（移动的）和陆军）",
    "RE103": "RE103限值",
    "65" : "适用于海军的RS101限值",
    "66" : "适用于陆军的RS101限值",
    "RS103_1": "RS103限值",
    "RS103_2": "RS103限值",
    "RS103_3": "RS103限值",
    "RS103_4": "RS103限值",
    "RS103_5": "RS103限值",
    "RS103_6": "RS103限值",
    "RS103_7": "RS103限值",
    "RS103_8": "RS103限值",
    "RS103_9": "RS103限值",
    "RS103_10": "RS103限值",
    "73" : "RS105限值",
};

function submitManageSysDevelop() {
    console.log("Begin to record the ManageSysDevelop info");

    var voltage = voltage_map[$("#voltage1").val()];   //单电源端口保存
    var voltage_num = voltage_num_map[$("#voltage_num1").val()];
    // var receive_launch = receive_launch_map[$("#receive_launch").val()]; //发射接收信息
    // var antenna_removal = antenna_removal_map[$("#antenna_removal").val()]; //天线拆卸信息
    var receive_launch_CE106 = 0; //CE106发射接收信息
    var receive_launch = 0; //发射接收信息
    var antenna_removal = 0; //天线拆卸信息
    var anolitude_modulation = 0;//调制信息
    var optList= getOptList();//获取用频信息
    var optListLength = optList.length;
    var receive_launch_num = 0; //发射次数计数
    var receive_receive_num = 0; //接收次数计数
    for(var len=0; len<optListLength; len++){
        if(optList[len]['opt_work_style'] == "1"){
            receive_launch_num++;
            if(optList[len]['opt_install_mode'] == "2"){
                antenna_removal = 2;
                receive_launch = 1;
            }
        }else if(optList[len]['opt_work_style'] == "2"){
            receive_receive_num++;
        }else if(optList[len]['opt_work_style'] == "3"){
            if(optList[len]['opt_install_mode'] == "2"){
                antenna_removal = 2;
                receive_launch = 3;
            }
        }
    }
    if(receive_launch_num == optListLength){
        receive_launch_CE106 = 1;
    }else if(receive_receive_num == optListLength){
        receive_launch_CE106 = 2;
    }else{
        receive_launch_CE106 = 3;
    }
    var key_equipment = key_equipment_map[$("#key_equipment").val()];
    var static_electricity = static_electricity_map[$("#static_electricity").val()];
    var special_equipment = special_equipment_map[$("#special_equipment").val()];
    var secondary_platform = parseInt($("#secondary_platform").val());
    var attribute = parseInt($("#attribute").val());
    var power_port_list = [];
    var power_supply_list =[];
    var voltage_list = [];
    var voltage_num_list = [];
    var count = 0;
    for(var i=0; i<powerNode; i++){
        count = i+1;
        power_port_list.push(String(power_port_map[$("#power_port"+count).val()]));
        power_supply_list.push(String(power_supply_map[$("#power_supply"+count).val()]));
        voltage_list.push(String(voltage_map[$("#voltage"+count).val()]));
        voltage_num_list.push(String(voltage_num_map[$("#voltage_num"+count).val()]));
    }



    $.ajax({
        type: "post",
        url: "/GJB151BSys/new/getRequiredSubject",
        data: {
            //判定需要测试项目信息
            primary_platform: parseInt($("#primary_platform").val()),                                //一级平台
            secondary_platform: secondary_platform,                            //二级平台
            power_port: JSON.stringify(power_port_list),                                      //电源端口
            power_supply: JSON.stringify(power_supply_list),                                //平台主电源供电，更改为固态电源
            voltage: JSON.stringify(voltage_list),                                               //电源供电频率（交直流）
            voltage_num: JSON.stringify(voltage_num_list),                                   //电源供电电压
            special_equipment: special_equipment,                 //特殊设备
            antenna_removal: antenna_removal,                       //天线端口
            receive_launch: receive_launch,                          //天线端口模式
            // anolitude_modulation: anolitude_modulation_map[$("#anolitude_modulation").val()],        //天线调制模式
            anolitude_modulation: anolitude_modulation,        //天线调制模式
            ground_line: $("#ground_line").val(),                                   //带地线
            static_electricity: static_electricity,              //静电威胁
            interconnected_port: $("#interconnected_port").val(),           //互联端口
            emp_reinforce: $("#emp_reinforce").val(),                             //emp加固
            key_equipment: key_equipment,                             //任务关键设备
            install_mode: install_mode_map[$("#install_mode").val()],                                //安装方式
            low_frequency_sensitive: $("#low_frequency_sensitive").val(),  //对低频信号敏感
            optList: JSON.stringify(getOptList()),
            attribute: attribute
        },
        success: function (data) {

            // 删除原有table,重置表头
            var tb = document.getElementById('standard_subject_table');
            var rowNum = tb.rows.length;
            for (i = rowNum - 1; i > 0; i--) {
                tb.deleteRow(i);
            }
            tb = document.getElementById('protocol_subject_table');
            rowNum = tb.rows.length;
            for (i = rowNum - 1; i > 0; i--) {
                tb.deleteRow(i);
            }
            tb = document.getElementById('protocol_optional_table');
            rowNum = tb.rows.length;
            for (i = rowNum - 1; i > 0; i--){
                tb.deleteRow(i);
            }
            // 处理各个项目，并放在合适的位置上
            console.log(">> status : " + data.status);
            standard_list = String(data.data["standard"]).split(',');
            protocol_list = String(data.data["protocol"]).split(',');
            extra_list = String(data.data["extra"]).split(',');

            console.log(standard_list);
            console.log(protocol_list);
            console.log(extra_list);

            if (String(data.data["extra"]) === "") {
                extra_list = [];
            }
            if (String(data.data["protocol"]) === "") {
                protocol_list = [];
            }
            if (String(data.data["standard"]) === "") {
                standard_list = [];
            }
            standardProjectList = [];
            for(var standard_project in standard_list){
                standardProjectList.push(standard_list[standard_project]);
            }
            for(var protocol_project in protocol_list){
                standardProjectList.push(protocol_list[protocol_project]);
            }
            console.log(standardProjectList);

            // getAllStdLimit(voltage, voltage_num, receive_launch, key_equipment, static_electricity, special_equipment, antenna_removal, secondary_platform);

            if (standard_list.length === 0) {
                $("#standard_subject_table").addClass("hidden");
                $("#standard_subject_table_none").removeClass("hidden");
            } else {
                $("#standard_subject_table").removeClass("hidden");
                $("#standard_subject_table_none").addClass("hidden");
                for (var standard_list_index = 0; standard_list_index < standard_list.length; standard_list_index++) {
                    $("#standard_subject_table").append(generate_template_subject_table_element(standard_list[standard_list_index]))
                    $("#standard_subject_table").attr("disabled",true);
                    if(devStatus != 0) {
                        standard_key = standard_list[standard_list_index];
                        var projectName = 'dev' + standard_key.replace(standard_key.charAt(1), standard_key.charAt(1).toLowerCase());
                        var projectId = "#" + standard_key + "_usage";
                        var remarkId = "#" + standard_key + "_remark";
                        if(JSON.parse(allProjectInfo[projectName]).standard_usage != null) {
                            $(projectId).val(JSON.parse(allProjectInfo[projectName]).standard_usage);
                        }else{
                            $(projectId).val("1");
                        }
                        $(remarkId).val(JSON.parse(allProjectInfo[projectName]).remark);
                        if(standard_key == "CS103" || standard_key == "CS104" || standard_key == "CS105"){
                            var limitId = "#" + standard_key + "_limit";
                            $(limitId).val(JSON.parse(allProjectInfo[projectName]).limit_value_current.value);
                        }
                    }
                    if(devStatus == 1 || devStatus == 2 || devStatus == 3 || Status == 1){
                        $("input").attr("readonly","readonly");
                        $("select").attr("readonly","readonly");
                        $("select").attr("disabled","disabled");
                        // $("table button").attr("readonly","readonly");
                        // $("table button").attr("disabled","disabled");
                        $("table button").html("查看");
                    }
                }
            }

            if (protocol_list.length === 0) {
                $("#protocol_subject_table").addClass("hidden");
                $("#protocol_subject_table_none").removeClass("hidden");
            } else {
                $("#protocol_subject_table").removeClass("hidden");
                $("#protocol_subject_table_none").addClass("hidden");
                for (var protocol_list_index = 0; protocol_list_index < protocol_list.length; protocol_list_index++) {
                    $("#protocol_subject_table").append(generate_template_subject_table_element(protocol_list[protocol_list_index]))
                    if(devStatus != 0) {
                        protocol_key = protocol_list[protocol_list_index];
                        var projectName = 'dev' + protocol_key.replace(protocol_key.charAt(1), protocol_key.charAt(1).toLowerCase());
                        var projectId = "#" + protocol_key + "_usage";
                        var remarkId = "#" + protocol_key + "_remark";
                        if(JSON.parse(allProjectInfo[projectName]).protocol_usage != null) {
                            $(projectId).val(JSON.parse(allProjectInfo[projectName]).protocol_usage);
                        }else{
                            $(projectId).val("1");
                        }
                        // $(projectId).val(JSON.parse(allProjectInfo[projectName]).protocol_usage);
                        $(remarkId).val(JSON.parse(allProjectInfo[projectName]).remark);
                        if(protocol_key == "CS103" || protocol_key == "CS104" || protocol_key == "CS105"){
                            var limitId = "#" + protocol_key + "_limit";
                            $(limitId).val(JSON.parse(allProjectInfo[projectName]).limit_value_current.value);
                        }
                    }
                    if(devStatus == 1 || devStatus == 2 || devStatus == 3 || Status == 1){
                        $("input").attr("readonly","readonly");
                        $("select").attr("readonly","readonly");
                        $("select").attr("disabled","disabled");
                        // $("table button").attr("readonly","readonly");
                        // $("table button").attr("disabled","disabled");
                        $("table button").html("查看");
                    }
                }
            }

            if (extra_list.length === 0) {
                $("#protocol_optional_table").addClass("hidden");
                $("#protocol_optional_table_none").removeClass("hidden");
            } else {
                $("#protocol_optional_table").removeClass("hidden");
                $("#protocol_optional_table_none").addClass("hidden");
                for (var protocol_list_index = 0; protocol_list_index < extra_list.length; protocol_list_index++) {
                    $("#protocol_optional_table").append(generate_template_subject_table_element(extra_list[protocol_list_index]));
                    extra_key = extra_list[protocol_list_index];
                    var projId = "#" + extra_key + "_usage";
                    $(projId).val("0");
                    // $(projId).find("option[value='0']").prop("selected",true);

                    if(devStatus != 0) {
                        // extra_key = extra_list[protocol_list_index];
                        var projectName = 'dev' + extra_key.replace(extra_key.charAt(1), extra_key.charAt(1).toLowerCase());
                        var projectId = "#" + extra_key + "_usage";
                        var remarkId = "#" + extra_key + "_remark";
                        if(JSON.parse(allProjectInfo[projectName]).extra_usage != null) {
                            $(projectId).val(JSON.parse(allProjectInfo[projectName]).extra_usage);
                        }else{
                            $(projectId).val("0");
                        }
                        // $(projectId).val(JSON.parse(allProjectInfo[projectName]).extra_usage);
                        $(remarkId).val(JSON.parse(allProjectInfo[projectName]).remark);
                        if(extra_key == "CS103" || extra_key == "CS104" || extra_key == "CS105"){
                            var limitId = "#" + extra_key + "_limit";
                            $(limitId).val(JSON.parse(allProjectInfo[projectName]).limit_value_current.value);
                        }
                    }
                    if(devStatus == 1 || devStatus == 2 || devStatus == 3 || Status == 1){
                        $("input").attr("readonly","readonly");
                        $("select").attr("readonly","readonly");
                        $("select").attr("disabled","disabled");
                        // $("table button").attr("readonly","readonly");
                        // $("table button").attr("disabled","disabled");
                        $("table button").html("查看");
                    }
                }
            }

            getAllStdLimit(voltage_list, voltage_num_list, power_supply_list, receive_launch, key_equipment, static_electricity, special_equipment, antenna_removal, secondary_platform, receive_launch_CE106);

        },
        error: function (data) {
            console.log("ajax 请求失败");
            console.log(data)
        }
    });

    console.log("Finish to record ManageSysDevelop");

}

function getAllStdLimit(voltage_list, voltage_num_list, power_supply_list, receive_launch, key_equipment, static_electricity, special_equipment, antenna_removal, secondary_platform, receive_launch_CE106) {
    $.ajaxSettings.async = false;
    for (var key in stdLimitMap) {
        if ($.inArray(key, stdTextList) != -1) {
            getStdTextValue(voltage_list, receive_launch, key_equipment, static_electricity, antenna_removal, secondary_platform, key, receive_launch_CE106);
        } else if ($.inArray(key, stdBiPicList) != -1) {
            getStdBiPicValue(voltage_num_list, key);
        } else if ($.inArray(key, stdCurveList) != -1 || $.inArray(key, stdLineList) != -1) {
            getpicValue(voltage_list, voltage_num_list, power_supply_list, receive_launch, key_equipment, static_electricity, special_equipment, secondary_platform, key)
        } else if ($.inArray(key, stdCurveTextList) != -1 || $.inArray(key, stdLineTextList) != -1) {
            getStdTextValue(voltage_list, receive_launch, key_equipment, static_electricity, antenna_removal, secondary_platform, key, receive_launch_CE106);
            getpicValue(voltage_list, voltage_num_list, power_supply_list, receive_launch, key_equipment, static_electricity, special_equipment, secondary_platform, key)
        } else {
            stdLimitMap[key]['limit_value']['value'] = '';
            stdLimitMap[key]['limit_value']['bias'] = '';
            stdLimitMap[key]['limit_value_current']['value'] = '';
            stdLimitMap[key]['limit_value_current']['bias'] = '';
        }
    }


    // console.log(JSON.stringify(stdLimitMap))
}

function getStdTextValue(voltage_list, receive_launch, key_equipment, static_electricity, antenna_removal, secondary_platform, key, receive_launch_CE106) {
    CE107Num = 0;
    console.log("getText"+JSON.stringify(voltage_list));
    $.post("new/getLimitValuesText", {
        receiveLaunch: receive_launch,
        projectId: key,
        voltage: JSON.stringify(voltage_list),
        antennaRemoval: antenna_removal,
        keyEquipment: key_equipment,
        staticElectricity: static_electricity,
        secondaryPlatform: secondary_platform,
        receiveLaunchCE106: receive_launch_CE106
    }, function (data) {
        if(key == 'CE107'){
                if(data.status == 'success'){
                    CE107Num = data.data.length;
                    for(var i=0;i<data.data.length;i++) {
                        var value = i + 1;
                        if (stdLimitMap[key]['limit_value'][i]['text'] != data.data[i]['textDescription']) {
                            stdLimitMap[key]['limit_value'][i]['text'] = data.data[i]['textDescription'];
                            stdLimitMap[key]['limit_value_current'][i]['text'] = data.data[i]['textDescription'];
                            delete stdTextChangeMap[value + "_" + key];
                            delete stdValuesChangeMap[value + "_" + key + '_text'];
                        }
                    }
                }else {  //不满足任何标准限值则设置为空
                    CE107Num = 0;
                    for(var i=0; i<2; i++) {
                        stdLimitMap[key]['limit_value'][i]['text'] = '';
                        stdLimitMap[key]['limit_value_current'][i]['text'] = '';
                    }
                }

        }else {
            if (data.status == 'success') {
                // console.log("stdLimitMap_text");
                // console.log(JSON.stringify(stdLimitMap[key]));
                if (stdLimitMap[key]['limit_value']['text'] != data.data[0]['textDescription']) {
                    stdLimitMap[key]['limit_value']['text'] = data.data[0]['textDescription'];
                    stdLimitMap[key]['limit_value_current']['text'] = data.data[0]['textDescription'];
                    delete stdTextChangeMap[key];
                    delete stdValuesChangeMap[key + '_text'];
                }
            } else {  //不满足任何标准限值则设置为空
                // console.log("stdLimitMap_text_NULL");
                // console.log(JSON.stringify(stdLimitMap[key]));
                stdLimitMap[key]['limit_value']['text'] = '';
                stdLimitMap[key]['limit_value_current']['text'] = '';
            }
        }
    });
}

function getStdBiPicValue(voltage_num_list, key) {
    imgNumNow1 = [];
    imgNumNow2 = [];
    for(var i=0; i<powerNode; i++){
            imgNumNow1[i] = "";
            imgNumNow2[i] = "";
    }
    $.post("new/getLimitValuesBiPic", {
        voltageNum: JSON.stringify(voltage_num_list),
        projectId: key,
        picOneNow: JSON.stringify(imgNumNow1),
        picTwoNow: JSON.stringify(imgNumNow2),
        devName : devName
    }, function (data) {
        CS101Num  = 0;
            if (data.status === 'error') {
                for(var i=0; i<2; i++) {
                    stdLimitMap[key]['limit_value'][i - 1]['pic_one'] = '';
                    stdLimitMap[key]['limit_value'][i - 1]['pic_two'] = '';
                    stdLimitMap[key]['limit_value_current'][i - 1]['pic_one'] = '';
                    stdLimitMap[key]['limit_value_current'][i - 1]['pic_two'] = '';
                }
            } else {
                for(var i in data.data) {
                    if (stdLimitMap[key]['limit_value'][i-1]['pic_one'] != data.data[i][0].imgNum || stdLimitMap[key]['limit_value'][i-1]['pic_two'] != data.data[i][1].imgNum) {
                        stdLimitMap[key]['limit_value'][i-1]['pic_one'] = data.data[i][0].imgNum;
                        stdLimitMap[key]['limit_value'][i-1]['pic_two'] = data.data[i][1].imgNum;
                        stdLimitMap[key]['limit_value_current'][i-1]['pic_one'] = data.data[i][0].imgNum;
                        stdLimitMap[key]['limit_value_current'][i-1]['pic_two'] = data.data[i][1].imgNum;
                        delete stdBiPicChangeMap[i + "_" + key + '_1'];
                        delete stdBiPicChangeMap[i + "_" + key + '_2'];
                    }
                    CS101Num+=1;
            }

        }
    });
}

function getpicValue(voltage_list, voltage_num_list, power_supply_list, receive_launch, key_equipment, static_electricity, special_equipment, secondary_platform, key) {   //曲线图或折线图
    imgNumNow = [];
    if(key == 'CE101'){
        for(var i=0; i<powerNode; i++){
                imgNumNow[i] = '-1';
        }
    }else {
            imgNumNow[0] = '-1';
    }
    $.post("new/getLimitValuesPic", {
        voltage: JSON.stringify(voltage_list),
        voltageNum: JSON.stringify(voltage_num_list),
        powerSupply: JSON.stringify(power_supply_list),
        receiveLaunch: receive_launch,
        keyEquipment: key_equipment,
        staticElectricity: static_electricity,
        specialEquipment: special_equipment,
        projectId: key,
        secondaryPlatform: secondary_platform,
        imgNumNow: JSON.stringify(imgNumNow),
        devName: devName
    }, function (data) {
        if(key == 'CE101'){
                if (data.status === 'success') {
                    CE101Num = data.data.length;
                    for (var i = 0; i < data.data.length; i++) {
                        var value = i + 1;
                        if (stdLimitMap[key]['limit_value'][i]['pic'] != data.data[i]['imgNum']) {
                            stdLimitMap[key]['limit_value'][i]['pic'] = data.data[i]['imgNum'];
                            stdLimitMap[key]['limit_value_current'][i]['pic'] = data.data[i]['imgNum'];
                            delete stdValuesChangeMap[value + "_" + key];
                        }
                    }
                } else {
                    CE101Num = 0;
                    for(var i=0; i<3; i++) {
                        stdLimitMap[key]['limit_value'][i]['pic'] = '';
                        stdLimitMap[key]['limit_value_current'][i]['pic'] = '';
                    }
                }


        }else {
            if (data.status === 'success') {
                if (stdLimitMap[key]['limit_value']['pic'] != data.data[0]['imgNum']) {
                    stdLimitMap[key]['limit_value']['pic'] = data.data[0]['imgNum'];
                    stdLimitMap[key]['limit_value_current']['pic'] = data.data[0]['imgNum'];
                    delete stdValuesChangeMap[key];
                }
            } else {
                stdLimitMap[key]['limit_value']['pic'] = '';
                stdLimitMap[key]['limit_value_current']['pic'] = '';
            }
        }
    });
}

function generate_template_subject_table_element(project_id) {
    var res = "";
    switch (project_id) {
        // $(".selector").find("option[value= 0]").attr("selected",true);
        case 'CE101':
        case 'CE102':
        case 'CS109':
        case 'RE101':
        case 'RE102':
        case 'RS101':
        case 'RS105':
        case 'RS103':
        case 'CS114':
            res += '<tr>' +
                '<td>' + project_id + '</td>' +
                '<td><select class="form-control" id="' + project_id + '_usage"><option value="1" selected>适用</option><option value="0">不适用</option></select></td>' +
                '<td><button class="btn btn-default" onclick=adjustLimitValues("' + project_id + '")>限值调整</button></td>' +
                '<td><input type="text" class="form-control radius" id="' + project_id + '_remark"></td>' +
                '</tr>';
            break;
        case 'CE106':
        case 'CE107':
        case 'CS106':
        case 'CS112':
        case 'CS102':
        case 'RE103':
            res += '<tr>' +
                '<td>' + project_id + '</td>' +
                '<td><select class="form-control" id="' + project_id + '_usage"><option value="1" selected>适用</option><option value="0">不适用</option></select></td>' +
                '<td><button class="btn btn-default" onclick=adjustText("' + project_id + '")>限值调整</button></td>' +
                '<td><input type="text" class="form-control radius" id="' + project_id + '_remark"></td>' +
                '</tr>';
            break;
        case 'CS115':
        case 'CS116':
            res += '<tr>' +
                '<td>' + project_id + '</td>' +
                '<td><select class="form-control" id="' + project_id + '_usage"><option value="1" selected>适用</option><option value="0">不适用</option></select></td>' +
                '<td><button class="btn btn-default" onclick=adjustTextAndPic("' + project_id + '")>限值调整</button></td>' +
                '<td><input type="text" class="form-control radius" id="' + project_id + '_remark"></td>' +
                '</tr>';
            break;
        case 'CS103':
        case 'CS104':
        case 'CS105':
            res += '<tr>' +
                '<td>' + project_id + '</td>' +
                '<td><select class="form-control" id="' + project_id + '_usage"><option value="1" selected>适用</option><option value="0">不适用</option></select></td>' +
                '<td><input type="text" class="form-control radius" id="' + project_id + '_limit"></td>' +
                '<td><input type="text" class="form-control radius" id="' + project_id + '_remark"></td>' +
                '</tr>';
            break;
        case 'CS101':
            res += '<tr>' +
                '<td>' + project_id + '</td>' +
                '<td><select class="form-control" id="' + project_id + '_usage"><option value="1" selected>适用</option><option value="0">不适用</option></select></td>' +
                '<td><button class="btn btn-default" onclick=adjustBiPic("' + project_id + '")>限值调整</button></td>' +
                '<td><input type="text" class="form-control radius" id="' + project_id + '_remark"></td>' +
                '</tr>';
            break;
        default:
            console.log('ERR id > ' + project_id);
            break;
    }
    // console.log(">> " + res);
    return res;
}


function nextPage() {
    if (page_num === 3) { //需要判断分系统和设备名称等有没有填 0分系统 1设备
        var subsysOrEqp = $("#subsys_eqp").val();
        if (subsysOrEqp == '0') {
            subsysOrEqpName = $("#subsys_name").val().trim();
            subsysOrEqpModel = $("#subsys_model").val().trim();
            subsysOrEqpNum = '空';
        } else {
            subsysOrEqpName = $("#eqp_name").val().trim();
            subsysOrEqpModel = $("#eqp_model").val().trim();
            subsysOrEqpNum = $("#eqp_num").val().trim();
        }

        if (subsysOrEqpName.length == 0) {
            if (subsysOrEqp == 0) {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '分系统名称不能为空'});
                return
            } else {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '设备名称不能为空'});
                return
            }
        }
        if (subsysOrEqpModel.length == 0) {
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '型号不能为空'});
            return
        }

        if (subsysOrEqpNum.length == 0) {
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '串号不能为空'});
            return
        }

        $("input[id^='subsys_eqp_name']").val(subsysOrEqpName);
        $("input[id^='subsys_eqp_model']").val(subsysOrEqpModel);
        $("input[id^='subsys_eqp_num']").val(subsysOrEqpNum);
    }
    if (page_num === 5) {
        submitManageSysDevelop();
        $("#submit").removeClass("hidden");
    }

    var attribute = $("#attribute").val();
    if (page_num === 4) {
        if (attribute === '0') {  //在设备属性为非用频时直接跳到第六页
            $("#new" + page_num).addClass("hidden");
            page_num = page_num + 2;
            $("#new" + page_num).removeClass("hidden");
            submitManageSysDevelop();
            $("#submit").removeClass("hidden");
            return
        }
    }

    $("#new" + page_num).addClass("hidden");
    page_num = page_num + 1;
    $("#new" + page_num).removeClass("hidden");
    if(devStatus == 1 || devStatus == 2 || devStatus == 3 || Status == 1){
        $("input").attr("readonly","readonly");
    }

}

function prePage() {
    console.log("page:"+page_num);
    if (page_num == 6) {
        if(devStatus == 0 || devStatus == 4) {
            $.tipModal('confirm', 'warning', '当前修改的内容将不被保存，确定返回上一页？', function (result) {
                if (result) {
                    // history.back(-1);
                    console.log("确认返回");
                    stdValuesChangeMap = {};
                    stdTextChangeMap = {};
                    stdBiPicChangeMap = {};

                    $("#submit").addClass("hidden");

                    var attribute = $("#attribute").val();
                    if (attribute === '0') {  //在设备属性为非用频时直接跳到第四页
                        $("#new" + page_num).addClass("hidden");
                        page_num = page_num - 2;
                        $("#new" + page_num).removeClass("hidden");

                        return
                    }
                    $("#new" + page_num).addClass("hidden");
                    page_num = page_num - 1;
                    $("#new" + page_num).removeClass("hidden");
                }
            });
        }else{
            $("#submit").addClass("hidden");

            var attribute = $("#attribute").val();
            if (attribute === '0') {  //在设备属性为非用频时直接跳到第四页
                $("#new" + page_num).addClass("hidden");
                page_num = page_num - 2;
                $("#new" + page_num).removeClass("hidden");

                return
            }
            $("#new" + page_num).addClass("hidden");
            page_num = page_num - 1;
            $("#new" + page_num).removeClass("hidden");
        }
    }else {
        $("#new" + page_num).addClass("hidden");
        page_num = page_num - 1;
        $("#new" + page_num).removeClass("hidden");
    }

}

$(document).ready(function () {
    $("#primary_platform").on('change',
        function () {
            var primary = $(this).val();
            var secondaryMap = {};
            if (primary === '1') {
                secondaryMap = {
                    '11': '海军舰船甲板上',
                    '12': '陆军舰船甲板上',
                    '13': '海军舰船水下外部',
                    '14': '陆军舰船水下外部',
                    '15': '海军金属舰船甲板下',
                    '16': '陆军金属舰船甲板下',
                    '17': '海军非金属舰船甲板下（包括航母机库内设备）',
                    '18': '陆军非金属舰船甲板下'
                };
            } else if (primary === '2') {
                secondaryMap = {
                    '21': '海军水下外部',
                    '22': '陆军水下外部',
                    '23': '海军水下内部（压力舱内）',
                    '24': '陆军水下内部（压力舱内）',
                    '25': '海军水下内部（压力舱外）',
                    '26': '陆军水下内部（压力舱外）'
                };
            } else if (primary === '3') {
                secondaryMap = {
                    '31': '固定翼飞机外部',
                    '32': '直升机外部',
                    '33': '直升机内部',
                    '34': '固定翼飞机内部（首尾间距≧25m）',
                    '35': '固定翼飞机内部（首尾间距<25m）'
                };
            } else if (primary === '4') {
                secondaryMap = {
                    '41': '固定翼飞机外部',
                    '42': '直升机外部',
                    '43': '直升机内部',
                    '44': '固定翼飞机内部（首尾间距≧25m）',
                    '45': '固定翼飞机内部（首尾间距<25m）'
                };
            } else if (primary === '5') {
                secondaryMap = {
                    '51': '固定翼飞机外部',
                    '52': '直升机外部',
                    '53': '直升机内部',
                    '54': '固定翼飞机内部（首尾间距≧25m）',
                    '55': '固定翼飞机内部（首尾间距<25m）'
                };
            } else if (primary === '6') {
                secondaryMap = {'61': '空间系统外部', '62': '空间系统内部（首尾间距≧25m）', '63': '空间系统内部（首尾间距<25m）'};
            } else if (primary === '7') {
                secondaryMap = {'71': '地面固定', '72': '地面移动'};
            } else if (primary === '8') {
                secondaryMap = {'81': '地面固定', '82': '地面移动'};
            } else if (primary === '9') {
                secondaryMap = {'91': '地面固定', '92': '地面移动'};
            }

            var option = "";
            for (var i in secondaryMap) {
                option += '<option value="' + i + '">' + secondaryMap[i] + '</option>';
            }

            $("#secondary_platform").empty().append(option);
        });

    $("#subsys_eqp").on('change',
        function () {
            var subsysEqp = $(this).val();
            if (subsysEqp == "0") { //分系统
                $("#subsys").removeClass("hidden");
                $("#eqp").addClass("hidden");
            } else {
                $("#eqp").removeClass("hidden");
                $("#subsys").addClass("hidden");
            }
        });

    $("#attribute").on('change',
        function () {
            var attribute = $(this).val();
            if (attribute == "0") { //分系统
                $("#antenna_name").addClass("hidden");
                $("#antenna_attribute").addClass("hidden");
                $("#antenna_removal").val("none");
                $("#anolitude_modulation").val("no_am");
            } else {
                $("#antenna_name").removeClass("hidden");
                $("#antenna_attribute").removeClass("hidden");
                $("#antenna_removal").val("removal");
                $("#anolitude_modulation").val("am");
            }
        });

    $("#power_port1").on('change',
        function () {
            var port = $(this).val();
            var powerSupplyMap = {};
            if (port == 'input_port' || port == 'other_port') {
                powerSupplyMap = {'platform_main': '固态电源', 'no_platform_main': '非固态电源'};
            } else {
                powerSupplyMap = {'none': '无'};
            }

            var option = "";
            for (var i in powerSupplyMap) {
                option += '<option value="' + i + '">' + powerSupplyMap[i] + '</option>';
            }

            $("#power_supply1").empty().append(option);
            $("#power_supply1").trigger("change");
        });

    $("#power_supply1").on('change',
        function () {
            var supply = $(this).val();
            console.log("supply:"+supply);
            var voltageMap = {};
            if (supply === 'platform_main' || supply === 'no_platform_main') {
                voltageMap = {
                    'direct_current': '直流',
                    'alternate_current50': '交流50Hz',
                    'alternate_current400': '交流400Hz'
                };
            } else {
                voltageMap = {'none': '无'};
            }

            var option = "";
            for (var i in voltageMap) {
                option += '<option value="' + i + '">' + voltageMap[i] + '</option>';
            }

            $("#voltage1").empty().append(option);
            console.log("option"+option);
            $("#voltage1").trigger("change");
        });

    $("#voltage1").on('change',
        function () {
            var voltage = $(this).val();
            var voltageNumMap = {};
            if (voltage != 'none') {
                if ($("#power_supply1").val() === 'platform_main' || $("#power_supply1").val() === 'no_platform_main') {
                    voltageNumMap = {'smaller_than': '<= 28V', 'bigger_than': '> 28V'};
                } else {
                    voltageNumMap = {'smaller_or_bigger': '<= 28V或> 28V'};
                }
            } else {
                voltageNumMap = {'none': '无'};
            }

            var option = "";
            for (var i in voltageNumMap) {
                option += '<option value="' + i + '">' + voltageNumMap[i] + '</option>';
            }

            $("#voltage_num1").empty().append(option);
        });

    // powerSelect(1);
    $("#antenna_removal").on('change',
        function () {
            var antenna = $(this).val();
            var receiveLaunchMap = {};
            if (antenna == 'none') {
                receiveLaunchMap = {'none': '无'};
            } else {
                receiveLaunchMap = {'receive': '接收', 'launch': '发射', 'receive_and_launch': '收/发'};
            }

            var option = "";
            for (var i in receiveLaunchMap) {
                option += '<option value="' + i + '">' + receiveLaunchMap[i] + '</option>';
            }

            $("#receive_launch").empty().append(option);
            $("#receive_launch").trigger("change");
        });

    $("#receive_launch").on('change',
        function () {
            var mode = $(this).val();
            var removal = $("#antenna_removal").val();
            amMap = {};
            if (removal == 'removal' || removal == 'no_removal') {
                // if (mode == 'launch' || mode == 'receive_and_launch') {
                amMap = {'am': '调幅', 'no_am': '非调幅'};
                // } else {
                //     amMap = {'none': '无'};
                // }
            } else {
                amMap = {'none': '无'};
            }

            var option = "";
            for (var i in amMap) {
                option += '<option value="' + i + '">' + amMap[i] + '</option>';
            }

            $("#anolitude_modulation").empty().append(option);
        });

    $("#opt_connect_mode_1").on('change',
        function () {
            var opt_connect_mode = $(this).val();
            if(opt_connect_mode == "1"){
                $("#opt_wave_guide_transmit_max_1").addClass("hidden");
                $("#opt_cutoff_fre_name_1").addClass("hidden");
            }else if(opt_connect_mode == "2"){
                $("#opt_wave_guide_transmit_max_1").removeClass("hidden");
                $("#opt_cutoff_fre_name_1").removeClass("hidden");
            }
        });

    $("#fre_select_option").on('change',
        function () {
            var opt_select = $(this).val();
            if(opt_select == "1"){
                $('div[id^="opt_"]').removeClass("hidden");
                $('div[id^="FH_"]').addClass("hidden");
                $('div[id^="DSSS"]').addClass("hidden");
            }else if(opt_select == "2"){
                $('div[id^="opt_"]').addClass("hidden");
                $('div[id^="FH_"]').removeClass("hidden");
                $('div[id^="DSSS"]').addClass("hidden");
            }else if(opt_select == "3"){
                $('div[id^="opt_"]').addClass("hidden");
                $('div[id^="FH_"]').addClass("hidden");
                $('div[id^="DSSS"]').removeClass("hidden");
            }
        });

});

function powerSelect(value) {
    console.log("powerSelectValue:"+value);
    $("#power_port"+value).off('change').on('change',
        function () {
            // var id = $(this).attr("id");
            // var value = id.replace(/[^0-9]/ig, "");//id中取数字
            var port = $(this).val();
            console.log("power_port_value"+value);
            var powerSupplyMap = {};
            if (port == 'input_port' || port == 'other_port') {
                powerSupplyMap = {'platform_main': '固态电源', 'no_platform_main': '非固态电源'};
            } else {
                powerSupplyMap = {'none': '无'};
            }

            var option = "";
            for (var i in powerSupplyMap) {
                option += '<option value="' + i + '">' + powerSupplyMap[i] + '</option>';
            }

            $("#power_supply"+value).empty().append(option);
            $("#power_supply"+value).trigger("change");
        });

    $("#power_supply"+value).off('change').on('change',
        function () {
            // var id = $(this).attr("id");
            // var value = id.replace(/[^0-9]/ig, "");//id中取数字
            var supply = $(this).val();
            var voltageMap = {};
            if (supply === 'platform_main' || supply === 'no_platform_main') {
                voltageMap = {
                    'direct_current': '直流',
                    'alternate_current50': '交流50Hz',
                    'alternate_current400': '交流400Hz'
                };
            } else {
                voltageMap = {'none': '无'};
            }

            var option = "";
            for (var i in voltageMap) {
                option += '<option value="' + i + '">' + voltageMap[i] + '</option>';
            }

            $("#voltage"+value).empty().append(option);
            $("#voltage"+value).trigger("change");
        });

    $("#voltage"+value).off('change').on('change',
        function () {
            // var id = $(this).attr("id");
            // var value = id.replace(/[^0-9]/ig, "");//id中取数字
            var voltage = $(this).val();
            var voltageNumMap = {};
            // if (voltage === 'direct_current') {
            if (voltage != 'none') {
                if ($("#power_supply"+value).val() === 'platform_main' ||  $("#power_supply"+value).val() === 'no_platform_main') {
                    voltageNumMap = {'smaller_than': '<= 28V', 'bigger_than': '> 28V'};
                } else {
                    voltageNumMap = {'smaller_or_bigger': '<= 28V或> 28V'};
                }
            } else {
                voltageNumMap = {'none': '无'};
            }

            var option = "";
            for (var i in voltageNumMap) {
                option += '<option value="' + i + '">' + voltageNumMap[i] + '</option>';
            }

            $("#voltage_num"+value).empty().append(option);
        });

}


function optConnectSelect(value) {
    console.log("optConnectSelectValue:"+value);
    $("#opt_connect_mode_"+value).off('change').on('change',
        function () {
            var opt_connect_mode = $(this).val();
            if(opt_connect_mode == "1"){
                $("#opt_wave_guide_transmit_max_"+value).addClass("hidden");
                $("#opt_cutoff_fre_name_"+value).addClass("hidden");
            }else if(opt_connect_mode == "2"){
                $("#opt_wave_guide_transmit_max_"+value).removeClass("hidden");
                $("#opt_cutoff_fre_name_"+value).removeClass("hidden");
            }
        });
}


function adjustLimitValues(projectId) {
    if (projectId == null || projectId.length === 0) {
        $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '没有选择需要修改的项目'});
    }

    project = projectId;
    console.log("测试stdValuesChangeMap:"+JSON.stringify(stdValuesChangeMap));
    console.log("测试 stdLimitMap"+JSON.stringify(stdLimitMap));

    // var voltage = conditionMap[$("#voltage").val()];
    // var voltageNum = conditionMap[$("#voltage_num").val()];
    var voltage_list = [];
    var voltage_num_list = [];
    var power_supply_list = [];
    var count = 0;
    for(var i=0; i<powerNode; i++){
        count = i+1;
        power_supply_list.push(String(power_supply_map[$("#power_supply"+count).val()]));
        voltage_list.push(String(voltage_map[$("#voltage"+count).val()]));
        voltage_num_list.push(String(voltage_num_map[$("#voltage_num"+count).val()]));
    }
    var receiveLaunch = conditionMap[$("#receive_launch").val()];
    var keyEquipment = $("#key_equipment").val();
    var staticElectricity = $("#static_electricity ").val();
    var specialEquipment = $("#special_equipment ").val();
    var secondaryPlatform = $("#secondary_platform").val();
    // var stdValueChange = 0;  //还未被点击
    // if(stdValuesChangeMap.hasOwnProperty('test_'+projectId)) {
    //     stdValueChange = 1;  //已被点击
    // }
    imgNumNow = [];
    console.log("stdValuesChangeMap:"+stdValuesChangeMap);
    var j =0;
    if(projectId == 'CE101'){
        for(var i=0; i<CE101Num; i++){
            j=i+1;
            if(stdValuesChangeMap.hasOwnProperty(j+'_'+projectId)){
                imgNumNow[i] = stdValuesChangeMap[j+'_'+projectId];
            }else if(stdValuesChangeMap.hasOwnProperty(j+'_'+projectId) == false){
                imgNumNow[i] = '-1';
            }
        }
    }else {
        if (stdValuesChangeMap.hasOwnProperty(projectId)) {
            imgNumNow[0] = stdValuesChangeMap[projectId];
        }else if(stdValuesChangeMap.hasOwnProperty(projectId) == false){
            imgNumNow[0] = '-1';
        }
    }
    console.log("imgNumNow:"+imgNumNow[0]);
    console.log("imgNumNow:"+imgNumNow[1]);

    // console.log('voltage' + voltage);
    // console.log('voltageNum' + voltageNum);
    // console.log('receiveLaunch' + receiveLaunch);
    // console.log('keyEquipment' + keyEquipment);
    // console.log('staticElectricity' + staticElectricity);
    // console.log('specialEquipment' + specialEquipment);

    $.post("new/getLimitValuesPic", {
        voltage: JSON.stringify(voltage_list),
        voltageNum: JSON.stringify(voltage_num_list),
        powerSupply:JSON.stringify(power_supply_list),
        receiveLaunch: receiveLaunch,
        keyEquipment: keyEquipment,
        staticElectricity: staticElectricity,
        specialEquipment: specialEquipment,
        projectId: projectId,
        secondaryPlatform: secondaryPlatform,
        devId: devId,
        // stdValueChange: stdValueChange
        imgNumNow: JSON.stringify(imgNumNow),
        devName: devName
    }, function (data) {
        if (data.status === 'error') {
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
        } else {
            // for(var key in data.data) {
            //    imgNum = data.data;
            //    imgDescription = data.data[key];
            // }
            imgAll = data.data;
            // for(var i=0; i<data.data.length; i++ ) {
            //     imgSingle = {};
            //     imgSingle.imgNum = data.data[0]['imgNum'];
            //     imgSingle.imgInfo = data.data[0]['imgInfo'];
            //     imgSingle.imgSrc = data.data[0]['imgSrc'];
            //     imgSingle.imgMinX = data.data[0]['imgMinX'];
            //     imgSingle.imgMaxX = data.data[0]['imgMaxX'];
            //     imgSingle.imgMinY = data.data[0]['imgMinY'];
            //     imgSingle.imgMaxY = data.data[0]['imgMaxY'];
            //     imgSingle.imgAxisX = data.data[0]['imgAxisX'];
            //     imgSingle.imgAxisY = data.data[0]['imgAxisY'];
            //     imgAll.push(imgSingle);
            // }

            // if(imgAll.length == 1) {
            //     if (imgNum != '-1') {
            //         // $.frontModal({size: 'modal-lg', href: 'views/develop/manage/changeLimitValues.jsp'}).on('shown.bs.modal', function () {
            //         // })
            //         if (projectId != 'CS115' && projectId != 'CS116') {
            //             $("#changeLimitValueModal").click();
            //         }
            //     } else {
            //         $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '不存在标准限值图'});
            //     }
            // }else{
                numBool = false;
                projectBool = false;
                for(var single in imgAll){
                    if(single.imgNum != '-1'){
                        numBool = true;
                        if(projectId != 'CS115' && projectId != 'CS116'){
                            projectBool = true;
                        }
                    }
                }
                if(numBool == true && projectBool == true){
                    $("#changeLimitValueModal").click();
                }else if(numBool == false){
                    $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '不存在标准限值图'});
                }
            // }
        }
    });
}

function adjustBiPic(projectId) {
    if (projectId == null || projectId.length === 0) {
        $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '没有选择需要修改的项目'});
    }
    console.log("测试stdBiPicChangeMap:"+JSON.stringify(stdBiPicChangeMap));
    console.log("测试双图stdLimitMap"+JSON.stringify(stdLimitMap));
    // var voltageNum = conditionMap[$("#voltage_num").val()];
    var voltage_num_list = [];
    var count = 0;
    for(var i=0; i<powerNode; i++){
        count = i+1;
        voltage_num_list.push(String(voltage_num_map[$("#voltage_num"+count).val()]));
    }
    project = projectId;
    imgNumNow1 = [];
    imgNumNow2 = [];
    var j=0;
    for(var i=0; i<CS101Num; i++){
        j=i+1;
        if (stdBiPicChangeMap.hasOwnProperty(j+'_'+projectId + '_1')) {
            imgNumNow1[i] = stdBiPicChangeMap[j+'_'+projectId + '_1'];
        }else if(stdBiPicChangeMap.hasOwnProperty(j+'_'+projectId + '_1') == false){
            imgNumNow1[i] = "";
        }
        if (stdBiPicChangeMap.hasOwnProperty(j+'_'+projectId + '_2')) {
            imgNumNow2[i] = stdBiPicChangeMap[j+'_'+projectId + '_2'];
        }else if(stdBiPicChangeMap.hasOwnProperty(j+'_'+projectId + '_2') == false){
            imgNumNow2[i] = "";
        }
    }

    $.post("new/getLimitValuesBiPic", {
        voltageNum: JSON.stringify(voltage_num_list),
        projectId: projectId,
        picOneNow: JSON.stringify(imgNumNow1),
        picTwoNow: JSON.stringify(imgNumNow2),
        devId: devId,
        devName: devName
    }, function (data) {
        if (data.status === 'error') {
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
        } else {
            imgBiAll = data.data;
            // imgBiSingle = {};
            // for(var i in data.data) {
            //     imgBiSingle.picOneInfo = data.data[i][0];
            //     imgBiSingle.picTwoInfo = data.data[i][1];
            //     imgBiAll.push(imgBiSingle);
            // }
            $("#changeLimitValueModal").click();
        }
    });
}

function adjustText(projectId) {
    if (projectId == null || projectId.length === 0) {
        $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '没有选择需要修改的项目'});
    }
    console.log("测试stdTextChangeMap:"+JSON.stringify(stdTextChangeMap));
    console.log("测试双图stdLimitMap"+JSON.stringify(stdLimitMap));
    // var receiveLaunch = conditionMap[$("#receive_launch").val()];
    // var voltage = conditionMap[$("#voltage").val()];
    // var antennaRemoval = conditionMap[$("#antenna_removal").val()];
    var receive_launch_CE106 = 0; //CE106发射接收信息
    var receive_launch = 0; //发射接收信息
    var antenna_removal = 0; //天线拆卸信息
    var optList= getOptList();//获取用频信息
    var optListLength = optList.length;
    var receive_launch_num = 0; //发射次数计数
    var receive_receive_num = 0; //接收次数计数
    for(var len=0; len<optListLength; len++){
        if(optList[len]['opt_work_style'] == "1"){
            receive_launch_num++;
            if(optList[len]['opt_install_mode'] == "2"){
                antenna_removal = 2;
                receive_launch = 1;
            }
        }else if(optList[len]['opt_work_style'] == "2"){
            receive_receive_num++;
        }else if(optList[len]['opt_work_style'] == "3"){
            if(optList[len]['opt_install_mode'] == "2"){
                antenna_removal = 2;
                receive_launch = 3;
            }
        }
    }
    if(receive_launch_num == optListLength){
        receive_launch_CE106 = 1;
    }else if(receive_receive_num == optListLength){
        receive_launch_CE106 = 2;
    }else{
        receive_launch_CE106 = 3;
    }
    var keyEquipment = $("#key_equipment").val();
    var staticElectricity = $("#static_electricity ").val();
    var voltage_list = [];
    var count = 0;
    for(var i=0; i<powerNode; i++){
        count = i+1;
        voltage_list.push(String(voltage_map[$("#voltage"+count).val()]));
    }

    textNow = [];
    var j=0;
    if(projectId == 'CE107') {
        for (var i = 0; i < CE107Num; i++) {
            j = i + 1;
            if (stdValuesChangeMap.hasOwnProperty(j + '_' + projectId + '_text')) {
                textNow[i] = stdValuesChangeMap[j + '_' + projectId + '_text'];
            }else if(stdValuesChangeMap.hasOwnProperty(j + '_' + projectId + '_text') == false){
                textNow[i] = "";
            }
        }
    }else {
        if (stdValuesChangeMap.hasOwnProperty(projectId + '_text')) {
            textNow[0] = stdValuesChangeMap[projectId + '_text'];
        }else if (stdValuesChangeMap.hasOwnProperty(projectId + '_text') == false){
            textNow[0] = "";
        }
    }


    project = projectId;
    $.post("new/getLimitValuesText", {
        receiveLaunch: receive_launch,
        projectId: project,
        voltage: JSON.stringify(voltage_list),
        antennaRemoval: antenna_removal,
        keyEquipment: keyEquipment,
        staticElectricity: staticElectricity,
        receiveLaunchCE106: receive_launch_CE106
    }, function (data) {
        if (data.status === 'error') {
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
        } else {
            textNum = [];
            textDescription = [];
            for(var i=0; i<data.data.length; i++) {
                textNum[i] = data.data[i]['textNum'];
                if (textNum[i] == textNow[i]) {
                    if(project == 'CE107'){
                        var j=i+1;
                        textDescription[i] = stdTextChangeMap[j+'_'+project];
                    }else {
                        textDescription[i] = stdTextChangeMap[project];
                    }
                } else {
                    textDescription[i] = data.data[i]['textDescription'];
                }
            }
            if (projectId != 'CS115' && projectId != 'CS116') {
                $("#changeLimitValueModal").click();
            }
        }
    });

}

function adjustTextAndPic(projectId) {
    adjustLimitValues(projectId);
    adjustText(projectId);
    $("#changeLimitValueModal").click();
}

function submitProject(type,userName,userId) {
    for (var standard_list_index = 0; standard_list_index < standard_list.length; standard_list_index++) {
        var standardKey = standard_list[standard_list_index];
        // var projectId = "#" + standardKey + "_usage";
        // var remarkId = "#" + standardKey + "_remark";
        if($("#" + standardKey + "_usage").val() == "0"){
            var standard_remark = $("#" + standardKey + "_remark").val().trim();
            if (standard_remark.length == 0) {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: standardKey+'项目裁剪理由不能为空'});
                return
            }
        }else if($("#" + standardKey + "_usage").val() == null){
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '请选择'+standardKey+'项目适用性'});
            return
        }
    }
    for (var protocol_list_index = 0; protocol_list_index < protocol_list.length; protocol_list_index++) {
        var protocolKey = protocol_list[protocol_list_index];
        if($("#" + protocolKey + "_usage").val() == "0"){
            var protocol_remark = $("#" + protocolKey + "_remark").val().trim();
            if (protocol_remark.length == 0) {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: protocolKey+'项目裁剪理由不能为空'});
                return
            }else if($("#" + protocolKey + "_usage").val() == null){
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '请选择'+protocolKey+'项目适用性'});
                return
            }
        }
    }

    var subsysOrEqp = $("#subsys_eqp").val();
    var formData = new FormData();
    //提交类型，如果是save，则项目状态变为待审核，如果是draft，则状态变为待修改
    if (type == 'save') {
        formData.append('devStatus', 1);
    } else if (type == 'draft') {
        formData.append('devStatus', 4)
    }
    formData.append('devItemid', devItemid);
    formData.append('userName', userName);
    formData.append('devId', devId);
    formData.append('userId', userId);


    // 页面3的数据
    formData.append('devSubsysSource',$("#subsys_source").val().trim());
    formData.append('devSubsysComRef',$("#subsys_com_ref").val().trim());
    formData.append('devSubsysQuantity',$("#subsys_quantity").val().trim());
    formData.append('devSubsysEnvironment',$("#subsys_environment").val().trim());
    formData.append('devSubsysEqp', subsysOrEqp);
    formData.append('devSubsysEqpName', subsysOrEqpName);
    formData.append('devSubsysEqpModel', subsysOrEqpModel);
    formData.append('devSubsysEqpNum', subsysOrEqpNum);
    formData.append('devSupplier', $("#supplier").val());
    formData.append('devPrimaryPlatform', $("#primary_platform").val());
    formData.append('devSecondaryPlatform', $("#secondary_platform").val());

    //页面4的数据
    var power_name_list = [];
    var power_port_list = [];
    var power_supply_list =[];
    var voltage_list = [];
    var voltage_num_list = [];
    var count = 0;
    for(var i=0; i<powerNode; i++){
        count = i+1;
        power_name_list.push($("#power_name"+count).val());
        power_port_list.push(String(power_port_map[$("#power_port"+count).val()]));
        power_supply_list.push(String(power_supply_map[$("#power_supply"+count).val()]));
        voltage_list.push(String(voltage_map[$("#voltage"+count).val()]));
        voltage_num_list.push(String(voltage_num_map[$("#voltage_num"+count).val()]));
    }
    formData.append('devAttribute', $("#attribute").val());
    formData.append('devKey', $("#key_equipment").val());
    formData.append('devInstall', install_mode_map[$("#install_mode").val()]);
    formData.append('devGnd', $("#ground_line").val());
    formData.append('devSpecial', $("#special_equipment").val());
    formData.append('devInterport', $("#interconnected_port").val());
    formData.append('devLowsensitive', $("#low_frequency_sensitive").val());
    formData.append('devEmp', $("#emp_reinforce").val());
    formData.append('devStatic', $("#static_electricity").val());
    formData.append('devPowername', JSON.stringify(power_name_list));
    formData.append('devPowerport', JSON.stringify(power_port_list));
    formData.append('devPowersupply', JSON.stringify(power_supply_list));
    formData.append('devVoltage', JSON.stringify(voltage_list));
    formData.append('devVoltagenum', JSON.stringify(voltage_num_list));
    formData.append('devAntenna', antenna_removal_map[$("#antenna_removal").val()]);
    formData.append('devReceiveLaunch', receive_launch_map[$("#receive_launch").val()]);
    formData.append('devModulation', anolitude_modulation_map[$("#anolitude_modulation").val()]);





    //页面5的数据
    //可选用频方式
    var freSelect = "1";
    formData.append('devFreSelect', fre_select_map[freSelect]);

    // var freSelect = $("#fre_select_option").val();
    // var freSelect = "1";

    var optList= getOptList();
    // var optSelector = $('div[id^="opt_"]');
    // var optList = [];
    // if(freSelect == "1") {
    //     $.each(optSelector, function (idx, value) {
    //         var optEach = {};
    //         optEach['opt_fre_select_option'] = $(value).find('select[id*="opt_fre_select_option"]').val();//用频方式选择
    //         optEach['opt_freq_mode'] = $(value).find('select[class*="opt_freq_mode"]').val();
    //         optEach['opt_freq_range'] = $(value).find('input[class*="opt_freq_range"]').val();
    //         optEach['opt_freq_low'] = $(value).find('input[class*="opt_freq_low"]').val();
    //         optEach['opt_freq_mid'] = $(value).find('input[class*="opt_freq_mid"]').val();
    //         optEach['opt_freq_high'] = $(value).find('input[class*="opt_freq_high"]').val();
    //         var freq_points = $(value).find('input[class*="opt_freq_points"]').val();
    //         if (freq_points == '固定多频点时填写' || optEach['opt_freq_mode'] == '1') {
    //             freq_points = '';
    //         }
    //         optEach['opt_freq_points'] = freq_points;
    //         optEach['opt_work_style'] = $(value).find('select[class*="opt_work_style"]').val();
    //         optEach['opt_install_mode'] = $(value).find('select[class*="opt_install_mode"]').val();//安装方式
    //         optEach['opt_connect_mode'] = $(value).find('select[class*="opt_connect_mode"]').val();//连接方式
    //         optEach['opt_modulation_mode'] = $(value).find('select[class*="opt_modulation_mode"]').val();//调制方式
    //         optEach['opt_ave_pow_transmit_max'] = $(value).find('input[class*="opt_ave_pow_transmit_max"]').val();//最大发射平均功率
    //         optEach['opt_wave_guide_transmit_max'] = $(value).find('input[class*="opt_wave_guide_transmit_max"]').val();//最大波导发射功率
    //         optEach['opt_port_name'] = $(value).find('input[class*="opt_port_name"]').val();
    //
    //         // var modulationSelector = $(value).find('input[class*="opt_modulation_mode"]'); //还有opt_modulation_mode_num也会被取到，所以加上opt_modulation_mode_1和opt_modulation_mode_2最多有三个，最少有两个
    //         // var modulationMap = {};
    //         // if (modulationSelector.length == 2) {
    //         //     modulationMap['opt_modulation_mode_1'] = $(modulationSelector[1]).val();
    //         //     modulationMap['opt_modulation_mode_2'] = '';
    //         // } else if (modulationSelector.length == 3) {
    //         //     modulationMap['opt_modulation_mode_1'] = $(modulationSelector[1]).val();
    //         //     modulationMap['opt_modulation_mode_2'] = $(modulationSelector[2]).val();
    //         // }
    //         //
    //         // optEach['opt_modulation_mode_num'] = modulationMap;
    //
    //         optList.push(optEach)
    //
    //     });
    // }

    formData.append('devFreqOptional', JSON.stringify(optList));

    //跳频
    var FHlist = ['low', 'mid', 'high'];
    for (var index in FHlist) {
        var band = FHlist[index];
        var eachFHmap = {};
        if(freSelect == "2") {
            var FHnode = $("#FH_" + band);
            eachFHmap['freq_range'] = FHnode.find('input[id$="freq_range"]').val();
            eachFHmap['freq_low'] = FHnode.find('input[id$="freq_low"]').val();
            eachFHmap['freq_high'] = FHnode.find('input[id$="freq_high"]').val();
            var freq_points = FHnode.find('input[id$="freq_points"]').val();
            if (freq_points == '固定多频点时填写') {
                freq_points = '';
            }
            eachFHmap['freq_points'] = freq_points;
            eachFHmap['work_style'] = FHnode.find('select[id$="work_style"]').val();
            eachFHmap['install_mode'] = FHnode.find('select[id$="install_mode"]').val();
            eachFHmap['ave_pow_transmit_max'] = FHnode.find('input[id$="ave_pow_transmit_max"]').val();
            eachFHmap['port_name'] = FHnode.find('input[id$="port_name"]').val();
        }

        var FHkey = 'devFreqFh' + band.replace(band.charAt(0), band.charAt(0).toUpperCase());
        formData.append(FHkey, JSON.stringify(eachFHmap))
    }

    //直序扩频
    var DSSSmap = {};
    if(freSelect == "3") {
        DSSSmap['trans_rate_max'] = $("#DSSS_trans_rate_max").val();
        DSSSmap['freq_points'] = $("#DSSS_freq_points").val();
        DSSSmap['work_style'] = $("#DSSS_work_style").val();
        DSSSmap['install_mode'] = $("#DSSS_install_mode").val();
        DSSSmap['ave_pow_transmit_max'] = $("#DSSS_ave_pow_transmit_max").val();
        DSSSmap['port_name'] = $("#DSSS_port_name").val();
    }
    formData.append('devFreqDsss', JSON.stringify(DSSSmap));

    //页面6的数据

    //适用项目
    for (index in standard_list) {
        createProjectISubmit(standard_list[index], 'standard');
    }

    //合同项目
    for (index in protocol_list) {
        createProjectISubmit(protocol_list[index], 'protocol');
    }

    //额外项目
    for (index in extra_list) {
        createProjectISubmit(extra_list[index], 'extra');
    }

    for (var key in stdLimitMap) {
        formData.append('dev' + key, JSON.stringify(stdLimitMap[key]))
    }
    var projectList = [];
    console.log("0310开始standardProjectList");
    for(var project in standardProjectList){
        console.log(standardProjectList[project]);
        console.log("#" + standardProjectList[project] + "_usage");
        console.log($("#" + standardProjectList[project] + "_usage").val());
        if( $("#" + standardProjectList[project] + "_usage").val() == "1"){
            projectList.push(standardProjectList[project]) ;
        }
    }

    formData.append('projectList', JSON.stringify(projectList));
    console.log("提交报告stdLimitMap:"+JSON.stringify(stdLimitMap));
    console.log("devPowerport:"+formData.get("devPowerport"));
    console.log("devPowersupply:"+formData.get("devPowersupply"));
    console.log("devVoltage:"+formData.get("devVoltage"));
    console.log("devVoltagenum:"+formData.get("devVoltagenum"));

    $.ajax({
        url: 'new/saveNewProject',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.status == 'success') {
                // $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: "成功"});
                alert("提交成功");
                window.onbeforeunload = undefined;
                window.location.href = "/GJB151BSys/views/develop/project/project.jsp?userName="+userName;
            } else {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
            }
        },
        error: function () {
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '保存项目失败，请稍后再试。'});
        }
    });
}

/*
 * 获取用频装备信息中的天线端口信息
 */
function getOptList() {
    var optSelector = $('div[id^="opt_"]');
    var optList = [];
        $.each(optSelector, function (idx, value) {
            var optEach = {};
            optEach['opt_fre_select_option'] = $(value).find('select[id*="opt_fre_select_option"]').val();//用频方式选择
            optEach['opt_freq_mode'] = $(value).find('select[class*="opt_freq_mode"]').val();//用频方式
            optEach['opt_freq_range'] = $(value).find('input[class*="opt_freq_range"]').val();//频段
            optEach['opt_freq_low'] = $(value).find('input[class*="opt_freq_low"]').val();
            optEach['opt_freq_mid'] = $(value).find('input[class*="opt_freq_mid"]').val();
            optEach['opt_freq_high'] = $(value).find('input[class*="opt_freq_high"]').val();
            var freq_points = $(value).find('input[class*="opt_freq_points"]').val();
            if (freq_points == '固定多频点时填写' || optEach['opt_freq_mode'] == '1') {
                freq_points = '';
            }
            optEach['opt_freq_points'] = freq_points;
            optEach['opt_work_style'] = $(value).find('select[class*="opt_work_style"]').val();
            optEach['opt_install_mode'] = $(value).find('select[class*="opt_install_mode"]').val();//安装方式
            optEach['opt_connect_mode'] = $(value).find('select[class*="opt_connect_mode"]').val();//连接方式
            optEach['opt_modulation_mode'] = $(value).find('select[class*="opt_modulation_mode"]').val();//调制方式
            optEach['opt_ave_pow_transmit_max'] = $(value).find('input[class*="opt_ave_pow_transmit_max"]').val();//最大发射平均功率
            optEach['opt_wave_guide_transmit_max'] = $(value).find('input[class*="opt_wave_guide_transmit_max"]').val();//截止频率
            optEach['opt_fix_trans_point'] = $(value).find('input[class*="opt_fix_trans_point"]').val();//固定频点
            optEach['opt_trans_speed'] = $(value).find('input[class*="opt_trans_speed"]').val();//传输速率
            optEach['opt_port_name'] = $(value).find('input[class*="opt_port_name"]').val();

            optList.push(optEach)

        });
        return optList;
}

function createProjectISubmit(key, category) {  //类别分为standard, protocol, extra
    //所属项目类别，项目适用性
    if (category == 'standard') {
        stdLimitMap[key]['standard'] = 1;
        stdLimitMap[key]['standard_usage'] = $("#" + key + "_usage").val();
        stdLimitMap[key]['protocol'] = 0;
        stdLimitMap[key]['extra'] = 0;
    } else if (category == 'protocol') {
        stdLimitMap[key]['protocol'] = 1;
        stdLimitMap[key]['protocol_usage'] = $("#" + key + "_usage").val();
        stdLimitMap[key]['standard'] = 0;
        stdLimitMap[key]['extra'] = 0;
        if ($.inArray(key, stdSpecialList) != -1) {
            stdLimitMap[key]['limit_value_current']['value'] = $("#" + key + "_limit").val();
            stdLimitMap[key]['limit_value_current']['bias'] = $("#" + key + "_remark").val();
        }
    } else {
        stdLimitMap[key]['extra'] = 1;
        stdLimitMap[key]['extra_usage'] = $("#" + key + "_usage").val();
        stdLimitMap[key]['standard'] = 0;
        stdLimitMap[key]['protocol'] = 0;
    }

    //剪裁理由
    if ($.inArray(key, stdCurveTextList) == -1) {
        stdLimitMap[key]['remark'] = $("#" + key + "_remark").val();
    } else {  //特殊项目的剪裁理由设置为空
        stdLimitMap[key]['remark'] = '';
    }

    // 当前标准限值
    // if (stdTextChangeMap.hasOwnProperty(key)) {
    //     stdLimitMap[key]['limit_value_current']['text'] = stdTextChangeMap[key];
    // } else if (stdBiPicChangeMap.hasOwnProperty(key + '_1')) {
    //     stdLimitMap[key]['limit_value_current']['pic_one'] = stdBiPicChangeMap[key + '_1'];
    // } else if (stdBiPicChangeMap.hasOwnProperty(key + '_2')) {
    //     stdLimitMap[key]['limit_value_current']['pic_two'] = stdBiPicChangeMap[key + '_2'];
    // } else if (stdValuesChangeMap.hasOwnProperty(key)) {
    //     stdLimitMap[key]['limit_value_current']['pic'] = stdValuesChangeMap[key];
    // }
}
totalNode = 1;
powerNode = 1;

function increase(para) {
    var s;
    s = $(para).parents("div").parents("div").attr("id");
    if (parseInt($("#" + s).find('input[class^="opt_modulation_mode_num"]').val()) < 2) {
        $("#" + s).find('div[class^="opt_modulation_mode_1"]').after('<div class="opt_modulation_mode_2"><br><input type="text"  style="margin:0" class="form-control radius opt_modulation_mode_2"></div>');
        $("#" + s).find('input[class^="opt_modulation_mode_num"]').val(parseInt($("#" + s).find('input[class^="opt_modulation_mode_num"]').val()) + 1);
    }
    else
        $.tipModal('alert', '', '最多添加两个');
    if(devStatus ==1 || devStatus == 2  ||  devStatus == 3){
        $("input").hide();
    }
}

function decrease(para) {
    var s;
    s = $(para).parents("div").parents("div").attr("id");
    if (parseInt($("#" + s).find('input[class^="opt_modulation_mode_num"]').val()) > 1) {
        $("#" + s).find('input[class^="opt_modulation_mode_num"]').val(parseInt($("#" + s).find('input[class^="opt_modulation_mode_num"]').val()) - 1);
        $("#" + s).find('div[class^="opt_modulation_mode_2"]').remove();
    }
}

var sourceNode = document.getElementById("opt_1");
var sourcePowerNode = document.getElementById("pow_1");
var clone = sourceNode.cloneNode(true); // 克隆节点
var clonePower = sourcePowerNode.cloneNode(true); //克隆电源节点
function addFrequency() {
    if (totalNode < 20) {
        totalNode = totalNode + 1;
        var clonedNode = clone.cloneNode(true); // 克隆节点
        clonedNode.setAttribute("id", "opt_" + totalNode); // 修改一下id 值，避免id 重复
        $("#opt_" + (totalNode - 1)).after(clonedNode);
        $("#opt_" + totalNode).find('input[class^="form-control radius opt_freq_range"]').val(totalNode);
        $("#opt_" + totalNode).find('[name^="opt_connect_mode"]').attr("id", "opt_connect_mode_"+totalNode);
        $("#opt_" + totalNode).find('[name^="opt_wave_guide_transmit_max"]').attr("id", "opt_wave_guide_transmit_max_"+totalNode);
        $("#opt_" + totalNode).find('[name^="opt_cutoff_fre_name"]').attr("id", "opt_cutoff_fre_name_"+totalNode);
        optConnectSelect(totalNode);
    }
    else
        $.tipModal('alert', '', '最多添加20个');
}

function addPower() {
    if (powerNode < 20) {
        powerNode = powerNode + 1;
        console.log("powerNodeNew:"+powerNode);
        var clonedNode = clonePower.cloneNode(true); // 克隆节点
        clonedNode.setAttribute("id", "pow_" + powerNode); // 修改一下id 值，避免id 重复
        $("#pow_" + (powerNode - 1)).after(clonedNode);
        $("#pow_" + powerNode).find('[id^="powerNumb"]').text("电源类型"+powerNode);
        $("#pow_" + powerNode).find('[name^="power_name"]').attr("id", "power_name"+powerNode);
        $("#pow_" + powerNode).find('[name^="power_port"]').attr("id", "power_port"+powerNode);
        $("#pow_" + powerNode).find('[name^="power_supply"]').attr("id", "power_supply"+powerNode);
        $("#pow_" + powerNode).find('[name^="voltage"]').attr("id", "voltage"+powerNode);
        $("#pow_" + powerNode).find('[name^="voltage_num"]').attr("id", "voltage_num"+powerNode);
        powerSelect(powerNode);
    }
    else
        $.tipModal('alert', '', '最多添加20个');
}

function deleteFrequency(element) {
    var id = element.parentNode.parentNode.parentNode.id;
    console.log("id:"+id);
    var value = id.replace(/[^0-9]/ig, "");//id中取数字
    var num = totalNode;//记录当前共几个节点
    if (totalNode > 1) {
        $("#" + id).remove();
        console.log("value:"+value);
        value++;
        for (var a = value; a <= num; a++) {
            $("#opt_" + a).find('input[class^="form-control radius opt_freq_range"]').val(a - 1);
            $("#opt_" + a).find('[name^="opt_connect_mode"]').attr("id", "opt_connect_mode_"+(a - 1));
            $("#opt_" + a).find('[name^="opt_wave_guide_transmit_max"]').attr("id", "opt_wave_guide_transmit_max_"+(a - 1));
            $("#opt_" + a).find('[name^="opt_cutoff_fre_name"]').attr("id", "opt_cutoff_fre_name_"+(a - 1));
            $("#opt_" + a).attr("id", "opt_" + (a - 1));
            optConnectSelect(a-1);
        }
        totalNode--;
    }
    else
        $.tipModal('alert', '', '至少有一个');
}

function deletePower(element) {
    var id = element.parentNode.parentNode.parentNode.id;
    console.log("id:"+id);
    var value = id.replace(/[^0-9]/ig, "");//id中取数字
    var num = powerNode;//记录当前共几个节点
    if (powerNode > 1) {
        // $("#power_port"+value).off('change');
        $("#" + id).remove();
        value++;
        console.log("value:"+value);
        for (var a = value; a <= num; a++) {
            $("#pow_" + a).find('[id^="powerNumb"]').text("电源类型"+(a - 1));
            $("#pow_" + a).find('select[id^="power_port"]').attr("id", "power_port" + (a - 1));
            $("#pow_" + a).find('select[id^="power_supply"]').attr("id", "power_supply" + (a - 1));
            $("#pow_" + a).find('select[name="voltage"]').attr("id", "voltage" + (a - 1));
            $("#pow_" + a).find('select[id^="voltage_num"]').attr("id", "voltage_num" + (a - 1));
            $("#pow_" + a).attr("id", "pow_" + (a - 1));
            powerSelect(a - 1);
        }
        powerNode--;
    }
    else
        $.tipModal('alert', '', '至少有一个电源类型');
}

function cancel() {
    $.tipModal('confirm', 'warning', '您已输入的内容将不被保存，确定取消提交？', function (result) {
        if (result) {
            history.back(-1);
        }
    });
}

window.onbeforeunload = function () {
    return "你确定要离开吗";
}


/*
 根据Status和devStatus值更换历史校对/审核/批准意见框，控制删除，提交报告等按钮的展示
 Status代表项目是否为已完成，Status=1代表项目已完成
 devStatus为项目自身状态，同数据库同名含义
 */
function adviceTitle(devStatus,Status) {
    if(Status == 1){
        $("#advice_title_div").hide();
        $("#advice_div").hide();
        $("#delete_new").hide();
        $("#delete_new_power").hide();
        $("#cancel_draft_save").hide();
        $("input").attr("readonly", "readonly");
        $("select").attr("readonly", "readonly");
        $("select").attr("disabled", "disabled");
    }else {
        if (devStatus == 1) {
            $("#advice_title_div").show();
            $("#advice_div").show();
            $("#delete_new").hide();
            $("#delete_new_power").hide();
            $("#cancel_draft_save").hide();
            $("input").attr("readonly", "readonly");
            $("select").attr("readonly", "readonly");
            $("select").attr("disabled", "disabled");
            $("#advice").attr("placeholder", "输入校对意见");
            document.getElementById("advice_title").innerHTML = "校对意见";
            document.getElementById("pass").innerHTML = "校对通过";
            document.getElementById("fail").innerHTML = "校对不通过";
        } else if (devStatus == 2) {
            $("#advice_title_div").show();
            $("#advice_div").show();
            $("#delete_new").hide();
            $("#delete_new_power").hide();
            $("#cancel_draft_save").hide();
            $("input").attr("readonly", "readonly");
            $("select").attr("readonly", "readonly");
            $("select").attr("disabled", "disabled");
            $("#advice").attr("placeholder", "输入审核意见");
            document.getElementById("advice_title").innerHTML = "审核意见";
            document.getElementById("pass").innerHTML = "审核通过";
            document.getElementById("fail").innerHTML = "审核不通过";
        } else if (devStatus == 3) {
            $("#advice_title_div").show();
            $("#advice_div").show();
            $("#delete_new").hide();
            $("#delete_new_power").hide();
            $("#cancel_draft_save").hide();
            $("input").attr("readonly", "readonly");
            $("select").attr("readonly", "readonly");
            $("select").attr("disabled", "disabled");
            $("#advice").attr("placeholder", "输入批准意见");
            document.getElementById("advice_title").innerHTML = "批准意见";
            document.getElementById("pass").innerHTML = "批准通过";
            document.getElementById("fail").innerHTML = "批准不通过";
        } else {
            $("#advice_title_div").hide();
            $("#advice_div").hide();
            $("#delete_new").show();
            $("#delete_new_power").show();
            $("#cancel_draft_save").show();

        }
    }

}




//实时输入校对意见框展示
function historyAdviceProofread(json) {
    var len = json.length;
    var text = "";
    // var draft = "";
    if(len === 0){
        text = "无历史校对意见";
    }else {
        for (var i = 0; i < len; i++) {
            var index = i+1;
            text += "<tr><td>第" + index + "条校对意见: " + json[i] + "</td></tr>";
        }
    }
    document.getElementById("advice_history_proofread").innerHTML=text;
}

//实时输入审核意见框展示
function historyAdviceAudit(json) {
    var len = json.length;
    var text = "";
    if(len === 0){
        text = "无历史审核意见";
    }else {
        for (var i = 0; i < len; i++) {
            var index = i+1;
            text += "<tr><td>第" + index + "条审核意见: " + json[i] + "</td></tr>";
        }
    }
    document.getElementById("advice_history_audit").innerHTML=text;
}

//实时输入批准意见框展示
function historyAdviceAuthorize(json) {
    var len = json.length;
    var text = "";
    if(len === 0){
        text = "无历史批准意见";
    }else {
        for (var i = 0; i < len; i++) {
            var index = i+1;
            text += "<tr><td>第" + index + "条批准意见: " + json[i] + "</td></tr>";
        }
    }
    document.getElementById("advice_history_authorize").innerHTML=text;
}

//取消操作
function cancelProofread() {
    $("#advice_proofread").val("");
    window.onbeforeunload = undefined;
    window.location.href = "/GJB151BSys/views/develop/project/project.jsp?userName="+userName;
}


/*
校对/审核/批准通过
userName为用户名，devStatus为项目自身状态同数据库同名含义
审核通过时，项目状态发生改变：
校对/审核通过时，devStatus均加1；批准通过时，devStatus由3变为5
*/
function  passProofread(userName,devStatus,userId){
    console.log("传递过来的userName"+userName);
    //批准通过devStatus先加1，再在data中加1，最后状态为5
    if(devStatus == 3){
        devStatus = 4;
    }
    //devAdviceString获取意见框输入
    var devAdviceString = $("#advice").val().trim();
    console.log("devAdviceString:"+devAdviceString);
    var data = {"devId":devId,"devStatus":devStatus+1, "devStatusOriginal":devStatus, "userId":userId};
    if(devAdviceString != ""){
        var myDate = new Date();
        var adviceDate = myDate.toLocaleString();
        devAdviceString = adviceDate+" "+devAdviceString;
        var adviceJson = "";
        if(devStatus == 1){
            adviceJson = JSON.parse(devAdviceProofread);
        }else if(devStatus == 2){
            adviceJson = JSON.parse(devAdviceAudit);
        }else if(devStatus == 4){
            adviceJson = JSON.parse(devAdviceAuthorize);
        }
        adviceJson.push(devAdviceString);
        devAdviceString = JSON.stringify(adviceJson);
        if(devStatus == 1){
            data.devAdviceProofread=devAdviceString;
        }else if(devStatus == 2){
            data.devAdviceAudit=devAdviceString;
        }else if(devStatus == 4){
            data.devAdviceAuthorize=devAdviceString;
        }
    }
    console.log(data);
    $.ajax({
        type: "POST", // 请求类型（get/post）
        // url: "/GJB151BSys/proofread/updateProjectStatus",
        url:"/GJB151BSys/new/updateProjectStatus",
        // data: {"devId":devId,"devStatus":devStatus+1,"userName":userName,"devItemid":devItemid},
        data:data,
        async: true, // 是否异步
        dataType: "json", // 设置数据类型
        success: function (data){
            console.log("请求成功");
            if(data.status === 'success')  {
                if(devStatus == 1){
                    alert("校对结果提交成功！");
                }else if(devStatus == 2){
                    alert("审核结果提交成功！");
                }else if(devStatus == 4){
                    alert("批准结果提交成功！");
                }
                window.onbeforeunload = undefined;
                // $.fillTipBox({type: 'warning',icon: 'glyphicon-exclamation-sign', content: "校对结果提交成功"});
                window.location.href = "/GJB151BSys/views/develop/project/project.jsp?userName="+userName;
            }else {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});

            }
        },
        error: function (errorMsg){
            // 请求失败
            console.log("请求失败");
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: "请求失败"});
        }
    });
}

/*
校对/审核/批准不通过
userName为用户名，devStatusOriginal为项目不通过前所处的阶段
不通过时devStatus统一变为4
*/
function failProofread(userName,devStatusOriginal,userId){
    //devAdviceString获取意见框输入
    var devAdviceString = $("#advice").val().trim();
    var myDate = new Date();
    var adviceDate = myDate.toLocaleString();
    devAdviceString = adviceDate+" "+devAdviceString;
    console.log("devAdviceString"+devAdviceString);
    //按照项目本身不同的阶段json序列化历史意见
    var adviceJson = "";
    if(devStatusOriginal == 1){
        adviceJson = JSON.parse(devAdviceProofread);
    }else if(devStatusOriginal == 2){
        adviceJson = JSON.parse(devAdviceAudit);
    }else if(devStatusOriginal == 3){
        adviceJson = JSON.parse(devAdviceAuthorize);
    }
    if(devAdviceString !== null || devAdviceString != ""){
        adviceJson.push(devAdviceString);
    }
    devAdviceString = JSON.stringify(adviceJson);
    var data = {"devId":devId,"devStatus":4,"devStatusOriginal":devStatusOriginal,"userId":userId};
    if(devStatusOriginal == 1){
        data.devAdviceProofread=devAdviceString;
    }else if(devStatusOriginal == 2){
        data.devAdviceAudit=devAdviceString;
    }else if(devStatusOriginal == 3){
        data.devAdviceAuthorize=devAdviceString;
    }
    console.log("data是"+data);
    console.log(devAdviceString);
    $.ajax({
        type: "POST", // 请求类型（get/post）
        // url: "/GJB151BSys/proofread/updateProjectAdvice",
        url:"/GJB151BSys/new/updateProjectStatus",
        // data: {"devId":devId,"devStatus":4,"devAdviceProofread":devAdviceString,"devItemid":devItemid, "devStatusOriginal":devStatusOriginal},
        data: data,
        async: true, // 是否异步
        dataType: "json", // 设置数据类型
        success: function (data){
            console.log("请求成功");
            if(data.status === 'success')  {
                if(devStatusOriginal == 1){
                    alert("校对意见提交成功！");
                }else if(devStatusOriginal == 2){
                    alert("审核意见提交成功！");
                }else if(devStatusOriginal == 3){
                    alert("批准意见提交成功！");
                }
                window.onbeforeunload = undefined;
                // $.fillTipBox({type: 'warning',icon: 'glyphicon-exclamation-sign', content: "校对结果提交成功"});
                window.location.href = "/GJB151BSys/views/develop/project/project.jsp?userName="+userName;
            }else {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});

            }
        },
        error: function (errorMsg){
            // 请求失败
            console.log("请求失败");
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: "请求失败"});
        }
    });

}

