package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class ProfileScreen extends Screen {
	private JButton buttonChangePassword;
	private JLabel labelUserID, labelUsername, labelJobOffer, labelOffer;
	private JPanel panelTitlebar, panelUserInfo, panelFrame, panelCenter, panelExampleOffer, panelExampleJobOffer;
	private static Border line  = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

	public ProfileScreen() {
		this.setLayout(new BorderLayout());
		panelTitlebar = getTitleBar("Job");
		panelTitlebar.setBackground(Util.orange);
		
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

	//returns a JPanel with the user information
	public JPanel getUserInfoPanel() {
		JPanel panelUserInfo = new JPanel(new GridLayout(2, 1));
		
		labelUsername = new JLabel("You are logged in as:   ");
		labelUserID = new JLabel(LoginScreen.userID);
		
		JPanel panelUsername = new JPanel(new GridLayout(1, 2));
		panelUsername.add(labelUsername);
		panelUsername.add(labelUserID);
		panelUsername.setBackground(Util.orange);
		
		buttonChangePassword = new JButton("Change your password");
		buttonChangePassword.addActionListener(new ChangePasswordListener());
		
		panelUserInfo.add(panelUsername);
		panelUserInfo.add(buttonChangePassword);
		panelUserInfo.setBackground(Util.orange);
		panelUserInfo.repaint();	
		
		return panelUserInfo;
	}
	
	
	//returns a JPanel with an example offer
	public JPanel getExampleOffer() {
		JPanel panelExampleOffer = new JPanel(new BorderLayout());
		
		JLabel labelExampleOffer = new JLabel("Your offer No.1");
		labelExampleOffer.setToolTipText(
				"This function hasn´t been implemented. By pushing this you could e.g. change details of your job offer.");
		
		panelExampleOffer.setBorder(line);
		panelExampleOffer.setBackground(new Color(255, 237, 203));
		panelExampleOffer.add(labelExampleOffer, BorderLayout.WEST);
		panelExampleOffer.add(getDeleteButton(), BorderLayout.EAST);
		panelExampleOffer.repaint();
		panelExampleOffer.revalidate();
		
		return panelExampleOffer;
	}
	
	//returns a JButton to delete an offer
	public JButton getDeleteButton() {
		JButton buttonDelete;
		buttonDelete = Util.getCustomButton("delete");
		buttonDelete.addActionListener(new DeleteListener());
		buttonDelete.setBackground(new Color(255, 237, 203));
		buttonDelete.setVisible(true);
		return buttonDelete;
	}
	
}

class ChangePasswordListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		new ChangePasswordDialog();
	}
	
}

class DeleteListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null,
				"Are you sure you want to delete this item? (Hasn´t been implemented yet).");
	}
	
}
