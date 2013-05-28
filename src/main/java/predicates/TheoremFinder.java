package predicates;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Valiev
 * Date: 27.05.13
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class TheoremFinder {
    static ArrayList<ArrayList<Integer>> predToFunc = new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> funcToPred = new ArrayList<ArrayList<Integer>>();
    static int[][] probs = new int[82][122283];//для i j хранит сколько предикатов забивается функцией #i начиная с позиции j
    static boolean[][] matrix = new boolean[122283][82];
    static String[] predNames = new String[82];

    private static void prepareData() throws FileNotFoundException {
        Scanner scanner1 = new Scanner(new FileInputStream("text/predicates.txt"));
        for (int i = 0; i < 82; i++) {
            String s;
            s = scanner1.next();
            s = scanner1.next();
            int k = scanner1.nextInt();
            s = scanner1.nextLine();
            for (int j = 0; j < k; j++)
                s = scanner1.nextLine();
            predNames[i] = scanner1.next();
        }


        for (int i = 0; i < 82; i++)
            predToFunc.add(new ArrayList<Integer>());
        for (int i = 0; i < 122283; i++)
            funcToPred.add(new ArrayList<Integer>());

        Scanner scanner = new Scanner(new FileInputStream("text/result_new_withfunk.txt"));
        for (int i = 0; i < 122283; i++) {
            for (int j = 0; j < 82; j++) {
                int x = scanner.nextInt();
                if (x == 1) {
                    matrix[i][j] = true;
                    predToFunc.get(j).add(i);
                    funcToPred.get(i).add(j);
                    for (int k = 0; k < j; k++)
                        probs[k][i]++;
                }
            }
            scanner.nextLong();
        }

    }


    public static void main(String[] args) throws Exception {
        prepareData();
        Map<String, Set<Integer>> thms= new TreeMap<String, Set<Integer>>();
        int num=0;
        BufferedWriter writer = new BufferedWriter(new FileWriter("text/theorems.txt"));
        writer.write("111");
        writer.flush();
        for (int i = 0; i < 82; i++)
            for (int j = i + 1; j < 82; j++) {
                Set<Integer> in = new TreeSet<Integer>();
                Set<Integer> out = new TreeSet<Integer>();
                for (int k = 0; k < 122283; k++)
                    for (int q = 0; q < 82; q++) {
                        if (matrix[k][q]) {
                            if (matrix[k][i] || matrix[k][j])
                                in.add(q);
                            else
                                out.add(q);
                        }
                    }

                in.removeAll(out);
                in.remove(i);
                in.remove(j);
                if (!in.isEmpty()) {
                    num++;
                    if(!thms.containsKey(i+" "+j))
                        thms.put(i+" "+j,new TreeSet<Integer>());
                    boolean b=false;
                    for (int k : in){
                        if(b)
                            System.out.print(" \\cup ");
                        else{
                            System.out.print("&$ ");
                            b=true;
                        }
                        System.out.print(predNames[k]);
                        thms.get(i+" "+j).add(k);
                    }
                    System.out.print(" \\subseteq " +predNames[i] + " \\cup " + predNames[j]);
                    System.out.println("$ & (" + num + ") \\\\");
                }
            }


        for (int i = 0; i < 82; i++)
            for (int j = i + 1; j < 82; j++)
            for(int z=j+1;z<82;z++){
                Set<Integer> in = new TreeSet<Integer>();
                Set<Integer> out = new TreeSet<Integer>();
                for (int k = 0; k < 122283; k++)
                    for (int q = 0; q < 82; q++) {
                        if (matrix[k][q]) {
                            if (matrix[k][i] || matrix[k][j] || matrix[k][z])
                                in.add(q);
                            else
                                out.add(q);
                        }
                    }

                in.removeAll(out);
                in.remove(i);
                in.remove(j);
                in.remove(z);
                ArrayList<Integer> xx = new ArrayList<Integer>(in);
                for (int k:xx){
                    if ((thms.containsKey(i+" "+j) && thms.get(i+" "+j).contains(k))||(thms.containsKey(i+" "+z) && thms.get(i+" "+z).contains(k))||(thms.containsKey(j+" "+z) && thms.get(j+" "+z).contains(k)))
                        in.remove(k);
                }
                if (!in.isEmpty()) {
                    num++;
                    if(!thms.containsKey(i+" "+j+" "+z))
                        thms.put(i+" "+j+" "+z,new TreeSet<Integer>());
                    boolean b=false;
                    for (int k : in){
                        if(b)
                            writer.write(" \\cup ");
                        else{
                            writer.write("&$ ");
                            b=true;
                        }
                        writer.write(predNames[k]);
                        thms.get(i+" "+j+" "+z).add(k);
                    }
                    writer.write(" \\subseteq " +predNames[i] + " \\cup " + predNames[j]+ " \\cup " + predNames[z]);
                    writer.write("$ & ("+num+") \\\\\n");
                    writer.flush();
                }
            }
    }
}