package Thuyen;

public class Ship {
    private String nameShip;
    private int lengthShip;
    private String coorDinate1, coorDinate2;
    private Boolean shot;
    private int shotPoint;

    public Ship(String nameShip, int lengthShip, String coorDinate1, String coorDinate2, Boolean shot, int shotPoint) {
        this.nameShip = nameShip;
        this.lengthShip = lengthShip;
        this.coorDinate1 = coorDinate1;
        this.coorDinate2 = coorDinate2;
        this.shot = shot;
        this.shotPoint = shotPoint;
    }

    public String getNameShip() {
        return nameShip;
    }

    public void setNameShip(String nameShip) {
        this.nameShip = nameShip;
    }

    public int getLengthShip() {
        return lengthShip;
    }

    public void setLengthShip(int lengthShip) {
        this.lengthShip = lengthShip;
    }

    public String getCoorDinate1() {
        return coorDinate1;
    }

    public void setCoorDinate1(String coorDinate1) {
        this.coorDinate1 = coorDinate1;
    }

    public String getCoorDinate2() {
        return coorDinate2;
    }

    public void setCoorDinate2(String coorDinate2) {
        this.coorDinate2 = coorDinate2;
    }

    public Boolean getShot() {
        return shot;
    }

    public void setShot(Boolean shot) {
        this.shot = shot;
    }

    public int getShotPoint() {
        return shotPoint;
    }

    public void setShotPoint(int shotPoint) {
        this.shotPoint = shotPoint;
    }
    public Boolean testShank(){
        if(this.shotPoint==this.lengthShip)
            return true;
        return false;
    }
    public void testShot(String coorDinate, Player player, Player enermy){
        char[] toaDo1 = coorDinate1.toCharArray();
        char[] toaDo2 = coorDinate2.toCharArray();
        char[] toaDo = coorDinate.toCharArray();
        if (toaDo1[0] == toaDo2[0]) {
            int a = Boards.row(coorDinate1);
            int b = Boards.col(coorDinate1);
            int c = Boards.col(coorDinate2);
            if (c < b) {
                int temp = b;
                b = c;
                c = temp;
            }
            int t1 = Boards.row(coorDinate);
            int t2 = Boards.col(coorDinate);
            for (int i = b; i <= c; i++) {
                if(a==t1&&i==t2 && enermy.getBoard()[t1][t2]=='S' && player.getFog()[t1][t2]=='~'){
                    player.setFog(t1, t2, 'X');
                    this.shotPoint++;
                }
            }
        } else if (toaDo1[1] == toaDo2[1] && toaDo1.length == toaDo2.length) {
            int a = Boards.col(coorDinate1);
            int b = Boards.row(coorDinate1);
            int c = Boards.row(coorDinate2);
            if (c < b) {
                int temp = b;
                b = c;
                c = temp;
            }
            int t1 = Boards.row(coorDinate);
            int t2 = Boards.col(coorDinate);
            for (int i = b; i <= c; i++) {
                if(t2==a && t1==i && enermy.getBoard()[t1][t2]=='S') {
                    player.setFog(t1, t2, 'X');
                    this.shotPoint++;
                }
            }
        }
    }
}