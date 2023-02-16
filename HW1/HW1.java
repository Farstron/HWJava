package HW1;
import java.util.Scanner;
// Написать программу вычисления n-ого треугольного числа.
public class HW1 {

    public static void main(String[] args) {
        
    }
    public static int n_triangle(int n) {
        int res = (n*(n+1))/2;
        return res;
    }
    public static int Read (String way){
        int n =0;
        if (way == ""){
            Scanner scan = new Scanner(System.in);
            n = scan.nextInt();
            scan.close();
        }
        return n;
    }
}