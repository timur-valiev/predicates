package predicates;

import predicates.domain.FastPredicate;
import predicates.domain.Function;
import predicates.domain.MyArray;

import java.util.ArrayList;

public class PredicateService {
    //Метод, проверяющий факт сохранения функцией предиката
    public static boolean checkSave(FastPredicate predicate, Function function){
        MyArray res;
        if (function.getCapacity()==1){
            for (MyArray cur:  predicate.getVectors()) {
                res = new MyArray(cur.array.length);
                int ii=0;
                for (Integer i: cur.array){
                    res.array[ii]=(function.getValue(i));
                }
                if (!predicate.getVectors().contains(res))
                    return false;
            }
            return true;
        }
        else
        if(function.getCapacity()==2){
            ArrayList<Integer> args = new ArrayList<Integer>();
            args.add(0);
            args.add(0);
            for (MyArray cur1:  predicate.getVectors())
            for (MyArray cur2:  predicate.getVectors()) {
                res = new MyArray(cur1.array.length);
                for (Integer i=0;i<cur1.array.length;i++){
                    args.set(0,cur1.array[i]);
                    args.set(1,cur2.array[i]);
                    res.array[i]=(function.getValue(args));
                }

                if (!predicate.getVectors().contains(res))
                    return false;
            }
            return true;
        }
        return false;
    }
}
