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

        yearLabel = new JLabel("Year: ");
        g.gridx = 0;
        g.gridy = 3;
        inputPanel.add(yearLabel, g);
        yearField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 3;
        inputPanel.add(yearField, g);

        priceLabel = new JLabel("Price: ");
        g.gridx = 0;
        g.gridy = 4;
        inputPanel.add(priceLabel, g);
        priceField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 4;
        inputPanel.add(priceField, g);

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

    	bottomPanel.setPreferredSize(new Dimension(100,200));
    	bottomPanel.add(scroll, BorderLayout.CENTER);

        this.add(bottomPanel, BorderLayout.SOUTH);
        
        typeComboBox.setSelectedItem("Book");
    }

    protected String getType() {
        return (String) typeComboBox.getSelectedItem();       
    }

    protected static ArrayList<String> getFields() {
        ArrayList<String> result = new ArrayList<String>();
        result.add(idField.getText());
        result.add(nameField.getText());
        result.add(yearField.getText());
        result.add(priceField.getText());
        result.add(authorsField.getText());
        result.add(publisherField.getText());
        result.add(makerField.getText());

        return result;
    }

    class NotifyTypeChange implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            resetFields();
        }
    }

    private void resetFields() {
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

    class AddButtonPressed implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            addProduct();
            resetFields();

        }
    }

    private void addProduct() {
        System.out.println("Add button pressed");

        /*This is the handler*/
        ArrayList<String> fields = getFields();

        String id, name, price, year, author, publisher, maker;
        id = fields.get(0);
        name = fields.get(1);
        year = fields.get(2);
        price = fields.get(3);
        author = fields.get(4);
        publisher = fields.get(5);
        maker = fields.get(6);

        System.out.println(fields);


        try{  
            Product product = null;

            if (getType().equals("Book")) {
                product = new Book(id, name, year, price, author, publisher);
            } else if (getType().equals("Electronic")) {
                product = new Electronic(id, name, year, price, maker);
            } else {
                System.out.println("An error has occcured, this is not a recognized Type.");
            }

            StoreSearch.addToStore(product);
            textArea.setText("Added Product:\n" + product.toString());

        } catch(IllegalArgumentException error) {
            System.out.println("Could not add product");
            String msg = "Error creating product: " + error.getMessage();
            JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.WARNING_MESSAGE);
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

        idField.setText("123456");
        nameField.setText("Harry Potter");
        priceField.setText("29.99");
        yearField.setText("2004");
        authorsField.setText("Rowlings");
        publisherField.setText("BloomsBerg");

    }
}
