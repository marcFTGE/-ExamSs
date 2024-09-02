package be.iccbxl.pid.controller;

import be.iccbxl.pid.model.Troupe;
import be.iccbxl.pid.model.TroupeService;
import be.iccbxl.pid.model.Type;
import be.iccbxl.pid.model.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("troupes")
public class TroupeController {

    @Autowired
    private TroupeService service;

    @GetMapping("/troupes")
    public String index2(Model model) {
        List<Troupe> troupes = service.getAll();

        model.addAttribute("troupes", troupes);
        model.addAttribute("title", "Liste des troupes");

        return "troupes/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") String id) {
        Troupe troupe = service.get(id);

        model.addAttribute("troupe", troupe);
        model.addAttribute("title", "Fiche d'une troupe");

        return "type/show";
    }
}
