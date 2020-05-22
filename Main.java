import java.util.Scanner;

public class Main {
	
	public static void main(String args[])
	{
		boolean winner = true;
		boolean valid = true;
		boolean good_move;
		char result;
		String Agent = ("Agent vs. Opponent");
		String Opponent = ("Opponent vs Agent");
		String Score_board;
		char [][] def = new char[8][8];
		String [] Agent_Answers = new String[64];
		String [] Opponent_Answers = new String[64];
		int turn, move_row, move_column, order;										// Alternate Turns Between Agent and Opponent
		String Move;
		int Round = 1;
		
		// Initialize Opponent Answers with Spaces
		for(int i = 0; i < Agent_Answers.length; i++)
		{
			Agent_Answers[i] = " ";
			Opponent_Answers[i] = " ";
		}
		
		// Initialize board
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				def[i][j] = '_';
			}
		}
		
		System.out.println("Who goes first, A for Agent, O for opponent");
		Scanner sc = new Scanner(System.in);
		char c = sc.next().charAt(0); 
		
		System.out.println("\n");
		
		// Check for Valid Input
		while(valid)
		{
			if(c == 'A' || c =='O')
				valid = false;
			else
			{
				System.out.println("Invalid Character please choose again: A for Agent, O for opponent");
				sc = new Scanner(System.in);
				c = sc.next().charAt(0);
			}
		}
		
		// Print Board with Score Board
		if(c == 'A')
		{
			Score_board = Agent;
			turn = 0;
			order = 0;
			print_board(def, Score_board, order, Round, Agent_Answers, Opponent_Answers);
		}
		
		else
		{
			Score_board = Opponent;
			turn = 1;
			order = 0;
			print_board(def, Score_board, order, Round, Agent_Answers, Opponent_Answers);
		}
		
		int Agent_counter = 0;
		int Opponent_counter = 0;
		int turn_counter = 0;
		
		// Start Game
		while (winner)
		{
			// Agent Move
			if(turn == 0)
			{
				/*
				 * Node answer = Alpha_Beta(current);
				 * 
				 * move_row = answer.get_move().charAt(0) - 97							// Convert from Ascii to actual row 
				 * move_column = ((int) answer.get_move().charAt(1)) - 48;				// Convert from Ascii to actual column
				 * Agent_counter++;
				 * def[move_row][move_column-1] = 'X';
				 * System.out.println("\n\nAgent Chose this move " + Move + "\n"); 
				 * turn = 1;
				 */
				
				System.out.println("\nChoose Agents Next move");
				Scanner scanner = new Scanner(System.in);
				Move = scanner.nextLine();
				Agent_Answers[Agent_counter] = Move;					// Put Answer in Agent Array
				
				move_row = Move.charAt(0) - 97;							// Convert from Ascii to actual row 
				move_column = ((int) Move.charAt(1)) - 48;				// Convert from Ascii to actual column
				
				good_move = ValidMove(def, move_row, move_column-1);
				while(good_move == false)
				{
					System.out.println("\nError Choose Open Spot: ");
					Scanner scanner2 = new Scanner(System.in);
					Move = scanner2.nextLine();
					Agent_Answers[Agent_counter] = Move;				// Put Answer in Agent Array
					
					move_row = Move.charAt(0) - 97;						// Convert from Ascii to actual row 
					move_column = (((int) Move.charAt(1)) - 48);		// Convert from Ascii to actual column
					good_move = ValidMove(def, move_row, move_column-1);
				}
				
				Agent_counter++;
				
				def[move_row][move_column-1] = 'X';
				
				System.out.println("\n\nAgent Chose this move " + Move + "\n"); 
				turn = 1;
			}
			
			// Opponent Move
			else if(turn == 1)
			{
				System.out.println("\nChoose Opponent Next move: ");
				Scanner scanner1 = new Scanner(System.in);
				Move = scanner1.nextLine();
				Opponent_Answers[Opponent_counter] = Move;				// Put Answer in Agent Array
				
				move_row = Move.charAt(0) - 97;							// Convert from Ascii to actual row 
				move_column = ((int) Move.charAt(1)) - 48;				// Convert from Ascii to actual column
				
				good_move = ValidMove(def, move_row, move_column-1);	// Check to see if Move is Valid
				while(good_move == false)
				{
					System.out.println("\nError Choose Open Spot: ");
					Scanner scanner2 = new Scanner(System.in);
					Move = scanner2.nextLine();
					Agent_Answers[Agent_counter] = Move;				// Put Answer in Agent Array
					
					move_row = Move.charAt(0) - 97;						// Convert from Ascii to actual row 
					move_column = (((int) Move.charAt(1)) - 48);		// Convert from Ascii to actual column
					good_move = ValidMove(def, move_row, move_column-1);
				}
				
				Opponent_counter++;
				
				def[move_row][move_column-1] = 'O';
				
				turn = 0;
			}
			
			// Check to see if the Game Over after 4 moves
			if(Round >= 4)
			{
				result = gamewinner(def);
				
				if(result == 'X')
				{
					System.out.println("\nAgent Has Won the Game!");
					winner = false;
				}
				
				else if(result == 'O')
				{
					System.out.println("\nOpponent Has Won the Game!");
					winner = false;
				}
				
			}
			
			// If there are no more spaces left on the board
			if(Round == 32)
			{
				System.out.println("Draw!");
				return;
			}
			
			// Make sure the Agent and the Opponent both make a move before incrementing the turn
			turn_counter++;
			if(turn_counter == 2)
			{
				turn_counter = 0;
				Round++;
			}
			
			print_board(def, Score_board, turn, Round, Agent_Answers, Opponent_Answers);
			
		}	
  
	}
	
	
	static boolean ValidMove(char board[][], int x, int y)
	{
		boolean validmove = false;
		
		// Check to see if Spot is open
		if(board[x][y] == '_')
			validmove = true;
		
		return validmove;
	}
	
	static char gamewinner(char board[][])
	{
		// Check to see if Agent Wins in Column
		for(int y = 0; y < 8; y++)
		{
			for(int x = 0; x < 5; x++)
			{
				if(board[x][y] == 'X' && board[x+1][y] == 'X' && board[x+2][y] == 'X' && board[x+3][y] == 'X')
				{
					return 'X';
				}
			}
		}
		
		// Check to see if Opponent Wins in Column
		for(int y = 0; y < 8; y++)
		{
			for(int x = 0; x < 5; x++)
			{
				if(board[x][y] == 'O' && board[x+1][y] == 'O' && board[x+2][y] == 'O' && board[x+3][y] == 'O')
					{
						return 'O';
					}
			}
		}
		
		// Check to see if Agent Wins in Row
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 5; y++)
			{
				if(board[x][y] == 'X' && board[x][y+1] == 'X' && board[x][y+2] == 'X' && board[x][y+3] == 'X')
				{
					return 'X';
				}
			}
		}
		
		// Check to see if Opponent Wins in Row
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 5; y++)
			{
				if(board[x][y] == 'O' && board[x][y+1] == 'O' && board[x][y+2] == 'O' && board[x][y+3] == 'O')
				{
					return 'O';
				}
			}
			
		}	
			
		// If No Winner then return N
		return 'N';
	}
	

	int ascii (char asci)
	{
		int asc = (int) asci;
		return asc;
	}
	
	public static void print_board(char board[][], String Score_board, int order, int round, String [] Agent_Answers, String [] Opponent_Answers)
	{
		int ascii = 65;
		int x = 1;
		
		System.out.print("  ");
		
		// Print out 1-9
		while(x < 9)
		{
			System.out.print(x + " ");
			x++;
		}
		
		// Print out Score headline
		System.out.println("    " + Score_board);
		
		// Print out board plus moves
		for(int i = 0; i < 8; i++)
		{
			char asc = (char)ascii;
			System.out.print(asc + " ");
			
			for(int j = 0; j < 8; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			
			if (i < round)
			{
				if(order == 0)
					System.out.printf( "      " + (i+1) + ".  " + Agent_Answers[i] + " " + Opponent_Answers[i]);
					
				else if(order == 1)
					System.out.printf( "      " + (i+1) + ".  " + Opponent_Answers[i] + " " + Agent_Answers[i]);
				
			}
			
			System.out.println();
			ascii++;
		}
	
	}
}


