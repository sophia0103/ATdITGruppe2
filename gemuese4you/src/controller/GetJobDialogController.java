package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.GetJobDialogView;
import view.GetOfferDialogView;

/**
 * @author Luis 
 * 		   Represents the logic of the dialog which opens when the user
 *         clicks a job to get the details.
 */
public class GetJobDialogController {
	private GetJobDialogView getJobDialogView;

	public GetJobDialogController(GetJobDialogView getJobDialogView) {
		this.getJobDialogView = getJobDialogView;
	}

	/**
	 * Action which is performed when the user clicks the apply button.
	 * 
	 * @return returns a listener for the buy button.
	 */
	public ActionListener getApplyListener() {
		ActionListener applyListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"By pushing this button you can apply to this job (function hasn´t been implemented yet).");
			}

		};
		return applyListener;
	}

	/**
	 * Action which is performed when the user clicks the cancel button.
	 * 
	 * @return returns a listener for the cancel button.
	 */
	public ActionListener getCancelListener() {
		ActionListener cancelListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getJobDialogView.dispose();

			}
		};
		return cancelListener;
	}

}