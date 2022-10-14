package br.uff.tickets;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventsService implements EventsUseCases {
    @Override
    public UUID createEvent(Event event) {
        return null;
    }
}
