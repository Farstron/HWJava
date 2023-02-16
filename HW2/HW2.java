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

public class HW2 {
    public static void main(String[] args) {
        String inputFileName = "D:/GeBr/HWJava/HW2/demo.txt";
        String[] input = Reader(inputFileName);
    }

    public static String[] Reader (String Path){
        String[] lines={" "," "," "};
        int i =0;
        try (BufferedReader reader = new BufferedReader(new FileReader(Path))) {
			while ((lines[i] = reader.readLine()) != null) {
                i+=1;
			}
		}
                catch (IOException e) {
			e.printStackTrace();
		}
        return lines;
    }
}
