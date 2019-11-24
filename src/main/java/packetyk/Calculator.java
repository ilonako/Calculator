package packetyk;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    private JTextField resultsTextField;
    private JButton clearButton;
    private JButton sevenButton;
    private JButton eightButton;
    private JButton nineButton;
    private JButton sixButton;
    private JButton fiveButton;
    private JButton fourButton;
    private JButton threeButton;
    private JButton twoButton;
    private JButton oneButton;
    private JButton divideButton;
    private JButton multiplyButton;
    private JButton minusButton;
    private JButton plusButton;
    private JButton equalsButton;
    private JButton dotButton;
    private JButton zeroButton;
    private JButton percentButton;
    private JButton signButton;
    private JPanel calculatorView;
    private Double leftOperand;
    private Double rightOperand;
    private OperationType calcOperation;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().calculatorView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Calculator() {

        sevenButton.addActionListener(new NumberBtnClicked(sevenButton.getText()));
        eightButton.addActionListener(new NumberBtnClicked(eightButton.getText()));
        nineButton.addActionListener(new NumberBtnClicked(nineButton.getText()));
        fourButton.addActionListener(new NumberBtnClicked(fourButton.getText()));
        fiveButton.addActionListener(new NumberBtnClicked(fiveButton.getText()));
        sixButton.addActionListener(new NumberBtnClicked(sixButton.getText()));
        oneButton.addActionListener(new NumberBtnClicked(oneButton.getText()));
        twoButton.addActionListener(new NumberBtnClicked(twoButton.getText()));
        threeButton.addActionListener(new NumberBtnClicked(threeButton.getText()));
        zeroButton.addActionListener(new NumberBtnClicked(zeroButton.getText()));

        percentButton.addActionListener(new OperationBtnClicked(OperationType.PERCENTAGE));
        multiplyButton.addActionListener(new OperationBtnClicked(OperationType.MULTIPLICATION));
        divideButton.addActionListener(new OperationBtnClicked(OperationType.DIVISION));
        minusButton.addActionListener(new OperationBtnClicked(OperationType.SUBTRACTION));
        plusButton.addActionListener(new OperationBtnClicked(OperationType.ADDITION));
        equalsButton.addActionListener(new EqualBtnClicked());
        clearButton.addActionListener(new ClearBtnClicked());
        signButton.addActionListener(new SignBtnClicked());
        dotButton.addActionListener(new DigitBtnClicked());
    }

    private class NumberBtnClicked implements ActionListener {

        private String value;

        NumberBtnClicked(String value) {
            this.value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(leftOperand == null || leftOperand == 0.0) {
                value = resultsTextField.getText() + value;
            }else{
                rightOperand = Double.valueOf(value);
            }
            resultsTextField.setText(value);

        }
    }

    private class OperationBtnClicked implements ActionListener {

        private OperationType operation;

        OperationBtnClicked(OperationType operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            calcOperation = operation;
            leftOperand = Double.valueOf(resultsTextField.getText());
        }
    }

    private class ClearBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            resultsTextField.setText("");
            leftOperand = 0.0;
            rightOperand = 0.0;
        }
    }

    private class DigitBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            resultsTextField.setText(resultsTextField.getText() + ".");

        }
    }

    private class EqualBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Double output = calcOperation.getOperator().applyAsDouble(leftOperand, rightOperand);
            resultsTextField.setText(output%1==0?String.valueOf(output.intValue()):String.valueOf(output));
            leftOperand = 0.0;
            rightOperand = 0.0;
        }
    }

    private class SignBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            resultsTextField.setText("-"+ resultsTextField.getText());
        }
    }
}
