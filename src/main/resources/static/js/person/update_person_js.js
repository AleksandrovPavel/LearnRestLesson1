const url = "http://localhost:8080/api/person";
let number = Number(id);

fetch(url + "/" + number)
    .then(function (response) {
        return response.json();
    })
    .then((person) => {
        document.querySelector("#firstName").value = person.firstName;
        document.querySelector("#lastName").value = person.lastName;
    })
    .catch((err) => {
        console.log("Error: " + err.message);
        console.log(err.response);
    });


document.getElementById("add-form").addEventListener("submit", function (evt) {

    evt.preventDefault();

    if (validation(this) == true) {

        const form = document.getElementById("add-form")

        let person = {
            firstName: form.querySelector("[name='firstName']").value,
            lastName: form.querySelector("[name='lastName']").value,
        }

        fetch(url + "/" + number, {
            method: "PATCH",
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
                    location.href = "http://localhost:8080/person/" + number;
                }
            })
            .catch((err) => {
                console.log("Error: " + err.message);
                console.log(err.response);
            });
    }
})



