import java.util.Scanner;
import java.util.Arrays;
import java.util.*;

public class Reto76{
    
    public static void level(double num){
        if(num >= 0 && num <= 5){System.out.println("Continuar el control y la vigilancia"); return;}
        if(num > 5 && num <= 14){System.out.println("BAJO"); return;}
        if(num > 14 && num <= 35){System.out.println("MEDIO"); return;}
        if(num > 35 && num <= 80){System.out.println("ALTO"); return;}
        if(num > 80 && num <= 100){System.out.println("INVIABLE SANITARIAMENTE"); return;}
        }
    
    public static void main(String args[]) {
        
        double data[] = new double[6];
        double sum = 0;
        double mult = 1;
        double prom;
        double min = 101;
        double max = -1;
        
        Scanner sc = new Scanner(System.in);
        
        for(int i = 0; i<= 5; i++){
            data[i] = sc.nextDouble();
            sum += data[i];
            mult *= data[i];
            if(data[i]<min && data[i]>=0 && data[i]<=5){
                min = data[i];
            }
            if(data[i]>max && data[i]<=5 && data[i]>=0){
                max = data[i];
            }
        }
        
        prom = sum/6;
        
        Arrays.sort(data);
        
        level(prom);
        System.out.println(String.format("%.2f", mult));
        if(mult >= 50000000){
            System.out.println("SI");
        }else{System.out.println("NO");}
        
    }
}