package 백준21276계보복원가호석;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static class People {

        HashSet<String> descendants;
        String ancestor;

        public People() {
            this.descendants = new HashSet<>();
            this.ancestor = null;
        }
    }

    private static HashMap<String, People> genealogy;
    private static HashMap<String, Integer> counts;
    private static String[] names;
    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String, ArrayList<String>> results = new TreeMap<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int k;

        st = new StringTokenizer(br.readLine());
        names = new String[N];
        genealogy = new HashMap<>();
        counts = new HashMap<>();

        for (int i = 0; i < N; i++) {

            names[i] = st.nextToken();
            genealogy.put(names[i], new People());
        }

        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {

            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            String ancestor = st.nextToken();

            People people = genealogy.get(ancestor);
            People child = genealogy.get(name);

            people.descendants.add(name);
            child.ancestor = ancestor;

            counts.put(name, counts.getOrDefault(name, 0) + 1);
            genealogy.replace(ancestor, people);
            genealogy.replace(name, child);
        }
        ArrayList<String> roots = getAncestors();

        sb.append(roots.size()).append("\n");
        for (String s : roots)
            sb.append(s).append(" ");
        sb.append("\n");

        for (String root : roots)
            results.putAll(navigateGenealogy(root));

        for (String name : results.keySet()) {

            ArrayList<String> childes = results.get(name);
            sb.append(name).append(" ");

            if (childes == null) {
                sb.append(0);
            } else {
                Collections.sort(childes);
                sb.append(childes.size());

                for (String children : childes)
                    sb.append(" ").append(children);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static ArrayList<String> getAncestors() {

        ArrayList<String> root = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            People people = genealogy.get(names[i]);

            if (people.ancestor == null)
                root.add(names[i]);
        }
        Collections.sort(root);
        return root;
    }

    private static HashMap<String, ArrayList<String>> navigateGenealogy(String start) {

        HashMap<String, ArrayList<String>> descendants = new HashMap<>();
        ArrayDeque<String> deque = new ArrayDeque<>();
        ArrayList<String> childList;
        deque.add(start);

        while (!deque.isEmpty()) {

            String name = deque.remove();
            People people = genealogy.get(name);

            if (people.descendants.isEmpty()) {

                descendants.put(name, null);
                continue;
            }

            for (String descendant : people.descendants) {

                counts.replace(descendant, counts.get(descendant) - 1);

                if (counts.get(descendant) == 0) {

                    if (descendants.containsKey(name))
                        childList = descendants.get(name);
                    else
                        childList = new ArrayList<>();
                    childList.add(descendant);
                    descendants.put(name, childList);
                    deque.add(descendant);
                }
            }
        }
        return descendants;
    }
}
