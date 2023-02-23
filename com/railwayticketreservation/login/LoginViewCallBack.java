package com.railwayticketreservation.login;

import com.railwayticketreservation.dto.User;

public abstract class LoginViewCallBack {

	protected abstract void loginSucccess(User user);

	protected abstract void loginFailde(String message);

}
