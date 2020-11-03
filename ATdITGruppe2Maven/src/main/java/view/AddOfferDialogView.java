package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AddOfferDialogController;
import controller.CancelController;
import controller.Controller;
import gemuese4you.Starter;
import gemuese4you.Util;

/**
 * @author I518189 Represents the UI of the dialog which opens when the user
 *         wants to add an offer.
 */
public class AddOfferDialogView extends JFrame implements DataView {
	public ArrayList<String> productList;
	private JTextField textFieldDistance;
	private JTextField textFieldProducts;
	private JTextField textFieldPrice;
	private JTextField textFieldDate;
	private JLabel labelDistance;
	private JLabel labelProducts;
	private JLabel labelPrice;
	private JLabel labelExpirationDate;
	private JLabel labelProductInfo;
	private JLabel labelDateInfo;
	private JLabel labelDistanceMeters;
	private JLabel labelPriceEuro;
	private JPanel panelInput;
	private JPanel panelProduct;
	private JPanel panelDate;
	private JPanel panelDistance;
	private JPanel panelPrice;
	private JPanel panelButton;
	private JButton buttonSave;
	private JButton buttonCancel;
	private Controller controller;
	private Controller cancelController;
	static Connection connection;

	public AddOfferDialogView() {

		Container container = getContentPane();
		container.setBackground(Util.orange);
		container.setLayout(new BorderLayout());
		panelInput = new JPanel(new GridLayout(8, 1));

		textFieldDistance = new JTextField();
		textFieldProducts = new JTextField();
		textFieldPrice = new JTextField();
		textFieldDate = new JTextField();

		labelDistance = new JLabel(Starter.content.getString("distance"));
		panelDistance = new JPanel(new BorderLayout());
		labelDistanceMeters = new JLabel(Starter.content.getString("meters"));
		panelDistance.add(textFieldDistance, BorderLayout.CENTER);
		panelDistance.add(labelDistanceMeters, BorderLayout.EAST);
		panelDistance.setBackground(Util.orange);

		panelProduct = new JPanel(new GridLayout(1, 6));
		panelProduct.setBackground(Util.orange);
		labelProducts = new JLabel(Starter.content.getString("products"));
		labelProductInfo = new JLabel();
		labelProductInfo.setIcon(new ImageIcon("images/info.png"));
		labelProductInfo.setToolTipText(Starter.content.getString("offerToolTipProduct"));
		panelProduct.add(labelProducts);
		panelProduct.add(labelProductInfo);
		panelProduct.add(getEmptyLabel());
		panelProduct.add(getEmptyLabel());
		panelProduct.add(getEmptyLabel());
		panelProduct.add(getEmptyLabel());

		labelPrice = new JLabel(Starter.content.getString("price"));
		panelPrice = new JPanel(new BorderLayout());
		labelPriceEuro = new JLabel(Starter.content.getString("euro"));
		panelPrice.add(textFieldPrice, BorderLayout.CENTER);
		panelPrice.add(labelPriceEuro, BorderLayout.EAST);
		panelPrice.setBackground(Util.orange);

		panelDate = new JPanel(new GridLayout(1, 5));
		panelDate.setBackground(Util.orange);
		labelExpirationDate = new JLabel(Starter.content.getString("expDate"));
		labelDateInfo = new JLabel();
		labelDateInfo.setIcon(new ImageIcon("images/info.png"));
		labelDateInfo
				.setToolTipText(Starter.content.getString("offerToolTipDate"));
		panelDate.add(labelExpirationDate);
		panelDate.add(labelDateInfo);
		panelDate.add(getEmptyLabel());
		panelDate.add(getEmptyLabel());
		panelDate.add(getEmptyLabel());

		panelInput.add(labelDistance);
		panelInput.add(panelDistance);
		panelInput.add(panelProduct);
		panelInput.add(textFieldProducts);
		panelInput.add(labelPrice);
		panelInput.add(panelPrice);
		panelInput.add(panelDate);
		panelInput.add(textFieldDate);
		panelInput.setBackground(Util.orange);

		buttonCancel = new JButton(Starter.content.getString("cancel"));
		cancelController = new CancelController();
		buttonCancel.addActionListener(e -> cancelController.startProcess(this));

		buttonSave = new JButton(Starter.content.getString("save"));
		controller = new AddOfferDialogController(this);
		buttonSave.addActionListener(e -> this.controller.startProcess(this));

		panelButton = new JPanel(new GridLayout(1, 2));
		panelButton.add(buttonSave);
		panelButton.add(buttonCancel);

		container.add(panelInput, BorderLayout.CENTER);
		container.add(panelButton, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setTitle(Starter.content.getString("createOffer"));
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
		String[] inputArray = new String[4];
		inputArray[0] = textFieldPrice.getText();
		inputArray[1] = textFieldDistance.getText();
		inputArray[2] = textFieldDate.getText();
		inputArray[3] = textFieldProducts.getText();
		return inputArray;
	}

}
