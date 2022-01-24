/** 
 *  This class manages the games scanner, placing it in one place for other classes to call upon.
 *  This is so that is is easier to change, or be used by new parts
 * 
 *  @author Alex Peschel 
 */
package IO;

import java.util.Scanner;

public class Scan{
    private Scanner in = new Scanner(System.in);
    private String input;

    public String Scanning(){
        input = in.nextLine();
        return input;
    }

}
