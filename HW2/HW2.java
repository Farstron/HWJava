package HW2;
// Реализовать функцию возведения числа а в степень b. a, b ∈ Z. Сводя количество выполняемых действий к минимуму. 
// Пример 1: а = 3, b = 2, ответ: 9 
// Пример 2: а = 2, b = -2, ответ: 0.25
// Пример 3: а = 3, b = 0, ответ: 1
// Пример 4: а = 0, b = 0, ответ: не определено
// Пример 5
// входные данные находятся в файле input.txt в виде
// b 3
// a 10
// Результат нужно сохранить в файле output.txt
// 1000
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HW2 {
    public static void main(String[] args) {
        String inputFileName = "D:/GeBr/HWJava/HW2/demo.txt";
        // String[] input = Reader(inputFileName);
        int[] num = Reader(inputFileName);
        long res = 1;
        res = POW(num,res);
        System.out.println(res);
    }

    public static int[] Reader (String Path){
        int[] out={0,0};
        String line = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(Path))) {
			while ((line = reader.readLine()) != null) {
                int temp = 0;
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(line);
                int start = 0;
                while (matcher.find(start)) {
                String value = line.substring(matcher.start(), matcher.end());
                int result = Integer.parseInt(value);
                temp=result;
                start = matcher.end();
                if (line.charAt(0) == 'a'){
                        out[0]=temp;
                     }
                     else{
                        out[1]=temp;
                     }
                }
			}
		}
                catch (IOException e) {
			e.printStackTrace();
		}
        return out;
    }

    public static long POW(int[] num, long res){
        if (num[1] != 0){
            res *= num[0];
            num[1] -= 1; 
            return POW(num,res);
        }return res;
        
    }
}
