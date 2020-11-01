package controller;

import java.awt.Window;

import javax.swing.JFrame;

import view.RegisterView;
import view.View;

public class OpenRegisterViewController implements Controller{
	private View view;

	@Override
	public void startProcess(View view) {
		this.view = view;
		((JFrame) view).dispose();
		RegisterView registerView = new RegisterView();	
	}

}
