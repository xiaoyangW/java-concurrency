/**
 * @author xiaoyang.wen
 * @date 2019/7/15 11:43
 */
public class Confusing {
    public Confusing(Object o) {
        System.out.println("Object");
    }
    public Confusing(Object[] dArray) {
        System.out.println("double array");
    }
    public static void main(String args[]) {
        new Confusing(null);
    }

}
