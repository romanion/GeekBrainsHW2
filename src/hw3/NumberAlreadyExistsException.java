package hw3;

public class NumberAlreadyExistsException extends RuntimeException{

    public NumberAlreadyExistsException(String number){
        super(String.format("Contact with number %s already exists", number));
    }
}
