package predicates.factory;

import predicates.domain.Permutation;
import predicates.domain.Predicate;
import predicates.domain.Tuple;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 12.04.12
 * Time: 23:52
 */
public class PredicateFactory_O extends PredicateFactory implements Iterable<Predicate>, Iterator<Predicate> {
    private Permutation currentPermutation;
    private Tuple currentTuple;

    public PredicateFactory_O(Integer dim) {
        this.dim = dim;
        currentPermutation = new Permutation(dim);
        currentPermutation.next();
        currentTuple = new Tuple(2,((dim-2) * (dim-3))/2);
    }


    @Override
    public boolean hasNext() {
        return currentPermutation.hasNext() || currentTuple.hasNext();
    }

    @Override
    public Predicate next() {
        if (currentTuple.hasNext()){
            currentTuple.next();
        } else {
            if (currentPermutation.hasNext()){
                currentPermutation.next();
                currentTuple.reset();
            }
            else {
                return null;
            }
        }
        return makePredicate();
    }

    private Predicate makePredicate() {
        Predicate result = new Predicate(dim,2);
        for (int i = 1;i < currentPermutation.getCapacity(); i++)
            result.addVector(Arrays.asList(currentPermutation.getValue().get(0),currentPermutation.getValue().get(i)));
        for (int i = 0;i < currentPermutation.getCapacity() - 1; i++)
            result.addVector(Arrays.asList(currentPermutation.getValue().get(i),currentPermutation.getValue().get(currentPermutation.getCapacity()-1)));
        int edge = 0;
        for (int parent = 1; parent < dim - 2; parent++) {
            for (int child = 2;child < dim - 1; child++){
                if (currentTuple.getValue(edge) == 1)
                    result.addVector(Arrays.asList(currentPermutation.getValue().get(parent),currentPermutation.getValue().get(child)));
                edge++;
            }
        }
        return result;
    }

    @Override
    public Iterator<Predicate> iterator() {
        return this;
    }

    @Override
    public void remove() {}
}
