package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gemuese4you.MainScreen;
import view.HomeScreenView;
import view.ProfileScreenView;


/**
 * @author Luis
 *Represents the logic behind the profile Screen UI.
 */
public class HomeScreenController{
	private HomeScreenView homeScreenView;
	
	public HomeScreenController(HomeScreenView homeScreenView) {
		this.homeScreenView = homeScreenView;
	}
	
	
	/**
	 * Action which is performed when the Shop button is clicked.
	 * @return Returns a listener for the Shop button.
	 */
	public ActionListener getSwitchToShopScreenListener() {
		ActionListener switchToShopScreenListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				MainScreen.tabPane.setSelectedIndex(1);
			}
		};
		return switchToShopScreenListener;
	}
	
	
	/**
	 * Action which is performed when the Job button is clicked.
	 * @return Returns a listener for the Job button.
	 */
	public ActionListener getSwitchToJobScreenListener() {
		ActionListener switchToJobScreenListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				MainScreen.tabPane.setSelectedIndex(2);
			}
		};
		return switchToJobScreenListener;
	}
}
