import java.util.ArrayList;
import java.util.Scanner;

public class MainTest {
    public static void main(String [] args){
        Scanner in=new Scanner(System.in);
        EightPuzzleGraph graph = new EightPuzzleGraph();
        int goal[][]={{1,2,3},
                      {8,0,4},
                      {7,6,5}};
        ArrayList<ArrayList<Integer>> srcgrid = new ArrayList();
        ArrayList<ArrayList<Integer>> dstgrid = new ArrayList();
        for(int i=0;i<3;i++){
            ArrayList<Integer> list=new ArrayList<>();
            for(int j=0;j<3;j++){
                list.add(goal[i][j]);
            }
            dstgrid.add(list);
        }
        System.out.println("input S0：");
        for (int i = 0; i < 3; i++) {
            ArrayList<Integer> row = new ArrayList();
            String temp=in.nextLine();
            String [] string=temp.split(" ");
            for(String mString:string){
                row.add(Integer.parseInt(mString.trim()));
            }
            srcgrid.add(row);
        }
        graph.astarSearch(srcgrid, dstgrid);
    }
}
