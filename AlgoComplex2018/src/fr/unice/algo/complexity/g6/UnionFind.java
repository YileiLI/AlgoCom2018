package fr.unice.algo.complexity.g6;

public class UnionFind {
    int[] id;

    public UnionFind(int n){
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i+1;
        }

    }


    //union()方法只需要将一个根节点连接到另一个根节点即可
    public void qu_union(int p,int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot) return;
        id[pRoot-1] = qRoot;
    }

    public int find(int p) {
        while(id[p-1] !=p) p=id[p-1];
        return p;
    }
    //connected()方法简单比较find(p)和find(q)
    public boolean connected(int p, int q){ return find(p) == find(q); }
}
