package hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    private Map<String, List<String>> contactsMap = new HashMap<>();

    public void add(String surname, String phoneNumber){
        checkNumbersDuplicates(phoneNumber);
        List<String> newPhoneNumberList;
        if(contactsMap.containsKey(surname)){
            newPhoneNumberList = contactsMap.get(surname);
            newPhoneNumberList.add(phoneNumber);
            contactsMap.put(surname, newPhoneNumberList);
        } else {
            newPhoneNumberList = new ArrayList<>();
            newPhoneNumberList.add(phoneNumber);
            contactsMap.put(surname, newPhoneNumberList);
        }
    }

    private void checkNumbersDuplicates(String phoneNumber){
        for (List<String> numbersList: contactsMap.values()){
            for (String existingPhoneNumbers: numbersList){
                if(existingPhoneNumbers.equalsIgnoreCase(phoneNumber)){
                    throw new NumberAlreadyExistsException(phoneNumber);
                }
            }
        }
    }

    public List<String> get(String surname){
        return contactsMap.get(surname);
    }

    public void printSurnameNumbers(String surname){
        if(contactsMap.containsKey(surname)){
            System.out.println(String.format("Phone numbers of contact by surname \"%s\":", surname));
            for (String phoneNumber: get(surname)){
                System.out.println(phoneNumber);
            }
        } else {
            System.out.println(String.format("Contact with surname \"%s\" doesn't exists", surname));
        }

    }
}
