import java.util.Calendar;

/**
 * @author xiaoyang.wen
 * @date 2019/7/15 11:23
 */
public class Elvis {
    private static final int CURRENT_YEAR =
        Calendar.getInstance().get(Calendar.YEAR);
    public static final Elvis INSTANCE = new Elvis();
    private final int beltSize;



    private Elvis() {
        beltSize = CURRENT_YEAR - 1930;
    }

    public int beltSize() {
        return beltSize;
    }

    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().get(Calendar.YEAR));
        System.out.println("Elvis wears size " +
                INSTANCE.beltSize() + " belt.");
    }
}
