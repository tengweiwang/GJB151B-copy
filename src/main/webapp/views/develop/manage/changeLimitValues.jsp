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
    <link rel="stylesheet" href="statics/css/develop/new/changeLimitValues.css">
</head>
<div class="modal-header">
    <button type="button" class="close modal-close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h4 class="modal-title">标准限值</h4>
</div>
<div class="modal-body">
    <!-- 图片1张 -->
    <div class="row" id="changePic1">
        <p style="margin-left: 20px;font-weight: bold;font-size: 20px" id="PicId">限值1</p>
        <div id="pic1" style="width: 650px; height: 600px;">

        </div>
        <table class="table" id="limitValueTable1">
        </table>
        <div id="imgForOne" class="form-group col-md-12">
            <button class="btn btn-primary" onclick="addLimitValueNode('limitValueTable1', 'submitNode_1', 'x1', 'y1', 0)"
                    style="margin-left: 20px">
                添加限值节点
            </button>
            <button class="btn btn-primary hidden" id="submitNode_1" onclick="submitNode('pic1', 'x1', 'y1', 0)">提交修改结果</button>
        </div>
        <br><br><br><br><br>
        <div id="imgForTwo" class="form-group col-md-12 hidden">
            <button class="btn btn-primary" onclick="changeForTwo(1)">修改标准限值</button>
            <button class="btn btn-primary hidden" id="submitNodeForTwo1" onclick="submitNodeForTwo(0)">提交修改结果</button>
        </div>
        <div id="tableForTwo1" class="hidden">
            <p style="margin-left: 10px">曲线一</p>
            <table class="table" id="lineOne1">
                <tr>
                    <td>频率</td>
                    <td><input type="text" class="form-control radius" name="lineOneX1"></td>
                    <td>限值</td>
                    <td><input type="text" class="form-control radius" name="lineOneY1"></td>
                    <%--<td>--%>
                    <%--<button class="btn btn-primary" onclick="deleteNode(this)">删除节点</button>--%>
                    <%--</td>--%>
                </tr>
            </table>
            <button class="btn btn-primary" onclick="addForTwoNode('lineOne1','lineOneX1','lineOneY1')"
                    style="margin-left: 10px">添加限值节点
            </button>
            <!-- 曲线二添加节点-->
            <p style="margin-left: 10px; margin-top:20px">曲线二</p>
            <table class="table" id="lineTwo1">
                <tr>
                    <td>频率</td>
                    <td><input type="text" class="form-control radius" name="lineTwoX1"></td>
                    <td>限值</td>
                    <td><input type="text" class="form-control radius" name="lineTwoY1"></td>
                    <%--<td>--%>
                    <%--<button class="btn btn-primary" onclick="deleteNode(this)">删除节点</button>--%>
                    <%--</td>--%>
                </tr>
            </table>
            <button class="btn btn-primary" onclick="addForTwoNode('lineTwo1','lineTwoX1','lineTwoY1')"
                    style="margin-left: 10px">添加限值节点
            </button>
        </div>
    </div>

    <!-- 曲线图一张 -->
    <div class="row" id="changeCurve">
        <p style="margin-left: 20px;font-weight: bold;font-size: 20px" id="PicCurveId">限值1</p>
        <div id="picCurve" style="width: 650px; height: 600px; margin-left: 20px">

        </div>
        <div class="col-sm-10">
            <%--<input id="myFile" type="file" name="upImg">--%>
            <span class="btn btn-primary fileinput-button" style="margin-left:20px">
                <span>上传标准限值图片</span>
                <input type="file" id="myFile" name="upImg">
            </span>
        </div>
    </div>

    <!-- 文字 -->
    <div class="row" style="margin:20px; width: 95%" id="changeText1" name="changeText">
        <p style="font-weight: bold;font-size: 20px" id="TextId">限值1</p>
        <pre id="textStd1" name="textStd"></pre>
        <br/>
        <p style="font-weight: bold">更改标准限值描述</p>
        <textarea id="textNew1" class="form-control radius" rows="5" style="width: 100%" name="textNew"></textarea><br>
        <button class="btn btn-primary" onclick="submitText(this)">确认提交
        </button>
        <br><br><br><br><br><br>
    </div>

    <!-- 图片两张 -->
    <div class="row" id="PicForTwo1">
        <p style="margin-left: 20px;font-weight: bold;font-size: 20px" id="BiPicId">限值1</p>
        <p style="margin-left: 20px;font-weight: bold" id="BiPic1Id">限值图片1</p>
        <div id="picOneDiv1" style="width: 650px; height: 600px;">
        </div>
        <table class="table" id="limitValueTableOne1">
        </table>
        <div id="biPicOne" class="form-group col-md-12">
            <button class="btn btn-primary"
                    onclick="addLimitValueNode('limitValueTableOne1', 'submitNode1_1', '1_x1', '1_y1', 0)">添加限值节点
            </button>
            <button class="btn btn-primary hidden" id="submitNode1_1" onclick="submitNode('picOneDiv1', '1_x1', '1_y1', 0)">
                提交修改结果
            </button>
        </div>
        <p style="margin-left: 20px;font-weight: bold" id="BiPic2Id">限值图片2</p>
        <div id="picTwoDiv1" style="width: 650px; height: 600px;">

        </div>
        <table class="table" id="limitValueTableTwo1">
        </table>
        <div id="biPicTwo" class="form-group col-md-12">
            <button class="btn btn-primary"
                    onclick="addLimitValueNode('limitValueTableTwo1', 'submitNode2_1', '1_x2', '1_y2', 0)">添加限值节点
            </button>
            <button class="btn btn-primary hidden" id="submitNode2_1" onclick="submitNode('picTwoDiv1', '1_x2', '1_y2', 0)">
                提交修改结果
            </button>
        </div>
        <br><br><br><br><br><br>
    </div>
