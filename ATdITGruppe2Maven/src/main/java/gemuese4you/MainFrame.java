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
public class MainFrame extends JFrame {
	private Container c;
	public static JTabbedPane tabPane;
	private JPanel pHome, pShop, pJob, pProfile;

	public MainFrame() {
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

		tabPane.addTab("Home", new ImageIcon("src/main/resources//home.png"), pHome);
		tabPane.addTab("Shop", new ImageIcon("src/main/resources//shop.png"), pShop);
		tabPane.addTab("Jobs", new ImageIcon("src/main/resources//farmer.png"), pJob);
		tabPane.addTab("Profile", new ImageIcon("src/main/resources//job.png"), pProfile);
		c.add(tabPane);

		ImageIcon frameIcon = new ImageIcon("src/main/resources//carrotMain.png");
		this.setIconImage(frameIcon.getImage());

		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setTitle("Gemüse 4 You");
	}
	

}
