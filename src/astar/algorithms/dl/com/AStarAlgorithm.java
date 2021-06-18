package astar.algorithms.dl.com;

import java.util.PriorityQueue;

public class AStarAlgorithm {

    public static Node aStar(Node start, Node target){
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        start.f = start.g + start.calculateHeuristic();
        openList.add(start);

        while(!openList.isEmpty()){

            Node n = openList.peek();
            if(n == target){
                return n;
            }

            for(Node.Edge edge : n.neighbors){
                Node m = edge.node;
                double totalWeight = n.g + edge.weight;

                if(!openList.contains(m) && !closedList.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristic();
                    openList.add(m);
                }else{
                    if(totalWeight < m.g){
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristic();

                        if(closedList.contains(m)){
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }
}
