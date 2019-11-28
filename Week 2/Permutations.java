/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Scanner;

public class Permutations {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int i = Integer.parseInt(args[0]);
        System.out.println(i);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String in = sc.next();
            System.out.println(in);
            rq.enqueue(in);
        }
        System.out.println("enqueue complete");
        for (int j = 0; j < i; j++) {
            System.out.println(rq.dequeue());
        }
    }
}
