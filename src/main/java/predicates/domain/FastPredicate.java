package predicates.domain;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FastPredicate {
    public Set<MyArray> vectors;
    public Integer dim;
    public Integer capacity;

    public FastPredicate(Predicate predicate) {
        dim = predicate.getDim();
        capacity = predicate.getCapacity();
        vectors = new HashSet<MyArray>();
        for (ImmutableList<Integer> list:predicate.getVectors()){
            vectors.add(new MyArray(list));
        }
    }

    public Set<MyArray> getVectors() {
        return vectors;
    }

    public int computeHashCode(Object[] array) {
        return Arrays.hashCode(array);
    }

}


