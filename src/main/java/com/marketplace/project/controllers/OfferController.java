package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.OfferRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.enums.ConditionType;
import com.marketplace.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
//@RequestMapping(value = "/offers")
public class OfferController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CategoryService categoryService;


    //delete Offer by Id
   // @ResponseBody - get JSON if REST
//        RedirectAttributes attributes
//        attributes.addAttribute("massage", id + "- offer was deleted!");

    //delete offer
    @RequestMapping("offer/delete/{id}")
    public String deleteOfferById(@PathVariable Integer id, Model model) {
        offerRepository.deleteById(id);
       model.addAttribute("offers", offerRepository.findAll());
       return "redirect:/offers";
    }

    //add new Offer
    @RequestMapping("offer/new")
    public
    String addNewOffer(Model model)
    {
        model.addAttribute("offer", new Offer());
        model.addAttribute("conditions",ConditionType.values());
        model.addAttribute("categories", categoryService.findAll());
        return "addOffer";
    }

    //save offer
    @RequestMapping(value = "offer", method = RequestMethod.POST)
    public String saveOffer (@ModelAttribute Offer offer) {
        LocalDateTime today = LocalDateTime.now();
            offer.setCreationTimeAndDate(today);
            offerRepository.save(offer);
            return "redirect:/offers";

    }

    //get All Offers
    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public String getAllOffers(Model model) {
        model.addAttribute("offers", offerRepository.findAll());
        return "offers";
    }


    //edit offer
    @GetMapping(value = "offer/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("offer", offerRepository.findById(id));
        model.addAttribute("conditions", ConditionType.values());
        model.addAttribute("categories", categoryService.findAll());
        return "updateOffer";
    }


    //find all offer by seller id
    @GetMapping(value = "/offers/{id}")
    public String findAllOfferBySellerId (@PathVariable int id, Model model) {
        model.addAttribute("offers", offerRepository.findBySeller(userRepository.findById(id)));
        return "offers";

    }


    //find all offer by title
    @GetMapping(path = "/title/{title}")
    public String findOfferByTitle(@PathVariable String title) {
       offerRepository.findByTitle(title);
        return "offers";
    }

}
