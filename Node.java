public class Node{

	private int heuristic;
	private char [][] board = new char [8][8];
	private int depth;
	String Move;
	
	public Node(char [][] board)
	{
		this.board = board;
		heuristic = get_evaluation();
	}
	
	public int get_evaluation()
	{
		int x_counter = 0;
		int o_counter = 0;
		
		//Check Evaluation Per Column
		for(int y = 0; y < 8; y++)
		{
			for(int x = 0; x < 7; x++)
			{
				if(this.board[x][y] == 'A' && this.board[x+1][y] == 'A')
					x_counter += 5;
	
				if(this.board[x][y] == 'A' && this.board[x+1][y] == 'A')
					x_counter += 20;

				if(this.board[x][y] == 'A' && this.board[x+1][y] == '_')
					x_counter += 10;
				
				if(this.board[x][y] == 'O' && this.board[x+1][y] == 'O')
					o_counter += 5;
	
				if(this.board[x][y] == 'O' && this.board[x+1][y] == 'O')
					o_counter += 20;

				if(this.board[x][y] == 'O' && this.board[x+1][y] == '_')
					o_counter += 10;
			}
		}
		
		//Check Evaluation Per Column
		for(int y = 0; y < 8; y++)
		{
			for(int x = 0; x < 5; x++)
			{
				if(this.board[x][y] == 'A' && this.board[x+1][y] == 'A'&& this.board[x+2][y] == 'A')
					x_counter += 100;
				
				if(this.board[x][y] == 'A' && this.board[x+1][y] == 'A'&& this.board[x+2][y] == 'A' && this.board[x+3][y] == '_')
					x_counter += 150;
	
				if(this.board[x][y] == 'O' && this.board[x+1][y] == 'A'&& this.board[x+2][y] == 'O')
					o_counter += -100;
				
				if(this.board[x][y] == 'O' && this.board[x+1][y] == 'O'&& this.board[x+2][y] == 'O' && this.board[x+3][y] == '_')
					o_counter += 150;
			}
		}
		
		//Check Evaluation Per Column
		for(int y = 0; y < 8; y++)
		{
			for(int x = 0; x < 4; x++)
			{
				if(this.board[x][y] == 'A' && this.board[x+1][y] == 'A'&& this.board[x+2][y] == 'A' && this.board[x+3][y] == 'X')
					x_counter += 10000;
				
				if(this.board[x][y] == 'O' && this.board[x+1][y] == 'O'&& this.board[x+2][y] == 'O' && this.board[x+3][y] == 'O')
					o_counter += -9999;
			}
		}
		// Check Evaluation Per Row
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 7; y++)
			{
				if(this.board[x][y] == 'A' && this.board[x+1][y] == 'A')
					x_counter += 5;
	
				if(this.board[x][y] == 'A' && this.board[x+1][y] == 'A')
					x_counter += 20;

				if(this.board[x][y] == 'A' && this.board[x+1][y] == '_')
					x_counter += 10;
				
				if(this.board[x][y] == 'O' && this.board[x+1][y] == 'O')
					o_counter += 5;
	
				if(this.board[x][y] == 'O' && this.board[x+1][y] == 'O')
					o_counter += 20;

				if(this.board[x][y] == 'O' && this.board[x+1][y] == '_')
					o_counter += 10;
			}
		}
			
		//Check Evaluation Per Column
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 5; y++)
			{
				if(this.board[x][y] == 'A' && this.board[x][y+1] == 'A'&& this.board[x][y+2] == 'A')
					x_counter += 100;
				
				if(this.board[x][y] == 'A' && this.board[x][y+1] == 'A'&& this.board[x][y+2] == 'A' && this.board[x][y+3] == '_')
					x_counter += 140;
	
				if(this.board[x][y] == 'O' && this.board[x][y+1] == 'A'&& this.board[x][y+2] == 'O')
					o_counter += -100;
				
				if(this.board[x][y] == 'O' && this.board[x][y+1] == 'O'&& this.board[x][y+2] == 'O' && this.board[x][y+3] == '_')
					o_counter += 140;
			}
		}
		
		//Check Evaluation Per Row
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 4; y++)
			{
				if(this.board[x][y] == 'A' && this.board[x][y+1] == 'A'&& this.board[x+2][y] == 'A' && this.board[x+3][y] == 'X')
					x_counter += 10000;
				
				if(this.board[x][y] == 'O' && this.board[x][y+1] == 'O'&& this.board[x+2][y] == 'O' && this.board[x+3][y] == 'O')
					o_counter += -9999;
			}
		}
		
		return x_counter + o_counter;
	}
	
	public String get_move()
	{
		return this.Move;
	}
	
	public char [][] get_board()
	{
		return this.board;
	}
	
	public int get_depth()
	{
		return this.depth;
	}
	
	public char get_board_element(int element, int el)
	{
		return this.board[element][el];
	}
	
	public int get_board_length()
	{
		return this.board.length;
	}
	
}

