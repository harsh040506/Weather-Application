import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.Scanner;

class WeatherApp {
    public static void main(String[] args) {
        Scanner Input = new Scanner(System.in);
        System.out.println("Kindly enter your Location(City): ");

        // Define your city and API key here
        String inputcity = Input.nextLine();// Example city
        String apiKey = "bd5e378503939ddaee76f12ad7a97608"; // Replace with your actual API key

        // Build the API request URL
        String apiUrl = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", inputcity, apiKey);

        // Create an HttpRequest object
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .GET() // Equivalent to .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        // Create an HttpClient object
        HttpClient client = HttpClient.newHttpClient();

        // Send the request and get the response
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the response body
        if (response != null && response.statusCode() == 200) {
            System.out.println("Weather data: " + response.body());
        } else {
            System.out.println("Failed to retrieve weather data. Status code: " + response.statusCode());
        }
    }
}