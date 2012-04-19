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

    public void addVector(List<Integer> vector){
        vectors.add(ImmutableList.copyOf(vector));
    }

    public Set<ImmutableList<Integer>> getVectors() {
        return vectors;
    }
}
