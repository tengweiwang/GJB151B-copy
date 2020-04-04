function cancelProofread() {
    // var adviceJson = JSON.parse(devAdviceProofread);
    // if(adviceJson[adviceJson.length-1].substr(0,2) === '##'){
    //     adviceJson.pop();}
    // var devAdviceProofreadString = JSON.stringify(adviceJson);
    // console.log("cancel的devAdviceProofreadString"+devAdviceProofreadString);
    // $.ajax({
    //     type: "POST", // 请求类型（get/post）
    //     url: "/GJB151BSys/proofread/saveAdviceDraft",
    //     data: {"devId":devId,"devAdviceProofread":devAdviceProofreadString},
    //     async: true, // 是否异步
    //     dataType: "json", // 设置数据类型
    //     success: function (data){
    //         console.log("请求成功");
    //         if(data.status === 'success')  {
    //             alert("操作取消成功！");
    //             window.location.href = "/GJB151BSys/views/develop/project/project.jsp?userName="+userName;
    //         }else {
    //             $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
    //
    //         }
    //     },
    //     error: function (errorMsg){
    //         // 请求失败
    //         console.log("请求失败");
    //     }
    // });
    $("#advice_proofread").val("");
    window.location.href = "/GJB151BSys/views/develop/project/project.jsp?userName="+userName;
    // document.getElementById("advice_proofread").value="";
}

function historyAdviceProofread(json) {
    var len = json.length;
    var text = "";
    // var draft = "";
    if(len === 0){
        text = "无历史校对意见";
    }else {
        for (var i = 0; i < len; i++) {
            text += "<tr><td>" + json[i] + "</td></tr>";
        }
        // if (json[len - 1].substr(0, 2) === '##') {
        //     draft = json[len - 1].substring(2);
        //     document.getElementById("advice_proofread").value = draft;
        // } else {
        //     text += "<tr><td>" + json[len - 1] + "</td></tr>";
        // }
    }
    document.getElementById("advice_history_proofread").innerHTML=text;
    // document.getElementById("advice_proofread").value=draft;
}


function historyAdviceAudit(json) {
    var len = json.length;
    var text = "";
    if(len === 0){
        text = "无历史审核意见";
    }else {
        for (var i = 0; i < len; i++) {
            text += "<tr><td>" + json[i] + "</td></tr>";
        }
        // if (json[len - 1].substr(0, 2) === '##') {
        //     // draft = json[len-1].substring(2);
        //     // document.getElementById("advice_proofread").value=draft;
        // } else {
        //     text += "<tr><td>" + json[len - 1] + "</td></tr>";
        // }
    }
    document.getElementById("advice_history_audit").innerHTML=text;
}


function historyAdviceAuthorize(json) {
    var len = json.length;
    var text = "";
    if(len === 0){
        text = "无历史批准意见";
    }else {
        for (var i = 0; i < len; i++) {
            text += "<tr><td>" + json[i] + "</td></tr>";
        }
        // if (json[len - 1].substr(0, 2) === '##') {
        //     // draft = json[len-1].substring(2);
        //     // document.getElementById("advice_proofread").value=draft;
        // } else {
        //     text += "<tr><td>" + json[len - 1] + "</td></tr>";
        // }
    }
    document.getElementById("advice_history_authorize").innerHTML=text;
}


function  passProofread(userName,devStatus){
    console.log("传递过来的userName"+userName);
    if(devStatus == 3){
        devStatus = 4;
    }
    $.ajax({
        type: "POST", // 请求类型（get/post）
        url: "/GJB151BSys/proofread/updateProjectStatus",
        data: {"devId":devId,"devStatus":devStatus+1,"userName":userName,"devItemid":devItemid},
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
                // $.fillTipBox({type: 'warning',icon: 'glyphicon-exclamation-sign', content: "校对结果提交成功"});
                window.location.href = "/GJB151BSys/views/develop/project/project.jsp?userName="+userName;
            }else {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});

            }
        },
        error: function (errorMsg){
            // 请求失败
            console.log("请求失败");
        }
    });
}

