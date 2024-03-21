package org.senechka.task2;

public class MergeSort {
    public static void merge(int[] arr, int l, int m, int r) {
        // Вычисляем размеры временных массивов
        int n1 = m - l + 1;
        int n2 = r - m;

        // Создаем временные массивы
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Копируем данные во временные массивы L[] и R[]
        System.arraycopy(arr, l, L, 0, n1);
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Индексы начала первого и второго подмассива
        int i = 0, j = 0;

        // Индекс начала сливаемого подмассива
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Копируем оставшиеся элементы из L[], если они есть
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Копируем оставшиеся элементы из R[], если они есть
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Основная функция, которая сортирует массив arr[l..r] используя сортировку слиянием
    public static int[] sort(int[] arr, int l, int r) {
        if (arr == null ||  arr.length == 0){
            throw new IllegalArgumentException();
        }
        if (l < r) {
            // Находим средний индекс массива
            int m = (l + r) / 2;

            // Сортируем первую и вторую половины массива
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Сливаем две отсортированные половины
            merge(arr, l, m, r);
        }
        return arr;
    }
}
