package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import view.ChangePasswordDialogView;
import view.ProfileScreenView;

public class ProfileScreenController {
	ProfileScreenView profileScreenView;

	public ProfileScreenController(ProfileScreenView profileScreenView) {
		this.profileScreenView = profileScreenView;
	}

	// returns a listener for the delete button
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

	// returns a listener for the change password button
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
