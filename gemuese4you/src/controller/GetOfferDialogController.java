package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Offer;
import view.GetOfferDialogView;

/**
 * @author I518189 Represents the logic of the dialog which opens when the user clicks an offer to get the details.
 */
public class GetOfferDialogController {
	private GetOfferDialogView getOfferDialogView;

	public GetOfferDialogController(GetOfferDialogView getOfferDialogView) {
		this.getOfferDialogView = getOfferDialogView;
	}

	/**
	 * Action which is performed when the user clicks the buy button.
	 * 
	 * @return Returns a listener for the buy button.
	 */
	public ActionListener getBuyListener() {
		ActionListener buyListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"By pushing this button you can add this offer to your shopping cart (function hasn´t been implemented yet).");
			}

		};
		return buyListener;
	}

	/**
	 * Action which is performed when the user clicks the cancel button.
	 * 
	 * @return Returns a listener for the cancel button.
	 */
	public ActionListener getCancelListener() {
		ActionListener cancelListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getOfferDialogView.dispose();

			}
		};
		return cancelListener;
	}

	/**
	 * Formats the list productList into a readable String
	 * @param offer Offer which the productList belongs to.
	 * @return Returns the productList as a String.
	 */
	public String getFormattedProductList(Offer offer) {
		String products = "";
		for (int i = 0; i < offer.getProductList().size(); i++) {
			products = products + offer.getProductList().get(i) + ", ";
		}
		products = products.substring(0, products.length() - 2);
		return products;
	}
}
