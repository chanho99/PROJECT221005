public class Practice_6_1 {
    private int x, y;

    public Practice_6_1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "Point(" + x + "," + y + ")";
    }
    public boolean equals(Object obj) {
        Practice_6_1 p = (Practice_6_1)obj;
        if(x == p.x && y == p.y)
            return true;
        else
            return false;
    }
    public static void main(String [] args) {
        Practice_6_1 p = new Practice_6_1(3, 50);
        Practice_6_1 q = new Practice_6_1(4, 50);
        System.out.println(p);
        if(p.equals(q)) System.out.println("같은 점");
        else System.out.println("다른 점");
    }
}