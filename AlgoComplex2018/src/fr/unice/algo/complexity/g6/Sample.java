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
            boolean flag = false;
            for (int j = 0; j < c.size(); j++) {
                if (Arrays.equals(c.get(j), temp)){
                    flag = true;
                    break;
                }
            }
            if (!flag){
                i++;
                c.add(temp);
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


        int n = Integer.parseInt(args[0]);
        int p = Integer.parseInt(args[1]);
        int t = Integer.parseInt(args[2]);
        Sample ex = new Sample(n, p, t);

        ex.generate();

        ex.print();
    }
}
