import java.util.Objects;

public class Fighter {
    private String name;
    private int totalFights;
    private int wins;
    private int losses;
    private double winLoseRatio;

    public String getName() {
        return name;
    }

    Fighter(String name, int wins, int losses){
        this.name = name;
        this.totalFights= wins + losses;
        this.wins=wins;
        this.losses=losses;
        this.winLoseRatio = wins / losses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fighter fighter = (Fighter) o;
        return totalFights == fighter.totalFights && wins == fighter.wins && losses == fighter.losses && Double.compare(fighter.winLoseRatio, winLoseRatio) == 0 && Objects.equals(name, fighter.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, totalFights, wins, losses, winLoseRatio);
    }

}
