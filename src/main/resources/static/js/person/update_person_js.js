const url = "http://localhost:8080/api/person";
let number = Number(id);

async function start() {
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
}
start();

document.querySelector("form").addEventListener("submit", evt => {
    evt.preventDefault();
    const form = document.querySelector("form")
    let person = {
        firstName: form.querySelector("[name='firstName']").value,
        lastName: form.querySelector("[name='lastName']").value,
    }

    try {
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
                if(response === 'OK') {
                    location.href = "http://localhost:8080/person/" + number;
                }
            })


    } catch (err) {
        form.style.color = "red";
        form.innerHTML = err.message;

    }
})



