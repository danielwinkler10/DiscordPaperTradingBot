import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;

public class Main {
    public static void main(String[] args){
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "Njg1NjM4MTM3NDg5MjYwNTY5.XmLkXw.eYnurRxlegp11xRh4YQwIyCr9MU";
        builder.setToken(token);
    }
}
