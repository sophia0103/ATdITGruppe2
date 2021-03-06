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
import gemuese4you.Starter;
import gemuese4you.Util;
import model.Job;

/**
 * @author I518189 Represents the UI of the dialog which opens when the user
 *         wants to add an offer.
 */
public class AddJobDialogView extends JFrame implements DataView {

	private JTextField textFieldTitle;
	private JTextField textFieldDuration; 
	private JTextField textFieldDistance;
	private JTextField textFieldDate;
	private JTextField textFieldEmploymentType;
	private JTextField textFieldSalary;
	private JTextField textFieldDescription;
	private JLabel labelTitle;
	private JLabel labelDuration;
	private JLabel labelDistance;
	private JLabel labelDate;
	private JLabel labelEmploymentType;
	private JLabel labelSalary;
	private JLabel labelDescription;
	private JLabel labelDateInfo;
	private JLabel labelDistanceMeters;
	private JLabel labelSalaryEuroPerHour;
	private JLabel labelDurationMonths;
	private JPanel panelInput;
	private JPanel panelDuration;
	private JPanel panelDistance;
	private JPanel panelDate;
	private JPanel panelSalary;
	private JPanel panelButton;
	private JButton buttonSave, buttonCancel;
	private Controller controller;
	private Controller cancelController;
	static Connection connection;
	
	public AddJobDialogView() {

		//Container and Layout
		Container container = getContentPane();
		container.setBackground(Util.orange);
		container.setLayout(new BorderLayout());
		panelInput = new JPanel(new GridLayout(14, 1));

		//Textfields
		textFieldTitle = new JTextField();
		textFieldDuration = new JTextField();
		textFieldDistance = new JTextField();
		textFieldDate = new JTextField();
		textFieldEmploymentType = new JTextField();
		textFieldSalary = new JTextField();
		textFieldDescription = new JTextField();
		
		labelTitle = new JLabel(Starter.content.getString("title"));

		labelDistance = new JLabel(Starter.content.getString("distance"));

		panelDistance = new JPanel(new BorderLayout());
		labelDistanceMeters = new JLabel(Starter.content.getString("meters"));
		panelDistance.add(textFieldDistance, BorderLayout.CENTER);
		panelDistance.add(labelDistanceMeters, BorderLayout.EAST);
		panelDistance.setBackground(Util.orange);

		labelDuration = new JLabel(Starter.content.getString("duration"));
		
		labelDurationMonths = new JLabel(Starter.content.getString("months"));
		panelDuration = new JPanel(new BorderLayout());
		panelDuration.add(textFieldDuration, BorderLayout.CENTER);
		panelDuration.add(labelDurationMonths, BorderLayout.EAST);
		panelDuration.setBackground(Util.orange);

		labelSalary = new JLabel(Starter.content.getString("wage"));
		
		panelSalary = new JPanel(new BorderLayout());
		labelSalaryEuroPerHour = new JLabel(Starter.content.getString("euro"));
		panelSalary.add(textFieldSalary, BorderLayout.CENTER);
		panelSalary.add(labelSalaryEuroPerHour, BorderLayout.EAST);
		panelSalary.setBackground(Util.orange);

		labelDate = new JLabel(Starter.content.getString("applicationDeadline"));
		
		panelDate = new JPanel(new GridLayout(1, 5));
		panelDate.setBackground(Util.orange);		
		labelDateInfo = new JLabel();
		labelDateInfo.setIcon(new ImageIcon("src/main/resources/info.png"));
		labelDateInfo.setToolTipText(Starter.content.getString("tooltipJob"));
		panelDate.add(labelDate);
		panelDate.add(labelDateInfo);
		panelDate.add(getEmptyLabel());
		panelDate.add(getEmptyLabel());
		panelDate.add(getEmptyLabel());

		labelEmploymentType = new JLabel(Starter.content.getString("employmentType"));
		
		labelDescription = new JLabel(Starter.content.getString("description"));

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

		buttonCancel = new JButton(Starter.content.getString("cancel"));
		cancelController = new CancelController();
		buttonCancel.addActionListener(e -> cancelController.startProcess(this));

		buttonSave = new JButton(Starter.content.getString("save"));
		controller = new AddJobDialogController();
		buttonSave.addActionListener(e -> controller.startProcess(this));

		panelButton = new JPanel(new GridLayout(1, 2));
		panelButton.add(buttonSave);
		panelButton.add(buttonCancel);

		container.add(panelInput, BorderLayout.CENTER);
		container.add(panelButton, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setTitle(Starter.content.getString("titleAddJob"));
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
		String[] input = new String[7];
		input[0] = textFieldTitle.getText();
		input[1] = textFieldDistance.getText();
		input[2] = textFieldDuration.getText();
		input[3] = textFieldSalary.getText();
		input[4] = textFieldDate.getText();
		input[5] = textFieldEmploymentType.getText();		
		input[6] = textFieldDescription.getText();
		return input;

	}

}
