package Views;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.minesweeper.MainActivity;
import com.example.minesweeper.MainActivity2;
import com.example.minesweeper.Mainactivity_easy;
import com.example.minesweeper.R;
import com.example.minesweeper.choose_activity;
import com.example.minesweeper.home_activity;

import java.util.Random;

import static com.example.minesweeper.MainActivity2.*;

public class Easy_random extends View {
    Rect rect;
    Paint paint,paint_crct,paint_bomb,paint_border,paint_remains,paint_top,paint_highscore,paint_start,paint_number,paint_score,paint_scoretext;
    int l=0,vib=0,start=0;
    int t=100;
    int k=2;
    int g_x;
    int g_y;
    public int score=0,high_score,bomb_checked=0;
    int w=0,posx,posy;
    int stop=0;
    int first=0,bomb_x,bomb_y;
    int s=0;
    String text="score_easy";
    Vibrator v;
    boolean value;
    Bitmap bitmap,bitmap_happy,bitmap_sad,bitmap_home,bitmap_restart,bitmap_click;
    int[] t_x=new int[64];
    int[] t_y=new int[64];
    int[] rect_x =new int[8];
    int[] rect_y =new int[8];
    int[][] table=new int[8][8];
    int[][] k_val=new int[8][8];
    int[] frect_yc=new int[64];
    int[] frect_xc=new int[64];
    int[][] add_up=new int[8][8];
    int num=0;
    SharedPreferences sharedPreferences;
    private static final String TAG= "easy_random";
    public static String shared="shared";
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Easy_random(Context context) {
        super(context);
        init(null);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Easy_random(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Easy_random(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Easy_random(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void init(AttributeSet attrs) {
        rect=new Rect();
        paint=new Paint();
        paint_border=new Paint();
        paint_crct=new Paint();
        paint_top=new Paint();
        paint_highscore=new Paint();
        paint_start=new Paint();
        paint_number=new Paint();
        paint_bomb=new Paint();
        paint_score=new Paint();
        paint_scoretext=new Paint();
        paint_remains=new Paint();
        paint_remains.setColor(Color.parseColor("#0dbd04"));
        paint_scoretext.setColor(Color.parseColor("#000000"));
        Typeface typeface_score=getResources().getFont(R.font.press_start_2p);
        paint_scoretext.setTypeface(typeface_score);
        paint_scoretext.setTextSize(80);
        paint_score.setColor(Color.parseColor("#07fa18"));
        paint_score.setAlpha(192);
        paint_bomb.setColor(Color.parseColor("#f70519"));
        paint_number.setColor(Color.parseColor("#f70505"));
        paint_number.setTextSize(100);
        paint_number.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        paint_start.setColor(Color.parseColor("#f20707"));
        paint_start.setTextSize(110);
        paint_highscore.setColor(Color.parseColor("#fc9d03"));
        paint_highscore.setTextSize(100);
        paint_top.setColor(Color.WHITE);
        paint.setColor(Color.WHITE);
        paint_crct.setColor(Color.parseColor("#1bf207"));
        paint_border.setColor(Color.parseColor("#fcf403"));
        paint_border.setStrokeWidth(15);
        paint_border.setStyle(Paint.Style.STROKE);
        Typeface typeface =getResources().getFont(R.font.bungee_inline);
        paint.setTypeface(typeface);
        paint_highscore.setTypeface(typeface);
        paint_start.setTypeface(typeface);
        paint_remains.setTypeface(typeface);
        paint_remains.setTextSize(90);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint({"DrawAllocation", "NewApi"})
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawRect(80 ,getHeight()/9,getWidth()-80,(float) (4*getHeight()/5),paint_border);
        canvas.drawRect(120,(float) (getHeight()/4.4),getWidth ()-120,(float) (getHeight()/8),paint_top);
        bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
        bitmap_click=BitmapFactory.decodeResource(getResources(),R.drawable.bomb_clicked);
        bitmap_happy=BitmapFactory.decodeResource(getResources(),R.drawable.happy);
        bitmap_sad=BitmapFactory.decodeResource(getResources(), R.drawable.sad);
        bitmap_restart=BitmapFactory.decodeResource(getResources(),R.drawable.restart);
        canvas.drawBitmap(bitmap_restart,140,getHeight()/7,null);
        bitmap_home=BitmapFactory.decodeResource(getResources(),R.drawable.home);
        canvas.drawBitmap(bitmap_home,3*getWidth()/4,getHeight()/7,null);
        l=120;
        t=getHeight()/4;
        for(int i=1;i<=8;i++) {
            for (int j = 1; j <= 8; j++) {
                rect_x[j-1]=l;
                canvas.drawRect(l, t, l + (getWidth()-270)/8, t +(getWidth()-270)/8, paint);
                l = l + (getWidth()-270)/8+5;
                if(j!=8)
                    w=l;
            }
            rect_y[i-1]=t;
            t=t+(getWidth()-270)/8+5;
            l=120;
        }
        switch(choose_activity.choose)
        {
            case 0:
                text="score_easy";
                canvas.drawText("EASY MODE",(120+w)/4,getHeight()/11,paint_highscore);
                break;
            case 1:
                text="score_medium";
                canvas.drawText("MEDIUM MODE",(120+w)/6,getHeight()/11,paint_highscore);
                break;
            case 2:
                text="score_hard";
                canvas.drawText("HARD MODE",(120+w)/4,getHeight()/11,paint_highscore);
                break;
            case 3:
                text="score_expert";
                canvas.drawText("EXPERT MODE",(120+w)/6,getHeight()/11,paint_highscore);
                break;
        }

        if(k==3&&stop==0)
        {frect_xc[num]=g_x;
            frect_yc[num]=g_y;
            t_x[num]=posx;
            t_y[num]=posy;
            score=score+1;
            num++;
            k=2;
        }
        if(score>0)
            canvas.drawBitmap(bitmap_happy, (float) (getWidth()/2.5), (float) (getHeight()/7.5),null);

        if(k==-1)
        { bomb_x=g_x;
            bomb_y=g_y;
            stop=1;}
        paint.setTextSize(50);
        for(int r=0;r<num;r++){
            canvas.drawRect(frect_xc[r],frect_yc[r],frect_xc[r]+(getWidth()-270)/8,frect_yc[r]+(getWidth()-270)/8,paint_crct);
            canvas.drawText(String.valueOf(table[t_x[r]][t_y[r]]),frect_xc[r]+(getWidth()-270)/48, (float) (frect_yc[r]+(getWidth()-270)/9),paint_number);

        }
        canvas.drawText("Score="+String.valueOf(score), 120,(4*getHeight()/5+t)/2,paint);
        gethighscore();
        canvas.drawText("HIGH SCORE :"+sharedPreferences.getInt(text,0),120,9*getHeight()/10,paint_highscore);
        if(first==1)
            canvas.drawText("Bombs="+s,  (float) 7*(120+w)/10,(4*getHeight()/5+t)/2,paint);
        if(start==0) {
            canvas.drawText("CLICK START", getWidth() / 7, (float) 9*(getHeight() /20), paint_start);
            canvas.drawText("START", getWidth() / 3, (float) (getHeight() / 5.2), paint_start);
        }
        if(stop==1){
            canvas.drawBitmap(bitmap_sad, (float) (getWidth()/2.5), (float) (getHeight()/7.5),null);
            if(vib==0) {
                v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(1000);
                vib = 1;
            }
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++) {
                    if (k_val[i][j] == -1) {
                        canvas.drawBitmap(bitmap, rect_x[j], rect_y[i], null);
                    }
                }
            }
            bomb_checked=1;
            canvas.drawRect(bomb_x,bomb_y,bomb_x+(getWidth()-270)/8,bomb_y+(getWidth()-270)/8,paint_bomb);
            canvas.drawBitmap(bitmap_click, bomb_x, bomb_y, null);
            canvas.drawRect(bomb_x,bomb_y,bomb_x+(getWidth()-270)/8,bomb_y+(getWidth()-270)/8,paint_bomb);
            canvas.drawBitmap(bitmap_click, bomb_x, bomb_y, null);
            paint_score.setColor(Color.parseColor("#fa0207"));
            paint_score.setAlpha(192);
            paint_scoretext.setColor(Color.WHITE);
            canvas.drawRoundRect(85,3*getHeight()/10,getWidth()-85, (float) (6*getHeight()/10),50,50,paint_score);
            paint_scoretext.setTextSize(80);
            canvas.drawText("YOU LOST", (float) (getWidth()/5),2*getHeight()/5,paint_scoretext);
            paint_scoretext.setTextSize(55);
            canvas.drawText("TRY AGAIN!", (float) (2.5*getWidth()/10),9*getHeight()/20,paint_scoretext);
            canvas.drawText("SCORE="+String.valueOf(score), 2*getWidth()/10,getHeight()/2,paint_scoretext);
            canvas.drawText("HIGH SCORE="+sharedPreferences.getInt(text,0),2*getWidth()/10, (float) (5.5*getHeight()/10),paint_scoretext);
        }
        canvas.drawText(String.valueOf(64-s-num)+"TILES TO WIN",120, (float) (7.9*getHeight()/10),paint_remains);
        if(score==64-s)
        {
            paint_scoretext.setColor(Color.BLACK);
            paint_score.setColor(Color.parseColor("#07fa18"));
            paint_score.setAlpha(192);
            canvas.drawRoundRect(85,getHeight()/3,getWidth()-85,getHeight()/2,50,50,paint_score);
            paint_scoretext.setTextSize(80);
            canvas.drawText("YOU WON", (float) (getWidth()/5),2*getHeight()/5,paint_scoretext);
            paint_scoretext.setTextSize(55);
            canvas.drawText("CONGRATULATIONS", getWidth()/8,9*getHeight()/20,paint_scoretext);

        }
    }
    private void gethighscore() {
        sharedPreferences= getContext().getSharedPreferences("shared",MODE_PRIVATE);
        if(sharedPreferences.getInt(text,0)<=score){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt(text,score);
            editor.apply();
            high_score=sharedPreferences.getInt(text,0);
            Log.d(TAG,"highscore="+high_score);
        }
        Log.d(TAG,"highscore="+sharedPreferences.getInt(text,0));
    }
        @Override
    public boolean onTouchEvent(MotionEvent event) {
        value=super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float x=event.getX();
                float y=event.getY();
                if(first==0&&x>=getWidth()/3&&x<=2*getWidth()/3&&y>=getHeight()/8&&y<=getHeight()/4.4) {
                    genrerate_random();
                    clue();
                    first = 1;
                    start=1;
                }
                if (start == 1&&bomb_checked==0) {
                    for(int i=0;i<8;i++){
                        for(int j=0;j<8;j++){
                            if((x>rect_x[j]&&x<rect_x[j]+100)&&(y>rect_y[i]&&y<rect_y[i]+100)&&add_up[i][j]!=1){
                                k=k_val[i][j];
                                posx=i;
                                posy=j;
                                g_x=rect_x[j];
                                g_y=rect_y[i];
                                add_up[i][j]=1;
                            }
                        }}}
                postInvalidate();
                return true;
            case MotionEvent.ACTION_UP:
                float e_x=event.getX(),e_y=event.getY();
                if(e_x>=130&&e_x<=230&&e_y>=getHeight()/7&&e_y<=getHeight()/7+100&&start==1){
                    vib=0;
                    stop=0;
                    k=2;
                    num=0;
                    first=1;
                    score=0;
                    start=1;
                    bomb_checked=0;
                    for(int i=0;i<8;i++)
                    {
                        for(int j=0;j<8;j++)
                        {
                            add_up[i][j]=0;
                        }
                    }

                    s=0;
                    genrerate_random();
                    clue();
                    postInvalidate();

                }
                if(e_x>=3*getWidth()/4&&e_x<=3*getWidth()/4+100&&e_y>=getHeight()/7&&e_y<=getHeight()/7+100) {
                    Intent intent=new Intent(getContext(),choose_activity.class);
                    getContext().startActivity(intent);
                }
                return true;
        }
        return  value;
    }
    private void genrerate_random()
    {int a;
    boolean exp=true;
        Random random=new Random();
       do {
           s=0;
           for (int i = 0; i < 8; i++) {
               for (int j = 0; j < 8; j++) {
                   a = random.nextInt(5);
                   if(choose_activity.choose==0||choose_activity.choose==1) {
                       if (a == 0) {
                           k_val[i][j] = -1;
                           s++;
                           Log.d(TAG, "Bombs=" + i + "\t" + j);
                       } else
                           k_val[i][j] = 3;
                   }
                   if(choose_activity.choose==2)
                   {
                       if (a == 0||a==1) {
                           k_val[i][j] = -1;
                           s++;
                           Log.d(TAG, "Bombs=" + i + "\t" + j);
                       } else
                           k_val[i][j] = 3;
                   }
                   if(choose_activity.choose==3)
                   {
                       if (a == 0||a==1||a==2) {
                           k_val[i][j] = -1;
                           s++;
                           Log.d(TAG, "Bombs=" + i + "\t" + j);
                       } else
                           k_val[i][j] = 3;
                   }
               }
           }
           Log.d(TAG, "No .of .bombs=" + s);
           if(choose_activity.choose==0)
               exp=(s>10);
           if(choose_activity.choose==1)
               exp=(s>20||s<10);
           if(choose_activity.choose==2)
               exp=(s>30||s<20);
           if(choose_activity.choose==3)
               exp=(s<30);
       }while(exp);
    }
    private void clue() {
        int nb=0;
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(k_val[i][j]==-1)
                    table[i][j]=-1;
                if(k_val[i][j]==3)
                {
                    if(j>=0&&j<7)
                    {
                        if (k_val[i][j + 1] == -1)
                            nb++;
                    }
                    if(j>0)
                    {
                        if(k_val[i][j-1]==-1)
                            nb++;
                    }
                    if(i>0)
                    {
                        if(k_val[i-1][j]==-1)
                            nb++;
                    }
                    if(i>=0&&i<7)
                    {
                        if(k_val[i+1][j]==-1)
                            nb++;
                    }
                    if(i>0&&i<7&&j>0&&j<7)
                    {
                        if(k_val[i-1][j-1]==-1)
                            nb++;
                        if(k_val[i-1][j+1]==-1)
                            nb++;
                        if(k_val[i+1][j-1]==-1)
                            nb++;
                        if(k_val[i+1][j+1]==-1)
                            nb++;
                    }
                    if(j==0)
                    {
                        if(i==0)
                        {
                            if(k_val[i+1][j+1]==-1)
                                nb++;
                        }
                        if(i==7)
                        {
                            if(k_val[i-1][j+1]==-1)
                                nb++;
                        }
                        if(i>0&&i<7)
                        {
                            if(k_val[i+1][j+1]==-1)
                                nb++;
                            if(k_val[i-1][j+1]==-1)
                                nb++;
                        }
                    }
                    if(j==7)
                    {
                        if(i==0)
                        {
                            if(k_val[i+1][j-1]==-1)
                                nb++;
                        }
                        if(i==7)
                        {
                            if(k_val[i-1][j-1]==-1)
                                nb++;
                        }
                        if(i>0&&i<7)
                        {
                            if(k_val[i+1][j-1]==-1)
                                nb++;
                            if(k_val[i-1][j-1]==-1)
                                nb++;
                        }
                    }
                    if(i==0)
                    {
                        if(j>0&&j<7)
                        {
                            if(k_val[i+1][j-1]==-1)
                                nb++;
                            if(k_val[i+1][j+1]==-1)
                                nb++;
                        }
                    }
                    if(i==7)
                    {
                        if(j>0&&j<7)
                        {
                            if(k_val[i-1][j-1]==-1)
                                nb++;
                            if(k_val[i-1][j+1]==-1)
                                nb++;
                        }
                    }
                    table[i][j]=nb;
                }
                Log.d(TAG,"surrounding : "+i+"\t"+j+"\t"+table[i][j]);
                nb=0;
            }
        }
    }
}