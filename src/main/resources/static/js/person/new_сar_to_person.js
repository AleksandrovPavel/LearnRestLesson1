document.querySelector("form").addEventListener("submit", evt => {
    evt.preventDefault();
    let number = Number(id);
    const form = document.querySelector("form")
    let person = {
        model: form.querySelector("[name='model']").value,
        series: form.querySelector("[name='series']").value,
    }

    try {
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
                }
            })


    } catch (err) {
        form.style.color = "red";
        form.innerHTML = err.message;

    }
})
start()



