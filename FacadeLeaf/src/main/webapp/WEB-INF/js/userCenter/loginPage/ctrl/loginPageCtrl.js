function loginPageCtrl($scope,$http) {
	// 验证码url
	$scope.captchaUrl = "/static/getVerifyCode?t=" + Math.random();
	
	
	/*------------------------方法区--------------------*/
	
	//刷新验证码
	$scope.refreshCaptcha = function() {
		$scope.captchaUrl = "/static/getVerifyCode?t=" + Math.random();
	};
	
	//点击登录
	$scope.doLogin = function() {
		$http.post("/userCenter/doLogin2");
	};
}

