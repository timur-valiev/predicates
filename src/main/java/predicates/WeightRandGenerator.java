package predicates;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Valiev
 * Date: 21.03.13
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
public class WeightRandGenerator {
    private Random generator;
    private ArrayList<Integer> list;
    private int[][] xxx;
    int[] sizes;
    int pos;

    public WeightRandGenerator(Random generator, ArrayList<Integer> list, int[] prob, int pos) {
        this.generator = generator;
        this.list = list;
        this.pos = pos;
        sizes =  new int[82-pos];
        for (int func: list){
            for (int i=0;i<82-pos-prob[func];i++){
                sizes[i]++;
            }
        }
        xxx = new int[82-pos][];
        for(int i=0;i<82-pos;i++){
            xxx[i] = new int[sizes[i]];
            sizes[i]=0;
        }

        for (int func: list){
            for (int i=0;i<82-pos-prob[func];i++){
                xxx[i][sizes[i]]=func;
                sizes[i]++;
            }

        }
    }

    public int next(int need) throws Exception {
        int col = getCol(need);
        if (sizes[col]==0)
            throw new Exception();
        return xxx[col][generator.nextInt(sizes[col])];
    }

    private int getCol(int need){
        return need + generator.nextInt(82-pos-need);
    }

    public int getSize(int need){
        return sizes[need];
    }
}
