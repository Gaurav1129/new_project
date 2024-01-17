// Function to get quantity from user
function getQuantity(productName) {
    return parseInt(prompt(`Enter the quantity of ${productName}:`), 10);
}

// Function to get gift wrap status from user
function getGiftWrapStatus() {
    return prompt("Is this product wrapped as a gift? (yes/no)").toLowerCase() === 'yes';
}

// Function to calculate total amount for a product
function calculateTotal(price, quantity, giftWrap) {
    let totalAmount = price * quantity;
    if (giftWrap) {
        totalAmount += quantity; // Gift wrap fee: $1 per unit
    }
    return totalAmount;
}

// Function to calculate discount based on rules
function calculateDiscount(subtotal, quantityA, quantityB, quantityC) {
    const flat10Discount = (subtotal > 200) ? 10 : 0;
    const bulk5Discount = (quantityA > 10 || quantityB > 10 || quantityC > 10) ? subtotal * 0.05 : 0;
    const bulk10Discount = (quantityA + quantityB + quantityC > 20) ? subtotal * 0.10 : 0;
    const tiered50Discount = (quantityA + quantityB + quantityC > 30 && quantityA > 15) ? subtotal * 0.50 : 0;

    return Math.max(Math.max(flat10Discount, bulk5Discount), Math.max(bulk10Discount, tiered50Discount));
}

// Function to calculate shipping fee
function calculateShippingFee(quantityA, quantityB, quantityC) {
    const totalUnits = quantityA + quantityB + quantityC;
    return Math.ceil(totalUnits / 10) * 5; // 10 units per package, $5 per package
}

// Function to calculate gift wrap fee
function calculateGiftWrapFee(quantityA, quantityB, quantityC) {
    return quantityA + quantityB + quantityC; // Gift wrap fee: $1 per unit
}

// Function to display product details
function displayDetails(productName, quantity, totalAmount) {
    console.log(`${productName}: Quantity: ${quantity}, Total: $${totalAmount}`);
}

// Main program
function main() {
    // Product prices
    const productAPrice = 20;
    const productBPrice = 40;
    const productCPrice = 50;

    // Quantity and gift wrap status for each product
    const quantityA = getQuantity("Product A");
    const giftWrapA = getGiftWrapStatus();

    const quantityB = getQuantity("Product B");
    const giftWrapB = getGiftWrapStatus();

    const quantityC = getQuantity("Product C");
    const giftWrapC = getGiftWrapStatus();

    // Calculate totals
    const totalAmountA = calculateTotal(productAPrice, quantityA, giftWrapA);
    const totalAmountB = calculateTotal(productBPrice, quantityB, giftWrapB);
    const totalAmountC = calculateTotal(productCPrice, quantityC, giftWrapC);

    const subtotal = totalAmountA + totalAmountB + totalAmountC;

    // Apply discounts
    const discountAmount = calculateDiscount(subtotal, quantityA, quantityB, quantityC);

    // Calculate shipping and gift wrap fees
    const shippingFee = calculateShippingFee(quantityA, quantityB, quantityC);
    const giftWrapFee = calculateGiftWrapFee(quantityA, quantityB, quantityC);

    // Calculate the final total
    const total = subtotal - discountAmount + shippingFee + giftWrapFee;

    // Display the details
    displayDetails("Product A", quantityA, totalAmountA);
    displayDetails("Product B", quantityB, totalAmountB);
    displayDetails("Product C", quantityC, totalAmountC);

    console.log(`\nSubtotal: $${subtotal}`);
    console.log(`Discount Applied: $${discountAmount}`);
    console.log(`Shipping Fee: $${shippingFee}`);
    console.log(`Gift Wrap Fee: $${giftWrapFee}`);
    console.log(`Total: $${total}`);
}

// Run the main program
main();
