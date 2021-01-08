// Fun fact, you can assign multiple variables in one go like below #bars
let pendingR = [], approvedR = [], deniedR = [], form = document.querySelector(".form-select"), table = document.querySelector(".body")

// Pretend this is data from the DB you got back as json after an ajx request
const dataFromDb = [
    {
        id: 1,
        reimbursementType: "Other",
        amount: 57,
        details: "Other",
        status: "Approved"
    },
    {
        id: 2,
        reimbursementType: "Food",
        amount: 12.34,
        details: "I bought some veggie burgers",
        status: "Pending"
    },
    {
        id: 3,
        reimbursementType: "Lodging",
        amount: 17,
        details: "Denied",
        status: "Denied"
    },
    {
        id: 4,
        reimbursementType: "Other",
        amount: 20,
        details: "I had to take a client on a date",
        status: "Approved"
    },
]

// Filter into individual array based on status type
dataFromDb.filter(reimbursement => { // FILTER CREATES A NEW ARRAY BASED ON A CONDITION
    if (reimbursement.status == "Denied") {
        deniedR.push(reimbursement);
    } else if (reimbursement.status == "Approved") {
        approvedR.push(reimbursement);
    } else {
        pendingR.push(reimbursement); // ADD ANOTHER ELSE IF FOR ERROR CHECKING
    }
})

// Creating html to append to table
dataToDisplay = (arr) => {
    // Each time it is called, erase previous html
    table.innerHTML = '';
    // Loop through array and replace with this 
    arr.forEach(item => {
        table.insertAdjacentHTML('beforeEnd', `
    <tr>
      <th scope="row">${item.id}</th>
      <td>${item.reimbursementType}</td>
      <td>$${item.amount}</td> 
      <td>${item.details}</td>
      <td class="status">${item.status}</td>
    </tr>
    `)  // FIND A WAY TO ROUND DOLLAR AMOUNTS TO TWO PLACES
    })
}

// This function is the actual function that shows data, it takes an input value
showData = (filterValue) => {
    switch (filterValue) {
        case "Pending":
            dataToDisplay(pendingR);
            break;
        case "Denied":
            dataToDisplay(deniedR);
            break;
        case "Approved":
            dataToDisplay(approvedR);
            break;
        default:
            dataToDisplay(dataFromDb);
    }
}

// Depending on which option you select, function will update table using showData
form.addEventListener('change', (e) => {
    switch (parseInt(e.target.value)) {
        case 1:
            showData("All");
            break;
        case 2:
            showData("Approved");
            break;
        case 3:
            showData("Pending");
            break;
        case 4:
            showData("Denied");
            break;
            deault:
            showData("All");
    }
})

// Want it to run initially with all values
showData("All"); // REMOVE THIS PART SO IT DOES NOT SHOW ANYTHING AT FIRST