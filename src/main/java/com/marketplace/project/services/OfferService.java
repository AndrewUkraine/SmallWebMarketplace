package com.marketplace.project.services;

import com.marketplace.project.dao.jpadatarepository.OfferRepository;
import com.marketplace.project.entities.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
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
}
