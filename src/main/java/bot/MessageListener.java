package bot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import player.DiscordUser;
import player.Users;
import stock.Stock;
import stock.StockUtil;

public class MessageListener extends ListenerAdapter {

    private Users userList = new Users();


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        Message msg = event.getMessage();
        User author = event.getAuthor();
        String content =  msg.getContentDisplay();
        MessageChannel channel = msg.getChannel();

        System.out.println("We received a message from " + author.getName() + ": " + content);

        if(content.equals("!createAccount")){
            if (userList.containsUser(author.getId())){
                channel.sendMessage("You already have an account").queue();
            } else {
                DiscordUser user = new DiscordUser(author);
                userList.addUser(user.getId(), user);
                channel.sendMessage("Account Created").queue();
            }
        }

        if(content.equals("!balance")){
            if(userList.containsUser(author.getId())){
                channel.sendMessage("Your balance - " +
                        userList.getUser(author.getId()).getBalance()).queue();
            } else {
                channel.sendMessage("You don't have an account, use !createAccount").queue();
            }
        } else if (content.contains("!balance")){
            String id = content.split(" ")[1];
            if(userList.containsUser(id)){

                channel.sendMessage(id + "'s balance - " +
                        userList.getUser(id).getBalance()).queue();
            } else {
                channel.sendMessage("not a valid user id").queue();
            }
        }



        if(content.contains("!getStockPrice")){
            String stockSymbol = content.split(" ")[1];
            StockUtil stock = new StockUtil();
            channel.sendMessage(stockSymbol + "'s Price is: " +
                    stock.getStockValue(stockSymbol) + "$").queue();
        }

        if(content.contains("!buyStock")){
            String stockSymbol = content.split(" ")[1];
            String amount = content.split(" ")[2];
            boolean success;
            success = userList.getUser(author.getId()).buyStock(stockSymbol, Integer.valueOf(amount));
            if(success){
                channel.sendMessage("Transaction complete").queue();
            } else {
                channel.sendMessage("Error, transaction incomplete").queue();
            }
        }

        if(content.contains("!setBalance")){
            String balance = content.split(" ")[1];
            userList.getUser(author.getId()).setBalance(Double.valueOf(balance));
        }

        if(content.contains("!getStocks")){
            userList.getUser(author.getId()).stocks.forEach((s, stock) ->
                channel.sendMessage(s + " - " + stock).queue());
        }
    }
}
