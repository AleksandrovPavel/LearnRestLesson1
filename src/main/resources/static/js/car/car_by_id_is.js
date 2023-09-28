const urlCar = "http://localhost:8080/api/car";
let placeholder = document.querySelector("#list");
let number = Number(id);
console.log(number)
async function start() {
    fetch(urlCar + "/" + number)
        .then(function (response) {
            return response.json();
        })
        .then(function (car) {
            placeholder.innerHTML = `
                <p> ID:      ${car.id}</p>
                <p> Модель:     ${car.model}</p>
                <p> Серийный номер: ${car.series}</p> 
      `;
        })
        .catch((err) => {
            placeholder.style.color = "red";
            placeholder.innerHTML = err.message;
        });
}
start();