</div>
</div>

</body>
<!-- 可直接使用框架提供的在线js文件 -->
<script type="text/javascript" src="repack/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="repack/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="repack/js/plugin/front.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript" src="js/develop/manage.js"></script>
<script>
    var count = [0, 0, 0];  //标记只有一张图的情况下是第几个频率限值对
    var count1 = [0, 0];  //标记两张图的情况下第一个图是第几个频率限值对
    var count2 = [0, 0];  //标记两张图的情况下第二个图是第几个频率限值对
    var thePic = -1;  //标记是两张图情况下哪张图

    var params = getParams();
    devStatus = params['devStatus'];
    Status = params['Status'];
    // console.log("Status"+Status);
    // console.log("devStatus"+devStatus);
    if(devStatus == 1 || devStatus == 2 || devStatus == 3 || Status == 1){
        $("[id^='changePic'] button").addClass("hidden");
        $("#changeCurve span").addClass("hidden");
        $("[id^='changeText'] button").addClass("hidden");
        $("[id^='changeText'] p").addClass("hidden");
        $("[id^='changeText'] textarea").addClass("hidden");
        $("[id^='PicForTwo'] button").addClass("hidden");

    }

    if (project == "CE106" || project == "CE107" || project == "CS102" || project == "CS106" || project == "RE103" || project == "CS112") {
        $("[id^='changePic']").addClass("hidden");
        $("[id^='PicForTwo']").addClass("hidden");
        $("#changeCurve").addClass("hidden");
        $("[id^='changeText']").removeClass("hidden");
        showText();
    } else if (project == "CS101") {
        $("[id^='PicForTwo']").removeClass("hidden");
        $("[id^='changePic']").addClass("hidden");
        $("[id^='changeText']").addClass("hidden");
        $("#changeCurve").addClass("hidden");
        showTwoImg();
    } else if (project == "RS105") {  //一个曲线图
        $("[id^='PicForTwo']").addClass("hidden");
        $("[id^='changePic']").addClass("hidden");
        $("[id^='changeText']").addClass("hidden");
        $("#changeCurve").removeClass("hidden");
        showImgUpload();
    } else if (project == "CS115") {  //一个曲线图 一个文字描述
        $("[id^='changePic']").addClass("hidden");
        $("[id^='PicForTwo']").addClass("hidden");
        $("#changeCurve").removeClass("hidden");
        $("[id^='changeText']").removeClass("hidden");
        showText();
        showImgUpload();
    } else if (project == "CS116") {  //一个折线图 一个文字描述
        $("[id^='PicForTwo']").addClass("hidden");
        $("#changeCurve").addClass("hidden");
        $("[id^='changePic']").removeClass("hidden");
        $("[id^='changeText']").removeClass("hidden");
        showImg();
        showText();
    } else {
        $("[id^='changePic']").removeClass("hidden");
        $("[id^='changeText']").addClass("hidden");
        $("[id^='PicForTwo']").addClass("hidden");
        $("#changeCurve").addClass("hidden");
        // if (imgNum == '9' || imgNum == '10') {
        //     $("#imgForOne").addClass("hidden");
        //     $("#imgForTwo").removeClass("hidden");
        // }
        showImg();
    }

    $('#myFile').on('change', function () {//当 input file 已经选中时触发js

        var formData = new FormData();
        var file = this.files[0];
        var fileType = file.type;
        var fileSize = file.size / 1024;

        if (fileType != 'image/png') {  //限制为只能上传png图片
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '只支持png格式的图片'});
        } else if (fileSize > 2048) {
            $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '图片大小不能超过2M'});
        } else {
            var windowURL = window.URL || window.webkitURL;
            formData.append("upImg", file);
            formData.append("stdProject", project);
            formData.append("devId", devId);
            formData.append("devName", devName);

            $.ajax({
                url: 'new/uploadImg',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if (data.status == 'success') {
                        var pic = $("#picCurve").empty();
                        var imgInfo = document.createElement("img");
                        // imgInfo.className = 'img-responsive';
                        imgInfo.style.maxHeight = '600px';
                        imgInfo.style.maxWidth = '650px';
                        var imgURL = windowURL.createObjectURL(file);
                        imgInfo.setAttribute("src", imgURL);
                        pic.append(imgInfo);
                        //保存完图片后设置为此项目的限值已经被调整
                        stdValuesChangeMap[project] = imgAll[0].imgNum;
                        stdLimitMap[project]['limit_value_current']['pic'] = data.data;
                    } else {
                        $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
                    }
                },
                error: function () {
                    $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '图片上传失败，请稍后再试'});
                }
            })

        }
    });


    function changeForTwo(j) {
        $("#tableForTwo"+j).removeClass("hidden");
        $("#submitNodeForTwo"+j).removeClass("hidden")
    }

    function addForTwoNode(tableName, xName, yName) {
        var tr = '<tr><td>频率</td><td><input type="text" class="form-control radius" name="' + xName + '"></td><td>限值</td><td><input type="text" class="form-control radius" name="' + yName + '"></td><td><button class="btn btn-primary" onclick="deleteNode(this, \'' + tableName + '\')">删除节点</button></td></tr>';
        $("#" + tableName).append(tr)
        console.log("addForTwoNode");
    }

    // function showUpload() {
    //     $("#myFile").fileinput({
    //         language: 'zh',  //设置语言
    //         uploadUrl: "new/uploadImg",
    //         autoReplace: true,
    //         maxFileCount: 1,  //最大上传文件个数
    //         allowedFileExtensions: ["jpg", "png"], //接收的文件后缀
    //         browseClass: "btn btn-primary", //按钮样式
    //         dropZoneEnabled: false,//是否显示拖拽区域
    //         showClose: false,
    //         previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
    //         layoutTemplates: {
    //             actionUpload: '' //去除上传预览缩略图中的上传图片；
    //         },
    //         showUpload: true
    //     }).on("fileuploaded", function (event, data) {
    //         $("#myFile").empty();
    //         $(event.target)
    //             .fileinput('clear')
    //             .fileinput('unlock')
    //         $(event.target)
    //             .parent()
    //             .siblings('.fileinput-remove')
    //             .hide();
    //         debugger
    //         imgSrc = data.reader.result;
    //         showImgUpload();
    //         //保存完图片后设置为此项目的限值已经被调整
    //         stdValuesChangeMap['test_' + project] = imgNum;
    //     }).on('fileerror', function (event, data, msg) {  //上传失败错误处理
    //         // 清除当前的预览图 ，并隐藏 【移除】 按钮
    //         $(event.target)
    //             .fileinput('clear')
    //             .fileinput('unlock')
    //         $(event.target)
    //             .parent()
    //             .siblings('.fileinput-remove')
    //             .hide();
    //         $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '图片上传失败，请稍后再试'});
    //     });
    // }

    function showText() {
        // if(textDescription.length == 1){
        //     $("#textStd1").text(textDescription[0]);
        // }else {
            var textNode = document.getElementById("changeText1");
            var cloneNodeText = textNode.cloneNode(true);
            var j=0;
            for (var i = 0; i < textDescription.length; i++) {
                j=i+1;
                if(i>0) {
                    var clonetext = cloneNodeText.cloneNode(true);
                    clonetext.setAttribute("id", "changeText" + j);
                    $("#changeText" + (j - 1)).after(clonetext);
                    // $("#changeText" + j).find('[id^="TextId"]').text("限值"+j);
                    $("#changeText" + j).find('[id^="TextId"]').text(stdLimitNameMap[textNum[i]]);
                    $("#changeText" + j).find('[name^="textStd"]').attr("id", "textStd" + j);
                    $("#changeText" + j).find('[name^="textNew"]').attr("id", "textNew" + j);
                }else{
                    $("#changeText1").find('[id^="TextId"]').text(stdLimitNameMap[textNum[0]]);
                }
                $("#textStd"+j).text(textDescription[i]);
            }
        // }


    }

    function showImg() {

        var imgNode = document.getElementById("changePic1");
        var cloneNodeImg = imgNode.cloneNode(true);
        var j=0;
        for(var i=0; i<imgAll.length; i++){
            j=i+1;
            if(i>0) {
                var cloneImg = cloneNodeImg.cloneNode(true);
                cloneImg.setAttribute("id", "changePic" + j);
                $("#changePic" + (j - 1)).after(cloneImg);
                $("#changePic" + j).find('[id^="pic"]').attr("id", "pic" + j);
                $("#changePic" + j).find('[id^="limitValueTable"]').attr("id", "limitValueTable" + j);
                $("#changePic" + j).find('[id^="PicId"]').text(stdLimitNameMap[imgAll[i].imgNum]);
                if(imgAll[i].imgNum == 9 || imgAll[i].imgNum == 10){
                    // $("#changePic" + j).find('[id^="imgForOne"]').addClass("hidden");
                    // $("#changePic" + j).find('[id^="imgForTwo"]').removeClass("hidden");
                    $("#changePic" + j).find('[onclick^="changeForTwo"]').attr("onclick","changeForTwo("+ j +")");
                    $("#changePic" + j).find('[id^="tableForTwo"]').attr("id", "tableForTwo" + j);
                    $("#changePic" + j).find('[id^="submitNodeForTwo"]').attr("id", "submitNodeForTwo" + j);
                    $("#changePic" + j).find('[id^="lineOne"]').attr("id", "lineOne" + j);
                    $("#changePic" + j).find('[name^="lineOneX"]').attr("name", "lineOneX" + j);
                    $("#changePic" + j).find('[name^="lineOneY"]').attr("name", "lineOneY" + j);
                    $("#changePic" + j).find('[onclick^="addForTwoNode(\'lineOne"]').attr("onclick", "addForTwoNode('lineOne"+ j +"','lineOneX"+ j + "','lineOneY" + j + "')");
                    $("#changePic" + j).find('[id^="lineTwo"]').attr("id", "lineTwo" + j);
                    $("#changePic" + j).find('[name^="lineTwoX"]').attr("name", "lineTwoX" + j);
                    $("#changePic" + j).find('[name^="lineTwoY"]').attr("name", "lineTwoY" + j);
                    $("#changePic" + j).find('[onclick^="addForTwoNode(\'lineTwo"]').attr("onclick", "addForTwoNode('lineTwo"+ j +"','lineTwoX"+ j + "','lineTwoY" + j + "')");
                    $("#changePic" + j).find('[onclick^="submitNodeForTwo"]').attr("onclick", "submitNodeForTwo("+ i +")");

                }else {
                    $("#changePic" + j).find('[onclick^="addLimitValueNode"]').attr("onclick", "addLimitValueNode('limitValueTable" + j + "', 'submitNode_" + j + "', 'x"+ j +"', 'y"+ j +"', "+ i +")");
                    $("#changePic" + j).find('[id^="submitNode"]').attr("id", "submitNode_" + j);
                    $("#changePic" + j).find('[onclick^="submitNode(\'pic"]').attr("onclick", "submitNode('pic"+ j +"', 'x"+ j +"', 'y"+ j +"', "+ i +")");
                }
            }else{
                $("#changePic1").find('[id^="PicId"]').text(stdLimitNameMap[imgAll[0].imgNum]);
            }

            var pic = document.getElementById("pic"+j);
            var imgInfo = document.createElement("img");
            imgInfo.setAttribute("src", imgAll[i].imgSrc);
            pic.appendChild(imgInfo);
            if(imgAll[i].imgNum == 9 || imgAll[i].imgNum == 10) {
                $("#changePic" + j).find('[id^="imgForOne"]').addClass("hidden");
                $("#changePic" + j).find('[id^="imgForTwo"]').removeClass("hidden");
            }
        }
        // var pic = document.getElementById("pic1");
        // var imgInfo = document.createElement("img");
        // imgInfo.setAttribute("src", imgAll[0].imgSrc);
        // pic.appendChild(imgInfo);
        // if(imgAll.length>1){
        //
        // }
    }

    function showImgUpload() {
        var pic = $("#picCurve").empty();
        var imgInfo = document.createElement("img");
        $("#changeCurve").find('[id^="PicCurveId"]').text(stdLimitNameMap[imgAll[0].imgNum]);
        imgInfo.setAttribute("src", imgAll[0].imgSrc);
        pic.append(imgInfo);

        // showUpload();
    }

    function showTwoImg() {
        var biPicNode = document.getElementById("PicForTwo1");
        var cloneNodeBiPic = biPicNode.cloneNode(true);
        var i=0;
        for(var j in imgBiAll) {
            i=j-1;
            if(i>0){
                var cloneBiPic = cloneNodeBiPic.cloneNode(true);
                cloneBiPic.setAttribute("id", "PicForTwo" + j);
                $("#PicForTwo" + (j - 1)).after(cloneBiPic);
                $("#PicForTwo" + j).find('[id^="BiPicId"]').text("限值"+j);
                $("#PicForTwo" + j).find('[id^="BiPic1Id"]').text(stdLimitNameMap[imgBiAll[j][0].imgNum]);
                $("#PicForTwo" + j).find('[id^="BiPic2Id"]').text(stdLimitNameMap[imgBiAll[j][1].imgNum]);
                $("#PicForTwo" + j).find('[id^="picOneDiv"]').attr("id", "picOneDiv" + j);
                $("#PicForTwo" + j).find('[id^="limitValueTableOne"]').attr("id", "limitValueTableOne" + j);
                $("#PicForTwo" + j).find('[id^="submitNode1"]').attr("id", "submitNode1_" + j);
                $("#PicForTwo" + j).find('[onclick^="addLimitValueNode(\'limitValueTableOne"]').attr("onclick", "addLimitValueNode('limitValueTableOne"+ j +"', 'submitNode1_"+ j +"', '"+ j +"_x1', '"+ j +"_y1', "+ i +")");
                $("#PicForTwo" + j).find('[id^="picTwoDiv"]').attr("id", "picTwoDiv" + j);
                $("#PicForTwo" + j).find('[id^="limitValueTableTwo"]').attr("id", "limitValueTableTwo" + j);
                $("#PicForTwo" + j).find('[id^="submitNode2"]').attr("id", "submitNode2_" + j);
                $("#PicForTwo" + j).find('[onclick^="addLimitValueNode(\'limitValueTableTwo"]').attr("onclick", "addLimitValueNode('limitValueTableTwo"+ j +"', 'submitNode2_"+ j +"', '"+ j +"_x2', '"+ j +"_y2', "+ i +")");
                $("#PicForTwo" + j).find('[onclick^="submitNode(\'picOneDiv"]').attr("onclick", "submitNode('picOneDiv"+ j +"', '"+ j +"_x1', '"+ j +"_y1', "+ i +")");
                $("#PicForTwo" + j).find('[onclick^="submitNode(\'picTwoDiv"]').attr("onclick", "submitNode('picTwoDiv"+ j +"', '"+ j +"_x2', '"+ j +"_y2', "+ i +")");

            }else {
                $("#PicForTwo1").find('[id^="BiPic1Id"]').text(stdLimitNameMap[imgBiAll[1][0].imgNum]);
                $("#PicForTwo1").find('[id^="BiPic2Id"]').text(stdLimitNameMap[imgBiAll[1][1].imgNum]);
            }
            var pic1 = document.getElementById("picOneDiv"+j);
            var imgInfo1 = document.createElement("img");
            imgInfo1.setAttribute("src", imgBiAll[j][0].imgSrc);
            pic1.appendChild(imgInfo1);

            var pic2 = document.getElementById("picTwoDiv"+j);
            var imgInfo2 = document.createElement("img");

            imgInfo2.setAttribute("src", imgBiAll[j][1].imgSrc);
            pic2.appendChild(imgInfo2);
        }

    }

    function submitText(element) {
        if(project == 'CE107'){
            var id = element.parentNode.id;
            var value = id.replace(/[^0-9]/ig, "");
            console.log("id:"+id+"value:"+value);
            var text = $("#textNew"+value).val();
            $("#textStd"+value).text(text);
            stdTextChangeMap[value+"_"+project] = text;
            stdValuesChangeMap[value+"_"+project + '_text'] = textNum[value-1];
            stdLimitMap[project]['limit_value_current'][value-1]['text'] = text;
        }else {
            var text = $("#textNew1").val();
            $("#textStd1").text(text);
            stdTextChangeMap[project] = text;
            stdValuesChangeMap[project + '_text'] = textNum[0];
            stdLimitMap[project]['limit_value_current']['text'] = text;
        }
    }

    function addLimitValueNode(tableName, submitId, xAxis, yAxis, i) {
        var num = parseInt(i)+1;
        var tr = '<tr><td>频率</td><td><input type="text" class="form-control radius" name="' + xAxis + '"></td><td>限值</td><td><input type="text" class="form-control radius" name="' + yAxis + '"></td><td><button class="btn btn-primary" onclick="deleteNode(this, \'' + tableName + '\')">删除节点</button></td></tr>';
        if (tableName == 'limitValueTableOne'+num) {
            count1[i]++;
        } else if (tableName == 'limitValueTableTwo'+num) {
            count2[i]++;
        } else {
            count[i]++;
        }
        $("#" + tableName).append(tr);
        $("#" + submitId).removeClass("hidden")
    }

    function submitNodeForTwo(numNode) {
        var emptyFlag = false;
        var axisListOne = [];
        var idNode = parseInt(numNode)+1;
        $("#lineOne" + idNode).find('input[name^="lineOneX"]').each(function () {
            var axisNodeOne = [];
            if (!isRealNum($(this).val())) {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '曲线一频率不能为空'});
                emptyFlag = true;
                return;
            }

            axisNodeOne.push($(this).val());
            axisListOne.push(axisNodeOne)
        });


        if (!emptyFlag) {
            var i = 0;
            $("#lineOne" + idNode).find('input[name^="lineOneY"]').each(function () {
                if (!isRealNum($(this).val())) {
                    $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '曲线一限值不能为空'});
                    emptyFlag = true;
                    return;
                }
                axisListOne[i++].push($(this).val())
            });
        } else {
            return
        }

        if (emptyFlag) {
            return
        }

        var axisListTwo = [];
        $("#lineTwo" + idNode).find('input[name^="lineTwoX"]').each(function () {
            var axisNodeTwo = [];
            if (!isRealNum($(this).val())) {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '曲线二频率不能为空'});
                emptyFlag = true;
                return;
            }

            axisNodeTwo.push($(this).val());
            axisListTwo.push(axisNodeTwo)
        });

        if (!emptyFlag) {
            i = 0;
            $("#lineTwo" + idNode).find('input[name^="lineTwoY"]').each(function () {
                if (!isRealNum($(this).val())) {
                    $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '曲线二限值不能为空'});
                    emptyFlag = true;
                    return;
                }
                axisListTwo[i++].push($(this).val())
            });
        } else {
            return
        }

        if (emptyFlag) {
            return
        }

        markPointListOne = [];
        for (i in axisListOne) {
            axisNode = axisListOne[i];
            axisMap = {};
            valueStr = '(' + formatterValue(axisNode[0]) + ',' + formatterValue(axisNode[1]) + ')';
            axisMap['value'] = valueStr;
            axisMap['xAxis'] = axisNode[0];
            axisMap['yAxis'] = axisNode[1];
            markPointListOne.push(axisMap)
        }
        markPointListTwo = [];
        for (i in axisListTwo) {
            axisNode = axisListTwo[i];
            axisMap = {};
            valueStr = '(' + formatterValue(axisNode[0]) + ',' + formatterValue(axisNode[1]) + ')';
            axisMap['value'] = valueStr;
            axisMap['xAxis'] = axisNode[0];
            axisMap['yAxis'] = axisNode[1];
            markPointListTwo.push(axisMap)
        }
        imgInfo = imgAll[numNode].imgInfo;
        imgMinX = imgAll[numNode].imgMinX;
        imgMaxX = imgAll[numNode].imgMaxX;
        imgMinY = imgAll[numNode].imgMinY;
        imgMaxY = imgAll[numNode].imgMaxY;
        imgAxisX = imgAll[numNode].imgAxisX;
        imgAxisY = imgAll[numNode].imgAxisY;
        imgNum = imgAll[numNode].imgNum;

        drawImgForTwo(axisListOne, markPointListOne, axisListTwo, markPointListTwo, numNode)
    }

    function submitNode(picName, xName, yName, nodeNum) {
        // if (count == 0) {
        //     $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '请添加标准限值节点'});
        //     return
        // }
        var num = parseInt(nodeNum)+1;
        //对于两张图片的情况，标识修改的是第一幅图还是第二幅图
        if (picName == 'picTwoDiv'+num) {
            if (count2[nodeNum] == 0) {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '请添加图片二标准限值节点'});
                return
            }
            thePic = 2;
            imgInfo = imgBiAll[num][1].imgInfo;
            imgMinX = imgBiAll[num][1].imgMinX;
            imgMaxX = imgBiAll[num][1].imgMaxX;
            imgMinY = imgBiAll[num][1].imgMinY;
            imgMaxY = imgBiAll[num][1].imgMaxY;
            imgAxisX = imgBiAll[num][1].imgAxisX;
            imgAxisY = imgBiAll[num][1].imgAxisY;
            imgNum2 = imgBiAll[num][1].imgNum;
        } else if (picName == 'picOneDiv'+num) {
            if (count1[nodeNum] == 0) {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '请添加图片一标准限值节点'});
                return
            }
            thePic = 1;
            imgInfo = imgBiAll[num][0].imgInfo;
            imgMinX = imgBiAll[num][0].imgMinX;
            imgMaxX = imgBiAll[num][0].imgMaxX;
            imgMinY = imgBiAll[num][0].imgMinY;
            imgMaxY = imgBiAll[num][0].imgMaxY;
            imgAxisX = imgBiAll[num][0].imgAxisX;
            imgAxisY = imgBiAll[num][0].imgAxisY;
            imgNum1 = imgBiAll[num][0].imgNum;
        } else {
            if (count[nodeNum] == 0) {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '请添加标准限值节点'});
                return
            }
            imgInfo = imgAll[nodeNum].imgInfo;
            imgMinX = imgAll[nodeNum].imgMinX;
            imgMaxX = imgAll[nodeNum].imgMaxX;
            imgMinY = imgAll[nodeNum].imgMinY;
            imgMaxY = imgAll[nodeNum].imgMaxY;
            imgAxisX = imgAll[nodeNum].imgAxisX;
            imgAxisY = imgAll[nodeNum].imgAxisY;
            imgNum = imgAll[nodeNum].imgNum;

        }

        var emptyFlag = false;
        var axisList = [];
        $('input[name=' + xName + ']').each(function () {
            var axisNode = [];
            if (!isRealNum($(this).val())) {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '频率不能为空'});
                emptyFlag = true;
                return
            }

            axisNode.push($(this).val());
            axisList.push(axisNode)
        });


        if (!emptyFlag) {
            var i = 0;
            $('input[name=' + yName + ']').each(function () {
                if (!isRealNum($(this).val())) {
                    $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: '限值不能为空'});
                    emptyFlag = true
                    return
                }
                axisList[i++].push($(this).val())
            });
        } else {
            return
        }

        if (emptyFlag) {
            return
        }

        markPointList = [];
        for (i in axisList) {
            axisNode = axisList[i];
            axisMap = {};
            var valueStr = '(' + formatterValue(axisNode[0]) + ',' + formatterValue(axisNode[1]) + ')';
            axisMap['value'] = valueStr;
            axisMap['xAxis'] = axisNode[0];
            axisMap['yAxis'] = axisNode[1];
            markPointList.push(axisMap)
        }

        drawImg(axisList, markPointList, picName, nodeNum)
    }

    function isRealNum(val) {
        if (val.trim().length === 0 || val == null) {
            return false;
        }

        if (!isNaN(val)) {
            return true;
        } else {
            return false;
        }
    }

    function deleteNode(node, tableName) {
        $(node).parent().parent().remove();
        if (tableName == 'limitValueTableOne') {
            count1--;
        } else if (tableName == 'limitValueTableTwo') {
            count2--;
        } else {
            count--;
        }
        // count--;
    }

    function formatterValue(value) {
        var label = "";
        if (value >= Math.pow(10, 3) && value < Math.pow(10, 6)) {
            label = value / Math.pow(10, 3) + 'K'
        } else if (value >= Math.pow(10, 6) && value < Math.pow(10, 9)) {
            label = value / Math.pow(10, 6) + 'M'
        } else if (value >= Math.pow(10, 9)) {
            label = value / Math.pow(10, 9) + 'G'
        } else {
            label = value
        }

        return label;
    }

    function drawImg(axisList, markPointList, picName, nodeNum) {
        var picDiv = echarts.init(document.getElementById(picName));
        var picOption = {
            tooltip: {
                trigger: 'item'
            }, //提示框触发方式：trigger，axis:坐标轴触发，主要在柱状图、折线图等会使用类目轴的图表中使用

            calculable: true,
            animation: false,
            title: [{
                subtext: imgInfo,
                subtextStyle: {
                    color: 'black',
                    lineHeight: 20,
                    rich: {
                        lineHeight: 20
                    },
                    fontSize: 14
                },
                left: '10%',
                top: '68%'
            }],

            grid: {
                bottom: '40%'
            },

            xAxis: [
                {
                    type: 'log',  //category 类目轴，需要通过data设置类目数据 与series 中data对应，data是一维数组
                    boundaryGap: false,
                    name: imgAxisX,
                    nameLocation: 'center',
                    nameGap: 25,
                    min: imgMinX,
                    max: imgMaxX,
                    axisLabel: {
                        formatter: function (value) {
                            var labels = [];
                            if (value >= Math.pow(10, 3) && value < Math.pow(10, 6)) {
                                labels.push(value / Math.pow(10, 3) + 'K')
                            } else if (value >= Math.pow(10, 6) && value < Math.pow(10, 9)) {
                                labels.push(value / Math.pow(10, 6) + 'M')
                            } else if (value >= Math.pow(10, 9)) {
                                labels.push(value / Math.pow(10, 9) + 'G')
                            } else {
                                labels.push(value)
                            }

                            return labels;
                        },
                        interval: 0
                    }
                }
            ],
            yAxis: [
                {

                    type: 'value',  //value ，数值轴，适用于连续的数据，是数据类型的数据 与series 中data对应，data是二维数组，第一个数值对应x轴
                    name: imgAxisY,
                    nameLocation: 'center',
                    nameGap: 30,
                    min: imgMinY,
                    max: imgMaxY
                }
            ],
            series: [
                {
                    // name: '限值（dBμA）',
                    type: 'line',
                    symbol: 'none',
                    color: 'black',
                    data: axisList,
                    markPoint: {
                        symbol: 'pin',
                        symbolSize: 0.1,
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    position: 'top',
                                    fontSize: 10
                                }
                            }
                        },
                        data: markPointList,
                    }
                }
            ]
        };

        picDiv.setOption(picOption);
        var pivInfo = picDiv.getDataURL({
            type: 'png',
            backgroundColor: 'white',
            pixelRatio: 1
        });
        var j = parseInt(nodeNum)+1;

        //保存完图片成功后设置为此项目的限值已经被调整
        if (project == "CS101") {
            if (thePic == 1) {
                // projectNum = project + '_1';
                projectNum = j + "_" + project + "_1";
                $.post("new/saveImg", {imgBase64: pivInfo, stdProject: projectNum, devId: devId, devName: devName}, function (data) {
                    if (data.status == "error") {
                        $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
                    } else {
                        stdBiPicChangeMap[projectNum] = imgNum1;
                        stdLimitMap[project]['limit_value_current'][nodeNum]['pic_one'] = data.data;
                    }
                });
            } else {
                // projectNum = project + '_2';
                projectNum = j + "_" + project + "_2";
                $.post("new/saveImg", {imgBase64: pivInfo, stdProject: projectNum, devId: devId, devName: devName}, function (data) {
                    if (data.status == "error") {
                        $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
                    } else {
                        stdBiPicChangeMap[projectNum] = imgNum2;
                        stdLimitMap[project]['limit_value_current'][nodeNum]['pic_two'] = data.data;
                    }
                });
            }
        } else {
            if(project == 'CE101'){
                stproject = j+"_"+project;
            }else{
                stproject = project;
            }
            $.post("new/saveImg", {imgBase64: pivInfo, stdProject: stproject, devId: devId, devName: devName}, function (data) {
                if (data.status == "error") {
                    $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
                } else {
                    if(project == 'CE101'){

                        stdValuesChangeMap[j+"_"+project] = imgNum;
                        console.log("j:"+j);
                        stdLimitMap[project]['limit_value_current'][nodeNum]['pic'] = data.data;
                        console.log("stdValuesChangeMap"+stdValuesChangeMap[j+"_"+project]+"j:"+j+"nodeNum:"+nodeNum);
                        console.log(stdValuesChangeMap);
                    }else {
                        stdValuesChangeMap[project] = imgAll[0].imgNum;
                        stdLimitMap[project]['limit_value_current']['pic'] = data.data;
                    }
                }
            });
        }
    }

    function drawImgForTwo(axisListOne, markPointListOne, axisListTwo, markPointListTwo, numNode) {
        var num = parseInt(numNode)+1;
        console.log("num:"+num);
        var picDiv = echarts.init(document.getElementById("pic"+num));
        var picOption = {
            tooltip: {
                trigger: 'item'
            }, //提示框触发方式：trigger，axis:坐标轴触发，主要在柱状图、折线图等会使用类目轴的图表中使用
            legend: {
                data: ['曲线一', '曲线二']
            },
            calculable: true,
            animation: false,
            title: [{
                subtext: imgInfo,
                subtextStyle: {
                    color: 'black',
                    lineHeight: 20,
                    rich: {
                        lineHeight: 20
                    },
                    fontSize: 14
                },
                left: '10%',
                top: '68%'
            }],

            grid: {
                bottom: '40%'
            },

            xAxis: [
                {
                    type: 'log',  //category 类目轴，需要通过data设置类目数据 与series 中data对应，data是一维数组
                    boundaryGap: false,
                    name: imgAxisX,
                    nameLocation: 'center',
                    nameGap: 25,
                    min: imgMinX,
                    max: imgMaxX,
                    axisLabel: {
                        formatter: function (value) {
                            var labels = [];
                            if (value >= Math.pow(10, 3) && value < Math.pow(10, 6)) {
                                labels.push(value / Math.pow(10, 3) + 'K')
                            } else if (value >= Math.pow(10, 6) && value < Math.pow(10, 9)) {
                                labels.push(value / Math.pow(10, 6) + 'M')
                            } else if (value >= Math.pow(10, 9)) {
                                labels.push(value / Math.pow(10, 9) + 'G')
                            } else {
                                labels.push(value)
                            }

                            return labels;
                        },
                        interval: 0
                    }
                }
            ],
            yAxis: [
                {

                    type: 'value',  //value ，数值轴，适用于连续的数据，是数据类型的数据 与series 中data对应，data是二维数组，第一个数值对应x轴
                    name: imgAxisY,
                    nameLocation: 'center',
                    nameGap: 30,
                    min: imgMinY,
                    max: imgMaxY
                }
            ],
            series: [
                {
                    name: '曲线一',
                    type: 'line',
                    symbol: 'circle',
                    symbolSize: 6,
                    color: 'black',
                    data: axisListOne,
                    markPoint: {
                        symbol: 'pin',
                        symbolSize: 0.1,
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    position: 'top',
                                    fontSize: 10
                                }
                            }
                        },
                        data: markPointListOne
                    }
                },
                {
                    name: '曲线二',
                    type: 'line',
                    symbol: 'triangle',
                    symbolSize: 6,
                    color: 'black',
                    data: axisListTwo,
                    markPoint: {
                        symbol: 'pin',
                        symbolSize: 0.1,
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    position: 'top',
                                    fontSize: 10
                                }
                            }
                        },
                        data: markPointListTwo
                    }
                }
            ]
        };

        picDiv.setOption(picOption);
        var pivInfo = picDiv.getDataURL({
            type: 'png',
            backgroundColor: 'white',
            pixelRatio: 1
        });
        if(project == 'CE101'){
            stproject = num+"_"+project;
        }else{
            stproject = project;
        }
        $.post("new/saveImg", {imgBase64: pivInfo, stdProject: stproject, devId: devId, devName: devName}, function (data) {
            if (data.status == "error") {
                $.fillTipBox({type: 'warning', icon: 'glyphicon-exclamation-sign', content: data.message});
            } else {
                if(project == 'CE101'){

                    stdValuesChangeMap[num+"_"+project] = imgNum;
                    console.log("num:"+num);
                    stdLimitMap[project]['limit_value_current'][numNode]['pic'] = data.data;
                }else {
                    //图片保存成功后设置为此项目的限值已经被调整
                    stdValuesChangeMap[project] = imgNum;
                    stdLimitMap[project]['limit_value_current']['pic'] = data.data;
                }
            }
        });
    }
</script>
</body>
</html>
