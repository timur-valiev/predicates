package predicates;

import com.google.common.collect.ImmutableList;
import predicates.domain.Function;
import predicates.domain.Predicate;

import java.util.ArrayList;

public class PredicateService {
    //Метод, проверяющий факт сохранения функцией предиката
    public static boolean checkSave(Predicate predicate, Function function){
        if (function.getCapacity()==1){
            ArrayList<Integer> res = new ArrayList<Integer>();
            for (ImmutableList<Integer> cur:  new ArrayList<ImmutableList<Integer>>(predicate.getVectors())) {
                res.clear();
                for (Integer i: cur)
                    res.add(function.getValue(i));
                if (!predicate.getVectors().contains(ImmutableList.copyOf(res)))
                    return false;
            }
            return true;
        }
        else 
            return false;
    }
}
