package Thuyen;
import java.lang.Math;
public class Boards {
    public static char[][] createBoard(char water){
        char[][] gameBoard = new char[15][15];
        for(int i=1;i<=10;i++){
            gameBoard[0][i]=(char)(48+i);
            gameBoard[i][0]= (char) (65+i-1);
        }
        gameBoard[0][10]='1';
        gameBoard[0][11]='0';
        for(int i=1;i<=10;i++){
            for(int j=1;j<=10;j++)
                gameBoard[i][j]=water;
        }
        return gameBoard;
    }
    public static Boolean checkPoint(String coorDinate){
        char [] toaDo = coorDinate.toCharArray();
        return (toaDo.length == 2 && toaDo[0] <= 'J' && toaDo[0] >= 'A' && toaDo[1] <= '9' && toaDo[1] >= 1) ||
                (toaDo.length == 3 && toaDo[0] <= 'J' && toaDo[0] >= 'A' && toaDo[1] == '1' && toaDo[2] == '0');
    }
    public static int row(String coorDinate){
        char [] toaDo = coorDinate.toCharArray();
        return toaDo[0]-65+1;
    }
    public static int col(String coorDinate){
        char [] toaDo = coorDinate.toCharArray();
        int a;
        if(toaDo.length==3) a=10;
        else a = toaDo[1]-48;
        return a;
    }
    public static Boolean checkSetShip(Ship ship, char[][] board, String coorDinate1, String coorDinate2){
        char [] toaDo1 = coorDinate1.toCharArray();
        char [] toaDo2 = coorDinate2.toCharArray();
        if(checkPoint(coorDinate1)&&checkPoint(coorDinate2)){
            if (toaDo1[0] == toaDo2[0]) {
                int length, t1, t2;
                if(toaDo1.length==3) t1=10;
                else t1=toaDo1[1]-48;
                if(toaDo2.length==3) t2=10;
                else t2=toaDo2[1]-48;
                length = Math.abs(t2-t1)+1;
                if (length == ship.getLengthShip()){
                    int a = row(coorDinate1);
                    int b = col(coorDinate1);
                    int c = col(coorDinate2);
                    if(c<b){
                        int temp=b;
                        b=c;
                        c=temp;
                    }
                    for(int i=b;i<=c;i++){
                        if(board[a][i]!='~')
                            return false;
                    }
                    return true;
                }
                else return false;
            } else if (toaDo1[1] == toaDo2[1]&& toaDo1.length==toaDo2.length) {
                int length = Math.abs(toaDo1[0] - toaDo2[0])+1;
                if (length == ship.getLengthShip()){
                    int a = col(coorDinate1);
                    int b = row(coorDinate1);
                    int c = row(coorDinate2);
                    if(c<b){
                        int temp=b;
                        b=c;
                        c=temp;
                    }
                    for(int i=b;i<=c;i++){
                        if(board[i][a]!='~')
                            return false;
                    }
                    return true;
                }
                else return false;
            } else return false;
        }
        else return false;
    }
    public static void setShip(char[][] board, String coorDinate1, String coorDinate2) {
        char[] toaDo1 = coorDinate1.toCharArray();
        char[] toaDo2 = coorDinate2.toCharArray();
        if (toaDo1[0] == toaDo2[0]) {
            int a = row(coorDinate1);
            int b = col(coorDinate1);
            int c = col(coorDinate2);
            if (c < b) {
                int temp = b;
                b = c;
                c = temp;
            }
            for (int i = b; i <= c; i++) {
                board[a][i] = 'S';
            }
        } else if (toaDo1[1] == toaDo2[1] && toaDo1.length == toaDo2.length) {
            int a = col(coorDinate1);
            int b = row(coorDinate1);
            int c = row(coorDinate2);
            if (c < b) {
                int temp = b;
                b = c;
                c = temp;
            }
            for (int i = b; i <= c; i++) {
                board[i][a] = 'S';
            }
        }
    }
    public static Boolean checkShoot(String coorDinate, Player player, Player enermy){
        int roww = row(coorDinate);
        int coll = col(coorDinate);
        return enermy.getBoard()[roww][coll] == 'S' && player.getBoard()[roww][coll] == '~';
    }
    public static void shoot(String coorDinate, Player player, Player enermy){
        int roww = row(coorDinate);
        int coll = col(coorDinate);
        if(enermy.getBoard()[roww][coll]=='~'&&player.getFog()[roww][coll]=='~'){
            System.out.println("Khong trung!");
            player.setFog(roww, coll, 'O');
            showBoard(player.getFog());
        }
        else if(enermy.getBoard()[roww][coll]=='S'&&player.getBoard()[roww][coll]=='~'){
            System.out.println("Trung!");
            for (int i=1;i<=5;i++) {
                enermy.getShip()[i].testShot(coorDinate, player, enermy);
            }
            for (int i=1;i<=5;i++) {
                if(enermy.getShip()[i].testShank()){
                    enermy.getShip()[i].setShot(true);
                    enermy.setWreck(player.getWreck()+1);
                }
            }
        }
        else System.out.println("Ban da tung ban diem nay :((");
    }
     public static void showBoard(char[][] board){
        for (int i=0;i<=10;i++){
            for (int j=0;j<=10;j++){
                if(j<10)
                    System.out.printf("%c ", board[i][j]);
                else System.out.printf("%c", board[i][j]);
            }
            if(i==0) System.out.printf("%c", board[i][11]);
            System.out.println();
        }
    }

}
