public class PopulationLanternfishV2 {
    long[] fishPopulationInPhases;

    public PopulationLanternfishV2(String initialState) {
        fishPopulationInPhases = initialStateToList(initialState);
    }

    public Long getPopululationCountAfterDays(int days) {
        long[] fishPopulationResult = fishPopulationInPhases.clone();
        for (int i = 0; i < days; i++) {
            long dayOne = fishPopulationResult[0];
            fishPopulationResult[0] = fishPopulationResult[1];
            fishPopulationResult[1] = fishPopulationResult[2];
            fishPopulationResult[2] = fishPopulationResult[3];
            fishPopulationResult[3] = fishPopulationResult[4];
            fishPopulationResult[4] = fishPopulationResult[5];
            fishPopulationResult[5] = fishPopulationResult[6];
            fishPopulationResult[6] = fishPopulationResult[7];
            fishPopulationResult[7] = fishPopulationResult[8];
            fishPopulationResult[6] += dayOne;
            fishPopulationResult[8] = dayOne;
        }
        long population = 0;
        for (long l : fishPopulationResult) {
            population += l;
        }
        return population;
    }

    private long[] initialStateToList(String initialState) {
        long[] fishPopulationInPhases = new long[9];
        int curFishPhase;
        for (String s : initialState.split(",")) {
            curFishPhase = Integer.parseInt(s);
            switch (curFishPhase) {
                case 1 -> fishPopulationInPhases[1]++;
                case 2 -> fishPopulationInPhases[2]++;
                case 3 -> fishPopulationInPhases[3]++;
                case 4 -> fishPopulationInPhases[4]++;
                case 5 -> fishPopulationInPhases[5]++;
                case 6 -> fishPopulationInPhases[6]++;
                case 7 -> fishPopulationInPhases[7]++;
                case 8 -> fishPopulationInPhases[8]++;
                case 9 -> fishPopulationInPhases[9]++;
            }
        }
        return fishPopulationInPhases;
    }
}