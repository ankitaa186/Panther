package com.ankit.panther.rs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ankit.panther.model.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("item")
public class ItemResource {
	
	@GET
	@Produces("application/json")
	public String getAllItems(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Panther");
	    EntityManager em = factory.createEntityManager();
	    
	    String returnString=null;
		List<Item> itemList = (List<Item>)em.createNamedQuery("Item.findAll", Item.class).getResultList();
		ObjectMapper mapper = new ObjectMapper();
		try {
			returnString = mapper.writeValueAsString(itemList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnString;
		
	}

}
