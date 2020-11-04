package controller;

import view.AddJobDialogView;
import view.View;

/**
 * @author Luis Represents the logic of when the AddJobDialog is to be opened.
 */
public class OpenAddJobDialogController implements Controller{

	@Override
	public void startProcess(View view) {
		new AddJobDialogView();
	}

}
