import org.jibble.pircbot.*;

public class MyPercBot extends PircBot {
    private Backend backend = null;
    static String target = "#CS2336";
    public static void main(String[] args) throws Exception {
        // Create a new instance of the bot
        MyPercBot bot = new MyPercBot();
        // Set the bot's nickname
        bot.setName("MyPircBot");
        bot.connect("irc.libera.chat");
        bot.joinChannel(target);
        bot.sendMessage(target, "Hello, I am MyPircBot! Type !help for commands.");
    }

    public MyPercBot() {
        // Set the bot's name and other properties
        this.setName("MyPircBot");
        this.setVersion("MyPircBot v1.0");
        // Initialize the backend
        backend = new Backend();
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        // Respond to messages in the channel
        if (message.equalsIgnoreCase("!hello")) {
            sendMessage(channel, "Hello " + sender + "!");
        } else if (message.equalsIgnoreCase("!help")) {
            sendMessage(channel, "Available commands: !hello, !help");
        } else if (message.startsWith("!weather ")) {
            String searchInput = message.substring(9).trim();
            if(searchInput.isEmpty()) {
                sendMessage(channel, "Please provide a city name or zip code.");
                return;
            }
            backend.setSearchInput(searchInput);
            String response = backend.getWeather();
            sendMessage(target, response);
        } else if (message.startsWith("!stock ")) {
            String stockSymbol = message.substring(7).trim();
            if(stockSymbol.isEmpty()) {
                sendMessage(target, "Please provide a stock symbol.");
                return;
            }
            backend.setStockSymbol(stockSymbol);
            String stockResponse = backend.getStockPrice();
            sendMessage(target, stockResponse);
        } else {
            sendMessage(channel, "I don't understand that command.");
        }
    }
}
