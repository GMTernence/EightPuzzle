import java.util.ArrayList;
import java.util.Iterator;

class EightPuzzleNode {
    ArrayList<ArrayList<Integer>> grid;
    EightPuzzleNode predecessor;
    int open;
    int g, h;
    int rowzero,colzero;

    EightPuzzleNode(ArrayList<ArrayList<Integer>> i, ArrayList<ArrayList<Integer>> dst) {
        grid = i;  //start grid
        open = -1;
        h = 0;  //the number that dest not equals src
        predecessor = null;
        Iterator it1 = i.iterator(), it2 = dst.iterator();
        int rz=0;
        while(it1.hasNext() && it2.hasNext()){
            ArrayList<Integer> rowi = (ArrayList<Integer>)it1.next();
            ArrayList<Integer> rowdst = (ArrayList<Integer>)it2.next();
            Iterator it3 = rowi.iterator(), it4 = rowdst.iterator();
            int cz=0;
            while(it3.hasNext() && it4.hasNext()){
                int elemi = (Integer)it3.next();
                int elemdst = (Integer)it4.next();
                if(elemi != elemdst)
                    h++;
                if(elemi==0){rowzero = rz; colzero = cz;}  //0号位置
                cz++;
            }
            rz++;
        }

        it1 = i.iterator(); it2 = dst.iterator();
        int posx=0,posy=0;
        int[] siddu_set1x = new int[9];
        int[] siddu_set1y = new int[9];
        int[] siddu_set2x = new int[9];
        int[] siddu_set2y = new int[9];

        while(it1.hasNext() && it2.hasNext()){
            ArrayList<Integer> rowi = (ArrayList<Integer>)it1.next();
            ArrayList<Integer> rowdst = (ArrayList<Integer>)it2.next();
            Iterator it3 = rowi.iterator(), it4 = rowdst.iterator();
            while(it3.hasNext() && it4.hasNext()){
                int elemi = (Integer)it3.next();
                int elemdst = (Integer)it4.next();
                siddu_set1x[elemi]=posx;
                siddu_set1y[elemi]=posy;
                siddu_set2x[elemdst]=posx;
                siddu_set2y[elemdst]=posy;
                posy++;
            }
            posx++;
            posy=0;
        }
    }
    boolean equals(EightPuzzleNode dst){
        Iterator it1 = this.grid.iterator(), it2 = dst.grid.iterator();
        while(it1.hasNext() && it2.hasNext()){
            ArrayList<Integer> rowi = (ArrayList<Integer>)it1.next();
            ArrayList<Integer> rowdst = (ArrayList<Integer>)it2.next();
            Iterator it3 = rowi.iterator(), it4 = rowdst.iterator();
            while(it3.hasNext() && it4.hasNext()){
                int elemi = (Integer)it3.next();
                int elemdst = (Integer)it4.next();
                if(elemi != elemdst)return false;
            }
        }
        return true;
    }

    //扩展子节点
    ArrayList<EightPuzzleNode> getAdjacentNodes(ArrayList<ArrayList<Integer>> dst){
        ArrayList<EightPuzzleNode> adj_nodes=new ArrayList();
        int rz = rowzero-1;
        //下移
        if(rz>=0 && rz<=2){

            ArrayList<ArrayList<Integer>> new_grid = new ArrayList<>();
            for(ArrayList<Integer> row : grid)new_grid.add((ArrayList<Integer>)row.clone());
            new_grid.get(rowzero).set(colzero,new_grid.get(rz).get(colzero));
            new_grid.get(rz).set(colzero,0);
            EightPuzzleNode node = new EightPuzzleNode (new_grid, dst);
            adj_nodes.add(node);
        }
        rz = rowzero+1;
        //上移
        if(rz>=0 && rz<=2){
            ArrayList<ArrayList<Integer>> new_grid = new ArrayList<>();
            for(ArrayList<Integer> row : grid)new_grid.add((ArrayList<Integer>)row.clone());
            new_grid.get(rowzero).set(colzero,new_grid.get(rz).get(colzero));
            new_grid.get(rz).set(colzero,0);
            EightPuzzleNode node = new EightPuzzleNode (new_grid, dst);
            adj_nodes.add(node);
        }
        int cz = colzero-1;
        //左移
        if(cz>=0 && cz<=2){
            ArrayList<ArrayList<Integer>> new_grid = new ArrayList<>();
            for(ArrayList<Integer> row : grid)new_grid.add((ArrayList<Integer>)row.clone());
            new_grid.get(rowzero).set(colzero,new_grid.get(rowzero).get(cz));
            new_grid.get(rowzero).set(cz,0);
            EightPuzzleNode node = new EightPuzzleNode (new_grid, dst);
            adj_nodes.add(node);
        }
        cz = colzero+1;
        //右移
        if(cz>=0 && cz<=2){
            ArrayList<ArrayList<Integer>> new_grid = new ArrayList<>();
            for(ArrayList<Integer> row : grid)new_grid.add((ArrayList<Integer>)row.clone());
            new_grid.get(rowzero).set(colzero,new_grid.get(rowzero).get(cz));
            new_grid.get(rowzero).set(cz,0);
            EightPuzzleNode node = new EightPuzzleNode (new_grid, dst);
            adj_nodes.add(node);
        }
        return adj_nodes;
    }
    void printPath(){
        if(this.predecessor!=null)
            this.predecessor.printPath();
        Iterator it1=this.grid.iterator();
        while(it1.hasNext()){
            ArrayList<Integer> row=(ArrayList<Integer>) it1.next();
            Iterator it2=row.iterator();
            while(it2.hasNext()){
                int element=(Integer)it2.next();
                System.out.print(element+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

