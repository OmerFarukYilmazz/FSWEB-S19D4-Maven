package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.Converter;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/actor")
public class ActorController {
    private final ActorService actorService;

    @GetMapping
    public List<ActorResponse> findAll(){
        List<Actor> actors =  actorService.findAll();
        return Converter.actorResponseConvert(actors);
    }

    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable Long id){
        Actor actor = actorService.findById(id);
        return Converter.actorResponseConvert(actor);
    }

    @PostMapping ActorResponse save(@Validated @RequestBody ActorRequest actorRequest){
        Actor actor = actorRequest.getActor();
        List<Movie> movies = actorRequest.getMovies();
        for(Movie movie : movies){
            actor.addMovie(movie);
        }
        Actor savedActor = actorService.save(actor);
        return Converter.actorCreateResponseConvert(savedActor);
    }

    @PutMapping("/{id}")
    public ActorResponse update(@RequestBody Actor actor,@PathVariable Long id){
        Actor foundActor = actorService.findById(id);
        actor.setMovies(foundActor.getMovies()); // set found actors movies to actor to be updated
        actor.setId(id); // set found actors id to actor to be updated
        actorService.save(actor); // if actor exist method will save actor to database else will update actor.
        return Converter.actorResponseConvert(actor);
    }

    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable long id){
        Actor actor = actorService.findById(id);
        actorService.delete(actor);
        return Converter.actorResponseConvert(actor);
    }

}
