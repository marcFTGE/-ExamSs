package be.iccbxl.pid.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import be.iccbxl.pid.model.Troupe;
import be.iccbxl.pid.model.TroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import be.iccbxl.pid.model.Artist;
import be.iccbxl.pid.model.ArtistService;

@Controller
public class ArtistController {
	@Autowired
	ArtistService service;

	@Autowired
	TroupeService troupeService;

	@GetMapping("/artists")
	public String index(Model model) {
		List<Artist> artists = service.getAllArtists();

	    model.addAttribute("artists", artists);
	    model.addAttribute("title", "Liste des artistes");
	
        return "artist/index";

    }
	
	@GetMapping("/artists/{id}")
    public String show(Model model, @PathVariable("id") String id) {
		Artist artist = service.getArtist(id);

		model.addAttribute("artist", artist);
		model.addAttribute("title", "Fiche d'un artiste");
		
        return "artist/show";
    }
	
	@GetMapping("/artists/create")
	public String create(Model model) {
		Artist artist = new Artist(null,null, null);

		model.addAttribute("artist", artist);
		
		return "artist/create";
	}
	
	@PostMapping("/artists/create")
	public String store(@Valid @ModelAttribute("artist") Artist artist, BindingResult bindingResult, Model model) {
	    
		if (bindingResult.hasErrors()) {
			return "artist/create";
		}
			    
	    service.addArtist(artist);
	    
	    return "redirect:/artists/"+artist.getId();
	}
	
	@GetMapping("/artists/{id}/edit")
	public String edit(Model model, @PathVariable("id") String id, HttpServletRequest request) {
		Artist artist = service.getArtist(id);
		
		model.addAttribute("artist", artist);
		
		//Générer le lien retour pour l'annulation 
		String referrer = request.getHeader("Referer");

		if(referrer!=null && !referrer.equals("")) {
			model.addAttribute("back", referrer);
		} else {
			model.addAttribute("back", "/artists/"+artist.getId());
		}
				
		return "artist/edit";
	}
	
	@PutMapping("/artists/{id}/edit")
	public String update(@Valid @ModelAttribute("artist") Artist artist, BindingResult bindingResult, @PathVariable("id") String id, Model model) {
	    
		if (bindingResult.hasErrors()) {
			return "artist/edit";
		}
		
		Artist existing = service.getArtist(id);
		
		if(existing==null) {
			return "artist/index";
		}
		
		Long indice = (long) Integer.parseInt(id);
		
		artist.setId(indice);
	    service.updateArtist(artist.getId(), artist);
	    
		model.addAttribute("artist", artist);
	    
	    return "redirect:/artists/"+artist.getId();
	}
	
	@DeleteMapping("/artists/{id}")
	public String delete(@PathVariable("id") String id, Model model) {
		Artist existing = service.getArtist(id);
		
		if(existing!=null) {
			Long indice = (long) Integer.parseInt(id);
		
	    	service.deleteArtist(indice);
		}
	    	    
	    return "redirect:/artists";
	}

	@GetMapping("/addTroupe/{idArtist}")
	public String addtroupeToArtist(@PathVariable("idArtist") Long idArtist, Model model){
		Artist artist = service.getArtist(String.valueOf(idArtist));
		model.addAttribute("Artist", artist);
		List<Troupe> troupes = troupeService.getAll();
		model.addAttribute("troupes", troupes);
		return "troupe/addToArtist";
	}

	@PostMapping("/addTroupe/{idArtist}")
	public String addTroupeToArtist(@PathVariable("idArtist") Long idArtist, @RequestParam Long troupeId) {
		Artist artist = service.getArtist(String.valueOf(idArtist));
		Troupe troupe = troupeService.get(String.valueOf(troupeId));

		if (artist != null && troupe != null) {
			artist.setTroupe(troupe);
			service.addArtist(artist);
		}
		return "redirect:/artists";
	}


}
