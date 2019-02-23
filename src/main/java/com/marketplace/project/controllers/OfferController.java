package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.OfferRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.Category;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import com.marketplace.project.entities.enums.CategoryTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping(value = "/offer")
public class OfferController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    //delete Offer by Id
   // @ResponseBody - get JSON if REST


//    @GetMapping(path = "/{id}")
//    public
//    void deleteOfferById(@PathVariable Integer id) {
//        offerRepository.deleteById(id);
//    }

    //New Offer
//    @GetMapping(path = "/new-offer")
//    public
//    String addNewOffer(@RequestParam String condition, @RequestParam String description, @RequestParam BigDecimal price, @RequestParam Boolean status, @RequestParam String title, @RequestParam User sellerId, Model model) {
//
//        Offer offer = new Offer();
//        Category category1 = new Category();
//
//        offer.setCondition(condition);
//        offer.getCreationTimeAndDate();
//        offer.setOfferDescription(description);
//        offer.setPrice(price);
//        offer.setStatus(status);
//        offer.setTitle(title);
//
//        offer.setSeller(sellerId);
//
//        offerRepository.save(offer);
//        return "offersbyid";
//    }



    @RequestMapping(path = "/new-offer", method = RequestMethod.GET)
    public
    String addNewOffer() {
        return "addOffer";
    }

    @PostMapping(path = "/save")
    public
    String addNewOffer(@ModelAttribute Offer offer, Model model) {
        offerRepository.save(offer);
        model.addAttribute("offers", offerRepository.findAll());
        return "offersbyid";
    }

    //get All Offers
    @GetMapping(path = "/alloffers")
    public
    String getAllOffers(Model model) {
        model.addAttribute("offers", offerRepository.findAll());
        return "offersbyid";
    }


    //update Offer
    @PutMapping(path = "/upd-offer/{id}")
    public ResponseEntity<Object> updOffer(@RequestBody Offer offer, Integer id) {

        Optional<Offer> offerOptional = offerRepository.findById(id);

        if (!offerOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        offer.setId(id);
        offerRepository.save(offer);
        return ResponseEntity.noContent().build();
    }




    //find all offer by seller id
    @GetMapping(value = "/offers/{id}")
    public String findAllOfferBySellerId (@PathVariable int id, Model model) {

        model.addAttribute("offers", offerRepository.findBySeller(userRepository.findById(id)));
        return "bhb";

    }


    //find all offer by title
    @GetMapping(path = "/title/{title}")
    public
    Iterable<Offer> findOfferByTitle(@PathVariable String title) {
        Iterable<Offer> offers = offerRepository.findByTitle(title);
        return offers;

    }

//get ENUM
    @RequestMapping(value = { "/enum" }, method = RequestMethod.GET)
    public String selectOptionExample1Page(Model model) {

        model.addAttribute("category", CategoryTypes.values());


        return "offersbyid";
    }
}
