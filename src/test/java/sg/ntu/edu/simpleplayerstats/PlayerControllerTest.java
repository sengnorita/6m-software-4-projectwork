package sg.ntu.edu.simpleplayerstats;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import sg.ntu.edu.simpleplayerstats.entity.Player;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
  private static final String AUTH_TOKEN = "chickendinner";

  @DisplayName("Get player by Id")
  @Test
  public void getPlayerByIdTest() throws Exception {
    // Step 1: Build a request
    RequestBuilder request = MockMvcRequestBuilders.get("/players/1").header(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN);

    // Step 2: Perform the request, get the response and assert
    mockMvc.perform(request)
        // Assert that the status code is 200 OK
        .andExpect(status().isOk())
        // Assert the content is JSON
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        // Assert that the id returned is 1
        .andExpect(jsonPath("$.id").value(1));

  }

  @Test
  public void getAllPlayersTest() throws Exception {
    // Step 1: Build a GET request to /customers
    RequestBuilder request = MockMvcRequestBuilders.get("/players").header(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN);

    // Step 2: Perform the request, get the response and assert
    mockMvc.perform(request)
        // Assert that the status code is 200 OK
        .andExpect(status().isOk())
        // Assert that the content is JSON
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        // Assert that the size of the response is 5
        .andExpect(jsonPath("$.size()").value(5));
  }

  @Test
  public void validPlayerCreationTest() throws Exception {
    // Step 1: Create a Player object

    Player player = Player.builder()
        .firstName("Alfred")
        .lastName("Lim")
        .footballclub("Singapore Team")
        .playerposition("Middlefield")
        .age(46)
        .nationality("Singaporean")
        .build();

    // Step 2: Convert the Java objec to JSON using ObjectMapper
    String newCustomerAsJSON = objectMapper.writeValueAsString(player);

    // Step 3: Build the request
    RequestBuilder request = MockMvcRequestBuilders.post("/players")
        .contentType(MediaType.APPLICATION_JSON)
        .content(newCustomerAsJSON)
        .header(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN);

    // Step 4: Perform the request, get response and assert
    mockMvc.perform(request)
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(6))
        .andExpect(jsonPath("$.firstName").value("Alfred"))
        .andExpect(jsonPath("$.lastName").value("Lim"));
  }

  @Test
  public void invalidPlayerCreationTest() throws Exception {
    // Step 1: Create a Customer with invalid fields

    Player invalidPlayer = Player.builder()
        .firstName("AAA")
        .lastName("BBB")
        .build();

    // Step 2: Convert the Java object to JSON
    String invalidPlayerAsJSON = objectMapper.writeValueAsString(invalidPlayer);

    // Step 3: Build the request
    RequestBuilder request = MockMvcRequestBuilders.post("/players").contentType(MediaType.APPLICATION_JSON)
        .content(invalidPlayerAsJSON).header(AUTH_TOKEN_HEADER_NAME, AUTH_TOKEN);

    // Step 4: Perform the request and get the response and assert
    mockMvc.perform(request)
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

}
