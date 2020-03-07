package util;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadToken {

    private String discordToken;
    static private String stockToken;

    public ReadToken(){
        try {
            File myObj = new File("src/main/token.txt");
            Scanner myReader = new Scanner(myObj);
            discordToken = myReader.nextLine();
            stockToken = myReader.nextLine();
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getDiscordToken() {
        return discordToken;
    }
    static public String getStockToken() {
        return stockToken;
    }
}
