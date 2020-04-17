package dk.creditoro.clientrest;

import java.io.IOException;

import dk.creditoro.exceptions.HttpStatusException;

public class TestingStuffFoolAround {

	public static void main(String[] args)
	{
		Login login = new Login();
		try {
			System.out.println(login.signIn("string@string.dk", "string"));
			System.out.println("token:" + login.getToken());
		} catch (IOException e) {
			System.out.println("IO error: " + e);
		} catch (HttpStatusException e){
			System.out.println("Http error: " + e);
		}
		Channels channels = new Channels();

	try {
		String name = "DR999";
		System.out.println("New Channel name: " + name);
		System.out.println(channels.postChannel(name, login.getToken()));
		channels.getChannels("");
	} catch (IOException e) {
		System.out.println("IO error: " + e);
	} catch (HttpStatusException e){
		System.out.println("Http error: " + e);
	}
	
}

}

