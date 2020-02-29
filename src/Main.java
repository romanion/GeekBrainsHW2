import java.io.IOException;

public class Main {

    private static final int REQUIRED_SIZE = 4;

    public static int sumArrayElement(String [][] array) throws MyArraySizeException{
        checkArraySize(array);

        int rowIdx = 0;

        int sum = 0;
        for(String [] subArray: array){
            int columnIdx = 0;
            for (String elem: subArray){
                try {
                    sum += Integer.parseInt(elem);
                    columnIdx++;
                } catch(NumberFormatException ex){
                    throw new MyArrayDataException(rowIdx, columnIdx);
                }
            }
            rowIdx++;
        }
        return sum;
    }

    public static void checkArraySize(String [][] array) throws MyArraySizeException{
        if(array.length != REQUIRED_SIZE){
            throw new MyArraySizeException();
        }

        for(String [] subArray: array){
            if(subArray.length != REQUIRED_SIZE){
                throw new MyArraySizeException();
            }
        }

    }

    public static void main(String[] args) throws IOException {

        String [][] array = {{"3", "3", "5", "4"},
                             {"3", "3", "G", "4"},
                             {"3", "3", "5", "4"},
                             {"3", "3", "5", "4"}}; //new String[4][4];
        String [][] array1 = new String[5][5];
        try {
            System.out.println("Sum of array elements - " + sumArrayElement(array1));
        } catch (MyArraySizeException ex){
            System.out.println(ex.getMessage());
        } catch (MyArrayDataException ex){
            System.out.println(ex.getMessage());
        }
    }
}
