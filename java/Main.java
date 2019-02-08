import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] weights;

    public static void main(String[] args) {

        setWeights();

        System.out.println("Weights: ");
        for (int weight : weights)
            System.out.print(weight + " ");

        System.out.println();
        System.out.println("Maximum balanced weight is: " + maxBalancedWeight());
    }

    static void setWeights() {

        Scanner in = new Scanner(System.in);
        int n;

        while (true) {
            System.out.print("Enter number of weights: ");
            n = in.nextInt();
            if (n < 0) System.out.println("ERROR. You must have at least one weight.");
            else {
                if (n > 1000) System.out.println("ERROR. You can't have more than 1000 weights.");
                else break;
            }
        }

        weights = new int[n];

        System.out.print("Enter the weights: ");
        for (int i = 0; i < weights.length; i++) {
            while (true) {
                n = in.nextInt();
                if (n < 0) System.out.println("ERROR. Weight can't be negative");
                else{
                    if (n > 20) System.out.println("ERROR. You can't have a >20lbs weight.");
                    else break;
                }
            }
            weights[i] = n;
        }
    }

    static int maxBalancedWeight(){

        if (weights.length == 1)
            return 0;

        if (weights.length == 2) {
            if (weights[0] == weights[1])
                return 2 * weights[0];
            return 0;
        }

        Arrays.sort(weights);

        if (weights.length == 3) {
            if (weights[0] == weights[1])
                return 2 * weights[0];
            if (weights[1] == weights[2])
                return 2 * weights[1];
            return 0;
        }

        int currentSum = 0, max = maxPossibleWeight();

        for (int i = weights.length - 1; i >= 0; i--) {

            if (currentSum + weights[i] == max)
                return 2 * max;
            if (currentSum + weights[i] < max)
                currentSum += weights[i];
        }
        return 0;
    }

    static int maxPossibleWeight(){
        int max = 0;
        for (int weight : weights) {
            max += weight;
        }

        max /= 2;

        for (int weight : weights) {
            if (weight > max)
                max -= weight/2;
        }
        return max;
    }
}