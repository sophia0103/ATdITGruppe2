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
import gemuese4you.Util;

public class AddOfferDialogView extends JFrame {
	private JTextField textFieldDistance, textFieldProducts, textFieldPrice, textFieldDate;
	private JLabel labelDistance, labelProducts, labelPrice, labelExpirationDate, labelProductInfo, labelDateInfo,
			labelDistanceMeters, labelPriceEuro;
	private JPanel panelInput, panelProduct, panelDate, panelDistance, panelPrice, panelButton;
	private JButton buttonSave, buttonCancel;
	private String[] productArray;
	public ArrayList<String> productList;
	private AddOfferDialogController addOfferDialogController;
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

		labelDistance = new JLabel("Distance: ");
		panelDistance = new JPanel(new BorderLayout());
		labelDistanceMeters = new JLabel(" meters   ");
		panelDistance.add(textFieldDistance, BorderLayout.CENTER);
		panelDistance.add(labelDistanceMeters, BorderLayout.EAST);
		panelDistance.setBackground(Util.orange);

		panelProduct = new JPanel(new GridLayout(1, 6));
		panelProduct.setBackground(Util.orange);
		labelProducts = new JLabel("Products: ");
		labelProductInfo = new JLabel();
		labelProductInfo.setIcon(new ImageIcon("images/info.png"));
		labelProductInfo.setToolTipText("You can enter values as follows: apple,pear,...");
		panelProduct.add(labelProducts);
		panelProduct.add(labelProductInfo);
		panelProduct.add(getEmptyLabel());
		panelProduct.add(getEmptyLabel());
		panelProduct.add(getEmptyLabel());
		panelProduct.add(getEmptyLabel());

		labelPrice = new JLabel("Price: ");
		panelPrice = new JPanel(new BorderLayout());
		labelPriceEuro = new JLabel(" €    ");
		panelPrice.add(textFieldPrice, BorderLayout.CENTER);
		panelPrice.add(labelPriceEuro, BorderLayout.EAST);
		panelPrice.setBackground(Util.orange);

		panelDate = new JPanel(new GridLayout(1, 5));
		panelDate.setBackground(Util.orange);
		labelExpirationDate = new JLabel("Expiration Date: ");
		labelDateInfo = new JLabel();
		labelDateInfo.setIcon(new ImageIcon("images/info.png"));
		labelDateInfo
				.setToolTipText("This is the date when your offer expires. A valid input looks as follows: 2020-12-10");
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

		addOfferDialogController = new AddOfferDialogController(this);

		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(addOfferDialogController.getCancelListener());

		buttonSave = new JButton("Save");
		buttonSave.addActionListener(addOfferDialogController.getSaveListener());

		panelButton = new JPanel(new GridLayout(1, 2));
		panelButton.add(buttonSave);
		panelButton.add(buttonCancel);

		container.add(panelInput, BorderLayout.CENTER);
		container.add(panelButton, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setTitle("Create an offer");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);

	}

	// returns an empty JLabel
	// Java Swing Layouts are a bit odd, so I use it as a filler
	public JLabel getEmptyLabel() {
		return new JLabel("");
	}

	public String[] getProductArray() {
		return productArray;
	}

	public ArrayList<String> getProductList() {
		return productList;
	}

	public JTextField getTextFieldDistance() {
		return textFieldDistance;
	}

	public JTextField getTextFieldProducts() {
		return textFieldProducts;
	}

	public JTextField getTextFieldPrice() {
		return textFieldPrice;
	}

	public JTextField getTextFieldDate() {
		return textFieldDate;
	}

}
