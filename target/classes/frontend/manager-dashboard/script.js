window.onload = async () => {
    let response = await fetch("http://localhost:9001/check-session");
    let result = await response.json();

    /* if(!result.successful)
        window.location.href = "../"; */

    if ( result.roleId == 1){
        window.location.href= "../employee-dashboard"

    }else if (result.roleId != 2){
        window.location.href= "../"

    }

    populateReimbursements();

}

let tab = "";

async function logout(){
   

    window.location.href = "../"
}

async function populateReimbursements(){

    tab = "All";

    let response = await fetch("http://localhost:9001/reimbursements");
    let result = await response.json();

    

    //clear table

    let reimbTable = document.getElementById("tbody");
    reimbTable.innerHTML = "";

    //loop through and each reimbursement and create the elements in  them

    result.forEach(reimbursement => {
        let reimbElem = document.createElement("tr");
        reimbElem.id = reimbursement.reimbId;
        let reimbursementStatusNum = reimbursement.reimbStatusId;
        let reimbursementTypeNum = reimbursement.reimbType;

        let reimbStatus = "";

        if(reimbursementStatusNum == 1) {
            reimbStatus = "Pending"
        }
        if(reimbursementStatusNum == 2){
            reimbStatus = "Approved"
        }
        if(reimbursementStatusNum == 3){
            reimbStatus = "Denied"

           }


        let reimbType = "";


        if(reimbursementTypeNum == 1) {
            reimbType = "Lodging"
        }
        if(reimbursementTypeNum == 2){
            reimbType = "Travel"
        }if(reimbursementTypeNum == 3){
            reimbType = "Food"
        }
        if(reimbursementTypeNum == 4){
            reimbType = "Other"

           }



        

        reimbElem.innerHTML = `
        <th scope="row">${reimbursement.reimbId}</th>
              <td>${reimbursement.reimbAuthor}</td>
              <td>$${reimbursement.reimbAmount}</td>
              <td>${reimbursement.reimbSubmitted}</td>
              <td>${reimbursement.reimbDescrition}</td>
              <td>${reimbStatus}</td>
              <td>${reimbType}</td>
              <td>
                <button class="btn btn-success" onclick="approve(${reimbursement.reimbId})">Approve</button>
                <button class="btn btn-danger"onclick="deny(${reimbursement.reimbId})">Deny</button>
              
              </td>
        `
       
        reimbTable.appendChild(reimbElem);
        
    });

}

async function approve(any){

   // let reimbId = document.getElementById("1")

    await fetch(`http://localhost:9001/resolve/${any}/2`,{method: "PATCH"})

    if (tab == "Pending"){
        onlyPending();
    }
    if (tab == "Approved"){
        onlyApproved();
    }
    if(tab == "Denied"){
        onlyDenied();
    }
    if(tab == "All"){
        populateReimbursements();
    }

}

