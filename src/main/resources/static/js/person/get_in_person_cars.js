const urlCars = "http://localhost:8080/api/car";
let tableGetInPersonCars = document.querySelector("#data-output");


fetch(urlPerson + "/" + number + "/get_in_person_cars")
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
                <td>
                    <button class="button__edit" 
                            data-index=${car.id}
                            data-type="editPerson">Перейти
                    </button>
                </td>
                <td>
                    <button class="button__delete" 
                          data-index=${car.id}
                          data-type="deletePerson">Удалить
                    </button>
                </td>
            </tr>
      `;
        }
        tableGetInPersonCars.innerHTML = out;
    })
    .catch((err) => {
        console.log("Error: " + err.message);
        console.log(err.response);
    });

tableGetInPersonCars.onclick = function (event) {
    const index = parseInt(event.target.dataset.index);
    const type = event.target.dataset.type;

    if (type === "editPerson") {
        document.location.href = "http://localhost:8080/car/" + index;
    } else if (type === "deletePerson") {
        const res = fetch(urlCars + "/" + index, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            },
        }).catch((err) => {
            console.log("Error: " + err.message);
            console.log(err.response);
        });

        location.reload();
    }
};