function failProofread(userName,devStatusOriginal){
    var devAdviceString = $("#advice").val();
    // console.log(devAdviceProofreadString);
    var adviceJson = "";
    if(devStatusOriginal == 1){
        adviceJson = JSON.parse(devAdviceProofread);
    }else if(devStatusOriginal == 2){
        adviceJson = JSON.parse(devAdviceAudit);
    }else if(devStatusOriginal == 3){
        adviceJson = JSON.parse(devAdviceAuthorize);
    }

    // if(adviceJson[adviceJson.length-1].substr(0,2) === '##'){
    //     adviceJson[adviceJson.length-1]=devAdviceProofreadString;
    // }else{
    //     adviceJson.push(devAdviceProofreadString);
    // }


    // console.log(adviceJson);
    // adviceJson.push(devAdviceProofreadString);
    if(devAdviceString !== null){
        adviceJson.push(devAdviceString);
    }
    devAdviceString = JSON.stringify(adviceJson);
    var data = {"devId":devId,"devStatus":4,"devItemid":devItemid, "devStatusOriginal":devStatusOriginal};
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
        url: "/GJB151BSys/proofread/updateProjectAdvice",
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
                // $.fillTipBox({type: 'warning',icon: 'glyphicon-exclamation-sign', content: "校对结果提交成功"});
                window.location.href = "/GJB151BSys/views/develop/project/project.jsp?userName="+userName;
            }else {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});

            }
        },
        error: function (errorMsg){
            // 请求失败
            console.log("请求失败");
        }
    });

}

function draftProofread(){
    var devAdviceProofreadString = "##"+$("#advice_proofread").val();
    var adviceJson = JSON.parse(devAdviceProofread);
    // console.log(adviceJson);
    if(adviceJson[adviceJson.length-1].substr(0,2) === '##'){
        adviceJson[adviceJson.length-1]=devAdviceProofreadString;
    }else{
        adviceJson.push(devAdviceProofreadString);
    }
    // adviceJson.push(devAdviceProofreadString);
    devAdviceProofreadString = JSON.stringify(adviceJson);
    console.log(devAdviceProofreadString);
    $.ajax({
        type: "POST", // 请求类型（get/post）
        url: "/GJB151BSys/proofread/saveAdviceDraft",
        data: {"devId":devId,"devAdviceProofread":devAdviceProofreadString},
        async: true, // 是否异步
        dataType: "json", // 设置数据类型
        success: function (data){
            console.log("请求成功");
            if(data.status === 'success')  {
                alert("草稿保存成功！");
                // $.fillTipBox({type: 'warning',icon: 'glyphicon-exclamation-sign', content: "校对结果提交成功"});
                // window.location.href = "/GJB151BSys/views/develop/project/project.jsp?userName="+userName;
            }else {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});

            }
        },
        error: function (errorMsg){
            // 请求失败
            console.log("请求失败");
        }
    });
}

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


var standard_list = [];
var protocol_list = [];
var extra_list = [];

