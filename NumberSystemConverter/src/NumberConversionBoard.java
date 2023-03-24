/**
 * PROGRAMMER: JASMIN, Ramon Emmiel P.
 * DATE: March 24, 2023
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberConversionBoard extends JFrame {
    private JPanel numberPanel;
    private JPanel buttonPanel;
    private JButton convertButton, clearButton, exitButton;
    private JTextField binaryTF, decimalTF, octalTF, hexadecimalTF;

    private int decimalInteger;

    private JLabel problemDisplayer;

    private JPanel reminderPanel;

    private ButtonsHandler buttonsHandler;

    private JPanel headPanel;

    public static void main(String[] args) {
        NumberConversionBoard board = new NumberConversionBoard();
    }

    public NumberConversionBoard(){
        setTitle("Number Converter");
        setSize(800,400);

        headPanel = new JPanel();
        JLabel textArea = new JLabel();
        textArea.setText("This application will convert an integer to binary, octal, and hexadecimal.");
        headPanel.add(textArea);

        numberPanel = new JPanel();
        setNumberPanel(numberPanel);

        buttonPanel = new JPanel();
        setButtonPanel(buttonPanel);

        reminderPanel = new JPanel();
        problemDisplayer = new JLabel("");
        problemDisplayer.setFont(new Font("Arial", Font.ITALIC, 20));

        reminderPanel.add(problemDisplayer);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(4,1));
        pane.add(headPanel);
        pane.add(numberPanel);
        pane.add(reminderPanel);
        pane.add(buttonPanel);

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setNumberPanel(JPanel panel){
        JLabel decimalLabel = new JLabel("Decimal Number: ");
        decimalLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel binaryLabel = new JLabel("Binary Number: ");
        binaryLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel octalLabel = new JLabel("Octal Number: ");
        octalLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel hexadecimalLabel = new JLabel(" Hexadecimal Number: ");
        hexadecimalLabel.setFont(new Font("Arial", Font.BOLD, 20));

        decimalTF = new JTextField(20);
        decimalTF.setFont(new Font("Arial", Font.BOLD, 20));

        binaryTF = new JTextField(20);
        binaryTF.setFont(new Font("Arial", Font.BOLD, 20));

        octalTF = new JTextField(20);
        octalTF.setFont(new Font("Arial", Font.BOLD, 20));

        hexadecimalTF = new JTextField(20);
        hexadecimalTF.setFont(new Font("Arial", Font.BOLD, 20));

        panel.setLayout(new GridLayout(4,4));
        panel.add(decimalLabel);
        panel.add(decimalTF);
        panel.add(binaryLabel);
        panel.add(binaryTF);
        panel.add(octalLabel);
        panel.add(octalTF);
        panel.add(hexadecimalLabel);
        panel.add(hexadecimalTF);
        return;
    }

    public void setButtonPanel(JPanel panel){
        convertButton = new JButton("convert");
        clearButton = new JButton("clear");
        exitButton = new JButton("exit");

        buttonsHandler = new ButtonsHandler();
        convertButton.addActionListener(buttonsHandler);
        clearButton.addActionListener(buttonsHandler);
        exitButton.addActionListener(buttonsHandler);

        panel.add(convertButton);
        panel.add(clearButton);
        panel.add(exitButton);

        return;
    }

    private class ButtonsHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == exitButton){
                System.exit(0);
            }

            if (e.getSource() == clearButton){
                decimalTF.setText("");
                binaryTF.setText("");
                octalTF.setText("");
                hexadecimalTF.setText("");
                problemDisplayer.setText("");
            }

            if (e.getSource() == convertButton){
                try{
                    if (decimalTF.getText().equals("")){
                        throw new Exception("No specified decimal number");
                    }
                    else {
                        problemDisplayer.setText("");
                        decimalInteger = Integer.parseInt(decimalTF.getText());
                        binaryTF.setText(Integer.toBinaryString(decimalInteger));
                        octalTF.setText(Integer.toOctalString(decimalInteger));
                        hexadecimalTF.setText(Double.toHexString((double) decimalInteger));
                    }
                }catch (NumberFormatException x){
                    problemDisplayer.setText("The entered integer does not follow the format of a number");
                }catch (Exception e2){
                    problemDisplayer.setText(e2.getMessage());
                }
            }
        }
    }
}
