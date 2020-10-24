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

import controller.Controller;
import controller.ProfileScreenController;
import gemuese4you.Screen;
import gemuese4you.Util;

/**
 * @author I518189
 * Represents the UI of the Profile Screen.
 */
public class ProfileScreenView extends Screen implements View{
	private JButton buttonChangePassword;
	private JButton buttonDelete;
	private JLabel labelUserID;
	private JLabel labelUsername;
	private JLabel labelJobOffer;
	private JLabel labelOffer;
	private JPanel panelTitlebar;
	private JPanel panelUserInfo;
	private JPanel panelFrame;
	private JPanel panelCenter;
	private JPanel panelExampleOffer;
	private JPanel panelExampleJobOffer;
	private Controller controller;
	private static Border line = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

	public ProfileScreenView() {
		this.setLayout(new BorderLayout());
		panelTitlebar = getTitleBar("Profile");
		panelTitlebar.setBackground(Util.orange);
		
		panelUserInfo = getUserInfoPanel();

		labelOffer = new JLabel("These are your current offers: ");
		panelExampleOffer = getExampleOffer();

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

	/**
	 * @return Returns a JPanel with information about the user.
	 */
	public JPanel getUserInfoPanel() {
		JPanel panelUserInfo = new JPanel(new GridLayout(2, 1));

		labelUsername = new JLabel("You are logged in as:   ");
		labelUserID = new JLabel(LoginScreenView.userID);

		JPanel panelUsername = new JPanel(new GridLayout(1, 2));
		panelUsername.add(labelUsername);
		panelUsername.add(labelUserID);
		panelUsername.setBackground(Util.orange);

		buttonChangePassword = new JButton("Change password");
		buttonChangePassword.addActionListener(e -> new ChangePasswordDialogView());

		panelUserInfo.add(panelUsername);
		panelUserInfo.add(buttonChangePassword);
		panelUserInfo.setBackground(Util.orange);
		panelUserInfo.repaint();

		return panelUserInfo;
	}

	/**
	 * @return Returns a JPanel which represents an example offer (mock data).
	 */
	public JPanel getExampleOffer() {
		buttonDelete = Util.getCustomButton("delete");
		buttonDelete.setBackground(new Color(255, 237, 203));
		controller = new ProfileScreenController();
		buttonDelete.addActionListener(e -> this.controller.startProcess(this));

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
