package Model;

import View.ChooseLevel;

/**
 * Created by Blossoming on 2016/12/6.
 */
public class Computer {
    private int test=0;
    private int scores;
    private Coord coord=new Coord();
    private int everyPlayerPointScore[][]=new int[19][19];
    private int everyComputerPointScore[][]=new int[19][19];
    /**
     * 计算下棋位置
     * @param role 角色 chess[][]棋盘
     */
    public Coord computePos(int role,int chess[][],int level)
    {
        int x, y, posX, posY;
        //初级
        if(level== ChooseLevel.PRIMARY_LEVEL) {
            while (true) {
                posX = (int) (Math.random() * 10 + Math.random() * 9);
                posY = (int) (Math.random() * 10 + Math.random() * 9);
                if (chess[posX][posY] == Chess.NO_CHESS) {
                    coord.setX(posX);
                    coord.setY(posY);
                    return coord;
                }
            }
        }
        //中级
        else if(level==ChooseLevel.MEDIUM_LEVEL)
        {
            countMaxLines_primary(chess, Chess.WHITE);
            return coord;

        }
        else if(level==ChooseLevel.SENIOR_LEVEL)
        {
            countMaxLines_medium(chess,Chess.WHITE);
            return coord;
        }
        return coord;
    }


    /**********************下面是中级人机*************************************/
    /**
     * 找出分数最大的坐标
     * @param chess 棋盘数组
     * @param role  白棋还是黑棋
     */
    public void countMaxLines_medium(int chess[][],int role)
    {
        Coord playerCoord=new Coord();
        Coord computerCoord=new Coord();
        int x,y;
        for(x=0;x<19;x++)
        {
            for(y=0;y<19;y++)
            {
                if(chess[x][y]==Chess.NO_CHESS) {
                    countEveryPos_medium(x, y, chess, role);
                    everyPlayerPointScore[x][y] = scores;
                    countEveryPos_medium(x, y, chess, Chess.BLACK);
                    everyComputerPointScore[x][y] = scores;
                }
                else
                {
                    everyPlayerPointScore[x][y]=0;
                    everyComputerPointScore[x][y]=0;
                }
            }
        }
        if(findBestPos_medium(everyPlayerPointScore, playerCoord)>=findBestPos(everyComputerPointScore,computerCoord))
        {
            coord=playerCoord;
        }
        else
        {
            coord=computerCoord;
        }
    }

    /**
     * 找到最大分数点的坐标
     * @param a 数组 存储每个点的分数
     * @param c 保存最大分数点的坐标
     * @return
     */
    public int findBestPos_medium(int a[][],Coord c)
    {
        int i,j,max=0;

        for(i=0;i<19;i++)
        {
            for(j=0;j<19;j++)
            {
                if(a[i][j]>max)
                {
                    max=a[i][j];
                    c.setX(i);
                    c.setY(j);
                }
            }
        }
        return max;
    }

    /**
     * 估分函数
     * @param count 每一个方向上的相同颜色的总个数
     * @param i 不同颜色的个数
     */
    public void mark_medium(int count,int i,int countTwo,int role)
    {
        if(count==1)
        {
            scores=scores+10;
        }
        //活二
        else if(count==2&&i==0&&role==Chess.WHITE&&countTwo<=1)
        {
            scores=scores+200;
            //System.out.println("白子活二"+scores);
        }
        else if(count==2&&i==0&&role==Chess.BLACK&&countTwo<=1)
        {
            scores=scores+400;
           // System.out.println("黑子活二"+scores);
        }
        //冲二
        else if(count==2&&i==1)
        {
            scores=scores+50;
           // System.out.println("冲二"+scores);
        }
        //双活二
        else if(count==2&&i==0&&countTwo>1)
        {
            scores=scores+84000;
            System.out.println("双活二"+scores);
        }
        //活三(黑子和白子同时活三的时候选择黑子成)
        else if(count==3&&i==0&&role==Chess.WHITE)
        {
            scores=scores+85000;
            //System.out.println("白子活三"+scores);
        }
        else if(count==3&&i==0&&role==Chess.BLACK)
        {
            scores=scores+86000;
            //System.out.println("黑子活三"+scores);
        }
        //冲三
        else if(count==3&&i==1&&role==Chess.WHITE)
        {
            scores=scores+300;
            System.out.println("白子冲三"+scores);
        }
        else if(count==3&&i==1&&role==Chess.BLACK)
        {
            scores=scores+1000;
            System.out.println("黑子冲三"+scores);
        }
        //白子活四
        else if(count==4&&i==0&&role==Chess.WHITE)
        {
            scores=scores+90000;
            //System.out.println("活四"+scores);
        }
        //黑子活四
        else if(count==4&&i==0&&role==Chess.BLACK)
        {
            scores=scores+91000;
        }
        //冲四
        else if(count==4&&i==1)
        {
            scores=scores+87000;
            //System.out.println("冲四"+scores);
        }
        else if(count==5)
        {
            scores=scores+100000;
        }
    }
    public void basicScore(int x,int y)
    {
        if(x==0||y==0)
        {
            scores=scores+3;
        }
        else
        {
            scores=scores+5;
        }
    }
    /**
     * 计算每个坐标的分数
     * @param x
     * @param y
     * @param chess
     * @param role
     */
    public void countEveryPos_medium(int x,int y,int chess[][],int role)
    {
        scores=0;
        basicScore(x,y);
        //countTwo代表有一个坐标向八个方向数 两个连一起的个数
        int matchRole,startX=x,startY=y,
                count=0,up=0,down=0,left=0,right=0,
                  leftUp=0,leftDown=0,rightUp=0,rightDown=0,countTwo=0;
        if(role==Chess.BLACK)
        {
            matchRole=Chess.WHITE;
        }
        else
        {
            matchRole=Chess.BLACK;
        }
        //竖直方向上判断输赢
        while (true)
        {
            //向上判断
            y--;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else if((x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole)||y<0){
                up++;
                break;
            }
            else
            {
                break;
            }
        }
        y = startY;
        while (true) {
            //向下判断
            y = y + 1;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            }  else if((x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole)||y>19){
                down++;
                break;
            }
            else
            {
                break;
            }
        }
        //此处代表在上下方向有二连 或者 夹了一个空子两边各有一个棋子 并且两端没有其他子挡住
        if(count==2&&(up+down==0))
        {
            countTwo++;
            //System.out.println("出现二连"+countTwo);
        }
        mark_medium(count, up + down, countTwo,role);

