package Thuyen;

import java.util.Scanner;

public class MainBattle {
    private static void menuGame() {
        while (true) {
            System.out.println("Chao mung den voi tro choi \"Sea Battle\"");
            System.out.println("1. Bat dau");
            System.out.println("2. Thoat");
            Scanner sc = new Scanner(System.in);
            int press = sc.nextInt();
            if (press == 1) Start();
            else if (press == 2) break;
            else System.out.println("Ban da nhap sai cu phap!\nMoi nhap lai!");
        }
    }
    private static void Start(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Moi nguoi choi thu nhat nhap ten: ");
        String namePlayer = sc.nextLine();
        char[][] board1 = Boards.createBoard('~');
        char[][] fog = Boards.createBoard('~');
        String[] ships = new String[6];
        ships[1] = "Thiet Giap Ham";
        ships[2] = "Tau Khu Truc";
        ships[3] = "Tau Ngam";
        ships[4] = "Thuyen Tuan Tra";
        int[] length = new int[6];
        length[1]=5;
        length[2]=4;
        length[3]=3;
        length[4]=length[5]=2;
        Ship[] ship = new Ship[6];
        System.out.printf("Moi %s dat thuyen!\n", namePlayer);
        System.out.println("Chu y: Nhap toa do dung cu phap va sao cho khoang cach 2 diem bang do dai thuyen!");
        Boards.showBoard(board1);
        for (int i=1;i<=5;i++){
            ship[i]=new Ship(ships[i], length[i], "", "", false, 0);
            System.out.printf("Moi nhap toa do cho %s co do dai la %d\n", ships[i], length[i]);
            System.out.println("Toa do diem dau (VD: A1, B4,...): ");
            String coorDinate1 = sc.nextLine();
            System.out.println("Toa do diem cuoi (VD: A1, B4,...): ");
            String coorDinate2 = sc.nextLine();
            while(!Boards.checkSetShip(ship[i], board1, coorDinate1, coorDinate2)){
                System.out.println("Ban da nhap sai cu phap toa do hoac do dai thuyen khong dung!\nMoi nhap lai!");
                System.out.println("Toa do diem dau (VD: A1, B4,...): ");
                coorDinate1 = sc.nextLine();
                System.out.println("Toa do diem cuoi (VD: A1, B4,...): ");
                coorDinate2 = sc.nextLine();
            }
            ship[i]=new Ship(ships[i], length[i], coorDinate1, coorDinate2, false, 0);
            Boards.setShip(board1, coorDinate1, coorDinate2);
            Boards.showBoard(board1);
        }
        Player player1 = new Player(namePlayer, 0, 5, board1, ship, fog);
        System.out.println("Moi nguoi choi thu hai nhap ten: ");
        namePlayer = sc.nextLine();
        board1 = Boards.createBoard('~');
        ship = new Ship[6];
        System.out.printf("Moi %s dat thuyen!\n", namePlayer);
        System.out.println("Chu y: Nhap toa do dung cu phap va sao cho khoang cach 2 diem bang do dai thuyen!");
        Boards.showBoard(board1);
        for (int i=1;i<=5;i++){
            ship[i]=new Ship(ships[i], length[i], "", "", false, 0);
            System.out.printf("Moi nhap toa do cho %s co do dai la %d\n", ships[i], length[i]);
            System.out.println("Toa do diem dau (VD: A1, B4,...): ");
            String coorDinate1 = sc.nextLine();
            System.out.println("Toa do diem cuoi (VD: A1, B4,...): ");
            String coorDinate2 = sc.nextLine();
            while(!Boards.checkSetShip(ship[i], board1, coorDinate1, coorDinate2)){
                System.out.println("Ban da nhap sai cu phap toa do hoac do dai thuyen khong dung!\nMoi nhap lai!");
                System.out.println("Toa do diem dau (VD: A1, B4,...): ");
                coorDinate1 = sc.nextLine();
                System.out.println("Toa do diem cuoi (VD: A1, B4,...): ");
                coorDinate2 = sc.nextLine();
            }
            ship[i]=new Ship(ships[i], length[i], coorDinate1, coorDinate2, false, 0);
            Boards.setShip(board1, coorDinate1, coorDinate2);
            Boards.showBoard(board1);
        }
        Player player2 = new Player(namePlayer, 0, 5, board1, ship, fog);
        System.out.println("Da dat thuyen xong!\nTran chien bat dau!");
        player1.setBoard(4, 5, 'X');
        player1.setFog(4, 5, 'X');
        Boards.showBoard(player1.getBoard());
        Boards.showBoard(player2.getBoard());
        Boards.showBoard(player1.getFog());
        Boards.showBoard(player2.getFog());
        Player player;
        while(true){
            System.out.printf("Luot cua %s!\n", player1.getNamePlayer());
            Boards.showBoard(player1.getFog());
            System.out.println("Nhap toa do muon ban(VD: A1, C4,..): ");
            String coorDinate = sc.nextLine();
            while (!Boards.checkPoint(coorDinate)){
                System.out.println("Toa do ban nhap khong ton tai!\nMoi nhap lai(VD: A1, C4,..): ");
                coorDinate = sc.nextLine();
            }
            int check=0;
            if(Boards.checkShoot(coorDinate, player1, player2)) check=1;
            Boards.shoot(coorDinate, player1, player2);
            Boards.showBoard(player1.getFog());
            Boards.showBoard(player2.getFog());
            if(check==1){
                if(player2.winner())
                    endGame(player1.getNamePlayer());
            }
            else{
                player=player1;
                player1=player2;
                player2=player;
            }
        }
    }
    private static void endGame(String namePlayer){
        System.out.printf("Tau cua doi thu da bi chim het!\nChuc mung %s da chien thang!!!", namePlayer);
    }
    public static void main(String[] args) {
        menuGame();
    }
}
