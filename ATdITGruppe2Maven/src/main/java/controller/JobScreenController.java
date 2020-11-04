package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import gemuese4you.Starter;
import gemuese4you.Util;
import model.Job;
import view.AddJobDialogView;
import view.AddOfferDialogView;
import view.GetJobDialogView;
import view.GetOfferDialogView;
import view.View;

/**
 * @author Luis
 *Represents the logic behind the job Screen UI.
 */
public class JobScreenController implements Controller{
	private View view;
	private ArrayList<Job> jobs;
	private JPanel jobScreenContent;
	
	public JobScreenController() {
		jobScreenContent = new JPanel();
		jobs = new ArrayList<Job>();
	}
	
	@Override
	public void startProcess(View view) {
		this.view = view;
		showCurrentJobs();
		SwingUtilities.updateComponentTreeUI((JPanel)view);		
	}
	
	/**
	 * Display existing offers
	 * 
	 */
	public void showCurrentJobs() {
		// panel has to be removed and added again in order to fetch new jobs which
		// might have been created in the process
		if (jobScreenContent != null) {
			((JPanel)view).remove(jobScreenContent);
			jobScreenContent = null;
		}
		
		try {
			readJobs();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, Starter.content.getString("sqlStatementError"),
					"Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		JPanel jobScreenContent = new JPanel();
		jobScreenContent.setLayout(new BoxLayout(jobScreenContent, BoxLayout.Y_AXIS));
		jobScreenContent.setBackground(Util.orange);
		
		JLabel headlineJobs = createHeadline(Starter.content.getString("jobScreenHeadline"));
		jobScreenContent.add(headlineJobs);
		jobScreenContent.setBorder(BorderFactory.createEmptyBorder(10, 30, 5, 30));
		
		for (int i = 0; i < jobs.size(); i++) {
			jobScreenContent.add(createJobButton(jobs.get(i)));
			jobScreenContent.add(Box.createVerticalStrut(10));
		}
		JScrollPane scrollable = new JScrollPane(jobScreenContent, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollable.setBorder(BorderFactory.createEmptyBorder());
		scrollable.setSize(450, 450);
		((JPanel)view).add(scrollable, BorderLayout.CENTER);
		
	}

	/**
	 * Reads the current job offers from the job database table.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void readJobs() throws SQLException{
		jobs.clear();

		String today = Util.returnDateAsString();
		Statement statement = Util.getConnection().createStatement();
		String queryOffers = "SELECT * FROM jobs WHERE exp_date > '" + today + "' ORDER BY distance";
		ResultSet resultJobs = statement.executeQuery(queryOffers);
		while (!resultJobs.isAfterLast() && Util.checkDatabaseEntries("title", "jobs") && resultJobs!=null) {
			if (resultJobs.next()) {
				String title = resultJobs.getString(1);
				int duration = resultJobs.getInt(2);
				int distance = resultJobs.getInt(3);
				String expDate = resultJobs.getString(4);
				String creator = resultJobs.getString(5);
				String employmentType = resultJobs.getString(6);
				double salary = resultJobs.getDouble(7);
				String description = resultJobs.getString(8);
				jobs.add(new Job(title, creator, duration, distance, expDate, employmentType, salary, description));
			}
		}
	}
	
	/**
	 * Creates a button which shows the job details.
	 * @param job The job model that a button is to be created for.
	 * @return Returns a JButton with the details of the job offer.
	 */
	private JButton createJobButton(Job job) {
		JButton button = new JButton();
		button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		JLabel title = new JLabel(job.getTitle());
		title.setFont(new Font("Arail", Font.BOLD, 18));
		button.add(title);
		button.add(new JLabel(Starter.content.getString("creator") + job.getCreator()));
		button.add(new JLabel(Starter.content.getString("duration") + job.getDuration()));
		button.add(new JLabel(Starter.content.getString("distance") + job.getDistance()));
		button.add(new JLabel(Starter.content.getString("applicationDeadline") + job.getExpDate()));
		button.add(new JLabel(Starter.content.getString("wage") + job.getSalary()));
		button.add(new JLabel(Starter.content.getString("description") + job.getDescription()));
		button.setBackground(new Color(255, 242, 229));
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(400,135));
		button.setMinimumSize(new Dimension(400,135));
		button.setMaximumSize(new Dimension(400,135));
		
		ActionListener offerListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GetJobDialogView(job);
			}
		};
		button.addActionListener(offerListener);
		return button;
	}
	
	/**
	 * Creates a specifically designed headline.
	 * @param content Content which is displayed in the headline.
	 * @return Returns a JLabel with the headline.
	 */
	private JLabel createHeadline(String content) {
		JLabel headline = new JLabel(content);
		headline.setFont(new Font("Arial", Font.BOLD, 16));
		return headline;
	}


}
