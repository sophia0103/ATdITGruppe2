package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.ChangePasswordDialogView;
import view.ProfileScreenView;


/**
 * @author I518189
 *Represents the logic behind the profile Screen UI.
 */
public class ProfileScreenController {
	private ProfileScreenView profileScreenView;

	public ProfileScreenController(ProfileScreenView profileScreenView) {
		this.profileScreenView = profileScreenView;
	}

	/**
	 * Action which is performed when the delete button is clicked.
	 * @return Returns a listener for the delete button.
	 */
	public ActionListener getDeleteListener() {
		ActionListener deleteListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Are you sure you want to delete this offer? (Hasn´t been implemented yet).");
			}
		};
		return deleteListener;
	}

	/**
	 * Action which is performed when the change password button is clicked.
	 * @return Returns a listener for the delete button.
	 */
	public ActionListener getChangePasswordListener() {
		ActionListener changePasswordListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePasswordDialogView();
			}
		};
		return changePasswordListener;
	}

}
