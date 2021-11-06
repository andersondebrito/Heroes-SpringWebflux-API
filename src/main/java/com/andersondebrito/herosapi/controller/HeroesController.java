package com.andersondebrito.herosapi.controller;

import com.andersondebrito.herosapi.constants.HeroesConstants;
import com.andersondebrito.herosapi.document.Heroes;
import com.andersondebrito.herosapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HeroesController {

    private static final Logger logger = LoggerFactory.getLogger(HeroesController.class.getName());

    private HeroesService service;

    private HeroesController(HeroesService service) {
        this.service = service;
    }

    @GetMapping(HeroesConstants.HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllHeroes() {
        log.info("request the list off all heroes");
        return service.findAll();
    }

    @GetMapping(HeroesConstants.HEROES_ENDPOINT_LOCAL+ "/{id}")
    public Mono<ResponseEntity<Heroes>> findHeroById(@PathVariable String id){
        log.info("request the hero by id {}", id);
        return service.findByIdHero(id)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK));
    }

    @PostMapping(HeroesConstants.HEROES_ENDPOINT_LOCAL)
    public Mono<Heroes> createHero(@RequestBody Heroes hero){
        log.info("a new hero was created");
        return service.save(hero);
    }

    @DeleteMapping(HeroesConstants.HEROES_ENDPOINT_LOCAL+ "/{id}")
    @ResponseStatus(code = HttpStatus.CONTINUE)
    public Mono<HttpStatus> deleteHeroById(@PathVariable String id){
        service.delete(id);
        log.info("deleting a hero with id {}", id);
        return Mono.just(HttpStatus.CONTINUE);
    }
}
