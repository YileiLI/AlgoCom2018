package fr.unice.algo.complexity.g6;

import java.util.*;

public class Prim {
    static int MAX = Integer.MAX_VALUE;
    List<Set> v = new ArrayList<>();
    List<int[][]> graph = new ArrayList<>();
    List<Edge> e = new ArrayList<>(); //E
    List<Integer> list = new ArrayList<>();//用来存储加入结点的顺序
    List<int[]> c;
    int[][] map;
    int minimum = 1;
    int n;

    public static void main(String[] args) {
        Prim p = new Prim();
        List<int[]> c = new ArrayList<>();
//        c.add(new int[]{1,2,3});
//        c.add(new int[]{2,4,5});
//        c.add(new int[]{3,4,5});
//        c.add(new int[]{4,5,6,7});
//        p.first(7, c);
        c.add(new int[]{1,2,3});
        c.add(new int[]{1,3});
        c.add(new int[]{4,5});
        c.add(new int[]{5,6});
        c.add(new int[]{7});
        p.first(7, c);
        p.primALL();
        p.verifyConnectivity();
        System.out.println(p.e.size());
        for (int i = 0; i < p.e.size(); i++) {
            System.out.println(p.e.get(i).toString());
        }
    }

    public void first(int n, List<int[]> c){
        this.n = n;
        this.c = c;
        map = new int[n][n];
        int temp = -1;
        Set<Integer> v1 = new HashSet<>();
        for (int i = 0; i < c.size(); i++) {
            for (int j = 0; j < c.get(i).length - 1; j++){
                for (int k = j+1; k < c.get(i).length; k++) {
                    map[c.get(i)[j]-1][c.get(i)[k]-1] -= 1;
                    map[c.get(i)[k]-1][c.get(i)[j]-1] -= 1;
                    if (temp > map[c.get(i)[j]-1][c.get(i)[k]-1]){
                        temp = map[c.get(i)[j]-1][c.get(i)[k]-1];
                        minimum = c.get(i)[j];
                    }
                }
            }
        }
    }

    public void prim(int start) {
        int n = map.length;
        int[] lowcost = new int[n];  //到新集合的最小权
        int[] mid = new int[n];//存取前驱结点
        int i, j, min, minid = 0;
        //初始化辅助数组
        for (i = 0; i < n; i++) {
            if (i == start - 1) {
                lowcost[i] = 100;
            } else {
                lowcost[i] = map[start - 1][i];
            }
            mid[i] = start;
        }
        list.add(start);
        //一共需要加入n-1个点
        for (i = 0; i < n; i++) {
            min = 0;
            minid = start;
            //每次找到距离集合最近的点
            for (j = 0; j < n; j++) {
                if (lowcost[j] != 100 && lowcost[j] < min) {
                    min = lowcost[j];
                    minid = j + 1;
                }
            }

            if (minid == start) return;
            list.add(minid);
            lowcost[minid - 1] = 100;

            Edge edge = new Edge(mid[minid - 1], minid, min);
            e.add(edge);
//            System.out.print(edge.toString());
//            System.out.print(" Weight：");
//            System.out.print(min);
//            System.out.print("\n");

            //加入该点后，更新其它点到集合的距离
            for (j = 0; j < n; j++) {
                if (lowcost[j] != 100 && lowcost[j] > map[minid - 1][j]) {
                    lowcost[j] = map[minid - 1][j];
                    mid[j] = minid;
                }
            }
        }
    }

    public void primALL(){
        prim(minimum);
        while (list.size() < n){
            for (int i = 0; i < n; i++) {
                if (!list.contains(i+1)) {

                    minimum = i + 1;
                    prim(minimum);
                    break;
                }
            }
        }
    }

    public void verifyConnectivity(){

        for (int i = 0; i < c.size(); i++) {
            List<Edge> subE = new ArrayList<>();
            int[] ci = c.get(i);
            int[][] matrix = new int[n][n];
            for (int k = 0; k < e.size(); k++) {
                Edge edge = e.get(k);
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

