package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.ShopScreenController;
import gemuese4you.Screen;
import gemuese4you.Util;

public class ShopScreenView extends Screen{
	
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
