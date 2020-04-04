function showProjectInfo(id) {
    $.post("modify/findProjectById", {
        devId: id
    }, function (data) {
        if (data.status == 'success') {
            fillInfo(data.data)
        } else { //查找项目失败 返回上一页
            $.tipModal('confirm', 'warning', data.message, function(result) {
                history.back();
            })
        }
    });
}

function checkNull(data,id) {
    if(data != null){
        var stringData = data.toString();
        $(id).val(stringData);
    }

}
allProjectInfo = "";
function fillInfo(projectInfo) {
    allProjectInfo = projectInfo;
    test = projectInfo;
    //页面3
    var subsysEqp = projectInfo.devSubsysEqp.toString();
    $("#subsys_source").val(projectInfo.devSubsysSource);
    $("#subsys_com_ref").val(projectInfo.devSubsysComRef);
    $("#subsys_quantity").val(projectInfo.devSubsysQuantity);
    $("#subsys_environment").val(projectInfo.devSubsysEnvironment);
    $("#subsys_eqp").val(subsysEqp);
    // checkNull(projectInfo.devSubsysEqp,"#subsys_eqp");
    if (subsysEqp == "0") { //分系统
        $("#subsys").removeClass("hidden");
        $("#eqp").addClass("hidden");
        $("#subsys_name").val(projectInfo.devSubsysEqpName);
        $("#subsys_model").val(projectInfo.devSubsysEqpModel);
    } else {
        $("#eqp").removeClass("hidden");
        $("#subsys").addClass("hidden");
        $("#eqp_name").val(projectInfo.devSubsysEqpName);
        $("#eqp_model").val(projectInfo.devSubsysEqpModel);
        $("#eqp_num").val(projectInfo.devSubsysEqpNum);
    }
    $("#supplier").val(projectInfo.devSupplier);
    $("#primary_platform").val(projectInfo.devPrimaryPlatform.toString());
    $("#primary_platform").trigger("change");
    $("#secondary_platform").val(projectInfo.devSecondaryPlatform.toString());

    //页面4
    $("#attribute").val(projectInfo.devAttribute.toString());
    $("#key_equipment").val(projectInfo.devKey.toString());
    $("#install_mode").val(install_mode_reverse_map[projectInfo.devInstall.toString()]);
    $("#special_equipment").val(projectInfo.devSpecial.toString());
    $("#interconnected_port").val(projectInfo.devInterport.toString());
    $("#low_frequency_sensitive").val(projectInfo.devLowsensitive.toString());
    $("#emp_reinforce").val(projectInfo.devEmp.toString());
    $("#static_electricity").val(projectInfo.devStatic.toString());
    $("#ground_line").val(projectInfo.devGnd.toString());

    //电源端口信息
    powerNode = JSON.parse(projectInfo.devPowerport).length;
    console.log("powerNodeModify:"+powerNode);
    var power_name_list = JSON.parse(projectInfo.devPowername);
    var power_port_list = JSON.parse(projectInfo.devPowerport);
    var power_supply_list = JSON.parse(projectInfo.devPowersupply);
    var voltage_list = JSON.parse(projectInfo.devVoltage);
    var voltage_num_list = JSON.parse(projectInfo.devVoltagenum);
    for(var i=0; i<powerNode; i++){
        var powerNum = i+1;
        if(i>0){
            var sourcePowerNode = document.getElementById("pow_1");
            var clonePower = sourcePowerNode.cloneNode(true); //克隆电源节点
            var clonedNode = clonePower.cloneNode(true); // 克隆节点
            clonedNode.setAttribute("id", "pow_" + powerNum); // 修改一下id 值，避免id 重复
            $("#pow_" + (powerNum - 1)).after(clonedNode);
            $("#pow_" + powerNum).find('[id^="powerNumb"]').text("电源端口"+powerNum);
            $("#pow_" + powerNum).find('[name^="power_name"]').attr("id", "power_name"+powerNum);
            $("#pow_" + powerNum).find('[name^="power_port"]').attr("id", "power_port"+powerNum);
            $("#pow_" + powerNum).find('[name^="power_supply"]').attr("id", "power_supply"+powerNum);
            $("#pow_" + powerNum).find('[name^="voltage"]').attr("id", "voltage"+powerNum);
            $("#pow_" + powerNum).find('[name^="voltage_num"]').attr("id", "voltage_num"+powerNum);
            powerSelect(powerNum);
        }
        $("#power_name"+ powerNum).val(power_name_list[i]);
        $("#power_port"+ powerNum).val(power_port_reverse_map[power_port_list[i]]);
        $("#power_port"+ powerNum).trigger("change");
        $("#power_supply"+ powerNum).val(power_supply_reverse_map[power_supply_list[i]]);
        $("#power_supply"+ powerNum).trigger("change");
        $("#voltage"+ powerNum).val(voltage_reverse_map[voltage_list[i]]);
        $("#voltage"+ powerNum).trigger("change");
        $("#voltage_num"+ powerNum).val(voltage_num_reverse_map[voltage_num_list[i]]);
    }


    //天线端口信息
    // $("#antenna_removal").val(antenna_removal_reverse_map[projectInfo.devAntenna.toString()]);
    // $("#antenna_removal").trigger("change");
    // $("#receive_launch").val(receive_launch_reverse_map[projectInfo.devReceiveLaunch.toString()]);
    // $("#receive_launch").trigger("change");
    // $("#anolitude_modulation").val(anolitude_modulation_reverse_map[projectInfo.devModulation.toString()]);

    //页面5p
    $("#fre_select_option").val(projectInfo.devFreSelect.toString());
    $("#fre_select_option").trigger("change");
    if(projectInfo.devFreSelect == 1) {
        var freqOptinalList = JSON.parse(projectInfo.devFreqOptional);
        var freqOptionalLength = freqOptinalList.length;
        totalNode = freqOptionalLength;
        var sourceNode = document.getElementById("opt_1");
        var clone = sourceNode.cloneNode(true);
        for (var idx = 0; idx < freqOptionalLength; idx++) {
            if (idx != 0) { //等于0时不用克隆节点，直接使用原始界面上的节点
                var clonedNode = clone.cloneNode(true); // 克隆节点
                var cloneNodeIdx = idx + 1;
                clonedNode.setAttribute("id", "opt_" + cloneNodeIdx);
                $("#opt_" + idx).after(clonedNode);
                $("#opt_" + cloneNodeIdx).find('input[class^="form-control radius opt_freq_range"]').val(cloneNodeIdx);
                $("#opt_" + cloneNodeIdx).find('[name^="opt_connect_mode"]').attr("id", "opt_connect_mode_"+cloneNodeIdx);
                $("#opt_" + cloneNodeIdx).find('[name^="opt_wave_guide_transmit_max"]').attr("id", "opt_wave_guide_transmit_max_"+cloneNodeIdx);
                $("#opt_" + cloneNodeIdx).find('[name^="opt_cutoff_fre_name"]').attr("id", "opt_cutoff_fre_name_"+cloneNodeIdx);
                optConnectSelect(cloneNodeIdx);
            }

            //给每一个可选用频方式赋值
            var eachFreqOptMap = freqOptinalList[idx];
            var nodeIdx = idx + 1;
            eachFreqNode = $("#opt_" + nodeIdx);
            eachFreqNode.find('select[id*="opt_fre_select_option"]').val(eachFreqOptMap['opt_fre_select_option']);
            eachFreqNode.find('select[class*="opt_freq_mode"]').val(eachFreqOptMap['opt_freq_mode']);
            eachFreqNode.find('input[class*="opt_freq_range"]').val(eachFreqOptMap['opt_freq_range']);
            eachFreqNode.find('input[class*="opt_freq_low"]').val(eachFreqOptMap['opt_freq_low']);
            eachFreqNode.find('input[class*="opt_freq_mid"]').val(eachFreqOptMap['opt_freq_mid']);
            eachFreqNode.find('input[class*="opt_freq_high"]').val(eachFreqOptMap['opt_freq_high']);
            eachFreqNode.find('input[class*="opt_freq_points"]').val(eachFreqOptMap['opt_freq_points']);
            eachFreqNode.find('select[class*="opt_work_style"]').val(eachFreqOptMap['opt_work_style']);
            eachFreqNode.find('select[class*="opt_install_mode"]').val(eachFreqOptMap['opt_install_mode']);
            eachFreqNode.find('select[class*="opt_connect_mode"]').val(eachFreqOptMap['opt_connect_mode']);
            eachFreqNode.find('select[class*="opt_modulation_mode"]').val(eachFreqOptMap['opt_modulation_mode']);
            eachFreqNode.find('input[class*="opt_ave_pow_transmit_max"]').val(eachFreqOptMap['opt_ave_pow_transmit_max']);
            eachFreqNode.find('input[class*="opt_wave_guide_transmit_max"]').val(eachFreqOptMap['opt_wave_guide_transmit_max']);
            eachFreqNode.find('input[class*="opt_fix_trans_point"]').val(eachFreqOptMap['opt_fix_trans_point']);
            eachFreqNode.find('input[class*="opt_trans_speed"]').val(eachFreqOptMap['opt_trans_speed']);
            eachFreqNode.find('input[class*="opt_port_name"]').val(eachFreqOptMap['opt_port_name']);
            eachFreqNode.find('select[class*="opt_connect_mode"]').trigger("change");

            // 调制方式
            // var optModeNum = eachFreqOptMap['opt_modulation_mode_num'];
            // eachFreqNode.find('input[class="opt_modulation_mode_num"]').val(2); //展示都按照两种调制方式展示
            // eachFreqNode.find('div[class*="opt_modulation_mode_1"]').after('<div class="opt_modulation_mode_2"><br><input type="text"  style="margin:0" class="form-control radius opt_modulation_mode_2"></div>');
            // eachFreqNode.find('input[class*="opt_modulation_mode_1"]').val(optModeNum['opt_modulation_mode_1']);
            // eachFreqNode.find('input[class*="opt_modulation_mode_2"]').val(optModeNum['opt_modulation_mode_2']);
        }
    }

    //跳频
    if(projectInfo.devFreSelect == 2) {
        var FHlist = ['low', 'mid', 'high'];
        for (var index in FHlist) {
            var band = FHlist[index];
            var bandName = band.replace(band.charAt(0), band.charAt(0).toUpperCase());
            freqFhMap = JSON.parse(projectInfo['devFreqFh' + bandName]);
            FHnode = $("#FH_" + band);
            FHnode.find('input[id$="freq_range"]').val(freqFhMap['freq_range']);
            FHnode.find('input[id$="freq_low"]').val(freqFhMap['freq_low']);
            FHnode.find('input[id$="freq_high"]').val(freqFhMap['freq_high']);
            FHnode.find('input[id$="freq_points"]').val(freqFhMap['freq_high']);
            FHnode.find('select[id$="work_style"]').val(freqFhMap['work_style']);
            FHnode.find('select[id$="install_mode"]').val(freqFhMap['work_style']);
            FHnode.find('input[id$="ave_pow_transmit_max"]').val(freqFhMap['ave_pow_transmit_max']);
            FHnode.find('input[id$="port_name"]').val(freqFhMap['port_name']);
        }
    }

    //直序扩频
    if(projectInfo.devFreSelect == 3) {
        var DSSSmap = JSON.parse(projectInfo.devFreqDsss);
        $("#DSSS_trans_rate_max").val(DSSSmap['trans_rate_max']);
        $("#DSSS_freq_points").val(DSSSmap['freq_points']);
        $("#DSSS_work_style").val(DSSSmap['work_style']);
        $("#DSSS_install_mode").val(DSSSmap['install_mode']);
        $("#DSSS_ave_pow_transmit_max").val(DSSSmap['ave_pow_transmit_max']);
        $("#DSSS_port_name").val(DSSSmap['port_name']);
    }

    //页面6
    for(key in stdLimitMap){
        var projectName = 'dev'+key.replace(key.charAt(1), key.charAt(1).toLowerCase());
        var eachStdProject = JSON.parse(projectInfo[projectName]);
        stdLimitMap[key].limit_value = eachStdProject.limit_value;
        stdLimitMap[key].limit_value_current = eachStdProject.limit_value_current;
        console.log(JSON.stringify(eachStdProject));
        if(projectName == "devCe106" || projectName == "devCe107" || projectName == "devCs102" || projectName == "devCs106" || projectName == "devRe103" || projectName == "devCs112"){
            if(projectName == "devCe107"){
                for(var j= 0; j<2; j++){
                    var Ce107num = j+1;
                    if (stdLimitMap[key].limit_value[j].text != stdLimitMap[key].limit_value_current[j].text) {
                        if (voltage_list[j] == "1") {
                            stdValuesChangeMap[Ce107num + "_" + key + '_text'] = "CE107_2";
                        } else if (voltage_list[j] == "2" || voltage_list[j] == "3") {
                            stdValuesChangeMap[Ce107num + "_" + key + '_text'] = "CE107_1";
                        }
                        stdTextChangeMap[Ce107num + "_" + key] = eachStdProject.limit_value_current[j].text;
                    }
                }
            }else {
                if (stdLimitMap[key].limit_value.text != stdLimitMap[key].limit_value_current.text) {
                    if (projectName == "devCe106") {
                        if (projectInfo.devReceiveLaunch == 1) {
                            stdValuesChangeMap[key + '_text'] = "CE106_b_c";
                        } else if (projectInfo.devReceiveLaunch == 2) {
                            stdValuesChangeMap[key + '_text'] = "CE106_a";
                        } else if (projectInfo.devReceiveLaunch == 3) {
                            stdValuesChangeMap[key + '_text'] = "CE106_a_b_c";
                        }
                    }
                    // else if (projectName == "devCe107") {
                    //     if (projectInfo.devVoltage == 1) {
                    //         stdValuesChangeMap[key + '_text'] = "CE107_2";
                    //     } else if (projectInfo.devVoltage == 2 || projectInfo.devVoltage == 3) {
                    //         stdValuesChangeMap[key + '_text'] = "CE107_1";
                    //     }
                    // }
                    else if (projectName == "devCs112") {
                        if (projectInfo.devKey == 1 && projectInfo.devStatic == 1) {
                            stdValuesChangeMap[key + '_text'] = "CS112_10_11_A";
                        } else if (projectInfo.devKey == 0 && projectInfo.devStatic == 1) {
                            stdValuesChangeMap[key + '_text'] = "CS112_10_11_B";
                        }
                    } else {
                        // stdTextChangeMap[key] = eachStdProject.limit_value_current.text;
                        stdValuesChangeMap[key + '_text'] = eachStdProject.project_id;
                    }
                    stdTextChangeMap[key] = eachStdProject.limit_value_current.text;
                }
            }
        }else if(projectName == "devCs101"){
            for(var j=0; j<2; j++) {
                var Cs101num = j+1;
                key_1 = Cs101num + "_" + key + "_1";
                key_2 = Cs101num + "_" + key + "_2";
                if (stdLimitMap[key].limit_value[j].pic_one != stdLimitMap[key].limit_value_current[j].pic_one) {
                    stdBiPicChangeMap[key_1] = eachStdProject.limit_value[j].pic_one;
                }
                if (stdLimitMap[key].limit_value[j].pic_two != stdLimitMap[key].limit_value_current[j].pic_two) {
                    stdBiPicChangeMap[key_2] = eachStdProject.limit_value[j].pic_two;
                }
            }
        }else if(projectName == "devCs115" || projectName == "devCs116"){
            if(stdLimitMap[key].limit_value.text != stdLimitMap[key].limit_value_current.text) {
                stdTextChangeMap[key] = eachStdProject.limit_value_current.text;
                stdValuesChangeMap[key + '_text'] = eachStdProject.project_id;
            }
            if(stdLimitMap[key].limit_value.pic != stdLimitMap[key].limit_value_current.pic){
                stdValuesChangeMap[key] = eachStdProject.limit_value.pic;
            }
        }else{
            if(projectName == "devCe101"){
                for(var j=0; j<3; j++){
                    var Ce101num = j+1;
                    if (stdLimitMap[key].limit_value[j].pic != stdLimitMap[key].limit_value_current[j].pic) {
                        stdValuesChangeMap[Ce101num+ "_" + key] = eachStdProject.limit_value[j].pic;
                    }
                }
            }else {
                if (stdLimitMap[key].limit_value.pic != stdLimitMap[key].limit_value_current.pic) {
                    stdValuesChangeMap[key] = eachStdProject.limit_value.pic;
                }
            }
        }
        // console.log("stdTextChangeMap"+JSON.stringify(stdTextChangeMap));
        // console.log("stdValuesChangeMap"+JSON.stringify(stdValuesChangeMap));
        // console.log("stdBiPicChangeMap"+JSON.stringify(stdBiPicChangeMap));
    }
    // console.log(stdLimitMap);
    // console.log("stdTextChangeMap"+JSON.stringify(stdTextChangeMap));
    // console.log("stdValuesChangeMap"+JSON.stringify(stdValuesChangeMap));
    // console.log("stdBiPicChangeMap"+JSON.stringify(stdBiPicChangeMap));
    // console.log("devReceiveLauch:"+projectInfo.devReceiveLaunch);
}