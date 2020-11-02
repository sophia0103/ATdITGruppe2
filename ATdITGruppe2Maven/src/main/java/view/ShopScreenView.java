package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;
import controller.ShopScreenController;
import gemuese4you.Screen;
import gemuese4you.Util;

/**
 * @author I518189 Represents the UI of the Shop Screen.
 */
public class ShopScreenView extends Screen implements View {

	private JPanel panelEast;
	private JPanel panelTitlebar;
	private JButton buttonAddOffer;
	private JButton buttonRefresh;
	private Controller controller;

	public ShopScreenView() {

		this.setLayout(new BorderLayout());
		panelTitlebar = getTitleBar("Shop");

		buttonAddOffer = Util.getCustomButton("add");
		buttonAddOffer.addActionListener(e -> new AddOfferDialogView());

		buttonRefresh = Util.getCustomButton("refresh");
		controller = new ShopScreenController();
		buttonRefresh.addActionListener(e -> controller.startProcess(this));

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

		controller.startProcess(this);
	}

}
