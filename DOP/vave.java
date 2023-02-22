package DOP;

import java.util.ArrayDeque;

public class vave {
    
    public static void main(String[] args) {
        int[][] map = createMap(6,6);
        // drawMap(map);
        addWall(4,3,2,1,map);
        addWall(5,6,2,0,map);
        addEscape(7,4,map);
        ArrayDeque<Coordinates> Queue = new ArrayDeque<Coordinates>();
        Queue.add(addCat(2,2,map));
        // System.out.println("_______________"+Queue.getFirst().X+","+Queue.getFirst().Y);
        // Coordinates c = Queue.removeFirst();
        // System.out.println("_______________"+c.X+","+c.Y);
        // System.out.println("_______________"+Queue.getFirst().X+","+Queue.getFirst().Y);

        maping(map,Queue);
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
            System.out.println("_______________"+nP.X+","+nP.Y);
            goUp(nP, qC, map);
            goRight(nP, qC, map);
            goDown(nP, qC, map);
            goLeft(nP, qC, map);
            System.out.println("_______________"+qC.getFirst().X+","+qC.getFirst().Y);
            maping(map,qC);
        }
    }
    public static void goUp(Coordinates pos, ArrayDeque<Coordinates> qC, int[][] map){
        Coordinates tempu = pos;
        System.out.println("goUp "+tempu.X+","+tempu.Y);
        tempu.Y=tempu.Y+1;
        System.out.println("goUp "+tempu.X+","+tempu.Y);
        if (map[tempu.Y][tempu.X] == 0){
            qC.add(tempu);
            map[tempu.Y][tempu.X] = map[pos.Y][pos.X]+1;
        }
        
    }
    public static void goRight(Coordinates pos, ArrayDeque<Coordinates> qC, int[][] map){
        Coordinates tempr = pos;
        System.out.println("goRight "+tempr.X+","+tempr.Y);
        tempr.X=tempr.X+1;
        System.out.println("goRight "+tempr.X+","+tempr.Y);
        if (map[tempr.Y][tempr.X] == 0){
            qC.add(tempr);
            map[tempr.Y][tempr.X] = map[pos.Y][pos.X]+1;
        }
    }
    public static void goDown(Coordinates pos, ArrayDeque<Coordinates> qC, int[][] map){
        Coordinates tempd = pos;
        System.out.println("goDown "+tempd.X+","+tempd.Y);
        tempd.Y=tempd.Y-1;
        System.out.println("goDown "+tempd.X+","+tempd.Y);
        if (map[tempd.Y][tempd.X] == 0){
            qC.add(tempd);
            map[tempd.Y][tempd.X] = map[pos.Y][pos.X]+1;
        }
    }
    public static void goLeft(Coordinates pos, ArrayDeque<Coordinates> qC, int[][] map){
        Coordinates temp = pos;
        System.out.println("goLeft "+temp.X+","+temp.Y);
        temp.X=temp.X-1;
        System.out.println("goLeft "+temp.X+","+temp.Y);
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
    public static void findWay(int[][] map){

    }
    public static void addEscape(int x,int y,int[][] map){
        map[y][x]=-2;
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

class Coordinates{
    int X;
    int Y;
    Coordinates(int x, int y) {
        this.X = x;
        this.Y = y;
    }
}