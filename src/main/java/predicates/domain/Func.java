package predicates.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 11.04.12
 * Time: 23:24
 */
public class Func implements Iterable<Func>, Iterator<Func> {
    private Integer dim;
    private Integer capacity;
    private Tuple values;

    public Func(Integer dim, Integer capacity) {
        this.dim = dim;
        this.capacity = capacity;
        values = new Tuple(dim, (int) Math.pow(dim,capacity));
    }

    public Integer getValue(int code){
        return values.getValue(code);
    }

    public Integer getValue(Iterable<Integer> args){
        Integer q = 1;
        Integer s = 0;
        Integer ans = 0;
        for (Integer x:args){
            s++;
            if (s>capacity)
                return null;
            ans += q*x;
            q *= dim;
        }
        return getValue(ans);
    }

    @Override
    public boolean hasNext() {
        return values.hasNext();
    }

    @Override
    public Func next() {
        values.next();
        return this;
    }


    @Override
    public Iterator<Func> iterator() {
        return this;
    }

    @Override
    public void remove() {}

    public Integer getCapacity() {
        return capacity;
    }


    public Tuple getValues() {
        return values;
    }
}
