package stratgies2.segment_tree;

public class SegmentTreeRange {

	// 부분 구간의 계산 결과
	static class RangeResult {
		// 이 구간의 크기
		int size;
		// 가장 자주 등장하는 숫자의 출현 횟수
		int mostFrequent;
		// 왼쪽 끝 "숫자" 와 왼쪽 끝 숫자의 "출현 횟수"
		int leftNumber, leftFreq;
		// 오른쪽 끝 "숫자" 와 오른쪽 끝 숫자의 "출현 횟수"
		int rightNumber, rightFreq;
	}

	// 왼쪽 부분 구간의 계산 결과 a, 오른쪽 부분 구간의 계산 결과 b 를 합친다.

	// 계산 순서는 다음과 같이 한다.
	// 1. 구간의 크기 계산
	// 2. 왼쪽 숫자와 왼쪽 숫자의 출현빈도 계산
	// 3. 오른 쪽 숫자와 오른쪽 숫자의 출현빈도 계산
	// 4. 가장 많이 출현하는 수의 빈도수 계산
	RangeResult merge(RangeResult a, RangeResult b) {
		RangeResult ret = new RangeResult();

		// 1. 구간의 크기
		ret.size = a.size + b.size;

		// 2. 왼쪽 계산
		// 2-1. 왼쪽 기본 계산
		// 2-2. 왼쪽 빈도 업데이트
		// 왼쪽 숫자는 a.leftNumber 로 정해져 있다.
		ret.leftNumber = a.leftNumber;
		ret.leftFreq = a.leftFreq;

		// 왼쪽 부분 구간이 전부 a.leftNumber 인 경우만 왼쪽 끝 숫자의 출현횟수만 별도로 처리해준다.
		// 예) [1,1,1,1] 과 [1,2,2,2] 를 합칠 때
		if (a.size == a.leftFreq && a.leftNumber == b.leftNumber) {
			ret.leftFreq += b.leftFreq;
		}

		// 3. 오른쪽 계산
		// 3-1. 오른쪽 기본 계산
		// 3-2. 오른쪽 빈도 업데이트
		// 오른쪽 끝 숫자
		ret.rightNumber = b.rightNumber;
		ret.rightFreq = b.rightFreq;

		// 오른쪽 부분 구간이 전부 b.rightNumber 인 경우
		// 오른쪽 끝 숫자의 출현횟수만 별도로 처리한다.
		// 예) [1,1,1,2] 와 [2,2,2,2] 를 합칠 때
		if (b.size == b.rightFreq && a.rightNumber == b.rightNumber) {
			ret.rightFreq += a.rightFreq;
		}

		// 4. 빈도 계산
		// 기본적으로 가장 많이 출현하는 수의 빈도수는 둘 중 큰쪽으로
		ret.mostFrequent = Math.max(a.mostFrequent, b.mostFrequent);

		// 왼쪽 구간의 오른쪽 끝 숫자와 오른쪽 구간의 왼쪽 끝 숫자가 합쳐지는 경우
		// 이 두 수를 합쳤을 때 mostFrequent 보다 커지는지 확인한다.
		if (a.rightNumber == b.leftNumber) {
			ret.mostFrequent = Math.max(ret.mostFrequent, a.rightFreq + b.leftFreq);
		}

		return ret;
	}

	static class STR {
		int n;
		int [] rangeMin;

		STR(int [] arr) {
			n = arr.length;
			rangeMin = new int[n * 4]; // key point
			init(arr, 1, 0, n-1);
		}

		int init(int[] arr, int node, int left, int right) {
			if (left == right) {
				return rangeMin[node] = arr[left];
			}

			int mid = (left + right) / 2;
			int leftMin = init(arr, node*2, left, mid);
			int rightMin = init(arr, node*2+1, mid+1, right);

			return rangeMin[node] = Math.min(leftMin, rightMin);
		}


	}
}
