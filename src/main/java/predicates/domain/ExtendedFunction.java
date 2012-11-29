package predicates.domain;

import java.util.Arrays;

public class ExtendedFunction {
    private Integer dim;
    private Integer capacity;
    public int[] values;
    public int[][] valuesForVectors;
    public static int[] pows;
    public byte existValues;

    static {
        pows=new int[5];
        for(int i=1;i<5;i++){
            pows[i]= (int)Math.pow(4,2*i);
        }
    }

    public ExtendedFunction() {
        valuesForVectors = new int[5][];
        existValues = 0;
        for(int i=1;i<4;i++){
            valuesForVectors[i]= new int[pows[i]];
        }
    }

    public void fill(Function function) {
        dim = function.getDim();
        capacity = function.getCapacity();
        values = new int[function.getValues().getValues().size()];
        existValues = 0;
        for (int i=0;i<function.getValues().getValues().size();i++){
            values[i] = function.getValue(i);
            existValues |= 1 << function.getValue(i);
        }
        if (capacity==2){
            for(int capacityForVector=1;capacityForVector<4;capacityForVector++){
                for(int vector=0;vector<pows[capacityForVector];vector++){
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
                }
            }
        }
    }

    public int getValueForVector(int capacityForVector, int vector){
        if (capacity<4)
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
        return res;
    }

    public Integer getCapacity() {
        return capacity;
    }

}
