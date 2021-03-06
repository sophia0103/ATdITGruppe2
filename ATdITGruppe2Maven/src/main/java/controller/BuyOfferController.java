package controller;

import javax.swing.JOptionPane;

import gemuese4you.Starter;
import view.View;

/**
 * @author I518189 Represents the logic of the dialog which opens when the user
 *         wants to buy an offer.
 */
public class BuyOfferController implements Controller {
	private View view;

	@Override
	public void startProcess(View view) {
		this.view = view;
		JOptionPane.showMessageDialog(null,
				Starter.content.getString("addOfferToShoppingCard"));
	}

}
