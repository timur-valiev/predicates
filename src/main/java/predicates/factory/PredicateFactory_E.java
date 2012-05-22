package predicates.factory;

import predicates.domain.Function;
import predicates.domain.Predicate;
import predicates.domain.Tuple;

import java.util.*;

public class PredicateFactory_E extends PredicateFactory implements Iterator<Predicate>, Iterable<Predicate>{
    Tuple current;
    
    public PredicateFactory_E(Integer dim) {
        this.dim = dim;
        current = new Tuple(dim-1, dim);
    }

    public Predicate getMostExtensivePredicate(Function function) {
        Map<Integer,Set<Integer>> classes = new HashMap<Integer, Set<Integer>>();
        for(int i=0;i<dim;i++){
            classes.put(i,new HashSet<Integer>());
            classes.get(i).add(i);
        }
        for(int i = 0; i < dim; i++){
            int f = function.getValue(i);
            classes.get(i).addAll(classes.get(f));
            classes.put(f,classes.get(i));
        }
        if(classes.get(dim-1).size()==dim)
            return null;
        Predicate predicate = new Predicate(dim,2);
        for (int i = 0; i < dim;i++){
            for (int j:classes.get(i)){
                ArrayList<Integer> vector = new ArrayList<Integer>();
                vector.add(i);
                vector.add(j);
                predicate.addVector(vector);
            }
        }
        return predicate;
    }

    @Override
    public Iterator<Predicate> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return current.hasNext() && !current.isPreFull();
    }

    @Override
    public Predicate next() {
        current.next();
        while (current.isHomogeneous() && current.hasNext())
            current.next();
        if (current.isFull())
            return null;
        Predicate answer = new Predicate(dim,2);
        for (int i=0;i<dim;i++)
            for(int j=0;j<dim;j++)
                if (current.getValue(i).equals(current.getValue(j)))
                    answer.addVector(Arrays.asList(i,j));
        return answer;
    }

    @Override
    public void remove() {
    }
}
