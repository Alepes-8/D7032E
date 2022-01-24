/**
 *  Localise the main menu code into it's own file so that it can easily be edited and
 *  called upon. But also for easier understanding.
 * 
 *  @author Alex Peschel
 */
package UI;

public class MainMenuUI {
    
    public void MainMenu(int[][] settings, boolean newStart ,String language){
        if(newStart==true){
            System.out.println( 
            "****************************************************************\n" +
            " Welcome to VarietyBoggle\n");
        }
        System.out.println(
            "****************************************************************\n" +
            "   Current settings:\n" +
            "   Number of players: " + settings[0][0] + "\n"+
            "   Number of bots: " + settings[1][0] + "\n"+
            "   Board size (rows/columns): " + settings[2][0] + "/" + settings[2][1] + "\n" +
            "   Language: "+ language + "\n" + 
            "****************************************************************\n" +
            " Menu:\n" +
            "  [1] Play standard WordSquares\n" +
            "  [2] Play ScrabbleSquares on standard board\n" +
            "  [3] Load 5x5 predefined ScrabbleBoard and play ScrabbleSquares\n" +
            "  [4] Load randomised ScrabbleBoard and play ScrabbleSquares\n" +
            "  [5] Settings\n" +
            "  [6] Client\n" +
            "  [!] Quit\n" +
            "****************************************************************\n");
    }
}
