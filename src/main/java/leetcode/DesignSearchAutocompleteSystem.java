package leetcode;

import java.util.*;

public class DesignSearchAutocompleteSystem {
	static class AutocompleteSystem {
		StringBuilder sb = new StringBuilder();
		Trie trie = new Trie();
		TrieNode cur;

		public AutocompleteSystem(String[] sentences, int[] times) {
			for (int i = 0; i < sentences.length; i++) {
				String s = sentences[i];
				int time = times[i];

				trie.insert(s, time);
			}
		}

		public List<String> input(char c) {
			if (c == '#') {
				if (sb.length() > 0) {
					trie.insert(sb.toString(), 1);
					sb.delete(0, sb.length());
					this.cur = null;
				}

				return new ArrayList<>();
			} else {
				sb.append(c);
			}

			TrieNode trieNode = trie.search(sb.toString());

			if (trieNode == null) {
				return new ArrayList<>();
			}

			TreeMap<Integer, TreeSet<String>> treeMap = trieNode.treeMap;
			List<String> res = new ArrayList<>();

			for (TreeSet<String> treeSet : treeMap.values()) {
				res.addAll(treeSet);

				if (res.size() > 3) {
					break;
				}
			}

			this.cur = trieNode;

			if (res.size() > 3) {
				res = res.subList(0, 3);
			}

			return res;
		}

		static class TrieNode {
			TrieNode [] children = new TrieNode[27];
			Map<String, Integer> countMap = new HashMap<>();
			TreeMap<Integer, TreeSet<String>> treeMap = new TreeMap<>((o1, o2) -> o2-o1);
		}

		static class Trie {
			TrieNode root = new TrieNode();

			void insert(String s, int time) {
				TrieNode cur = root;

				for (int i = 0; i < s.length(); i++) {
					int idx = getIndex(s, i);

					if (cur.children[idx] == null) {
						cur.children[idx] = new TrieNode();
					}

					Map<String, Integer> countMap = cur.children[idx].countMap;
					TreeMap<Integer, TreeSet<String>> treeMap = cur.children[idx].treeMap;

					Integer count = countMap.get(s);

					if (count != null && treeMap.containsKey(count)) {
						treeMap.get(count).remove(s);

						if (treeMap.get(count).size() == 0) {
							treeMap.remove(count);
						}
					}

					countMap.merge(s, time, Integer::sum);

					if (!treeMap.containsKey(countMap.get(s))) {
						treeMap.put(countMap.get(s), new TreeSet<>());
					}

					treeMap.get(countMap.get(s)).add(s);

					cur = cur.children[idx];
				}
			}

			TrieNode search(String s) {
				TrieNode cur = this.root;

				for (int i = 0; i < s.length(); i++) {
					int idx = getIndex(s, i);

					if (cur.children[idx] == null) {
						return null;
					}

					cur = cur.children[idx];
				}

				return cur;
			}

			private int getIndex(String s, int i) {
				if (s.charAt(i) == ' ') {
					return 26;
				}

				return s.charAt(i) - 'a';
			}
		}
	}

