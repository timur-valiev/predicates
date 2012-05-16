package predicates.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 25.04.12
 * Time: 16:06
 */
public class Tuple implements Iterable<Tuple>, Iterator<Tuple> {
    private int dim;
    private List<Integer> current;
    private List<Integer> next;
    private Integer capacity;

    public Tuple(int dim, int capacity) {
        this.dim = dim;
        this.capacity = capacity;
        next = new ArrayList<Integer>();
        for (int i=0;i<capacity;i++)
            next.add(0);
    }

    public Tuple(Tuple next) {
        this.dim = next.dim;
        this.capacity = next.capacity;
        this.next = new ArrayList<Integer>(next.next);
        this.current = new ArrayList<Integer>(next.current);
    }

    public Integer getValue(int code){
        return current.get(code);
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Tuple next() {
        if (next == null) {
            current = null;
            return null;
        }
        current = new ArrayList<Integer>(next);
        int i = 0;
        boolean changed = false;
        while (i < next.size() && !changed){
            if (next.get(i) == dim-1){
                next.set(i,0);
                i++;
            } else {
                next.set(i, next.get(i)+1);
                changed = true;
            }
        }
        if (!changed)
            next = null;
        return this;
    }

    @Override
    public Iterator<Tuple> iterator() {
        return this;
    }

    @Override
    public void remove() {}

    public List<Integer> getValues() {
        return current;
    }

    public void reset() {
        next = new ArrayList<Integer>();
        for (int i=0;i<capacity;i++)
            next.add(0);
    }

    public boolean isStart() {
        if(current == null)
            return false;
        for (Integer i: current)
            if (i!=0)
                return false;
        return true;
    }

    public boolean isFull() {
        if(current == null)
            return false;
        for (Integer i: current)
            if (i!= dim-1)
                return false;
        return true;
    }

    public boolean isPreFull() {
        if(current == null)
            return false;
        if(current.get(0)!= dim-2)
            return false;
        for(int i=1;i<capacity;i++)
            if(current.get(i)!=dim-1)
                return false;
        return true;
    }

    public boolean isHomogeneous(){
        if(current == null)
            return false;
        int a = current.get(0);
        for (Integer i: current)
            if (i!= a)
                return false;
        return true;
    }
}
