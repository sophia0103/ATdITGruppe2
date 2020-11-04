package controller;

import java.awt.Window;

import javax.swing.JFrame;

import view.RegisterView;
import view.View;

/**
 * @author I518189 Represents the logic of when the RegisterView is to be opened.
 */
public class OpenRegisterViewController implements Controller{
	private View view;

	@Override
	public void startProcess(View view) {
		this.view = view;
		((JFrame) view).dispose();
		RegisterView registerView = new RegisterView();	
	}

}
