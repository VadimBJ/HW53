import java.io.BufferedReader;
import java.io.IOException;

public class Point {

  private final int x;
  private final int y;

  private Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public static Point parse(BufferedReader br) throws IOException {
    String[] coord = br.readLine().split(",");
    if (coord.length == 0 || coord.length > 2 || coord[0].trim().isEmpty() || coord[1].trim().isEmpty()) {
      throw new IllegalArgumentException(
          "Неправильный формат координат, введите два целых числа через запятую!");
    }
    int x = Integer.parseInt(coord[0].trim());
    int y = Integer.parseInt(coord[1].trim());
    if (x < Field.MIN_X + 1 || x > Field.MAX_X - 1) {
      throw new IllegalArgumentException(
          "Неверное значение Х: "+x);
    }    if (y < Field.MIN_Y + 1 || y > Field.MAX_Y - 1) {
      throw new IllegalArgumentException(
          "Неверное значение Y: "+y);
    }
      return new Point(x, y);

  }

}