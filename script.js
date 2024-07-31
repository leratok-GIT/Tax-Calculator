const ageInput = document.getElementById('age');
const incomeInput = document.getElementById('income');
const deductionsInput = document.getElementById('deductions');
const exemptionsInput = document.getElementById('exemptions');
const calculateButton = document.getElementById('calculate-button');
const resultElement = document.getElementById('result');

calculateButton.addEventListener('click', calculateTax);

function calculateTax() {
    const age = parseInt(ageInput.value);
    const income = parseFloat(incomeInput.value);
    const deductions = parseFloat(deductionsInput.value);
    const exemptions = parseFloat(exemptionsInput.value);

    if (isNaN(age) || isNaN(income) || isNaN(deductions) || isNaN(exemptions)) {
        resultElement.textContent = 'Invalid input. Please enter numerical values.';
        return;
    }

    const taxableIncome = income - deductions - exemptions;

    if (taxableIncome < 0) {
        resultElement.textContent = 'Taxable income cannot be negative. Check your inputs.';
        return;
    }

    let tax = 0.0;

    // Apply tax-free thresholds based on age
    if (age < 65) {
        if (taxableIncome <= 833000) {
            tax = 0.0;
        } else {
            taxableIncome -= 833000;
        }
    } else if (age >= 65 && age <= 75) {
        if (taxableIncome <= 1163000) {
            tax = 0.0;
        } else {
            taxableIncome -= 1163000;
        }
    } else {
        if (taxableIncome <= 1298500) {
            tax = 0.0;
        } else {
            taxableIncome -= 1298500;
        }
    }

    // Apply tax brackets
    if (taxableIncome <= 216000) {
        tax = 0.18 * taxableIncome;
    } else if (taxableIncome <= 337800) {
        tax = 38880 + 0.26 * (taxableIncome - 216000);
    } else if (taxableIncome <= 467500) {
        tax = 70440 + 0.31 * (taxableIncome - 337800);
    } else if (taxableIncome <= 613600) {
        tax = 110760 + 0.36 * (taxableIncome - 467500);
    } else if (taxableIncome <= 782200) {
        tax = 163096 + 0.39 * (taxableIncome - 613600);
    } else if (taxableIncome <= 1656600) {
        tax = 239952 + 0.41 * (taxableIncome - 782200);
    } else {
        tax = 614992 + 0.45 * (taxableIncome - 1656600);
    }

    resultElement.textContent = `Your tax obligation is: R${tax.toFixed(2)}`;
}