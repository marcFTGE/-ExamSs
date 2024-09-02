package be.iccbxl.pid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.model.Location;
import be.iccbxl.pid.model.LocationService;

@Controller
public class LocationController {
	@Autowired
	LocationService service;

	@GetMapping("/locations")
    public String index(Model model) {
		List<Location> locations = service.getAll();

		model.addAttribute("locations", locations);
		model.addAttribute("title", "Liste des lieux de spectacle");
		
        return "location/index";
    }
	
	@GetMapping("/locations/{id}")
    public String show(Model model, @PathVariable("id") String id) {
		Location location = service.get(id);

		model.addAttribute("location", location);
		model.addAttribute("title", "Fiche d'un lieu de spectacle");
		
        return "location/show";
    }

}
