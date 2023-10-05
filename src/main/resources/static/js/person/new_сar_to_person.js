let series = document.querySelector("#series-input");


document.getElementById("add-form").addEventListener("submit", function (evt) {

    evt.preventDefault();

    if (validation(this) == true) {

        let number = Number(id);
        const form = document.getElementById("add-form")

        let person = {
            model: form.querySelector("[name='model']").value,
            series: form.querySelector("[name='series']").value,
        }

        fetch(urlPerson + "/" + number + "/new_car_to_person", {
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
                    location.href = "http://localhost:8080/person/" + number;
                } else {
                    series.style.color = "red";
                    series.append(response.message.replace("series - ", "").replace(";", ""));
                }
            })
            .catch((err) => {
                console.log("Error: " + err.message);
                console.log(err.response);
            });
    }
})





