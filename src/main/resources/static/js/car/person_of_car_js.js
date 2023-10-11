const urlPersonOfCar = "http://localhost:8080/api/car/";
let personOfCar = document.querySelector("#data-output");
let carId = Number(id);

async function start() {
    fetch(urlPersonOfCar + carId + "/get_in_car_person")
        .then(function (response) {
            return response.json();
        })
        .then(function (person) {
            personOfCar.innerHTML =  `
            <tr>
                <td>${person.firstName}</td>
                <td>${person.lastName}</td>
                <td>${person.email}</td>
                <td>
                    <button class="button__edit" 
                            data-index=${person.id}
                            data-type="editPerson">Перейти
                    </button>
                </td>
            </tr>
      `;

        })
        .catch((err) => {
            console.log("Error: " + err.message);
            console.log(err.response);
        });
}
start();

personOfCar.onclick = function (event) {
    const index = parseInt(event.target.dataset.index);
    const type = event.target.dataset.type;

    if (type === "editPerson") {
        document.location.href = "http://localhost:8080/person/" + index;
    }
};

