import java.util.ArrayList;
import java.util.List;

public class lc1041 {
    private boolean assignFlower(int[] answer, int garden, final int[][] pathMap) {
        if (garden >= answer.length) return true;
        for (int flower = 1; flower <= 4; flower++) {
            int[] gardenAdjacentList = pathMap[garden];
            boolean chosen = false;
            for (int i = 0; i < 4; i++) {
                int gardenAdjacent = gardenAdjacentList[i];
                if (gardenAdjacent == -1)
                    break;
                int flowerAdjacent = answer[gardenAdjacent];
                if (flowerAdjacent == 0)
                    break;
                if (flowerAdjacent == flower) {
                    chosen = true;
                    break;
                }
            }
            if (chosen == false) {
                answer[garden] = flower;
                boolean success = assignFlower(answer, garden + 1, pathMap);
                if (success)
                    return true;
                answer[garden] = 0;
            }
        }
        return false;
    }

    public int[] gardenNoAdj(int N, int[][] paths) {
        int[][] pathMap = new int[N][4];
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = 0;
            for (int j = 0; j < 4; j++)
                pathMap[i][j] = -1;
        }
        for (int i = 0; i < paths.length; i++) {
            for (int j = 0; j < 2; j++) {
                int garden = paths[i][j] - 1;
                int gardenAdjacent = paths[i][1 - j];
                for (int k = 0; k < 4; k++) {
                    if (pathMap[garden][k] == gardenAdjacent)
                        break;
                    if (pathMap[garden][k] == -1) {
                        pathMap[garden][k] = gardenAdjacent;
                        break;
                    }
                }
            }
        }
        boolean success = assignFlower(answer, 0, pathMap);
        return answer;
    }
}
