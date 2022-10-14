package br.uff.tickets;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/events")
public class EventsHttpAdapter {
    private final EventsUseCases eventsUseCases;

    public EventsHttpAdapter(final EventsUseCases eventsUseCases) {
        this.eventsUseCases = eventsUseCases;
    }

    @PostMapping
    public ResponseEntity<Object> createEvent(@RequestBody @Valid Event event) {
        final var eventId = eventsUseCases.createEvent(event);
        final var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
