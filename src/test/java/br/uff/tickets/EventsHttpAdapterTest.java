package br.uff.tickets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventsHttpAdapterTest {

    private static final String LOCATION_URL = "http://localhost/v1/events/";
    private final String API_ENDPOINT = "/v1/events";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EventsUseCases eventsUseCases;

    @Autowired
    EventsHttpAdapter eventsHttpAdapter;

    @Test
    void shouldCreateEventSuccessfully() throws Exception {
        final var date = LocalDate.of(2024, Month.MARCH, 15);
        final var time = LocalTime.of(19, 0, 0);
        final var datetime = LocalDateTime.of(date, time);

        final List<Ticket> tickets = List.of(
                new Ticket(null, Type.INTEIRA, BigDecimal.valueOf(800), 4000),
                new Ticket(null, Type.ESTUDANTE, BigDecimal.valueOf(250), 3000),
                new Ticket(null, Type.PCD, BigDecimal.valueOf(250), 500),
                new Ticket(null, Type.IDOSO, BigDecimal.valueOf(250), 500));

        final var event = new Event(null, "Paramore", "South America Tour", "Qualistage",
                datetime, tickets);

        final var id = UUID.randomUUID();

        doReturn(id).when(eventsUseCases).createEvent(any());

        mockMvc.perform(post(API_ENDPOINT)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", LOCATION_URL + id));
    }
}