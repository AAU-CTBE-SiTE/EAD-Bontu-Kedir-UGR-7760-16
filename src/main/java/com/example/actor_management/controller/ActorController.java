
package com.example.actor_management.controller;

import com.example.actor_management.model.Actor;
import com.example.actor_management.service.ActorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    // GET: Get all actors
    @GetMapping("/actors")
    public ResponseEntity<List<Actor>> getAllActors() {

        return ResponseEntity.ok(
                actorService.getAllActors()
        );
    }

    // GET: Get actor by ID
    @GetMapping("/actor/{id}")
    public ResponseEntity<Actor> getActorById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                actorService.getActorById(id)
        );
    }

    // POST: Create actor
    @PostMapping("/actor")
    public ResponseEntity<Actor> createActor(
            @RequestBody Actor actor) {

        Actor createdActor = actorService.createActor(actor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdActor);
    }

    // PUT: Update entire actor
    @PutMapping("/actor/{id}")
    public ResponseEntity<Actor> updateActor(
            @PathVariable Long id,
            @RequestBody Actor actor) {

        return ResponseEntity.ok(
                actorService.updateActor(id, actor)
        );
    }

    // PATCH: Update selected actor fields
    @PatchMapping("/actor/{id}")
    public ResponseEntity<Actor> patchActor(
            @PathVariable Long id,
            @RequestBody Actor actor) {

        return ResponseEntity.ok(
                actorService.patchActor(id, actor)
        );
    }

    // DELETE: Delete actor
    @DeleteMapping("/actor/{id}")
    public ResponseEntity<Void> deleteActor(
            @PathVariable Long id) {

        actorService.deleteActor(id);

        return ResponseEntity.noContent().build();
    }
}
