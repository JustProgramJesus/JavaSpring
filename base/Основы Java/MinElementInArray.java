public class MinElementInArray{
    public static void main(String[] args){
        int[][] arr = {
                {20, 34, 55},
                {624, 43, 18},
                {76, 34, 235}
        };
        int minElement = arr[0][0];

        for (int i = 0; i < arr.length; i++){
            for (int k = 0; k < arr[i].length; k++){
                if (arr[i][k] < minElement){
                    minElement = arr[i][k];
                }
            }
        }
        System.out.println("Самое маленькое число в массиве - [ "  + minElement + " ]");
    }
}
