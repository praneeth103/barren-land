package com.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Point {
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	int x;
	int y;
}

public class BarrenSolution {

	public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Enter input in the exact following pattern or press q to exit :");
            System.out.println("{\"48 192 351 207\",\"48 392 351 407\",\"120 52 135 547\",\"260 52 275 547\"}");
            System.out.print("Input is :");
            String input = scanner.nextLine();

            if ("q".equals(input)) {
                System.out.println("Exit!");
                break;
            }
            input = input.replaceAll("\\}", "");
            input = input.replaceAll("\\{", "");
            input = input.replaceAll("\"", "");
            
            List<Integer> result = getFertileLand(input.split(","));
            result.forEach(item->System.out.print(item + " "));
			System.out.println();
            System.out.println("-----------\n");
        }

        scanner.close();
	}
	
	public static  List<Integer> getFertileLand(String [] input) {
		try {
			int[][] area = new int[400][600];

			
			//For each barren rectangle loop through
			for (int i = 0; i < input.length; i++) {
				// Create a 2D integer array and set 1 for all barren points
				String[] split = input[i].split(" ");
				for (int x = Integer.parseInt(split[0]); x <= Integer.parseInt(split[2]); x++) {
					for (int y = Integer.parseInt(split[1]); y <= Integer.parseInt(split[3]); y++) {
						
						area[x][y] = 1;

					}
				}
			}
			//Using BFS to traverse the rectangle. 
			Queue<Point> queue = new LinkedList<>();
			int x = 0, y = 0;
			
			//Counter counting the current fertile land value
			int fertileArea = 0;
			
			List<Integer> result = new ArrayList<>();
			while (x < 400 && y < 600) {
				if (queue.isEmpty()) {

					//Add to result only if fertile area has some points in it 
					if (fertileArea != 0)
						result.add(fertileArea);
					
					//Once we get a fertile land added reset the fertile land counter to 0
					fertileArea = 0;
					if (area[x][y] == 0) {
						//Once we count a fertile area make it barren so that it is not counted again
						area[x][y] = 1;
						queue.add(new Point(x, y));

					}
					
					//Traversing from bottom left to top right of rectangle
					if (y < 599)
						y++;
					else {
						y = 0;
						x++;
					}

				}
				//Keep searching for points which are are 0 and add it to the fertile area. 
				if (!queue.isEmpty()) {
					Point point = queue.poll();
					int m = point.x;
					int n = point.y;
					fertileArea++;

					//Search the four connected points if they are fertile or not and add them to the queue if they are fertile 
					if (m - 1 >= 0 && area[m - 1][n] == 0) {
						queue.add(new Point(m - 1, n));
						area[m - 1][n] = 1;
					}

					if (n - 1 >= 0 && area[m][n - 1] == 0) {
						queue.add(new Point(m, n - 1));
						area[m][n - 1] = 1;
					}

					if (m + 1 < 400 && area[m + 1][n] == 0) {
						queue.add(new Point(m + 1, n));
						area[m + 1][n] = 1;
					}

					if (n + 1 < 600 && area[m][n + 1] == 0) {
						queue.add(new Point(m, n + 1));
						area[m][n + 1] = 1;
					}

				}
			}
			Collections.sort(result);
			return result;			

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Enter information only in the specified format");
		}
		return null;
	}
}
