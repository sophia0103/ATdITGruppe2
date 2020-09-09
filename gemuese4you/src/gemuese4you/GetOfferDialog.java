package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GetOfferDialog extends JFrame implements ActionListener {
	private Container container;
	private JButton buttonBuy, buttonCancel;
	private JLabel labelOfferTitle, labelExpirationDate, labelPrice, labelDistance, labelProducts;
	private JPanel panelDescription, panelFrame, panelImage, panelButton;

	public GetOfferDialog(Offer offer) {
		container = getContentPane();
		this.setBackground(Util.orange);
		
		JButton buttonBuy = new JButton("Buy");
		ActionListener buyListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"By pushing this button you can add this offer to your shopping cart (function hasn´t been implemented yet).");
			}
			
		};
		buttonBuy.addActionListener(buyListener);
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(this);
		panelButton = new JPanel(new GridLayout(1,2));
		panelButton.add(buttonBuy);
		panelButton.add(buttonCancel);
		
		panelDescription = new JPanel(new GridLayout(5, 1));
		panelDescription.setBackground(Util.orange);
		
		labelOfferTitle = new JLabel(offer.getUserID() + "´s offer: ");
		labelOfferTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		labelExpirationDate = new JLabel("Offer expires on: " + offer.getDate());
		labelPrice = new JLabel("Price: " + offer.getPrice() + "€");
		labelDistance = new JLabel("Distance: " + offer.getDistance() + " meters");
		labelProducts = new JLabel("Products included: " + getFormattedProductList(offer));
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
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
