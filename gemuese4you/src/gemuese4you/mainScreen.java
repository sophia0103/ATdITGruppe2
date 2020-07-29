package gemuese4you;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mainScreen extends JFrame implements ActionListener {
Container c;
JButton btBack;
JTabbedPane tabPane;
JPanel pHome, pShop, pJob, pProfile;

	public mainScreen() {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		btBack = new JButton("Back");
		c.add(btBack, BorderLayout.CENTER);
		btBack.addActionListener(this);
		
		tabPane = new JTabbedPane(JTabbedPane.BOTTOM);
		pHome = new JPanel();
		pShop = new JPanel();
		pJob = new JPanel();
		pProfile = new JPanel();
		
//		ImageIcon iconHome = new ImageIcon("images/haus.jpg");
//		ImageIcon iconArm = new ImageIcon("images/arm.jpg");
//		ImageIcon iconFarmer = new ImageIcon("images/Farmer.jpg");
//		ImageIcon iconZahnrad = new ImageIcon("images/Zahnrad.jpg");
		
		tabPane.addTab("Home", pHome);
		tabPane.addTab("Shop", pShop);
		tabPane.addTab("Jobs", pJob);
		tabPane.addTab("Profile", pProfile);
		c.add(tabPane, BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setSize(500, 500);
		this.setTitle("Gemüse 4 You");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		loginScreen log = new loginScreen();
//		log.setLocation(MAXIMIZED_HORIZ, MAXIMIZED_VERT);
		log.setVisible(true);
		log.setSize(500, 500);
		log.setTitle("Gemüse 4 You");
	}

}
