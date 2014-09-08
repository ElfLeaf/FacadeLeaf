function loginPageCtrl($scope) {
	// 验证码url
	$scope.captchaUrl = "/static/getVerifyCode?t=" + Math.random();
	
	//刷新验证码
	$scope.refreshCaptcha = function() {
		$scope.captchaUrl = "/static/getVerifyCode?t=" + Math.random();
	};
}

