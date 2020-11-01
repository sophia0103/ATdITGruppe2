package controller;

import java.awt.Window;

import javax.swing.JFrame;

import view.RegisterView;
import view.View;

public class OpenRegisterViewController implements Controller{
	private View view;
	
	@Override
	public void setView(View view) {
		this.view = view;
		
	}

	@Override
	public void startProcess(View view) {
		this.setView(view);
		((JFrame) view).dispose();
		RegisterView registerView = new RegisterView();	
	}

}
