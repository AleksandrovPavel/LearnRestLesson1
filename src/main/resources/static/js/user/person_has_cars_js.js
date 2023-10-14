const urlCars = "http://localhost:8080/api/car";
let tableGetInPersonCars = document.querySelector("#data-output");
let number = Number(id);

fetch(urlCars + "/" + number + "/get_in_person_cars")
    .then(function (response) {
        return response.json();
    })
    .then(function (cars) {
        let out = "";
        for (let car of cars) {
            out += `
            <tr>
                <td>${car.model}</td>
                <td>${car.series}</td>
            </tr>
      `;
        }
        tableGetInPersonCars.innerHTML = out;
    })
    .catch((err) => {
        console.log("Error: " + err.message);
        console.log(err.response);
    });
