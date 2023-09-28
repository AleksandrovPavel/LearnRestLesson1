const url = "http://localhost:8080/api/person";

document.querySelector("form").addEventListener("submit", evt => {
    evt.preventDefault();
    const form = document.querySelector("form")
    let person = {
        firstName: form.querySelector("[name='firstName']").value,
        lastName: form.querySelector("[name='lastName']").value,
        email: form.querySelector("[name='email']").value,
    }

    try {
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
            .then(response => console.log(response))
          

    } catch (err) {
        form.style.color = "red";
        form.innerHTML = err.message;

    }
})



