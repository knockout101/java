// java.swing imports
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;
// java.awt imports
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

public class WeatherGUI extends JFrame implements ActionListener, FocusListener {

    // Declare GUI components
    private static JTextField weatherField;
    private static JTextField stockField;
    private static JPanel weatherPanel;
    private static JPanel stockPanel;
    private static JLabel weatherLabel;
    private static JLabel stockLabel;
    private static JButton weatherButton;
    private static JButton stockButton;
    // Fields for API and search type
    private static SearchType searchType;
    private static String weatherUnits = "imperial"; // Use imperial units for temperature

    public static void main(String[] args) {
        // Create an instance of the WeatherGUI
        new WeatherGUI();
    }

    public WeatherGUI() { 
        // Initialize settings for the JFrame
        setTitle("Show GUI");
        setSize(500, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);
        setLocationRelativeTo(null); // Center the window on the screen
        
        // Create a JPanel
        weatherPanel = new JPanel();
        stockPanel = new JPanel();
        add(weatherPanel);
        add(stockPanel);
        
        // Set layout manager
        weatherPanel.setLayout(new FlowLayout());
        
        // Create components
        // Weather
        weatherLabel = new JLabel("Enter City:");
        weatherField = new JTextField("Enter City or Zip", 15);
        weatherButton = new JButton("Get Weather");
        // Stock
        stockLabel = new JLabel("Enter Stock Symbol:");
        stockField = new JTextField("Enter Stock Symbol", 15);
        stockButton = new JButton("Get Stock Price");
        
        // Add components to panel
        weatherPanel.add(weatherLabel);
        weatherPanel.add(weatherField);
        weatherPanel.add(weatherButton);
        stockPanel.add(stockLabel);
        stockPanel.add(stockField);
        stockPanel.add(stockButton);


        // Add action listeners
        weatherButton.addActionListener(this);
        stockButton.addActionListener(this);

        // Add focus listeners
        weatherField.addFocusListener(this);
        stockField.addFocusListener(this);

        // Set the inital focus to the main panel
        weatherPanel.setFocusable(true);
        weatherPanel.requestFocusInWindow();

        // Set visibility
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == weatherButton || e.getSource() == weatherField) {
            // Call the method to get weather when the button is clicked or Enter is pressed in the text field
            getWeather();
        }
        if(e.getSource() == stockButton) {
            getStockPrice();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        // Clear the text field when it gains focus
        if (e.getSource() == weatherField) {
            weatherField.setText("");
        }
        if(e.getSource() == stockField) {
            stockField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Optionally handle focus lost event
    }

    // Method to get weather information based on user input
    void getWeather() {
        // Get the text from the text field
        String searchInput = weatherField.getText();
        
        // Error message if the text field is empty
        if (searchInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a city name.");
            return;
        }

        // Create a URL for the Weather API request
        String weatherApiKey = getApiKey(); // "Default" API key
        if(weatherApiKey.isEmpty()) {
            JOptionPane.showMessageDialog(null, "API key not found. Please check API key file.");
            return;
        }
        // OpenWeatherMap API URL for current weather data *EXAMPLE*:
        // https://api.openweathermap.org/data/2.5/weather?zip={zip_code},{country_code}&units={units}&appid={API_key}
        // Determine the search type based on the input
        searchType = getSearchType(searchInput);
        // Check if the input is a zip code or city name and format the search input accordingly
        if(searchType == SearchType.ZIP) {
            searchInput = "zip=" + searchInput + ",US";
        } else {
            searchInput = "q=" + searchInput + ",US";
        }

        String urlString = "https://api.openweathermap.org/data/2.5/weather?" 
                         + searchInput 
                         + "&units=" + weatherUnits
                         + "&appid=" + weatherApiKey;
        
        try {
            // Make the API request and get the response
            String response = httpGet(urlString);
            
            // Parse the JSON response
            Gson gson = new Gson();
            WeatherResponse weatherResponse = gson.fromJson(response, WeatherResponse.class);
            
            // Display the weather information
            JOptionPane.showMessageDialog(null,
                      "Weather in " + searchInput + ": " + weatherResponse.weather[0].description + "\n" 
                    + "Temperature: " + weatherResponse.main.temp + "\u00B0F\n"
                    + "Feels Like: " + weatherResponse.main.feels_like + "\u00B0F\n"
                    + "Min Temperature: " + weatherResponse.main.temp_min + " \u00B0F\n"
                    + "Max Temperature: " + weatherResponse.main.temp_max + "\u00B0F\n"
                    + "Pressure: " + weatherResponse.main.pressure + " hPa\n"
                    + "Humidity: " + weatherResponse.main.humidity + "%");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error fetching weather data: " + ex.getMessage());
        }
    }

    // Method to get stock price information based on user input
    void getStockPrice() {
        // Get the stock price when the stock button is clicked
        String stockSymbol = stockField.getText().trim();
        if (stockSymbol.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a stock symbol.");
            return;
        }
        String stockApiKey = getStockApiKey();
        if(stockApiKey.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Stock API key not found. Please check API key file.");
            return;
        }
        // api example: https://www.alphavantage.co/query?function={GLOBAL_QUOTE}&symbol={symbol}&apikey={key}
        String urlString = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" 
                         + stockSymbol 
                         + "&apikey=" + stockApiKey;
        try {
            String response = httpGet(urlString);
            Gson gson = new Gson();
            StockResponse stockResponse = gson.fromJson(response, StockResponse.class);
            JOptionPane.showMessageDialog(null,
                          "Stock Symbol: " + stockResponse.globalQuote.symbol + "\n"
                        + "Current Price: $" + stockResponse.globalQuote.price + "\n"
                        + "Change: $" + stockResponse.globalQuote.change + "\n"
                        + "Change Percent: " + stockResponse.globalQuote.change_percent);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error fetching stock data: " + ex.getMessage());
        }
    }

    // Helper method to collect Stock API key from file
    private static String getStockApiKey() {
        try {
            // Read the Stock API key from a file named "API_key.txt"
            BufferedReader reader = new BufferedReader(new FileReader("API_key.txt"));
            // Read the second line of the file (assuming the first line is the Weather API key)
            reader.readLine(); // Skip the first line
            String apiKey = reader.readLine().trim(); // Read the second line and trim whitespace
            reader.close();
            return apiKey; // Return the API key
        } catch (Exception e) {
            // If there's an error reading the file, return an empty string
            System.err.println("Error reading Stock API key: " + e.getMessage());
            return ""; // Return an empty string if the API key cannot be read
        }
    }

    // Help method to get the Weather API key from a file
    private static String getApiKey() {
        try {
            // Read the API key from a file named "API_key.txt"
            BufferedReader reader = new BufferedReader(new FileReader("API_key.txt"));
            String apiKey = reader.readLine().trim(); // Read the first line and trim whitespace
            reader.close();
            return apiKey; // Return the API key
        } catch (Exception e) {
            // If there's an error reading the file, return an empty string
            System.err.println("Error reading Weather API key: " + e.getMessage());
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