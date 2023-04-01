import java.util.concurrent.TimeUnit;

public class Field {
  public static final int MIN_X = -16;
  public static final int MAX_X = 16;
  public static final int MIN_Y = -16;
  public static final int MAX_Y = 16;
  public static int fieldX;
  public static int fieldY;
  public static int center;
  public char[][] charArray;
  private final int x;
  private final int y;

  public Field(Point point) {
    fieldX = MAX_X - MIN_X;
    fieldY = MAX_Y - MIN_Y;
    center = fieldX / 2;
    charArray = new char[Field.fieldY][Field.fieldX];
    x = center + point.getX();
    y = center - point.getY();
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
      TimeUnit.MILLISECONDS.sleep(20);
    }
    charArray[0][center] = '△';
    charArray[0][center + 1] = 'Y';
    charArray[center][fieldX - 1] = '▷';
    charArray[center - 1][fieldX - 1] = 'X';
    charArray[center - 1][center + 1] = '0';
  }

  public void placePoint() {
        charArray[y][x] = '●';
  }

  public String checkPointCircle(Point point, int radius) {
    String result;
    int x1 = point.getX();
    int y1 = point.getY();
    int testPoint = (int) (Math.sqrt((x1 * x1) + (y1 * y1)));
    if ((x1 == 0 && y1 == 0) || testPoint < radius) {
      result = "и попадает внутрь окружности с радиусом " + radius;
    } else if (testPoint > radius) {
      result = "и находится вне окружности с радиусом " + radius;
    } else {
      result = "и находится на границе окружности с радиусом " + radius;
    }
    return result;
  }

  public String checkPointQuarter(Point point) {
    if (x == center && y == center) {
      return String.format("Точка с координатами (%d, %d) находится в центре координат",
          point.getX(), point.getY());
    } else if (x == center) {
      return String.format("Точка с координатами (%d, %d) находится на оси Y",
          point.getX(), point.getY());
    } else if (y == center) {
      return String.format("Точка с координатами (%d, %d) находится на оси X",
          point.getX(), point.getY());
    }
    if (x > center && y < center) {
      return String.format("Точка с координатами (%d, %d) находится в I координатной четверти",
          point.getX(), point.getY());
    }
    if (x < center && y < center) {
      return String.format("Точка с координатами (%d, %d) находится во II координатной четверти",
          point.getX(), point.getY());
    }
    if (x < center) {
      return String.format("Точка с координатами (%d, %d) находится в III координатной четверти",
          point.getX(), point.getY());
    }
    return String.format("Точка с координатами (%d, %d) находится в IV координатной четверти",
        point.getX(), point.getY());
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
