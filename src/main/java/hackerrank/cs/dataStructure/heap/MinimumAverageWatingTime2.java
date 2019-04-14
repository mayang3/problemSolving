package hackerrank.cs.dataStructure.heap;

import java.util.*;

/**
 * @author baejunbeom
 */
public class MinimumAverageWatingTime2 {

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

	static long addOrder(Customer customer, long currentTime) {
		if (pq.isEmpty()) {
			currentTime = Math.max(currentTime, customer.time);
		}

		pq.add(customer);

		return currentTime;
	}

	static long solve(List<Customer> customerList, int n) {
		long currentTime = 0;
		long totalWatingTime = 0;

		for (int i=0; i<n; i++) {
			// 1. 젤 처음값이거나, 현재시간보다 이전에 들어온 고객들은 pq 에 집어넣는다.
			if (i == 0 || customerList.get(i).time <= currentTime) {
				currentTime = addOrder(customerList.get(i), currentTime);
			} else {
				// 2. 현재시간보다 이후에 들어온 고객이 들어온다면, 이전 주문건부터 처리한다.
				while (pq.isEmpty() == false && customerList.get(i).time > currentTime) {
					Customer customer = pq.poll();
					currentTime = serveOrderCurrentTime(customer, currentTime);
					totalWatingTime += serveOrderTotalTime(customer, currentTime);
				}

				// 3. 현재 시간의 주문을 받는다.
				currentTime = addOrder(customerList.get(i), currentTime);
			}
		}

		// 4. 남아있는 고객의 주문건들을 처리한다.
		while(pq.isEmpty() == false) {
			Customer customer = pq.poll();
			currentTime = serveOrderCurrentTime(customer, currentTime);
			totalWatingTime += serveOrderTotalTime(customer, currentTime);
		}

		return totalWatingTime / n;
	}

	static long serveOrderCurrentTime(Customer customer, long currentTime) {
		return currentTime + customer.order;
	}

	static long serveOrderTotalTime(Customer customer, long currentTime) {
		return currentTime - customer.time;
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

//		System.out.println(T[50]);
//		System.out.println(L[50]);


		List<Customer> list = new ArrayList<>();

		for (int i=0 ; i<N ; i++) {
			list.add(new Customer(T[i], L[i]));
		}

		Collections.sort(list, (o1, o2) -> {
			if (o1.time > o2.time) return 1;
			else if (o1.time < o2.time) return -1;
			else return 0;
		});

		System.out.println(solve(list, N));
	}
}
