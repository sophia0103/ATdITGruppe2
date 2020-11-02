package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.LoginScreenView;


/**
 * @author I518189
 * Structure for each screen class which implements a UI.
 */
public abstract class Screen extends JPanel {
	static Color orange = new Color(255, 229, 204);;

	// returns a title bar for a screen
	public JPanel getTitleBar(String title) {
		JPanel pTitleBar = new JPanel(new BorderLayout());
		JLabel lTitle = new JLabel("   " + title);
		lTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		pTitleBar.add(lTitle, BorderLayout.CENTER);
		pTitleBar.setBackground(orange);
		pTitleBar.add(getBackButton(), BorderLayout.WEST);
		return pTitleBar;
	}

	/**
	 * @return Returns a button to get back to the Login Screen.
	 */
	public JButton getBackButton() {
		JButton btBack = Util.getCustomButton("arrow");
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Component parent = MainFrame.tabPane.getParent();
				do {
					parent = parent.getParent();
				} while (!(parent instanceof JFrame));
				JFrame parentFrame = (JFrame) parent;
				parentFrame.dispose();
				LoginScreenView log = new LoginScreenView();
				log.pack();
				log.setLocationRelativeTo(null);
				log.setVisible(true);
				log.setTitle("Gemüse 4 You");

			}

		};
		btBack.addActionListener(buttonListener);
		btBack.setToolTipText("Log out.");
		return btBack;
	}

}
