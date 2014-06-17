package com.ankit.panther.rs;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ankit.panther.model.Item;
import com.ankit.panther.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("item")
public class ItemResource {

	@GET
	@Produces("application/json")
	public String getAllItems() {

		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("Panther");
		EntityManager em = factory.createEntityManager();

		String returnString = null;
		em.getTransaction().begin();
		List<Item> itemList = (List<Item>) em.createNamedQuery("Item.findAll",
				Item.class).getResultList();
		em.getTransaction().rollback();
		ObjectMapper mapper = new ObjectMapper();
		try {
			returnString = mapper.writeValueAsString(itemList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.close();
		return returnString;

	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getItem(@PathParam("id") Integer id) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("Panther");
		EntityManager em = factory.createEntityManager();

		String returnString = null;
		Item item = em.find(Item.class, id);
		ObjectMapper mapper = new ObjectMapper();
		try {
			returnString = mapper.writeValueAsString(item);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.close();
		return returnString;

	}

	@POST
	@Produces("application/json")
	public String addItem(@FormParam("user") String user,
			@FormParam("title") String title, @FormParam("desc") String desc,
			@FormParam("due_date") String dueDate) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("Panther");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		String returnString = null;
		Item item = new Item();
		Query userQuery = em.createQuery("select u from User u where u.username=?1");
		userQuery.setParameter(1, user);
		List<User> userList = (List<User>)userQuery.getResultList();
		item.setUserBean(userList.get(0));
		item.setTitle(title);
		item.setDescription(desc);
		item.setInsDttm(new Date());
		item.setDueDateDttm(null);
		item.setIsCompleted(false);
		em.persist(item);
		em.getTransaction().commit();
		em.refresh(item);
		ObjectMapper mapper = new ObjectMapper();
		try {
			returnString = mapper.writeValueAsString(item);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		em.close();
		return returnString;
	}
	
	@POST
	@Path("{id}")
	@Produces("application/json")
	public String updateItem(@PathParam("id") int id,@FormParam("name") String name,
			@FormParam("value") String value) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("Panther");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		String returnString = null;
		Item item = em.find(Item.class, id);
		if(name.equals("isCompleted")){
			item.setIsCompleted(value.equalsIgnoreCase("true"));
		}else if(name.equals("title")){
			item.setTitle(value);
		}else if(name.equals("desc")){
			item.setDescription(value);
		}
		item.setLastModDttm(new Date());
		em.merge(item);
		em.getTransaction().commit();
		em.refresh(item);
		ObjectMapper mapper = new ObjectMapper();
		try {
			returnString = mapper.writeValueAsString(item);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		em.close();
		return returnString;
	}
	
	@DELETE
	@Path("{id}")
	@Produces("application/json")
	public String deleteItem(@PathParam("id") int id){
		
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("Panther");
		EntityManager em = factory.createEntityManager();

		String returnString = null;
		Item item = em.find(Item.class, id);
		em.getTransaction().begin();
		em.remove(item);
		em.getTransaction().commit();
		//em.getTransaction().commit();
		ObjectMapper mapper = new ObjectMapper();
		try {
			returnString = mapper.writeValueAsString(item);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.close();
		return returnString;
		
	}

}
