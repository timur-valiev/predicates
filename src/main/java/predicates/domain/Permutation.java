package predicates.domain;

import java.util.*;

import static java.util.Collections.max;
import static java.util.Collections.sort;

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
    private Set<Integer> primes = new HashSet<Integer>();

    public Permutation(Integer capacity) {
        this.capacity = capacity;
        nextPerm = new ArrayList<Integer>(capacity);
        for (int i=0;i<capacity;i++)
            nextPerm.add(i);
        value = null;
        num = 0;
        primes.add(2);
        primes.add(3);
        primes.add(5);
        primes.add(7);
        primes.add(11);
    }

    public Permutation(Permutation perm) {
        this.capacity = perm.getCapacity();
        this.nextPerm = perm.nextPerm!=null?new ArrayList<Integer>(perm.nextPerm):null;
        this.value = new ArrayList<Integer>(perm.getValue());
        this.num = perm.getNum();
        this.primes = perm.primes;
    }

    @Override
    public Iterator<Permutation> iterator() {
        return this;    
    }

    @Override
    public boolean hasNext() {
        return nextPerm != null;
    }

    @Override
    public Permutation next() {
        if (nextPerm==null)
            return null;
        value = new ArrayList<Integer>(nextPerm);
        if(!next(nextPerm,0))
            nextPerm = null;
        num++;
        return this;
    }

    private boolean next(List<Integer> perm, int position) {
        if (position == capacity - 1)
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
        List<Integer> tail = perm.subList(position + 1, perm.size());
        sort(tail);
        for (int i = position+1;i<perm.size();i++)
            perm.set(i,tail.get(i-position-1));
        return true;
    }

    public boolean isProductOfSimpleEqualCycles(){
        ArrayList<Boolean> checked = new ArrayList<Boolean>();
        for (int i=0;i<capacity;i++)
            checked.add(false);
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
        return primes.contains(cs);
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
