package gemuese4you;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public abstract class Screen extends JPanel {
	static Color orange = new Color(255, 229, 204);;

	//returns a title bar for a screen
	public JPanel getTitleBar(String title) {
		JPanel pTitleBar = new JPanel(new BorderLayout());
		JLabel lTitle = new JLabel(title);
		lTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		pTitleBar.add(lTitle, BorderLayout.CENTER);
		pTitleBar.setBackground(orange);
		pTitleBar.add(getBackButton(), BorderLayout.WEST);
		return pTitleBar;
	}

	// returns a button to get back to the last Screen
	public JButton getBackButton() {
		JButton btBack = new JButton();
		btBack = new JButton();
		btBack.setBackground(orange);
		btBack.setIcon(new ImageIcon("images/back2.png"));
		btBack.setMargin(new Insets(0, 0, 0, 0));
		btBack.setBorder(null);
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Component parent = MainScreen.tabPane.getParent();
				do {
					parent = parent.getParent();
				}while(!(parent instanceof JFrame));
				JFrame parentFrame = (JFrame) parent;
				parentFrame.dispose();
				LoginScreen log = new LoginScreen();
				log.setVisible(true);
				log.setSize(500, 500);
				log.setTitle("Gemüse 4 You");

			}

		};
		btBack.addActionListener(buttonListener);
		return btBack;
	}

}
