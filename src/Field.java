import java.util.concurrent.TimeUnit;

public class Field {
  public static final int MIN_X = -15;
  public static final int MAX_X = 15;
  public static final int MIN_Y = -15;
  public static final int MAX_Y = 15;
  public static int fieldX;
  public static int fieldY;
  public static int center;
  public char[][] charArray;
  private int x;
  private int y;

  public Field() {
    fieldX = MAX_X - MIN_X;
    fieldY = MAX_Y - MIN_Y;
    center = fieldX / 2;
    charArray = new char[Field.fieldY][Field.fieldX];
    ;
  }

  public void showField() {
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

  public void buildField() throws InterruptedException {
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
      System.out.print("░");
      TimeUnit.MILLISECONDS.sleep(50);
    }
    charArray[0][center] = '△';
    charArray[0][center + 1] = 'Y';
    charArray[center][fieldX - 1] = '▷';
    charArray[center - 1][fieldX - 1] = 'X';
    charArray[center - 1][center + 1] = '0';
  }

  public void placePoint(Point point, int radius) {
    int x = center + point.getX();
    int y = center - point.getY();
    charArray[y][x] = '●';
  }

  public String checkPointCircle(int radius, Point point) {
    String result = "";
    int testPoint = (int) Math.round((Math.sqrt((x * x) + (y * y))));
    if (testPoint < radius) {
      result = "и попадает внутрь окружности с радиусом " + radius;
    } else if (testPoint > radius) {
      result = "и находится вне окружности с радиусом " + radius;
    } else {
      result = "и находится на границе окружности с радиусом " + radius;
    }
    return result;
  }

  public String checkPointQuarter(Point point) {
    String result = "";
    x = point.getX();
    y = point.getY();
    if (x == center && y == center) {
      result = String.format("Точка с координатами (%d, %d) находится в центре координат", x, y);
    } else if (x == center) {
      result = "попали в Y";
    } else if (y == center) {
      result = "попали в X";
    }
    if (x > center && y < center) {
      result = String.format("Точка с координатами (%d, %d) находится в I координатной четверти",
          x, y);
    }
    if (x < center && y < center) {
      result = String.format("Точка с координатами (%d, %d) находится во II координатной четверти",
          x, y);
    }
    if (x < center && y > center) {
      result = String.format("Точка с координатами (%d, %d) находится в III координатной четверти",
          x, y);
    }
    if (x > center && y > center) {
      result = String.format("Точка с координатами (%d, %d) находится в IV координатной четверти",
          x, y);
    }
    return result;
  }

  public void buildCircle(int radius) {
    for (int row = 0; row < fieldY; row++) {
      for (int column = 0; column < fieldX; column++) {
        int x = column + MIN_X;
        int y = row + MIN_Y;
        int point = (int) Math.round((Math.sqrt((x * x) + (y * y))));
        if (point == radius) {
          charArray[row][column] = '.';
        }
      }
      System.out.print("░");
    }
  }


}
