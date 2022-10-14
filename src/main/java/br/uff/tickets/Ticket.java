package br.uff.tickets;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;

public record Ticket(@Id UUID id,
                     @NotNull Type type,
                     @NotNull BigDecimal price,
                     @PositiveOrZero Integer quantity) {
}
