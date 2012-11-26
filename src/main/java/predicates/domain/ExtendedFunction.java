package predicates.domain;

import java.util.Arrays;

public class ExtendedFunction {
    private Integer dim;
    private Integer capacity;
    public int[] values;
    private int[][] valuesForVectors;
    private boolean[][] computed;
    private static int[] pows;
    static {
        pows=new int[5];
        for(int i=1;i<5;i++){
            pows[i]= (int)Math.pow(4,2*i);
        }
    }

    public ExtendedFunction(Function function) {
        dim = function.getDim();
        capacity = function.getCapacity();
        values = new int[function.getValues().getValues().size()];
        for (int i=0;i<function.getValues().getValues().size();i++){
            values[i] = function.getValue(i);
        }
        if (capacity==2){
            valuesForVectors = new int[5][];
            computed = new boolean[5][];
            for(int i=1;i<5;i++){
                valuesForVectors[i]= new int[pows[i]];
                computed[i] = new boolean[pows[i]];
            }
        }
    }

    public ExtendedFunction() {
        valuesForVectors = new int[5][];
        computed = new boolean[5][];
        for(int i=1;i<5;i++){
            valuesForVectors[i]= new int[pows[i]];
            computed[i] = new boolean[pows[i]];
        }
    }

    public int getValueForVector(int capacityForVector, int vector){
        if (computed[capacityForVector][vector])
            return valuesForVectors[capacityForVector][vector];
        int res = 0;
        int [] args = new int[capacityForVector];
        int vector2 = vector;
        for (int i=0;i<capacityForVector;i++){
            args[i]=vector2%(dim*dim);
            vector2/=dim*dim;
        }
        for (int i=capacityForVector-1;i>=0;i--){
            res*=dim;
            res+=values[args[i]];
        }
        valuesForVectors[capacityForVector][vector] = res;
        computed[capacityForVector][vector]=true;
        return res;
    }

    public Integer getCapacity() {
        return capacity;
    }


    public void fill(Function function) {
        dim = function.getDim();
        capacity = function.getCapacity();
        values = new int[function.getValues().getValues().size()];
        for (int i=0;i<function.getValues().getValues().size();i++){
            values[i] = function.getValue(i);
        }
        if (capacity==2){
            for(int i=1;i<5;i++){
                Arrays.fill(computed[i],false);
            }
        }
    }
}
