package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddOfferDialog extends JFrame implements ActionListener {
	private JTextField textFieldDistance, textFieldProducts, textFieldPrice, textFieldDate;
	private JLabel labelDistance, labelProducts, labelPrice, labelExpirationDate, labelProductInfo, labelDateInfo,
			labelEmpty;
	private JPanel panelInput, panelProduct, panelDate;
	private JButton buttonSave;
	private String[] productArray;
	private ArrayList<String> productList;
	static Connection connection;

	public AddOfferDialog() {
		Container container = getContentPane();
		container.setBackground(Util.orange);
		container.setLayout(new BorderLayout());
		labelEmpty = new JLabel();
		panelInput = new JPanel(new GridLayout(8, 1));

		textFieldDistance = new JTextField();
		textFieldProducts = new JTextField();
		textFieldPrice = new JTextField();
		textFieldDate = new JTextField();

		labelDistance = new JLabel("Distance: ");

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

		panelDate = new JPanel(new GridLayout(1, 5));
		panelDate.setBackground(Util.orange);
		labelExpirationDate = new JLabel("Expiration Date: ");
		labelDateInfo = new JLabel();
		labelDateInfo.setIcon(new ImageIcon("images/info.png"));
		labelDateInfo.setToolTipText("This is the date when your offer expires. A valid input looks as follows: 2020-12-10");
		panelDate.add(labelExpirationDate);
		panelDate.add(labelDateInfo);
		panelDate.add(getEmptyLabel());
		panelDate.add(getEmptyLabel());
		panelDate.add(getEmptyLabel());

		panelInput.add(labelDistance);
		panelInput.add(textFieldDistance);
		panelInput.add(panelProduct);
		panelInput.add(textFieldProducts);
		panelInput.add(labelPrice);
		panelInput.add(textFieldPrice);
		panelInput.add(panelDate);
		panelInput.add(textFieldDate);
		panelInput.setBackground(Util.orange);
		buttonSave = Util.getCustomButton("save");
		container.add(panelInput, BorderLayout.CENTER);
		container.add(buttonSave, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setTitle("Create an offer");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);

		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ShopScreen.lastOfferID = Offer.getLastOfferID();

		buttonSave.addActionListener(this);
	}
	
	//returns an empty JLabel
	//Java Swing Layouts are a bit odd, so I use it as a filler
	public JLabel getEmptyLabel() {
		return new JLabel("");
	}

	// add a valid offer and its products to the database
	@Override
	public void actionPerformed(ActionEvent e) {
		if (inputIsValid()) {
			try {
				addOffer();
				readProducts();
				addProductListOfOffer();
				JOptionPane.showMessageDialog(null, "Offer was successfully created! :)");
				this.dispose();
			} catch (SQLException sqlException) {
				// Can´t check for wrong data type in inputIsValid method
				JOptionPane.showMessageDialog(null, "Check for wrong data type", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// get the products from the product text field
	public void readProducts() {
		productArray = textFieldProducts.getText().split(",");
		productList = new ArrayList<String>();
		for (int i = 0; i < productArray.length; i++) {
			productList.add(productArray[i]);
			checkIfProductExists(productArray[i]);
		}
	}

	// if one of the products doesn´t yet exist in the database, add it to the
	// products database table
	public void checkIfProductExists(String productName) {
		try {
			Statement statementProducts = connection.createStatement();
			int numberOfExistingProducts;
			for (int i = 0; i < productList.size(); i++) {
				String checkProductExists = "SELECT COUNT(productName) FROM products WHERE productName = '"
						+ productName + "'";
				ResultSet resultProductExists = statementProducts.executeQuery(checkProductExists);
				resultProductExists.next();
				numberOfExistingProducts = resultProductExists.getInt(1);
				if (numberOfExistingProducts == 0) {
					addNonExistingProduct(productName);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// adds a non existing product to the product database table
	public void addNonExistingProduct(String productName) {
		try {
			Statement statementNonExistingProduct = connection.createStatement();
			String queryNonExistingProduct = "INSERT INTO products(productName) VALUES ('" + productName + "')";
			statementNonExistingProduct.executeQuery(queryNonExistingProduct);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean inputIsValid() {
		if (textFieldDistance.getText().equals("") || textFieldProducts.getText().equals("")
				|| textFieldPrice.getText().equals("") || textFieldDate.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Input mustn´t be empty", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// check if the expiration date is before the current date
		if (Util.returnStringAsDate(textFieldDate.getText()).compareTo(Util.returnDate()) < 0) {
			JOptionPane.showMessageDialog(null, "Expiration date has to be later than current date.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			//Check if input for product is a number
		Integer.parseInt(textFieldProducts.getText());
		JOptionPane.showMessageDialog(null, "Check for wrong datatype", "Error",
				JOptionPane.ERROR_MESSAGE);
		return false;
		}
		catch(Exception e) {
			return true;
		}
	}

	// Insert new offer into the offers database table
	public void addOffer() throws SQLException {
		Statement statement = connection.createStatement();
		// Auto increment in SQL doesn´t work properly, so we do it manually
		ShopScreen.lastOfferID++;

		String querySaveOffer = "INSERT INTO offers VALUES (" + ShopScreen.lastOfferID + ",'" + LoginScreen.userID
				+ "'," + textFieldDistance.getText() + ",'" + textFieldDate.getText() + "'," + textFieldPrice.getText()
				+ ")";
		statement.execute(querySaveOffer);
	}

	// add products off an offer to database table productsInOffer
	public void addProductListOfOffer() {
		try {
			Statement statementAddProductList = connection.createStatement();
			for (int i = 0; i < productList.size(); i++) {
				String productOffer = "INSERT INTO productsinoffer VALUES (" + ShopScreen.lastOfferID
						+ ",(SELECT productID FROM products WHERE products.productName ='" + productList.get(i) + "'))";
				statementAddProductList.execute(productOffer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
