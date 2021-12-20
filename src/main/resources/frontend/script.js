
window.onload = async() => {
    let a = "a"
    let b = "b"
    let response = await fetch(`http://localhost:9001/login`,{
        method: "POST",
        body: JSON.stringify({
            username: a,
            password: b

        })
    })

    let result = await response.json();

    console.log(result)

}


async function wrongLogin(){
    console.log("Username or Password doesn't exist")
}

async function login(e){
    e.preventDefault();

    let usernameInputElem = document.getElementById("username-input");
    let username = usernameInputElem.value;

    let passwordInputElem = document.getElementById('password-input');
    let password = passwordInputElem.value;

    let response = await fetch(`http://localhost:9001/login`,{
        method: "POST",
        body: JSON.stringify({
            username: username,
            password: password

        })
    })

    

    let result = await response.json();

    let id = result.roleId;

    console.log(id);

    if( id == 2){
        window.location.href= "./manager-dashboard"
    }else if (id == 1){
        window.location.href= "./employee-dashboard"
    }else{
        /// logic if user doesn't exist in database
        wrongLogin();

    }
    

 


    //console.log(username);
}