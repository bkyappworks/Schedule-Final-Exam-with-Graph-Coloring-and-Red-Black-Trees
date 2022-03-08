import java.io.*;

public class Main {
    static RedBlackTree rbtree = new RedBlackTree();
    static StringBuilder stringbuilder = new StringBuilder();
    static StringBuilder names = new StringBuilder();
    static boolean print = false;

    public static void main(String[] args) throws IOException {
        try {
            BufferedReader in =
                    new BufferedReader(
                            new FileReader(args[0])
                    );
            String line;
            line = in.readLine();

            while(line != null) {
                processLine(line);
                line = in.readLine();
            }
        } catch(IOException e) {
            System.out.println("IO Exception"); }

        int[][] graph = buildGraph();
        PrintWriter writer = new PrintWriter(new FileOutputStream(
                "result.txt",
                true /* append = true */));
        if (print == false) {
            System.out.println("yuehl");
            writer.println("yuehl");
            print = true;
        }

        // print matrix
        for (int i = 0; i < graph.length; i++) {
            System.out.println(graphFormat(graph[i]));
            writer.println(graphFormat(graph[i]));
        }
        System.out.println();
        writer.println("");
        // call greedy()
        String raw_schedule = greedy(graph);
        String[] schedule = schedule(raw_schedule);
        for (int i = 0; i < schedule.length; i++) {
            System.out.println(schedule[i]);
            writer.println(schedule[i]);
        }
        writer.println("");
        writer.close();

    }
    public static void processLine(String line) {
        StringBuilder sb = new StringBuilder();
        String[] data = line.split(" ");
        int num = Integer.parseInt(data[1]);
        int[] courses = new int[num];
        for (int i = 2; i < 2 + num; i++) {
            if (!rbtree.contains(data[i])) {
                rbtree.insert(data[i]);
                names.append(data[i]).append('!');
            }
            courses[i-2] = rbtree.getID(data[i]);
            for (int j = 0; j < i-2 ; j++) {
                sb.append(courses[i-2]).append(',').append(courses[j]);
                sb.append('&');
            }
        }
//        System.out.println("rbtree: "+rbtree.toString());
        stringbuilder.append(sb);
        System.out.println("stringbuilder: "+stringbuilder);
    }
    public static int[][] buildGraph() {
        int size = rbtree.getSize();
        String s = stringbuilder.toString();

        int[][] graph = new int[size][size];
        String[] arr = s.split("&");
        for (String pair: arr) {
            int i  = Integer.parseInt(pair.split(",")[0]);
            int j = Integer.parseInt(pair.split(",")[1]);
            graph[i][j] = 1;
            graph[j][i] = 1;
        }
        return graph;
    }
    public static String greedy(int[][] graph) {
        boolean found;
        int[] courses = new int[graph.length];
        // populate courses
        for (int i = 0; i < graph.length; i++) {
            courses[i] = i;
        }
        // collection of newclr to return
        StringBuilder result = new StringBuilder();
        // start greedy
        while (courses[0] != -1) { // keep loop through when there are vertex uncolored
            // populate newclr
            int[] newclr = new int[graph.length];
            int newclrSize = 0; // get index position to add
            for (int i = 0; i < graph.length; i++) {
                newclr[i] = -1;
            }
            // populate uncolored
            int[] uncolored = new int[graph.length];
            for (int i = 0; i < graph.length; i++) {
                uncolored[i] = -1;
            }

            for (int v = 0; v < courses.length; v++) { // while v <> null
                if (courses[v] == -1) {
                    break;
                }
                found = false;
//                w = newclr[0]; // w = first vertex in newclr
                for (int w = 0; w < newclr.length; w++) { // while w <> null
                    if (newclr[w] == -1) {
                        break;
                    }
                    // if there is an edge between v and w in G then
                    if (graph[courses[v]][newclr[w]] == 1) { // course[v]
                        found = true;
                    }
                    // w = next vertex in newclr
                }
                if (found == false) {
                    // mark v colored
                    // add v to newclr
                    for (int e = 0; e < newclr.length-1; e++) {
                        if (newclr[e] == -1) {
                            break;
                        }
                        newclrSize++;
                    }
                    // add v to newclr
                    newclr[newclrSize] = courses[v];

                } else { // if cur v is uncolored
                    // add uncolored v to uncolored
                    int uncoloredSize = 0; // get index to add
                    for (int e = 0; e < uncolored.length-1; e++) {
                        if (uncolored[e] == -1) {
                            break;
                        }
                        uncoloredSize++;
                    }
                    uncolored[uncoloredSize] = courses[v];
                }
                // v = next uncolored vertex in G
            }
            courses = uncolored;
            for (int i = 0; i < newclr.length; i++) {
                result.append(newclr[i]).append(',');
            }
            result.append('/');
        }
        return result.toString();
    }
    public static String[] schedule(String raw_schedule) {
        String [] arr = names.toString().split("!");
        String[] data = raw_schedule.split("/");
        int cnt = 1;
        for (String s1: data) {
            String [] s2 = s1.split(",");
            String r = "";
            for (String c: s2) {
                if (!c.equals("-1")) {
                    int i = Integer.parseInt(c);
                    r += arr[i]+" ";
                }
            }
            cnt++;
        }
        int recnt = 0;
        int num = 1;
        String[] result  = new String[cnt-1];
        for (String s1: data) {
            String [] s2 = s1.split(",");
            String r = "";
            for (String c: s2) {
                if (!c.equals("-1")) {
                    int i = Integer.parseInt(c);
                    r += arr[i]+" ";
                }
            }
            result[recnt] = "Final Exam Period "+num+" => "+r;
            recnt++;
            num++;
        }
        return result;
    }
    public static String graphFormat(int[] graphLine) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graphLine.length; i++) {
            sb.append(graphLine[i]);
        }
        return sb.toString();
    }

}
