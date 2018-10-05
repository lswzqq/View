package demo.view.com.viewdemo.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import demo.view.com.viewdemo.R;
import demo.view.com.viewdemo.view.viewbean.AngleBean;
import demo.view.com.viewdemo.view.viewbean.LastBean;
import demo.view.com.viewdemo.view.viewbean.RingBean;

public class NewIncomeRingView extends View {

    private Paint mPaint;
    private int mWidth,mHeight;
    private Context mContext;
    private Paint textCirclePaint;
    private int centerX;
    private int centerY;
    private float radius;
    private RectF rectF;
    private float startAngle;
    private float saoguoAngle;
    private float percentage;
    private AngleBean currentAngle;
    private ArrayList<RingBean> list=new ArrayList<>();
    private ArrayList<LastBean> lastFloat=new ArrayList<>();
    private ValueAnimator animator;
    private int  isHaveSet=-1;
    private Rect dataTextBound = new Rect();
    float touchX,touchY;
    public NewIncomeRingView(Context context) {
        this(context,null);
    }

    public NewIncomeRingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NewIncomeRingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        percentage=360f/100f;
    }

    private void initPaint() {
        mPaint=new Paint();
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.dp_50));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    //    mPaint.setColor(Color.RED);


        textCirclePaint=new Paint();
        textCirclePaint.setColor(Color.parseColor("#ffffff"));


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode= MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode== MeasureSpec.EXACTLY){
            mWidth= MeasureSpec.getSize(widthMeasureSpec);
        }else {
            mWidth=(int) getResources().getDimension(R.dimen.dp_230);
        }

        if (heightMode== MeasureSpec.EXACTLY){
            mHeight= MeasureSpec.getSize(heightMeasureSpec);
        }else {
            mHeight= (int) getResources().getDimension(R.dimen.dp_230);
        }
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = getMeasuredWidth() / 2;
        centerY = getMeasuredHeight() / 2; //设置半径为宽高最小值的1/4
        radius = (Math.min(getMeasuredWidth(), getMeasuredHeight()) *1/ 3); //设置扇形外接矩形
        rectF = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentAngle==null){
            currentAngle=new AngleBean(0f);
            canvas.drawArc(rectF,startAngle,saoguoAngle,false,mPaint);
            AngleBean startAngles=new AngleBean(0f);
            AngleBean endAngles=new AngleBean(360f);
            animator = ObjectAnimator.ofObject(new ColorEvaluator(), startAngles, endAngles);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentAngle= (AngleBean) animation.getAnimatedValue();
                    Log.i("lsw","222222=="+currentAngle.getSaoAngle());
                    for (int i=0;i<list.size();i++){
                        if (currentAngle.getSaoAngle()<=list.get(i).getEndAngle()&&currentAngle.getSaoAngle()>=list.get(i).getStartAngle()){
                            if (isHaveSet !=i){
                                startAngle=list.get(i).getStartAngle();
                                LastBean bean = new LastBean();
                                bean.setColor(list.get(i).getColor());
                                bean.setLastStartAngle(startAngle);
                                lastFloat.add(bean);
//                                saoguoAngle=currentAngle.getSaoAngle()-startAngle;
//                                mPaint.setColor(list.get(i).getColor());
                                isHaveSet=i;
                            }else {
                              //  saoguoAngle=currentAngle.getSaoAngle()-startAngle;
                            }
                        }
                    }
                    invalidate();
                }
            });
            animator.setDuration(2000);
            animator.start();
        }else {
            if (lastFloat!=null&&lastFloat.size()!=0){
                for (int i=0;i<lastFloat.size()&&i<list.size();i++){
                    mPaint.setColor(lastFloat.get(i).getColor());
                    float lastStartAngle = lastFloat.get(i).getLastStartAngle();
                    canvas.drawArc(rectF,lastStartAngle,currentAngle.getSaoAngle()-lastStartAngle,false,mPaint);
                    float anele;
                    if (currentAngle.getSaoAngle()-lastStartAngle<list.get(i).getEndAngle()){
                         anele=lastStartAngle+90+(currentAngle.getSaoAngle()-lastStartAngle)/2;
                    }else {
                         anele=lastStartAngle+90+list.get(i).getEndAngle()/2;
                    }
                 //   float anele=lastStartAngle+90+(list.get(i).getEndAngle())/2;
                    jisuan(canvas,anele, String.valueOf(list.get(i).getBaifenbi()));
                }
            }
          //  canvas.drawArc(rectF,startAngle,saoguoAngle,false,mPaint);
        }
    }

    private void jisuan(Canvas canvas, float anele, String percent) {
        float x= (float) (Math.sin(Math.toRadians(anele))*radius);
        float y= (float) (Math.cos(Math.toRadians(anele))*radius);
        float startX=centerX+x;
        float startY=centerY-y;
        String new_percent = percent+"%";
        textCirclePaint.setTextSize(50f);
        textCirclePaint.getTextBounds(new_percent,0,new_percent.length(),dataTextBound);
        canvas.drawText(new_percent,startX-dataTextBound.width()/2,startY+dataTextBound.height()/2,textCirclePaint);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = 0,y = 0;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                touchX=x;
                touchY=y;
            case MotionEvent.ACTION_MOVE:
                if (event.getY()-y> 10){
                    return true;
                }
            case MotionEvent.ACTION_UP:
                if (rectF.contains(x,y)){
//            double v = Math.atan2(centerY-y, x-centerX);
//            double v1 = 180 * v / Math.PI;
//            v1 = v1 < 0 ? 180 - v1 : v1;
                    float lenXY= (float) Math.sqrt((x-centerX)*(x-centerX)+(centerY-y)*(centerY-y));
                    double radian= Math.acos((x-centerX)/lenXY)*(touchY<centerY?-1:1);
                    double tmp= Math.round(radian/ Math.PI*180);
                    //顺时针角度
                    double angle=tmp>=0?tmp:360+tmp;
                    for (int i=0;i<list.size();i++){
                        float startAngle = list.get(i).getStartAngle();
                        float endAngle = list.get(i).getEndAngle();
                        if (angle<=endAngle&&angle>=startAngle){
                            if (listener!=null){
                                listener.select(i);
                            }
                        }
                    }
                    return true;
                }else {
                    return false;
                }

        }
        return super.onTouchEvent(event);
    }

    public void setData(ArrayList<RingBean> lists){
        list.clear();
        list.addAll(lists);
        lastFloat.clear();
        if (list.size()!=0){
            if (list.get(0).getBaifenbi()==0){
                LastBean bean = new LastBean();
                bean.setColor(list.get(0).getColor());
                bean.setLastStartAngle(0f);
                lastFloat.add(bean);
            }
        }
        currentAngle=null;
        invalidate();
    }


    public interface TouchListener{
        void select(int i);
    }
    private TouchListener listener;
    public void setListener(TouchListener touchListener){
        this.listener=touchListener;
    }

}
