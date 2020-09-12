package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gemuese4you.Util;
import model.Offer;
import view.AddJobDialogView;
import view.AddOfferDialogView;
import view.LoginScreenView;

/**
 * @author Luis
 * Represents the logic of a dialog which opens when the user wants to create a job offer
 */
public class AddJobDialogController {
	private AddJobDialogView addJobDialogView;
	private Connection connection;
	private JTextField textFieldTitle, textFieldDuration, textFieldDistance, textFieldDate, textFieldEmploymentType, textFieldSalary, textFieldDescription;

	public AddJobDialogController(AddJobDialogView addJobDialogView) {
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ShopScreenController.lastOfferID = Offer.getLastOfferID();
		this.addJobDialogView = addJobDialogView;
		textFieldTitle = addJobDialogView.getTextFieldTitle();
		textFieldDistance = addJobDialogView.getTextFieldDistance();
		textFieldDuration = addJobDialogView.getTextFieldDuration();
		textFieldEmploymentType = addJobDialogView.getTextFieldEmploymentType();
		textFieldDate = addJobDialogView.getTextFieldDate();
		textFieldSalary = addJobDialogView.getTextFieldSalary();
		textFieldDescription = addJobDialogView.getTextFieldDescription();

	}

	

	/** Checks if the input values of the input fields are valid
	 * @return Returns true if the input is valid, otherwise false.
	 */
	public boolean inputIsValid() {
		if (textFieldDistance.getText().equals("") || textFieldTitle.getText().equals("")
				|| textFieldSalary.getText().equals("") || textFieldDate.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Input mustn´t be empty", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// check if the expiration date is before the current date
		if (Util.returnStringAsDate(textFieldDate.getText()).compareTo(Util.returnDate()) < 0) {
			JOptionPane.showMessageDialog(null, "Expiration date has to be later than current date.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			return true;
		}
	}

	
	/**Inserts a new job into the job database table.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void addJob() throws SQLException {
		Statement statementAddOffer = connection.createStatement();
		// Auto increment in SQL doesn´t work properly, so we do it manually
		ShopScreenController.lastOfferID++;

		String queryAddOffer = "INSERT INTO job VALUES (" + textFieldTitle.getText() + ",'" + textFieldDuration.getText() + ",'" + textFieldDistance.getText() + ",'" + textFieldDate.getText() +
				"'," + LoginScreenView.userID + "'," + textFieldEmploymentType.getText() + ",'" + textFieldSalary.getText() + "',"
				+ textFieldDescription.getText() + ")";
		statementAddOffer.execute(queryAddOffer);
	}


	
	/** Action which is performed if the cancel button is clicked.
	 * @return returns a listener for the cancel button.
	 */
	public ActionListener getCancelListener() {
		ActionListener cancelListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addJobDialogView.dispose();
			}

		};
		return cancelListener;
	}

	/**Action which is performed if the save button is clicked.
	 * @return returns a listener for the safe button.
	 */
	public ActionListener getSaveListener() {
		ActionListener saveListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputIsValid()) {
					try {
						addJob();
						JOptionPane.showMessageDialog(null, "Job offer was successfully created! :)");
						addJobDialogView.dispose();
					} catch (SQLException sqlException) {
						// Can´t check for wrong data type in inputIsValid method
						JOptionPane.showMessageDialog(null, "Check for wrong data type or if the job offer already exists", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}

		};
		return saveListener;
	}

}
