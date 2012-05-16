package predicates.domain;

import com.google.common.collect.ImmutableList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 11.04.12
 * Time: 23:24
 */
public class Predicate {
    private Integer dim;
    private Integer capacity;
    private Set<ImmutableList<Integer>> vectors = new HashSet<ImmutableList<Integer>>();

    public Predicate(Integer dim, Integer capacity) {
        this.dim = dim;
        this.capacity = capacity;
    }

    public Predicate(Predicate refl) {
        this.dim = refl.dim;
        this.capacity = refl.capacity;
        vectors = new HashSet<ImmutableList<Integer>>(refl.vectors);
    }

    public void addVector(List<Integer> vector){
        vectors.add(ImmutableList.copyOf(vector));
    }

    public boolean contains(List<Integer> v) {
        return vectors.contains(ImmutableList.copyOf(v));
    }

    public Set<ImmutableList<Integer>> getVectors() {
        return vectors;
    }


    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Predicate) {
            Predicate that = (Predicate) obj;
            result = this.vectors.containsAll(that.vectors) && that.vectors.containsAll(this.vectors);
        }
        return result;
    }

    @Override
    public int hashCode() {
        Integer ans = dim.hashCode()+capacity.hashCode();
        for(ImmutableList<Integer> list:vectors)
            ans += list.hashCode();
        return ans;
    }
}
