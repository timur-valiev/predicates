package predicates.domain;

import java.util.Iterator;

public class Function implements Iterable<Function>, Iterator<Function> {
    private Integer dim;//размерность
    private Integer capacity;//местность
    private Tuple values;//значения

    public Function(Integer dim, Integer capacity) {
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
    public Function next() {
        values.next();
        return this;
    }

    @Override
    public Iterator<Function> iterator() {
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
