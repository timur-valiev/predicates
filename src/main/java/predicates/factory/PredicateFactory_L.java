package predicates.factory;

import predicates.domain.Permutation;
import predicates.domain.Predicate;
import predicates.domain.Tuple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PredicateFactory_L extends PredicateFactory implements Iterable<Predicate>, Iterator<Predicate> {
    private int[][] table = new int[4][];
    private Permutation current;
    
    public PredicateFactory_L(Integer dim) throws Exception {
        this.dim = dim;
        current = new Permutation(dim);
        if (dim==4){
            table[0] = new int[]{0, 1, 2, 3};
            table[1] = new int[]{1, 0, 3, 2};
            table[2] = new int[]{2, 3, 0, 1};
            table[3] = new int[]{3, 2, 1, 0};
        }
        else throw new Exception("Not supported dim");
    }

    public Predicate getPredicate4(){
        Predicate predicate = new Predicate(dim,4);
        for (Tuple tuple: new Tuple(4,4)){
            if(table[tuple.getValue(0)][tuple.getValue(1)]==table[tuple.getValue(2)][tuple.getValue(3)]){
                List<Integer> a = new ArrayList<Integer>();
                a.add(current.getValue().get(tuple.getValue(0)));
                a.add(current.getValue().get(tuple.getValue(1)));
                a.add(current.getValue().get(tuple.getValue(2)));
                a.add(current.getValue().get(tuple.getValue(3)));
                predicate.addVector(a);
            }
        }
        return predicate;
    }

    @Override
    public Iterator<Predicate> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return current.hasNext();
    }

    @Override
    public Predicate next() {
        if(current.hasNext())
            current.next();
        else 
            return null;
        return getPredicate4();
    }

    @Override
    public void remove() {}
}
