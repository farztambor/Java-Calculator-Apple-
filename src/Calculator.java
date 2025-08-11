import java.awt.*; // for Graphics
import java.awt.event.*; // for event handling
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class Calculator {
    // Create Window for the calculator
        int boardwidth = 360;
        int boardHeight = 540;

   // Set color for the window https://www.schemecolor.com/
        Color customLightGray = new Color(212, 212, 210);
        Color customDarkGray = new Color(80, 80, 80);
        Color customBlack = new Color(28, 28, 28);
        Color customOrange = new Color(255, 149, 0);

            //for buttons Panel
        String[] buttonValues = {
                "AC", "+/-", "%", "÷",
                "7", "8", "9", "×",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "√", "="
        };
        String[] rightSymbols = {"÷", "×", "-", "+", "="};
        String[] topSymbols = {"AC", "+/-", "%"};

        //Frame
        JFrame frame = new JFrame("Calculator");
        //Label
        JLabel displayLabel = new JLabel();
        //Panel for display
        JPanel displayPanel = new JPanel();
        //Buttons Panel
        JPanel buttonsPanel = new JPanel();

        //Create a constructor
        Calculator() {
            //modify the window
            frame.setVisible(true);// we can see the window
            frame.setSize(boardwidth,boardHeight);// size of the window
            frame.setLocationRelativeTo(null); // make the window center
            frame.setResizable(false); // window cannot be resized
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // click the x button on the window to close
            frame.setLayout(new BorderLayout()); // can set layout location N E W S  on the window

            // Put the panel and label
            displayLabel.setBackground(customBlack);
            displayLabel.setForeground(Color.WHITE);
            displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
            displayLabel.setHorizontalAlignment(JLabel.RIGHT);
            displayLabel.setText("0");
            displayLabel.setOpaque(true);

            displayPanel.setLayout(new BorderLayout());
            displayPanel.add(displayLabel);
            frame.add(displayPanel, BorderLayout.NORTH); // add border layout to the frame to customize the label layout

            buttonsPanel.setLayout(new GridLayout(5,4)); // display grid layout 5row 4 columns
            buttonsPanel.setBackground(customBlack);
            frame.add(buttonsPanel); // add this to frame

            //iterate to the array for buttons
            for (String value : buttonValues) {
                // Create a button
                JButton button = new JButton();
                button.setFont(new Font("Ariel", Font.PLAIN, 30));
                button.setText(value);
                button.setFocusable(false); // get rid of square when clicking
                button.setBorder(new LineBorder(customBlack)); // set the border of the buttons black

                //Customize the location of the button
                if (Arrays.asList(topSymbols).contains(value)){
                    button.setBackground(customLightGray);
                    button.setForeground(customBlack);
                }else if(Arrays.asList(rightSymbols).contains(value)){
                    button.setBackground(customOrange);
                    button.setForeground(Color.WHITE);
                }else{
                    button.setBackground(customDarkGray);
                    button.setForeground(Color.WHITE);
                }
                buttonsPanel.add(button);
            }
        }
}