async function deny(any){

    // let reimbId = document.getElementById("1")
 
     await fetch(`http://localhost:9001/resolve/${any}/3`,{method: "PATCH"})
 
     if (tab == "Pending"){
        onlyPending();
    }
    if (tab == "Approved"){
        onlyApproved();
    }
    if(tab == "Denied"){
        onlyDenied();
    }
    if(tab == "All"){
        populateReimbursements();
    }

 
 }

 async function onlyPending(){

    tab="Pending"

    let response = await fetch(`http://localhost:9001/reimbursements/status/1`)
    let result = await response.json();
    
    

    //clear table

    let reimbTable = document.getElementById("tbody");
    reimbTable.innerHTML = "";

    //loop through and each reimbursement and create the elements in  them

    result.forEach(reimbursement => {
        let reimbElem = document.createElement("tr");
        reimbElem.id = reimbursement.reimbId;
        let reimbursementStatusNum = reimbursement.reimbStatusId;
        let reimbursementTypeNum = reimbursement.reimbType;

        let reimbStatus = "";

        if(reimbursementStatusNum == 1) {
            reimbStatus = "Pending"
        }
        if(reimbursementStatusNum == 2){
            reimbStatus = "Approved"
        }
        if(reimbursementStatusNum == 3){
            reimbStatus = "Denied"

           }


        let reimbType = "";


        if(reimbursementTypeNum == 1) {
            reimbType = "Lodging"
        }
        if(reimbursementTypeNum == 2){
            reimbType = "Travel"
        }if(reimbursementTypeNum == 3){
            reimbType = "Food"
        }
        if(reimbursementTypeNum == 4){
            reimbType = "Other"

           }



        

        reimbElem.innerHTML = `
        <th scope="row">${reimbursement.reimbId}</th>
              <td>${reimbursement.reimbAuthor}</td>
              <td>$${reimbursement.reimbAmount}</td>
              <td>${reimbursement.reimbSubmitted}</td>
              <td>${reimbursement.reimbDescrition}</td>
              <td>${reimbStatus}</td>
              <td>${reimbType}</td>
              <td>
                <button class="btn btn-success" onclick="approve(${reimbursement.reimbId})">Approve</button>
                <button class="btn btn-danger"onclick="deny(${reimbursement.reimbId})">Deny</button>
              
              </td>
        `
       
        reimbTable.appendChild(reimbElem);
        
    });
 }

 async function onlyApproved(){

    tab="Approved"

    let response = await fetch(`http://localhost:9001/reimbursements/status/2`)
    let result = await response.json();
    
    

    //clear table

    let reimbTable = document.getElementById("tbody");
    reimbTable.innerHTML = "";

    //loop through and each reimbursement and create the elements in  them

    result.forEach(reimbursement => {
        let reimbElem = document.createElement("tr");
        reimbElem.id = reimbursement.reimbId;
        let reimbursementStatusNum = reimbursement.reimbStatusId;
        let reimbursementTypeNum = reimbursement.reimbType;

        let reimbStatus = "";

        if(reimbursementStatusNum == 1) {
            reimbStatus = "Pending"
        }
        if(reimbursementStatusNum == 2){
            reimbStatus = "Approved"
        }
        if(reimbursementStatusNum == 3){
            reimbStatus = "Denied"

           }


        let reimbType = "";


        if(reimbursementTypeNum == 1) {
            reimbType = "Lodging"
        }
        if(reimbursementTypeNum == 2){
            reimbType = "Travel"
        }if(reimbursementTypeNum == 3){
            reimbType = "Food"
        }
        if(reimbursementTypeNum == 4){
            reimbType = "Other"

           }



        

        reimbElem.innerHTML = `
        <th scope="row">${reimbursement.reimbId}</th>
              <td>${reimbursement.reimbAuthor}</td>
              <td>$${reimbursement.reimbAmount}</td>
              <td>${reimbursement.reimbSubmitted}</td>
              <td>${reimbursement.reimbDescrition}</td>
              <td>${reimbStatus}</td>
              <td>${reimbType}</td>
              <td>
                <button class="btn btn-success" onclick="approve(${reimbursement.reimbId})">Approve</button>
                <button class="btn btn-danger"onclick="deny(${reimbursement.reimbId})">Deny</button>
              
              </td>
        `
       
        reimbTable.appendChild(reimbElem);
        
    });
 }

 async function onlyDenied(){

    tab="Denied"

    let response = await fetch(`http://localhost:9001/reimbursements/status/3`)
    let result = await response.json();
    
    

    //clear table

    let reimbTable = document.getElementById("tbody");
    reimbTable.innerHTML = "";

    //loop through and each reimbursement and create the elements in  them

    result.forEach(reimbursement => {
        let reimbElem = document.createElement("tr");
        reimbElem.id = reimbursement.reimbId;
        let reimbursementStatusNum = reimbursement.reimbStatusId;
        let reimbursementTypeNum = reimbursement.reimbType;

        let reimbStatus = "";

        if(reimbursementStatusNum == 1) {
            reimbStatus = "Pending"
        }
        if(reimbursementStatusNum == 2){
            reimbStatus = "Approved"
        }
        if(reimbursementStatusNum == 3){
            reimbStatus = "Denied"

           }


        let reimbType = "";


        if(reimbursementTypeNum == 1) {
            reimbType = "Lodging"
        }
        if(reimbursementTypeNum == 2){
            reimbType = "Travel"
        }if(reimbursementTypeNum == 3){
            reimbType = "Food"
        }
        if(reimbursementTypeNum == 4){
            reimbType = "Other"

           }



        

        reimbElem.innerHTML = `
        <th scope="row">${reimbursement.reimbId}</th>
              <td>${reimbursement.reimbAuthor}</td>
              <td>$${reimbursement.reimbAmount}</td>
              <td>${reimbursement.reimbSubmitted}</td>
              <td>${reimbursement.reimbDescrition}</td>
              <td>${reimbStatus}</td>
              <td>${reimbType}</td>
              <td>
                <button class="btn btn-success" onclick="approve(${reimbursement.reimbId})">Approve</button>
                <button class="btn btn-danger"onclick="deny(${reimbursement.reimbId})">Deny</button>
              
              </td>
        `
       
        reimbTable.appendChild(reimbElem);
        
    });
 }
