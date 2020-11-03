package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gemuese4you.Starter;
import view.GetJobDialogView;
import view.GetOfferDialogView;
import view.View;

/**
 * @author Luis 
 * 		   Represents the logic of the dialog which opens when the user
 *         clicks a job to get the details.
 */
public class ApplyToJobController implements Controller{
	private View view;

	@Override
	public void startProcess(View view) {
		this.view = view;
		JOptionPane.showMessageDialog(null,
				Starter.content.getString("applyToJob"));
	}

}