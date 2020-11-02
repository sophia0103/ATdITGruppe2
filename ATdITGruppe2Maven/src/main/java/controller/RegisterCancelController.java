package controller;

import javax.swing.JFrame;

import view.LoginScreenView;
import view.View;

public class RegisterCancelController implements Controller {
	private View view;

	@Override
	public void startProcess(View view) {
		this.view = view;
		((JFrame) view).dispose();
		LoginScreenView log = new LoginScreenView();
	}

}
