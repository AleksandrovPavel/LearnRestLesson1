const urlCar = "http://localhost:8080/api/car";
let number = Number(id);

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


document.getElementById("add-form").addEventListener("submit", function (evt) {

    evt.preventDefault();

    if (validation(this) == true) {

        const form = document.getElementById("add-form")

        let car = {
            model: form.querySelector("[name='model']").value
        }

        fetch(urlCar + "/" + number, {
            method: "PATCH",
            body: JSON.stringify(car),
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
            .catch((err) => {
                console.log("Error: " + err.message);
                console.log(err.response);
            });
    }
})



