package predicates.factory;

import predicates.domain.Predicate;
import predicates.domain.Tuple;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 08.05.12
 * Time: 18:53
 */
public class PredicateFactory_B extends PredicateFactory implements Iterable<Predicate>, Iterator<Predicate> {
    private Integer h;
    private Integer l;
    private Tuple current;
    private Tuple next;
    private Predicate refl;

    public PredicateFactory_B(Integer h, Integer l, Integer dim) throws Exception {
        if (l>1)
            throw new Exception("Now supported l=1");
        this.h = h;
        this.l = l;
        this.dim = dim;
        next = new Tuple(h, dim).next();
        while (!isDifferent(next))
            next = next.next();
        refl = PredicateFactory_Reflecsive.getTau(h, h);
    }

    private boolean isDifferent(Tuple next) {
        boolean bb[] = new boolean[h];
        for (Integer i: next.getValues()){
                bb[i] = true;
        }
        for (boolean b:bb)
            if(!b)
                return false;
        return true;
    }

    @Override
    public Iterator<Predicate> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Predicate next() {
        if(next == null) {
            current = null;
            return null;
        }
        current = new Tuple(next);
        
        next = next.next();
        while (next != null && !isDifferent(next))
            next = next.next();
        
        Predicate answer = new Predicate(dim, h);
        for (Tuple tuple: new Tuple(dim, h)){
            ArrayList<Integer> v = new ArrayList<Integer>();
            for (Integer i: tuple.getValues())
                v.add(current.getValue(i));

            if (refl.contains(v))
                answer.addVector(tuple.getValues());
        }
        return answer;
    }

    @Override
    public void remove() {}
}
