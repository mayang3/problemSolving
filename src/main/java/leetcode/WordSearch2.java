package leetcode;


import java.util.*;

public class WordSearch2 {
	public static void main(String[] args) {
		char [][] boards = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
		String [] words = {"oath","pea","eat","rain"};

		WordSearch2 wordSearch2= new WordSearch2();
		System.out.println(wordSearch2.findWords(boards, words));
	}

	public List<String> findWords(char[][] board, String[] words) {
		List<String> li=new ArrayList<String>();
		for(String word : words){
			if(exist(board,word)){
				li.add(word);
			}
		}
		return li;
	}

	public boolean exist(char[][] board, String word) {
		int row=board.length;
		int col=board[0].length;

		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(board[i][j]==word.charAt(0) && dfs(board,i,j,0,word)){
					return true;
				}
			}
		}
		return false;
	}

	public boolean dfs(char[][] board,int i,int j,int count,String word){
		if(count==word.length()){
			return true;
		}

		if(i< 0 || i>=board.length || j<0 || j>= board[0].length || board[i][j]!=word.charAt(count)){
			return false;
		}

		char temp=board[i][j];
		board[i][j]=' ';
		boolean found= dfs(board,i-1,j,count+1,word) ||
			dfs(board,i+1,j,count+1,word) ||
			dfs(board,i,j-1,count+1,word) ||
			dfs(board,i,j+1,count+1,word) ;
		board[i][j]=temp;
		return found;
	}

}
