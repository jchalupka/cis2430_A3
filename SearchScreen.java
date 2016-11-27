import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;

public class SearchScreen extends JPanel {
	protected JTextField idField,
						 nameKeyWordsField,
						 startYearField,
						 endYearField;

	protected static JTextArea textArea;

	public SearchScreen() {
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Searching products"), BorderLayout.NORTH);


        /*Hold all the input fields*/
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;

        
        /*Labels and Text Fields*/
        g.gridx = 0;
        g.gridy = 0; 
        inputPanel.add(new JLabel("ProductID: "), g);
        idField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 0; 
        inputPanel.add(idField, g);

        g.gridx = 0;
        g.gridy = 1;
        inputPanel.add(new JLabel("Name Keywords: "), g);
        nameKeyWordsField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 1;
        inputPanel.add(nameKeyWordsField, g);
        
        g.gridx = 0;
        g.gridy = 2;
        inputPanel.add(new JLabel("Start Year: "), g);
        startYearField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 2;
        inputPanel.add(startYearField, g);

        g.gridx = 0;
        g.gridy = 3;
        inputPanel.add(new JLabel("EndYear: "), g);
        endYearField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 3;
        inputPanel.add(endYearField, g);

        this.add(inputPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1,0,80));
        
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetButtonPressed());
        buttonPanel.add(resetButton);
        
        JButton addButton = new JButton("Search");
        addButton.addActionListener(new SearchButtonPressed());
        buttonPanel.add(addButton);

        rightPanel.add(buttonPanel, new GridBagConstraints());


        this.add(rightPanel, BorderLayout.EAST);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());

	    bottomPanel.add(new JLabel("Messages"), BorderLayout.NORTH);

		textArea = new JTextArea();
		textArea.setEditable(false);

		JScrollPane scroll = new JScrollPane(textArea);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	
    	bottomPanel.setPreferredSize(new Dimension(100,200));
		bottomPanel.add(scroll, BorderLayout.CENTER);

	    this.add(bottomPanel, BorderLayout.SOUTH);
        
	}

	protected ArrayList<String> getFields() {
		ArrayList<String> result = new ArrayList<String>();
		result.add(idField.getText());
		result.add(nameKeyWordsField.getText());
		result.add(startYearField.getText());
		result.add(endYearField.getText());

		return result;
	}

	class SearchButtonPressed implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Search button pressed");

            ArrayList<String> fields = getFields();
            String idToSearch = fields.get(0);
            ArrayList<String> nameKeywordsToSearch = new ArrayList<String>(Arrays.asList(fields.get(1).split(" +")));
            String startYearToSearch = fields.get(2);
            String endYearToSearch = fields.get(3);

            try {
	            Search search = new Search(idToSearch, startYearToSearch, endYearToSearch, nameKeywordsToSearch);

                ArrayList<Product> storeCopy = new ArrayList<Product>();
                for (Product p : StoreSearch.productAList) {
                    storeCopy.add(p.deepCopy());
                }
                

	            ArrayList<Product> results = search.doSearch(StoreSearch.productAList);
	            System.out.println(results.toString());
                textArea.setText("Results:\n");
                for (Product s : results) {
                    textArea.append(s.toString() + "\n\n");
                }

	        } catch(IllegalArgumentException error) {
	        	System.out.println("Could not perform search");
	        	String msg = "Error performing search: " + error.getMessage();
            	JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.WARNING_MESSAGE);
	        }
            
        }
    }

    class ResetButtonPressed implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Reset button pressed");
            idField.setText("");
            nameKeyWordsField.setText("");
            startYearField.setText("");
            endYearField.setText("");
        }
    }

	public static void main(String[] args) {
        JFrame frame = new JFrame();
        SearchScreen searchScreen = new SearchScreen();
        frame.add(searchScreen);
        frame.setVisible(true);
        frame.pack();
	}
}