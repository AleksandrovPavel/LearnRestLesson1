document.querySelector("form").addEventListener("submit", evt => {
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
            .then(response => console.log(response))


    } catch (err) {
        form.style.color = "red";
        form.innerHTML = err.message;

    }
})
start()