//1 文字 2 双图 3 单曲线图 4 单曲线图+文字 5 单折线图+文字 6 单折线图 7 特殊处理的项目
stdLimitMap = {
    'CE101': {'project_id': 'CE101', 'type': 6, 'limit_value': {}, 'limit_value_current': {}},
    'CE102': {'project_id': 'CE102', 'type': 6, 'limit_value': {}, 'limit_value_current': {}},
    'CE106': {'project_id': 'CE106', 'type': 1, 'limit_value': {}, 'limit_value_current': {}},
    'CE107': {'project_id': 'CE107', 'type': 1, 'limit_value': {}, 'limit_value_current': {}},
    'CS101': {'project_id': 'CS101', 'type': 2, 'limit_value': {}, 'limit_value_current': {}},
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

function submitManageSysDevelop() {
    console.log("Begin to record the ManageSysDevelop info");

    var voltage = voltage_map[$("#voltage").val()];
    var voltage_num = voltage_num_map[$("#voltage_num").val()];
    var receive_launch = receive_launch_map[$("#receive_launch").val()];
    var key_equipment = key_equipment_map[$("#key_equipment").val()];
    var static_electricity = static_electricity_map[$("#static_electricity").val()];
    var special_equipment = special_equipment_map[$("#special_equipment").val()];
    var antenna_removal = antenna_removal_map[$("#antenna_removal").val()];
    var secondary_platform = parseInt($("#secondary_platform").val());

    $.ajax({
        type: "post",
        url: "/GJB151BSys/new/getRequiredSubject",
        data: {
            //判定需要测试项目信息
            primary_platform: parseInt($("#primary_platform").val()),                                //一级平台
            secondary_platform: secondary_platform,                            //二级平台
            power_port: power_port_map[$("#power_port").val()],                                      //电源端口
            power_supply: power_supply_map[$("#power_supply").val()],                                //平台主电源供电
            voltage: voltage,                                               //电源供电频率（交直流）
            voltage_num: voltage_num,                                   //电源供电电压
            special_equipment: special_equipment,                 //特殊设备
            antenna_removal: antenna_removal,                       //天线端口
            receive_launch: receive_launch,                          //天线端口模式
            anolitude_modulation: anolitude_modulation_map[$("#anolitude_modulation").val()],        //天线调制模式
            ground_line: $("#ground_line").val(),                                   //带地线
            static_electricity: static_electricity,              //静电威胁
            interconnected_port: $("#interconnected_port").val(),           //互联端口
            emp_reinforce: $("#emp_reinforce").val(),                             //emp加固
            key_equipment: key_equipment,                             //任务关键设备
            install_mode: install_mode_map[$("#install_mode").val()],                                //安装方式
            low_frequency_sensitive: $("#low_frequency_sensitive").val() //对低频信号敏感
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

            if (standard_list.length === 0) {
                $("#standard_subject_table").addClass("hidden");
                $("#standard_subject_table_none").removeClass("hidden");
            } else {
                $("#standard_subject_table").removeClass("hidden");
                $("#standard_subject_table_none").addClass("hidden");
                for (var standard_list_index = 0; standard_list_index < standard_list.length; standard_list_index++) {
                    $("#standard_subject_table").append(generate_template_subject_table_element(standard_list[standard_list_index]))
                    $("#standard_subject_table").attr("disabled",true);
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
                }
            }

            if (extra_list.length === 0) {
                $("#protocol_optional_table").addClass("hidden");
                $("#protocol_optional_table_none").removeClass("hidden");
            } else {
                $("#protocol_optional_table").removeClass("hidden");
                $("#protocol_optional_table_none").addClass("hidden");
                for (var protocol_list_index = 0; protocol_list_index < extra_list.length; protocol_list_index++) {
                    $("#protocol_optional_table").append(generate_template_subject_table_element(extra_list[protocol_list_index]))
                }
            }

            getAllStdLimit(voltage, voltage_num, receive_launch, key_equipment, static_electricity, special_equipment, antenna_removal, secondary_platform);

        },
        error: function (data) {
            console.log("ajax 请求失败");
            console.log(data)
        }
    });

    console.log("Finish to record ManageSysDevelop");

}

function getAllStdLimit(voltage, voltage_num, receive_launch, key_equipment, static_electricity, special_equipment, antenna_removal, secondary_platform) {
    $.ajaxSettings.async = false;
    for (var key in stdLimitMap) {
        if ($.inArray(key, stdTextList) != -1) {
            getStdTextValue(voltage, receive_launch, key_equipment, static_electricity, antenna_removal, secondary_platform, key);
        } else if ($.inArray(key, stdBiPicList) != -1) {
            getStdBiPicValue(voltage_num, key);
        } else if ($.inArray(key, stdCurveList) != -1 || $.inArray(key, stdLineList) != -1) {
            getpicValue(voltage, voltage_num, receive_launch, key_equipment, static_electricity, special_equipment, secondary_platform, key)
        } else if ($.inArray(key, stdCurveTextList) != -1 || $.inArray(key, stdLineTextList) != -1) {
            getStdTextValue(voltage, receive_launch, key_equipment, static_electricity, antenna_removal, secondary_platform, key);
            getpicValue(voltage, voltage_num, receive_launch, key_equipment, static_electricity, special_equipment, secondary_platform, key)
        } else {
            stdLimitMap[key]['limit_value']['value'] = '';
            stdLimitMap[key]['limit_value']['bias'] = '';
            stdLimitMap[key]['limit_value_current']['value'] = '';
            stdLimitMap[key]['limit_value_current']['bias'] = '';
        }
    }


    // console.log(JSON.stringify(stdLimitMap))
}

function getStdTextValue(voltage, receive_launch, key_equipment, static_electricity, antenna_removal, secondary_platform, key) {
    $.post("new/getLimitValuesText", {
        receiveLaunch: receive_launch,
        projectId: key,
        voltage: voltage,
        antennaRemoval: antenna_removal,
        keyEquipment: key_equipment,
        staticElectricity: static_electricity,
        secondaryPlatform: secondary_platform
    }, function (data) {
        if (data.status == 'success') {
            stdLimitMap[key]['limit_value']['text'] = data.data['textDescription'];
            stdLimitMap[key]['limit_value_current']['text'] = data.data['textDescription'];
        } else {  //不满足任何标准限值则设置为空
            stdLimitMap[key]['limit_value']['text'] = '';
            stdLimitMap[key]['limit_value_current']['text'] = '';
        }
    });
}

function getStdBiPicValue(voltage_num, key) {
    $.post("new/getLimitValuesBiPic", {
        voltageNum: voltage_num,
        projectId: key,
        picOneNow: "",
        picTwoNow: ""
    }, function (data) {
        if (data.status === 'error') {
            stdLimitMap[key]['limit_value']['pic_one'] = '';
            stdLimitMap[key]['limit_value']['pic_two'] = '';
            stdLimitMap[key]['limit_value_current']['pic_one'] = '';
            stdLimitMap[key]['limit_value_current']['pic_two'] = '';
        } else {
            stdLimitMap[key]['limit_value']['pic_one'] = data.data[0].imgNum;
            stdLimitMap[key]['limit_value']['pic_two'] = data.data[1].imgNum;
            stdLimitMap[key]['limit_value_current']['pic_one'] = data.data[0].imgNum;
            stdLimitMap[key]['limit_value_current']['pic_two'] = data.data[1].imgNum;
        }
    });
}

function getpicValue(voltage, voltage_num, receive_launch, key_equipment, static_electricity, special_equipment, secondary_platform, key) {   //曲线图或折线图
    $.post("new/getLimitValuesPic", {
        voltage: voltage,
        voltageNum: voltage_num,
        receiveLaunch: receive_launch,
        keyEquipment: key_equipment,
        staticElectricity: static_electricity,
        specialEquipment: special_equipment,
        projectId: key,
        secondaryPlatform: secondary_platform,
        imgNumNow: "-1"
    }, function (data) {
        if (data.status === 'success') {
            stdLimitMap[key]['limit_value']['pic'] = data.data['imgNum'];
            stdLimitMap[key]['limit_value_current']['pic'] = data.data['imgNum'];
        } else {
            stdLimitMap[key]['limit_value']['pic'] = '';
            stdLimitMap[key]['limit_value_current']['pic'] = '';
        }
    });
}

function generate_template_subject_table_element(project_id) {
    var res = "";
    switch (project_id) {
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
                '<td><select class="form-control" id="' + project_id + '_usage" readonly disabled="disabled"><option value="1" selected>适用</option><option value="0">不适用</option></select></td>' +
                '<td><button class="btn btn-default" onclick=adjustLimitValues("' + project_id + '") readonly disabled="disabled">限值调整</button></td>' +
                '<td><input type="text" class="form-control radius" id="' + project_id + '_remark" readonly></td>' +
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
                '<td><select class="form-control" id="' + project_id + '_usage" readonly disabled="disabled"><option value="1" selected>适用</option><option value="0">不适用</option></select></td>' +
                '<td><button class="btn btn-default" onclick=adjustText("' + project_id + '") readonly disabled="disabled">限值调整</button></td>' +
                '<td><input type="text" class="form-control radius" id="' + project_id + '_remark" readonly></td>' +
                '</tr>';
            break;
        case 'CS115':
        case 'CS116':
            res += '<tr>' +
                '<td>' + project_id + '</td>' +
                '<td><select class="form-control" id="' + project_id + '_usage" readonly disabled="disabled"><option value="1" selected>适用</option><option value="0">不适用</option></select></td>' +
                '<td><button class="btn btn-default" onclick=adjustTextAndPic("' + project_id + '") readonly disabled="disabled">限值调整</button></td>' +
                '<td><input type="text" class="form-control radius" id="' + project_id + '_remark" readonly ></td>' +
                '</tr>';
            break;
        case 'CS103':
        case 'CS104':
        case 'CS105':
            res += '<tr>' +
                '<td>' + project_id + '</td>' +
                '<td><select class="form-control" id="' + project_id + '_usage" readonly disabled="disabled"><option value="1" selected>适用</option><option value="0">不适用</option></select></td>' +
                '<td><input type="text" class="form-control radius" id="' + project_id + '_limit" readonly ></td>' +
                '<td><input type="text" class="form-control radius" id="' + project_id + '_remark" readonly ></td>' +
                '</tr>';
            break;
        case 'CS101':
            res += '<tr>' +
                '<td>' + project_id + '</td>' +
                '<td><select class="form-control" id="' + project_id + '_usage" readonly disabled="disabled"><option value="1" selected>适用</option><option value="0">不适用</option></select></td>' +
                '<td><button class="btn btn-default" onclick=adjustBiPic("' + project_id + '") readonly disabled="disabled">限值调整</button></td>' +
                '<td><input type="text" class="form-control radius" id="' + project_id + '_remark" readonly></td>' +
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

}

function prePage() {
    if (page_num === 6) {
        $("#submit").addClass("hidden");

        var attribute = $("#attribute").val();
        if (attribute === '0') {  //在设备属性为非用频时直接跳到第四页
            $("#new" + page_num).addClass("hidden");
            page_num = page_num - 2;
            $("#new" + page_num).removeClass("hidden");

            return
        }
    }
    $("#new" + page_num).addClass("hidden");
    page_num = page_num - 1;
    $("#new" + page_num).removeClass("hidden");
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

    $("#power_port").on('change',
        function () {
            var port = $(this).val();
            var powerSupplyMap = {};
            if (port === 'input_port') {
                powerSupplyMap = {'platform_main': '平台主电源供电', 'no_platform_main': '非平台主电源供电'};
            } else {
                powerSupplyMap = {'none': '无'};
            }

            var option = "";
            for (var i in powerSupplyMap) {
                option += '<option value="' + i + '">' + powerSupplyMap[i] + '</option>';
            }

            $("#power_supply").empty().append(option);
            $("#power_supply").trigger("change");
        });

    $("#power_supply").on('change',
        function () {
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

            $("#voltage").empty().append(option);
            $("#voltage").trigger("change");
        });

    $("#voltage").on('change',
        function () {
            var voltage = $(this).val();
            var voltageNumMap = {};
            if (voltage === 'direct_current') {
                if ($("#power_supply").val() === 'platform_main') {
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

            $("#voltage_num").empty().append(option);
        });

    $("#antenna_removal").on('change',
        function () {
            var antenna = $(this).val();
            var receiveLaunchMap = {};
            if (antenna === 'none') {
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
            if (removal === 'removal') {
                if (mode === 'receive' || mode === 'receive_and_launch') {
                    amMap = {'am': '调幅', 'no_am': '非调幅'};
                } else {
                    amMap = {'none': '无'};
                }
            } else {
                amMap = {'none': '无'};
            }

            var option = "";
            for (var i in amMap) {
                option += '<option value="' + i + '">' + amMap[i] + '</option>';
            }

            $("#anolitude_modulation").empty().append(option);
        });
});

function adjustLimitValues(projectId) {
    if (projectId == null || projectId.length === 0) {
        $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '没有选择需要修改的项目'});
    }

    project = projectId;

    var voltage = conditionMap[$("#voltage").val()];
    var voltageNum = conditionMap[$("#voltage_num").val()];
    var receiveLaunch = conditionMap[$("#receive_launch").val()];
    var keyEquipment = $("#key_equipment").val();
    var staticElectricity = $("#static_electricity ").val();
    var specialEquipment = $("#special_equipment ").val();
    var secondaryPlatform = $("#secondary_platform").val();
    // var stdValueChange = 0;  //还未被点击
    // if(stdValuesChangeMap.hasOwnProperty('test_'+projectId)) {
    //     stdValueChange = 1;  //已被点击
    // }
    imgNumNow = '-1';
    if (stdValuesChangeMap.hasOwnProperty(projectId)) {
        imgNumNow = stdValuesChangeMap[projectId]
    }

    // console.log('voltage' + voltage);
    // console.log('voltageNum' + voltageNum);
    // console.log('receiveLaunch' + receiveLaunch);
    // console.log('keyEquipment' + keyEquipment);
    // console.log('staticElectricity' + staticElectricity);
    // console.log('specialEquipment' + specialEquipment);

    $.post("new/getLimitValuesPic", {
        voltage: voltage,
        voltageNum: voltageNum,
        receiveLaunch: receiveLaunch,
        keyEquipment: keyEquipment,
        staticElectricity: staticElectricity,
        specialEquipment: specialEquipment,
        projectId: projectId,
        secondaryPlatform: secondaryPlatform,
        // stdValueChange: stdValueChange
        imgNumNow: imgNumNow
    }, function (data) {
        if (data.status === 'error') {
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
        } else {
            // for(var key in data.data) {
            //    imgNum = data.data;
            //    imgDescription = data.data[key];
            // }
            imgNum = data.data['imgNum'];
            imgInfo = data.data['imgInfo'];
            imgSrc = data.data['imgSrc'];
            imgMinX = data.data['imgMinX'];
            imgMaxX = data.data['imgMaxX'];
            imgMinY = data.data['imgMinY'];
            imgMaxY = data.data['imgMaxY'];
            imgAxisX = data.data['imgAxisX'];
            imgAxisY = data.data['imgAxisY'];

            if (imgNum != '-1') {
                // $.frontModal({size: 'modal-lg', href: 'views/develop/manage/changeLimitValues.jsp'}).on('shown.bs.modal', function () {
                // })
                if (projectId != 'CS115' && projectId != 'CS116') {
                    $("#changeLimitValueModal").click();
                }
            } else {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '不存在标准限值图'});
            }
        }
    });
}

