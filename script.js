/*const form = document.getElementById('tax-form');
const resultElement = document.getElementById('result');

form.addEventListener('submit', (e) => {
    e.preventDefault();

    const age = document.getElementById('age').value.trim().toLowerCase();
    const income = parseFloat(document.getElementById('income').value);
    const deductions = parseFloat(document.getElementById('deductions').value);
    const exemptions = parseFloat(document.getElementById('exemptions').value);

    if (age !== 'yes' && age !== 'no') {
        alert('Invalid input for age. Please enter "Yes" or "No".');
        return;
    }

    if (isNaN(income) || isNaN(deductions) || isNaN(exemptions)) {
        alert('Invalid input. Please enter numerical values for income, deductions, and exemptions.');
        return;
    }

    if (income < 0 || deductions < 0 || exemptions < 0) {
        alert('Income, deductions, and exemptions cannot be negative.');
        return;
    }

    const taxableIncome = income - deductions - exemptions;

    if (taxableIncome < 0) {
        alert('Taxable income cannot be negative. Check your inputs.');
        return;
    }

    const isUnder65 = age === 'yes';
    const tax = calculateTax(taxableIncome, isUnder65);

    resultElement.textContent = `Your tax obligation is: ${tax.toFixed(2)}`;
});

function calculateTax(income, isUnder65) {
    let tax = 0.0;

    // Apply tax-free thresholds
    if (isUnder65) {
        if (income <= 95750)
      }
}

*/

const form = document.getElementById('tax-form');
const resultElement = document.getElementById('result');

form.addEventListener('submit', (e) => {
    e.preventDefault();

    const age = document.getElementById('age').value.trim().toLowerCase();
    const income = parseFloat(document.getElementById('income').value);
    const deductions = parseFloat(document.getElementById('deductions').value);
    const exemptions = parseFloat(document.getElementById('exemptions').value);

    if (age !== 'yes' && age !== 'no') {
        alert('Invalid input for age. Please enter "Yes" or "No".');
        return;
    }

    if (isNaN(income) || isNaN(deductions) || isNaN(exemptions)) {
        alert('Invalid input. Please enter numerical values for income, deductions, and exemptions.');
        return;
    }

    if (income < 0 || deductions < 0 || exemptions < 0) {
        alert('Income, deductions, and exemptions cannot be negative.');
        return;
    }

    const taxableIncome = income - deductions - exemptions;

    if (taxableIncome < 0) {
        alert('Taxable income cannot be negative. Check your inputs.');
        return;
    }

    const isUnder65 = age === 'yes';
    const tax = calculateTax(taxableIncome, isUnder65);

    resultElement.textContent = `Your tax obligation is: $${tax.toFixed(2)}`;
});

function calculateTax(income, isUnder65) {
    let tax = 0.0;

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