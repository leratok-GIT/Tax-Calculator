package taxcalculator;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
        JLabel ageLabel = new JLabel("Enter your age:");
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
            int age = Integer.parseInt(ageField.getText().trim());

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

            double tax = calculateTax(taxableIncome, age);

            resultLabel.setText(String.format("Your tax obligation is: %.2f", tax));

        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter \n numerical values for age, income, \n deductions, and exemptions.");
        } catch (IllegalArgumentException e) {
            resultLabel.setText(e.getMessage());
        }
    }

    public static double calculateTax(double income, int age) {
        double tax = 0.0;

        // Apply tax-free thresholds based on age
        if (age < 65) {
            if (income <= 95750) {
                return 0.0;
            } else {
                income -= 95750;
            }
        } else if (age >= 65 && age <= 75) {
            if (income <= 148217) {
                return 0.0;
            } else {
                income -= 148217;
            }
        } else {
            if (income <= 165689) {
                return 0.0;
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
