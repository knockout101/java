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

public class WeatherGUI extends JFrame implements ActionListener, FocusListener {

    // Declare GUI components
    private static JTextField textField;
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel label;
    private static JButton weatherButton;

    public WeatherGUI() { 
        // Initialize settings for the JFrame
        setTitle("Show GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        // Create a JPanel
        panel = new JPanel();
        frame.add(panel);
        
        // Set layout manager
        panel.setLayout(new FlowLayout());
        
        // Create components
        label = new JLabel("Enter City:");
        textField = new JTextField(15);
        weatherButton = new JButton("Get Weather");
        
        // Add components to panel
        panel.add(label);
        panel.add(textField);
        panel.add(weatherButton);

        // Add action listener to button
        weatherButton.addActionListener(this);

        // Set visibility
        frame.setVisible(true);
        
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
        
        // Check if the city is empty
        if (zipcode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a city name.");
            return;
        }
        
        // API key for geocode.maps.co (for coordinates)
        String coordApiKey = "686da940ca91d239180481mvba0feb6";
        // geocode.maps.co API URL for coordinates *EXAMPLE*:
        // https://geocode.maps.co/search?q={address}&api_key={API key}
        String coordUrlString = "https://geocode.maps.co/search?q=" + zipcode + "&api_key=" + coordApiKey;

        // Get the return value from geocode.maps.co API
        CoordResponse coordResponse;
        try {
            // Make the API request and get the response
            String response = httpGet(coordUrlString);
            
            // Parse the JSON response
            Gson gson = new Gson();
            coordResponse = gson.fromJson(response, CoordResponse.class);
            
            // Check if coordinates were found
            if (coordResponse.lat == null || coordResponse.lon == null) {
                JOptionPane.showMessageDialog(null, "Could not find coordinates for the city: " + zipcode);
                return;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error fetching coordinates: " + ex.getMessage());
            return;
        }

        // Create a URL for the Weather API request
        String weatherApiKey = "3a5a2e8025a0f5c7427858342ebbf3ee"; // "Default" API key
        // OpenWeatherMap API URL for current weather data *EXAMPLE*:
        // https://api.openweathermap.org/data/3.0/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}
        String urlString = "https://api.openweathermap.org/data/3.0/onecall?lat=" 
                         + coordResponse.lat
                         + "&lon=" + coordResponse.lon
                         // excluding exclude call (optional)
                         + "&appid=" + weatherApiKey;
        
        try {
            // Make the API request and get the response
            String response = httpGet(urlString);
            
            // Parse the JSON response
            Gson gson = new Gson();
            WeatherResponse weatherResponse = gson.fromJson(response, WeatherResponse.class);
            
            // Display the weather information
            JOptionPane.showMessageDialog(null, "Weather in " + zipcode + ": " + weatherResponse.weather[0].description);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error fetching weather data: " + ex.getMessage());
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        // Clear the text field when it gains focus
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Optionally handle focus lost event
    }

    public static void main(String[] args) {
        // Create an instance of the WeatherGUI
        new WeatherGUI();
    }

}

class CoordResponse {
    String lat;
    String lon;

    public CoordResponse (String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }
}

class WeatherResponse {
    Weather[] weather;

    class Weather {
        String description;
    }

}