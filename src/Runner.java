import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Runner {
  public static final String CYAN = "\u001B[36m";
  public static final String RESET = "\u001B[0m";
  public static final String GREEN = "\u001B[32m";


  public static void main(String[] args) throws IOException, InterruptedException, AWTException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int exit;
    do {
      messagePrint(CYAN + "Укажите координаты Вашей точки через запятую\n");
      messagePrint("Значение X и Y должны находиться в пределах от "
                   + (Field.MIN_X + 1) + " до " + (Field.MAX_X - 1));
      messagePrint(RESET + "\nВведите координаты [x,y]: ");
      String coordinates = br.readLine();
      Point point = Point.parse(coordinates);
      Field field = new Field(point);
      System.out.println();
      messagePrint(CYAN + "Укажите радиус окружности с центром в точке O (0, 0)\n");
      messagePrint("Значение должно находиться в пределах от 1 до " + (Field.center - 2));
      messagePrint(RESET + "\nВведите радиус R: ");
      int radius = readIntLimited(1, Field.center - 2);
      System.out.println();
      System.out.print(CYAN + "\nПроводим необходимые вычисления" + RESET + " [");
      field.buildField();
      field.buildCircle(radius);
      field.placePoint();
      System.out.println("]");
      System.out.println();
      System.out.println(GREEN + "... нажмите ENTER для просмотра результата ..." + RESET);
      br.readLine();
      clearScreen();
      TimeUnit.MILLISECONDS.sleep(200);
      System.out.println(CYAN + field.checkPointQuarter(point));
      System.out.println(field.checkPointCircle(point, radius) + RESET);
      field.showField();

      System.out.print("""
          Хотите повторить?
           1. Да!
           2. Нет
           ➥""");
      exit = readIntLimited(1, 2);
      clearScreen();
      TimeUnit.MILLISECONDS.sleep(500);
    } while (exit == 1);
  }

  public static int readIntLimited(int min, int max) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int num = 0;
    do {
      try {
        num = Integer.parseInt(br.readLine());
      } catch (NumberFormatException e) {
        System.out.println("Вводите только цифры!");
      }
      if (!(num >= min && num <= max)) {
        System.out.printf("Введите число от %d до %d: ", min, max);
      }
    } while (!(num >= min && num <= max));
    return num;
  }

  public static void clearScreen() throws AWTException {
    Robot robot = new Robot();
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_SHIFT);
    robot.keyPress(KeyEvent.VK_Q);
    robot.keyRelease(KeyEvent.VK_Q);
    robot.keyRelease(KeyEvent.VK_SHIFT);
    robot.keyRelease(KeyEvent.VK_ALT);
    robot.keyRelease(KeyEvent.VK_CONTROL);
  }

  public static void messagePrint(String massage) {
    for (int i = 0; i < massage.length(); i++) {
      System.out.print(massage.charAt(i));
    }
  }


}