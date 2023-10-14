const register = "http://localhost:8080/api/register";
let firstName = document.querySelector("#firstName-input");
let lastName = document.querySelector("#lastName-input");
let email = document.querySelector("#email-input");


document.getElementById("add-form").addEventListener("submit",
    function (evt) {

    evt.preventDefault();

    if (validation(this) == true) {

        const form = document.getElementById("add-form")

        let person = {
            firstName: form.querySelector("[name='firstName']").value,
            lastName: form.querySelector("[name='lastName']").value,
            username: form.querySelector("[name='username']").value,
            password: form.querySelector("[name='password']").value,
        }

        fetch(register, {
            method: "POST",
            body: JSON.stringify(person),
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then(response => {
                return response.json()
            })
            .then(response => {
                if (response === 'OK') {
                    location.href = "http://localhost:8080/auth/login"
                } else {
                    email.style.color = "red";
                    email.append(response.message.replace("email - ", "").replace(";", ""));
                    console.log(response);
                }
            })
            .catch((err) => {
                console.log("Error: " + err.message);
                console.log(err.response);
            });
    }

})