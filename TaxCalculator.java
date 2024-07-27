package taxcalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TaxCalculator extends JFrame {

    private final JTextField ageField;
    private final JTextField incomeField;
    private final JTextField deductionsField;
    private final JTextField exemptionsField;
    private final JLabel resultLabel;

    public TaxCalculator() {
        // Set up the frame
        setTitle("Tax Calculator");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));
        getContentPane().setBackground(new Color(220, 220, 220)); // Light gray background

        // Create components with colors
        JLabel ageLabel = new JLabel("Are you younger than 65 years? (Yes/No)");
        ageLabel.setForeground(Color.BLUE);
        ageField = new JTextField();
        ageField.setBackground(Color.WHITE);

        JLabel incomeLabel = new JLabel("Enter your annual income:");
        incomeLabel.setForeground(Color.BLUE);
        incomeField = new JTextField();
        incomeField.setBackground(Color.WHITE);

        JLabel deductionsLabel = new JLabel("Enter your deductions:");
        deductionsLabel.setForeground(Color.BLUE);
        deductionsField = new JTextField();
        deductionsField.setBackground(Color.WHITE);

        JLabel exemptionsLabel = new JLabel("Enter your exemptions:");
        exemptionsLabel.setForeground(Color.BLUE);
        exemptionsField = new JTextField();
        exemptionsField.setBackground(Color.WHITE);

        JButton calculateButton = new JButton("Calculate Tax");
        calculateButton.setBackground(Color.BLUE);
        calculateButton.setForeground(Color.white);

        resultLabel = new JLabel("Your tax obligation is: ");
        resultLabel.setForeground(Color.black);

        // Add components to the frame
        add(ageLabel);
        add(ageField);
        add(incomeLabel);
        add(incomeField);
        add(deductionsLabel);
        add(deductionsField);
        add(exemptionsLabel);
        add(exemptionsField);
        add(calculateButton);
        add(resultLabel);

        // Add action listener to the button
        calculateButton.addActionListener((ActionEvent e) -> {
            calculateTax();
        });
    }

    private void calculateTax() {
        try {
            // Get and validate inputs
            String ageResponse = ageField.getText().trim().toLowerCase();
            if (!ageResponse.equals("yes") && !ageResponse.equals("no")) {
                throw new IllegalArgumentException("Invalid input for age. Please enter 'Yes' or 'No'.");
            }

            double annualIncome = Double.parseDouble(incomeField.getText().trim());
            double deductions = Double.parseDouble(deductionsField.getText().trim());
            double exemptions = Double.parseDouble(exemptionsField.getText().trim());

            if (annualIncome < 0 || deductions < 0 || exemptions < 0) {
                throw new IllegalArgumentException("Income, deductions, and exemptions cannot be negative.");
            }

            double taxableIncome = annualIncome - deductions - exemptions;

            if (taxableIncome < 0) {
                throw new IllegalArgumentException("Taxable income cannot be negative. Check your inputs.");
            }

            boolean isUnder65 = ageResponse.equals("yes");
            double tax = calculateTax(taxableIncome, isUnder65);

            resultLabel.setText(String.format("Your tax obligation is: %.2f", tax));

        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter numerical values for income, deductions, and exemptions.");
        } catch (IllegalArgumentException e) {
            resultLabel.setText(e.getMessage());
        }
    }

    public static double calculateTax(double income, boolean isUnder65) {
        double tax = 0.0;

        // Apply tax-free thresholds
        if (isUnder65) {
            if (income <= 95750) {
                return 0.0;
            } else {
                income -= 95750;
            }
        } else {
            if (income <= 148217) {
                return 0.0;
            } else if (income <= 165689) {
                income -= 148217;
            } else {
                income -= 165689;
            }
        }

        // Apply tax brackets
        if (income <= 195850) {
            tax = 0.18 * income;
        } else if (income <= 305850) {
            tax = 35253 + 0.26 * (income - 195850);
        } else if (income <= 423300) {
            tax = 63853 + 0.31 * (income - 305850);
        } else if (income <= 555600) {
            tax = 100263 + 0.36 * (income - 423300);
        } else if (income <= 708310) {
            tax = 147891 + 0.39 * (income - 555600);
        } else if (income <= 1500000) {
            tax = 207448 + 0.41 * (income - 708310);
        } else {
            tax = 532041 + 0.45 * (income - 1500000);
        }

        return tax;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TaxCalculator().setVisible(true);
        });
    }
}
