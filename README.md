To set up the DKSRService application locally, follow these steps:

1) AirQualityIndex Setup:

Navigate to the AirQualityIndex folder.
Run **gradle clean build** to generate the JAR file.
Use the generated JAR file to create a Docker image.

2) IncidenceService Setup:
Set API key in the Application.properties with key value **incidence-details.api-key=**
Run gradle clean build under the IncidenceService application folder to generate the JAR file.Use the generated JAR file to create a Docker image.

3) DKSRService Setup:

Navigate to the DKSRService folder.
Run docker-compose up -d.

4) Accessing the Application: Once the containers are up and running, you can access the application at localhost:9500.
5) This setup will deploy two backend services, AirQualityIndex and IncidenceService, along with the UI application (dksr-ui), which consumes these web services. 
   The UI provides visualization of air quality and traffic incidents on a map for Berlin.
6) The DKSRService application utilizes Kafka for passing bounds information fetched specifically for Berlin. Here's how it works:

    Producer (AirQualityIndex Web Service):

The AirQualityIndex web service acts as the producer in this setup.
It gathers bounds data for Berlin and adds this information into Kafka as messages.

Consumer (IncidenceService Web Service):
On the other end, the IncidenceService web service functions as the consumer.
It consumes the bounds data from Kafka.
Once it receives the bounds information, the IncidenceService web service utilizes it to fetch incident details specifically for those bounds.






