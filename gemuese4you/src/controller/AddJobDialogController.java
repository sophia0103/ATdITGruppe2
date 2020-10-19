package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import gemuese4you.Util;
import model.Job;
import view.AddJobDialogView;
import view.DataView;
import view.LoginScreenView;
import view.View;

/**
 * @author Luis
 * Represents the logic of a dialog which opens when the user wants to create a job offer
 */
public class AddJobDialogController implements DataController{
	private Connection connection;
	private String title;
	private String creator;
	private int distance;
	private int duration;
	private String exp_date;
	private String employmentType;
	private double salary;
	private String description;
	private View view;
	
	public AddJobDialogController() {
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void startProcess(View view) {
		
		setView(view);
		
		Job data = ((DataView) view).getData();
		title = data.getTitle();
		creator = data.getCreator();
		duration = data.getDuration();
		distance = data.getDistance();
		exp_date = data.getExpDate();
		employmentType = data.getEmplyomentType();
		salary = data.getSalary();
		description = data.getDescription();
		
		if (checkInputValidity()) {
			try {
				addJob();
				JOptionPane.showMessageDialog(null, "Job offer was successfully created! :)");
				((AddJobDialogView)view).dispose();
			} catch (SQLException sqlException) {
				// Can�t check for wrong data type in inputIsValid method
				JOptionPane.showMessageDialog(null, "Check for wrong data type or if the job offer already exists", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**Inserts a new job into the job database table.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void addJob() throws SQLException {
		Statement statementAddOffer = connection.createStatement();
		// Auto increment in SQL doesn´t work properly, so we do it manually
		ShopScreenController.lastOfferID++;

		String queryAddOffer = "INSERT INTO jobs VALUES ('" + title + "'," + duration + "," + distance + ",'" + exp_date +
				"','" + creator + "', '" + employmentType + "'," + salary + ",'"
				+ description + "');";
		statementAddOffer.execute(queryAddOffer);
	}
	
	
	@Override
	public void setView(View view) {
		this.view = view;
	}


	/** Checks if the input values of the input fields are valid
	 * @return Returns true if the input is valid, otherwise false.
	 */
	
	@Override
	public boolean checkInputValidity() {
		if (description.equals("") || title.equals("")
				|| salary == 0 || duration == 0 || employmentType.equals("")) {
			JOptionPane.showMessageDialog(null, "Input mustn´t be empty", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// check if the expiration date is before the current date
		if (Util.returnStringAsDate(exp_date).compareTo(Util.returnDate()) < 0) {
			JOptionPane.showMessageDialog(null, "Expiration date has to be later than current date.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			return true;
		}
	}

}
