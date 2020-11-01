package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.CancelController;
import controller.Controller;
import controller.GetJobDialogController;
import controller.GetOfferDialogController;
import gemuese4you.Screen;
import gemuese4you.Util;
import model.Job;
import model.Offer;


/**
 * @author Luis
 * Represents the UI of the dialog which opens when the user wants to get the details of a job.
 */

public class GetJobDialogView extends JFrame implements View{
	private Container container;
	private JButton buttonApply, buttonCancel;
	private JLabel labelTitle, labelDuration, labelExpirationDate, labelSalary, labelDistance, labelCreator, labelEmploymentType, labelDescription;
	private JPanel panelDescription, panelFrame, panelButton;
	private Controller getJobDialogController;
	private Controller cancelController;
	
	public GetJobDialogView(Job job) {
		getJobDialogController = new GetJobDialogController();
		cancelController = new CancelController();
		
		container = getContentPane();
		this.setBackground(Util.orange);
		
		buttonApply = new JButton("Apply");
		buttonApply.addActionListener(e -> getJobDialogController.startProcess(this));
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(e -> cancelController.startProcess(this));
		
		panelButton = new JPanel(new GridLayout(1,2));
		panelButton.add(buttonApply);
		panelButton.add(buttonCancel);
		
		panelDescription = new JPanel(new GridLayout(8, 1));
		panelDescription.setBackground(Util.orange);
		
		labelTitle = new JLabel(job.getTitle() + " - Job Offer by "  + job.getCreator());
		labelTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		labelExpirationDate = new JLabel("Offer expires on: " + job.getExpDate());
		labelDuration = new JLabel("duration: " + job.getDuration());
		labelDistance = new JLabel("Distance: " + job.getDistance() + " meters");
		labelEmploymentType = new JLabel("EmploymentType: " + job.getEmploymentType());
		labelSalary = new JLabel("Salaray: " + job.getSalary() + "€/h");
		labelDescription = new JLabel("Description: " + job.getSalary() + "€/h");
		
		panelFrame = new JPanel(new BorderLayout());

		
		panelDescription.add(labelTitle);
		panelDescription.add(labelExpirationDate);
		panelDescription.add(labelDuration);
		panelDescription.add(labelDistance);
		panelDescription.add(labelEmploymentType);
		panelDescription.add(labelSalary);
		panelDescription.add(labelDescription);
		
		panelFrame.add(panelDescription, BorderLayout.CENTER);
		panelFrame.add(panelButton, BorderLayout.SOUTH);
		panelFrame.setBackground(Util.orange);
		
		container.add(panelFrame);
		
		this.setVisible(true);
		this.setTitle("Details");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
	}
}
