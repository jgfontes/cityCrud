package com.jgfontes.CityCrud.Controller;

import com.jgfontes.CityCrud.Entity.City;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class CityController {

    private Set<City> cities;

    public CityController() {
        cities = new HashSet<>();
    }

    @GetMapping("/")
    public String list(Model memory) {
        memory.addAttribute("cities", cities);
        return "/crud";
    }

    @PostMapping("/create")
    public String create(City city) {
        cities.add(city);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(
            @RequestParam String name,
            @RequestParam String state
    ) {
        System.out.println("City name and state" + name + state);
        cities.removeIf(city -> {
            return (city.getName().equals(name) && city.getState().equals(state));
        });

        return "redirect:/";
    }

    @GetMapping("/prepareUpdate")
    public String prepareUpdate(
            @RequestParam String name,
            @RequestParam String state,
            Model memory
    ) {
        var actualCity = cities
                                        .stream()
                                        .filter(city ->
                                                city.getName().equals(name) &&
                                                city.getState().equals(state)
                                        ).findAny();
        if(actualCity.isPresent()){
            memory.addAttribute("actualCity", actualCity.get());
            memory.addAttribute("cities", cities);
        }
        return "/crud";
    }

    @PostMapping("/update")
    public String alterar(
            @RequestParam String actualName,
            @RequestParam String actualState,
            City city
    ) {
        cities.removeIf(actualCity -> {
            return (actualCity.getName().equals(actualName) && actualCity.getState().equals(actualName));
        });
        create(city);
        return "redirect:/";
    }
}