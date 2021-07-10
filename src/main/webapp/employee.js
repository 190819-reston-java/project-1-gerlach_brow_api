'use strict';

const BASE_URL = "/Project01/employee";
const VIEW_INFO = `${BASE_URL}/view_info`;
const UPDATE_INFO = `${BASE_URL}/update_info`;
const VIEW_RES = `${BASE_URL}/view_res`;
const VIEW_PENDING = `${BASE_URL}/view_pending`;
const CREATE_NEW = `${BASE_URL}/create_new`;
const VIEW_IMG = `${BASE_URL}/view_img`;

let viewInfo = document.getElementById("view-info");
let infoDisplay = document.getElementById("info-display");
let viewMyPendingReimbursements = document.getElementById("view-my-pending-reimbursements");
let pendingDisplay = document.getElementById("pending-display");
let viewMyResolvedReimbursements = document.getElementById("view-my-resolved-reimbursements");
let resolvedDisplay = document.getElementById("resolved-display");
let submitReimbursement = document.getElementById("submit-reimbursement");
let updateUserInfo = document.getElementById("update-user-info");
let viewImg = document.getElementById("view-img");

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
    li.className = "w3-padding-small";
    li.innerText = ` ${user.firstName}
    	 ${user.lastName}
    	 ${user.email}
    	 ${user.address}
    	 ${user.address2}
    	 ${user.phoneNumber}
    	 ${user.position}`;
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
};

viewImg.addEventListener("click", (event) => {
    fetch(VIEW_IMG, { method: "GET" })
        .then((response) => {
            return response.json();
        })
        .then((imgJson) => {
            var img = document.createElement('img');
            img.src = "data:image/png;base64, " + imgJson;
            document.getElementById('img').appendChild(img);
        })
        .catch(console.error);
});

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

submitReimbursement.addEventListener("submit", (event) => {
    event.preventDefault();

    fetch(CREATE_NEW, { method: "POST", body: JSON.stringify(formSubmission(submitReimbursement)) })
        .then((response) => {
            if (response.status >= 200 && response.status < 300) {
                alert("Employee reimbursement submitted");
            } else {
                alert("Failed to submit reimbursement");
            }
        })
});

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
});

let formSubmission = (form) => {
    let trs = {};
    trs.comment = form.comment.value;
    return trs;
}

let employeeFromForm = (form) => {
    let user = {};
    user.firstName = form.firstName.value;
    user.lastName = form.lastName.value;
    user.password = form.password.value;
    user.address = form.address.value;
    user.address2 = form.address2.value;
    user.phoneNumber = form.phoneNumber.value;
    return user;
}

let clearSubDisplay = () => {
    subDisplay.innerHTML = "";
}

let clearUpdateDisplay = () => {
    updateDisplay.innerHTML = "";
}