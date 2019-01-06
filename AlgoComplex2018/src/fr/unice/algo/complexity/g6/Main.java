package fr.unice.algo.complexity.g6;

import java.util.Iterator;

public class Main {


    public static void main(String[] args) {
        Prim prim;
        Kruskal kruskal;
        Sample sample;
        int n = 100;
        int p[] = new int[]{2,3,4,5,6,7,8,9,10};//!!!!!+2,3,....
        int t[] = new int[]{10,20,30,40,50,60,70,80,90,100};
        for (int i = 0; i < p.length; i++) {
            int pi = p[i];
            for (int j = 0; j < t.length; j++){
                double pr = 0;
                double kr = 0;
                for (int k = 0; k < 1000; k++) {
                    int ti = t[j];
                    prim = new Prim();
                    kruskal = new Kruskal();
//                System.out.println(pi);
//                System.out.println(ti);
                    sample = new Sample(n, pi, ti);
//                sample.print();
//                    System.out.println(sample.c.size());
                    prim.first(n, sample.c);
                    prim.primALL();
                    prim.verifyConnectivity();
//                for (int k = 0; k < prim.e.size(); k++) {
//                    System.out.print(prim.e.get(k).toString());
//                }
//                    System.out.print("prim: ");
//                    System.out.print(prim.e.size());
//                    System.out.print("\n");
                    pr += prim.e.size();

                    kruskal.first(n, sample.c);
                    kruskal.verifyConnectivity();
                    kr += kruskal.e.size();
//                Iterator<Edge> iterator = kruskal.e.iterator();
//                while (iterator.hasNext()){
//                    System.out.print(iterator.next().toString());
//                }
//                    System.out.print("Kruskal: ");
//                    System.out.print(kruskal.e.size());
//                    System.out.print("\n");
                }

                System.out.println(pr/1000);
                System.out.println(kr/1000);

            }
        }
//        sample = new Sample(n, 2, 20);
//        sample.print();
//        prim.first(n, sample.c);
//        prim.primALL();
//        prim.verifyConnectivity();
//        System.out.print("\n");
//        System.out.println(prim.e.size());
//        for (int i = 0; i < prim.e.size(); i++) {
//            System.out.println(prim.e.get(i).toString());
//        }
    }
}
