package controller;

import view.AddJobDialogView;
import view.View;

public class OpenAddJobDialogController implements Controller{

	@Override
	public void startProcess(View view) {
		new AddJobDialogView();
	}

}
