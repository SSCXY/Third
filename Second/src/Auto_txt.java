import java.io.*;
import java.util.Scanner;


public class Auto_txt {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String UserInput = input.nextLine();//存放用户输入的字符串
        String AfterSplitStr[] = new String[3];
        AfterSplitStr = UserInput.split(" ");//分割用户输入的字符串

        String [] strArr = new String [500];
        int count = 0;
        try {
            String pathname = "src/" + AfterSplitStr[0];
            File filename = new File(pathname); // 读取以上路径的txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename), "GBK"); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();
            while (line != null) {
                strArr[count] = line;
                count++;
                line = br.readLine(); // 一次读入一行数据
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i = 0; i <= count - 1; i++){
            if (strArr[i] != null){
                strArr[i] = strArr[i].replaceAll("\\s+"," ");

            }
        }
        Infomation[] provinces = new Infomation[count];
        for(int i = 0; i <= count - 1; i++){
            String [] str = new String[3];
            str = strArr[i].split(" ");
            provinces[i] = new Infomation(str[0],str[1],str[2]);
        }

           try {
               FileWriter filewriter = new FileWriter("src/" + AfterSplitStr[1]);
               for(int i = 0; i <= count - 1; i++){
                   if(AfterSplitStr.length == 2){
                       if(provinces[i].getNum().equals("0"))continue;
                       else if(i == 0)
                           filewriter.write(provinces[0].getProvince() + "\r\n"
                                   + provinces[i].getArea() + "\t" + provinces[i].getNum() + "\r\n");
                       else if(provinces[i].getProvince().equals( provinces[i - 1].getProvince())){
                           filewriter.write(provinces[i].getArea() + "\t"
                                   + provinces[i].getNum() + "\r\n");
                       }else {
                           filewriter.write( "\r\n" + provinces[i].getProvince() + "\r\n"
                                   + provinces[i].getArea() + "\t" + provinces[i].getNum() + "\r\n");
                       }
                   }else {
                       if(provinces[i].getNum().equals("0") ||(! provinces[i].getProvince().equals(AfterSplitStr[2])))continue;
                       else if(i == 0)
                           filewriter.write(provinces[0].getProvince() + "\r\n"
                                   + provinces[i].getArea() + "\t" + provinces[i].getNum() + "\r\n");
                       else if(provinces[i].getProvince().equals( provinces[i - 1].getProvince())){
                           filewriter.write(provinces[i].getArea() + "\t"
                                   + provinces[i].getNum() + "\r\n");
                       }else {
                           filewriter.write(provinces[i].getProvince() + "\r\n"
                                   + provinces[i].getArea() + "\t" + provinces[i].getNum() + "\r\n");
                       }
                   }
               }
               filewriter.flush();
               filewriter.close();
           }catch (IOException e){
               e.printStackTrace();
           }
       }



}