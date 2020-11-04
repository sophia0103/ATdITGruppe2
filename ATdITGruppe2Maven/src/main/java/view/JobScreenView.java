package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;
import controller.JobScreenController;
import controller.OpenAddJobDialogController;
import gemuese4you.Screen;
import gemuese4you.Starter;
import gemuese4you.Util;
import model.Job;

/**
 * @author Luis
 * Represents the UI of the job screen.
 */
public class JobScreenView extends Screen implements View{
	
	ArrayList<Job> jobs = new ArrayList();
	private static Connection connection;
	private JPanel jobScreenContent, panelEast, panelTitlebar;
	private JButton buttonAddOffer, buttonRefresh;
	private Controller jobScreenController;
	private Controller openAddJobDialogController;
	
	public JobScreenView() {
		this.setBackground(Util.orange);
		panelTitlebar = getTitleBar(Starter.content.getString("jobScreenTitle"));
		
		jobScreenController = new JobScreenController();
		openAddJobDialogController = new OpenAddJobDialogController();
		
		buttonAddOffer = Util.getCustomButton("add");
		buttonAddOffer.addActionListener(e -> openAddJobDialogController.startProcess(this));

		buttonRefresh = Util.getCustomButton("refresh");
		buttonRefresh.addActionListener(e -> jobScreenController.startProcess(this));
		
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
		

		jobScreenController.startProcess(this);
	}
	

}
