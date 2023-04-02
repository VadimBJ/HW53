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

  public static Point parse(String coordinates) {
    String[] coordXY = coordinates.split(",");
    if (coordXY.length == 0 || coordXY.length > 2 || coordXY[0].trim().isEmpty() || coordXY[1].trim().isEmpty()) {
      throw new IllegalArgumentException(
          "Неправильный формат координат, введите два целых числа через запятую!");
    }
    int x = Integer.parseInt(coordXY[0].trim());
    int y = Integer.parseInt(coordXY[1].trim());
    if (x < Field.MIN_X + 1 || x > Field.MAX_X - 1) {
      throw new IllegalArgumentException(
          "Неверное значение Х: " + x);
    }
    if (y < Field.MIN_Y + 1 || y > Field.MAX_Y - 1) {
      throw new IllegalArgumentException(
          "Неверное значение Y: " + y);
    }
    return new Point(x, y);
  }
}