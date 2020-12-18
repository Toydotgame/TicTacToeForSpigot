package io.github.Toydotgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TicTacToe implements CommandExecutor {
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player || sender instanceof ConsoleCommandSender) {
			char[][] gameBoard = {
					{' ', '|', ' ', '|', ' '},
					{'-', '+', '-', '+', '-'},
					{' ', '|', ' ', '|', ' '},
					{'-', '+', '-', '+', '-'},
					{' ', '|', ' ', '|', ' '}
			};
			
			if(args.length == 0) {
				printGameBoard(gameBoard, sender);
				sender.sendMessage("Enter your placement (1-9): (/tictactoe move <position>)");
				return true;
			} else if(args.length == 1) {
				return false;
			} else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("move")) {
					if(args[1].matches("^[0-9]*$") && args[1].length() == 1 && args[1] != "0") {
						// Move and positioning input sits here. The position is the variable "args[1]".
						sender.sendMessage("All requirements are met for the move command!");
						
						int playerPos = Integer.parseInt(args[1]);
						
						while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
							System.out.print("Position taken. Please choose a valid one: ");
							playerPos = Integer.parseInt(args[1]);
						}
						
						placePiece(gameBoard, playerPos, "player");
						
//						String result = checkWinner(sender); // Also not needed for now.
						checkWinner(sender); // I've changed the checkWinner() method to work on it's own now! (Thus, the "sender" pass I added)
//						if(result.length() > 0) {
							// Not needed for now. I'm going to keep it just in case.
//						}
						
						Random rand = new Random();
						int cpuPos = rand.nextInt(9) + 1;
						while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
							cpuPos = rand.nextInt(9) + 1;
						}
						placePiece(gameBoard, cpuPos, "cpu");
						
						printGameBoard(gameBoard, sender);
//						if(result.length() > 0) {
							// Copy of this same loop above. checkWinner() works by itself, so it should be fine!
//						}
						sender.sendMessage("Enter your placement (1-9): (/tictactoe move <position>)");
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else if(args.length >= 3) {
				return false;
			}
			
			return true;
		} else {
			return false;
		}
    }
	
	public static void printGameBoard(char[][] gameBoard, CommandSender sender) {
		for(char[] row : gameBoard) {
//			System.out.println(new String(row));
//			sender.sendMessage(new String(row));
			sender.sendMessage(String.valueOf(row));
//			for(char c : row) {
//				System.out.print(c);
//			}
//			System.out.println();
		}
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String user) {
		char symbol = ' ';
		
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch(pos) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static String checkWinner(CommandSender sender) {
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List diag1 = Arrays.asList(1, 5, 9);
		List diag2 = Arrays.asList(7, 5, 3);
		
		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(topRow);
		winningConditions.add(midRow);
		winningConditions.add(botRow);
		winningConditions.add(leftCol);
		winningConditions.add(midCol);
		winningConditions.add(rightCol);
		winningConditions.add(diag1);
		winningConditions.add(diag2);
		
		for(List l : winningConditions) {
			if(playerPositions.containsAll(l)) {
				sender.sendMessage("You won!");
				return "player win";
			} else if(cpuPositions.containsAll(l)) {
				sender.sendMessage("You lost...");
				return "cpu win";
			} else if(playerPositions.size() + cpuPositions.size() == 9) {
				sender.sendMessage("It's a tie!");
				return "tie";
			}
		}
		
		return "";
	}
}
