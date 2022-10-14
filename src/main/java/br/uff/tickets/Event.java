package br.uff.tickets;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;


public record Event(@Id UUID id,
                    @NotNull String artist,
                    @NotNull String name,
                    @NotEmpty String venue,
                    @NotNull LocalDateTime datetime,
                    @NotEmpty Collection<Ticket> tickets) {
}
