public class Main {
    public static void main(String[] args) {
        int [] arr = {1,5,33,12,88,9,192,123,567,88,44,32};
        for (int i = 0; i < arr.length; i++)
        {
            System.out.println(arr[i]);
        }
        for (int i : arr){
            System.out.println(i);
        }
        int i = 0;
        while (i < arr.length)
        {
            System.out.println(arr[i]);
            i++;
        }
        i -= 12;
        do {
            System.out.println(arr[i]);
            i++;
        } while (i < arr.length);
        }
    }


