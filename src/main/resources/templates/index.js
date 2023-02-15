function getAllBuddies() {
    const httpRequest = new XMLHttpRequest();
    httpRequest.setRequestHeader(
        "Content-Type",
        "application/json"
    );
    const url = "http://localhost:8080/buddyInfoes";
    httpRequest.open("GET", url);
    httpRequest.send();

    let resp = null;
    httpRequest.onload = () => {
        const obj = JSON.parse(httpRequest.responseText);
        resp = obj;
    }
    return resp;
}

function getBuddy() {

    $.ajax('http://localhost:8080/buddyInfoes',
        {
            type: 'GET',
            success: function (data, status, xhr) {

            },
            error: function (jqXhr, textStatus, errorMessage) {

            }
        })
}