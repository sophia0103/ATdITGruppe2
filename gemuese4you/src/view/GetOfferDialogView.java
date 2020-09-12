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

import controller.GetOfferDialogController;
import gemuese4you.Util;
import model.Offer;

/**
 * @author I518189
 * Represents the UI of the dialog which opens when the user wants to get the details of an offer.
 */
public class GetOfferDialogView extends JFrame {
	private Container container;
	private JButton buttonBuy, buttonCancel;
	private JLabel labelOfferTitle, labelExpirationDate, labelPrice, labelDistance, labelProducts;
	private JPanel panelDescription, panelFrame, panelButton;
	private GetOfferDialogController getOfferDialogController;

	public GetOfferDialogView(Offer offer) {
		getOfferDialogController = new GetOfferDialogController(this);
		
		container = getContentPane();
		this.setBackground(Util.orange);
		
		buttonBuy = new JButton("Buy");
		buttonBuy.addActionListener(getOfferDialogController.getBuyListener());
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(getOfferDialogController.getCancelListener());
		
		panelButton = new JPanel(new GridLayout(1,2));
		panelButton.add(buttonBuy);
		panelButton.add(buttonCancel);
		
		panelDescription = new JPanel(new GridLayout(5, 1));
		panelDescription.setBackground(Util.orange);
		
		labelOfferTitle = new JLabel(offer.getUserID() + "´s offer: ");
		labelOfferTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		labelExpirationDate = new JLabel("Offer expires on: " + offer.getExpDate());
		labelPrice = new JLabel("Price: " + offer.getPrice() + "€");
		labelDistance = new JLabel("Distance: " + offer.getDistance() + " meters");
		labelProducts = new JLabel("Products included: " + getOfferDialogController.getFormattedProductList(offer));
		
		panelFrame = new JPanel(new BorderLayout());
		
		JPanel panelImage = new JPanel();
		JLabel labelImageProduct = new JLabel(new ImageIcon("images/"+offer.getProductList().get(0)+".png"));
		panelImage.add(labelImageProduct);
		panelImage.setBackground(Util.orange);

		
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
		this.setTitle("Details");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
	}

}
