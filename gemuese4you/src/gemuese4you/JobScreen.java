package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class jobScreen extends Screen{

	ArrayList<Job> jobs = new ArrayList();
	
	public jobScreen() {
		this.setBackground(orange);
		this.setLayout(new BorderLayout());
		this.add(this.getTitleBar("Jobs"), BorderLayout.NORTH);
		
		JPanel jobScreenContent = new JPanel();
		jobScreenContent.setLayout(new BoxLayout(jobScreenContent, BoxLayout.Y_AXIS));
		jobScreenContent.setBackground(orange);
		
		JLabel headlineJobs = createHeadline("Neueste Stellenangebote in deiner Nähe:");
		
		jobScreenContent.setBorder(BorderFactory.createEmptyBorder(10, 30, 5, 30));
		
		jobs.add(new Job("Erntehelfer - 50€/h", "Luis Hammecke",12, 5, "30.11.2020","Vollzeit", 15, "Dauerhafter Helfer auf meinem wunderbaren Bauernhof"));
		jobs.add(new Job("Verpacker (m/w/d)", "Markus Söder",6, 8, "20.02.2021","Vollzeit", 15, "Verpacken von Erdbeeren in Dürkheim"));
		jobs.add(new Job("Aufseher (m/w/d) bei der Ernte", "Bernd Brumm",4, 15, "24.12.2020", "Vollzeit", 30, "Aufseher bei der Spargelernte"));
		jobs.add(new Job("Spargelstecher (m/w/d)", "Heinz Schaum", 2, 20, "03.10.2020", "Teilzeit", 12, "Gut bezahlte Hilfe bei der Spargelernte"));

		for(int i = 0; i < jobs.size(); i++) {
			String[] buttonContent = new String[8];
			buttonContent[0] = jobs.get(i).getTitle();
			buttonContent[1] = "Ersteller: " + jobs.get(i).getCreatorName();
			buttonContent[2] = "Dauer: " + jobs.get(i).getDuration() + " Monate";
			buttonContent[3] = "Entfernung: " + Integer.toString(jobs.get(i).getDistance()) + "km";
			buttonContent[4] = "Ablaufdatum: " + jobs.get(i).getExpDate();
			buttonContent[5] = "Beschäftigungstyp: " + jobs.get(i).getEmplyomentType();
			buttonContent[6] = "Gehalt: " + Integer.toString(jobs.get(i).getSalary()) + "€/h";
			buttonContent[7] = "Beschreibung: " + jobs.get(i).getDescription();
			jobScreenContent.add(createButton(buttonContent));
			jobScreenContent.add(Box.createVerticalStrut(10));
		}
		
		JScrollPane scrollable = new JScrollPane(jobScreenContent, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollable.setBorder(BorderFactory.createEmptyBorder());
		scrollable.setSize(450, 450);
		this.add(scrollable, BorderLayout.CENTER);
	}

	private JButton createButton(String[] content) {
		JButton button = new JButton();
		button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		JLabel title = new JLabel(content[0]);
		title.setFont(new Font("Arail", Font.BOLD, 18));
		button.add(title);
		button.add(new JLabel(content[1]));
		button.add(new JLabel(content[2]));
		button.add(new JLabel(content[3]));
		button.add(new JLabel(content[4]));
		button.add(new JLabel(content[5]));
		button.setBackground(new Color(255, 242, 229));
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(400,120));
		button.setMinimumSize(new Dimension(400,120));
		button.setMaximumSize(new Dimension(400,120));
		return button;
	}
	
	private JLabel createHeadline(String content) {
		JLabel headline = new JLabel(content);
		headline.setFont(new Font("Arial", Font.BOLD, 16));
		return headline;
	}
	
}
