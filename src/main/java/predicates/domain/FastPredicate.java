package predicates.domain;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FastPredicate {
    public Set<MyArray> vectors;
    public Integer dim;
    public Integer capacity;
    public boolean[] codes;//коды всех векторов
    public int[] pairCodes;
    public byte[] existValues;

    public FastPredicate(Predicate predicate) {
        dim = predicate.getDim();
        capacity = predicate.getCapacity();
        vectors = new HashSet<MyArray>();
        codes = new boolean[(int) Math.pow(dim,capacity)];
        existValues = new byte[predicate.getVectors().size()];

        int ii=0;
        for (ImmutableList<Integer> list:predicate.getVectors()){
            vectors.add(new MyArray(list));
            Integer x = 0;
            for (Integer i:list){
                x=x*dim+i;
                existValues[ii] |= 1<<i;
            }
            codes[x]=true;
            ii++;
        }

        pairCodes = new int[vectors.size()*vectors.size()];
        ii=0;
        for (MyArray cur1:  vectors)
            for (MyArray cur2:  vectors){
                int res = 0;
                for (int i=0;i<cur1.array.length;i++){
                    res*=dim*dim;
                    res+=cur1.array[i]*dim+cur2.array[i];
                }
                pairCodes[ii]=res;
                ii++;
            }

    }

    public Set<MyArray> getVectors() {
        return vectors;
    }

    public int computeHashCode(Object[] array) {
        return Arrays.hashCode(array);
    }

}


