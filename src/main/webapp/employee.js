'use strict';

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


let clearInfoDisplay = () => {
    infoDisplay.innerHTML = "";
}

let clearPendingDisplay = () => {
    pendingDisplay.innerHTML = "";
}

let clearResDisplay = () => {
    resolvedDisplay.innerHTML = "";
}

let createInfoLi = (user) => {
    let li = document.createElement("li");
    li.innerText = `First Name: ${user.firstName}
    	Last Name: ${user.lastName}
    	Email: ${user.email}
    	Address 1: ${user.address}
    	Address 2: ${user.address2}
    	Phone Number: ${user.phoneNumber}
    	Position: ${user.position}`;
    infoDisplay.append(li);
}

let createPendingLi = (trs) => {
    let li = document.createElement("li");
    li.innerText = `Reimbursement Identification Number: ${trs.id}
    	Status: ${trs.status}
    	Reimbursement Date: ${trs.transDate}
    	Receipt Img: ${trs.imgUrl}
    	Your Comment: ${trs.comment}`;
    pendingDisplay.append(li);
}

let createResolvedLi = (trs) => {
    let li = document.createElement("li");
    li.innerText = `Reimbursement Identification Number: ${trs.id}
    	Status: ${trs.status}
    	Reimbursement Date: ${trs.transDate}
    	Receipt Img: ${trs.imgUrl}
    	Your Comment: ${trs.comment}`;
    resolvedDisplay.append(li);
}

viewInfo.addEventListener("click", (event) => {
    fetch(VIEW_INFO, { method: "GET" })
        .then((response) => {
            return response.json();
        })
        .then((usersJson) => {
            clearInfoDisplay();
            createInfoLi(usersJson);
        })
        .catch(console.error);
});

viewMyPendingReimbursements.addEventListener("click", (event) => {
    fetch(VIEW_PENDING, { method: "GET" })
        .then((response) => {
            return response.json();
        })
        .then((trsJson) => {
            clearPendingDisplay();
            console.log(trsJson);
            for (let transaction in trsJson) {
                createPendingLi(trsJson[transaction]);
            }
        })
        .catch(console.error)
});

viewMyResolvedReimbursements.addEventListener("click", (event) => {
    fetch(VIEW_RES, { method: "GET" })
        .then((response) => {
            return response.json();
        })
        .then((trsJson) => {
            clearResDisplay();
            for (let transaction in trsJson) {
                createResolvedLi(trsJson[transaction]);
            }
        })
});

//submitReimbursement.addEventListener("submit", (event) => {
//    event.preventDefault();
//
//    fetch(CREATE_NEW, { method: "POST" })
//        .then((response) => {
//            return response.json();
//        })
//        .then((playerJson)=>{
//          clearSubDisplay();
//        })
//});

updateUserInfo.addEventListener("submit", (event) => {
    event.preventDefault();

    fetch(UPDATE_INFO, { method: "POST", body: JSON.stringify(employeeFromForm(updateUserInfo)) })
        .then((response) => {
            if (response.status >= 200 && response.status < 300) {
                alert("Employee info updated");
            } else {
                alert("Failed to update employee info");
            }
        })
        // .then((playerJson) => {
        //     clearUpdateDisplay
        // })
});

let employeeFromForm = (form) => {
    let user = {};
    user.firstName = form.firstName.value;
    user.lastName = 'a';
    user.password = 'wasspord';
    user.address = 'a';
    user.address2 = 'a';
    user.phoneNumber = 'a';
    return user;
}

/*let clearSubDisplay = ()=>{
	subDisplay.innerHTML = "";
}*/

let clearUpdateDisplay = () => {
    updateDisplay.innerHTML = "";
}