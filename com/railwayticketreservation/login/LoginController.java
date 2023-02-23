package com.railwayticketreservation.login;

import com.railwayticketreservation.dto.User;
import com.railwayticketreservation.login.LoginModel.loginModelControllerCallback;

public class LoginController extends LoginControllerCallback implements loginModelControllerCallback {
	private LoginViewCallBack viewCallBack;
	private LoginModelCallback modelCallback;

	public LoginController(LoginView view) {
		this.viewCallBack = view;
		this.modelCallback = new LoginModel(this);
	}

	@Override
	protected void checkCredential(String name, String password) {
		modelCallback.checkCredentials(name, password);
	}

	@Override
	public void loginSuccess(User user) {
		viewCallBack.loginSucccess(user);
	}

	@Override
	public void loginFailed(String message) {
		viewCallBack.loginFailde(message);
	}

}
