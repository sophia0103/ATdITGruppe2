package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import gemuese4you.Starter;
import gemuese4you.Util;
import gemuese4you.Validator;
import model.Job;
import model.Offer;
import view.AddJobDialogView;
import view.DataView;
import view.LoginScreenView;
import view.View;

/**
 * @author Luis
 * Represents the logic of a dialog which opens when the user wants to create a job offer
 */
public class AddJobDialogController implements DataController{
	private View view;
	
	@Override
	public void startProcess(View view) {
		
		this.view = view;
		
		String[] data =  ((DataView) view).getData();
		if (Validator.isValidJob(data)) {
			Job job = createModel(data);
			try {
				addJob(job);
				JOptionPane.showMessageDialog(null, Starter.content.getString("jobCreated"));
				((AddJobDialogView)view).dispose();
			} catch (SQLException sqlException) {
				// Can´t check for wrong data type in inputIsValid method
				JOptionPane.showMessageDialog(null, Starter.content.getString("sqlStatementError"), "Error",
						JOptionPane.ERROR_MESSAGE);
				sqlException.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, Starter.content.getString("unableToCreateJob"), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**Inserts a new job into the job database table.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void addJob(Job job) throws SQLException {
		Statement statementAddOffer = Util.getConnection().createStatement();
		// Auto increment in SQL doesnÂ´t work properly, so we do it manually
		ShopScreenController.lastOfferID++;

		String queryAddOffer = "INSERT INTO jobs VALUES ('" + job.getTitle() + "'," + job.getDuration() + "," + job.getDistance() + ",'" + job.getExpDate() +
				"','" + job.getCreator() + "', '" + job.getEmploymentType() + "'," + job.getSalary() + ",'"
				+ job.getDescription() + "');";
		statementAddOffer.execute(queryAddOffer);
	}


	@Override
	public <T> T createModel(String[] inputArray) {
		String title = inputArray[0];
		String creator = LoginScreenView.userID;
		int distance = Integer.parseInt(inputArray[1]);
		int duration = Integer.parseInt(inputArray[2]);
		double salary = Double.parseDouble(inputArray[3]);
		String exp_date = inputArray[4];
		String employmentType = inputArray[5];
		String description = inputArray[6];
		Job job = new Job(title, creator, distance, duration, exp_date, employmentType, salary, description);
		// Auto increment in SQL doesn´t work properly, so we do it manually
		return (T) job;
	}

}
