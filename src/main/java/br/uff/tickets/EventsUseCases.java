package br.uff.tickets;

import java.util.UUID;

public interface EventsUseCases {
    UUID createEvent(final Event event);
}
