package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GetOfferDialog extends JFrame implements ActionListener {
	private Container c;
	private JButton btBuy;
	private JLabel lOfferTitle, lExpDate, lPrice, lDist;
	private JPanel pDes;

	public GetOfferDialog(Offer offer) {
		c = getContentPane();
		JButton btBuy = Util.getCustomButton("shop");
		btBuy.addActionListener(this);
		this.setBackground(Util.orange);
		c.setBackground(Util.orange);
		c.setLayout(new BorderLayout());
		pDes = new JPanel(new GridLayout(5, 1));
		pDes.setBackground(Util.orange);
		lOfferTitle = new JLabel(offer.getUserID() + "´s offer: ");
		lExpDate = new JLabel("Offer expires on: " + offer.getDate());
		lPrice = new JLabel("Price: " + offer.getPrice() + "€");
		lDist = new JLabel("Distance: " + offer.getDistance());
		String prod = "";
		for (int i = 0; i < offer.getProductList().size(); i++) {
			prod = prod + offer.getProductList().get(i) + ", ";
		}
		JLabel lProducts = new JLabel("Products included: " + prod.substring(0, prod.length() - 2));
		pDes.add(lOfferTitle);
		pDes.add(lExpDate);
		pDes.add(lPrice);
		pDes.add(lDist);
		pDes.add(lProducts);
		c.add(pDes, BorderLayout.CENTER);
		c.add(btBuy, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setSize(500, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null,
				"By pushing this button you can add this offer to your shopping cart (function hasn´t been implemented yet).");
	}

}
