/**
 *  Instead of having a String to int converstion located on several places it is 
 *  centered in one place.
 * 
 *  @author Alex Peschel
 */
package tools;

public class StringToInt {
    
    public static int ConvertString(String value){
        int interger=Integer.valueOf(value);
        return interger;
    }
    
    public static int[] SplitString(String value){
        int[] size = new int[2];
        String[] dimensions = value.split("/");
        size[0] = Integer.parseInt(dimensions[0]);
        size[1] = Integer.parseInt(dimensions[1]);
        return size;
    }
    
    
}
