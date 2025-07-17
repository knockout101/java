import org.jibble.pircbot.*;

public class MyPercBot extends PircBot {
    public static void main(String[] args) throws Exception {
        // Create a new instance of the bot
        MyPercBot bot = new MyPercBot();
        // Set the bot's nickname
        bot.setName("MyPircBot");
        bot.connect("irc.us.libera.chat");
        bot.joinChannel("#CS2336");
        bot.sendMessage("#CS2336", "Hello, I am MyPircBot! Type !help for commands.");
    }

    public MyPercBot() {
        // Set the bot's name and other properties
        this.setName("MyPircBot");
        this.setVersion("MyPircBot v1.0");
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        // Respond to messages in the channel
        if (message.equalsIgnoreCase("!hello")) {
            sendMessage(channel, "Hello " + sender + "!");
        } else if (message.equalsIgnoreCase("!help")) {
            sendMessage(channel, "Available commands: !hello, !help");
        } else if (message.equalsIgnoreCase("!weather")) {
            
        
        } else {
            sendMessage(channel, "I don't understand that command.");
        }
    }

}
