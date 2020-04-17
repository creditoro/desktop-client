package dk.creditoro.rest_client;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The type Channels.
 */
public class Channels {
    /**
     * The Http manager.
     */

    HttpManager httpManager;
    private static final String PATH = "/channels/";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Instantiates a new Channels.
     */
    public Channels() {
        //Create HttpManager
        httpManager = new HttpManager("api.creditoro.nymann.dev", 443, "https");
    }

    /**
     * Post channel boolean.
     *
     * @param name  the name
     * @param token the token
     * @return the boolean
     */
    public boolean postChannel(String name, String token) {
        JSONObject json = new JSONObject();
        json.put("name", name);

        //Return true if httpMangerPost it not null
        return (httpManager.post(PATH, json, token) != null);
    }

    /**
     * Gets channels.
     */
    public void getChannels() {
        List<Channel> channelList = getList(httpManager.get(PATH, ""));
        LOGGER.info(channelList.toString());
    }

    /**
     * Gets channel.
     *
     * @param quarry the quarry
     */
    public void getChannel(String quarry) {
        List<Channel> channelList = getList(httpManager.get(PATH, "?=" + quarry));
        LOGGER.info(channelList.toString());
    }


    private List<Channel> getList(String response) {
        JSONArray channels = new JSONArray(response);
        List<Channel> temp = new ArrayList<>();

        for (int i = 0; i < channels.length(); i++) {
            JSONObject channel = channels.getJSONObject(i);
            String identifier = channel.getString("identifier");
            String name = channel.getString("name");

            temp.add(new Channel(identifier, name));
        }

        return temp;
    }
}
