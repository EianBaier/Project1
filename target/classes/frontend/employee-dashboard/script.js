window.onload = async () => {
    let response = await fetch("http://localhost:9001/check-session");
    let result = await response.json();

    /* if(!result.successful)
        window.location.href = "../"; */

    if ( result.roleId == 2){
        window.location.href= "../manager-dashboard"

    }else if (result.roleId != 1){
        window.location.href= "../"

    }

    populateReimbursementsForEmployee();



    
    

}

async function logout(){
   

    window.location.href = "../"
}

/* <tbody id="tbody">
<tr>
  <th scope="row">1</th>
  <td>5</td>
  <td>500</td>
  <td>2:00</td>
  <td>3:00</td>
  <td>This is a test</td>
  <td>Pending</td>
  <td>Food</td>
  
</tr>
<tr>
  <th scope="row">2</th>
  <td>4</td>
  <td>600</td>
  <td>23:00</td>
  <td>3:00</td>
  <td>This is another test</td>
  <td>Approved</td>
  <td>Travel</td>
</tr> */


async function populateReimbursementsForEmployee(){

    let response1 = await fetch("http://localhost:9001/check-session");
    let result1 = await response1.json();

    let userid = result1.userId;


    let response = await fetch(`http://localhost:9001/reimbursements/${userid}`);
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
              <td>${reimbursement.reimbResolved}</td>
              <td>${reimbursement.reimbDescrition}</td>
              <td>${reimbStatus}</td>
              <td>${reimbType}</td>
                 
        `
       
        reimbTable.appendChild(reimbElem);
        
    });

}

async function submitReimbursement(){

    console.log("submit clicked");

    let response = await fetch("http://localhost:9001/check-session");
    let result = await response.json();

    let author = result.userId;

    let amountInput = document.getElementById("amount-input")
    let amount = amountInput.value;

    let descriptionInput = document.getElementById("description-input")
    let description = descriptionInput.value;

    let typeInput = document.getElementById("inputGroupSelect01")
    let type = typeInput.value;

    await fetch(`http://localhost:9001/reimbursements`,{
        method: "POST",
        body: JSON.stringify({
        reimbAmount: amount,
        reimbDescrition: description,
        reimbAuthor: author,
        reimbResolver: 2,
        reimbStatusId: 1,
        reimbType: type

        })
    });

    populateReimbursementsForEmployee();
}