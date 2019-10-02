'use strict'

const BASE_URL = "/Project01/employee";
const VIEW_INFO = `${BASE_URL}/view_info`;
const UPDATE_INFO = `${BASE_URL}/update_info`;
const VIEW_RES = `${BASE_URL}/view_res`;
const VIEW_PENDING = `${BASE_URL}/view_pending`;
const CREATE_NEW = `${BASE_URL}/create_new`;

let viewInfo = document.getElementById("view-info");
let infoDisplay = document.getElementById("info-display");
let viewMyPendingReimbursements = document.getElementById("view-my-pending-reimbursements");
let pendingDisplay = document.getElementById("pending-display");
let viewMyResolvedReimbursements = document.getElementById("view-my-resolved-reimbursements");
let resolvedDisplay = document.getElementById("resolved-display");
let submitReimbursement = document.getElementById("sumbit-reimbursement");
let updateUserInfo = document.getElementById("update-user-info");

viewInfo.addEventListener("click", (event)=>{
	fetch(VIEW_INFO, {method: "GET" })
		.then((response)=>{
			return response.json();
		})
		.then((usersJson)=>{
			//clearInfoDisplay();
			for(let user in usersJson) {
				createLi(usersJson[user]);
			}
		})
		.catch(console.error);
});

viewMyResolvedReimbursements.addEventListener("click", (event)=>{
	fetch(VIEW_PENDING, {method: "GET" })
		.then((response)=>{
			return response.json();
		})
		.then((transactionsJSON)=>{
			clearPendingDisplay();
			for(let transaction in transactionsJson) {
				createLi(transactionsJson[transaction]);
			}
		})
});

viewMyPendingReimbursements.addEventListener("click", (event)=>{
	fetch(VIEW_RES, {method: "GET" })
		.then((response)=>{
			return response.json();
		})
		.then((transactionsJSON)=>{
			clearResDisplay();
			for(let transaction in transactionsJson) {
				createLi(transactionsJson[transaction]);
			}
		})
});

submitReimbursement.addEventListener("submit", (event)=>{
	  event.preventDefault();

	  fetch(CREATE_NEW, {method: "POST"})
	      .then((response)=>{
	        return response.json();
	      })
	      /*.then((playerJson)=>{
	        clearSubDisplay();
	      })*/
	});

updateUserInfo.addEventListener("submit", (event)=>{
	event.preventDefault();
	
	fetch(CREATE_NEW, {method: "POST"})
		.then((response)=>{
			return response.json();
		})
		/*.then((playerJson)=>{
			clearDisplay
		})*/
})

let createLi = (employee) => {
	let li = document.createELement("li");
	li.innerText = `${employee.firstName}`;
	infoDisplay.appendChild(li);
}

let clearInfoDisplay = ()=>{
	infoDisplay.innerHTML = "";
}

let clearPendingDisplay = ()=>{
	pendingDisplay.innerHTML = "";
}

let clearResDisplay = ()=>{
	resolvedDisplay.innerHTML = "";
}

/*let clearSubDisplay = ()=>{
	subDisplay.innerHTML = "";
}

let clearUpdateDisplay = ()=>{
	updateDisplay.innerHTML = "";
}
*/

