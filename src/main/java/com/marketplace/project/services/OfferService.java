package com.marketplace.project.services;

import com.marketplace.project.dao.jpadatarepository.OfferRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private OfferRepository offerRepository;
    private UserRepository userRepository;


    @Autowired
    public OfferService(OfferRepository offerRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
    }

    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    public List<Offer> findAll(Sort sort) {
        return offerRepository.findAll(sort);
    }

    public <S extends Offer> S save(S entity) {
        return offerRepository.save(entity);
    }

    public Optional<Offer> findById(Integer integer) {
        return offerRepository.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return offerRepository.existsById(integer);
    }

    public long count() {
        return offerRepository.count();
    }

    public void deleteById(Integer integer) {
        offerRepository.deleteById(integer);
    }

    public void delete(Offer entity) {
        offerRepository.delete(entity);
    }


    public void deleteAll(Iterable<? extends Offer> entities) {
        offerRepository.deleteAll(entities);
    }

    //To work with new Time package, we need to configure our template engine to use the new Java8TimeDialect:
    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }


//    @Transactional
//    public List<OfferService> getSongPlayersDTOList (List<Offer> songPlayersList){
//        List<OfferService> songPlayersDTOList = new ArrayList<>();
//
//        for (Offer songPlayers : songPlayersList){
//            songInstrumentalistList = new ArrayList<>();
//
//            Offer songPlayersDTO = new Offer();
//            songPlayersDTO.setId(songPlayers.getId());
//            songPlayersDTO.setCondition(songPlayers.getCondition());
//            songPlayersDTO.setOfferDescription(songPlayers.getOfferDescription());
//            songPlayersDTO.setPrice(songPlayers.getPrice());
//            songPlayersDTO.setStatus(songPlayers.getStatus());
//            songPlayersDTO.setTitle(songPlayers.getTitle());
//            songPlayersDTO.setSeller(songPlayers.getSeller());
//
//            for (User people : songPlayers.getSeller()){
//                PeopleDTO peopleDTO = new PeopleDTO();
//                peopleDTO.setId(people.getId());
//                peopleDTO.setHuman(people.getHuman());
//                peopleDTO.setRockGroups(people.getRockGroups());
//                songInstrumentalistList.add(peopleDTO);
//            }
//            songPlayersDTO.setSongInstrumentalistList(songInstrumentalistList);
//
//            songPlayersDTOList.add(songPlayersDTO);
//
//        }
//        return songPlayersDTOList;
//    }

}


//    //Get User by Id
//    @GetMapping(value = "/user-{id}")
//    public String getById(@PathVariable int id, Model model) {
//
//        userRepository.findById(id).ifPresent(o -> model.addAttribute("users", o));
//

