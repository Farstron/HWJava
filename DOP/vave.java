package DOP;

import java.util.ArrayDeque;

class Coordinates{
    int X;
    int Y;
    Coordinates(int x, int y) {
        this.X = x;
        this.Y = y;
    }
}
class MinCoordinates{
    int min;
    Coordinates co;
    MinCoordinates(int min, Coordinates co) {
        this.min = min;
        this.co = co;
    }
}
public class vave {
    
    public static void main(String[] args) {
        int[][] map = createMap(6,6);
        addWall(4,3,2,1,map);
        addWall(5,6,2,0,map);
        ArrayDeque<Coordinates> Queue = new ArrayDeque<Coordinates>();
        Queue.add(addCat(2,2,map));
        drawMap(map);
        maping(map,Queue);
        drawMap(map);
        Queue.add(addEscape(7,4,map));
        drawMap(map);
        findWay(map, Queue);
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
            System.out.println(qC.getFirst().X+","+qC.getFirst().Y);
            findMin(map,qC);
            System.out.println(qC.getFirst().X+","+qC.getFirst().Y);
            map[qC.getFirst().Y][qC.getFirst().X]=0;
            // findWay(map,qC);
        }
        map[qC.getFirst().Y][qC.getFirst().X]=0; 
        System.out.println(qC.getFirst().X+","+qC.getFirst().Y);
    }
    public static void findMin(int[][] map, ArrayDeque<Coordinates> qC){
        MinCoordinates mC = new MinCoordinates(map[qC.getFirst().Y][qC.getFirst().X], new Coordinates(qC.getFirst().X, qC.getFirst().Y));
        while(qC.peek()!=null){
            Coordinates temp = new Coordinates(0, 0);
            temp.X = qC.getFirst().X;
            temp.Y = qC.getFirst().Y;
            qC.remove();
            checkUp(temp,map,mC);
            checkRight(temp,map,mC);
            checkDown(temp,map,mC);
            checkLeft(temp,map,mC);
        }
        
        qC.add(mC.co);
    }
    public static void checkUp(Coordinates pos, int[][] map, MinCoordinates mC){
        System.out.println(mC.min+"----"+map[pos.Y+1][pos.X]);
        if (map[pos.Y+1][pos.X] < mC.min && map[pos.Y+1][pos.X]>0){
            mC.min=map[pos.Y+1][pos.X];
            mC.co.X=pos.X;
            mC.co.Y=pos.Y+1;
        }
        
    }
    public static void checkRight(Coordinates pos, int[][] map, MinCoordinates mC){
        if (map[pos.Y][pos.X+1] < mC.min && map[pos.Y][pos.X+1]>0){
            mC.min=map[pos.Y][pos.X+1];
            mC.co.X=pos.X+1;
            mC.co.Y=pos.Y;
        }
    }
    public static void checkDown(Coordinates pos, int[][] map, MinCoordinates mC){
        if (map[pos.Y-1][pos.X] < mC.min && map[pos.Y-1][pos.X]>0){
            mC.min=map[pos.Y-1][pos.X];
            mC.co.X=pos.X;
            mC.co.Y=pos.Y-1;
        }
    }
    public static void checkLeft(Coordinates pos, int[][] map, MinCoordinates mC){
        if (map[pos.Y][pos.X-1] < mC.min && map[pos.Y][pos.X-1]>0){
            mC.min=map[pos.Y][pos.X-1];
            mC.co.X=pos.X-1;
            mC.co.Y=pos.Y;
        }
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

