package Thuyen;

public class Player {
    private String namePlayer;
    private int wreck;
    private int numberShip;
    private char[][] board;
    private Ship[] ship;
    private char[][] fog;

    public Player(String namePlayer, int wreck, int numberShip, char[][] board, Ship[] ship, char[][] fog) {
        this.namePlayer = namePlayer;
        this.wreck = wreck;
        this.numberShip = numberShip;
        this.board = board;
        this.ship = ship;
        this.fog = fog;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public int getWreck() {
        return wreck;
    }

    public void setWreck(int wreck) {
        this.wreck = wreck;
    }

    public int getNumberShip() {
        return numberShip;
    }

    public void setNumberShip(int numberShip) {
        this.numberShip = numberShip;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
    public void setFog(char[][] fog) {
        this.fog = fog;
    }
    public Ship[] getShip() {
        return ship;
    }

    public void setShip(Ship[] ship) {
        this.ship = ship;
    }

    public char[][] getFog() {
        return fog;
    }

    public void setFog(int row, int col, char kt) {
        this.fog[row][col] = kt;
    }
    public void setBoard(int row, int col, char kt) {
        this.board[row][col] = kt;
    }
    public Boolean winner(){
        if(this.wreck==this.numberShip) return true;
        return false;
    }
}
