package binary_decimal_calculator;

//Adding Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Making the public class
public class CalculatorGUI_Term3 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
//  Making the frames, text fields, buttons and a panel
	JFrame frame;
	JTextField TextField;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[6];
	JButton leftButton, rightButton, b10Button, b2Button, clearbutton, closebutton,dot;
	JPanel panel;
	
//  Setting the fonts for the buttons
	Font myFont = new Font("Times New Roman", Font.BOLD, 30); // global font
	Font btfont = new Font("Times New Roman", Font.BOLD, 20);
  
	CalculatorGUI_Term3() {

      // application window
      frame = new JFrame("Calculator"); // title name
      frame.setSize(350, 500);
      frame.setLayout(null);
      frame.setVisible(true);
      frame.setResizable(false);
      frame.getContentPane().setBackground(new Color(173, 216, 230));
      

      // output Text window
      TextField = new JTextField();
      TextField.setBounds(16, 25, 300, 50); // window location
      TextField.setFont(myFont);
      TextField.setEditable(false);
      

      // all operation buttons
      leftButton = new JButton("<-");
      rightButton = new JButton("->"); 
      b10Button = new JButton("B10");
      b2Button = new JButton("B2");
      clearbutton = new JButton("Clr");
      closebutton = new JButton("Off");
      dot = new JButton(".");
      
//		Putting the buttons in the functionButtons array
      functionButtons[0] = leftButton;
      functionButtons[1] = rightButton;
      functionButtons[2] = b10Button;
      functionButtons[3] = b2Button;
      functionButtons[4] = clearbutton;
      functionButtons[5] = closebutton;
      
//		Assigning the action listeners, font, dimensions to the function buttons
//		Using a loop to use less lines.
      for (int i = 0; i < 6; i++) {
          functionButtons[i].addActionListener(this);
          functionButtons[i].setFont(btfont);
          functionButtons[i].setFocusable(false);
          functionButtons[i].setPreferredSize(new Dimension(70, 70)); // Set preferred size
      }

//    Making the number buttons and assigning their action listeners, font and dimensions.
//    Using a for loop for less lines
      for (int i = 0; i < 10; i++) {
          numberButtons[i] = new JButton(String.valueOf(i));
          numberButtons[i].addActionListener(this);
          numberButtons[i].setFont(myFont);
          numberButtons[i].setFocusable(false);
          numberButtons[i].setPreferredSize(new Dimension(70, 70)); // Set preferred size
      }
      
//    The decimal place action listener 
      dot.addActionListener(this);


      // background for buttons
      panel = new JPanel();
      panel.setBounds(16, 100, 300, 350);
      panel.setLayout(new GridLayout(6, 3, 10, 10)); // Grid for buttons
      panel.setBackground(new Color(173, 216, 230));

      // add all buttons on the panel
      panel.add(clearbutton);
      panel.add(new JLabel("")); // empty space
      panel.add(closebutton);

      panel.add(numberButtons[7]);
      panel.add(numberButtons[8]);
      panel.add(numberButtons[9]);

      panel.add(numberButtons[4]);
      panel.add(numberButtons[5]);
      panel.add(numberButtons[6]);

      panel.add(numberButtons[1]);
      panel.add(numberButtons[2]);
      panel.add(numberButtons[3]);

      panel.add(b10Button);
      panel.add(numberButtons[0]);
      panel.add(b2Button);

      panel.add(leftButton);
      panel.add(dot);
      panel.add(rightButton);
      
//    Adding the panel to the frame (GUI Window), along with the textbox 
      frame.add(panel);
      frame.add(TextField);
      frame.setVisible(true);

  }
//  Making the main function and calling the constructor
  public static void main(String[] args) {
      new CalculatorGUI_Term3(); // create a new calculator
  }

//Since Action Listener is an interface, it needs to be overridden.
//This method has several if statements, for loops and error handling try...catch.
  @Override
  public void actionPerformed(ActionEvent e) {
      for (int i = 0; i < 10; i++) { // output only numbers
          if (e.getSource() == numberButtons[i]) { // check on pressing a number
              String number = String.valueOf(i);
              int pos = TextField.getCaretPosition();
              StringBuilder text = new StringBuilder(TextField.getText());
              text.insert(pos, number);
              TextField.setText(text.toString()); //Add the text to the text field.
              TextField.setCaretPosition(pos + 1); // Move the cursor position after the inserted number
          }
      }
      if (e.getSource() == dot) {
          String dec = ".";
          int pos = TextField.getCaretPosition();
          StringBuilder text = new StringBuilder(TextField.getText());
          if (!text.toString().contains(".")) { // Prevent multiple decimal points
              text.insert(pos, dec);
              TextField.setText(text.toString()); //Add the decimal point to the test field
              TextField.setCaretPosition(pos + 1); // Move the cursor position after the inserted decimal
          }
      }

      // checking operation buttons clicks
      
//    Left button moves the cursor to the left by one.
      if (e.getSource() == leftButton) {
          int pos = TextField.getCaretPosition();
          if (pos > 0) {
              TextField.setCaretPosition(pos - 1);
          }
      }
      
//    Right button moves the cursor to the right by one.
      if (e.getSource() == rightButton) {
          int pos = TextField.getCaretPosition();
          if (pos < TextField.getText().length()) {
              TextField.setCaretPosition(pos + 1);
          }
      }
      
//    This turns the binary to a decimal value (base-2 to base-10)
      if (e.getSource() == b10Button) {
          String text = TextField.getText();
          if (!text.isEmpty()) {
              try {
                  int decimal = Integer.parseInt(text, 2); // Parse the binary number as base 2
                  TextField.setText(Integer.toString(decimal)); // Convert to decimal string
              } catch (NumberFormatException ex) {
                  TextField.setText("Error");
              }
          }
      }
      
//      This turns the decimal value to binary. (base-10 to base-2)
      if (e.getSource() == b2Button) {
          String text = TextField.getText();
          if (!text.isEmpty()) {
              try {
                  int decimal = Integer.parseInt(text); // Parse the decimal number
                  TextField.setText(Integer.toBinaryString(decimal)); // Convert to binary string
              } 
              catch (NumberFormatException ex) {
                  TextField.setText("Error");
              }
          }
      }
      
//    This clears the field.
      if (e.getSource() == clearbutton) {
      	TextField.setText("");
      }
      
//    This closes the window.
      if (e.getSource() == closebutton) {
      	System.exit(0);
      }
  }
}
