/**
 * @author xiaoyang.wen
 * @date 2019/7/23 13:33
 */
public enum  PowerOfTen {
    ONE(1), TEN(10),
    HUNDRED(100) {
        @Override
        public String toString() {
            return Integer.toString(val);
        }
    };
     final int val;

    PowerOfTen(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(ONE + " " + TEN + " " + HUNDRED);
    }
}
