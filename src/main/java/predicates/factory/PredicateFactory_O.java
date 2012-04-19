package predicates.factory;

import predicates.domain.Predicate;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 12.04.12
 * Time: 23:52
 */
public class PredicateFactory_O extends PredicateFactory{
    private Integer dim;
    Predicate current;

    public PredicateFactory_O(Integer dim) {
        this.dim = dim;
    }


}
