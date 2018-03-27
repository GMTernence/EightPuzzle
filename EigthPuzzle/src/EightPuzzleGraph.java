import java.util.*;
public class EightPuzzleGraph {
    PriorityQueue<EightPuzzleNode> open_list;
    HashSet<EightPuzzleNode> closed_list;

    void astarSearch(ArrayList<ArrayList<Integer>> src, ArrayList<ArrayList<Integer>> dst){
        if(src.size()!=3 || dst.size()!=3){System.err.println("Array sizes not correct");return;}
        boolean[] srccheck=new boolean[9];
        boolean[] dstcheck=new boolean[9];
        Iterator it1 = src.iterator(), it2 = dst.iterator();
        while(it1.hasNext() && it2.hasNext()){
            ArrayList<Integer> rowsrc = (ArrayList<Integer>)it1.next();
            ArrayList<Integer> rowdst = (ArrayList<Integer>)it2.next();

            if(rowsrc.size()!=3 || rowdst.size()!=3){System.err.println("Array sizes not correct");return;}
            Iterator it3 = rowsrc.iterator(), it4 = rowdst.iterator();
            while(it3.hasNext() && it4.hasNext()){
                int elemsrc = (Integer)it3.next();
                int elemdst = (Integer)it4.next();
                //number not correct
                if(elemsrc<0 || elemsrc>8 || elemdst<0 || elemdst>8){System.err.println("Array elements out of bound");return;}
                srccheck[elemsrc]=true;
                dstcheck[elemdst]=true;
            }
        }
        boolean check=true;
        for(int i=0;i<9;i++)
            check = check && srccheck[i] && dstcheck[i];
        if(!check){System.err.println("Array elements do not satisfy 8 puzzle grid");return;}
        if(src.equals(dst)){System.out.println("source = destination");return;}
        Comparator<EightPuzzleNode> comparator = new EightPuzzleNodeComparator();
        open_list = new PriorityQueue(10,comparator);
        closed_list = new HashSet();
        EightPuzzleNode start = new EightPuzzleNode(src,dst);
        start.g = 0;
        open_list.add(start);
        EightPuzzleNode transfer_node = null;

        while(!open_list.isEmpty() && (transfer_node == null || !transfer_node.grid.equals(dst))){
            transfer_node = open_list.poll();
            transfer_node.open = 0;
            closed_list.add(transfer_node);
            //扩展子节点
            ArrayList<EightPuzzleNode> adj_nodes = transfer_node.getAdjacentNodes(dst);
            for(EightPuzzleNode adj_node : adj_nodes){
                Iterator it = closed_list.iterator();
                boolean inCL=false;
                while(it.hasNext()){
                    EightPuzzleNode closed_node = (EightPuzzleNode)it.next();
                    if(adj_node.equals(closed_node)){
                        if (transfer_node.g + 1 < closed_node.g) {
                            closed_node.g = transfer_node.g + 1;
                            closed_node.predecessor = transfer_node;
                            parentRedirection(closed_node,dst);
                        }
                        inCL = true;break;
                    }
                }
                if(inCL)continue;
                it = open_list.iterator();
                boolean inOL=false;
                while(it.hasNext()){
                    EightPuzzleNode open_node = (EightPuzzleNode)it.next();
                    if(adj_node.equals(open_node)){
                        if (transfer_node.g + 1 < open_node.g) {
                            open_node.g = transfer_node.g + 1;
                            open_node.predecessor = transfer_node;
                        }
                        inOL = true;
                        break;
                    }
                }
                if(inOL)continue;
                adj_node.g = transfer_node.g + 1;
                adj_node.predecessor = transfer_node;
                adj_node.open = 1;
                open_list.add(adj_node);
            }
        }
        if(transfer_node.grid.equals(dst)){System.out.println("Path:");transfer_node.printPath();}
    }

    void parentRedirection(EightPuzzleNode node, ArrayList<ArrayList<Integer>> dst){
        ArrayList<EightPuzzleNode> adj_nodes = node.getAdjacentNodes(dst);
        for(EightPuzzleNode adj_node : adj_nodes){
            Iterator it = closed_list.iterator();
            boolean inCL = false;
            while (it.hasNext()) {
                EightPuzzleNode closed_node = (EightPuzzleNode) it.next();
                if (adj_node.equals(closed_node)) {
                    if (node.g + 1 < closed_node.g) {
                        closed_node.g = node.g + 1;
                        closed_node.predecessor = node;
                        parentRedirection(closed_node,dst);
                    }
                    inCL = true;
                    break;
                }
            }
            if (inCL)continue;
        }
    }
}


