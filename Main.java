import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Матрицы комплексных чисел ===");

        while (true) {
            System.out.println("\nВведите матрицу A:");
            Matrix a = readMatrix(sc, "A");

            System.out.println("Введите матрицу B:");
            Matrix b = readMatrix(sc, "B");

            System.out.println("\nМатрица A:");
            System.out.print(a);
            System.out.println("Матрица B:");
            System.out.print(b);

            System.out.println("Выберите операцию:");
            System.out.println("1 - A + B");
            System.out.println("2 - A - B");
            System.out.println("3 - A * B");
            System.out.println("4 - A / B");
            System.out.println("5 - транспонировать A");
            System.out.println("6 - определитель A");
            System.out.println("7 - определитель B");
            System.out.println("8 - обратная к A");
            System.out.println("0 - выход");
            System.out.print("Ваш выбор: ");

            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("Выход");
                return;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.println("A + B =");
                        System.out.println(a.add(b));
                        break;
                    case 2:
                        System.out.println("A - B =");
                        System.out.println(a.sub(b));
                        break;
                    case 3:
                        System.out.println("A * B =");
                        System.out.println(a.mul(b));
                        break;
                    case 4:
                        System.out.println("A / B =");
                        System.out.println(a.div(b));
                        break;
                    case 5:
                        System.out.println("A транспонированная =");
                        System.out.println(a.transpose());
                        break;
                    case 6:
                        System.out.println("det(A) = " + a.determinant());
                        break;
                    case 7:
                        System.out.println("det(B) = " + b.determinant());
                        break;
                    case 8:
                        System.out.println("A в степени -1 =");
                        System.out.println(a.inverse());
                        break;
                    default:
                        System.out.println("Нет такой операции");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    public static Matrix readMatrix(Scanner sc, String name) {
        System.out.print("Сколько строк у " + name + ": ");
        int rows = sc.nextInt();
        System.out.print("Сколько столбцов у " + name + ": ");
        int cols = sc.nextInt();

        Matrix m = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Элемент " + name + "[" + i + "][" + j + "]: ");
                System.out.print("действительная часть = ");
                double re = sc.nextDouble();
                System.out.print("мнимая часть = ");
                double im = sc.nextDouble();
                m.set(i, j, new Complex(re, im));
            }
        }

        return m;
    }
}