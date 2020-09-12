package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ShopScreenController;
import gemuese4you.Screen;
import gemuese4you.Util;

/**
 * @author I518189
 * Represents the UI of the Shop Screen.
 */
public class ShopScreenView extends Screen {

	private ShopScreenController shopScreenController;

	private JPanel panelEast, panelTitlebar;
	private JButton buttonAddOffer, buttonRefresh;

	public ShopScreenView() {

		shopScreenController = new ShopScreenController(this);

		this.setLayout(new BorderLayout());
		panelTitlebar = getTitleBar("Shop");

		buttonAddOffer = Util.getCustomButton("add");
		buttonAddOffer.addActionListener(shopScreenController.getAddListener());

		buttonRefresh = Util.getCustomButton("refresh");
		buttonRefresh.addActionListener(shopScreenController.getRefreshListener());

		// AddOfferButton only exists for farmers (scope)
		if (Util.isUserFarmer()) {
			panelEast = new JPanel(new GridLayout(1, 2));
			panelEast.add(buttonRefresh);
			panelEast.add(buttonAddOffer);
		} else {
			panelEast = new JPanel();
			panelEast.add(buttonRefresh);
		}
		panelTitlebar.add(panelEast, BorderLayout.EAST);
		this.add(panelTitlebar, BorderLayout.NORTH);

		shopScreenController.refresh();
	}

}
