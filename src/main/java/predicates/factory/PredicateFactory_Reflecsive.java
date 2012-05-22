package predicates.factory;

import predicates.domain.Predicate;
import predicates.domain.Tuple;

public class PredicateFactory_Reflecsive extends PredicateFactory{
    
    public static Predicate getTau(Integer dim, Integer capacity){
        if (capacity == 1){
            return new Predicate(dim,capacity);
        }
        Predicate refl = new Predicate(dim, capacity);
        for (Tuple tuple: new Tuple(dim, capacity))
            if (isRefl(tuple, dim))
                refl.addVector(tuple.getValues());
        return refl;
    }

    public static boolean isRefl(Tuple tuple, Integer dim) {
        boolean bb[] = new boolean[dim];
        for (Integer i: tuple.getValues()){
            if (!bb[i])
                bb[i] = true;
            else
                return true;
        }
        return false;
    }
}
