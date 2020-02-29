package hw2;

public class MyArraySizeException extends IllegalArgumentException {

    public MyArraySizeException(){
        super("Invalid array size. Array should be 4x4");
    }

}
