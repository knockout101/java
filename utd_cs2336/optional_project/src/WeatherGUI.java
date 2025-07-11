import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import com.google.gson.Gson;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.FileReader;

public class WeatherGUI extends JFrame implements ActionListener, FocusListener {

    // Declare GUI components
    private static JTextField textField;
    private static JPanel panel;
    private static JLabel label;
    private static JButton weatherButton;

    private static String weatherUnits = "imperial"; // Use imperial units for temperature

    private static SearchType searchType;

    public static void main(String[] args) {
        // Create an instance of the WeatherGUI
        new WeatherGUI();
    }

    public WeatherGUI() { 
        // Initialize settings for the JFrame
        setTitle("Show GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        // Create a JPanel
        panel = new JPanel();
        add(panel);
        
        // Set layout manager
        panel.setLayout(new FlowLayout());
        
        // Create components
        label = new JLabel("Enter City:");
        textField = new JTextField("Enter City or Zip", 15);
        weatherButton = new JButton("Get Weather");
        
        // Add components to panel
        panel.add(label);
        panel.add(textField);
        panel.add(weatherButton);

        // Add action listeners
        weatherButton.addActionListener(this);

        // Add focus listeners
        textField.addFocusListener(this);

        // Set the inital focus to the main panel
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        // Set visibility
        setVisible(true);
    }

    // Method to get the Weather API key from a file
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

    // Helper method to check if zip code or city
    private static SearchType getSearchType(String input) {
        // Check if the input is numeric (zip code)
        if (input.matches("\\d+")) {
            return searchType.ZIP; // Return ZIP type
        } else {
            return searchType.CITY; // Return CITY type
        }
    }

    // Helper method to perform HTTP GET request
    private static String httpGet(String urlString) throws Exception {
        java.net.URL url = new java.net.URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();
        return content.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the text from the text field
        String zipcode = textField.getText();
        
        // Error message if the text field is empty
        if (zipcode.isEmpty()) {
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
        String urlString = "https://api.openweathermap.org/data/2.5/weather?" 
                         + "zip=" + zipcode + ",US"
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
                      "Weather in " + zipcode + ": " + weatherResponse.weather[0].description + "\n" 
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

    @Override
    public void focusGained(FocusEvent e) {
        // Clear the text field when it gains focus
        if (e.getSource() == textField) {
            textField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Optionally handle focus lost event
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

enum SearchType {
    ZIP, CITY
}