        //水平方向判断输赢
        x = startX;
        y = startY;
        count = 0;
        while (true) {
            //向左判断
            x--;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            }else if((x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole)||x==0){
                left++;
                break;
            }
            else
            {
                break;
            }

        }
        x = startX;
        while (true) {
            //向右判断
            x++;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            }else if((x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole)||x>19){
                right++;
                break;
            }
            else
            {
                break;
            }
        }
        if(count==2&&(left+right==0))
        {
            countTwo++;
            //System.out.println("出现二连"+countTwo);
        }
        mark_medium(count, left + right, countTwo,role);

        //右倾斜方向判断输赢
        x = startX;
        y = startY;
        count = 0;
        while (true) {
            //向左上判断
            y--;
            x--;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else if((x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole)||x<0||y<0){
                leftUp++;
                break;
            }
            else
            {
                break;
            }
        }
        x = startX;
        y = startY;
        while (true) {
            //向右下判断
            x++;
            y++;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else if((x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole)||x>19||y>19){
                rightDown++;
                break;
            }
            else
            {
                break;
            }
        }
        if(count==2&&(leftUp+rightDown==0))
        {
            countTwo++;
           // System.out.println("出现二连"+countTwo);
        }
        mark_medium(count, leftUp + rightDown, countTwo,role);

        //左倾斜方向判断
        x = startX;
        y = startY;
        count = 0;
        while (true) {
            //向左下判断
            x--;
            y++;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else if((x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole)||x<0||y>19){
                leftDown++;
                break;
            }
            else
            {
                break;
            }
        }
        x = startX;
        y = startY;
        while (true) {
            //向右上判断
            x++;
            y--;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            }else if((x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole)||x>19||y<0){
                rightUp++;
                break;
            }
            else
            {
                break;
            }
        }
        if(count==2&&(leftDown+rightUp==0))
        {
            countTwo++;
           // System.out.println("出现二连"+countTwo);
        }
        mark_medium(count, leftDown + rightUp, countTwo,role);
    }



    /*********************下面是初级人机*************************************/
    /**
     * 找出分数最大的坐标
     * @param chess 棋盘数组
     * @param role  白棋还是黑棋
     */
    public void countMaxLines_primary(int chess[][],int role)
    {
        Coord playerCoord=new Coord();
        Coord computerCoord=new Coord();
        int x,y;
        for(x=0;x<19;x++)
        {
            for(y=0;y<19;y++)
            {
                if(chess[x][y]==Chess.NO_CHESS) {
                    countEveryPos_primary(x, y, chess, role);
                    everyPlayerPointScore[x][y] = scores;
                    countEveryPos_primary(x, y, chess, Chess.BLACK);
                    everyComputerPointScore[x][y] = scores;
                }
                else
                {
                    everyPlayerPointScore[x][y]=0;
                    everyComputerPointScore[x][y]=0;
                }
            }
        }
        if(findBestPos(everyPlayerPointScore,playerCoord)>=findBestPos(everyComputerPointScore,computerCoord))
        {
            coord=playerCoord;
        }
        else
        {
            coord=computerCoord;
        }
    }

    /**
     * 找到最大分数点的坐标
     * @param a 数组 存储每个点的分数
     * @param c 保存最大分数点的坐标
     * @return
     */
    public int findBestPos(int a[][],Coord c)
    {
        int i,j,max=0;

        for(i=0;i<19;i++)
        {
            for(j=0;j<19;j++)
            {
                if(a[i][j]>max)
                {
                    max=a[i][j];
                    c.setX(i);
                    c.setY(j);
                }
            }
        }
        return max;
    }

    /**
     * 估分函数
     * @param count 每一个方向上的相同颜色的总个数
     * @param i 不同颜色的个数
     */
    public void mark_primary(int count,int i)
    {
        if(count==1)
        {
            scores=scores+10;
        }
        //活二
        else if(count==2&&i==0)
        {
            scores=scores+200;
        }
        //冲二
        else if(count==2&&i==1)
        {
            scores=scores+50;
        }
        //活三
        else if(count==3&&i==0)
        {
            scores=scores+85000;
            System.out.println("分数"+scores);
        }
        //冲三
        else if(count==3&&i==1)
        {
            scores=scores+150;
            System.out.println("分数"+scores);
        }
        //活四
        else if(count==4&&i==0)
        {
            scores=scores+90000;
            System.out.println("分数"+scores);
        }
        //冲四
        else if(count==4&&i==1)
        {
            scores=scores+87000;
            System.out.println("分数"+scores);
        }
        else if(count==5)
        {
            scores=scores+100000;
        }
    }

    /**
     * 计算每个坐标的分数
     * @param x
     * @param y
     * @param chess
     * @param role
     */
    public void countEveryPos_primary(int x,int y,int chess[][],int role)
    {
        scores=0;
        int matchRole,startX=x,startY=y,count=0,up=0,down=0,left=0,right=0,leftUp=0,leftDown=0,rightUp=0,rightDown=0;
        if(role==Chess.BLACK)
        {
            matchRole=Chess.WHITE;
        }
        else
        {
            matchRole=Chess.BLACK;
        }
        //竖直方向上判断输赢
        while (true)
        {
            //向上判断
            y--;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else if(x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole){
                up++;
                break;
            }
            else
            {
                break;
            }
        }
        y = startY;
        while (true) {
            //向下判断
            y = y + 1;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            }  else if(x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole){
                down++;
                break;
            }
            else
            {
                break;
            }
        }
        mark_primary(count, up + down);

        //水平方向判断输赢
        x = startX;
        y = startY;
        count = 0;
        while (true) {
            //向左判断
            x--;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            }else if(x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole){
                down++;
                break;
            }
            else
            {
                break;
            }

        }
        x = startX;
        while (true) {
            //向右判断
            x++;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            }else if(x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole){
                right++;
                break;
            }
            else
            {
                break;
            }
        }
        mark_primary(count, left + right);

        //右倾斜方向判断输赢
        x = startX;
        y = startY;
        count = 0;
        while (true) {
            //向左上判断
            y--;
            x--;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else if(x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole){
                leftUp++;
                break;
            }
            else
            {
                break;
            }
        }
        x = startX;
        y = startY;
        while (true) {
            //向右下判断
            x++;
            y++;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else if(x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole){
                rightDown++;
                break;
            }
            else
            {
                break;
            }
        }
        mark_primary(count, leftUp + rightDown);

        //左倾斜方向判断
        x = startX;
        y = startY;
        count = 0;
        while (true) {
            //向左下判断
            x--;
            y++;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else if(x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole){
                leftDown++;
                break;
            }
            else
            {
                break;
            }
        }
        x = startX;
        y = startY;
        while (true) {
            //向右上判断
            x++;
            y--;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            }else if(x >= 0 && x < 19 && y >= 0 && y < 19 &&chess[x][y]==matchRole){
                rightUp++;
                break;
            }
            else
            {
                break;
            }
        }
        mark_primary(count, leftDown +rightUp);
    }
