import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import groovy.json.JsonSlurper;

// Base URI for the Petstore API
RestAssured.baseURI = 'https://petstore.swagger.io/v2';

// Step 1: Send a GET request to fetch available pets
Response findByStatusResponse = given()
    .queryParam("status", "available")
.when()
    .get("/pet/findByStatus");

// Parse the JSON response
def jsonResponse = new JsonSlurper().parseText(findByStatusResponse.asString());

if (jsonResponse && jsonResponse.size() > 0) {
    // Step 2: Get the first pet's ID
    def firstPet = jsonResponse[0];
    def firstPetId = firstPet.id;
    println "First Pet ID: " + firstPetId;

    // Step 3: Call getPetById with the first pet's ID
    Response getPetResponse = given()
        .pathParam("petId", firstPetId)
    .when()
        .get("/pet/{petId}");

    // Parse and print the details of the first pet
    def petDetails = new JsonSlurper().parseText(getPetResponse.asString());
    println "First Pet Name: " + petDetails.name;
    println "First Pet Status: " + petDetails.status;
} else {
    println 'No available pets found.';
}