package mobile.mcleo.ca.foodii;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private ArrayList<Node> mManagers;
    @Test
    public void addition_isCorrect() throws Exception {

        initData();
        List<Node> result = sortManager(0, mManagers);
        System.out.println("----final----");
        for (Node node : result) {
            System.out.println(node.toString());
        }
//        assertEquals(7, result.size());
    }

    private List<Node> sortManager(int managerId, List<Node> managers){

        System.out.println("----"+managerId+"----");
        for (Node node : managers) {
            System.out.println(node.toString());
        }


        ArrayList<Node> merged = new ArrayList();
        ArrayList<Node> temp = new ArrayList(managers);

        for (Node manager : managers) {
            if (manager.parentId == managerId) {
                merged.add(manager);
                temp.remove(manager);
                if (manager.hasChild) {
                    merged.addAll(sortManager(manager.Id, temp));
                }
            }
        }

        return merged;

    }

    private class Node{
        int Id;
        int parentId;
        boolean hasChild;

        public Node(int id, int pId, boolean child){
            Id = id;
            parentId = pId;
            hasChild = child;
        }

        @Override
        public String toString() {
            return ""+Id;
        }
    }

    private void initData(){
        mManagers = new ArrayList<>();
        mManagers.add(new Node(1037, 0, true));
        mManagers.add(new Node(1329, 1037, false));
        mManagers.add(new Node(1357, 1037, true));
//        mManagers.add(new Node(1357, 2127, true));
        mManagers.add(new Node(2051, 2127, false));
        mManagers.add(new Node(2052, 2127, false));
        mManagers.add(new Node(2043, 1037, false));
        mManagers.add(new Node(2127, 1037, true));
//        mManagers.add(new Node(2127, 1357, true));

    }
}