package predicates.factory;

import predicates.domain.Predicate;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 12.04.12
 * Time: 23:48
 */
public class PredicateFactory_E extends PredicateFactory{
    private Integer dim;
    Predicate current;

    public PredicateFactory_E(Integer dim) {
        this.dim = dim;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Predicate next() {
        return null;
    }

    @Override
    public Iterator<Predicate> iterator() {
        return this;
    }

    @Override
    public void remove() {

    }
}
