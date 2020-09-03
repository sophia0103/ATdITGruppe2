package gemuese4you;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class profileScreen extends Screen implements ActionListener{
private JTextField tUsername;
private JButton btChangePassword, btDelete;
private JLabel lUsername, lOffer, lExampleOffer, lJob, lExampleJob;
private JPanel pYourOffers,pYourJobs, pJob, pTitlebar, pUserInfo, pFrame, pOffer, pCenter;
private Connection connection;

	public profileScreen() {
		this.setLayout(new BorderLayout());
		pTitlebar = super.getTitleBar("Job");
		pTitlebar.setBackground(Util.orange);
		lUsername = new JLabel("You are logged in as: ");
		tUsername = new JTextField();
		btChangePassword = new JButton("Change your password.");
		btChangePassword.addActionListener(this);
		pUserInfo = new JPanel(new GridLayout(3,1));
		pUserInfo.add(lUsername);
		pUserInfo.add(tUsername);
		pUserInfo.add(btChangePassword);
		pUserInfo.setBackground(Util.orange);
		
		
		pOffer = new JPanel(new GridLayout(2,1));
		JPanel pExampleOffer = new JPanel(new BorderLayout());
		lOffer = new JLabel("These are your current offers: ");
		lExampleOffer = new JLabel("Your offer No.1");
		lExampleOffer.setToolTipText("This function hasn´t been implemented. By pushing this you could e.g. change details of your offer.");
		btDelete = Util.getCustomButton("delete");
		ActionListener deleteListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Are you sure you want to delete this item? (Hasn´t been implemented yet).");
			}
		};
		btDelete.addActionListener(deleteListener);
		pExampleOffer.add(lExampleOffer, BorderLayout.WEST);
		pExampleOffer.add(btDelete, BorderLayout.EAST);
		pExampleOffer.setBackground(new Color(255,237,203));
		pOffer.add(lOffer);
		pOffer.add(pExampleOffer);
		pOffer.setBackground(new Color(255,237,203));
		
		pJob = new JPanel(new GridLayout(2,1));
		JPanel pExampleJob = new JPanel(new BorderLayout());
		lJob = new JLabel("These are your current job offers: ");
		lExampleJob = new JLabel("Your job offer No.1");
		lExampleJob.setToolTipText("This function hasn´t been implemented. By pushing this you could e.g. change details of your job offer.");
		pExampleJob.add(lExampleJob, BorderLayout.EAST);
		pExampleJob.add(btDelete, BorderLayout.EAST);
		pExampleJob.setBackground(new Color(246,255,205));
		pJob.add(lJob);
		pJob.add(pExampleJob);
		pJob.setBackground(new Color(246,255,205));
		
		pFrame = new JPanel(new GridLayout(3,1));
		pCenter = new JPanel();
		pCenter.add(pUserInfo);
		pFrame.add(pCenter);
		pFrame.add(pOffer);
		pFrame.add(pJob);
		pCenter.setBackground(Util.orange);
		pFrame.setBackground(Util.orange);
		this.add(pTitlebar, BorderLayout.NORTH);
		this.add(pFrame,BorderLayout.CENTER);
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame fChangePassword = new JFrame();
		fChangePassword.setLayout(new GridLayout(7,1));
		JLabel lOldPassword = new JLabel("Enter your old password: ");
		JPasswordField tOldPassword = new JPasswordField();
		JLabel lNewPassword = new JLabel("Enter your new password: ");
		JPasswordField tNewPassword = new JPasswordField();
		JLabel lNewPassword2 = new JLabel("Repeat your new password: ");
		JPasswordField tNewPassword2 = new JPasswordField();
		JButton btSave = Util.getCustomButton("save");
		ActionListener saveListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				if(!(lNewPassword.getText()).equals((lNewPassword2.getText()))) {
					throw new Exception();
				}
				Statement stmt = connection.createStatement();
				String queryChangePassword = "UPDATE users SET password = "+lNewPassword.getText()+"WHERE userID = ";
				stmt.execute(queryChangePassword);
				JOptionPane.showMessageDialog(null, "Password changed.");
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "Please enter the same values for the new password.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
			
		};
		btSave.addActionListener(saveListener);
		fChangePassword.add(lOldPassword);
		fChangePassword.add(tOldPassword);
		fChangePassword.add(lNewPassword);
		fChangePassword.add(tNewPassword);
		fChangePassword.add(lNewPassword2);
		fChangePassword.add(tNewPassword2);
		fChangePassword.add(btSave);
		fChangePassword.setBackground(Util.orange);
		fChangePassword.setVisible(true);
		fChangePassword.setSize(500,500);
	}

}
	

