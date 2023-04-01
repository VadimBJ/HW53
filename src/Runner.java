import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner {
  public static final int MIN_X = -15;
  public static final int MAX_X = 15;
  public static final int MIN_Y = -15;
  public static final int MAX_Y = 15;
  public static int fieldX;
  public static int fieldY;
  public static int center;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    fieldX = MAX_X - MIN_X;
    fieldY = MAX_Y - MIN_Y;
    int radius = 10;
    char[][] charArray = new char[fieldY][fieldX];
    buildField(charArray);
    buildCircle(charArray, radius);
    placePoint(charArray, Point.read(br), radius);
    showField(charArray);
  }

  public static String checkPoint(int x, int y, int radius,Point point) {
    String result1 = "";
    String result2 = "";
    if (x == center && y == center) {
      result1 = "попали в центр";
    } else if (x == center) {
      result1 = "попали в Y";
    } else if (y == center) {
      result1 = "попали в X";
    }
    if (x > center && y > center) {
      result1 = "находимся в IV координатной четверти";
    }
    if (x < center && y > center) {
      result1 = "находимся в III координатной четверти";
    }
    if (x < center && y < center) {
      result1 = "находимся в II координатной четверти";
    }
    if (x > center && y < center) {
      result1 = "находимся в I координатной четверти";
    }
    x=point.getX();
    y=point.getY();
    int testPoint = (int) Math.round((Math.sqrt((x * x) + (y * y))));
    if (testPoint < radius) {
      result2 = "находимся внутри круга";
    } else if (testPoint > radius) {
      result2 = "находимся снаружи круга";
    } else {
      result2 = "находимся на границе круга";
    }
    return result1 + "," + result2;
  }

  public static void placePoint(char[][] charArray, Point point, int radius) {
    int x = center + point.getX();
    int y = center - point.getY();
    charArray[y][x] = '◉';
    System.out.println(checkPoint(x, y, radius,point));
  }

  public static void buildCircle(char[][] charArray, int radius) {
    for (int row = 0; row < fieldY; row++) {
      for (int column = 0; column < fieldX; column++) {
        int x = column + MIN_X;
        int y = row + MIN_Y;
        int point = (int) Math.round((Math.sqrt((x * x) + (y * y))));
        if (point == radius) {
          charArray[row][column] = '.';
        }
      }
    }

  }

  public static void buildField(char[][] charArray) {
    center = fieldX / 2;
    for (int row = 0; row < fieldY; row++) {
      for (int column = 0; column < fieldX; column++) {
        charArray[row][column] = ' ';
        if (column == center) {
          charArray[row][column] = '│';
        }
        if (row == center) {
          charArray[row][column] = '─';
        }
        if (column == center && row == center) {
          charArray[row][column] = '┿';
        }
      }
    }
    charArray[0][center] = '△';
    charArray[0][center + 1] = 'Y';
    charArray[center][fieldX - 1] = '▷';
    charArray[center - 1][fieldX - 1] = 'X';
    charArray[center - 1][center + 1] = '0';
  }

  public static void showField(char[][] charArray) {
    for (int i = 0; i < fieldY; i++) {
      for (int j = 0; j < fieldX; j++) {
        if (i == center) {
          System.out.print("──" + charArray[i][j]);
        } else {
          System.out.print("  " + charArray[i][j]);
        }
      }
      System.out.println();
    }
  }
}