const param = {
    "url" : "http://localhost:8080/person"
}

async function getAllPersons() {
    const res  = await fetch(param.url)
    const todos = await res.json()
}