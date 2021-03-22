import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        //Сортировка обменом
        Instant startB = Instant.now();
        System.out.println("Сортировка обменом");
        int[][] array = new int[1000][1000];
        rand(array);
        sortMatrixB(array);
        Instant finishB = Instant.now();
        long elapsed = Duration.between(startB, finishB).toMillis();
        System.out.println("Время сортировки, мс: " + elapsed);
        System.out.println("--------------------");

        //Быстрая сортировка
        Instant startQ = Instant.now();
        System.out.println("Быстрая сортировка");
        int[][] array2 = new int[1000][1000];
        rand(array2);
        sortMatrixQ(array2);
        Instant finishQ = Instant.now();
        long elapsed2 = Duration.between(startQ, finishQ).toMillis();
        System.out.println("Время сортировки, мс: " + elapsed2);
        System.out.println("-------------------");

        //Стандартная функция сортировки Java
        Instant start = Instant.now();
        System.out.println("Стандартная функция сортировки Java");
        int[][] array3 = new int[1000][1000];
        rand(array3);
        sortMatrix(array3);
        Instant finish = Instant.now();
        long elapsed3 = Duration.between(start, finish).toMillis();
        System.out.println("Время сортировки, мс: " + elapsed3);
    }

    public static int[][] sortMatrixB(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            Sort.bubbleSort(matrix[i]);
        }
        return matrix;
    }

    public static int[][] sortMatrixQ(int[][] matrix) {
        int low = 0;
        int high = matrix[0].length - 1;
        for (int i = 0; i < matrix.length; i++) {
            quickSort(matrix[i], low, high);
        }
        return matrix;
    }

    public static int[][] sortMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            Arrays.sort(matrix[i]);
        }
        return matrix;
    }

    //Сортировка обменом
    private static void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    public static int[] bubbleSort(int[] arr) {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < arr[i - 1]) {
                    swap(arr, i, i-1);
                    needIteration = true;
                }
            }
        }
        return arr;
    }

    //Бстрая сортировка
    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;//завершить выполнение если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    //заполнение матрицы рандомными значениями
    public static void rand (int[][] matrix) {
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                matrix[i][j] = ((int) (Math.random()*30)-15);
            }
        }
    }
}

