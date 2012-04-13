package predicates.domain;

import java.util.ArrayList;
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
    private List<Integer> nextPerm;

    public Permutation(Integer capacity) {
        this.capacity = capacity;
        nextPerm = new ArrayList<Integer>(capacity);
        for (int i=0;i<capacity;i++)
            nextPerm.set(i,i);
        value = null;
        num = 0;
    }

    @Override
    public Iterator<Permutation> iterator() {
        return this;    
    }

    @Override
    public boolean hasNext() {
        return nextPerm != null;
    }

    private int factorial(Integer x) {
        return x<=0?1:x*factorial(x-1);
    }

    @Override
    public Permutation next() {
        value = new ArrayList<Integer>(nextPerm);
        num++;
        return this;
    }

    private boolean next(List<Integer> perm, int position) {
        if (position == capacity)
            return false;
        if (next(perm, position+1))
            return true;
        Integer next = max(perm.subList(position+1, perm.size()));
        if (next < perm.get(position))
            return false;
        Integer pos = 0;
        for (int i = position+1; i < perm.size();i++) {
            if (perm.get(i) > perm.get(position) && perm.get(i) <= next) {
                next = perm.get(i);
                pos = i;
            }
        }
        Integer tmp = perm.get(pos);
        perm.set(pos,perm.get(position));
        perm.set(position, tmp);
        return true;
    }

    public boolean isProductOfSimpleEqualCycles(){
        ArrayList<Boolean> checked = new ArrayList<Boolean>(capacity);
        Integer cs = 0;
        for (int i=0;i<capacity;i++){
            if (!checked.get(i)){
                Integer ccs = 1;
                checked.set(i,true);
                Integer cur = value.get(i);
                while(!checked.get(cur)){
                    ccs++;
                    checked.set(cur,true);
                    cur = value.get(cur);
                }
                if(cs==0)
                    cs = ccs;
                else
                    if(!cs.equals(ccs))
                        return false;
                if (!cur.equals(i))
                    return false;
            }
        }
        return true;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getNum() {
        return num;
    }

    public List<Integer> getValue() {
        return value;
    }

    @Override
    public void remove() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
