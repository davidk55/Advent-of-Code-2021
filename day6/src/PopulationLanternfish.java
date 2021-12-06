import java.util.ArrayList;
import java.util.List;

public class PopulationLanternfish {
    List<Integer> initialFishPopulation;

    public PopulationLanternfish(String initialState) {
        this.initialFishPopulation = initialStateToList(initialState);
    }

    public int getPopululationCountAfterDays(int days) {
        List<Integer> populatedList = new ArrayList<>(initialFishPopulation);
        for (int i = 0; i < days; i++) {
            for (int j = 0; j < populatedList.size(); j++) {
                if (populatedList.get(j) == 0) {
                    populatedList.set(j, 6);
                    populatedList.add(9);
                } else populatedList.set(j, populatedList.get(j) - 1);
            }
        }
        return populatedList.size();
    }

    private List<Integer> initialStateToList(String initialState) {
        List<Integer> initialTimer = new ArrayList<>();
        for (String s : initialState.split(",")) {
            initialTimer.add(Integer.parseInt(s));
        }
        return initialTimer;
    }
}