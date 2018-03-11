package com.demir.address.parser;

import com.demir.address.parser.control.AddressParserService;
import com.demir.address.parser.entity.Address;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressParserApplicationTests {

    @Inject
    AddressParserService addressParserService;

    @Test
    public void simpleCases() {
        assertThat(addressParserService).isNotNull();

        /** Testing for Case-1 **/
        Address address = addressParserService.parse("Winterallee 3");
        nullCheck(address);
        assertThat(address.getStreet()).isEqualTo("Winterallee");
        assertThat(address.getNo()).isEqualTo("3");


        /** Testing for Case-2 **/
        address = addressParserService.parse("Musterstrasse 45");
        nullCheck(address);
        assertThat(address.getStreet()).isEqualTo("Musterstrasse");
        assertThat(address.getNo()).isEqualTo("45");

        /** Testing for Case-3 **/
        address = addressParserService.parse("Blaufeldweg 123B");
        nullCheck(address);
        assertThat(address.getStreet()).isEqualTo("Blaufeldweg");
        assertThat(address.getNo()).isEqualTo("123B");

    }

    @Test
    public void complicatedCases() {
        assertThat(addressParserService).isNotNull();

        /** Testing for Case-1 **/
        Address address = addressParserService.parse("Am Bächle 23");
        nullCheck(address);
        assertThat(address.getStreet()).isEqualTo("Am Bächle");
        assertThat(address.getNo()).isEqualTo("23");

        /** Testing for Case-2 **/
        address = addressParserService.parse("Auf der Vogelwiese 23 b");
        nullCheck(address);
        assertThat(address.getStreet()).isEqualTo("Auf der Vogelwiese");
        assertThat(address.getNo()).isEqualTo("23 b");


    }

    @Test
    public void complexCases() {
        assertThat(addressParserService).isNotNull();

        /** Testing for Case-1 **/
        Address address = addressParserService.parse("4, rue de la revolution");
        nullCheck(address);
        assertThat(address.getStreet()).isEqualTo("rue de la revolution");
        assertThat(address.getNo()).isEqualTo("4");

        /** Testing for Case-2 **/
        address = addressParserService.parse("200 Broadway Av");
        nullCheck(address);
        assertThat(address.getStreet()).isEqualTo("Broadway Av");
        assertThat(address.getNo()).isEqualTo("200");

        /** Testing for Case-3 **/
        address = addressParserService.parse("Calle Aduana, 29");
        nullCheck(address);
        assertThat(address.getStreet()).isEqualTo("Calle Aduana");
        assertThat(address.getNo()).isEqualTo("29");

        /** Testing for Case-4 **/
        address = addressParserService.parse("Calle 39 No 1540");
        nullCheck(address);
        assertThat(address.getStreet()).isEqualTo("Calle 39");
        assertThat(address.getNo()).isEqualTo("No 1540");

    }

    public void nullCheck(Address address) {
        assertThat(address.getStreet()).isNotNull().isNotEmpty();
        assertThat(address.getNo()).isNotNull().isNotEmpty();
    }

}
