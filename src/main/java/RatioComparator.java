import DTOs.Fighter;

import java.awt.*;
import java.util.Comparator;

public class RatioComparator implements Comparator<Fighter> {


    @Override
    public int compare(Fighter o1, Fighter o2) {
        return Double.compare(o2.getWinLoseRatio(),o1.getWinLoseRatio());
    }
}