/*    public void countEveryPos(int x,int y,int chess[][],int role)
    {
        scores=0;
        int startX=x,startY=y,count=0;
        //竖直方向上判断输赢
        while (true)
        {
            //向上判断
            y--;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else {
                break;
            }
        }
        y = startY;
        while (true) {
            //向下判断
            y = y + 1;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else {
                break;
            }
        }
        mark(count);

        //水平方向判断输赢
        x = startX;
        y = startY;
        count = 0;
        while (true) {
            //向左判断
            x--;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else {
                break;
            }
        }
        x = startX;
        while (true) {
            //向右判断
            x++;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else {
                break;
            }
        }
        mark(count);

        //右倾斜方向判断输赢
        x = startX;
        y = startY;
        count = 0;
        while (true) {
            //向左上判断
            y--;
            x--;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else {
                break;
            }
        }
        x = startX;
        y = startY;
        while (true) {
            //向右下判断
            x++;
            y++;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else {
                break;
            }
        }
        mark(count);

        //左倾斜方向判断
        x = startX;
        y = startY;
        count = 0;
        while (true) {
            //向左下判断
            x--;
            y++;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else {
                break;
            }
        }
        x = startX;
        y = startY;
        while (true) {
            //向右上判断
            x++;
            y--;
            if (x >= 0 && x < 19 && y >= 0 && y < 19 && chess[x][y] == role) {
                count++;
            } else {
                break;
            }
        }
        mark(count);
    }*/
}
