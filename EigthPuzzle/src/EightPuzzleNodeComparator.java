import java.util.Comparator;

class EightPuzzleNodeComparator implements Comparator<EightPuzzleNode> {
    @Override
    public int compare(EightPuzzleNode x, EightPuzzleNode y){
        if(x.g + x.h < y.g + y.h)return -1;
        if(x.g + x.h > y.g + y.h)return 1;
        else return 0;
    }
}
