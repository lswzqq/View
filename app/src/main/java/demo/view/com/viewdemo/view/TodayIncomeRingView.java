package demo.view.com.viewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import demo.view.com.viewdemo.R;
import demo.view.com.viewdemo.view.viewbean.PointBean;
import demo.view.com.viewdemo.view.viewbean.RingBean;

public class TodayIncomeRingView extends View {
    private int percentcolor,centetColor;
    private float percentage;
    private float textSize,centerSize;
    private Paint mPaint;
    private int mWidth,mHeight;
    private Context mContext;
    private int centerX;
    private int centerY;
    private float radius;
    private Paint textCirclePaint;
    private Paint textCnetetPaint;
    private int vipCardColor= Color.parseColor("#FF7328");
    private int trainerColor= Color.parseColor("#FAC300");
    private float percents;
    private ArrayList<RingBean> list=new ArrayList<>();
    private Rect dataTextBound = new Rect();
    private Rect centerTextBound=new Rect();
    private List<PointBean> pointBeanList=new ArrayList<>();
    float touchX,touchY;
    float saoguoAngel=0;
    float startAngle=0;


    /**
     * 弧形外接矩形
     */
    private RectF rectF;
    public TodayIncomeRingView(Context context) {
        this(context,null);
        this.mContext=context;
    }

    public TodayIncomeRingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TodayIncomeRingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint=new Paint();
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.dp_50));
        mPaint.setStyle(Paint.Style.STROKE);


        textCirclePaint=new Paint();
        textCirclePaint.setColor(Color.parseColor("#ffffff"));
        textCnetetPaint=new Paint();
        textCnetetPaint.setTextSize(70f);
        textCnetetPaint.setColor(Color.BLACK);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
         TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.todayIncomeRingView);
         percentcolor = array.getColor(R.styleable.todayIncomeRingView_textcolor, Color.WHITE);
         centetColor=array.getColor(R.styleable.todayIncomeRingView_centercolor, Color.BLACK);
         textSize = array.getDimension(R.styleable.todayIncomeRingView_textsize, context.getResources().getDimension(R.dimen.dp_18));
         centerSize=array.getDimension(R.styleable.todayIncomeRingView_centersize,context.getResources().getDimension(R.dimen.dp_36));

         array.recycle();

         percentage=360f/100f;

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
        if (list!=null&&list.size()!=0){
            float saoguoAngel=0;
            float startAngle=0;
            pointBeanList.clear();
            for (int i=0;i<list.size();i++){
                if (i!=list.size()-1){
                    saoguoAngel=percentage*list.get(i).getBaifenbi();
                }else {
                    saoguoAngel=360f-startAngle;
                }
                mPaint.setColor(list.get(i).getColor());
                canvas.drawArc(rectF,startAngle,saoguoAngel+1f,false,mPaint);
                float anele=startAngle+90+saoguoAngel/2;
                jisuan(canvas,anele, String.valueOf(list.get(i).getBaifenbi()));
                PointBean bean = new PointBean();
                bean.setStartAngle(startAngle);
                bean.setEndAngle(startAngle+saoguoAngel);
                pointBeanList.add(bean);
                startAngle+=saoguoAngel;
            }
            String centetText = list.get(0).getCentetText();
            if (!TextUtils.isEmpty(centetText)){
                textCnetetPaint.getTextBounds(centetText,0,centetText.length(),centerTextBound);
                textCnetetPaint.setColor(Color.BLACK);
                canvas.drawText(centetText,centerX-centerTextBound.width()/2,centerY+centerTextBound.height()/2,textCirclePaint);
            }
        //    Math.atan2(y,x)
        }

    }




    private void jisuan(Canvas canvas, float anele, String ss) {
        float x= (float) (Math.sin(Math.toRadians(anele))*radius);
        float y= (float) (Math.cos(Math.toRadians(anele))*radius);
        float startX=centerX+x;
        float startY=centerY-y;
        String s = ss+"%";
        textCirclePaint.setTextSize(50f);
        textCirclePaint.getTextBounds(s,0,s.length(),dataTextBound);
        canvas.drawText(s,startX-dataTextBound.width()/2,startY+dataTextBound.height()/2,textCirclePaint);
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
                    for (int i=0;i<pointBeanList.size();i++){
                        float startAngle = pointBeanList.get(i).getStartAngle();
                        float endAngle = pointBeanList.get(i).getEndAngle();
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

        postInvalidate();


    }

    public interface TouchListener{
        void select(int i);
    }
    private TouchListener listener;
    public void setListener(TouchListener touchListener){
        this.listener=touchListener;
    }
}
