public class Main {
    public static void main(String[] args) {
        int[] arr = {1,5,33,12,88,9,192,123,567,88,44,32};
        for (int i = 0; i < arr.length; i++){
            if (arr[i] % 2 == 0){
                System.out.println(arr[i]);
            }
            else if (arr[i] % 2 != 0){
                System.out.println(arr[i]*10);

            }
        }

    }
}
