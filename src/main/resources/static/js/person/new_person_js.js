const url = "http://localhost:8080/api/person";
let firstName = document.querySelector("#firstName-input");
let lastName = document.querySelector("#lastName-input");
let email = document.querySelector("#email-input");
let role;


document.getElementById("add-form").addEventListener("submit", function (evt) {

    evt.preventDefault();

    let radioBtn = document.roles.role;
    let role = document.querySelector("input[name='role']:checked").value;
    radioBtn.forEach(radio => {
        radio.addEventListener("change", role)
    })


    if (validation(this) == true) {

        const form = document.getElementById("add-form")

        let person = {
            firstName: form.querySelector("[name='firstName']").value,
            lastName: form.querySelector("[name='lastName']").value,
            username: form.querySelector("[name='username']").value,
            password: form.querySelector("[name='password']").value,
            roles: form.querySelector(role).value,
        }

        fetch(url, {
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
                    location.href = "http://localhost:8080/person"
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