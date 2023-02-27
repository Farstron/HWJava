package DOP;

import java.util.ArrayDeque;

class Coordinates{
    int X;
    int Y;
    
    Coordinates(){
        this.X = 0;
        this.Y = 0;
    }
    Coordinates(int x, int y) {
        this.X = x;
        this.Y = y;
    }
}
class MinCoordinates{
    int[] min;
    Coordinates[] co;
    static int it = 0;
    MinCoordinates() {
        this.min = new int[5];
        this.co = new Coordinates[5];
        this.co[0] = new Coordinates();
        this.co[1] = new Coordinates();
        this.co[2] = new Coordinates();
        this.co[3] = new Coordinates();
        this.co[4] = new Coordinates();
    }
    // void add(int m, Coordinates c){
    //     if (countMinCoordinates<5){
    //     this.min[countMinCoordinates] = m;
    //     this.co[countMinCoordinates] = c;
    //     countMinCoordinates += 1;}
    //     else{System.out.println("Переполнение!");}
    // }
}
public class vave {
    
    public static void main(String[] args) {
        int[][] map = createMap(6,6);
        System.out.println("Пустая карта");
        drawMap(map);
        addWall(4,3,2,1,map);
        addWall(5,6,2,0,map);
        System.out.println("Карта со стенами");
        drawMap(map);
        ArrayDeque<Coordinates> Queue = new ArrayDeque<Coordinates>();
        Queue.add(addCat(2,2,map));
        System.out.println("Карта с котом и стенами");
        drawMap(map);
        maping(map,Queue);
        System.out.println("Карта с маршрутами");
        drawMap(map);
        Queue.remove();
        Queue.remove();
        Queue.add(addEscape(7,4,map));
        Queue.add(addEscape(7,7,map));
        System.out.println("Карта с выходами");
        drawMap(map);
        findWay(map, Queue);
        System.out.println("Карта с путем");
        drawMap(map);
    }
    public static Coordinates addCat(int x,int y,int[][] map){
        map[y][x]=1;
        Coordinates posCat = new Coordinates(x,y);
        return posCat;
    }
    public static void maping(int[][] map, ArrayDeque<Coordinates> qC){
        if (check(map,0)==true){
            Coordinates nP = qC.removeFirst();
            goUp(nP, qC, map);
            goRight(nP, qC, map);
            goDown(nP, qC, map);
            goLeft(nP, qC, map);
            maping(map,qC);
        }
    }
    public static void goUp(Coordinates pos, ArrayDeque<Coordinates> qC, int[][] map){
        Coordinates temp = new Coordinates(0, 0);
        temp.X = pos.X;
        temp.Y = pos.Y;
        temp.Y=temp.Y+1;
        if (map[temp.Y][temp.X] == 0){
            qC.add(temp);
            map[temp.Y][temp.X] = map[pos.Y][pos.X]+1;
        }
        
    }
    public static void goRight(Coordinates pos, ArrayDeque<Coordinates> qC, int[][] map){
        Coordinates temp = new Coordinates(0, 0);
        temp.X = pos.X;
        temp.Y = pos.Y;
        temp.X=temp.X+1;
        if (map[temp.Y][temp.X] == 0){
            qC.add(temp);
            map[temp.Y][temp.X] = map[pos.Y][pos.X]+1;
        }
    }
    public static void goDown(Coordinates pos, ArrayDeque<Coordinates> qC, int[][] map){
        Coordinates temp = new Coordinates(0, 0);
        temp.X = pos.X;
        temp.Y = pos.Y;
        temp.Y=temp.Y-1;
        if (map[temp.Y][temp.X] == 0){
            qC.add(temp);
            map[temp.Y][temp.X] = map[pos.Y][pos.X]+1;
        }
    }
    public static void goLeft(Coordinates pos, ArrayDeque<Coordinates> qC, int[][] map){
        Coordinates temp = new Coordinates(0, 0);
        temp.X = pos.X;
        temp.Y = pos.Y;
        temp.X=temp.X-1;
        if (map[temp.Y][temp.X] == 0){
            qC.add(temp);
            map[temp.Y][temp.X] = map[pos.Y][pos.X]+1;
        }
    }
    private static boolean check(int[][] map, int toCheckValue)
    {
        boolean test = false;
        for (int yi = 2; yi < map.length; yi+=1){
            for (int xi = 2; xi < map[0].length; xi+=1){
                if (map[yi][xi] == toCheckValue) {
                    test = true;
                    break;
                }
            }
        }
        return test;
    }
    
    
    public static void findWay(int[][] map,ArrayDeque<Coordinates> qC){
        if (map[qC.getFirst().Y+1][qC.getFirst().X] != 1 &&
        map[qC.getFirst().Y-1][qC.getFirst().X] != 1 &&
        map[qC.getFirst().Y][qC.getFirst().X+1] != 1 &&
        map[qC.getFirst().Y][qC.getFirst().X-1] != 1){
            findMin(map,qC);
            map[qC.getFirst().Y][qC.getFirst().X]=0;
            findWay(map,qC);
        }
        map[qC.getFirst().Y][qC.getFirst().X]=0; 
        
    }
    public static void findMin(int[][] map, ArrayDeque<Coordinates> qC){
        MinCoordinates mC = new MinCoordinates();
        while(qC.peek()!=null){
            Coordinates temp = new Coordinates(0, 0);
            temp.X = qC.getFirst().X;
            temp.Y = qC.getFirst().Y;
            qC.remove();
            checkRight(temp,map,mC);
            checkDown(temp,map,mC);
            checkLeft(temp,map,mC);
            checkUp(temp,map,mC);
            for (int i = 0; i<4; i++){
                if (mC.min[i]>0){
                    if (mC.min[4]>mC.min[i] || mC.min[4] == 0){
                        mC.min[4]=mC.min[i];
                        mC.co[4].X=mC.co[i].X;
                        mC.co[4].Y=mC.co[i].Y;
                    }
                }
            }
            
        }
        qC.add(mC.co[4]);
    }
    public static void checkUp(Coordinates pos, int[][] map, MinCoordinates mC){
        
            mC.min[0]=map[pos.Y-1][pos.X];
            mC.co[0].X=pos.X;
            mC.co[0].Y=pos.Y-1;
        
    }
    public static void checkRight(Coordinates pos, int[][] map, MinCoordinates mC){
            mC.min[1]=map[pos.Y][pos.X+1];
            mC.co[1].X=pos.X+1;
            mC.co[1].Y=pos.Y;
    }
    public static void checkDown(Coordinates pos, int[][] map, MinCoordinates mC){
            mC.min[2]=map[pos.Y+1][pos.X];
            mC.co[2].X=pos.X;
            mC.co[2].Y=pos.Y+1;
    }
    public static void checkLeft(Coordinates pos, int[][] map, MinCoordinates mC){
            mC.min[3]=map[pos.Y][pos.X-1];
            mC.co[3].X=pos.X-1;
            mC.co[3].Y=pos.Y;
    }
    public static Coordinates addEscape(int x,int y,int[][] map){
        map[y][x]=-2;
        Coordinates posEsc = new Coordinates(x,y);
        return posEsc;
    }
    public static void drawMap(int[][] map){
        for (int yi = 0; yi < map.length; yi+=1){
            for (int xi = 0; xi < map[0].length; xi+=1){
                System.out.format("%2d | ", map[yi][xi]);
            }
            System.out.println();
        }
        System.out.println(); 
    }
    public static int[][] createMap(int x,int y){
        int[][] map = new int[y+3][x+3];
        for (int yi = 0; yi < y+3; yi+=1){
            for (int xi = 0; xi < x+3; xi+=1){
                if (yi==0) 
                    map[0][xi] = xi;
                if ((yi == 1 && xi !=0) || (xi == 1 && yi !=0) || (yi == y+2 && xi !=0) || (xi == x+2 && yi !=0))
                    map[yi][xi] = -5;
            }
            map[yi][0] = yi;
        }
        return map;
    }
    public static void addWall(int x, int y, int size, int rot, int[][] map){
        for(int i = 0; i < size; i += 1){
            if (rot == 1){ // rot == 1 -> | rot == 2 -> _
                map[y+i][x] = -1;
            }
            else map[y][x+i] = -1;
        }
    }  
}

