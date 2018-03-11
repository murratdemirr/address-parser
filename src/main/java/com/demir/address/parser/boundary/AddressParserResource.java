package com.demir.address.parser.boundary;

import com.demir.address.parser.control.AddressParserService;
import com.demir.address.parser.entity.Address;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * User: murat.demir
 */
@RestController
@RequestMapping("/api/v1")
public class AddressParserResource {

    @Inject
    AddressParserService service;


    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Address parse(@RequestBody @Size(min = 3) String address) {
        return service.parse(address);
    }


}
