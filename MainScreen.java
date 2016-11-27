import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainScreen extends JPanel {
	JButton addButton, searchButton, exitButton;
	JPanel cards;
	CardLayout cl;

	final static String initialScreen = "Initial Screen";
	final static String addScreen = "Add Screen";
	final static String searchScreen = "Search Screen";

	public static StoreSearch store = new StoreSearch();

	public MainScreen() {
		this.setLayout(new BorderLayout());
		JPanel commandPanel = new JPanel();
		commandPanel.add(new JLabel("Command"));
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add button clicked");
				cl = (CardLayout)(cards.getLayout());
				cl.show(cards, "Add Screen");
			}
		});
		commandPanel.add(addButton);

		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Search button clicked");
				cl = (CardLayout)(cards.getLayout());
				cl.show(cards, "Search Screen");
			}
		});
		commandPanel.add(searchButton);

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit button clicked");
				System.exit(0);
			}
		});
		commandPanel.add(exitButton);

		this.add(commandPanel, BorderLayout.NORTH);

		JPanel initialCard = new InitialScreen();
		JPanel addCard = new AddScreen();
		JPanel searchCard = new SearchScreen();

		cards = new JPanel(new CardLayout());
		cards.add(initialCard, initialScreen);
		cards.add(addCard, addScreen);
		cards.add(searchCard, searchScreen);
		cl = (CardLayout)(cards.getLayout());
		cl.show(cards, "Initial Screen");
		this.add(cards);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		MainScreen mainScreen = new MainScreen();
		frame.add(mainScreen);
		frame.setVisible(true);
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}


	
}