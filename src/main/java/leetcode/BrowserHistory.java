package leetcode;

public class BrowserHistory {

	private MyLinkedList myLinkedList;
	public BrowserHistory(String homepage) {
		this.myLinkedList = new MyLinkedList(homepage, null, null);
	}

	public void visit(String url) {
		this.myLinkedList.next = null;
		this.myLinkedList.next = new MyLinkedList(url, this.myLinkedList, null);
		this.myLinkedList = myLinkedList.next;
	}

	/**
	 * step 이 현재 list 의 사이즈를 초과하면 최대로 갈 수 있는 만큼만 간다.
	 * @param steps
	 * @return
	 */
	public String back(int steps) {
		while (steps-- > 0) {
			if (myLinkedList.before == null) {
				break;
			}

			this.myLinkedList = this.myLinkedList.before;
		}

		return this.myLinkedList.url;
	}

	/**
	 *
	 * @param steps
	 * @return
	 */
	public String forward(int steps) {
		while (steps-- > 0) {
			if (myLinkedList.next == null) {
				break;
			}

			this.myLinkedList = this.myLinkedList.next;
		}

		return this.myLinkedList.url;
	}

	static class MyLinkedList {
		String url;
		MyLinkedList before;
		MyLinkedList next;

		public MyLinkedList(String url, MyLinkedList before, MyLinkedList next) {
			this.url = url;
			this.before = before;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
		browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
		browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
		browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
		System.out.println(browserHistory.back(1));                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
		System.out.println(browserHistory.back(1));                   // You are in "facebook.com", move back to "google.com" return "google.com"
		System.out.println(browserHistory.forward(1));                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
		browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
		System.out.println(browserHistory.forward(2));                // You are in "linkedin.com", you cannot move forward any steps.
		System.out.println(browserHistory.back(2));                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
		System.out.println(browserHistory.back(7));                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
	}
}
