package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AddJobDialogController;
import controller.CancelController;
import controller.Controller;
import gemuese4you.Util;
import model.Job;

/**
 * @author I518189 Represents the UI of the dialog which opens when the user
 *         wants to add an offer.
 */
public class AddJobDialogView extends JFrame implements DataView {

	private JTextField textFieldTitle, textFieldDuration, textFieldDistance, textFieldDate, textFieldEmploymentType,
			textFieldSalary, textFieldDescription;
	private JLabel labelTitle, labelDuration, labelDistance, labelDate, labelEmploymentType, labelSalary,
			labelDescription, labelDateInfo, labelDistanceMeters, labelSalaryEuroPerHour, labelDurationMonths;
	private JPanel panelInput, panelDuration, panelDistance, panelDate, panelSalary, panelButton;
	private JButton buttonSave, buttonCancel;
	private Controller controller;
	private Controller cancelController;
	static Connection connection;
	
	public AddJobDialogView() {

		Container container = getContentPane();
		container.setBackground(Util.orange);
		container.setLayout(new BorderLayout());
		panelInput = new JPanel(new GridLayout(14, 1));

		textFieldTitle = new JTextField();
		textFieldDuration = new JTextField();
		textFieldDistance = new JTextField();
		textFieldDate = new JTextField();
		textFieldEmploymentType = new JTextField();
		textFieldSalary = new JTextField();
		textFieldDescription = new JTextField();

		labelTitle = new JLabel("Title: ");

		labelDistance = new JLabel("Distance: ");
		panelDistance = new JPanel(new BorderLayout());
		labelDistanceMeters = new JLabel(" meters   ");
		panelDistance.add(textFieldDistance, BorderLayout.CENTER);
		panelDistance.add(labelDistanceMeters, BorderLayout.EAST);
		panelDistance.setBackground(Util.orange);

		labelDuration = new JLabel("Duration:");
		labelDurationMonths = new JLabel("months   ");
		panelDuration = new JPanel(new BorderLayout());
		panelDuration.add(textFieldDuration, BorderLayout.CENTER);
		panelDuration.add(labelDurationMonths, BorderLayout.EAST);
		panelDuration.setBackground(Util.orange);

		labelSalary = new JLabel("Salary: ");
		panelSalary = new JPanel(new BorderLayout());
		labelSalaryEuroPerHour = new JLabel(" €    ");
		panelSalary.add(textFieldSalary, BorderLayout.CENTER);
		panelSalary.add(labelSalaryEuroPerHour, BorderLayout.EAST);
		panelSalary.setBackground(Util.orange);

		panelDate = new JPanel(new GridLayout(1, 5));
		panelDate.setBackground(Util.orange);
		labelDate = new JLabel("Expiration Date: ");
		labelDateInfo = new JLabel();
		labelDateInfo.setIcon(new ImageIcon("images/info.png"));
		labelDateInfo
				.setToolTipText("This is the date when your offer expires. A valid input looks as follows: 2020-12-10");
		panelDate.add(labelDate);
		panelDate.add(labelDateInfo);
		panelDate.add(getEmptyLabel());
		panelDate.add(getEmptyLabel());
		panelDate.add(getEmptyLabel());

		labelEmploymentType = new JLabel("Employment Type: ");

		labelDescription = new JLabel("Description: ");

		panelInput.add(labelTitle);
		panelInput.add(textFieldTitle);
		panelInput.add(labelDistance);
		panelInput.add(panelDistance);
		panelInput.add(labelDuration);
		panelInput.add(panelDuration);
		panelInput.add(labelSalary);
		panelInput.add(panelSalary);
		panelInput.add(panelDate);
		panelInput.add(textFieldDate);
		panelInput.add(labelEmploymentType);
		panelInput.add(textFieldEmploymentType);
		panelInput.add(labelDescription);
		panelInput.add(textFieldDescription);

		panelInput.setBackground(Util.orange);

		buttonCancel = new JButton("Cancel");
		cancelController = new CancelController();
		buttonCancel.addActionListener(e -> cancelController.startProcess(this));

		buttonSave = new JButton("Save");
		controller = new AddJobDialogController();
		buttonSave.addActionListener(e -> controller.startProcess(this));

		panelButton = new JPanel(new GridLayout(1, 2));
		panelButton.add(buttonSave);
		panelButton.add(buttonCancel);

		container.add(panelInput, BorderLayout.CENTER);
		container.add(panelButton, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setTitle("Create a job offer");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);

	}

	/**
	 * Acts as a filler to work around the Java Swing Layouts.
	 * 
	 * @return Returns an empty JLabel.
	 */
	public JLabel getEmptyLabel() {
		return new JLabel("");
	}

	@Override
	public String[] getData() {
		String[] input = new String[8];
		input[0] = textFieldTitle.getText();
		input[1] = LoginScreenView.userID;
		input[2] = textFieldDuration.getText();
		input[3] = textFieldDistance.getText();
		input[4] = textFieldDate.getText();
		input[5] = textFieldEmploymentType.getText();
		input[6] = textFieldSalary.getText();
		input[7] = textFieldDescription.getText();
		return input;

	}

}
