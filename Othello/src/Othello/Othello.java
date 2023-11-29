package Othello;

import java.util.*;

public class Othello{

    private static Players player1,player2;
    private static Board board;
    private static int playerCount=0;

    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args){

        // Initialize Players
        player1=setPlayer(++playerCount);
        player2=setPlayer(++playerCount);
        
	    while(player2.getName()==player1.getName() ){
	            System.out.println("Enter Player - "+playerCount+" name:");
	            String name=sc.next();
	            player2.setName(name);
	     }
         
	     while(player2.getColor()==player1.getColor() ){
	            System.out.println("Enter Player - "+playerCount+" color:");
	            char color=sc.next().charAt(0);
	            player2.setColor(color);
	      }


        // Create Board
        board=new Board(player1.getColor(),player2.getColor());
        board.printBoard();
        board.printNumber();

        // Start the Game
        boolean p1Turn=true;
        int status=Board.INCOMPLETE;
        while(status==Board.INCOMPLETE || status==Board.INVALIDMOVE){
            if(p1Turn){
                System.out.println("Player - 1 "+player1.getName()+"'s turn for color "+player1.getColor()+" :");
                System.out.println("Enter x value:");
                int x=sc.nextInt();
                System.out.println("Enter y value:");
                int y=sc.nextInt();
                status=board.move(player1.getColor(),x,y);
                if(status==Board.INVALIDMOVE){
                    System.out.println("Invalid Move. Please try again !!!!!!");
                    board.printBoard();
                    board.printNumber();
                    continue;
                }
            }
            else{
                System.out.println("Player - 2 "+player2.getName()+"'s turn for color "+player2.getColor()+" :");
                System.out.println("Enter x value:");
                int x=sc.nextInt();
                System.out.println("Enter y value:");
                int y=sc.nextInt();
                status=board.move(player2.getColor(),x,y);
                if(status==Board.INVALIDMOVE){
                    System.out.println("Invalid Move. Please try again !!!!!!");
                    board.printBoard();
                    board.printNumber();
                    continue;
                }
            }
            p1Turn=!p1Turn;
            board.printBoard();
            board.printNumber();
        }

        if(status==Board.PLAYER1WINS)
            System.out.println("Player - 1 "+player1.getName()+" Wins !!!!!!!!");
        
        if(status==Board.PLAYER2WINS)
            System.out.println("Player - 2 "+player2.getName()+" Wins !!!!!!!!");

        if(status==Board.DRAW)
            System.out.println("Draw !!!!!!!!!");
    }


    public static Players setPlayer(int num){
        String name="";
        char color='\0';
        while(name.isEmpty()){
            System.out.println("Enter Player - "+num+" name:");
            name=sc.next();
            if(name.isEmpty())
                System.out.println("Opps !!!!! Invalid Name");
        }
        while(color=='\0'){
            System.out.println("Enter Player - "+num+" color:");
            color=sc.next().charAt(0);
            if(color=='\0')
                System.out.println("Opps !!!!! Invalid Color");
        }

        Players p=new Players(name,color);
        return p;      

    }
}