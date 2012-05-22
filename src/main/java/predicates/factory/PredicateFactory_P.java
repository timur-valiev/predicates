package predicates.factory;

import predicates.domain.Permutation;
import predicates.domain.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class PredicateFactory_P extends PredicateFactory implements Iterable<Predicate>, Iterator<Predicate> {
    protected Permutation currentPerm;
    protected Permutation nextPerm;

    public PredicateFactory_P(Integer dim) {
        this.dim = dim;
        currentPerm = null;
        nextPerm = new Permutation(dim).next();
        while (!nextPerm.isProductOfSimpleEqualCycles())
            nextPerm.next();
    }

    @Override
    public boolean hasNext() {
        return nextPerm != null;
    }

    @Override
    public Predicate next() {
        currentPerm = new Permutation(nextPerm);
        nextPerm = nextPerm.next();
        while (nextPerm != null&& !nextPerm.isProductOfSimpleEqualCycles())
            nextPerm.next();
        return makePredicate();
    }

    private Predicate makePredicate() {
        Predicate result = new Predicate(dim, 2);
        for (int i = 0;i < currentPerm.getValue().size();i++ ){
            result.addVector(Arrays.asList(i, currentPerm.getValue().get(i)));
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
