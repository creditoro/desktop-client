package dk.creditoro.clientrest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Channels {
	HttpManager httpManager;

	public Channels (){
        //Create HttpManager
        httpManager = new HttpManager("api.creditoro.nymann.dev", 443, "https");
	}
    public boolean postChannel(String name, String token) {
        //Funktionalitet
        JSONObject json = new JSONObject();
        json.put("name", name);

		//Return true if httpMangerPost it not null
        return (httpManager.post("/channels/", json, token) != null);
    }

	//TODO NEED REFATOR to return some thing GOOD. not just print the result
	//TODO make these to method on single thing? - Kevin
    public void getChannels() {
        //Funktionalitet
        List<Channel> channelList = getList(httpManager.get("/channels/", ""));
        System.out.println(channelList);
    }
    public void getChannel(String quarry) {
        //Funktionalitet
        List<Channel> channelList = getList(httpManager.get("/channels/", "?=" + quarry));
        System.out.println(channelList);
    }



    private List<Channel> getList(String response)
    {
        JSONArray channels = new JSONArray(response);
        List<Channel> temp = new ArrayList<>();

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
