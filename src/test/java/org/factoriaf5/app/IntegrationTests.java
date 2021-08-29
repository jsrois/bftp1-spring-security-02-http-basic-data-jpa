package org.factoriaf5.app;

import org.factoriaf5.app.models.Book;
import org.factoriaf5.app.models.Secret;
import org.factoriaf5.app.repositories.BookRepository;
import org.factoriaf5.app.repositories.SecretRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTests {

    @Autowired
    private MockMvc server;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SecretRepository secretRepository;

    @BeforeEach
    // antes de cada test
    void setUp() {
        List<Book> sampleBooks = List.of(
                new Book("El señor de los anillos"),
                new Book("Harry Potter y la piedra filosofal"),
                new Book("Orgullo y prejuicio")
        );
        bookRepository.saveAll(sampleBooks);

        List<Secret> sampleSecrets = List.of(
            new Secret("El asesinato de Kennedy"),
            new Secret("El chupacabras"),
            new Secret("Los ovnis"),
            new Secret("Los Illuminati")
        );
        secretRepository.saveAll(sampleSecrets);
    }

    @AfterEach
    // después de cada test
    void tearDown() {
        bookRepository.deleteAll();
        secretRepository.deleteAll();
    }

    @Test
    void anyPersonCanAccessThePublicResources() throws Exception {

        server.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(jsonPath("$[0].title", equalTo("El señor de los anillos")))
                .andExpect(jsonPath("$[1].title", equalTo("Harry Potter y la piedra filosofal")))
                .andExpect(jsonPath("$[2].title", equalTo("Orgullo y prejuicio")));
    }

    @Test
    void notRegisterUsersCannotSeeTheSecrets() throws Exception {
        server.perform(get("/secrets"))
                .andExpect(status().is(401));
    }

    @Test
    @WithMockUser(roles = {"BASIC"})
    void registeredBasicUsersCanSeeTheSecrets() throws Exception {
        server.perform(get("/secrets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(4)))
                .andExpect(jsonPath("$[0].secret", equalTo("El asesinato de Kennedy")))
                .andExpect(jsonPath("$[1].secret", equalTo("El chupacabras")))
                .andExpect(jsonPath("$[2].secret", equalTo("Los ovnis")))
                .andExpect(jsonPath("$[3].secret", equalTo("Los Illuminati")));
    }
}
