package controller;

import javax.swing.JOptionPane;

import view.View;

/**
 * @author I518189 Represents the logic behind the profile Screen UI.
 */
public class ProfileScreenController implements Controller {
	private View view;

	@Override
	public void startProcess(View view) {
		setView(view);
		JOptionPane.showMessageDialog(null,
				"Are you sure you want to delete this offer? (Hasn´t been implemented yet).");
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

}
