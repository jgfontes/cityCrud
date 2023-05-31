package com.jgfontes.CityCrud.Controller;

import com.jgfontes.CityCrud.Entity.City;
import com.jgfontes.CityCrud.Entity.CityEntity;
import com.jgfontes.CityCrud.Repository.CityRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class CityController {

    @Autowired
    private final CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping("/")
    public String list(Model memory) {
        memory.addAttribute("cities",
            cityRepository
                .findAll()
                .stream()
                .map(cityMapped -> new City(cityMapped.getName(), cityMapped.getState()))
                .collect(Collectors.toList()));
        return "/crud";
    }

    @PostMapping("/create")
    public String create(@Valid City city, BindingResult validation, Model memory) {
        if(validation.hasErrors()) {
            validation
                .getFieldErrors()
                .forEach(fieldError -> {
                    memory.addAttribute(
                            fieldError.getField(),
                            fieldError.getDefaultMessage()
                    );
                });
                memory.addAttribute("nameSent", city.getName());
                memory.addAttribute("stateSent", city.getState());
                memory.addAttribute("cities",
                    cityRepository
                            .findAll()
                            .stream()
                            .map(cityMapped -> new City(cityMapped.getName(), cityMapped.getState()))
                            .collect(Collectors.toList()));

                return("/crud");
        } else {
            CityEntity cityEntity = new CityEntity();
            cityEntity.setName(city.getName());
            cityEntity.setState(city.getState());

            cityRepository.save(cityEntity);
            return "redirect:/";
        }
    }

    @GetMapping("/delete")
    public String delete(
            @RequestParam String name,
            @RequestParam String state
    ) {
        Optional<CityEntity> cityEntity = cityRepository.findByNameAndState(name, state);
        cityEntity.ifPresent(cityRepository::delete);
        return "redirect:/";
    }

    @GetMapping("/prepareUpdate")
    public String prepareUpdate(
            @RequestParam String name,
            @RequestParam String state,
            Model memory
    ) {
        Optional<CityEntity> actualCity = cityRepository.findByNameAndState(name, state);

        if(actualCity.isPresent()){
            memory.addAttribute("actualCity", actualCity.get());
            memory.addAttribute("cities",
                    cityRepository
                            .findAll()
                            .stream()
                            .map(city -> new City(city.getName(), city.getState()))
                            .collect(Collectors.toList()));
        }
        return "/crud";
    }

    @PostMapping("/update")
    public String update(
            @RequestParam String actualName,
            @RequestParam String actualState,
            City city
    ) {
//        if(validation.hasErrors()) {
//            validation
//                    .getFieldErrors()
//                    .forEach(fieldError -> {
//                        memory.addAttribute(
//                                fieldError.getField(),
//                                fieldError.getDefaultMessage()
//                        );
//                    });
//            memory.addAttribute("nameSent", city.getName());
//            memory.addAttribute("stateSent", city.getState());
//            memory.addAttribute("cities", cities);
//
//            return("/crud");
//        } else {
            System.out.println("ACTUAL NAME AND STATE: " + actualName + actualState);
            var actualCity = cityRepository.findByNameAndState(actualName, actualState);
            if(actualCity.isPresent()) {
                System.out.println("ENTERING IF LOOP");
                var foundCity = actualCity.get();
                foundCity.setName(city.getName());
                foundCity.setState(city.getState());

                cityRepository.saveAndFlush(foundCity);
            }
//            return ("redirect:/");
        }
    }
}
