package controller;

import javax.swing.JOptionPane;

import gemuese4you.Starter;
import view.View;

/**
 * @author I518189 Represents the logic behind the profile Screen UI.
 */
public class ProfileScreenController implements Controller {
	private View view;

	@Override
	public void startProcess(View view) {
		this.view = view;
		JOptionPane.showMessageDialog(null,
				Starter.content.getString("deleteOffer"));
	}

}
