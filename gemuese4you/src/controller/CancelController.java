package controller;

import javax.swing.JFrame;
import view.View;

public class CancelController implements Controller{
	private View view;
	

	@Override
	public void startProcess(View view) {
		this.view = view;
		((JFrame) view).dispose();		
	}

}
