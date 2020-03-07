package player;


import java.util.HashMap;

public class Users {
    private HashMap<String, DiscordUser> usersMap = new HashMap<>();


    public HashMap<String, DiscordUser> getUsersMap() {
        return usersMap;
    }

    public void addUser(String id, DiscordUser user) {
        usersMap.put(id, user);
    }

    public DiscordUser getUser(String id){
       return usersMap.get(id);
    }

    public boolean containsUser(String id){
        return usersMap.containsKey(id);
    }


}
