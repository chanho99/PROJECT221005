import java.util.Random;
import java.util.Scanner;

abstract class game {
    protected int distance;
    protected int x, y;

    public game(int startX, int startY, int distance)	 {
        this.x = startX;
        this.y = startY;
        this.distance = distance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean collide(game p) {
        if(this.x == p.getX() && this.y == p.getY()) {
            return true;
        } else {
            return false;
        }
    }

    protected abstract void move(int inputX, int inputY);
    protected abstract char getShape();
}

class Bear extends game {

    public Bear(int startX, int startY, int distance) {
        super(startX, startY, distance);
    }

    protected void move(int inputX, int inputY) {
        if(!(getX() == 0 && inputX == -1)) {
            this.x += inputX;
        }
        if(!(getY() == 0 && inputY == -1)) {
            this.y += inputY;
        }
    }

    protected char getShape() {
        return 'B';
    }

}

class Fish extends game {
    Random rand = new Random();
    int randomMove;
    int cnt = 1;

    public Fish(int startX, int startY, int distance) {
        super(startX, startY, distance);
    }

    protected void move(int x, int y) {
        if (cnt < 4) {
            cnt++;
            return;
        } else {
            randomMove = rand.nextInt(4);
            switch (randomMove) {
                case 0:
                    setX(getX() + 1);
                    break;
                case 1:
                    setX(getX() - 1);
                    break;
                case 2:
                    setY(getY() + 1);
                    break;
                case 3:
                    setY(getY() - 1);
            }
            if (cnt == 5) {
                cnt = 1;
                return;
            }
            cnt++;
        }
    }

    protected char getShape() {
        return '@';
    }
}

public class OpenChallenge5 {
    Scanner scan = new Scanner(System.in);
    char[][] map;
    Bear bear;
    Fish fish;
    int inputX = 0, inputY = 0;

    OpenChallenge5() {
        run();
    }

    void showMap(Bear bear, Fish fish) {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                map[i][j] = '-';
                if(i == fish.getX() && j == fish.getY()) {
                    map[i][j] = fish.getShape();
                }
                if(i == bear.getX() && j == bear.getY()) {
                    map[i][j] = bear.getShape();
                }
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    void convertInput(String choice) {
        while(true) {
            if(choice.equals("a")) {
                inputY = -1;
                return;
            } else if(choice.equals("s")) {
                inputX = 1;
                return;
            }else if(choice.equals("w")) {
                inputX = -1;
                return;
            } else if(choice.equals("d")) {
                inputY = 1;
                return;
            } else {
                System.out.println("잘못입력하셨습니다.");
            }
        }
    }

    void userControl(Bear bear, Fish fish) {
        System.out.print("왼쪽(a), 아래(s), 위(w), 오른쪽(d) >> ");
        convertInput(scan.next());
        bear.move(inputX, inputY);
        fish.move(0, 0);
        System.out.print("Bear: (" + bear.getX() + ", " + bear.getY() + ") \t");
        System.out.println("Fish: (" + fish.getX() + ", " + fish.getY() + ")");

    }

    void run() {
        map = new char[10][20];

        bear = new Bear(0, 0, 1);
        fish = new Fish(5, 5, 1);

        System.out.println("** Bear의 Fish 먹기 게임을 시작합니다.**");

        while (true) {
            showMap(bear, fish);
            if(bear.collide(fish)) {
                System.out.println("Bear Wins!!");
                break;
            }
            userControl(bear, fish);
        }
    }

    public static void main(String[] args) {
        new OpenChallenge5();
    }
}
