const urlPersons = "http://localhost:8080/api/person";
let allPerson = document.querySelector("#data-output");

async function start() {
  fetch(urlPersons)
    .then(function (response) {
      return response.json();
    })
    .then(function (persons) {
      let out = "";
      for (let person of persons) {
        out += `
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
                <td>
                    <button class="button__delete" 
                          data-index=${person.id}
                          data-type="deletePerson">Удалить
                    </button>
                </td>
            </tr>
      `;
      }
      allPerson.innerHTML = out;
    })
    .catch((err) => {
      console.log("Error: " + err.message);
      console.log(err.response);
    });
}

allPerson.onclick = function (event) {
  const index = parseInt(event.target.dataset.index);
  const type = event.target.dataset.type;

  if (type === "editPerson") {
    document.location.href = "http://localhost:8080/person/" + index;
  } else if (type === "deletePerson") {
    const res = fetch(urlPersons + "/" + index, {
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
start();
