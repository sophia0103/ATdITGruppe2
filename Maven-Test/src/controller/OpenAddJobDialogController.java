package controller;

import view.AddJobDialogView;
import view.View;

public class OpenAddJobDialogController implements Controller{
	private View view;
	
	@Override
	public void setView(View view) {
		this.view = view;	
	}

	@Override
	public void startProcess(View view) {
		new AddJobDialogView();
	}

}
