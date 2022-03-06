public class Fighter {
    private String name;
    private int totalFights;
    private int wins;
    private int losses;
    private double winLoseRatio;

    Fighter(String name, int wins, int losses){
        this.name = name;
        this.totalFights= wins + losses;
        this.wins=wins;
        this.losses=losses;
        this.winLoseRatio = wins / losses;
    }
}
