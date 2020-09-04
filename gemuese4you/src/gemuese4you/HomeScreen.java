package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class HomeScreen extends Screen implements ActionListener{

	private JButton buttonJobs;
	private JButton buttonShop;
	private JButton buttonNews;
	private JButton buttonAboutUs;
	
	public HomeScreen() {
		this.setBackground(orange);
		this.setLayout(new BorderLayout());
		this.add(this.getTitleBar("Home"), BorderLayout.NORTH);
		
		JPanel homeScreenContent = new JPanel();
		homeScreenContent.setLayout(new BoxLayout(homeScreenContent, BoxLayout.Y_AXIS));
		homeScreenContent.setBackground(orange);
		
		JLabel headlineJobs = createHeadline("Neueste Stellenangebote:");
		
		String[] contentJobs = {"\n\u2022 Erntehelfer/in Ludwigshafen", "\n\u2022 Ernteleiter/in Kaiserslautern", "\n\u2022 Kassierer/in Hofladen Juergens in Walldorf"}; 
		buttonJobs = createButton(contentJobs);
		buttonJobs.addActionListener(this);

		JLabel headlineShop= createHeadline("Neueste Angebote:");
		
		String[] contentShop = {"\n\u2022 Kartoffeln super günstig!", "\n\u2022 Spagel 500g bei Bauer Fitz", "\n\u2022 Pflaumen 10 kaufen 2 umsonst"};
		buttonShop = createButton(contentShop);
		buttonShop.addActionListener(this);
		
		JLabel headlineNews = createHeadline("Neuigkeiten:");
		
		String[] contentNews = {"\n\u2022 Spargelsaison hat endlich angefangen. Jetzt Spagel kaufen!", "\n\u2022 Coronavirus: Was Sie wissen müssen", "\n\u2022 Aktion: Wochen auf jeden Einkauf 5% sparen"};
		buttonNews = createButton(contentNews);
		buttonNews.addActionListener(this);
		
		JLabel headlineAboutUs = createHeadline("Über unsere App:");
		
		String[] contentAboutUs = {"\n\u2022 Das Ziel unserer App", "\n\u2022 Wer sind wir?", "\n\u2022 Wie kann man uns erreichen?"};
		buttonAboutUs = createButton(contentAboutUs);
		buttonAboutUs.addActionListener(this);
		
		homeScreenContent.add(headlineJobs);
		homeScreenContent.add(buttonJobs);
		homeScreenContent.add(headlineShop);
		homeScreenContent.add(buttonShop);
		homeScreenContent.add(headlineNews);
		homeScreenContent.add(buttonNews);
		homeScreenContent.add(headlineAboutUs);
		homeScreenContent.add(buttonAboutUs);
		
		homeScreenContent.setBorder(BorderFactory.createEmptyBorder(10, 30, 5, 30));

		JScrollPane scrollable = new JScrollPane(homeScreenContent, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollable.setBorder(BorderFactory.createEmptyBorder());
		scrollable.setSize(450, 450);
		this.add(scrollable, BorderLayout.CENTER);
	}

	private JButton createButton(String[] content) {
		JButton button = new JButton();
		button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.add(new JLabel(content[0]));
		button.add(new JLabel(content[1]));
		button.add(new JLabel(content[2]));
		button.setBackground(new Color(255, 242, 229));
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(400,100));
		button.setMinimumSize(new Dimension(400,100));
		button.setMaximumSize(new Dimension(400,100));
		return button;
	}
	
	private JLabel createHeadline(String content) {
		JLabel headline = new JLabel(content);
		headline.setFont(new Font("Arial", Font.BOLD, 16));
		headline.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 30));
		return headline;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonJobs) {
			MainScreen.tabPane.setSelectedIndex(2);
		}else if(e.getSource() == buttonShop) {
			MainScreen.tabPane.setSelectedIndex(1);
		}else if(e.getSource() == buttonNews) {
			//open new window
		}else{
			//open new window for AboutUs
		}
	}
}
	

