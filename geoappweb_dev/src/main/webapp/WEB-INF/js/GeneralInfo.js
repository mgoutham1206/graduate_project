/**
 * 
 */


function parseJSON(data) {
    return window.JSON && window.JSON.parse ? window.JSON.parse( data ) : (new Function("return " + data))(); 
}

$(document).ready(function() {
    var personJson = $('#person');
    person = parseJSON(personJson.val());
    alert(person.name);
});