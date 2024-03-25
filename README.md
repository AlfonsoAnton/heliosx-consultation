## HeliosX Consultation REST Application

## About

This spring-boot application aims to provide a REST based web service which will determine whether a consumer
will likely be prescribed medication to an allergy of the Genovian Pear.

The service exposes three REST endpoints: a GET endpoint to fetch the questions to be answered, A POST endpoint
from which a consumer can answer the questions retrieved from the previous endpoint and be given a verdict whether they
are likely to be prescribed the medication. An extra POST endpoint has been added to allow for the functionality
to add more questions for the consultation with the idea that perhaps a medical professional may want to add more questions.

### Inspiration

It is worth noting that many of the questions and their structure were taken from medexpress.co.uk's consultation questionnaire
for ordering a prescription of hayfever nasal spray to maintain as much realism in the consultation questions as possible.

### Notes

The main tradeoff is the way in which questions are stored and loaded to be accessed. In a production environment
It would be unrealistic to have to hardcode the questions required for the consultation as it would inevitably lead to requiring a
new deployment every time we need to modify the question set. I have tried to reconcile this by exposing a third endpoint not necessarily
pertinent to completing the task to be able to add further questions with the idea that medical professional can also utilize the system
to add their own questions. In a real life scenario this would probably be best outsourced to a CMS platform such as Contentful where
the aforementioned medical professional can modify the question set and the application load from there.

#### Further Improvements

In the interest of respecting the time constraints outlined by the task there are multiple ways in which the application can certainly
 be improved/productionized, these are outlined, but not limited to:

- Improving the testing (both unit and integration) much of the testing are predominantly 'happy path' testing, more testing
for edge cases and bad input can definitely be added.
- In a production environment the endpoints would almost certainly need to be secured (using HTTPS instead of HTTP)
- It would make sense in a practical context to also store user data such that if a prescription isn't likely to be prescribed
a medical professional can reach out given the contact details to finalize the decision
- Store the user against the consultation, so they may retrieve a previous consultations and also ensure that they cannot just
retake the questionnaire to get the correct answers

## Running


### Prerequisites

At a minimum you must have Java 17 installed and the ``$HOME`` path set.

To run the application please clone the public github repository by running

```
git clone https://github.com/AlfonsoAnton/heliosx-consultation.git 
```

### Running the application

From the root of the repository `(heliosx-consultation/)`:

To run the application via the command line: 
```
./mvnw spring-boot:run
```

To build the application via the command line 

```
./mvnw clean install
```

To run the tests via the command line:

```
./mvnw clean test
```

### API Usage and Documentation

If the application has been successfully run, navigate to:

```
http://localhost:8080/swagger-ui/index.html#/
```

To view a swagger UI documentation of the endpoints.

Alternatively from the root of the repository, in the ``api-docs/`` folder there are two files, a postman 
collection and an OPEN API json called `OAS3.json` which documents the three REST endpoints in OAS3 (the same one used by swagger).

### Running the endpoints manually

To test the endpoints ensure you have postman downloaded hit import in your postman workspace and navigate to the postman collection 
`heliosx-consultation.postman_collection.json`. From there all three endpoints should be testable with the running application
with sample request payloads.