function adjustBiPic(projectId) {
    if (projectId == null || projectId.length === 0) {
        $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '没有选择需要修改的项目'});
    }

    var voltageNum = conditionMap[$("#voltage_num").val()];
    project = projectId;
    imgNumNow1 = "";
    imgNumNow2 = "";
    if (stdBiPicChangeMap.hasOwnProperty(projectId + '_1')) {
        imgNumNow1 = stdBiPicChangeMap[projectId + '_1'];
    }

    if (stdBiPicChangeMap.hasOwnProperty(projectId + '_2')) {
        imgNumNow2 = stdBiPicChangeMap[projectId + '_2'];
    }

    $.post("new/getLimitValuesBiPic", {
        voltageNum: voltageNum,
        projectId: projectId,
        picOneNow: imgNumNow1,
        picTwoNow: imgNumNow2
    }, function (data) {
        if (data.status === 'error') {
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
        } else {
            picOneInfo = data.data[0];
            picTwoInfo = data.data[1];
            $("#changeLimitValueModal").click();
        }
    });
}

function adjustText(projectId) {
    if (projectId == null || projectId.length === 0) {
        $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '没有选择需要修改的项目'});
    }

    var receiveLaunch = conditionMap[$("#receive_launch").val()];
    var voltage = conditionMap[$("#voltage").val()];
    var antennaRemoval = conditionMap[$("#antenna_removal").val()];
    var keyEquipment = $("#key_equipment").val();
    var staticElectricity = $("#static_electricity ").val();

    textNow = "";
    if (stdValuesChangeMap.hasOwnProperty(projectId)) {
        textNow = stdValuesChangeMap[projectId];
    }

    project = projectId;
    $.post("new/getLimitValuesText", {
        receiveLaunch: receiveLaunch,
        projectId: project,
        voltage: voltage,
        antennaRemoval: antennaRemoval,
        keyEquipment: keyEquipment,
        staticElectricity: staticElectricity
    }, function (data) {
        if (data.status === 'error') {
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
        } else {
            textNum = data.data['textNum'];
            if (textNum == textNow) {
                textDescription = stdTextChangeMap[project];
            } else {
                textDescription = data.data['textDescription'];
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

function submitProject(type) {
    var subsysOrEqp = $("#subsys_eqp").val();
    var formData = new FormData();
    //提交类型，如果是save，则项目状态变为待审核，如果是draft，则状态变为待修改
    if (type == 'save') {
        formData.append('devStatus', 1);
    } else if (type == 'draft') {
        formData.append('devStatus', 4)
    }


    // 页面3的数据
    formData.append('devSubsysEqp', subsysOrEqp);
    formData.append('devSubsysEqpName', subsysOrEqpName);
    formData.append('devSubsysEqpModel', subsysOrEqpModel);
    formData.append('devSubsysEqpNum', subsysOrEqpNum);
    formData.append('devSupplier', $("#supplier").val());
    formData.append('devPrimaryPlatform', $("#primary_platform").val());
    formData.append('devSecondaryPlatform', $("#secondary_platform").val());

    //页面4的数据
    formData.append('devAttribute', $("#attribute").val());
    formData.append('devKey', $("#key_equipment").val());
    formData.append('devInstall', install_mode_map[$("#install_mode").val()]);
    formData.append('devGnd', $("#ground_line").val());
    formData.append('devSpecial', $("#special_equipment").val());
    formData.append('devInterport', $("#interconnected_port").val());
    formData.append('devLowsensitive', $("#low_frequency_sensitive").val());
    formData.append('devEmp', $("#emp_reinforce").val());
    formData.append('devStatic', $("#static_electricity").val());
    formData.append('devPowerport', power_port_map[$("#power_port").val()]);
    formData.append('devPowersupply', power_supply_map[$("#power_supply").val()]);
    formData.append('devVoltage', voltage_map[$("#voltage").val()]);
    formData.append('devVoltagenum', voltage_num_map[$("#voltage_num").val()]);
    formData.append('devAntenna', antenna_removal_map[$("#antenna_removal").val()]);
    formData.append('devReceiveLaunch', receive_launch_map[$("#receive_launch").val()]);
    formData.append('devModulation', anolitude_modulation_map[$("#anolitude_modulation").val()]);

    //页面5的数据
    //可选用频方式
    var optSelector = $('div[id^="opt_"]');
    var optList = [];
    $.each(optSelector, function (idx, value) {
        var optEach = {};
        optEach['opt_freq_mode'] = $(value).find('select[class*="opt_freq_mode"]').val();
        optEach['opt_freq_range'] = $(value).find('input[class*="opt_freq_range"]').val();
        optEach['opt_freq_low'] = $(value).find('input[class*="opt_freq_low"]').val();
        optEach['opt_freq_mid'] = $(value).find('input[class*="opt_freq_mid"]').val();
        optEach['opt_freq_high'] = $(value).find('input[class*="opt_freq_high"]').val();
        var freq_points = $(value).find('input[class*="opt_freq_points"]').val();
        if (freq_points == '固定多频点时填写' || optEach['opt_freq_mode'] == '1') {
            freq_points = '';
        }
        optEach['opt_freq_points'] = freq_points;
        optEach['opt_work_style'] = $(value).find('select[class*="opt_work_style"]').val();
        optEach['opt_install_mode'] = $(value).find('select[class*="opt_install_mode"]').val();
        optEach['opt_ave_pow_transmit_max'] = $(value).find('input[class*="opt_ave_pow_transmit_max"]').val();
        optEach['opt_port_name'] = $(value).find('input[class*="opt_port_name"]').val();

        var modulationSelector = $(value).find('input[class*="opt_modulation_mode"]'); //还有opt_modulation_mode_num也会被取到，所以加上opt_modulation_mode_1和opt_modulation_mode_2最多有三个，最少有两个
        var modulationMap = {};
        if (modulationSelector.length == 2) {
            modulationMap['opt_modulation_mode_1'] = $(modulationSelector[1]).val();
            modulationMap['opt_modulation_mode_2'] = '';
        } else if (modulationSelector.length == 3) {
            modulationMap['opt_modulation_mode_1'] = $(modulationSelector[1]).val();
            modulationMap['opt_modulation_mode_2'] = $(modulationSelector[2]).val();
        }

        optEach['opt_modulation_mode_num'] = modulationMap;

        optList.push(optEach)
    });

    formData.append('devFreqOptional', JSON.stringify(optList));

    //跳频
    var FHlist = ['low', 'mid', 'high'];
    for (var index in FHlist) {
        var band = FHlist[index];
        var eachFHmap = {};
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

        var FHkey = 'devFreqFh' + band.replace(band.charAt(0), band.charAt(0).toUpperCase());
        formData.append(FHkey, JSON.stringify(eachFHmap))
    }

    //直序扩频
    var DSSSmap = {};
    DSSSmap['trans_rate_max'] = $("#DSSS_trans_rate_max").val();
    DSSSmap['freq_points'] = $("#DSSS_freq_points").val();
    DSSSmap['work_style'] = $("#DSSS_work_style").val();
    DSSSmap['install_mode'] = $("#DSSS_install_mode").val();
    DSSSmap['ave_pow_transmit_max'] = $("#DSSS_ave_pow_transmit_max").val();
    DSSSmap['port_name'] = $("#DSSS_port_name").val();
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


    $.ajax({
        url: 'new/saveNewProject',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.status == 'success') {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: "成功"});
            } else {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
            }
        },
        error: function () {
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '保存项目失败，请稍后再试。'});
        }
    });
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

