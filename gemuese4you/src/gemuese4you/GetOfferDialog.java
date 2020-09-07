package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GetOfferDialog extends JFrame implements ActionListener {
	private Container container;
	private JButton buttonBuy;
	private JLabel labelOfferTitle, labelExpirationDate, labelPrice, labelDistance, labelProducts;
	private JPanel panelDescription;

	public GetOfferDialog(Offer offer) {
		container = getContentPane();
		container.setLayout(new BorderLayout());
		this.setBackground(Util.orange);
		
		JButton buttonBuy = Util.getCustomButton("cart");
		buttonBuy.addActionListener(this);
		
		panelDescription = new JPanel(new GridLayout(5, 1));
		panelDescription.setBackground(Util.orange);
		
		labelOfferTitle = new JLabel(offer.getUserID() + "´s offer: ");
		labelOfferTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		labelExpirationDate = new JLabel("Offer expires on: " + offer.getDate());
		labelPrice = new JLabel("Price: " + offer.getPrice() + "€");
		labelDistance = new JLabel("Distance: " + offer.getDistance() + " meters");
		
		labelProducts = new JLabel("Products included: " + getFormattedProductList(offer));
		
		panelDescription.add(labelOfferTitle);
		panelDescription.add(labelExpirationDate);
		panelDescription.add(labelPrice);
		panelDescription.add(labelDistance);
		panelDescription.add(labelProducts);
		
		container.add(panelDescription, BorderLayout.CENTER);
		container.add(buttonBuy, BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setSize(500, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null,
				"By pushing this button you can add this offer to your shopping cart (function hasn´t been implemented yet).");
	}

	//formats the productList into a readable String
	public String getFormattedProductList(Offer offer) {
		String products = "";
		for (int i = 0; i < offer.getProductList().size(); i++) {
			products = products + offer.getProductList().get(i) + ", ";
		}
		products = products.substring(0, products.length() - 2);
		return products;
	}
}
