import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

//-----------------------------------------------------
// Title: Main Class
// Author: Ayda Nil Özyürek
// Description: This class does the following:
//  			- Read the input from the console
//				- Crate a graph 
//				- Find the lexicographically smallest path.
//------------------------------------------------

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // the number of cities we have
		int M = sc.nextInt(); // the number of connections between the N cities
		int T = sc.nextInt(); // the time required by airports to change their states
		int C = sc.nextInt(); // the time for traveling one city to another.

		// initialize adjacency list
		List<Integer>[] v = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			v[i] = new ArrayList<Integer>();
		}

		boolean[] visited = new boolean[N + 1];

		// read in edges
		for (int i = 0; i < M; i++) {
			int U = sc.nextInt();
			int V = sc.nextInt();
			// -----------------------------
			// this is the part where you add the edges
			// -----------------------------
			v[U].add(V);
			v[V].add(U);
		}

		// The adjacency list v[i] is sorted ascendingly
		// by the code to assure the lexicographically shortest path.
		for (int i = 1; i <= N; i++) {
			Collections.sort(v[i]);
		}

		int[] parent = new int[N + 1];
		int[] distance = new int[N + 1];

		int X = sc.nextInt();// the city we start to travel
		int Y = sc.nextInt();// the city we want to reach

		parent[X] = 0;

		// initializes a queue "q" and adds the starting city a to it to keep track of
		// the cities to visit. To designate the starting city as visited, it sets
		// visited[X] to true.
		// BFS logic was used.
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(X);
		visited[X] = true;

		boolean ans = false;

		while (!q.isEmpty()) {
			int r = q.poll(); // removes the first city r from the queue

			if (ans) {
				break;
			}

			// The code sets the parent to r, marks the child as visited, and adds it to the
			// queue q for every unvisited neighboring city child.
			for (int i = 0; i < v[r].size(); i++) {
				int child = v[r].get(i);

				if (!visited[child]) {
					// If the adjacent city child is the ending city b, the code sets ans to true
					// and breaks out of the loop.
					parent[child] = r;
					visited[child] = true;

					q.offer(child);

					if (child == Y) {
						ans = true;
						break;
					}

					q.add(child);
				}
			}

		}

		// By tracing the parent of each city from Y to X, the code stores the route
		// from Y to X in a stack temp. The smallest path from a lexicographic
		// perspective is then obtained by printing the stack in reverse order.
		Stack<Integer> temp = new Stack<Integer>();

		while (parent[Y] != 0) {
			temp.push(Y);
			Y = parent[Y];
		}

		temp.push(X);
		int size = temp.size();
		System.out.println(size);

		while (!temp.isEmpty()) {
			System.out.print(temp.pop() + " ");
		}

		int time = 0;
		for (int i = 0; i < size-1; i++) {
			while (time % T != 0)
				time++;
			time += C;
		}

		System.out.println("\n" + time);

	}

}
