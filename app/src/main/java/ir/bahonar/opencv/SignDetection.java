package ir.bahonar.opencv;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SignDetection {
    private Bitmap bm ;
    public boolean isSign = false;
    public SignDetection(Bitmap bm){
        this.bm = bm;
        boolean[][] array = getReds(bm);
        Log.e("len",getAreas(toObjectiveArray(array)).size()+"");
        int allReds = 0;
        for(int i = 0; i < array.length;i++)
            for(int j = 0; j < array[i].length; j++)
                if(array[i][j])
                    allReds++;
        Log.e("all",allReds+"");
    }
    private boolean[][] getReds(Bitmap bm){
        boolean[][] result = new boolean[bm.getWidth()][bm.getHeight()];
        for(int i = 0; i < bm.getWidth();i++)
            for(int j = 0; j < bm.getHeight(); j++)
            {
                Col c = new Col(bm.getPixel(i,j));
                result[i][j] = (c.red > 230) && (c.blue < 15) && (c.green < 15);
            }
        return result;
    }
    private List<List<Pixel>> getAreas(Boolean[][] reds){
        List<List<Pixel>> result = new ArrayList<>();
        for(int i = 0; i < bm.getWidth();i++)
            for(int j = 0; j < bm.getHeight(); j++)
                if(reds[i][j])
                {
                    List<Pixel> area = new ArrayList<>();
                    area.add(new Pixel(COLORS.RED,i,j));
                    reds[i][j] = false;
                    getArea(reds,area,i,j);
                    result.add(area);
                }
        return result;
    }
    private void getArea(Boolean[][] red,List<Pixel> area,int x,int y){
        int nextX = 0;
        int nextY = 0;
        if(red[x-1][y-1]) {
            nextX = -1;
            nextY = -1;
            area.add(new Pixel(COLORS.RED,x+nextX,y+nextY));
            red[x+nextX][y+nextY] = false;
            getArea(red,area,x+nextX,y+nextY);
        }
        if(red[x-1][y]) {
            nextX = -1;
            nextY = 0;
            area.add(new Pixel(COLORS.RED,x+nextX,y+nextY));
            red[x+nextX][y+nextY] = false;
            getArea(red,area,x+nextX,y+nextY);
        }
        if(red[x-1][y+1]) {
            nextX = -1;
            nextY = 1;
            area.add(new Pixel(COLORS.RED,x+nextX,y+nextY));
            red[x+nextX][y+nextY] = false;
            getArea(red,area,x+nextX,y+nextY);
        }
        if(red[x][y-1]) {
            nextX = 0;
            nextY = -1;
            area.add(new Pixel(COLORS.RED,x+nextX,y+nextY));
            red[x+nextX][y+nextY] = false;
            getArea(red,area,x+nextX,y+nextY);
        }
        if(red[x][y+1]) {
            nextX = 0;
            nextY = 1;
            area.add(new Pixel(COLORS.RED,x+nextX,y+nextY));
            red[x+nextX][y+nextY] = false;
            getArea(red,area,x+nextX,y+nextY);
        }
        if(red[x+1][y-1]) {
            nextX = 1;
            nextY = -1;
            area.add(new Pixel(COLORS.RED,x+nextX,y+nextY));
            red[x+nextX][y+nextY] = false;
            getArea(red,area,x+nextX,y+nextY);
        }
        if(red[x+1][y]) {
            nextX = 1;
            nextY = 0;
            area.add(new Pixel(COLORS.RED,x+nextX,y+nextY));
            red[x+nextX][y+nextY] = false;
            getArea(red,area,x+nextX,y+nextY);
        }
        if(red[x+1][y+1]) {
            nextX = 1;
            nextY = 1;
            area.add(new Pixel(COLORS.RED,x+nextX,y+nextY));
            red[x+nextX][y+nextY] = false;
            getArea(red,area,x+nextX,y+nextY);
        }

    }
    void log(boolean[][] array){
        StringBuilder sb = new StringBuilder('\n');
        for(int i = 0; i < array.length;i++) {
            for (int j = 0; j < array[i].length; j++)
                sb.append(array[i][j] ? 'T' : 'F');
            sb.append('\n');
        }
        Log.e("array",sb.toString());
    }
    private Boolean[][] toObjectiveArray(final boolean[][] bool) {
        Boolean[][] result = new Boolean[bool.length][bool[0].length];
        for (int i = 0 ;i < bool.length; i ++)
            for (int j = 0 ;j < bool[i].length; j ++)
                result[i][j] = bool[i][j];
        return result;
    }
}
enum SIGN {STRIGHT,RIGHT,LEFT,TOP,RED}