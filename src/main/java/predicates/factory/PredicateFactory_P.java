package predicates.factory;

import predicates.domain.Permutation;
import predicates.domain.Predicate;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 12.04.12
 * Time: 23:44
 */
public class PredicateFactory_P extends PredicateFactory implements Iterable<Predicate>, Iterator<Predicate> {
    protected Permutation currentPerm;
    protected Permutation nextPerm;
    private Predicate value;

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
        makePredicate();
        return value;
    }

    private void makePredicate() {
        value = new Predicate(dim, 2);
        for (int i = 0;i < currentPerm.getValue().size();i++ ){
            ArrayList<Integer> vector = new ArrayList<Integer>();
            vector.add(i);
            vector.add(currentPerm.getValue().get(i));
            value.addVector(vector);
        }
    }

    @Override
    public Iterator<Predicate> iterator() {
        return this;
    }

    @Override
    public void remove() {}
}
