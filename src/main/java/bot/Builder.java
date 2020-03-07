package bot;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import util.ReadToken;

import javax.security.auth.login.LoginException;

public class Builder {
    public Builder(){
        try{
            JDABuilder builder = new JDABuilder(AccountType.BOT);
            ReadToken token = new ReadToken();
            builder.setToken(token.getDiscordToken());
            builder.addEventListeners(new MessageListener());
            builder.build();

        }catch (LoginException LE){
            System.out.println("Login Error - \n");
            LE.printStackTrace();
        }

    }
}
