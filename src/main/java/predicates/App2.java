package predicates;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App2 {
    static ArrayList<ArrayList<Integer>> predToFunc = new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> funcToPred = new ArrayList<ArrayList<Integer>>();
    static int[][] probs = new int[82][122283];//для i j хранит сколько предикатов забивается функцией #i начиная с позиции j
    static boolean[][] matrix = new boolean[122283][82];


    private static void prepareData() throws FileNotFoundException {
        for (int i = 0; i < 82; i++)
            predToFunc.add(new ArrayList<Integer>());
        for (int i = 0; i < 122283; i++)
            funcToPred.add(new ArrayList<Integer>());

        Scanner scanner = new Scanner(new FileInputStream("text/result_new_withfunk.txt"));
        for (int i = 0; i < 122283; i++) {
            for (int j = 0; j < 82; j++) {
                int x = scanner.nextInt();
                if (x == 0) {
                    matrix[i][j] = true;
                    predToFunc.get(j).add(i);
                    funcToPred.get(i).add(j);
                    for (int k = 0; k < j; k++)
                        probs[k][i]++;
                }
            }
            scanner.nextLong();
        }
    }


    public static void main(String[] args) throws Exception {
        prepareData();

        Random generator = new Random();
        generator.setSeed(System.currentTimeMillis());
        WeightRandGenerator[] generators = new WeightRandGenerator[82];
        for (int i = 0; i < 82; i++) {
            generators[i] = new WeightRandGenerator(generator, predToFunc.get(i), probs[i], i);
        }

        boolean[] usedFunctions = new boolean[122283];
        int[] funcs = new int[82];
        int funcCount = 0;
        boolean[] usedPredicates = new boolean[82];
        boolean[] basis = new boolean[82];

        int minPow = 10;
        System.out.println(1111);

        while (true) {
            try {
                for (int i = 0; i < 82; i++) {
                    if (!usedPredicates[i]) {
                        int after = 0;
                        for (int j=i+1;j<82;j++)
                            if (usedPredicates[j])
                                after++;
                        int f = nextFunction(generators[i], usedFunctions, Math.max(after+minPow - funcCount-1,0));
                        usedFunctions[f] = true;
                        funcs[funcCount] = f;
                        funcCount++;
                        for (int j = 0; j < funcToPred.get(f).size(); j++) {
                            usedPredicates[funcToPred.get(f).get(j)] = true;
                        }
                    }
                }

                //save,if need
                if (!basis[funcCount] && isBasis(funcs, funcCount, matrix)) {
                    basis[funcCount] = true;
                    System.out.print(funcCount + " : ");
                    for (int i = 0; i < funcCount; i++)
                        System.out.print(funcs[i] + " ");
                    System.out.println();
                    while(basis[minPow])
                        minPow++;
                }

            } catch (Exception e){

            } finally {
                //flush
                for (int i = 0; i < 82; i++)
                    usedPredicates[i] = false;
                for (int i = 0; i < funcCount; i++)
                    usedFunctions[funcs[i]] = false;
                funcCount = 0;
            }
        }
    }

    private static int nextFunction(WeightRandGenerator generator, boolean[] usedFunctions, int need) throws Exception {
        int f = generator.next(need);
        int cc = 0;
        while (usedFunctions[f]) {
            cc++;
            if (generator.getSize(need) / 2 < cc)
                throw new Exception();
            f = generator.next(need);
        }
        return f;
    }

    private static boolean isBasis(int[] funcs, int funcCount, boolean[][] matrix) {
        for (int i = 0; i < funcCount; i++) {
            boolean ans = false;
            for (int col = 0; col < 82; col++) {
                boolean ans2 = true;
                for (int j = 0; j < funcCount; j++) {
                    ans2 = ans2 & !(i != j && matrix[funcs[i]][col] && matrix[funcs[j]][col]);
                }
                ans |= ans2;
            }
            if (!ans)
                return false;

        }
        return true;
    }

}