var i = 1;

function increase(para) {
    var s;
    s = $(para).parents("div").parents("div").attr("id");
    if (parseInt($("#" + s).find('input[class^="opt_modulation_mode_num"]').val()) < 2) {
        $("#" + s).find('div[class^="opt_modulation_mode_1"]').after('<div class="opt_modulation_mode_2"><br><input type="text"  style="margin:0" class="form-control radius opt_modulation_mode_2"></div>');
        $("#" + s).find('input[class^="opt_modulation_mode_num"]').val(parseInt($("#" + s).find('input[class^="opt_modulation_mode_num"]').val()) + 1);
    }
    else
        $.tipModal('alert', '', '最多添加两个');
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
var clone = sourceNode.cloneNode(true); // 克隆节点
function addFrequency() {
    if (i < 20) {
        i = i + 1;
        var clonedNode = clone.cloneNode(true); // 克隆节点
        clonedNode.setAttribute("id", "opt_" + i); // 修改一下id 值，避免id 重复
        $("#opt_" + (i - 1)).after(clonedNode);
        $("#opt_" + i).find('input[class^="form-control radius opt_freq_range"]').val(i);
    }
    else
        $.tipModal('alert', '', '最多添加20个');
}

function deleteFrequency(element) {
    var id = element.parentNode.parentNode.parentNode.id;
    var value = id.replace(/[^0-9]/ig, "");//id中取数字
    var num = i;//记录当前共几个节点
    if (i > 1) {
        $("#" + id).remove();
        value++;
        for (var a = value; a <= num; a++) {
            $("#opt_" + a).find('input[class^="form-control radius opt_freq_range"]').val(a - 1);
            $("#opt_" + a).attr("id", "opt_" + (a - 1));
        }
        i--;
    }
    else
        $.tipModal('alert', '', '至少有一个');
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