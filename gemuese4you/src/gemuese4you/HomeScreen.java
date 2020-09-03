package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class HomeScreen extends Screen{

	
	public HomeScreen() {
		this.setBackground(orange);
		this.setLayout(new BorderLayout());
		this.add(this.getTitleBar("Home"), BorderLayout.NORTH);
		
		JPanel homeScreenContent = new JPanel();
		homeScreenContent.setLayout(new BoxLayout(homeScreenContent, BoxLayout.Y_AXIS));
		homeScreenContent.setBackground(orange);
		
		JLabel headline1 = createHeadline("Neueste Stellenangebote:");
		
		String[] content1 = {"\n\u2022 Erntehelfer/in Ludwigshafen", "\n\u2022 Ernteleiter/in Kaiserslautern", "\n\u2022 Kassierer/in Hofladen Jürgens in Walldorf"}; 
		JButton button1 = createButton(content1);

		JLabel headline2= createHeadline("Neueste Angebote:");
		
		String[] content2 = {"\n\u2022 Kartoffeln super günstig!", "\n\u2022 Spagel 500g 2€ bei Bauer Fitz", "\n\u2022 Pflaumen 10 kaufen 2 umsonst"};
		JButton button2 = createButton(content2);
		
		JLabel headline3 = createHeadline("Neuigkeiten:");
		
		String[] content3 = {"\n\u2022 Spargelsaison hat endlich angefangen. Jetzt Spagel kaufen!", "\n\u2022 Coronavirus: Was Sie wissen müssen", "\n\u2022 Aktion: Wochen auf jeden Einkauf 5% sparen"};
		JButton button3 = createButton(content3);
		
		JLabel headline4 = createHeadline("Über unsere App:");
		
		String[] content4 = {"\n\u2022 Das Ziel unserer App", "\n\u2022 Wer sind wir?", "\n\u2022 Wie kann man uns erreichen?"};
		JButton button4 = createButton(content4);
		
		homeScreenContent.add(headline1);
		homeScreenContent.add(button1);
		homeScreenContent.add(headline2);
		homeScreenContent.add(button2);
		homeScreenContent.add(headline3);
		homeScreenContent.add(button3);
		homeScreenContent.add(headline4);
		homeScreenContent.add(button4);
		
		homeScreenContent.setBorder(BorderFactory.createEmptyBorder(10, 30, 5, 30));

		JScrollPane scrollable = new JScrollPane(homeScreenContent, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
		button.setPreferredSize(new Dimension(400,150));
		button.setMinimumSize(new Dimension(400,150));
		button.setMaximumSize(new Dimension(400,150));
		return button;
	}
	
	private JLabel createHeadline(String content) {
		JLabel headline = new JLabel(content);
		headline.setFont(new Font("Arial", Font.BOLD, 16));
		headline.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 30));
		return headline;
	}
}
	

