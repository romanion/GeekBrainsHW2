package hw2;

public class MyArrayDataException extends RuntimeException {

    public MyArrayDataException(int rowIdx, int columnIdx){
        super(String.format("Invalid format of array element - [%d][%d]. Format of string should be numeric", rowIdx, columnIdx));
    }
}
