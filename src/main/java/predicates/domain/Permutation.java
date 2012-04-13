package predicates.domain;

import java.util.Iterator;
import java.util.List;

import static java.util.Collections.max;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 13.04.12
 * Time: 12:05
 */
public class Permutation implements Iterable<Permutation>, Iterator<Permutation> {
    private Integer capacity;
    private Integer num;
    private List<Integer> value;
    
    @Override
    public Iterator<Permutation> iterator() {
        return this;    
    }

    @Override
    public boolean hasNext() {
        return num < factorial(capacity)-1; 
    }

    private int factorial(Integer x) {
        return x<=0?1:x*factorial(x-1);
    }

    @Override
    public Permutation next() {
        if (next(value,0))
            return this;
        return null;
    }

    private boolean next(List<Integer> value, int position) {
        if (position == capacity)
            return false;
        if (next(value, position+1))
            return true;
        Integer next = max(value.subList(position+1, value.size()));
        if (next < value.get(position))
            return false;
        Integer pos = 0;
        for (int i = position+1; i < value.size();i++) {
            if (value.get(i) > value.get(position) && value.get(i) <= next) {
                next = value.get(i);
                pos = i;
            }
        }
        Integer tmp = value.get(pos);
        value.set(pos,value.get(position));
        value.set(position, tmp);
        return true;
    }

    @Override
    public void remove() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
