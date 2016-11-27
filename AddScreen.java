import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;

public class AddScreen extends JPanel {
    protected static JComboBox typeComboBox;
    protected static JLabel typeLabel,
                            idLabel,
                            nameLabel,
                            priceLabel,
                            yearLabel,
                            authorsLabel,
                            publisherLabel,
                            makerLabel;

    protected static JTextField idField,
                                nameField,
                                priceField,
                                yearField,
                                authorsField,
                                publisherField,
                                makerField;

    protected static JTextArea textArea;
    
    public AddScreen() {
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Adding a product"), BorderLayout.NORTH);


        /*Hold all the input fields*/
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;

        
        /*Labels and Text Fields*/
        typeLabel = new JLabel("Type: ");
        g.gridx = 0;
        g.gridy = 0; 
        inputPanel.add(typeLabel, g);
        String[] types = {"Book", "Electronic"};
        typeComboBox = new JComboBox<String>(types);
        typeComboBox.setSelectedIndex(0);
        typeComboBox.addActionListener(new NotifyTypeChange());
        g.gridx = 1;
        g.gridy = 0; 
        inputPanel.add(typeComboBox, g);


        idLabel = new JLabel("ProductID: ");
        g.gridx = 0;
        g.gridy = 1;
        inputPanel.add(idLabel, g);
        idField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 1;
        inputPanel.add(idField, g);
        

        nameLabel = new JLabel("Name: ");
        g.gridx = 0;
        g.gridy = 2;
        inputPanel.add(nameLabel, g);
        nameField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 2;
        inputPanel.add(nameField, g);


        priceLabel = new JLabel("Price: ");
        g.gridx = 0;
        g.gridy = 3;
        inputPanel.add(priceLabel, g);
        priceField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 3;
        inputPanel.add(priceField, g);

        yearLabel = new JLabel("Year: ");
        g.gridx = 0;
        g.gridy = 4;
        inputPanel.add(yearLabel, g);
        yearField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 4;
        inputPanel.add(yearField, g);


        authorsLabel = new JLabel("Authors");
        g.gridx = 0;
        g.gridy = 5;
        inputPanel.add(authorsLabel, g);
        authorsField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 5;
        inputPanel.add(authorsField, g);


        publisherLabel = new JLabel("Publisher: ");
        g.gridx = 0;
        g.gridy = 6;
        inputPanel.add(publisherLabel, g);
        publisherField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 6;
        inputPanel.add(publisherField, g);

                             
        makerLabel = new JLabel("Maker: ");
        g.gridx = 0;
        g.gridy = 7; 
        inputPanel.add(makerLabel, g);
        makerField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 7; 
        inputPanel.add(makerField, g);

        this.add(inputPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1,0,80));
        
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetButtonPressed());
        buttonPanel.add(resetButton);
        
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonPressed());
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

	bottomPanel.setPreferredSize(new Dimension(10,100));
	bottomPanel.add(scroll, BorderLayout.CENTER);

        this.add(bottomPanel, BorderLayout.SOUTH);
        
    }

    protected String getType() {
        return (String) typeComboBox.getSelectedItem();       
    }

    protected static ArrayList<String> getFields() {
        ArrayList<String> result = new ArrayList<String>();
        result.add(idField.getText());
        result.add(nameField.getText());
        result.add(priceField.getText());
        result.add(yearField.getText());
        result.add(authorsField.getText());
        result.add(publisherField.getText());
        result.add(makerField.getText());

        return result;
    }

    class NotifyTypeChange implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("State changed to: " + getType());
            if (getType().equals("Book")) {
                authorsLabel.setVisible(true);
                authorsField.setVisible(true);
                publisherLabel.setVisible(true);
                publisherField.setVisible(true);

                makerLabel.setVisible(false);
                makerField.setVisible(false);
            } else if (getType().equals("Electronic")) {
                makerLabel.setVisible(true);
                makerField.setVisible(true);

                authorsLabel.setVisible(false);
                authorsField.setVisible(false);
                publisherLabel.setVisible(false);
                publisherField.setVisible(false);
            } else {
                System.out.println("An error has occcured, this is not a recognized Type.");
            }
        }
    }

    class AddButtonPressed implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add button pressed");

            /*This is the handler*/
            ArrayList<String> fields = getFields();
            System.out.println(fields.toString());


        }
    }

    class ResetButtonPressed implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Reset button pressed");
            idField.setText("");
            nameField.setText("");
            priceField.setText("");
            yearField.setText("");
            authorsField.setText("");
            publisherField.setText("");
            makerField.setText("");
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        AddScreen addScreen = new AddScreen();
        frame.add(addScreen);
        frame.setVisible(true);
        frame.pack();
    }
}
