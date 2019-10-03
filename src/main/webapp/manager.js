'use strict';

const BASE_URL = "/Project01/manager";
const VIEW_ALL = `${BASE_URL}/view_all`;
const VIEW_PENDING = `${BASE_URL}/view_pending`;
const VIEW_RES = `${BASE_URL}/view_res`;
const VIEW_BY_EMP = `${BASE_URL}/view_by_emp`;
const APPROVE_DENY = `${BASE_URL}/approve_deny`;

let viewAll = document.getElementById("view-all-employees");
let employeeDisplay = document.getElementById("employee-display");
let viewPending = document.getElementById("view-all-pending-reimbursements");
let pendingDisplay = document.getElementById("pending-display");
let viewRes = document.getElementById("view-all-resolved-reimbursements");
let resolvedDisplay = document.getElementById("resolved-display");
let viewByEmp = document.getElementById("reimbursement-requests-by-employee");
let approveDeny = document.getElementById("approve-deny-request");

let createEmployeeLi = (user) => {
    let li = document.createElement("li");
    li.innerText = `First Name: ${user.firstName}
    	Last Name: ${user.lastName}
    	Email: ${user.email}
    	Address 1: ${user.address}
    	Address 2: ${user.address2}
    	Phone Number: ${user.phoneNumber}
    	Position: ${user.position}`;
    employeeDisplay.append(li);
}

viewAll.addEventListener("click", (event) => {
    fetch(VIEW_ALL, { method: "GET" })
        .then((response) => {
            return response.json();
        })
        .then((usersJson) => {
            clearEmployeeDisplay();
            for (let employee in usersJson) {
                createEmployeeLi(usersJson[employee]);
            }
        })
        .catch(console.error);
});

viewPending.addEventListener("click", (event) => {
    fetch(VIEW_PENDING, { method: "GET" })
        .then((response) => {
            return response.json();
        })
        .then((usersJson) => {
            clearPendingDisplay();
            createPendingLi(usersJson);
        })
        .catch(console.error);
});

viewRes.addEventListener("click", (event) => {
    fetch(VIEW_RES, { method: "GET" })
        .then((response) => {
            return response.json();
        })
        .then((usersJson) => {
            clearResolvedDisplay();
            createResolvedLi(usersJson);
        })
        .catch(console.error);
});

let clearEmployeeDisplay = () => {
    employeeDisplay.innerHTML = "";
}

let clearPendingDisplay = () => {
    pendingDisplay.innerHTML = "";
}

let clearResolvedDisplay = () => {
    resolvedDisplay.innerHTML = "";
}