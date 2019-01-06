package fr.unice.algo.complexity.g6;

import java.util.*;

public class Kruskal {
    PriorityQueue<Edge> edges = new PriorityQueue<>();
    Queue<Edge> e = new LinkedList<>(); //E
    List<int[]> c;
    int[][] map;
    int n;

    public static void main(String[] args) {
        Kruskal k = new Kruskal();
//        Sample sample = new Sample(100, 10, 10);
//        sample.print();
        List<int[]> c = new ArrayList<>();
//        c.add(new int[]{1,2,3});
//        c.add(new int[]{2,4,5});
//        c.add(new int[]{3,4,5});
//        c.add(new int[]{4,5,6,7});
//        k.first(7, c);
//        c.add(new int[]{1,2,3});
//        c.add(new int[]{1,3,4});
//        c.add(new int[]{4,5});
//        c.add(new int[]{5,6});
//        c.add(new int[]{7});
        c.add(new int[]{2, 47, 49, 74, 81, 83, 86, 89, 95, 97});
        c.add(new int[]{14, 35, 44, 49, 56, 61, 81, 86, 92, 93});
        c.add(new int[]{8, 21, 25, 27, 30, 32, 39, 50, 52, 87});
        c.add(new int[]{1, 4, 14, 28, 32, 34, 46, 60, 67, 86});
        c.add(new int[]{8, 21, 24, 28, 36, 40, 65, 67, 72, 86});
        c.add(new int[]{11, 20, 24, 27, 28, 52, 67, 85, 89, 92});
        c.add(new int[]{18, 20, 21, 22, 25, 45, 46, 55, 57, 71});
        c.add(new int[]{15, 25, 35, 42, 45, 50, 66, 67, 80, 99});
        c.add(new int[]{2, 18, 25, 54, 71, 77, 84, 96, 97, 100});
        c.add(new int[]{3, 17, 29, 42, 54, 65, 66, 74, 83, 94});
        k.first(100, c);
        k.kruskal();
        k.verifyConnectivity();
        System.out.println(k.e.size());
        Iterator<Edge> iterator = k.e.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next().toString());
        }
    }

    public void first(int n, List<int[]> c){
        this.n = n;
        this.c = c;
//        graph = new Graph(n);
        map = new int[n][n];
        for (int i = 0; i < c.size(); i++) {
            for (int j = 0; j < c.get(i).length - 1; j++){
                for (int k = j+1; k < c.get(i).length; k++) {
                    map[c.get(i)[j] - 1][c.get(i)[k] - 1] -= 1;
                    map[c.get(i)[k] - 1][c.get(i)[j] - 1] -= 1;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (map[i][j]!=0){
                    edges.add(new Edge(i+1, j+1, map[i][j]));
                }
            }

        }

    }

    public void kruskal() {
        UnionFind uf = new UnionFind(n);

        while (!edges.isEmpty() && e.size() < n-1){
            Edge edge = edges.poll();//从优先队列得到最小的边
            int v = edge.v1,w = edge.v2;//得到最小边的顶点
            if(uf.connected(v, w))	continue;//判断会不会构成环
            uf.qu_union(v,w);//合并分量
            e.add(edge);//将边添加进树中
        }

    }

    public void verifyConnectivity(){

        for (int i = 0; i < c.size(); i++) {
            List<Edge> subE = new ArrayList<>();
            int[] ci = c.get(i);
            int[][] matrix = new int[n][n];
            Iterator<Edge> iterator = e.iterator();
            while (iterator.hasNext()){
                Edge edge = iterator.next();
                if (Arrays.binarySearch(ci, edge.v1) >= 0 && Arrays.binarySearch(ci, edge.v2) >= 0){
                    subE.add(edge);
                    matrix[edge.v1-1][edge.v2-1] = 1;
                    matrix[edge.v2-1][edge.v1-1] = 1;
                }
            }
            int j = 1;
            while (subE.size() < ci.length -1 ){
                int sum = 0;
                for (int k = 0; ci[k] < ci[j]; k++) {
                    sum += matrix[ci[j]-1][ci[k]-1];
                    if (sum > 0){
                        break;
                    }
                }
                if (sum == 0){
                    subE.add(new Edge(ci[j-1], ci[j], 0));
                    e.add(new Edge(ci[j-1], ci[j], 0));
                }
                j++;
            }
        }
    }

}
