package fr.unice.algo.complexity.g6;

public class Edge implements Comparable<Edge>{
    int v1;
    int v2;
    int weight;

    public Edge(int v1, int v2, int weight){
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public boolean equals(Object object){
        if(object instanceof Edge){
            Edge e = (Edge) object;
            if(v1 == e.v1){
                if (v2 == e.v2)
                return true;
            }else if(v1 == e.v2){
                if (v2 == e.v1)
                    return true;
            }
        }
        return false;
    }

    public int other(int vertex) {
        if(vertex == v1){
            return v2;
        }
        else if(vertex == v2) {
            return v1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return v1+"-"+v2+"\n";
    }

    @Override
    public int compareTo(Edge e) {
        return this.weight-e.weight;
    }
}
