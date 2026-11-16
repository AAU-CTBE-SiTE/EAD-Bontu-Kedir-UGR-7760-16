
package com.example.actor_management.service;

import com.example.actor_management.model.Actor;
import com.example.actor_management.repository.ActorRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    // Get all actors
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    // Get actor by ID
    public Actor getActorById(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Actor not found"
                        )
                );
    }

    // Create actor
    public Actor createActor(Actor actor) {
        actor.setId(null);
        return actorRepository.save(actor);
    }

    // Update entire actor
    public Actor updateActor(Long id, Actor updatedActor) {

        Actor existingActor = getActorById(id);

        existingActor.setName(updatedActor.getName());
        existingActor.setMovieName(updatedActor.getMovieName());

        return actorRepository.save(existingActor);
    }

    // Delete actor
    public void deleteActor(Long id) {

        if (!actorRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Actor not found"
            );
        }

        actorRepository.deleteById(id);
    }

    // Partially update actor
    public Actor patchActor(Long id, Actor updatedActor) {

        Actor existingActor = getActorById(id);

        if (updatedActor.getName() != null) {
            existingActor.setName(updatedActor.getName());
        }

        if (updatedActor.getMovieName() != null) {
            existingActor.setMovieName(updatedActor.getMovieName());
        }

        return actorRepository.save(existingActor);
    }
}
