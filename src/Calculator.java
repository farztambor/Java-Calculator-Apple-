import java.awt.*; // for Graphics
import java.awt.event.*; // for event handling
import java.util.Arrays;
import java.util.Objects;
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

        //Variables for AC .. A+B, AB etc...
        String A  = "0";
        String operator = null;
        String B = null;

        //Create a constructor
        Calculator() {
            //modify the window
//            frame.setVisible(true);// we can see the window
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

                button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        JButton actionButton = (JButton) e.getSource(); // event that gets the source J button is clicked button
                        String  buttonValue = button.getText();// Identify the button that being clicked
                        if(Arrays.asList(rightSymbols).contains(buttonValue)){
                            if(buttonValue == "="){
                                if(A!= null){
                                    B = displayLabel.getText();
                                    double numA = Double.parseDouble(A);
                                    double numB = Double.parseDouble(B);

                                    if(Objects.equals(operator, "+")){
                                        displayLabel.setText(removeZeroDecimal(numA+numB));
                                    }else if(Objects.equals(operator, "-")){
                                        displayLabel.setText(removeZeroDecimal(numA-numB));
                                    }else if(Objects.equals(operator, "÷")){
                                        displayLabel.setText(removeZeroDecimal(numA/numB));
                                    }else if(Objects.equals(operator, "×")){
                                        displayLabel.setText(removeZeroDecimal(numA*numB));
                                    }
                                    clearAll();


                                }
                            } else if ("÷×-+".contains(buttonValue)) {
                                if(operator  == null){
                                    A= displayLabel.getText();
                                    displayLabel.setText("0");
                                }
                                operator = buttonValue;
                            }

                        }else if(Arrays.asList(topSymbols).contains(buttonValue)){
                            if(Objects.equals(buttonValue, "AC")){
                                clearAll();
                                displayLabel.setText("0");
                            }else if(Objects.equals(buttonValue, "+/-")){
                                double numDisplay = Double.parseDouble(displayLabel.getText()); // get the display on the label
                                numDisplay *= -1; // for change the sign
                                displayLabel.setText(removeZeroDecimal(numDisplay));
                            }else if(Objects.equals(buttonValue, "%")){
                                double numDisplay = Double.parseDouble(displayLabel.getText()); // get the display on the label
                                numDisplay /= 100; // converting to percentage
                                displayLabel.setText(removeZeroDecimal(numDisplay));
                            }
                        }else{
                            if(Objects.equals(buttonValue, ".")){
                                if(!displayLabel.getText().contains(buttonValue)){
                                    displayLabel.setText(displayLabel.getText() + buttonValue);
                                }
                            }
                            else if("0123456789".contains(buttonValue)){
                               if(Objects.equals(displayLabel.getText(), "0")){
                                displayLabel.setText(buttonValue);}
                               else{
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                                }
                            }
                        }
                    }
                });

                frame.setVisible(true);// we can see the window
            }
        }

        //function for clearing the text / number in display
    void clearAll(){
            A = "0 ";
            operator = null;
            B =null;
    }
        // function that remove zero decimal on changing the sign
    String removeZeroDecimal(double numDisplay){
        if(numDisplay % 1 == 0){
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }
}
