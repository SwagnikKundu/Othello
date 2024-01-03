package Othello;

public class Board{

    private static char p1Color,p2Color;
    private static int boardSize=8;
    private static char[][] board;
    private static int p1Count=2;
    private static int p2Count=2;
    private static int[] xDir={-1,0,1,-1,1,-1,0,1};
    private static int[] yDir={1,1,1,0,0,-1,-1,-1};



    private static final char BLANK=' ';

    public static final int INCOMPLETE=4;
    public static final int PLAYER1WINS=1;
    public static final int PLAYER2WINS=2;
    public static final int DRAW=3;
    public static final int INVALIDMOVE=5;


    public Board(char p1Color,char p2Color){
        board=new char[boardSize][boardSize];
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(i==3 && j==3)
                    board[i][j]=p1Color;
                else if(i==3 && j==4)
                    board[i][j]=p2Color;
                else if(i==4 && j==3)
                    board[i][j]=p2Color;
                else if(i==4 && j==4)
                    board[i][j]=p1Color;
                else
                    board[i][j]=BLANK;

            }
        }

        this.p1Color=p1Color;
        this.p2Color=p2Color;
    }

    public static void printNumber() {
    	System.out.println("No of disks of "+p1Color+" is: "+p1Count);
    	System.out.println();
    	System.out.println("No of disks of "+p2Color+" is: "+p2Count);
    	System.out.println();
    	}


    public static void printBoard(){
        System.out.println("__________________________________");
        for(int i=0;i<boardSize;i++){
        	System.out.print(i+1+"|");
            for(int j=0;j<boardSize;j++)
                System.out.print(" "+board[i][j]+" |");
            System.out.println();
            System.out.println("__________________________________");
            
        }
        System.out.println(" | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |");
       
    }





    public int move(char color,int x,int y){
        
        // Check move in bound
        if(x<0 || x>=boardSize || y<0 || y>=boardSize || board[x][y]!=BLANK)
            return INVALIDMOVE;

        boolean isValid=check(color,x,y);
        if(!isValid)
            return INVALIDMOVE;
        else if(p1Count+p2Count==8*8 || p1Count<=0 || p2Count<=0){
            if(p1Count!=p2Count && p1Count>p2Count)
                return PLAYER1WINS;
            else if(p1Count!=p2Count && p1Count<p2Count)
                return PLAYER2WINS;
            else
                return DRAW;
        }
        
        return INCOMPLETE;

    }


    public static boolean check(char color,int x,int y){
        boolean isValid=false;

        for(int i=0;i<xDir.length;i++){
            int xMove=x+xDir[i];
            int yMove=y+yDir[i];
            int count=0;

            while(xMove>=0 && xMove<boardSize && yMove>=0 && yMove<boardSize){
                // blank location
                if(board[xMove][yMove]==' ')
                    break;
                
                // neighbour present of another color
                else if(board[xMove][yMove]!=color){
                    count++;
                    xMove+=xDir[i];
                    yMove+=yDir[i];
                }

                // encounter of same color and other color present in between
                else{
                    if(count>0){
                        if(color==p1Color){
                            p1Count+=count;
                            p2Count-=count;
                        }
                        else{
                            p2Count+=count;
                            p1Count-=count;
                        }

                        isValid=true;
                        int convertX=xMove-xDir[i];
                        int convertY=yMove-yDir[i];
                        //Convert all color in between to same color    
                        while(convertX!=x || convertY!=y){
                            board[convertX][convertY]=color;
                            convertX-=xDir[i];
                            convertY-=yDir[i];
                        }
                    }
                    break;
                }

                
            }

        }
        if(isValid) {
            board[x][y]=color;
            if(color==p1Color)
            	p1Count++;
            else
            	p2Count++;
        }
            
        return isValid;
    }

}