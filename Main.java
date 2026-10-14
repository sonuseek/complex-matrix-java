import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Матрицы комплексных чисел ===");

        System.out.println("Введите матрицу A:");
        Matrix a = readMatrix(sc, "A");

        System.out.println("Введите матрицу B:");
        Matrix b = readMatrix(sc, "B");

        while (true) {
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
            System.out.println("9 - ввести A заново");
            System.out.println("10 - ввести B заново");
            System.out.println("0 - выход");
            System.out.print("Ваш выбор: ");

            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("Выход");
                return;
            }

            if (choice == 9) {
                a = readMatrix(sc, "A");
                continue;
            }

            if (choice == 10) {
                b = readMatrix(sc, "B");
                continue;
            }

            try {
                if (choice == 1) {
                    Matrix res = a.add(b);
                    System.out.println("A + B =");
                    System.out.println(res);
                    System.out.println("Сохранить? 1-никуда, 2-в A, 3-в B");
                    int s = sc.nextInt();
                    if (s == 2) a = res;
                    if (s == 3) b = res;
                } else if (choice == 2) {
                    Matrix res = a.sub(b);
                    System.out.println("A - B =");
                    System.out.println(res);
                    System.out.println("Сохранить? 1-никуда, 2-в A, 3-в B");
                    int s = sc.nextInt();
                    if (s == 2) a = res;
                    if (s == 3) b = res;
                } else if (choice == 3) {
                    Matrix res = a.mul(b);
                    System.out.println("A * B =");
                    System.out.println(res);
                    System.out.println("Сохранить? 1-никуда, 2-в A, 3-в B");
                    int s = sc.nextInt();
                    if (s == 2) a = res;
                    if (s == 3) b = res;
                } else if (choice == 4) {
                    Matrix res = a.div(b);
                    System.out.println("A / B =");
                    System.out.println(res);
                    System.out.println("Сохранить? 1-никуда, 2-в A, 3-в B");
                    int s = sc.nextInt();
                    if (s == 2) a = res;
                    if (s == 3) b = res;
                } else if (choice == 5) {
                    Matrix res = a.transpose();
                    System.out.println("A транспонированная =");
                    System.out.println(res);
                    System.out.println("Сохранить? 1-никуда, 2-в A, 3-в B");
                    int s = sc.nextInt();
                    if (s == 2) a = res;
                    if (s == 3) b = res;
                } else if (choice == 6) {
                    System.out.println("det(A) = " + a.determinant());
                } else if (choice == 7) {
                    System.out.println("det(B) = " + b.determinant());
                } else if (choice == 8) {
                    Matrix res = a.inverse();
                    System.out.println("A в степени -1 =");
                    System.out.println(res);
                    System.out.println("Сохранить? 1-никуда, 2-в A, 3-в B");
                    int s = sc.nextInt();
                    if (s == 2) a = res;
                    if (s == 3) b = res;
                } else {
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