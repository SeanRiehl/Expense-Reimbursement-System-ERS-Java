/**
 * JavaScript file for the financial manager page
 */
// Select the head and body of the table
let tableHead = document.querySelector(".tableHead")
let tableBody = document.querySelector(".tableBody")

// Select the radio button elements and selection
let radioButtonsUpdate = document.getElementsByName('RUpdate');
let radioButtonsStatus = document.getElementsByName('RStatus');
let selection = "";

// Create the arrays for every reimbursement type
let pendingR = [], approvedR = [], deniedR = [];

// Load everything when the window finishes loading
window.onload = function () {
    document
        .getElementById('reimbursementSubmit')
        .addEventListener('click', updateReimbursements);
    document
        .getElementById('financialManagerSubmit')
        .addEventListener('click', getReimbursements);
}

// Clear out the input boxes
document.querySelector("#form-holder").onsubmit = function(e) {
    e.preventDefault();
}

// AJAX stuff
function updateReimbursements() {
    let reimb_id = document.querySelector("#reimb_id").value;
    let author = document.querySelector("#author").value;
    let status_id;

    // See which radio button is hit
    for (let i = 0, length = radioButtonsUpdate.length; i < length; i++) {
        if (radioButtonsUpdate[i].checked) {
            selection = radioButtonsUpdate[i].value;

            switch (selection) {
                case "APPROVE":
                    status_id = 1;
                    break;
                case "DENY":
                    status_id = 2;
                    break;
                default:
                    console.log("UNKNOWN STATUS");
            }
        }
    }

    console.log(reimb_id, author, status_id);
    fetch(`http://localhost:9001/Project1/JSON/update?reimb_id=${reimb_id}&author=${author}&status_id=${status_id}`)
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
            let reimbursements = JSON.parse(xhttp.responseText);
            // console.log(reimbursements);

            // Erase any previous data in the arrays
            pendingR = [];
            approvedR = [];
            deniedR = [];

            // Filter the input JSON data into the corresponding arrays
            reimbursements.filter(reimbursement => {
                if (reimbursement.status_id == 0) { // Pending
                    pendingR.push(reimbursement);
                } else if (reimbursement.status_id == 1) { // Approved
                    approvedR.push(reimbursement);
                } else if (reimbursement.status_id == 2) { // Denied
                    deniedR.push(reimbursement);
                } else { // Unknown
                    console.log("UNKNOWN REIMBURSEMENT STATUS");
                    console.log(reimbursement);
                }
            })

            // See which radio button is hit
            for (let i = 0, length = radioButtonsStatus.length; i < length; i++) {
                if (radioButtonsStatus[i].checked) {
                    selection = radioButtonsStatus[i].value;

                    switch (selection) {
                        case "All":
                            return domManipulation(reimbursements);
                        case "Pending":
                            return domManipulation(pendingR);
                        case "Approved":
                            return domManipulation(approvedR);
                        case "Denied":
                            return domManipulation(deniedR);
                        default:
                            console.log("UNKNOWN SELECTION");
                    }
                }
            }
        }
    }

    // Step 3: Create and open a connection
    xhttp.open("GET", 'http://localhost:9001/Project1/JSON/reimbursement');

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
