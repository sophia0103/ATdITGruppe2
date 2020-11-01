package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gemuese4you.MainScreen;
import view.HomeScreenView;
import view.ProfileScreenView;
import view.View;


/**
 * @author Luis
 *Represents the logic behind the profile Screen UI.
 */
public class SwitchToShopScreenController implements Controller {
	private View view;
	
	
	@Override
	public void setView(View view) {
		this.view = view;
		
	}
	@Override
	public void startProcess(View view) {
		MainScreen.tabPane.setSelectedIndex(1);
		
	}
}
