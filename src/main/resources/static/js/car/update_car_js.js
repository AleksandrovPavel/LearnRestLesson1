const urlCar = "http://localhost:8080/api/car";
let number = Number(id);
async function start() {
    fetch(urlCar + "/" + number)
        .then(function (response) {
            return response.json();
        })
        .then((car) => {
            document.querySelector("#model").value = car.model;
        })
        .catch((err) => {
            console.log("Error: " + err.message);
            console.log(err.response);
        });
}
start();


document.querySelector("form").addEventListener("submit", evt => {

    evt.preventDefault();
    let number = Number(id);
    const form = document.querySelector("form")
    let person = {
        model: form.querySelector("[name='model']").value
    }

    try {
        fetch(urlCar + "/" + number, {
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
                    location.href = "http://localhost:8080/car/" + number;
                }
            })


    } catch (err) {
        form.style.color = "red";
        form.innerHTML = err.message;

    }
})



