package hackerrank.cs.dataStructure.heap;

/*

Tieu owns a pizza restaurant and he manages it in his own way. While in a normal restaurant, a customer is served by following the first-come, first-served rule, Tieu simply minimizes the average waiting time of his customers. So he gets to decide who is served first, regardless of how sooner or later a person comes.

Different kinds of pizzas take different amounts of time to cook. Also, once he starts cooking a pizza, he cannot cook another pizza until the first pizza is completely cooked. Let's say we have three customers who come at time t=0, t=1, & t=2 respectively, and the time needed to cook their pizzas is 3, 9, & 6 respectively. If Tieu applies first-come, first-served rule, then the waiting time of three customers is 3, 11, & 16 respectively. The average waiting time in this case is (3 + 11 + 16) / 3 = 10. This is not an optimized solution. After serving the first customer at time t=3, Tieu can choose to serve the third customer. In that case, the waiting time will be 3, 7, & 17 respectively. Hence the average waiting time is (3 + 7 + 17) / 3 = 9.

Help Tieu achieve the minimum average waiting time. For the sake of simplicity, just find the integer part of the minimum average waiting time.

Input Format

The first line contains an integer N, which is the number of customers.
In the next N lines, the ith line contains two space separated numbers Ti and Li. Ti is the time when ith customer order a pizza, and Li is the time required to cook that pizza.

The  customer is not the customer arriving at the  arrival time.

Output Format

Display the integer part of the minimum average waiting time.
Constraints0

1 ≤ N ≤ 10^5
0 ≤ Ti ≤ 10^9
1 ≤ Li ≤ 10^9
Note

The waiting time is calculated as the difference between the time a customer orders pizza (the time at which they enter the shop) and the time she is served.

Cook does not know about the future orders.

Sample Input #00

3
0 3
1 9
2 6
Sample Output #00

9
Sample Input #01

3
0 3
1 9
2 5
Sample Output #01

8
Explanation #01

Let's call the person ordering at time = 0 as A, time = 1 as B and time = 2 as C. By delivering pizza for A, C and B we get the minimum average wait time to be

(3 + 6 + 16)/3 = 25/3 = 8.33
the integer part is 8 and hence the answer.

 */

import java.util.*;

/**
 * @author baejunbeom
 */
public class MinimumAverageWatingTime {

	private static final PriorityQueue<Customer> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.order));

	static class Customer {
		long time;
		long order;

		Customer(long time, long order) {
			this.time = time;
			this.order = order;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("Customer{");
			sb.append("time=").append(time);
			sb.append(", order=").append(order);
			sb.append('}');
			return sb.toString();
		}
	}

	/**
	 * 1. P 를 만들기 전에 다음 C 가 들어온다면,

	   1-1. 다음 P 를 만들때, 밀린 C들의 order 를 확인하고, 가장 작은 cost 의 order 를 수행한다.

	   1-2. 밀린 C들이 존재하지 않는다면, 다음 step 의 order 를 수행한다.

	 	두번째 문제 :
	 *
	 *
	 * @param curTime
	 * @return
	 */
	static long cal(long [] T, long [] L, int customerIndex, long curTime) {
		// base case : customer 를 모두 검사...
		if (customerIndex >= T.length) {
			return 0;
		}

		long sum = 0;

		if (customerIndex < T.length && T[customerIndex] >= curTime) {
			curTime += (T[customerIndex] - curTime + L[customerIndex]);
			sum += (curTime - T[customerIndex]);
			customerIndex++;
		}

		while (true) {
			if (customerIndex < T.length && T[customerIndex] <= curTime) {
				pq.add(new Customer(T[customerIndex], L[customerIndex]));
				customerIndex++;
				continue;
			}

			break;
		}

		while (!pq.isEmpty()) {
			Customer customer = pq.poll();
			curTime += customer.order;

			sum += (curTime - customer.time);
		}

		return sum + cal(T, L, customerIndex, curTime);
	}

	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//
//		int N = scanner.nextInt();
//
//		long [] T = new long[N];
//		long [] L = new long[N];
//
//		for (int i=0; i<N; i++) {
//			T[i] = scanner.nextInt();
//			L[i] = scanner.nextInt();
//		}

		int N = 50;

		long [] T = new long[N];
		long [] L = new long[N];

		String s = "137857688 413115088\n"
			+ "679783990 171274023\n"
			+ "783725811 742261681\n"
			+ "238387441 531682046\n"
			+ "683427743 559301752\n"
			+ "843391076 398722857\n"
			+ "593760457 2628387\n"
			+ "441381803 788912528\n"
			+ "771854880 916901718\n"
			+ "976015955 978145894\n"
			+ "235492265 264125858\n"
			+ "866638949 551120745\n"
			+ "238176883 201620672\n"
			+ "254029772 950305054\n"
			+ "356294983 203393748\n"
			+ "291672611 722032663\n"
			+ "560013448 126478507\n"
			+ "929678215 321924654\n"
			+ "737812220 884493567\n"
			+ "388266395 252551113\n"
			+ "79292652 229453232\n"
			+ "367753702 242882326\n"
			+ "930211560 461283594\n"
			+ "955372388 594944846\n"
			+ "506995906 872449795\n"
			+ "538015463 457419763\n"
			+ "950540066 820099707\n"
			+ "823860276 896193555\n"
			+ "538832788 47584891\n"
			+ "88242680 700680580\n"
			+ "196842555 623621497\n"
			+ "700528228 610051112\n"
			+ "668066226 170226832\n"
			+ "522278872 914689320\n"
			+ "375621149 336628938\n"
			+ "418416931 270027322\n"
			+ "179882058 480538711\n"
			+ "540671906 215602397\n"
			+ "201411561 930064341\n"
			+ "36616963 35887002\n"
			+ "772894889 944088968\n"
			+ "891134170 633761602\n"
			+ "975099001 434725536\n"
			+ "926070958 326905396\n"
			+ "727328509 867529847\n"
			+ "340789259 890185621\n"
			+ "923620442 986091986\n"
			+ "747688776 107231383\n"
			+ "38070714 495529501\n"
			+ "610348800 235616181";

		String[] split = s.split("\n");

		int k =0;
		for (String t2 : split) {
			String[] split2 = t2.split(" ");

			T[k] = Long.parseLong(split2[0]);
			L[k] = Long.parseLong(split2[1]);
			k++;
		}


		List<Customer> list = new ArrayList<>();

		for (int i=0 ; i<N ; i++) {
			list.add(new Customer(T[i], L[i]));
		}

		Collections.sort(list, (o1, o2) -> {
			if (o1.time > o2.time) return 1;
			else if (o1.time < o2.time) return -1;
			else return 0;
		});

		int j=0;

		for (Customer customer : list) {
			T[j] = customer.time;
			L[j] = customer.order;

			j++;
		}

		System.out.println(cal(T, L, 0, 0) / N);
	}

}
