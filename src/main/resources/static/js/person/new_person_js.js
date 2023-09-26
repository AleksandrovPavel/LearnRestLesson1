const url = "http://localhost:8080/api/person";

document.querySelector("form").addEventListener("submit", e => {

    const form = document.querySelector("form")
    let person = {
        firstName: form.querySelector("[name='firstName']").value,
        lastName: form.querySelector("[name='lastName']").value,
        email: form.querySelector("[name='email']").value,
    }

    fetch(url, {
        method: "POST",
        body: JSON.stringify(person),
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => {
            if (response.status === 400) {
               return  e.preventDefault();
            }
        })
        .then(response => {
            return response.json()
        })
        .then(response => console.log(response))
        .catch((err) => {
            console.log("Error: " + err.message);
            console.log(err.response);
        })
})



