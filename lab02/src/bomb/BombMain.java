package bomb;

import common.IntList;

public class BombMain {
    public static void answers(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct inputs (passwords) to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("39291226"); //Answer 1
        }
        if (phase >= 1) {
            b.phase1(IntList.of(0, 9, 3, 0, 8)); // Answer 2
        }
        if (phase >= 2) {
            b.phase2("793227803"); // Answer 3
        }
    }
}
