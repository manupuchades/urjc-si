let socket = new WebSocket("ws://" + window.location.host + "/notifications")

socket.onopen = function (e) {
    console.log("WebSocket connection established")
}

socket.onmessage = function (event) {
    console.log(`[message] Data received from server: ${event.data}`)
    processProgress(JSON.parse(event.data))
}

socket.onclose = function (event) {
    if (event.wasClean) {
        console.log(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`)
    } else {
        console.log('[close] Connection died')
    }
}

socket.onerror = function (error) {
    console.log(`[error] ${error.message}`)
}

function addPlant() {
    let xhttp = new XMLHttpRequest()

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let eoloplant = JSON.parse(this.responseText)
            processProgress(eoloplant)
        }
    }

    xhttp.open("POST", "http://" + window.location.host + "/api/eoloplants", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("{\"city\": \"" + document.getElementById("city-name").value + "\"}");
    disableAdditon();
    loadList();
}

function loadList() {
    let xhttp = new XMLHttpRequest()

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let eoloplants = JSON.parse(this.responseText)
            printList(eoloplants)
        }
    }
    xhttp.open("GET", "http://" + window.location.host + "/api/eoloplants", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function printList(eoloplants) {
    clearList();
    for (item in eoloplants) {
        let eoloplant = eoloplants[item];
        if (eoloplant.progress === 100) {
            let li = document.createElement('li');
            li.appendChild(document.createTextNode(`City: ${eoloplant.city} Progress: ${eoloplant.progress}% Planning: ${eoloplant.planning}`));
            document.getElementById('eoloplants-list').appendChild(li);
        }
    }
}

function clearList() {
    const parent = document.getElementById('eoloplants-list');
    while (parent.firstChild) {
        parent.firstChild.remove()
    }
}

function processProgress(eoloplant) {
    let text = ''
    if (eoloplant.progress < 100) {
        text = `City: ${eoloplant.city}\nProgress: ${eoloplant.progress}%`;
    } else if (eoloplant.progress === 100) {
        text = `City: ${eoloplant.city}\nResult: ${eoloplant.result}`;
        enableAdditon();
    }
    document.getElementById("progress").innerHTML = text
}

function enableAdditon() {
    document.getElementById("add-plant-button").disabled = false;
}

function disableAdditon() {
    document.getElementById("add-plant-button").disabled = true;
}