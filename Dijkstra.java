import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Dijkstra {

    HashMap<Integer, ArrayList<int[]>> numbersAndDistances;
    PriorityQueue<int[]> pq;
    HashMap<Integer, Integer> distances;

    public Dijkstra(String filepath) {
        numbersAndDistances = new HashMap<Integer, ArrayList<int[]>>();
        pq = new PriorityQueue<int[]>((a, b) -> (a[1] - b[1]));
        distances = new HashMap<Integer, Integer>();
        createMapOfInts(filepath);
        computeShortestDistances();
    }

    private void createMapOfInts(String filePath) {
        try {
            File ints = new File(filePath);
            FileReader file = null;
            try {
                file = new FileReader(ints);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            assert file != null;
            BufferedReader buffer = new BufferedReader(file);
            String intLine = null;
            try {
                intLine = buffer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (intLine != null) {
                String[] lineArray = intLine.split("\t");
                Integer position = Integer.parseInt(lineArray[0]);
                ArrayList<int[]> intArray = new ArrayList<int[]>();
                for (int i = 1; i < lineArray.length; i++) {
                    String[] distanceArray = lineArray[i].split(",");
                    int node = Integer.parseInt(distanceArray[0]);
                    int distToNode = Integer.parseInt(distanceArray[1]);
                    intArray.add(new int[]{node, distToNode});
                }
                numbersAndDistances.put(position, intArray);
                intLine = buffer.readLine();
            }
            buffer.close();
        } catch (IOException e) {
            System.out.println("Couldn't find the file.");
        }
    }

    private void computeShortestDistances() {
        //while the size of the map < total places
        int totalNodes = this.numbersAndDistances.size();
        System.out.println(totalNodes);
        this.pq.add(new int[]{1, 0});
        while (distances.size() < totalNodes && !pq.isEmpty()) {
            int[] curNode = this.pq.poll();
            System.out.println(curNode[0] + " and " + curNode[1]);
            int curInt = curNode[0];
            int curDist = curNode[1];
            if (!distances.containsKey(curInt)) {
                //grab everything from the next # (if not seen)
                //push connected #s to PQ
                //add # and distance to map
                distances.put(curInt, curDist);
                ArrayList<int[]> distFromCurInt = this.numbersAndDistances.get(curInt);
                for (int[] dist : distFromCurInt) {
                    dist[1] += curDist;
                    pq.add(dist);
                }
            }
        }

    }

    public int getDistance(int position) {
        System.out.println(position);
        return this.distances.get(position);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Dijkstra dk = new Dijkstra("./dijkstraData.txt");
        System.out.println(dk.getDistance(7));
    }
}
