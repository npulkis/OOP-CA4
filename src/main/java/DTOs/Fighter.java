package DTOs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

public class Fighter implements Comparable<Fighter> {
    private String name;
    private int totalFights;
    private int wins;
    private int losses;
    private double winLoseRatio;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    public String getName() {
        return name;
    }

    public Fighter(String name, int wins, int losses){
        this.name = name;
        this.totalFights= wins + losses;
        this.wins=wins;
        this.losses=losses;
        BigDecimal bd = new BigDecimal(((double)wins) / losses).setScale(2, RoundingMode.HALF_UP);

        this.winLoseRatio = bd.doubleValue();
    }

    @Override
    public String toString() {
        return "Fighter{" +
                "name='" + name + '\'' +
                ", totalFights=" + totalFights +
                ", wins=" + wins +
                ", losses=" + losses +
                ", winLoseRatio=" + winLoseRatio +
                '}';
    }

    @Override
    public int compareTo(Fighter o){

        boolean SameName = this.getName().equalsIgnoreCase(o.getName());

        if (SameName){
            return this.getName().compareToIgnoreCase(o.getName());
        }else{
            return this.getWins() - o.getWins();
        }


    }


    public int getWins() {
        return wins;
    }

    public int getTotalFights() {
        return totalFights;
    }

    public int getLosses() {
        return losses;
    }

    public double getWinLoseRatio() {
        return winLoseRatio;
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
