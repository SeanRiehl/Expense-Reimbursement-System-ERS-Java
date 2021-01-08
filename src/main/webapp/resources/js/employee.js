/**
 * JavaScript file for the employee page
 */
// Select the head and body of the table
let tableHead = document.querySelector(".tableHead")
let tableBody = document.querySelector(".tableBody")

// Select the radio button elements and selection
let radioButtons = document.getElementsByName('RType');
let selection = "";

// Load everything when the window finishes loading
window.onload = function () {
    document
        .getElementById('reimbursementSubmit')
        .addEventListener('click', addReimbursement);
    document
        .getElementById('employeeSubmit')
        .addEventListener('click', getReimbursements);
}

// Clear out the input boxes
document.querySelector("#form-holder").onsubmit = function(e) {
    e.preventDefault();
}

// AJAX stuff
function addReimbursement() {
    let amount = document.querySelector("#Amount").value;
    let description = document.querySelector("#Description").value;
    let type;

    // See which radio button is hit
    for (let i = 0, length = radioButtons.length; i < length; i++) {
        if (radioButtons[i].checked) {
            selection = radioButtons[i].value;

            switch (selection) {
                case "LODGING":
                    type = 0;
                    break;
                case "TRAVEL":
                    type = 1;
                    break;
                case "FOOD":
                    type = 2;
                    break;
                case "OTHER":
                    type = 3;
                    break;
                default:
                    console.log("UNKNOWN TYPE");
            }
        }
    }

    fetch(`http://localhost:9001/Project1/JSON/send?amount=${amount}&description=${description}&type=${type}`)
        .then(() => {
            document.querySelector("#form-holder").reset();
        })
}

// AJAX stuff
function getReimbursements() {
    // Step 1: Create an XMLHtppRequest object
    let xhttp = new XMLHttpRequest();

    // Step 2: Create the callback function for the readystate changes
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let reimbursement = JSON.parse(xhttp.responseText);
            // console.log(reimbursement);

            domManipulation(reimbursement);
        }
    }

    // Step 3: Create and open a connection
    xhttp.open("GET", 'http://localhost:9001/Project1/JSON/reimbursementByAuthor');

    // Step 4: Send the request
    xhttp.setRequestHeader('Content-type', 'application/json');
    xhttp.send();
}

// DOM manipulation stuff
domManipulation = (inputJSON) => {
    // Erase the initial table header data
    tableHead.innerHTML = '';

    // Add the table headers
    tableHead.insertAdjacentHTML('beforeEnd', `
    <tr>
	    <th scope="col">ID</th>
		<th scope="col">Amount</th>
		<th scope="col">Submitted Date</th>
		<th scope="col">Resolved Date</th>
		<th scope="col">Description</th>
		<th scope="col">Author</th>
		<th scope="col">Resolver</th>
		<th scope="col">Status</th>
		<th scope="col">Type</th>
	</tr>
    `)

    // Erase the initial table body data
    tableBody.innerHTML = '';

    // Loop through the input JSON array and add the information
    inputJSON.forEach(item => {
        tableBody.insertAdjacentHTML('beforeEnd', `
    <tr>
        <th scope="row">${item.reimb_id}</th>
        <td>${item.amount.toFixed(2)}</td>
        <td>${new Date(item.submitted).toLocaleString()}</td>
        <td>${(item.resolved == null) ? "" : new Date(item.resolved).toLocaleString()}</td>
        <td>${item.description}</td>
        <td>${item.author}</td>
        <td>${(item.resolver == 0) ? "" : item.resolver}</td>
        <td class=${statusConversion(item.status_id).toLowerCase()}>${statusConversion(item.status_id)}</td>
        <td>${typeConversion(item.type_id)}</td>
    </tr>
    `)
    })
}

// Change the status integers to their string equivalents
function statusConversion(inputInt) {
    switch (inputInt) {
        case 0:
            return "PENDING";
        case 1:
            return "APPROVED";
        case 2:
            return "DENIED";
        default:
            return "UNKNOWN STATUS";
    }
}

// Change the type integers to their string equivalents
function typeConversion(inputInt) {
    switch (inputInt) {
        case 0:
            return "LODGING";
        case 1:
            return "TRAVEL";
        case 2:
            return "FOOD";
        case 3:
            return "OTHER";
        default:
            return "UNKNOWN TYPE";
    }
}
