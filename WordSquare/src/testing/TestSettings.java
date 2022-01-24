package testing;

public class TestSettings {
    
    public int[][] InitiateSettings(){
        int[][] settings = new int[5][2];
        settings[0][0] = 0; //number of Player
        settings[1][0] = 2; //number of bots
        settings[2][0] = 3; //rows
        settings[2][1] = 3; //columns
        settings[3][0] = 0; //language
        return settings;
    }
}
