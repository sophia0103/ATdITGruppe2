package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.ChangePasswordDialogView;
import view.ProfileScreenView;
import view.View;


/**
 * @author I518189
 * Represents the logic behind the profile Screen UI.
 */
public class ProfileScreenController implements Controller{
	private View view;

	public ProfileScreenController() {
		
	}

	
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
