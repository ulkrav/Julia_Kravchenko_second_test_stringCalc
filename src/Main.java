import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("Input:\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));//Создаем считыватель с консоли
        String inputString = reader.readLine();  //В InputString читаем данные с консоли
        String[] subStrings = prepare (inputString) ; // Разбиваем строку на несколько частей по знакам операций
        var string1 = subStrings[0];//Поместили первую часть строки
        var string2 = subStrings[2]; //Поместили вторую часть строки
        var operation = subStrings[1];//Извлекли символ операции
        var result = calc(string1, operation, string2);
        System.out.println("Output: \n" +  result);
    }
    public static String calc (String string1, String operation, String string2) throws Exception {// Создаем метод калькулятор
        String result;
//В зависимости от операции выполняем разные действия
        switch (operation) {
            case "+":
               result = string1 + string2;
               break;
            case "-":
               result = string1.replace(string2, "");
               break;
            case "*":
               result = string1.repeat(Integer.parseInt(string2));//преобразование строки в целове число и повторение
                break;
            case "/":
              result = string1.substring(0, (Integer.parseInt(string2))); //преобразование строки в целове число и повторение
                //и извлечение с первого знака по знак равный второй части строки
                break;
            default:
                throw new Exception("Недопустимая операция");
        }
        return "\"" + result + "\"";
    }
        public static String[] prepare (String inputString) throws Exception { // создаем метод подготовки массива строк
            inputString = inputString.trim(); // удаляем лишние пробелы
            if (!inputString.startsWith("\"")){ // проверяем на наличие кавычек
                throw new Exception("Отсутствует начальная кавычка");
            }
            inputString = inputString.substring(1);
            int position = inputString.indexOf("\""); // находим появление кавычек
            if (position < 0){
                throw new Exception("Отсутствует закрывающая кавычка");
            }
            String operand1 = inputString.substring(0, position);// находим первый операнд
            inputString = inputString.substring(position + 1).trim();// удаляем первоый операнд и кавычку и удаляем пробелы
            if (!inputString.startsWith("+")// если оставшаяся после отрезания строкане начинается на  ариифмет знаки, то  выдаем инф об ошибке
                    &&!inputString.startsWith("-")
                    &&!inputString.startsWith("*")
                    &&!inputString.startsWith("/")){
                throw new Exception("Некорректная операция");
            }
            String operation = inputString.substring(0, 1);// выделяем знак операции
            inputString = inputString.substring(1).trim();
            String operand2; // переменная, где потом окажется  второй операнд
            if (operation.equals("+") || operation.equals("-")){ // если арифметич знак + или -
                if (!inputString.startsWith("\"")){ // ищем кавычку перед вторым операндом
                    throw new Exception("Отсутствует начальная кавычка второго операнда");
                }
                inputString = inputString.substring(1); // отрезали первую кавычку перед втторым операндом
                position = inputString.indexOf("\""); // ищем кавычку
                if (position < 0){
                    throw new Exception("Отсутствует закрывающая кавычка второго операнда");
                }
                operand2 = inputString.substring(0, position); // выделяем операнд 2
                inputString = inputString.substring(position + 1).trim();// ищем наличие чего-то после закрывающей кавычки второго операнда
                }
            else {
                try{
               inputString =  inputString.trim();
                int number = Integer.parseInt(inputString); // если  арифмет действие не + и не -, то преобразуем второй операндв преобразуем в целое число
               operand2 = inputString;
               inputString = "";// обозначаем, что после второго операнда ничего нет
               }catch ( Exception ex)  {
                    throw new Exception("Второй операнд введен некорректно");
                }
            }
            if (!inputString.isEmpty()){
                throw new Exception("Выражение содержит больше двух операндов");
            }
           return new String[] {operand1, operation, operand2};// возвращаем массив строк из первого операнда, арифм операции, второго операнда
        }
    }


