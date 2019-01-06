package fr.unice.algo.complexity.g6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Vertex starts from 1
public class Sample {
    private int n;
    private int p;
    private int t;
    public List<int[]> c = new ArrayList();

    public Sample(int n, int p, int t){
        if (n > p){
            this.n = n;
            this.p = p;
            this.t = t;
        }else{
            System.out.println("Try Again");
        }
        this.generate();
    }

    public void generate(){
        int[] temp;
        int i = 0;
        while (i < t){
            temp = new int[p];
            for(int j = 0; j < p; j++){
                while (true) {
                    int k = 1 + (int) (Math.random() * (n));
                    Arrays.sort(temp);
                    int index = Arrays.binarySearch(temp, k);
                    if (index < 0) {
                        temp[0] = k;
                        break;
                    }
                }

            }
            Arrays.sort(temp);
            if (!c.contains(temp)){
                c.add(temp);
                i++;
            }

        }
    }

    public void print(){
        for (int i = 0; i < t; i++) {
            for(int j = 0; j < p; j++){
                System.out.print(c.get(i)[j] + ", ");

            }
            System.out.print("\n");

        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Entrer n p t：");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        String[] numbers = input.split(" ");
        int n = Integer.parseInt(numbers[0]);
        int p = Integer.parseInt(numbers[1]);
        int t = Integer.parseInt(numbers[2]);
        reader.close();
        System.out.println("OK");
        Sample ex = new Sample(n, p, t);

        ex.generate();

        ex.print();
    }
}
