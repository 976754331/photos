function opneNew(url) {
	var loginInfo = {
		loginName: accountInput.value,
		password: pwdInput.value
	};
	simpAjax(Url + "/user/login.do", loginInfo, function(result) {
		if(result.rtn_code == 0) {
			if(result.data.flag == "1") {
				localStorage.clear();				
				localStorage.setItem('userid', result.data.userId);
				localStorage.setItem('username', result.data.name);
				localStorage.setItem('sessionId', ";jsessionid="+result.data.sessionId);
				//console.log(localStorage.getItem("userid"));				
				mui.openWindow(url);
			} else {
				accountInput.value = "";
				pwdInput.value = "";
				mui.toast(result.data.msg);
			}
		} else {
			mui.toast(result.msg);
			accountInput.value = "";
			pwdInput.value = ""
		}
	})

}