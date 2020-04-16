package dk.creditoro.clientrest;

import dk.creditoro.exceptions.HttpStatusException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Channels {

    public boolean postChannel(String name, String token) throws IOException, HttpStatusException {
        //Create HttpManager
        HttpManager httpManager = new HttpManager("api.creditoro.nymann.dev", 443, "https");

        //Funktionalitet
        JSONObject json = new JSONObject();
        json.put("name", name);

        if(httpManager.post("/channels/", json, token) != null)
        {
            return true;
        } else {
            return false;
        }
    }

    public void getChannels(String q) throws IOException {
        //Connect to httpManager
        HttpManager httpManager = new HttpManager("api.creditoro.nymann.dev", 443, "https");

        //Funktionalitet
        ArrayList<Channel> channelList = getList(httpManager.get("/channels/", q));
        System.out.println(channelList);
    }



    public ArrayList<Channel> getList(String response)
    {
        JSONArray channels = new JSONArray(response);
        ArrayList<Channel> temp = new ArrayList<Channel>();

        for(int i = 0; i < channels.length(); i++)
        {
            JSONObject channel = channels.getJSONObject(i);
            String identifier = channel.getString("identifier");
            String name = channel.getString("name");

            temp.add(new Channel(identifier, name));
        }

        return temp;
    }
}
