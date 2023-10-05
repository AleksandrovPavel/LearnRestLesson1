const urlPerson = "http://localhost:8080/api/person";
let placeholder = document.querySelector("#list");
let number = Number(id);


fetch(urlPerson + "/" + number)
    .then(function (response) {
        return response.json();
    })
    .then(function (person) {
        placeholder.innerHTML = `
                <p> ID:      ${person.id}</p>
                <p> Имя:     ${person.firstName}</p>
                <p> Фамилия: ${person.lastName}</p>
                <p> Email:   ${person.email}</p>  
      `;
    })
    .catch((err) => {
        placeholder.style.color = "red";
        placeholder.innerHTML = err.message;
    });


