// gson imports
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
// java.net import
import java.net.HttpURLConnection;
import java.net.URL;
// java.io imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Backend{
    private SearchType searchType;
    private String weatherUnits = "imperial"; // Use imperial units for temperature
    // Front-end input for weather search
    private String searchInput = ""; // This should be set from the front-end
    private String stockSymbol = ""; // Stock symbol input from the front-end

    public void setSearchInput(String input) {
        this.searchInput = input;
    }
    
    public void setStockSymbol(String symbol) {
        this.stockSymbol = symbol;
    }

    // Method to get weather information based on user input
    public String getWeather() {
        // Error message if the text field is empty
        if (searchInput.isEmpty()) {
            System.err.println("Please enter a city name.");
            return "";
        }

        // Create a URL for the Weather API request
        String weatherApiKey = getApiKey(ApiKeyLine.WEATHER.getLineNumber());
        if(weatherApiKey.isEmpty()) {
            System.err.println("API key not found. Please check API key file.");
            return "";
        }
        // OpenWeatherMap API URL for current weather data *EXAMPLE*:
        // https://api.openweathermap.org/data/2.5/weather?zip={zip_code},{country_code}&units={units}&appid={API_key}
        searchType = getSearchType(searchInput);
        
        String apiSearchInput = "";
        if(searchType == SearchType.ZIP) {
            apiSearchInput = "zip=" + searchInput + ",US";
        } else if(searchType == SearchType.CITY) {
            apiSearchInput = "q=" + searchInput + ",US";
        }

        String urlString = "https://api.openweathermap.org/data/2.5/weather?" 
                         + apiSearchInput 
                         + "&units=" + weatherUnits
                         + "&appid=" + weatherApiKey;
        // Attempt to fetch weather data from the API
        try {
            String response = httpGet(urlString);
            
            Gson gson = new Gson();
            WeatherResponse weatherResponse = gson.fromJson(response, WeatherResponse.class);
            
            if (weatherResponse == null || weatherResponse.weather == null || weatherResponse.weather.length == 0) {
                System.err.println("Invalid response from weather API.");
                return "";
            }

            return "Weather in " + searchInput + ": " + weatherResponse.weather[0].description + " "
                 + "Temperature: " + weatherResponse.main.temp + " F "
                 + "Feels Like: " + weatherResponse.main.feels_like + " F "
                 + "Min Temperature: " + weatherResponse.main.temp_min + " F "
                 + "Max Temperature: " + weatherResponse.main.temp_max + " F "
                 + "Pressure: " + weatherResponse.main.pressure + " hPa "
                 + "Humidity: " + weatherResponse.main.humidity + "%";

        } catch (Exception ex) {
            System.err.println("Error fetching weather data: " + ex.getMessage());
            return "";
        }
    }

    // Method to get stock price information based on user input
    public String getStockPrice() {   
        if (stockSymbol.isEmpty()) {
            System.err.println("Please enter a stock symbol.");
            return "";
        }
        String stockApiKey = getApiKey(ApiKeyLine.STOCK.getLineNumber());
        if(stockApiKey.isEmpty()) {
            System.err.println("Stock API key not found. Please check API key file.");
            return "";
        }
        // api example: https://www.alphavantage.co/query?function={GLOBAL_QUOTE}&symbol={symbol}&apikey={key}
        String urlString = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" 
                         + stockSymbol 
                         + "&apikey=" + stockApiKey;

        // Attempt to fetch stock data from the API
        try {
            String response = httpGet(urlString);
            
            Gson gson = new Gson();
            StockResponse stockResponse = gson.fromJson(response, StockResponse.class);
            
            if(stockResponse == null || stockResponse.globalQuote == null) {
                System.err.println("Invalid response from stock API.");
                return "";
            }
            
            return "Stock Symbol: " + stockResponse.globalQuote.symbol
                 + " Current Price: $" + stockResponse.globalQuote.price
                 + " Change: $" + stockResponse.globalQuote.change
                 + " Change Percent: " + stockResponse.globalQuote.change_percent;

        } catch (Exception ex) {
            System.err.println("Error fetching stock data: " + ex.getMessage());
            return "";
        }
    }

    // Helper method to collect Stock API key from file
    private static String getApiKey(int lineNumber) {
        try {
            // Read the Stock API key from a file named "API_key.txt"
            BufferedReader reader = new BufferedReader(new FileReader("API_key.txt"));
            // Read the second line of the file (assuming the first line is the Weather API key)
            for(int i = 0; i < lineNumber; i++) {
                reader.readLine(); // Skip lines until we reach the desired line
            }
            String apiKey = reader.readLine().trim(); // Read the second line and trim whitespace
            reader.close();
            return apiKey; // Return the API key
        } catch (Exception e) {
            // If there's an error reading the file, return an empty string
            System.err.println("Error reading Stock API key: " + e.getMessage());
            return ""; // Return an empty string if the API key cannot be read
        }
    }

    // Helper method to check if zip code or city
    private static SearchType getSearchType(String input) {
        // Check if the input is numeric (zip code)
        if (input.matches("\\d+")) {
            return SearchType.ZIP; // Return ZIP type
        } else {
            return SearchType.CITY; // Return CITY type
        }
    }

    // Helper method to perform HTTP GET request
    private static String httpGet(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();
        return content.toString();
    }

}

class WeatherResponse {
    Weather[] weather;
    Main main;

    class Weather {
        String description;
    }

    class Main {
        double temp;
        double feels_like;
        double temp_min;
        double temp_max;
        int pressure;
        int humidity;

        public Main(double temp, double feels_like, double temp_min, double temp_max, int pressure, int humidity) {
            this.temp = temp;
            this.feels_like = feels_like;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
            this.pressure = pressure;
            this.humidity = humidity;
        }
    }
}

class StockResponse {
    @SerializedName("Global Quote")
    GlobalQuote globalQuote;

    public StockResponse(GlobalQuote globalQuote) {
        this.globalQuote = globalQuote;
    }

    class GlobalQuote {
        @SerializedName("01. symbol")
        String symbol;

        @SerializedName("05. price")
        double price;

        @SerializedName("09. change")
        double change;

        @SerializedName("10. change percent")
        String change_percent;

        public GlobalQuote(String symbol, double price, double change, String change_percent) {
            this.symbol = symbol;
            this.price = price;
            this.change = change;
            this.change_percent = change_percent;
        }
    }
}

enum SearchType {
    ZIP, CITY
}

enum ApiKeyLine {
    WEATHER(0), STOCK(1);

    private final int lineNumber;

    ApiKeyLine(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}