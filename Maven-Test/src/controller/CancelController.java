package controller;

import javax.swing.JFrame;
import view.View;

public class CancelController implements Controller{
	private View view;
	
	@Override
	public void setView(View view) {
		this.view = view;
		
	}

	@Override
	public void startProcess(View view) {
		this.setView(view);
		((JFrame) view).dispose();		
	}

}
