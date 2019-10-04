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
let viewByEmpDisplay = document.getElementById("view-by-employee-display");
let approveDeny = document.getElementById("approve-deny-request");

let createEmployeeLi = (user) => {
    let li = document.createElement("li");
    li.innerText = `Employee id: ${user.id}
    	First Name: ${user.firstName}
    	Last Name: ${user.lastName}
    	Email: ${user.email}
    	Address 1: ${user.address}
    	Address 2: ${user.address2}
    	Phone Number: ${user.phoneNumber}
    	Position: ${user.position}`;
    employeeDisplay.append(li);
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
    	Manager Name: ${trs.managerName}
    	Reimbursement Date: ${trs.transDate}
    	Receipt Img: ${trs.imgUrl}
    	Your Comment: ${trs.comment}`;
    resolvedDisplay.append(li);
}

let createViewByEmpLi = (trs) => {
    let li = document.createElement("li");
    li.innerText = `Reimbursement Identification Number: ${trs.id}
    	Status: ${trs.status}
    	Manager Name: ${trs.managerName}
    	Reimbursement Date: ${trs.transDate}
    	Receipt Img: ${trs.imgUrl}
        Your Comment: ${trs.comment}`;
    viewByEmpDisplay.append(li);
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
        .then((trsJson) => {
            clearPendingDisplay();
            for (let transaction in trsJson) {
                createPendingLi(trsJson[transaction]);
            }
        })
        .catch(console.error);
});

viewRes.addEventListener("click", (event) => {
    fetch(VIEW_RES, { method: "GET" })
        .then((response) => {
            return response.json();
        })
        .then((trsJson) => {
            clearResolvedDisplay();
            for (let transaction in trsJson) {
                createResolvedLi(trsJson[transaction]);
            }
        })
        .catch(console.error);
});

approveDeny.addEventListener("submit", (event) => {
    event.preventDefault();
    fetch(APPROVE_DENY, { method: "POST", body: JSON.stringify(approveDenyForm(approveDeny)) })
        .then((response) => {
            if (response.status >= 200 && response.status < 300) {
                alert("Successfully updated");
            } else {
                alert("Failed to update :(");
            }
        })
});

viewByEmp.addEventListener("submit", (event) => {
    event.preventDefault();
    fetch(VIEW_BY_EMP, { method: "POST", body: JSON.stringify(idForm(viewByEmp)) })
        .then((response) => {
            console.log(response);
            if (response.status >= 200 && response.status < 300) {
                System.out.println("howdy");
                alert("Data won't post :(")
            } else
                alert("bobo");
        })
});
//        .then((trsJson) => {
//            clearViewByEmp();
//            console.log(trsJson);
//            for (let transaction in trsJson) {
//                createViewByEmpLi(trsJson[transaction]);
//                
//            }
//        })
//});

let approveDenyForm = (form) => {
    let trs = {};
    trs.id = form.trsId.value;
    trs.status = form.status.value;
    return trs;
}

let idForm = (form) => {
    let user = {};
    user.id = form.userId.value;
    return user;
}

let clearViewByEmp = () => {
    viewByEmpDisplay.innerHTML = ""
}

let clearEmployeeDisplay = () => {
    employeeDisplay.innerHTML = "";
}

let clearPendingDisplay = () => {
    pendingDisplay.innerHTML = "";
}

let clearResolvedDisplay = () => {
    resolvedDisplay.innerHTML = "";
}