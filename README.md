# Tax-Calculator
The system calculator tax obligation for an individual given his/her annual income.

## Overview
 
The Tax Calculator System is a Java-based application designed to calculate the tax obligation for individuals based on their annual income, deductions, and exemptions. The application includes a graphical user interface (GUI) built with Swing, which allows users to input their data and view the calculated tax obligation. The system adheres to the South African Revenue Service (SARS) tax thresholds for different age groups.
 
## Features
 
- Input fields for age, annual income, deductions, and exemptions.
- Calculates tax obligation based on SARS tax thresholds:
  - R95,750 for those younger than 65 years.
  - R148,217 for those aged 65 to below 75 years.
  - R165,689 for taxpayers aged 75 years and older.
- Error handling for invalid inputs.
- Option to perform multiple calculations without restarting the application.
- User-friendly GUI with colored components for better visual appeal.
 
## Installation
 
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/tax-calculator-system.git
    ```
2. Navigate to the project directory:
    ```bash
    cd tax-calculator-system
    ```
3. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse or NetBeans).
 
## Usage
 
1. Run the `TaxCalculatorGUI` class to start the application.
2. Input your age.
3. Enter your annual income, deductions, and exemptions.
4. Click the "Calculate Tax" button to view your tax obligation.
5. A dialog box will prompt you to calculate again or exit the application.
 
## Code Structure
 
- **`TaxCalculator.java`**: Main class that sets up the GUI and handles user inputs and tax calculations.
- **`calculateTax(double income, boolean isUnder65)`**: Method to calculate the tax based on the income and age group.
 
## Example
 
1. Input: 
    - What is your age? `65`
    - Annual income: `200000`
    - Deductions: `20000`
    - Exemptions: `5000`
2. Output:
    - Your tax obligation is: `R4820.94`

# What you will see:
![image](https://github.com/user-attachments/assets/b3a6bae2-400a-445e-a210-cfdae0a20a76)


# The validation:
![Screenshot (14)](https://github.com/user-attachments/assets/a011f8f6-73dc-4910-8a12-a2f1bf99790d)


## Contributing
 
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.
 
## License
 
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
 

