package controller;

import gemuese4you.MainFrame;
import view.View;


/**
 * @author Luis
 *Represents the process of a switch to the JobScreen.
 */
public class SwitchToJobScreenController implements Controller {

	@Override
	public void startProcess(View view) {
		MainFrame.tabPane.setSelectedIndex(2);		
	}
}
