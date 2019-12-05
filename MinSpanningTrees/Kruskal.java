import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){
    		DisjointSets mySet = new DisjointSets(g.getNbNodes());
    		ArrayList<Edge> sorted=g.listOfEdgesSorted();
    		WGraph MST = new WGraph();
    		for(int i=0;i<sorted.size()-1;i++) {// we want to go through the edges starting with minimum weight
    			Edge current=sorted.get(i);
    			if (IsSafe(mySet,current)) {
    				MST.addEdge(current);
    				mySet.union(current.nodes[0],current.nodes[1]);//the edges is added to the MST, now add the nodes to the same set
    			}
    		}
        /* Fill this method (The statement return null is here only to compile) */
        
        return MST;
    }

    public static Boolean IsSafe(DisjointSets p,Edge e){
    	int i=p.find(e.nodes[0]);//parent of node 1
    	int j=p.find(e.nodes[1]);//parent of node 2
    	if(i!=j) {// then they are not in the same set, the edge crosses the cut
    		return true;
    	}
    	else {
        /* Fill this method (The statement return 0 is here only to compile) */
   
    		return false;
    	}
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
