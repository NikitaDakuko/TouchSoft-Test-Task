import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static int[] weights;

    public static void main(String[] args) {

        setWeights();

        System.out.println("Weights: ");
        for (int weight : weights)
            System.out.print(weight + " ");

        System.out.println();
        Arrays.sort(weights);
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

        switch (weights.length){
            case 1:
                return 0;
            case 2:
                if (weights[0] == weights[1])
                    return 2 * weights[0];
                return 0;
            case 3:
                if (weights[0] == weights[1])
                    return 2 * weights[0];
                if (weights[1] == weights[2])
                    return 2 * weights[1];
                if (weights[0] + weights[1] == weights[2])
                    return 2 * weights[2];
        }

        int currentSum = 0, max = maxPossibleWeight(0);

        for (int i = weights.length - 1; i >= 0; i--) {
            if (currentSum + weights[i] == max)
                return 2 * max;
            if (currentSum + weights[i] < max)
                currentSum += weights[i];
        }
        return 0;
    }

    static int maxPossibleWeight(int i){
        int max = 0;

        if (i > weights.length - 1)
            return 0;

        for (int i1 = 0; i1 < weights.length - i; i1++) {
            max += weights[i1];
        }

        if (max % 2 > 0){
            for (int i1 = 0; i1 < weights.length - i; i1++) {
                if (weights[i1] %2 > 0){
                    max -= weights[i1];
                    break;
                }
            }
        }

        max /= 2;

        if (weights[weights.length - (i + 1)] > max)
            max = maxPossibleWeight(++i);

        return max;
    }
}
