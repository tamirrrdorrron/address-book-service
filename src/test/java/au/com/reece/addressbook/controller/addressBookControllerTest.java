package au.com.reece.addressbook.controller;

import au.com.reece.addressbook.model.AddressBook;
import au.com.reece.addressbook.service.AddressBookService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressBookController.class)
public class addressBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookService addressBookServiceMock;

    private AddressBook dummyAddressBook() {
        return AddressBook.builder()
                .name("unit test")
                .branchNumber("2003")
                .build();
    }

    private List<AddressBook> listOfAddressBooks() {
        return List.of(dummyAddressBook());
    }

    @Test
    void shouldReturnAllAddressBooksAsListOfJsonStrings() throws Exception {
        when(addressBookServiceMock.getAllAddressBooks()).thenReturn(listOfAddressBooks());
        MvcResult result = mockMvc.perform(get("/address-book/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(
                "[{\"id\":0,\"name\":\"unit test\",\"branchNumber\":\"2003\"}]",
                result.getResponse().getContentAsString());
    }

    private static Stream<Arguments> addressBookRequestBodyVariations() {
        String moreThan200Chars = RandomStringUtils.randomAlphabetic(201);
        return Stream.of(
                Arguments.of(null, "\"2001\""),
                Arguments.of("\"\"", "\"2001\""),
                Arguments.of("\"address book name1\"", "\"\""),
                Arguments.of("\"address book name1\"", null),
                Arguments.of("\"" + moreThan200Chars + "\"", "\"2001\"")
        );
    }

    @ParameterizedTest
    @MethodSource("addressBookRequestBodyVariations")
    void shouldValidateAddressBookRequestBody(String addressBookName, String branchNumber) throws Exception {
        // validate address book name, branch number
        // not null, empty, field length
        mockMvc.perform(post("/address-book/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": " + addressBookName + ",\n" +
                        "    \"branchNumber\": " + branchNumber + "\n" +
                        "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnCreatedAddressBookAsJsonString() throws Exception {
        when(addressBookServiceMock.createAddressBook(any())).thenReturn(dummyAddressBook());
        MvcResult result = mockMvc.perform(post("/address-book/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                         "    \"name\": \"unit test\",\n" +
                         "    \"branchNumber\": \"2003\" \n" +
                         "}"))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(
                "{\"id\":0,\"name\":\"unit test\",\"branchNumber\":\"2003\"}",
                result.getResponse().getContentAsString());
    }

    @Test
    void shouldProvideFeedbackIfAddressBookAlreadyExistsOnCreate() throws Exception {
        when(addressBookServiceMock.createAddressBook(any())).thenThrow(new IllegalStateException("address book already exists"));
        MvcResult result = mockMvc.perform(post("/address-book/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"unit test\",\n" +
                        "    \"branchNumber\": \"2003\" \n" +
                        "}"))
                .andExpect(status().isConflict())
                .andReturn();
        assertEquals(
                "{\"httpStatus\":409,\"error\":\"RESOURCE_EXISTS\",\"message\":\"address book already exists\"}",
                result.getResponse().getContentAsString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "a", "1a", "a1"})
    void shouldValidateAddressBookIdOnGetEndpoint(String addressBookId) throws Exception {
        MvcResult result = mockMvc.perform(get("/address-book/" + addressBookId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(
                "{\"httpStatus\":400,\"error\":\"INVALID_REQUEST_TYPE\",\"message\":\"value '" + addressBookId + "' is invalid. address book id must be a integer >= 0\"}",
                result.getResponse().getContentAsString());
    }

    @Test
    void shouldProvideFeedbackIfGetAddressBookByIdNotFound() throws Exception {
        when(addressBookServiceMock.getAddressBook(anyInt())).thenThrow(new ResourceNotFoundException("no address book found for id 1"));
        MvcResult result = mockMvc.perform(get("/address-book/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(
                "{\"httpStatus\":404,\"error\":\"NOT_FOUND\",\"message\":\"no address book found for id 1\"}",
                result.getResponse().getContentAsString());
    }

    @Test
    void shouldReturnAddressBookByIdAsJsonString() throws Exception {
        when(addressBookServiceMock.getAddressBook(anyInt())).thenReturn(dummyAddressBook());
        MvcResult result = mockMvc.perform(get("/address-book/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(
                "{\"id\":0,\"name\":\"unit test\",\"branchNumber\":\"2003\"}",
                result.getResponse().getContentAsString());
    }

    @Test
    void shouldReturnOkIfAddressBookDeletedSuccessfully() throws Exception {
        doNothing().when(addressBookServiceMock).deleteAddressBook(anyInt());
        MvcResult result = mockMvc.perform(delete("/address-book/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(
                "{\n\"message\": \"address book with id '0' deleted successfully\"\n}",
                result.getResponse().getContentAsString());
    }

    @Test
    void shouldProvideFeedbackIfAddressBookForDeleteNotFound() throws Exception {
        doThrow(new ResourceNotFoundException("no address book found for id 1")).when(addressBookServiceMock).deleteAddressBook(anyInt());
        MvcResult result = mockMvc.perform(delete("/address-book/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(
                "{\"httpStatus\":404,\"error\":\"NOT_FOUND\",\"message\":\"no address book found for id 1\"}",
                result.getResponse().getContentAsString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "a", "1a", "a1"})
    void shouldProvideFeedbackIfAddressBookIdIsNotValid(String addressBookId) throws Exception {
        MvcResult result = mockMvc.perform(delete("/address-book/" + addressBookId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(
                "{\"httpStatus\":400,\"error\":\"INVALID_REQUEST_TYPE\",\"message\":\"value '" + addressBookId + "' is invalid. address book id must be a integer >= 0\"}",
                result.getResponse().getContentAsString());
    }
}
