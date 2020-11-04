package controller;

import javax.swing.JFrame;
import view.View;

/**
 * @author Luis Represents the logic of when a view is closed by the user.
 */
public class CancelController implements Controller{
	private View view;
	

	@Override
	public void startProcess(View view) {
		this.view = view;
		((JFrame) view).dispose();		
	}

}
