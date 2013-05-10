package predicates.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 20.11.12
 * Time: 15:29
 */
public class MyArray {
    public int[] array;

    public MyArray(int size) {
        array = new int[size];
    }

    public MyArray(List<Integer> list) {
        array = new int[list.size()];
        for (int j=0;j<list.size();j++)
            array[j]=list.get(j);
    }

    public MyArray(Scanner scanner, Integer capacity) {
        array = new int[capacity];
        for (int j=0;j<capacity;j++)
            array[j]=scanner.nextInt();

    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyArray))
            return false;
        return Arrays.equals(((MyArray) obj).array, array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
