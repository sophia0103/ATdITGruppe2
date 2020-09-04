package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Color;
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
	private JButton btChangePassword, btDelete;
	private JLabel lUserID, lUsername, lOffer, lExampleOffer, lJob, lExampleJob;
	private JPanel pYourOffers, pUsername, pYourJobs, pJobFrame, pTitlebar, pUserInfo, pFrame, pOfferFrame, pCenter;
	private Connection connection;
	private Border line;

	public ProfileScreen() {
		
		// show user information
		this.setLayout(new BorderLayout());
		pTitlebar = getTitleBar("Job");
		pTitlebar.setBackground(Util.orange);
		
		lUsername = new JLabel("You are logged in as:   ");
		lUserID = new JLabel(LoginScreen.userID);
		pUsername = new JPanel(new GridLayout(1, 2));
		pUsername.add(lUsername);
		pUsername.add(lUserID);
		pUsername.setBackground(Util.orange);
		btChangePassword = new JButton("Change your password");
		btChangePassword.addActionListener(new ChangePasswordListener());
		pUserInfo = new JPanel(new GridLayout(2, 1));
		pUserInfo.add(pUsername);
		pUserInfo.add(btChangePassword);
		pUserInfo.setBackground(Util.orange);
		pUserInfo.repaint();

		line = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		
		// show offers inserted by the user (mock data)
		pOfferFrame = new JPanel(new GridLayout(2, 1));
		
		JPanel pExampleOffer = new JPanel(new BorderLayout());
		pExampleOffer.setBorder(line);
		pExampleOffer.setBackground(new Color(255, 237, 203));
		lOffer = new JLabel("These are your current offers: ");
		lExampleOffer = new JLabel("Your offer No.1");
		lExampleOffer.setToolTipText(
				"This function hasn´t been implemented. By pushing this you could e.g. change details of your offer.");
		btDelete = Util.getCustomButton("delete");
		btDelete.addActionListener(new DeleteListener());
		btDelete.setBackground(new Color(255, 237, 203));
		btDelete.setVisible(true);
		
		pExampleOffer.add(lExampleOffer, BorderLayout.WEST);
		pExampleOffer.add(btDelete, BorderLayout.EAST);
		pExampleOffer.repaint();
		
		pOfferFrame.add(lOffer);
		pOfferFrame.add(pExampleOffer);
		pOfferFrame.setBackground(Util.orange);

		// show job offers inserted by the user (mock data)
		pJobFrame = new JPanel(new GridLayout(2, 1));
		JPanel pExampleJob = new JPanel(new BorderLayout());
		pExampleJob.setBorder(line);
		pExampleJob.setBackground(new Color(255, 237, 203));
		lJob = new JLabel("These are your current job offers: ");
		lExampleJob = new JLabel("Your job offer No.1");
		lExampleJob.setToolTipText(
				"This function hasn´t been implemented. By pushing this you could e.g. change details of your job offer.");
		pExampleJob.add(lExampleJob, BorderLayout.WEST);
		pExampleJob.add(btDelete, BorderLayout.EAST);
		pExampleJob.repaint();
		pJobFrame.add(lJob);
		pJobFrame.add(pExampleJob);
		pJobFrame.setBackground(Util.orange);

		pFrame = new JPanel(new GridLayout(3, 1));
		pCenter = new JPanel();
		pCenter.add(pUserInfo);
		pCenter.setBackground(Util.orange);
		pFrame.add(pCenter);
		pFrame.add(pOfferFrame);
		pFrame.add(pJobFrame);
		pFrame.setBackground(Util.orange);
		this.add(pTitlebar, BorderLayout.NORTH);
		this.add(pFrame, BorderLayout.CENTER);
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
