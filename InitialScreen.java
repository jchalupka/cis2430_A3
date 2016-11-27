import javax.swing.*;

public class InitialScreen extends JPanel{
    public InitialScreen() {
        JLabel welcomeMsg =
            new JLabel("<html> " + 
                       "Welcome to eStore<br><br> " +
                       "Choose a command from the \"Commands\" menu above for<br> " + 
                       "adding a product, searching products, or quitting the program. " +
                       "<html>");
        this.add(welcomeMsg);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        InitialScreen initScreen = new InitialScreen();
        frame.add(initScreen);
        frame.setVisible(true);
        frame.pack();
    }
}
