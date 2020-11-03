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
import controller.ApplyToJobController;
import controller.BuyOfferController;
import gemuese4you.Screen;
import gemuese4you.Starter;
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
		getJobDialogController = new ApplyToJobController();
		cancelController = new CancelController();
		
		container = getContentPane();
		this.setBackground(Util.orange);
		
		buttonApply = new JButton(Starter.content.getString("apply"));
		buttonApply.addActionListener(e -> getJobDialogController.startProcess(this));
		
		buttonCancel = new JButton(Starter.content.getString("cancel"));
		buttonCancel.addActionListener(e -> cancelController.startProcess(this));
		
		panelButton = new JPanel(new GridLayout(1,2));
		panelButton.add(buttonApply);
		panelButton.add(buttonCancel);
		
		panelDescription = new JPanel(new GridLayout(8, 1));
		panelDescription.setBackground(Util.orange);
		
		labelTitle = new JLabel(job.getTitle() + Starter.content.getString("jobOfferBy")  + job.getCreator());
		labelTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		labelExpirationDate = new JLabel(Starter.content.getString("offerExpires") + job.getExpDate());
		labelDuration = new JLabel(Starter.content.getString("duration") + job.getDuration());
		labelDistance = new JLabel(Starter.content.getString("distance") + job.getDistance() + " meters");
		labelEmploymentType = new JLabel(Starter.content.getString("employmentType") + job.getEmploymentType());
		labelSalary = new JLabel(Starter.content.getString("wage") + job.getSalary() + Starter.content.getString("euro"));
		labelDescription = new JLabel(Starter.content.getString("description") + job.getDescription());
		
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
		this.setTitle(Starter.content.getString("getDialogTitle"));
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
	}
}
