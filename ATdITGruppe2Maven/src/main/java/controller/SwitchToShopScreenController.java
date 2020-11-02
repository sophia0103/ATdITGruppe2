package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gemuese4you.MainFrame;
import view.HomeScreenView;
import view.ProfileScreenView;
import view.View;


/**
 * @author Luis
 *Represents the logic behind the profile Screen UI.
 */
public class SwitchToShopScreenController implements Controller {

	@Override
	public void startProcess(View view) {
		MainFrame.tabPane.setSelectedIndex(1);
		
	}
}
