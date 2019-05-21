//用户登录
function userLogin(){
//	获取用户名和密码
	var uName = $("#lName").val();
	var uPassword = $("#lPassword").val();
	if(uName=="" || uPassword==""){
		alert("用户名和密码不能为空");
	}
	else {
//		console.log(uName,uPassword);
		$.ajax({
			url:SMarketPath + "/user/userLogin?callback=?&myparam=test100",
			type:"post",
			//设置携带cookie信息，使session两次使用id相同
			xhrFields: {  
				withCredentials: true  
			},  
			data:{
				"uName":uName,
				"uPassword":uPassword
			},
			dataType:"json",
			success:function (data) {
				console.log(data[0]);
				console.log(data[0].result);
				if(data[0].result == "true"){
					alert("登录成功");
					window.location.href = "index.html";
				} else{
					alert("用户名或密码错误");
				}
			},
			error:function(res){
//				console.log(res.status);
				alert("网络错误,登录超时!");
				var msg = "网络错误：" + JSON.stringify(res);
				console.log(msg);
			}
		});
	}
}

//将form数据转化为json字符串
$.fn.changeJson = function() {
	var j = {};
	var f = this.serializeArray();//{[key1 value1],[key2 value2]}
	$.each(f,function() {
		if(j[this.name]) {
			if(!j[this.name].push) {
				j[this.name] = [j[this.name]];
			}
			j[this.name].push(this.value || '');
		} else {
			j[this.name] = this.value || '';
		}
	});
	return j;
};
function register() { 
	//将form表单数据转换为json格式
	var formData = $("#registerForm").changeJson();
//	console.log("传出数据为:" + JSON.stringify(formData));
	var nameLen = $("#rName").val().length;
	var pwdLen = $("#rPassword").val().length;
	var phoneLen = $("#phone").val().length;
	//用户名检测
	if(nameLen<3 || nameLen>12) {
		alert("用户名不能为空且应该是3-12位");
		return false;
	}
	//密码检测
	if(pwdLen<3 || pwdLen>12) {
		alert("密码不能为空且应该是3-12位");
		return false;
	}
	//手机检测
	if(phoneLen != 11) {
		alert("手机格式错误");
		return false;
	}
	$.ajax({
		type: "post",
		url: SMarketPath + "/user/faddUser",
		async: true,
		dataType: "json",
		data: formData,
		success: function(data) {
			if(data.result=="true") {
				alert("注册成功");
				window.location.href = "#loginPage";
			}else {
				alert("注册失败");
			}
		},
		error: function(err) {
			alert("网络错误,请求失败!");
			console.log("请求失败:" + err.status);
		}
	});
}

//页面元素事件（如：按钮点击）
$(function(){
	//失去焦点时验证用户名是否可用
	$("#rName").blur(function(){
		var inputName = $(this).val();
//		alert(inputName);
		if(inputName == ""){ //判空
			$("#nameCheck").html("用户名不能为空!");
		} else{ //用户名不为空
			$("#nameCheck").html("");
			$.ajax({
				type: "post",
				url: SMarketPath + "/user/fcheckName",
				async: true,
				data: {"inputName" : inputName},
				dataType: "json",
				success: function(data){
					$("#nameCheck").html(data.result);
				},
				error: function(err){
					alert("网络错误,加载失败!");
					console.log("加载错误：" + err.status);
				}
			});
		}
	});
	
	//失去焦点时验证密码是否为空
	$("#rPassword").blur(function(){
		var inputPwd = $(this).val();
		if(inputPwd == ""){ //判空
			$("#pwdCheck").html("密码不能为空!");
		} else{ //用户名不为空
			$("#pwdCheck").html("");
		}
	});
	
	//游客身份进入主页面 点击事件:surfMarket
	$("#loginPage").on("click","#surfMarket",function(){
		window.location.href = "index.html";
	});
	
	//找回密码点击事件:findPassword
	$("#passwordPage").on("click","#findPassword",function(){
		var myName = $("#forgetName").val();
		var myPhone = $("#forgetPhone").val();
		var newPassword = $("#forgetPassword").val();
//		console.log(myName + "," + myPhone + "," + newPassword);
		if(myName=="" || myPhone=="" || newPassword=="") {
			alert("用户名,手机号,新密码均不能为空!");
			return false;
		}
		$.ajax({
			type: "post",
			url: SMarketPath + "/user/fforgetPwd",
			async: true,
			data: {
				"uName" : myName,
				"phone" : myPhone,
				"newPassword" : newPassword
			},
			dataType: "json",
			success: function(data){
				if(data.result == "true") {
					alert("找回密码成功,请记住您的新密码:" + data.newPassword);
					window.location.href = "#loginPage";
				} else if(data.result == "phoneError") {
					alert("手机号核对失败,请查验后再试!");
				} else if(data.result == "nameError"){
					alert("该用户不存在,请核对用户名!");
				} else {
					alert("数据修改失败,请联系管理员...");
				}
			},
			error: function(err){
				alert("网络错误,修改失败...");
				console.log("加载错误:" + err.status);
			}
		});
	});
	//找回密码页-失去焦点时验证用户是否存在
	$("#forgetName").blur(function(){
		var inputName = $(this).val();
		console.log(inputName);
		if(inputName == ""){ //判空
			$("#pwdNameCheck").html("用户名不能为空!");
		} else{ //用户名不为空
			$("#pwdNameCheck").html("");
			$.ajax({
				type: "post",
				url: SMarketPath + "/user/fpwdCheckName",
				async: true,
				data: {"inputName" : inputName},
				dataType: "json",
				success: function(data){
					$("#pwdNameCheck").html(data.result);
				},
				error: function(err){
					console.log("加载错误：" + err.status);
				}
			});
		}
	});
})
