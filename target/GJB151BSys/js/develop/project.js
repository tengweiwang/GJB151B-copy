//按照项目完成与否定义8个列表
var newtext = "";
var proofreadtext = "";
var audittext ="";
var authorizetext = "";
var modifytext = "";
var endnew = "";
var endproofread = "";
var endaudit = "";
var endauthorize = "";
var showtext = "";
var currentdata;
// var params = getParams();
// var userName = params['userName'];
function showText(data,act) {
    currentdata = data;
    var text1 = "'><td >";
    var text2 = "\n";
    var new1 = "<tr id='new";
    var new2 = "</td><td style=\"text-align: center\"><div >待编制</div>\n" +
        "</td>\n" +"<td style=\"text-align: center;border: 0px\"><a href='/GJB151BSys/views/develop/new/new.jsp?devName=";
    var new3 = "&devStatus=";
    var new4 = "&devId=";
    var new5 = "'>编制</a>" +"</td>\n";
    var newn = "</tr>\n";
    var endnew1 = "<tr><td>";
    var endnew2 = "</td><td style=\"text-align: center\"><div >已编制</div>\n" +
        "</td>\n" +"<td style=\"text-align: center;border: 0px\"><a href='/GJB151BSys/views/develop/new/new.jsp?devName=";
    var endnew3 = "&devStatus=";
    var endnew4 = "&Status=1&devId=";
    var endnew5 = "'>查看</a></td></tr>";
    var proofread1 = "<tr id='proofread";
    var proofread2 = "</td><td style=\"text-align: center\"><div >待校对</div>\n" +
        "</td>\n" +"<td style=\"text-align: center;border: 0px\"><a href='/GJB151BSys/views/develop/new/new.jsp?devName=";
    var proofread3 ="&devStatus=";
    var proofread4 ="&devId=";
    var proofread5 ="'>校对</a>" +"</td>\n";
    var proofreadn ="</tr>\n";
    var endproofread1 = "<tr><td>";
    var endproofread2 = "</td><td style=\"text-align: center\"><div >已校对</div>\n" +
        "</td>\n" +"<td style=\"text-align: center;border: 0px\"><a href='/GJB151BSys/views/develop/new/new.jsp?devName=";
    var endproofread3 = "&devStatus=";
    var endproofread4 = "&Status=1&devId=";
    var endproofread5 = "'>查看</a></td></tr>";
    var audit1 = "<tr id='audit";
    var audit2 = "</td><td style=\"text-align: center\"><div >待审核</div>\n" +
        "</td>\n" +"<td style=\"text-align: center;border: 0px\"><a href='/GJB151BSys/views/develop/new/new.jsp?devName=";
    var audit3 = "&devStatus=";
    var audit4 = "&devId=";
    var audit5 = "'>审核</a>" +"</td>\n";
    var auditn = "</tr>\n";
    var endaudit1 = "<tr><td>";
    var endaudit2 = "</td><td style=\"text-align: center\"><div >已审核</div>\n" +
        "</td>\n" +"<td style=\"text-align: center;border: 0px\"><a href='/GJB151BSys/views/develop/new/new.jsp?devName=";
    var endaudit3 = "&devStatus=";
    var endaudit4 = "&Status=1&devId=";
    var endaudit5 = "'>查看</a></td></tr>";
    var authorize1 = "<tr id='authorize";
    var authorize2 = "</td><td style=\"text-align: center\"><div >待批准</div>\n" +
        "</td>\n" +"<td style=\"text-align: center;border: 0px\"><a href='/GJB151BSys/views/develop/new/new.jsp?devName=";
    var authorize3 = "&devStatus=";
    var authorize4 = "&devId=";
    var authorize5 = "'>批准</a>" +"</td>\n";
    var authorizen = "</tr>\n";
    var endauthorize1 = "<tr><td>";
    var endauthorize2 = "</td><td style=\"text-align: center\"><div >已批准</div>\n" +
        "</td>\n" +"<td style=\"text-align: center;border: 0px\"><a href='/GJB151BSys/views/develop/new/new.jsp?devName=";
    var endauthorize3 = "&devStatus=";
    var endauthorize4 = "&Status=1&devId=";
    var endauthorize5 = "'>查看</a></td></tr>";
    var modify1 = "<tr id='modify";
    var modify2 = "</td><td style=\"text-align: center\"><div >待修改</div>\n" +
        "</td>\n" +"<td style=\"text-align: center;border: 0px\"><a href='/GJB151BSys/views/develop/new/new.jsp?devName=";
    var modify3 = "&devStatus=";
    var modify4 = "&devId=";
    var modify5 = "'>修改</a>" +"</td>\n";
    var modifyn ="</tr>\n";


    for (var i=0;i<data.showNew.length;i++)
    {

        // newtext += new1+i+text1+data.showNew[i].devName+text2+new2+i+newn;
    newtext += new1+i+text1+data.showNew[i].devName+text2+new2+data.showNew[i].devName+new3+data.showNew[i].devStatus+new4+data.showNew[i].devId+new5+newn;
    }
    for (var i=0;i<data.showProofread.length;i++)
    {
        // proofreadtext += proofread1+i+text1+data.showProofread[i].devName+text2+proofread2;
        proofreadtext += proofread1+i+text1+data.showProofread[i].devName+text2+proofread2+data.showProofread[i].devName+proofread3+data.showProofread[i].devStatus+proofread4+data.showProofread[i].devId+proofread5+proofreadn;

    }
    for (var i=0;i<data.showAudit.length;i++)
    {
        // audittext += audit1+i+text1+data.showAudit[i].devName+text2+audit2;
        audittext += audit1+i+text1+data.showAudit[i].devName+text2+audit2+data.showAudit[i].devName+audit3+data.showAudit[i].devStatus+audit4+data.showAudit[i].devId+audit5+auditn;
    }
    for (var i=0;i<data.showAuthorize.length;i++)
    {
        // authorizetext += authorize1+i+text1+data.showAuthorize[i].devName+text2+authorize2;
        authorizetext += authorize1+i+text1+data.showAuthorize[i].devName+text2+authorize2+data.showAuthorize[i].devName+authorize3+data.showAuthorize[i].devStatus+authorize4+data.showAuthorize[i].devId+authorize5+authorizen;
    }
    for (var i=0;i<data.showModify.length;i++)
    {
        // modifytext += modify1+i+text1+data.showModify[i].devName+text2+modify2;
        modifytext += modify1+i+text1+data.showModify[i].devName+text2+modify2+data.showModify[i].devName+modify3+data.showModify[i].devStatus+modify4+data.showModify[i].devId+modify5+modifyn;

    }
    for (var i=0;i<data.endNew.length;i++){
        endnew += endnew1+data.endNew[i].devName+endnew2+data.endNew[i].devName+endnew3+data.endNew[i].devStatus+endnew4+data.endNew[i].devId+endnew5;
    }
    for (var i=0;i<data.endProofread.length;i++){
        // endproofread += endproofread1+data.endProofread[i].devName+endproofread2;
        endproofread += endproofread1+data.endProofread[i].devName+endproofread2+data.endProofread[i].devName+endproofread3+data.endProofread[i].devStatus+endproofread4+data.endProofread[i].devId+endproofread5;
    }
    for (var i=0;i<data.endAudit.length;i++){
        // endaudit += endaudit1+data.endAudit[i].devName+endaudit2;
        endaudit += endaudit1+data.endAudit[i].devName+endaudit2+data.endAudit[i].devName+endaudit3+data.endAudit[i].devStatus+endaudit4+data.endAudit[i].devId+endaudit5;
    }
    for (var i=0;i<data.endAuthorize.length;i++){
        // endauthorize += endauthorize1+data.endAuthorize[i].devName+endauthorize2;
        endauthorize += endauthorize1+data.endAuthorize[i].devName+endauthorize2+data.endAuthorize[i].devName+endauthorize3+data.endAuthorize[i].devStatus+endauthorize4+data.endAuthorize[i].devId+endauthorize5;
    }
    if(act == "0"){
        $("#nav-left-offcanvas").hide();
        $("#table").attr("style","margin-left:100px");
        document.getElementById("project_name").innerHTML="项目名称";
        showtext = newtext+modifytext+endnew;
    }else if(act == "1"){
        $("#nav-left-offcanvas").hide();
        $("#table").attr("style","margin-left:100px");
        document.getElementById("project_name").innerHTML="项目名称";
        showtext = proofreadtext+endproofread;
    }else if(act == "2"){
        $("#nav-left-offcanvas").hide();
        $("#table").attr("style","margin-left:100px");
        document.getElementById("project_name").innerHTML="项目名称";
        showtext = audittext+endaudit;
    }else if(act == "3"){
        $("#nav-left-offcanvas").hide();
        $("#table").attr("style","margin-left:100px");
        document.getElementById("project_name").innerHTML="项目名称";
        showtext = authorizetext+endauthorize;
    }else{
        $("#nav-left-offcanvas").show();
        $("#table").removeAttr("style");
        document.getElementById("project_name").innerHTML="待完成项目";
        showtext = newtext+proofreadtext+audittext+authorizetext+modifytext;
    }

    // showtext = newtext+proofreadtext+audittext+authorizetext+modifytext;

    return showtext;
}

