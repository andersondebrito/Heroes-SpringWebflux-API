package com.andersondebrito.herosapi.service;

import com.andersondebrito.herosapi.document.Heroes;
import com.andersondebrito.herosapi.repository.HeroesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {

    private final HeroesRepository repository;

    public HeroesService(HeroesRepository heroesRepository){
        this.repository = heroesRepository;
    }

    public Flux<Heroes> findAll(){
        return Flux.fromIterable(this.repository.findAll());
    }

    public Mono<Heroes> findByIdHero(String id) {
        return Mono.justOrEmpty(this.repository.findById(id));
    }

    public Mono<Heroes> save(Heroes hero){
        return Mono.justOrEmpty(this.repository.save(hero));
    }

    public Mono<Boolean> delete(String id) {
        repository.deleteById(id);
        return Mono.just(true);
    }
}