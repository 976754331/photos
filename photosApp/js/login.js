function opneNew(url) {
	var loginInfo = {
		loginName: accountInput.value,
		password: pwdInput.value
	};
	simpAjax(Url + "/user/login.do", loginInfo, function(result) {
		if(result.rtn_code == 0) {
			if(result.data.flag == "0") {
				mui.toast(result.data.msg);
				localStorage.setItem('userid', result.data.userId);
				localStorage.setItem('username', result.data.name);
				accountInput.value = "";
				pwdInput.value = ""
			} else {
				mui.openWindow(url);
			}
		} else {
			mui.toast(result.msg);
			accountInput.value = "";
			pwdInput.value = ""
		}
	})

}