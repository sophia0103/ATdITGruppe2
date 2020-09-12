package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.JobScreenController;
import gemuese4you.Screen;
import gemuese4you.Util;
import model.Job;

public class JobScreenView extends Screen {
	
	ArrayList<Job> jobs = new ArrayList();
	private static Connection connection;
	private JPanel jobScreenContent, panelEast, panelTitlebar;
	private JButton buttonAddOffer, buttonRefresh;
	private JobScreenController jobScreenController;
	
	public JobScreenView() {
		this.setBackground(Util.orange);
		panelTitlebar = getTitleBar("Job");
		
		jobScreenController = new JobScreenController(this);
		
		buttonAddOffer = Util.getCustomButton("add");
		buttonAddOffer.addActionListener(jobScreenController.getAddListener());

		buttonRefresh = Util.getCustomButton("refresh");
		buttonRefresh.addActionListener(jobScreenController.getRefreshListener());
		
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
		

		jobScreenController.refresh();
	}
	

}
