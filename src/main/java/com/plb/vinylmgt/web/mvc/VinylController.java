package com.plb.vinylmgt.web.mvc;

import com.plb.vinylmgt.domain.User;
import com.plb.vinylmgt.domain.Vinyl;
import com.plb.vinylmgt.service.AuthorService;
import com.plb.vinylmgt.service.UserService;
import com.plb.vinylmgt.service.VinylService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vinyls")
public class VinylController {

    private final VinylService vinylService;
    private final AuthorService authorService;
    private final UserService userService;

    public VinylController(VinylService vinylService, AuthorService authorService, UserService userService) {
        this.vinylService = vinylService;
        this.authorService = authorService;
        this.userService = userService;
    }

    @GetMapping
    public String vinyls(Model model) {
        List<Vinyl> vinylsByUser = vinylService.getVinylsByUser("toto@toto.com");
        model.addAttribute("vinyls", vinylsByUser);
        return "vinyls";
    }

    @GetMapping("/{id}")
    public String vinyls(@PathVariable Long id, Model model) {
        Optional<Vinyl> vinyl = vinylService.getById(id);
        vinyl.ifPresent(value -> model.addAttribute("vinyl", value));
        return "vinyl";
    }

    @GetMapping("/new")
    public String initNewVinyl(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("vinyl", new Vinyl());
        return "new-vinyl";
    }

    @PostMapping
    public ModelAndView createNewVinyl(Vinyl newVinyl) {
        vinylService.save(newVinyl);
        return new ModelAndView("redirect:/vinyls");
    }
}
