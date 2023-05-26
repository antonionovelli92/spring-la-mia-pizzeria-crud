package org.java.pizza.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

import org.java.pizza.pojo.Pizza;
import org.java.pizza.service.*;

@SpringBootApplication
public class PizzaApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;

	

	public PizzaApplication(PizzaService pizzaService) {
		setPizzaService(pizzaService);
	}



	public PizzaService getPizzaService() {
		return pizzaService;
	}



	public void setPizzaService(PizzaService pizzaService) {
		this.pizzaService = pizzaService;
	}



	@Override
	public void run(String... args) throws Exception {
		
		Pizza p = new Pizza("Margherita", "Sugo e mozzarella", "www.fotomiapizza.ciro", 10);
		
		System.out.println(p);
		
		pizzaService.save(p);
		
		List<Pizza> pizze = pizzaService.findAll();
		System.out.println(pizze);
		
		Optional<Pizza> optPizza = pizzaService.findById(1);
		
		if (optPizza.isPresent()) {
			
			Pizza dbPizza = optPizza.get();
			
			System.out.println("Pizza con id 1");
			System.out.println("--------------");
			System.out.println(dbPizza);
		} else 
			System.out.println("Pizza con id 1 non trovato :-(");
	}
}

