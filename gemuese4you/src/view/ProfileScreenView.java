package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import controller.ProfileScreenController;
import gemuese4you.LoginScreen;
import gemuese4you.Screen;
import gemuese4you.Util;

public class ProfileScreenView extends Screen {
	private JButton buttonChangePassword, buttonDelete;
	private JLabel labelUserID, labelUsername, labelJobOffer, labelOffer;
	private JPanel panelTitlebar, panelUserInfo, panelFrame, panelCenter, panelExampleOffer, panelExampleJobOffer;
	private static Border line = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	private ProfileScreenController profileScreenController;

	public ProfileScreenView() {
		this.setLayout(new BorderLayout());
		panelTitlebar = getTitleBar("Profile");
		panelTitlebar.setBackground(Util.orange);
		profileScreenController = new ProfileScreenController(this);
		
		// show user information
		panelUserInfo = getUserInfoPanel();

		// show offers inserted by the user (mock data)
		labelOffer = new JLabel("These are your current offers: ");
		panelExampleOffer = getExampleOffer();

		// show job offers inserted by the user (mock data)
		labelJobOffer = new JLabel("These are your current job offers: ");
		panelExampleJobOffer = getExampleOffer();

		panelFrame = new JPanel(new GridLayout(5, 1));

		panelCenter = new JPanel();
		panelCenter.add(panelUserInfo);
		panelCenter.setBackground(Util.orange);
		panelFrame.add(panelCenter);

		panelFrame.add(labelOffer);
		panelFrame.add(panelExampleOffer);

		panelFrame.add(labelJobOffer);
		panelFrame.add(panelExampleJobOffer);

		panelFrame.setBackground(Util.orange);
		this.add(panelTitlebar, BorderLayout.NORTH);
		this.add(panelFrame, BorderLayout.CENTER);
		panelFrame.revalidate();

	}

	// returns a JPanel with the user information
	public JPanel getUserInfoPanel() {
		JPanel panelUserInfo = new JPanel(new GridLayout(2, 1));

		labelUsername = new JLabel("You are logged in as:   ");
		labelUserID = new JLabel(LoginScreen.userID);

		JPanel panelUsername = new JPanel(new GridLayout(1, 2));
		panelUsername.add(labelUsername);
		panelUsername.add(labelUserID);
		panelUsername.setBackground(Util.orange);

		buttonChangePassword = new JButton("Change password");
		buttonChangePassword.addActionListener(profileScreenController.getChangePasswordListener());

		panelUserInfo.add(panelUsername);
		panelUserInfo.add(buttonChangePassword);
		panelUserInfo.setBackground(Util.orange);
		panelUserInfo.repaint();

		return panelUserInfo;
	}

	// returns a JPanel with an example offer
	public JPanel getExampleOffer() {
		buttonDelete = Util.getCustomButton("delete");
		buttonDelete.setBackground(new Color(255, 237, 203));
		buttonDelete.addActionListener(profileScreenController.getDeleteListener());

		JPanel panelExampleOffer = new JPanel(new BorderLayout());

		JLabel labelExampleOffer = new JLabel("Example offer");
		labelExampleOffer.setToolTipText(
				"This function hasn´t been implemented. By pushing this you could e.g. change details of your job offer.");

		panelExampleOffer.setBorder(line);
		panelExampleOffer.setBackground(new Color(255, 237, 203));
		panelExampleOffer.add(labelExampleOffer, BorderLayout.WEST);
		panelExampleOffer.add(buttonDelete, BorderLayout.EAST);
		panelExampleOffer.repaint();
		panelExampleOffer.revalidate();

		return panelExampleOffer;
	}

}
