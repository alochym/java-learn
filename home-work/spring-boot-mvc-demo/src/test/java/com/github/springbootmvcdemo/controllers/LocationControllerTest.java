package com.github.springbootmvcdemo.controllers;

import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springbootmvcdemo.exceptions.NotFoundException;
import com.github.springbootmvcdemo.models.Location;
import com.github.springbootmvcdemo.services.LocationService;

@WebMvcTest(LocationController.class)
@Nested
public class LocationControllerTest {
        private String END_POINT_PATH = "/v1/locations";

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper mapper;

        @MockBean
        private LocationService svc;

        @Test
        public void testMethodPOST400BadRequest() throws Exception {
                Location location = new Location();

                /*
                 * convert Java Location Object into String Object
                 * 
                 * Normalize the data into custome column is perform @JsonProperty annotation.
                 */
                String body = mapper.writeValueAsString(location);

                /*
                 * Make a HTTP POST request with invalid Location data.
                 * 
                 * Validation is performed in LocationController @Valid annotation.
                 * 
                 * Constrain is performed in Location Model @NotBlank annotation.
                 * 
                 * Output should be 400 Bad Request
                 */
                mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)).andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void testMethodPOST201Created() throws Exception {
                /*
                 * Expected value
                 */
                Location location = new Location();
                location.setCode("NYC_USA");
                location.setCityName("New York City");
                location.setRegionName("NY");
                location.setCountryCode("US");
                location.setCountryName("United State of America");
                location.setEnable(1);
                location.setTrashed(0);

                /*
                 * When ServiceLocation.save method is call and Faked return Location Object.
                 */
                Mockito.when(this.svc.save(location)).thenReturn(location);

                /*
                 * convert Java Location Object into String Object
                 * 
                 * Normalize the data into custome column is perform @JsonProperty annotation.
                 */
                String body = mapper.writeValueAsString(location);

                /*
                 * Make a HTTP POST request with valid Location data.
                 * 
                 * Validation is performed in LocationController @Valid annotation.
                 * 
                 * Constrain is performed in Location Model @NotBlank annotation.
                 * 
                 * Output should be 201 Created
                 */
                mockMvc.perform(
                                MockMvcRequestBuilders.post(END_POINT_PATH)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(body))
                                .andExpect(MockMvcResultMatchers.status().isCreated())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath(
                                                                "$.code", CoreMatchers.is(location.getCode())))
                                .andExpect(MockMvcResultMatchers.header().string("Location", "/v1/locations/NYC_USA"))
                                .andDo(MockMvcResultHandlers.print());

                /*
                 * Verify ServiceLocation.save method call 1 times
                 * Only use verify with doing Faked Object.
                 */
                Mockito.verify(this.svc, Mockito.times(1)).save(location);
        }

        @Test
        public void testMethodGET405NotAllowOnPostURI() throws Exception {
                /*
                 * Expected value
                 */
                String code = "/GA_USA";
                String uri = this.END_POINT_PATH + code;

                /*
                 * Make a invalid HTTP Method POST request.
                 * 
                 * Output should be 405 Method Not Allow
                 */
                mockMvc.perform(MockMvcRequestBuilders.post(uri))
                                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void testMethodGET405NotAllowOnPutURI() throws Exception {
                /*
                 * Expected value
                 */
                String code = "/GA_USA";
                String uri = this.END_POINT_PATH + code;

                /*
                 * Make a invalid HTTP Method POST request.
                 * 
                 * Output should be 405 Method Not Allow
                 */
                mockMvc.perform(MockMvcRequestBuilders.put(uri))
                                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void testMethodGET404NotFound() throws Exception {
                /*
                 * Expected invalue
                 */
                String code = "NYC_USA1";
                String uri = this.END_POINT_PATH + "/" + code;

                /*
                 * When ServiceLocation.update method is call and Faked return Location Object.
                 */
                String message = "No Location found with given code: " + code;
                Mockito.when(this.svc.get(code)).thenThrow(new NotFoundException(message));

                /*
                 * Make a HTTP Method GET request with a code that is not in database.
                 * 
                 * Output should be 404 Not Found
                 */
                mockMvc.perform(MockMvcRequestBuilders.get(uri))
                                .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andDo(MockMvcResultHandlers.print());

                /*
                 * Verify ServiceLocation.get method call 1 times
                 * Only use verify with doing Faked Object.
                 */
                Mockito.verify(this.svc, Mockito.times(1)).get(code);
        }

        @Test
        public void testMethodGET200ok() throws Exception {
                /*
                 * Expected value
                 */
                Location location = new Location();
                location.setCode("NYC_USA");
                location.setCityName("New York City");
                location.setRegionName("NY");
                location.setCountryCode("US");
                location.setCountryName("United State of America");
                location.setEnable(1);
                location.setTrashed(0);

                String code = "NYC_USA";
                String uri = this.END_POINT_PATH + "/" + code;

                /*
                 * When ServiceLocation.save method is call and Faked return Location Object.
                 */
                Mockito.when(this.svc.get(code)).thenReturn(location);

                /*
                 * Make a HTTP GET request with valid code.
                 * 
                 * Output should be 200 Ok
                 */
                mockMvc.perform(MockMvcRequestBuilders.get(uri))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath(
                                                                "$.code", CoreMatchers.is(location.getCode())))
                                .andDo(MockMvcResultHandlers.print());

                /*
                 * Verify ServiceLocation.get method call 1 times
                 * Only use verify with doing Faked Object.
                 */
                Mockito.verify(this.svc, Mockito.times(1)).get(code);
        }

        @Test
        public void testMethodPUT404NotFound() throws Exception {
                /*
                 * Expected value - Invalid
                 */
                Location location = new Location();
                location.setCode("NYC_USA1");
                location.setCityName("New York City");
                location.setRegionName("NY");
                location.setCountryCode("US");
                location.setCountryName("United State of America");
                location.setEnable(1);
                location.setTrashed(0);

                /*
                 * When ServiceLocation.update method is call and Faked return Location Object.
                 */
                String message = "No Location found with given code: " + location.getCode();
                Mockito.when(this.svc.update(location)).thenThrow(new NotFoundException(message));

                /*
                 * convert Java Location Object into String Object
                 * 
                 * Normalize the data into custome column is perform @JsonProperty annotation.
                 */
                String body = mapper.writeValueAsString(location);

                /*
                 * Make a HTTP PUT request with valid Location data.
                 * 
                 * Validation is performed in LocationController @Valid annotation.
                 * 
                 * Constrain is performed in Location Model @NotBlank annotation.
                 * 
                 * Output should be 404 Not Found
                 */
                mockMvc.perform(
                                MockMvcRequestBuilders.put(END_POINT_PATH)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(body))
                                .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andDo(MockMvcResultHandlers.print());

                /*
                 * Verify ServiceLocation.save method call 1 times
                 * Only use verify with doing Faked Object.
                 */
                Mockito.verify(this.svc, Mockito.times(1)).update(location);
        }

        @Test
        public void testMethodPUT400BadRequest() throws Exception {
                /*
                 * Expected value - Incomplete Location
                 */
                Location location = new Location();
                location.setCode("NYC_USA");
                // location.setCityName("New York City");
                // location.setRegionName("NY");
                // location.setCountryCode("USA");
                // location.setCountryName("United State of America");
                // location.setEnable(1);
                // location.setTrashed(0);

                /*
                 * convert Java Location Object into String Object
                 * 
                 * Normalize the data into custome column is perform @JsonProperty annotation.
                 */
                String body = mapper.writeValueAsString(location);

                /*
                 * Make a HTTP PUT request with incomplete Location data.
                 * 
                 * Validation is performed in LocationController @Valid annotation.
                 * 
                 * Constrain is performed in Location Model @NotBlank annotation.
                 * 
                 * Output should be 400 Bad Request
                 */
                mockMvc.perform(
                                MockMvcRequestBuilders.put(END_POINT_PATH)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(body))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print());

                /*
                 * Verify ServiceLocation.save method call 1 times
                 * Only use verify with doing Faked Object.
                 * 
                 * Controller validation execution so that there no service.update call
                 * 
                 * the error message - Actually, there were zero interactions with this mock.
                 */
                // Mockito.verify(this.svc, Mockito.times(1)).update(location);
        }

        @Test
        public void testMethodPUT200ok() throws Exception {
                /*
                 * Expected value
                 */
                Location location = new Location();
                location.setCode("NYC_USA");
                location.setCityName("New York City");
                location.setRegionName("NY");
                location.setCountryCode("US");
                location.setCountryName("United State of America");
                location.setEnable(1);
                location.setTrashed(0);

                /*
                 * convert Java Location Object into String Object
                 * 
                 * Normalize the data into custome column is perform @JsonProperty annotation.
                 */
                String body = mapper.writeValueAsString(location);

                /*
                 * Make a HTTP PUT request with valid Location data.
                 * 
                 * Validation is performed in LocationController @Valid annotation.
                 * 
                 * Constrain is performed in Location Model @NotBlank annotation.
                 * 
                 * Output should be 404 Not Found
                 */
                mockMvc.perform(
                                MockMvcRequestBuilders.put(END_POINT_PATH)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(body))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andDo(MockMvcResultHandlers.print());

                /*
                 * Verify ServiceLocation.save method call 1 times
                 * Only use verify with doing Faked Object.
                 */
                Mockito.verify(this.svc, Mockito.times(1)).update(location);
        }

        @Test
        public void testMethodDELETE404NotFound() throws Exception {
                /*
                 * Expected value - Invalid
                 */
                String code = "NYC_USA1";
                String uri = this.END_POINT_PATH + "/" + code;

                /*
                 * When ServiceLocation.delete method is call and No return value.
                 * Mockito.when()
                 * Use it when you want the mock to return particular value when particular
                 * method is called.
                 * We used Mockito.doThrow
                 */
                Mockito.doThrow(NotFoundException.class).when(this.svc).delete(code);

                /*
                 * Make a HTTP DELETE request with invalid code.
                 * 
                 * Output should be 404 Not Found
                 */
                mockMvc.perform(
                                MockMvcRequestBuilders.delete(uri))
                                .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andDo(MockMvcResultHandlers.print());

                /*
                 * Verify ServiceLocation.save method call 1 times
                 * Only use verify with doing Faked Object.
                 */
                Mockito.verify(this.svc, Mockito.times(1)).delete(code);
        }

        @Test
        public void testMethodDELETE204NoContent() throws Exception {
                /*
                 * Expected value - Invalid
                 */
                String code = "NYC_USA";
                String uri = this.END_POINT_PATH + "/" + code;

                /*
                 * When ServiceLocation.delete method is call and No return value.
                 * Mockito.when()
                 * Use it when you want the mock to return particular value when particular
                 * method is called.
                 * We used Mockito.doThrow
                 */
                Mockito.doNothing().when(this.svc).delete(code);

                /*
                 * Make a HTTP DELETE request with code.
                 * 
                 * Output should be 204 No Content
                 */
                mockMvc.perform(
                                MockMvcRequestBuilders.delete(uri))
                                .andExpect(MockMvcResultMatchers.status().isNoContent())
                                .andDo(MockMvcResultHandlers.print());

                /*
                 * Verify ServiceLocation.save method call 1 times
                 * Only use verify with doing Faked Object.
                 */
                Mockito.verify(this.svc, Mockito.times(1)).delete(code);
        }

        @Test
        public void testMethodPOST400RangeValidation() throws Exception {
                /*
                 * Expected value - Invalid
                 */
                Location location = new Location();
                location.setCode("NYC_USA 0123456789");
                location.setCityName("New York City");
                location.setRegionName("NY");
                location.setCountryCode("USA");
                location.setCountryName("United State of America");
                location.setEnable(10);
                location.setTrashed(10);

                /*
                 * convert Java Location Object into String Object
                 * 
                 * Normalize the data into custome column is perform @JsonProperty annotation.
                 */

                String body = mapper.writeValueAsString(location);
                /*
                 * Make a HTTP POST request with invalid code.
                 * 
                 * Output should be 400 Bad Request
                 */
                MvcResult result = mockMvc.perform(
                                MockMvcRequestBuilders.post(END_POINT_PATH)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(body))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print())
                                .andReturn();
                /*
                 * List <errs> items are return in random order.
                 * 
                 * We should use return value of mockMvc.perform().andReturn()
                 * 
                 * {
                 * "timestamp":"2023-09-06T18:14:13.587+00:00",
                 * "status":400,
                 * "path":"",
                 * "errs":
                 * [
                 * "Max Location Code is 12 character",
                 * "Location Country Code should be 2 Upper case character"
                 * ]
                 * }
                 */
                String resp = result.getResponse().getContentAsString();

                Assertions.assertThat(resp).contains("Max Location Code is 12 character");
                Assertions.assertThat(resp).contains("Location Country Code should be 2 Upper case character");
        }

        @Test
        public void testMethodPOST400NotNullValidation() throws Exception {
                /*
                 * Expected value - Invalid
                 */
                Location location = new Location();

                /*
                 * convert Java Location Object into String Object
                 * 
                 * Normalize the data into custome column is perform @JsonProperty annotation.
                 */
                String body = mapper.writeValueAsString(location);

                /*
                 * Make a HTTP POST request with invalid code.
                 * 
                 * Output should be 404 Not Found
                 */
                MvcResult result = mockMvc.perform(
                                MockMvcRequestBuilders.post(END_POINT_PATH)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(body))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print())
                                .andReturn();
                /*
                 * List <errs> items are return in random order.
                 * 
                 * We should use return value of mockMvc.perform().andReturn()
                 * 
                 * {
                 * "timestamp":"2023-09-06T18:14:13.587+00:00",
                 * "status":400,
                 * "path":"",
                 * "errs":
                 * [
                 * "Location Code cannot be blank",
                 * "Location Country Code cannot be blank",
                 * "Location City Name cannot be blank",
                 * "Location Country Name cannot be blank",
                 * "Location Region Name cannot be blank"
                 * ]
                 * }
                 */
                String resp = result.getResponse().getContentAsString();

                Assertions.assertThat(resp).contains("Location Code cannot be blank");
                Assertions.assertThat(resp).contains("Location Country Code cannot be blank");
                Assertions.assertThat(resp).contains("Location City Name cannot be blank");
                Assertions.assertThat(resp).contains("Location Country Name cannot be blank");
                Assertions.assertThat(resp).contains("Location Region Name cannot be blank");

        }
}
