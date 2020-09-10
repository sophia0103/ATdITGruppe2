package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

public class JobScreen extends Screen implements ActionListener{

	ArrayList<Job> jobs = new ArrayList();
	private static Connection connection;
	private JPanel jobScreenContent, panelEast, panelTitlebar;
	private JButton buttonAddOffer, buttonRefresh;
	
	public JobScreen() {
		this.setBackground(orange);
		panelTitlebar = getTitleBar("Job");
		buttonAddOffer = getAddOfferButton();
		buttonRefresh = getRefreshButton();
		
		// AddJobButton only exists for farmers (scope)
		if (Util.isUserFarmer()) {
			panelEast = new JPanel(new GridLayout(1, 2));
			panelEast.add(buttonRefresh);
			panelEast.add(buttonAddOffer);
		} else {
			panelEast = new JPanel();
			panelEast.add(buttonRefresh);
		}
		this.setLayout(new BorderLayout());
		panelTitlebar.add(panelEast, BorderLayout.EAST);
		this.add(panelTitlebar, BorderLayout.NORTH);
		
		
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		showCurrentJobs();
		
		JScrollPane scrollable = new JScrollPane(jobScreenContent, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollable.setBorder(BorderFactory.createEmptyBorder());
		scrollable.setSize(450, 450);
		this.add(scrollable, BorderLayout.CENTER);
	}

	private JButton createJobButton(Job job) {
		JButton button = new JButton();
		button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		JLabel title = new JLabel(job.getTitle());
		title.setFont(new Font("Arail", Font.BOLD, 18));
		button.add(title);
		button.add(new JLabel("Ersteller: " + job.getCreator()));
		button.add(new JLabel("Dauer: " + job.getDuration()));
		button.add(new JLabel("Entfernung: " + job.getDistance()));
		button.add(new JLabel("Bewerberschluss: " + job.getExpDate()));
		button.add(new JLabel("Stundenlohn: " + job.getSalary()));
		button.add(new JLabel("Beschreibung: " + job.getDescription()));
		button.setBackground(new Color(255, 242, 229));
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(400,135));
		button.setMinimumSize(new Dimension(400,135));
		button.setMaximumSize(new Dimension(400,135));
		
//		ActionListener offerListener = new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new GetOfferDialog(offer);
//			}
//		};
//		
		return button;
	}
	
	private JLabel createHeadline(String content) {
		JLabel headline = new JLabel(content);
		headline.setFont(new Font("Arial", Font.BOLD, 16));
		return headline;
	}
	
	// display existing jobs
		public void showCurrentJobs() {
			// panel has to be removed and added again in order to fetch new jobs which
			// might have been created in the process
			if (jobScreenContent != null) {
				this.remove(jobScreenContent);
				jobScreenContent = null;
			}
			readOffers();
			JPanel jobScreenContent = new JPanel();
			jobScreenContent.setLayout(new BoxLayout(jobScreenContent, BoxLayout.Y_AXIS));
			jobScreenContent.setBackground(orange);
			
			JLabel headlineJobs = createHeadline("Neueste Stellenangebote in deiner Nähe:");
			jobScreenContent.add(headlineJobs);
			jobScreenContent.setBorder(BorderFactory.createEmptyBorder(10, 30, 5, 30));
			
			for (int i = 0; i < jobs.size(); i++) {
				jobScreenContent.add(createJobButton(jobs.get(i)));
				jobScreenContent.add(Box.createVerticalStrut(10));
			}
			this.jobScreenContent = jobScreenContent;
		}

		// reads the current offers in the database and adds them to the offer list
		public void readOffers() {
			jobs.clear();
			try {
				String today = Util.returnDateAsString();
				Statement statement = connection.createStatement();
				String queryOffers = "SELECT * FROM job WHERE exp_date > '" + today + "' ORDER BY distance";
				ResultSet resultJobs = statement.executeQuery(queryOffers);
				while (!resultJobs.isAfterLast() && Util.checkDatabaseEntries("title", "job") && resultJobs!=null) {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// returns a button to add an offer
		public JButton getAddOfferButton() {
			JButton buttonAdd = Util.getCustomButton("add");
			// Function which is called when the add offer button is pressed
			ActionListener addListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new AddOfferDialog();
				}
			};
			buttonAdd.addActionListener(addListener);
			return buttonAdd;
		}
		
		// returns a button to refresh the shopScreen
		public JButton getRefreshButton() {
			JButton buttonRefresh = Util.getCustomButton("refresh");
//			ActionListener refreshListener = new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					showCurrentOffers();
//				}
//			};
			buttonRefresh.addActionListener(this);
			return buttonRefresh;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			showCurrentJobs();
			SwingUtilities.updateComponentTreeUI(this);
			
		}
}
