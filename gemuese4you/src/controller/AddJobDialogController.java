package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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
	private Connection connection;
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
		
		String[] data =  ((DataView) view).getData();
		if (Validator.isValidJob(data)) {
			Job job = createModel(data);
			try {
				addJob(job);
				JOptionPane.showMessageDialog(null, "Job offer was successfully created! :)");
				((AddJobDialogView)view).dispose();
			} catch (SQLException sqlException) {
				// Can´t check for wrong data type in inputIsValid method
				JOptionPane.showMessageDialog(null, "Check if your input complies with formatting requirements and if the job offer already exists", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Unable to create job offer - Check for wrong data type", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**Inserts a new job into the job database table.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void addJob(Job job) throws SQLException {
		Statement statementAddOffer = connection.createStatement();
		// Auto increment in SQL doesnÂ´t work properly, so we do it manually
		ShopScreenController.lastOfferID++;

		String queryAddOffer = "INSERT INTO jobs VALUES ('" + job.getTitle() + "'," + job.getDuration() + "," + job.getDistance() + ",'" + job.getExpDate() +
				"','" + job.getCreator() + "', '" + job.getEmploymentType() + "'," + job.getSalary() + ",'"
				+ job.getDescription() + "');";
		statementAddOffer.execute(queryAddOffer);
	}
	
	
	@Override
	public void setView(View view) {
		this.view = view;
	}


	@Override
	public <T> T createModel(String[] inputArray) {
		String title = inputArray[0];
		String creator = inputArray[1];
		int distance = Integer.parseInt(inputArray[2]);
		int duration = Integer.parseInt(inputArray[3]);
		String exp_date = inputArray[4];
		String employmentType = inputArray[5];
		double salary = Double.parseDouble(inputArray[6]);
		String description = inputArray[7];
		Job job = new Job(title, creator, distance, duration, exp_date, employmentType, salary, description);
		// Auto increment in SQL doesn´t work properly, so we do it manually
		return (T) job;
	}

}
