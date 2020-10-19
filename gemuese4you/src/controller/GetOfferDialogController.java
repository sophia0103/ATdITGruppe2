package controller;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Offer;
import view.GetOfferDialogView;
import view.View;

/**
 * @author I518189 Represents the logic of the dialog which opens when the user
 *         clicks an offer to get the details.
 */
public class GetOfferDialogController implements Controller {
	private View view;

	@Override
	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void startProcess(View view) {
		setView(view);
		JOptionPane.showMessageDialog(null,
				"By pushing this button you can add this offer to your shopping cart (function hasn´t been implemented yet).");
		setFormattedProductList();
	}
	
	/**
	 * Formats the list productList into a readable String
	 * 
	 * @param offer Offer which the productList belongs to.
	 * @return Returns the productList as a String.
	 */
	public void setFormattedProductList() {
		String products = "";
		Offer offer = ((GetOfferDialogView) view).getOffer();
		for (int i = 0; i < offer.getProductList().size(); i++) {
			products = products + offer.getProductList().get(i) + ", ";
		}
		products = products.substring(0, products.length() - 2);
		JLabel productLabel = new JLabel("Products included: " + products);
		((GetOfferDialogView) view).setLabelProducts(productLabel);
	}


}
