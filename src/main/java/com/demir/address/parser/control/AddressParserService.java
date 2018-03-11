package com.demir.address.parser.control;

import com.demir.address.parser.entity.Address;
import com.demir.address.parser.entity.AddressPiece;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: murat.demir
 */
@Service
public class AddressParserService {

    private static final String BASIC_REGEX = "(((\\s|^)(?i)NO[:\\s]?)?\\d+[^ ,]*)";


    public Address parse(String addressAsRaw) {
        Address address = Address.build();
        addressAsRaw = prepareToParse(addressAsRaw);

        final Pattern pattern = Pattern.compile(BASIC_REGEX);
        Matcher matcher = pattern.matcher(addressAsRaw);

        List<AddressPiece> addressPieces = new ArrayList<>();

        int count = 0;
        while (matcher.find()) {
            count++;
            AddressPiece piece = new AddressPiece();
            piece.setBegin(matcher.start());
            piece.setEnd(matcher.end());
            piece.setIndex(count);
            piece.setText(matcher.group());
            addressPieces.add(piece);
        }

        if (addressPieces.isEmpty()) {
            return address;
        }

        String number = null;
        // if there is only one number
        if (addressPieces.size() == 1) {
            AddressPiece piece = addressPieces.get(0);
            if (piece.getBegin() > 1 && piece.getEnd() < addressAsRaw.length()) {
                number = addressAsRaw.substring(piece.getBegin(), addressAsRaw.length());
            } else {
                number = piece.getText();
            }
            address.setNo(number);
            address.setStreet(extractStreet(addressAsRaw, address.getNo()));
        } else {
            //there are more than one numbers
            //if 'no' is contains
            Optional<AddressPiece> first = addressPieces.stream().filter(a -> a.getText().toLowerCase().contains("no")).findFirst();
            if (first.isPresent()) {
                number = first.get().getText();
            } else {
                Optional<AddressPiece> piece = addressPieces.stream().filter(a -> a.isFirst()).findFirst();
                if (piece.isPresent()) {
                    number = piece.get().getText();
                }
            }
        }
        if (number != null && !number.isEmpty()) {
            address.setNo(number.trim());
            address.setStreet(extractStreet(addressAsRaw, address.getNo()));
        }
        return address;
    }


    public static String prepareToParse(String address) {
        address = address.trim();
        return address;
    }

    public static String extractStreet(String address, String number) {
        address = address.replaceAll(number, "");
        address = address.replaceAll(",", "");
        address = address.trim();
        return address;
    }


}
