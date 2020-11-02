package view;
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

import controller.Controller;
//import controller.HomeScreenController;
import controller.ProfileScreenController;
import controller.SwitchToJobScreenController;
import controller.SwitchToShopScreenController;
import gemuese4you.Screen;
import gemuese4you.Util;

/**
 * @author Luis
 * Represents the UI of the home screen.
 */
public class HomeScreenView extends Screen implements View{

	private JButton buttonJobs;
	private JButton buttonShop;
	private JButton buttonNews;
	private JButton buttonAboutUs;
	private Controller switchToJobScreenController;
	private Controller switchToShopScreenController;
	
	public HomeScreenView() {
		this.setBackground(Util.orange);
		this.setLayout(new BorderLayout());
		this.add(this.getTitleBar("Home"), BorderLayout.NORTH);
		
		switchToJobScreenController = new SwitchToJobScreenController();
		switchToShopScreenController = new SwitchToShopScreenController();
		
		JPanel homeScreenContent = new JPanel();
		homeScreenContent.setLayout(new BoxLayout(homeScreenContent, BoxLayout.Y_AXIS));
		homeScreenContent.setBackground(Util.orange);
		
		JLabel headlineJobs = createHeadline("Neueste Stellenangebote:");
		
		String[] contentJobs = {"\n\u2022 Erntehelfer/in Ludwigshafen", "\n\u2022 Ernteleiter/in Kaiserslautern", "\n\u2022 Kassierer/in Hofladen Juergens in Walldorf"}; 
		buttonJobs = createButton(contentJobs);
		buttonJobs.addActionListener(e -> switchToJobScreenController.startProcess(this));

		JLabel headlineShop= createHeadline("Neueste Angebote:");
		
		String[] contentShop = {"\n\u2022 Kartoffeln super g¸nstig!", "\n\u2022 Spagel 500g bei Bauer Fitz", "\n\u2022 Pflaumen 10 kaufen 2 umsonst"};
		buttonShop = createButton(contentShop);
		buttonShop.addActionListener(e -> switchToShopScreenController.startProcess(this));
		
		JLabel headlineNews = createHeadline("Neuigkeiten:");
		
		String[] contentNews = {"\n\u2022 Spargelsaison hat endlich angefangen. Jetzt Spagel kaufen!", "\n\u2022 Coronavirus: Was Sie wissen m√ºssen", "\n\u2022 Aktion: Wochen auf jeden Einkauf 5% sparen"};
		buttonNews = createButton(contentNews);
		buttonNews.setBorderPainted( false );
		buttonNews.setFocusPainted( false );
		
		JLabel headlineAboutUs = createHeadline("‹ber unsere App:");
		
		String[] contentAboutUs = {"\n\u2022 Das Ziel unserer App", "\n\u2022 Wer sind wir?", "\n\u2022 Wie kann man uns erreichen?"};
		buttonAboutUs = createButton(contentAboutUs);
		buttonAboutUs.setBorderPainted( false );
		buttonAboutUs.setFocusPainted( false );

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

	/**
	 * @param content Content which  is displayed in the JButton.
	 * @return Returns a custom JButton which displays the information specified in 'content'.
	 */
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
	
	/**
	 * @param content Content which is displayed in the headline.
	 * @return Returns a JLabel with the headline.
	 */
	private JLabel createHeadline(String content) {
		JLabel headline = new JLabel(content);
		headline.setFont(new Font("Arial", Font.BOLD, 16));
		headline.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 30));
		return headline;
	}
}
