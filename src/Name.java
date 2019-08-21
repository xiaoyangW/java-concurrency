import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaoyang.wen
 * @date 2019/7/15 11:52
 */
public class Name {
    private String first, last;

    public Name(String first, String last) {
        if (first == null || last == null)
            throw new NullPointerException();
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        return first.equals(((Name)o).first) && last.equals(((Name)o).last);
    }

    public boolean equals(Name o) {
        return first.equals(o.first) && last.equals(o.last);
    }
    @Override
    public int hashCode() {
        return 31 * first.hashCode() + last.hashCode();
    }

    public static void main(String[] args) {
        final int start = Integer.MAX_VALUE - 100;
        final int end = Integer.MAX_VALUE;
        int count = 0;
        for (int i = start; i <= end; i++)
            count++;
        System.out.println(count);
    }
}