	public static void main(String[] args) {
		String [] s = {"uqpewwnxyqxxlhiptuzevjxbwedbaozz","ewftoujyxdgjtazppyztom","pvyqceqrdrxottnukgbdfcr","qtdkgdbcyozhllfycfjhdsdnuhycqcofaojknuqqnozltrjcabyxrdqwrxvqrztkcxpenbbtnnnkfhmebj","jwfbusbwahyugiaiazysqbxkwgcawpniptbtmhqyrlxdwxxwhtumglihrgizrczv","cfptjitfzdcrhw","aitqgitjgrcbacgnaasvbouqsqcwbyskkpsnigtfeecmlkcjbgduban","utsqkmiqqgglufourfdpgdmrkbippffacwvtkpflzrvdlkdxykfpkoqcb","ethtbdopotpamvrwuomlpahtveyw","jiaqkaxovsqtkpdjfbkajpvpyetuoqwnrnpjdhoojbsdvneecsdvgqpyurmsvcy","j","btbnuplyeuccjbernsfbnveillrwdbqledwvpmvdbcugkurrkabtpykhlcogeszclyfuquafouv","hndjzblegevtfkgbjttektox","gtvnlninpvenapyfgmsjdisfnmiktitrutctawosjflvzfkbegnprixzqwzcyhoovsivuwmofsveqkyosowuyamuvy","sawrirvrfrbfagreahrioaombukmdwztbpggnxd","mgdcwptvbvhzyvvumvbjjn","otjvvkegwleyyxtghwgfmlsqlhrlibdvqfinyyebotjpwoaejhtornfgikmifdmwswbqgwhcbzuhrpajxuqicegcptszct","zlondsttyvnnnnxjtoqnlktitwzurissczzbyfsbgpoawodwjpsmavaugnhqtsbeixwl","yehvdehbtmwqkmcjmvpivfzqvevkotwzvjoyfvp","bjximtpayjdcxbrnksbtfnpynzaygygdflowewprqngdadzdhxcpgapjejojrkzrutgcsfpfvpluagniqimfqddldxqiw","bysyrxfykivyauysytgxfhqcrxliulahuizjvozpywrokxujhzpauxwufcxiitukljiiclatfrspqcljjoxpxziumstnhqr","uxtvutlgqapyfltiulwrplesmtowzoyhhjhzihatpuvmutxqgxfawpwypedbz","jzgsdjdawrqfladolduldhpdpagmvllvzamypuqlrpbmhxxadqaqrqavtxeghcyysjynovkiyjtvdluttodtmtocajgttmv","mbijfkmepalhdiubposdksdmmttxblkodcdrxbnxaqebnwliatnxpwaohbwkidia","ljggggbyxwrwanhjonoramexdmgjigrtpz","cqfvkutpipxjepfgsufonvjtotwfxyn","kvseesjazssavispavchdpzvdhibptowhyrrshyntpwkez","nveuzbaosuayteiozmnelxlwkrrrjlwvhejxhupvchfwmvnqukphgoacnazuoimcliubvhv","uwrpwhfdrxfnarxqpkhrylkwiuhzubjfk","bniyggdcloefwy","ihranmhbsahqjxesbtmdkjfsupzdzjvdfovgbtwhqfjdddwhdvrnlyscvqlnqpzegnvvzyymrajvso","lscreasfuxpdxsiinymuzybjexkpfjiplevqcjxlm","uwgnfozopsygnmptdtmmuumahoungpkodwxrcvfymqpeymaqruayvqqgoddgbnhemtsjifhxwiehncswxzrghf","nyfbxgcpfrzyqwfjzgmhuohjhrjizsyjqgmertmooeiaadcmiuyyylpcosnweoyydeauazhzbeaqn","tpylrxbwnkrfxckfdlvrbytaezuzmyscpvruthuvbxjenkeolvqsrjqzizyclzmqtjvnamdansmzyspcfghfprorqprua","nhldlmxpuckxeekipkrzugatjiivtazjbjyxokksyueyjbgmrovbckbxqcqefaiavzsarbbypgmpxe","sylraqsd","xr","xkzpxkhrucyatpatkigvntohihibyisyqtkjdhatdvyvxbjttz","nvnz","blzddwxphkgqfsfzfclwytstpvpzgcdeggdwzukzirscfzcteeuqbmmrfxcnokbbyxkqrxtjfarcefiynwfmy","inuxmuhtdwpuvyludwtokhtalxbuccepsayrjycbcwbtnfholjvkmypodv","awwillrm","xznodxngrstjrwqzmlmigpw","khlxjdtictufdfbkgfusdtaaeuspbbfmtjodflgqofzlqnulkdztflm","nlngmckslyqzjiyiexbropbxnynjcstziluewypboqhqndqsxhtnosrgrameajovsclrgwqjgnztvxrkhwnxkfrf","yroadxhxyacaexrwju","ujxlbpcbxdqrvubifnpzjmmkolyljzjhdegaiowaal","tnfnjgtxbckbpyplucprxcqzhrfjimylmlhdglntfydepltjvklyxesndzuubienhvuaqc","ouedhtkpkg","ygchsrrubucqffewifsxaefwocfaiiupqbomktvrcddggqfgnaycstpccwtbheyaqwhosxajqeqqxzyjsfng","jqqgpjvfkgjh","csowoazaiyejgyixszqmtctpzlkccccqregyhtvxccvrpkupwcyhqatxscevzdfbdqnuyadiyfnhysddfyxpgqtjiogmxsmzbbkr","dlzxdpchkdaztkqtrjmuujgoiae","plcjkwukkyqluxjbhxsyeaqvviinfuujsafwsquidvmutsrukxwrv","yopqbtpoqhpcktjangauzcvvpephhprpaaclbbkgchlqkrwdsaupeizlwxzcpkchoagmrrkwdkthosmrjefgbumnrjsb"};
		int [] times = {12,9,4,4,1,5,3,4,7,9,2,4,2,3,11,13,1,3,4,10,7,1,9,5,10,14,5,3,2,11,5,14,4,13,11,5,15,8,1,12,2,11,4,2,11,14,9,12,1,7,13,11,7,2,6,10};

		// ["i"],[" "],["a"],["#"],["i"],[" "],["a"],["#"],["i"],[" "],["a"],["#"]]
		AutocompleteSystem autocompleteSystem = new AutocompleteSystem(s, times);
		System.out.println(autocompleteSystem.input('w'));
		System.out.println(autocompleteSystem.input('o'));

	}
}
