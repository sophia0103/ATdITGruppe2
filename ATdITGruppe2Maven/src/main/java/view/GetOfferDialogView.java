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

import controller.BuyOfferController;
import controller.CancelController;
import controller.Controller;
import gemuese4you.Starter;
import gemuese4you.Util;
import model.Offer;

/**
 * @author I518189 Represents the UI of the dialog which opens when the user
 *         wants to get the details of an offer.
 */
public class GetOfferDialogView extends JFrame implements View {
	private Container container;
	private JButton buttonBuy;
	private JButton buttonCancel;
	private JLabel labelOfferTitle;
	private JLabel labelExpirationDate;
	private JLabel labelPrice;
	private JLabel labelDistance;
	private JLabel labelProducts;
	private JPanel panelDescription;
	private JPanel panelFrame;
	private JPanel panelButton;
	private Controller controller;
	private Controller cancelController;
	private Offer offer;

	public GetOfferDialogView(Offer offer) throws Exception {
		this.offer = offer;

		container = getContentPane();
		this.setBackground(Util.orange);

		buttonBuy = new JButton(Starter.content.getString("buy"));
		controller = new BuyOfferController();
		buttonBuy.addActionListener(e -> controller.startProcess(this));

		buttonCancel = new JButton(Starter.content.getString("cancel"));
		cancelController = new CancelController();
		buttonCancel.addActionListener(e -> cancelController.startProcess(this));

		panelButton = new JPanel(new GridLayout(1, 2));
		panelButton.add(buttonBuy);
		panelButton.add(buttonCancel);

		panelDescription = new JPanel(new GridLayout(5, 1));
		panelDescription.setBackground(Util.orange);

		labelOfferTitle = new JLabel(offer.getUserID() + Starter.content.getString("creatorsOffer"));
		labelOfferTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		labelExpirationDate = new JLabel(Starter.content.getString("offerExpires") + offer.getExpDate());
		labelPrice = new JLabel(Starter.content.getString("price") + offer.getPrice() + Starter.content.getString("euro"));
		labelDistance = new JLabel(Starter.content.getString("distance") + offer.getDistance() + Starter.content.getString("meters"));

		panelFrame = new JPanel(new BorderLayout());

		JPanel panelImage = new JPanel();
		JLabel labelImageProduct = new JLabel(
				new ImageIcon("src/main/resources/" + offer.getProductList().get(0) + ".png"));
		panelImage.add(labelImageProduct);
		panelImage.setBackground(Util.orange);

		setFormattedProductList();

		panelDescription.add(labelOfferTitle);
		panelDescription.add(labelProducts);
		panelDescription.add(labelExpirationDate);
		panelDescription.add(labelPrice);
		panelDescription.add(labelDistance);

		panelFrame.add(panelDescription, BorderLayout.WEST);
		panelFrame.add(panelImage, BorderLayout.EAST);
		panelFrame.add(panelButton, BorderLayout.SOUTH);
		panelFrame.setBackground(Util.orange);

		container.add(panelFrame);

		this.setVisible(true);
		this.setTitle(Starter.content.getString("getDialogTitle"));
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
	}

	/**
	 * Formats the list productList into a readable String
	 * 
	 * @param offer Offer which the productList belongs to.
	 * @return Returns the productList as a String.
	 * @throws Exception
	 */

	public void setFormattedProductList() throws Exception {
		String products = "";
		for (int i = 0; i < offer.getProductList().size(); i++) {
			products = products + offer.getProductList().get(i) + ", ";
		}
		products = products.substring(0, products.length() - 2);
		labelProducts = new JLabel(Starter.content.getString("productsIncluded") + products);
	}

}
