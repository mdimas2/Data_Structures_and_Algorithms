import java.io.*;
import java.util.*;












public class FordFulkerson {

	
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> Stack = new ArrayList<Integer>();
			Stack.add(0,source);
			ArrayList<Edge> Edges=graph.getEdges();
			ArrayList<String> Visited =new ArrayList<String>();
			Integer current=source;
			for (int j=0;j<graph.getNbNodes();j++) {
				Visited.add(j,"false");
			}
			int z=1;
			Visited.set(0,"true");
			while(Stack.size()>0) {
				for (int i=0;i<Edges.size();i++) {
					if(Edges.get(i).weight>0) {
						if(Edges.get(i).nodes[0]==current && Visited.get(Edges.get(i).nodes[1])=="false") {
							Stack.add(z,Edges.get(i).nodes[1]);
							System.out.println("adding "+Edges.get(i).nodes[0]+" to "+Edges.get(i).nodes[1]);
							System.out.println("adding "+Edges.get(i).nodes[1]+" at "+z);	
							
							current=Edges.get(i).nodes[1];
							Visited.set(Edges.get(i).nodes[1],"true");
							i=0;
							if(current==destination) {
								System.out.println("returnning");
								return Stack;
							}
							z++;
						}
					}
				}
				
				System.out.println("removed "+current+"="+Stack.get(Stack.size()-1));
				if(Stack.get(Stack.size()-1)==0) {
					return Stack;
				}
				current=Stack.get(Stack.size()-2);
				Stack.remove(Stack.size()-1);
				
				z--;
			}
			
			
		return Stack;
	}
	
	
	
	public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath){
		String answer="";
		String myMcGillID = "260848649"; //Please initialize this variable with your McGill ID
		int maxFlow = 0;
		WGraph Old= new WGraph(graph);
		ArrayList<Integer> Path= pathDFS(source,destination,graph);
		while(Path.size()>1) {
				int betta=Integer.MAX_VALUE;
				for(int i=0;i<Path.size()-1;i++) {
					Edge e= graph.getEdge(Path.get(i),Path.get(i+1));
						if(e.weight<betta) {
							betta=e.weight;	
						}	
				}
				System.out.println("Betta ="+betta);
				for(int i=0;i<Path.size();i++) {
					System.out.print("Path index: "+i+" node: "+Path.get(i)+"\n");
				}
			
				
				for(int i=0;i<Path.size()-1;i++) {
					Edge e= graph.getEdge(Path.get(i),Path.get(i+1));
					
					graph.setEdge(Path.get(i),Path.get(i+1),e.weight-betta);
					
					Edge ee=graph.getEdge(Path.get(i+1),Path.get(i));
					if(ee==null) {
						Edge newEdge= new Edge(Path.get(i+1),Path.get(i),betta);
						graph.addEdge(newEdge);
					}
					else {
						graph.setEdge(Path.get(i+1),Path.get(i),ee.weight+betta);
					}
					
				}
				
				
				maxFlow+=betta;
				Path=pathDFS(source,destination,graph);
			
		}
		ArrayList<Edge> RezE=graph.getEdges();
		ArrayList<Edge> Edges=Old.getEdges();
		for(int i=0;i<Edges.size();i++) {
			Edge e= Edges.get(i);
			Edge re=null;
			for(int k=0;k<RezE.size();k++) {
				re=RezE.get(k);
				if(re.nodes[0]==e.nodes[0] && re.nodes[1]==e.nodes[1]) {
					break;
				}
			}
			Old.setEdge(e.nodes[0],e.nodes[1],e.weight-re.weight);
		}
		graph=Old;
		
		
		answer += maxFlow + "\n" + graph.toString();	
		writeAnswer(filePath+myMcGillID+".txt",answer);
		System.out.println(answer);
	}
	
	
	public static void writeAnswer(String path, String line){
		BufferedReader br = null;
		File file = new File(path);
		// if file doesnt exists, then create it
		
		try {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(line+"\n");	
		bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	 public static void main(String[] args){
		 String file = args[0];
		 File f = new File(file);
		 WGraph g = new WGraph(file);
		 fordfulkerson(g.getSource(),g.getDestination(),g,f.getAbsolutePath().replace(".txt",""));
	 }
}
