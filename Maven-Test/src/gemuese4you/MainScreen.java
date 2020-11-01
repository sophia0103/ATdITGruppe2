package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Container;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.HomeScreenView;
import view.JobScreenView;
import view.LoginScreenView;
import view.ProfileScreenView;
import view.ShopScreenView;


/**
 * @author I518189
 * Represents the Main Screen which includes the tabbed pane with each individual screen.
 */
public class MainScreen extends JFrame {
	private Container c;
	public static JTabbedPane tabPane;
	private JPanel pHome, pShop, pJob, pProfile;

	public MainScreen() {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.setBackground(Util.orange);

		tabPane = new JTabbedPane(JTabbedPane.BOTTOM);
		pHome = new HomeScreenView();
		pShop = new ShopScreenView();
		pJob = new JobScreenView();
		pProfile = new ProfileScreenView();

		pShop.setBackground(Util.orange);
		pJob.setBackground(Util.orange);
		pProfile.setBackground(Util.orange);

		tabPane.addTab("Home", new ImageIcon("Docmages/images/home.png"), pHome);
		tabPane.addTab("Shop", new ImageIcon("Docmages/images/shop.png"), pShop);
		tabPane.addTab("Jobs", new ImageIcon("Docmages/images/farmer.png"), pJob);
		tabPane.addTab("Profile", new ImageIcon("Docmages/images/job.png"), pProfile);
		c.add(tabPane);

		ImageIcon frameIcon = new ImageIcon("Docmages/images/carrotMain.png");
		this.setIconImage(frameIcon.getImage());

		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setTitle("Gem�se 4 You");
	}
	

}
