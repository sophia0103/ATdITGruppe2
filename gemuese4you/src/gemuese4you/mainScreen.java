package gemuese4you;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mainScreen extends JFrame implements ActionListener {
Container c;
JButton btBack;

	public mainScreen() {
		c = getContentPane();
		c.setLayout(new FlowLayout());
		btBack = new JButton("Back");
		c.add(btBack);
		btBack.addActionListener(this);
		
		this.setVisible(true);
		this.setSize(500, 500);
		this.setTitle("Gemüse 4 You");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		loginScreen log = new loginScreen();
		log.setVisible(true);
		log.setSize(500, 500);
		log.setTitle("Gemüse 4 You");
	}

}
