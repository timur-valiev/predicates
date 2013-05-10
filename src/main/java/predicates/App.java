package predicates;

import predicates.domain.ExtendedFunction;
import predicates.domain.FastPredicate;
import predicates.domain.Function;
import predicates.domain.Predicate;
import predicates.factory.*;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.*;

/*
Main class-Launcher
*/
public class App {
    public static void main(String[] args) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("text/prilozh_A3.txt"));

        FastPredicate[] fastPredicates = new FastPredicate[82];
        Scanner scanner= new Scanner(new FileInputStream("text/predicates.txt"));
        for (int i = 0; i < 82; i++)
            fastPredicates[i] = new FastPredicate(scanner);

        Map<String, Long> ans = new HashMap<String, Long>();

        Date date = new Date();

        ExtendedFunction extendedFunction = new ExtendedFunction();
        B4_checker b4_checker = new B4_checker();

        Long ii=0l;
        for (Function function : new Function(4, 2)) {
            if (ii % (1000000) == 0)
                System.out.println(ii.toString() + " " + ans.size() + " " + (new Date((new Date()).getTime() - date.getTime())));
            ii++;
            extendedFunction.fill(function);

            boolean hasPrev=false;
            for (int i=15;i>=0;i--){
                int l=i/4;
                int r=i%4;
                if (l>r){
                    if(extendedFunction.values[i]>extendedFunction.values[r*4+l]){
                        hasPrev = true;
                        break;
                    }
                    if(extendedFunction.values[i]<extendedFunction.values[r*4+l]){
                        break;
                    }
                }
            }
            if(hasPrev)
                continue;
            StringBuilder ss = new StringBuilder();
            for (int i = 0; i < fastPredicates.length - 1; i++) {
                if (PredicateService.checkSave(fastPredicates[i], extendedFunction))
                    ss.append("1 ");
                else
                    ss.append("0 ");
            }
            if (b4_checker.check(extendedFunction))
                ss.append("1 ");
            else
                ss.append("0 ");

            if(!ans.containsKey(ss.toString())){
                ans.put(ss.toString(),ii-1);
            }

        }

        for (String s : ans.keySet()) {
            writer.write(ans.get(s)+" "+s+"\n");
        }

        writer.flush();
        writer.close();

    }

    private static String makeResult(ExtendedFunction extendedFunction, FastPredicate[] fastPredicates){
        StringBuilder ss = new StringBuilder();
        B4_checker b4_checker = new B4_checker();
        for (int i = 0; i < fastPredicates.length - 1; i++) {
            if (PredicateService.checkSave(fastPredicates[i], extendedFunction))
                ss.append("1 ");
            else
                ss.append("0 ");
        }
        if (b4_checker.check(extendedFunction))
            ss.append("1 ");
        else
            ss.append("0 ");

        return ss.toString();
    }
}

class B4_checker {
    ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    HashSet<Integer> set1 = new HashSet<Integer>();
    HashSet<Integer> set2 = new HashSet<Integer>();

    B4_checker() {
        list.add(new ArrayList<Integer>());
        list.add(new ArrayList<Integer>());
        list.add(new ArrayList<Integer>());
        list.add(new ArrayList<Integer>());
    }

    public boolean check(ExtendedFunction function) {
        list.get(0).clear();
        list.get(1).clear();
        list.get(2).clear();
        list.get(3).clear();
        set1.clear();
        set2.clear();
        for (int i = 0; i < 16; i++)
            list.get(function.values[i]).add(i);
        if (list.get(0).size() * list.get(1).size() * list.get(2).size() * list.get(3).size() == 0)
            return true;
        for (Integer i0 : list.get(0))
            for (Integer i1 : list.get(1))
                for (Integer i2 : list.get(2))
                    for (Integer i3 : list.get(3)) {
                        set1.add(i0%4);set1.add(i1%4);set1.add(i2%4);set1.add(i3%4);
                        set2.add((i0/4)%4);set2.add((i1/4)%4);set2.add((i2/4)%4);set2.add((i3/4)%4);
                        if(set1.size()<4 && set2.size()<4)
                            return false;
                        set1.clear();
                        set2.clear();
                    }


        return true;
    }
}
