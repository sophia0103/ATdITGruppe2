package controller;

import javax.swing.JFrame;

import view.LoginScreenView;
import view.View;

/**
 * @author I518189 Represents the logic behind when the register view is to be closed/disposed.
 */
public class RegisterCancelController implements Controller {
	private View view;

	@Override
	public void startProcess(View view) {
		this.view = view;
		((JFrame) view).dispose();
		LoginScreenView log = new LoginScreenView();
	}

}
