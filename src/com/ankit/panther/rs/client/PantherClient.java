package com.ankit.panther.rs.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class PantherClient {
	
	public static void main(String[] args){
		Client client = ClientBuilder.newClient();
		String response = client.target("http://localhost:8080/Panther/resources/item/61").request().get(String.class);
		System.out.println(response);
	}

}
 