function userAll() {
    if(showtext == ""){
        document.getElementById("project_table").innerHTML = "<br><br>无可选项目";
        $("#table_head").hide();
    }else{
        document.getElementById("project_table").innerHTML = showtext;
        $("#table_head").show();
    }
}
//待编制项目展示
function userNew() {
    if(newtext == ""){
        document.getElementById("project_table").innerHTML = "<br><br>无可选项目";
        $("#table_head").hide();
    }else{
        document.getElementById("project_table").innerHTML = newtext;
        $("#table_head").show();
    }}
//待校对项目展示
function userProofread() {
    if(proofreadtext == ""){
        document.getElementById("project_table").innerHTML = "<br><br>无可选项目";
        $("#table_head").hide();
    }else{
        document.getElementById("project_table").innerHTML = proofreadtext;
        $("#table_head").show();
    }}
//待审核项目展示
function userAudit() {
    if(audittext == ""){
        document.getElementById("project_table").innerHTML = "<br><br>无可选项目";
        $("#table_head").hide();
    }else{
        document.getElementById("project_table").innerHTML = audittext;
        $("#table_head").show();
    }}
//待批准项目展示
function userAuthorize() {
    if(authorizetext == ""){
        document.getElementById("project_table").innerHTML = "<br><br>无可选项目";
        $("#table_head").hide();
    }else{
        document.getElementById("project_table").innerHTML = authorizetext;
        $("#table_head").show();
    }}
//待修改项目展示
function userModify() {
    if(modifytext == ""){
        document.getElementById("project_table").innerHTML = "<br><br>无可选项目";
        $("#table_head").hide();
    }else{
        document.getElementById("project_table").innerHTML = modifytext;
        $("#table_head").show();
    }}


