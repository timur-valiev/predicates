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
    private List<Integer> values;
    private List<Integer> nextFunc;
    private Long num;

    public Func(Integer dim, Integer capacity) {
        this.dim = dim;
        this.capacity = capacity;
        num = 0l;
        values = null;
        nextFunc = new ArrayList<Integer>((int) Math.pow(dim, capacity));
    }

    public Integer getValue(int code){
        return values.get(code);
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
        return nextFunc != null;
    }

    @Override
    public Func next() {
        values = new ArrayList<Integer>(nextFunc);
        int i = 0;
        boolean changed = false;
        while (i < nextFunc.size() && !changed){
            if (nextFunc.get(i) == dim-1){
                nextFunc.set(i,0);
                i++;
            } else {
                nextFunc.set(i, nextFunc.get(i)+1);
                changed = true;
                num++;
            }
        }
        if (!changed)
            nextFunc = null;
        return this;
    }


    @Override
    public Iterator<Func> iterator() {
        return this;
    }

@Override
    public void remove() {
    }
}
