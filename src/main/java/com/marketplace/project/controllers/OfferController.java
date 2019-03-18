package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.ImageRepository;
import com.marketplace.project.dao.jpadatarepository.OfferRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import com.marketplace.project.entities.enums.ConditionType;
import com.marketplace.project.services.CategoryService;
import com.marketplace.project.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
//@RequestMapping(value = "/offers")
public class OfferController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;


    //path to file for spring
    @Value("${upload.path}")
    private String uploadPath;


    //delete Offer by Id
   // @ResponseBody - get JSON if REST
//        RedirectAttributes attributes
//        attributes.addAttribute("massage", id + "- offer was deleted!");

    //delete offer
    @RequestMapping("offer/delete/{id}")
    public String deleteOfferById(@PathVariable Integer id, Model model) {


//delete images from hardware
            for (Image image : imageRepository.getAllByImageOffer(offerRepository.findById(id))) {

                if (!image.getName().isEmpty()) {

                    File file = new File(image.getPath() + image.getName());

                    if (file.delete()) {
                        System.out.println(file.getName() + " is deleted!");
                    } else {
                        System.out.println("Delete operation is failed.");
                    }
                }

        }

        offerRepository.deleteById(id);
       model.addAttribute("offers", offerRepository.findAll());
       return "redirect:/offers";
    }

    //add new Offer
    @RequestMapping("offer/new")
    public
    String addNewOffer(Model model) throws IOException {
        model.addAttribute("offer", new Offer());
        model.addAttribute("conditions",ConditionType.values());
        model.addAttribute("categories", categoryService.findAll());
        return "addOffer";
    }


    //save offer
    @RequestMapping(value = "offer", method = RequestMethod.POST)
    public String saveOffer (@AuthenticationPrincipal User user,  @ModelAttribute Offer offer, @RequestParam("files") MultipartFile[] files) throws IOException {

        LocalDateTime today = LocalDateTime.now();
            offer.setCreationTimeAndDate(today);

        List<Image>images = new ArrayList<>();



        offer.setSeller(user);

//        if (!user.getSellList().isEmpty())
//        {
//            user.getSellList().add(offer);
//        }
//
//        else {
//            List<Offer> offerList = new ArrayList<>();
//            offerList.add(offer);
//            user.setSellList(offerList);
//        }

        offerRepository.save(offer);


        StringBuilder fileNames = new StringBuilder();


                for (MultipartFile file : files) {

                    if (file!= null && !file.getOriginalFilename().isEmpty() && file.getContentType().equals("image/jpeg")) {

                    String uuidFile = UUID.randomUUID().toString();

                    Path fileNameAndPath = Paths.get(uploadPath, uuidFile + "." + file.getOriginalFilename());

                  //      File uploadDir = new File(uploadPath);
//                        if (!uploadPath.exists()) {
//                            uploadDir.mkdir();
//                        }

                    fileNames.append(uuidFile + "." + file.getOriginalFilename()+ "." + "jpg");

                    Image image = new Image();
                    image.setData(file.getBytes());
                    image.setName(uuidFile + "." + file.getOriginalFilename());
                    image.setPath(uploadPath);
                    image.setImageOffer(offer);
                    imageService.save(image);
                    images.add(image);
                    offer.setImages(images);

                    try {
                        Files.write(fileNameAndPath, file.getBytes());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

       // model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());


            return "redirect:/offers";
    }



    //get All Offers
    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public String getAllOffers(Model model, @AuthenticationPrincipal User user) {

        model.addAttribute("offers", offerRepository.findBySeller(userRepository.findById(user.getId())));
        return "offers";
    }

    //pre-View Offer
    @GetMapping(value = "offer/{id}")
    public String getOfferPreView(@PathVariable Integer id, Model model) {
        model.addAttribute("offer", offerRepository.findById(id));
        model.addAttribute("conditions", ConditionType.values());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("images", imageRepository.getAllByImageOffer(offerRepository.findById(id)));
        return "offerPreView";
    }


    //edit offer
    @GetMapping(value = "offer/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("offer", offerRepository.findById(id));
        model.addAttribute("conditions", ConditionType.values());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("images", imageRepository.getAllByImageOffer(offerRepository.findById(id)));
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
