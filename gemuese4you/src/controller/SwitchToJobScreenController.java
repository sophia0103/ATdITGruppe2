package controller;

import gemuese4you.MainFrame;
import view.View;


/**
 * @author Luis
 *Represents the logic behind the profile Screen UI.
 */
public class SwitchToJobScreenController implements Controller {

	@Override
	public void startProcess(View view) {
		MainFrame.tabPane.setSelectedIndex(2);		
	}
}
