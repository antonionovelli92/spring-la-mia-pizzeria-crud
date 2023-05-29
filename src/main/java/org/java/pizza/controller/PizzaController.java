package org.java.pizza.controller;

import java.util.List;
import java.util.Optional;

import org.java.pizza.pojo.Pizza;
import org.java.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Pizza> pizze = pizzaService.getAllPizze();
        model.addAttribute("pizze", pizze);
        return "index";
    }
    @GetMapping("/pizze/{id}")
	public String getPizza(
			Model model,
			@PathVariable("id") int id
	) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		
		return "pizza";
	}
//    Creazione 
    
    @GetMapping("/new")
    public String showCreatePizzaForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "createPizzaForm"; 
    }
    @PostMapping("/create")
    public String createPizza(Pizza pizza) {
        pizzaService.save(pizza);
        return "redirect:/";
    }
    
//    Filtro
    @PostMapping("/")
    public String indexWithFilter(Model model, @RequestParam("filtro") String filtro) {
        List<Pizza> pizze = pizzaService.getAllPizzeByNome(filtro);
        model.addAttribute("pizze", pizze);
        model.addAttribute("filtro", filtro);
        return "index";
    }

}