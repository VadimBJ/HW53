import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

  @DisplayName("x=0 y=0 R=10")
  @Test
  public void zeroXzeroY() throws InterruptedException {
    String coordXY = "0, 0";
    int radius = 10;

    Point point = Point.parse(coordXY);
    Field field = new Field(point);
    field.buildField();
    field.buildCircle(radius);
    field.placePoint();
    String expected1 = String.format("Точка с координатами (%d, %d) находится в центре координат",
        point.getX(), point.getY());
    String expected2 = "и попадает внутрь окружности с радиусом " + radius;
    assertEquals(expected1, field.checkPointQuarter(point));
    assertEquals(expected2, field.checkPointCircle(point, radius));
  }

  @DisplayName("x=0 y=10 R=10")
  @Test
  public void zeroXNotZeroY() throws InterruptedException {
    String coordXY = "0, 10";
    int radius = 10;

    Point point = Point.parse(coordXY);
    Field field = new Field(point);
    field.buildField();
    field.buildCircle(radius);
    field.placePoint();
    String expected1 = String.format("Точка с координатами (%d, %d) находится на оси Y",
        point.getX(), point.getY());
    String expected2 = "и находится на границе окружности с радиусом " + radius;
    assertEquals(expected1, field.checkPointQuarter(point));
    assertEquals(expected2, field.checkPointCircle(point, radius));
  }

  @DisplayName("x=10 y=0 R=10")
  @Test
  public void notZeroXZeroY() throws InterruptedException {
    String coordXY = "10, 0";
    int radius = 10;

    Point point = Point.parse(coordXY);
    Field field = new Field(point);
    field.buildField();
    field.buildCircle(radius);
    field.placePoint();
    String expected1 = String.format("Точка с координатами (%d, %d) находится на оси X",
        point.getX(), point.getY());
    String expected2 = "и находится на границе окружности с радиусом " + radius;
    assertEquals(expected1, field.checkPointQuarter(point));
    assertEquals(expected2, field.checkPointCircle(point, radius));
  }

  @DisplayName("x=-10 y=10 R=10")
  @Test
  public void negativeXNotZeroY() throws InterruptedException {
    String coordXY = "-10, 10";
    int radius = 10;

    Point point = Point.parse(coordXY);
    Field field = new Field(point);
    field.buildField();
    field.buildCircle(radius);
    field.placePoint();
    String expected1 = String.format("Точка с координатами (%d, %d) находится во II координатной четверти",
        point.getX(), point.getY());
    String expected2 = "и находится вне окружности с радиусом " + radius;
    assertEquals(expected1, field.checkPointQuarter(point));
    assertEquals(expected2, field.checkPointCircle(point, radius));
  }

  @DisplayName("x=10 y=-10 R=10")
  @Test
  public void notZeroXnegativeY() throws InterruptedException {
    String coordXY = "10, -10";
    int radius = 10;

    Point point = Point.parse(coordXY);
    Field field = new Field(point);
    field.buildField();
    field.buildCircle(radius);
    field.placePoint();
    String expected1 = String.format("Точка с координатами (%d, %d) находится в IV координатной четверти",
        point.getX(), point.getY());
    String expected2 = "и находится вне окружности с радиусом " + radius;
    assertEquals(expected1, field.checkPointQuarter(point));
    assertEquals(expected2, field.checkPointCircle(point, radius));
  }

  @DisplayName("x=-10 y=-10 R=10")
  @Test
  public void negativeXnegativeY() throws InterruptedException {
    String coordXY = "-10, -10";
    int radius = 10;

    Point point = Point.parse(coordXY);
    Field field = new Field(point);
    field.buildField();
    field.buildCircle(radius);
    field.placePoint();
    String expected1 = String.format("Точка с координатами (%d, %d) находится в III координатной четверти",
        point.getX(), point.getY());
    String expected2 = "и находится вне окружности с радиусом " + radius;
    assertEquals(expected1, field.checkPointQuarter(point));
    assertEquals(expected2, field.checkPointCircle(point, radius));
  }

  @DisplayName("x=-2 y=-2 R=10")
  @Test
  public void negativeXnegativeYInCircle() throws InterruptedException {
    String coordXY = "-2, -2";
    int radius = 10;

    Point point = Point.parse(coordXY);
    Field field = new Field(point);
    field.buildField();
    field.buildCircle(radius);
    field.placePoint();
    String expected1 = String.format("Точка с координатами (%d, %d) находится в III координатной четверти",
        point.getX(), point.getY());
    String expected2 = "и попадает внутрь окружности с радиусом " + radius;
    assertEquals(expected1, field.checkPointQuarter(point));
    assertEquals(expected2, field.checkPointCircle(point, radius));
  }
}