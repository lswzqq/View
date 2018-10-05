package demo.view.com.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import demo.view.com.viewdemo.R;
import demo.view.com.viewdemo.view.viewbean.PersonalFlowBean;

public class TodayFlowBrokenLineView extends View {
    private Paint coordinatePaint;//坐标
    private Paint brokenlinePaint;//折线1
    private Paint brokenlinePaint2;
    private Paint textPaint;
    private Paint circlePaint;//圆环
    private Paint rectPaint;
    private Paint peakPaint;
    private Rect peakRect;
    private Rect peakTimeRect;
    private int mWidth,mHeight;
    private int tiaoHeighn=20;
    private int danduWidth;
    private int allWidth;
    private int firstWeizhi;
    private Rect textRect =new Rect();
    private List<String> list=new ArrayList<>();
    private List<PersonalFlowBean.TimeListBean> timeList=new ArrayList<>();
    private Path mPath,closePath;
    private float percentage;
    private int maxPoint;
    private int mixPoint;
    private String peakTime;
    public TodayFlowBrokenLineView(Context context) {
        this(context,null);
    }

    public TodayFlowBrokenLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TodayFlowBrokenLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
       coordinatePaint=new Paint();
       coordinatePaint.setColor(getResources().getColor(R.color.text_999999));
       coordinatePaint.setStrokeWidth(getResources().getDimension(R.dimen.dp_2));

       brokenlinePaint=new Paint();
   //    brokenlinePaint.setColor(getResources().getColor(R.color.color_FF4F4F));
       brokenlinePaint.setStrokeWidth(getResources().getDimension(R.dimen.dp_2));
       brokenlinePaint.setAntiAlias(true);
       brokenlinePaint.setDither(true);
       brokenlinePaint.setStyle(Paint.Style.FILL_AND_STROKE);

       brokenlinePaint2=new Paint();
       brokenlinePaint2.setColor(getResources().getColor(R.color.color_FF4F4F));
       brokenlinePaint2.setStrokeWidth(getResources().getDimension(R.dimen.dp_2));
       brokenlinePaint2.setAntiAlias(true);
       brokenlinePaint2.setDither(true);
       brokenlinePaint2.setStyle(Paint.Style.STROKE);

       mPath=new Path();
       closePath=new Path();
       textPaint=new Paint();
       textPaint.setColor(getResources().getColor(R.color.text_999999));
       textPaint.setTextSize(getResources().getDimension(R.dimen.sp_8));

       circlePaint=new Paint();
       circlePaint.setStrokeWidth(getResources().getDimension(R.dimen.dp_2));
       circlePaint.setColor(getResources().getColor(R.color.color_FF4F4F));
       circlePaint.setStyle(Paint.Style.STROKE);

       rectPaint=new Paint();
       rectPaint.setColor(getResources().getColor(R.color.color_FF4F4F));
       rectPaint.setStyle(Paint.Style.FILL_AND_STROKE);
       rectPaint.setAntiAlias(true);

       peakPaint=new Paint();
       peakPaint.setColor(getResources().getColor(R.color.color_fff));
       peakPaint.setTextSize(getResources().getDimension(R.dimen.sp_10));
       peakPaint.setStyle(Paint.Style.STROKE);

       peakRect=new Rect();
       peakTimeRect=new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode== MeasureSpec.EXACTLY){
            mWidth= MeasureSpec.getSize(widthMeasureSpec);
        }
        if (heightMode== MeasureSpec.EXACTLY){
            mHeight= MeasureSpec.getSize(heightMeasureSpec);
        }else {
            mHeight= (int) getResources().getDimension(R.dimen.dp_100);
        }
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        allWidth=getMeasuredWidth()-60;
        danduWidth=allWidth/7;
        firstWeizhi=(getMeasuredWidth()-allWidth)/2;
        percentage=(getMeasuredHeight()*5/6)/200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
   //
//        canvas.drawColor(getResources().getColor(R.color.bg_F6F6F6), PorterDuff.Mode.CLEAR);
//        canvas.drawColor(getResources().getColor(R.color.bg_F6F6F6));
        mPath.reset();
        closePath.reset();
        canvas.drawLine(0,getMeasuredHeight()*5/6,getMeasuredWidth(),getMeasuredHeight()*5/6,coordinatePaint);
        if (timeList!=null&&timeList.size()!=0){
            for (int i=1;i<=timeList.size();i++){
                canvas.drawLine(firstWeizhi,getMeasuredHeight()*5/6,firstWeizhi,getMeasuredHeight()*5/6-tiaoHeighn,coordinatePaint);
                String timeStr = timeList.get(i - 1).getTimeStr();
                textPaint.getTextBounds(timeStr,0,timeStr.length(),textRect);
                canvas.drawText(timeStr,firstWeizhi-textRect.width()/2,getMeasuredHeight()*5/6+(getMeasuredHeight()/12),textPaint);
                if (i==1){
                    if (percentage*(timeList.get(i-1).getNum())==0){
                        mPath.moveTo(firstWeizhi,(getMeasuredHeight()*5/6)-percentage*(timeList.get(i-1).getNum()));
                        closePath.moveTo(firstWeizhi,(getMeasuredHeight()*5/6)-percentage*(timeList.get(i-1).getNum()));
                    }else {
                        if (getMeasuredHeight()*5/6-percentage*(timeList.get(i-1).getNum())+getMeasuredHeight()/12>=getMeasuredHeight()*5/6){
                            mPath.moveTo(firstWeizhi,getMeasuredHeight()*5/6-getMeasuredHeight()/12);
                            closePath.moveTo(firstWeizhi,getMeasuredHeight()*5/6-getMeasuredHeight()/12);
                        }else {
                            mPath.moveTo(firstWeizhi,(getMeasuredHeight()*5/6)-percentage*(timeList.get(i-1).getNum())+getMeasuredHeight()/12);
                            closePath.moveTo(firstWeizhi,(getMeasuredHeight()*5/6)-percentage*(timeList.get(i-1).getNum())+getMeasuredHeight()/12);
                        }
                    }
                }else{
                    if (percentage*(timeList.get(i-1).getNum())==0){
                        mPath.lineTo(firstWeizhi,(getMeasuredHeight()*5/6)-percentage*(timeList.get(i-1).getNum()));
                        closePath.lineTo(firstWeizhi,(getMeasuredHeight()*5/6)-percentage*(timeList.get(i-1).getNum()));
                    }else {
                        if (getMeasuredHeight()*5/6-percentage*(timeList.get(i-1).getNum())+getMeasuredHeight()/12>=getMeasuredHeight()*5/6){
                            mPath.lineTo(firstWeizhi,getMeasuredHeight()*5/6-getMeasuredHeight()/12);
                            closePath.lineTo(firstWeizhi,getMeasuredHeight()*5/6-getMeasuredHeight()/12);
                        }else {
                            mPath.lineTo(firstWeizhi,(getMeasuredHeight()*5/6)-percentage*(timeList.get(i-1).getNum())+getMeasuredHeight()/12);
                            closePath.lineTo(firstWeizhi,(getMeasuredHeight()*5/6)-percentage*(timeList.get(i-1).getNum())+getMeasuredHeight()/12);
                        }
                    }
                }
                if (maxPoint==i-1){
                    if (percentage!=0){
                        canvas.drawCircle(firstWeizhi,(getMeasuredHeight()*5/6)-percentage*(timeList.get(i-1).getNum())+getMeasuredHeight()/12,10,circlePaint);
                    }
                }
                firstWeizhi+=danduWidth;
            }

          //  LinearGradient linearGradient = new LinearGradient(getMeasuredWidth()/2, getMeasuredHeight()-percentage*timeList.get(mixPoint).getNum()*1.5f,getMeasuredWidth()/2, 0, Color.parseColor("#00000000"),Color.parseColor("#88E74747"), Shader.TileMode.CLAMP);
            LinearGradient linearGradient = new LinearGradient(getMeasuredWidth()/2, getMeasuredHeight()*5/6,getMeasuredWidth()/2, 0, Color.parseColor("#00000000"), Color.parseColor("#88E74747"), Shader.TileMode.CLAMP);
            brokenlinePaint.setShader(linearGradient);
            closePath.lineTo(firstWeizhi-danduWidth,getMeasuredHeight()*5/6);
            closePath.lineTo((getMeasuredWidth()-allWidth)/2,getMeasuredHeight()*5/6);
            closePath.close();
            canvas.drawPath(closePath,brokenlinePaint);
            canvas.drawPath(mPath,brokenlinePaint2);
            firstWeizhi=(getMeasuredWidth()-allWidth)/2;
            if (percentage!=0){
                if (maxPoint>=0&&maxPoint<6){
                    RectF rectF = new RectF(firstWeizhi + danduWidth * (maxPoint)+danduWidth/2, getMeasuredHeight()/12, firstWeizhi + danduWidth * (maxPoint) + getResources().getDimension(R.dimen.dp_90), getMeasuredHeight()/6+getResources().getDimension(R.dimen.dp_15));
                    canvas.drawRoundRect(rectF,10,10,rectPaint);
                    String peak="人数峰值";
                    String timeAndCount=peakTime+" "+timeList.get(maxPoint).getNum()+"人";
                    peakPaint.getTextBounds(timeAndCount,0,timeAndCount.length(),peakTimeRect);
                    peakPaint.getTextBounds(peak,0,peak.length(),peakRect);
                    canvas.drawText(peak,firstWeizhi + danduWidth * (maxPoint)+peakRect.width()/5+danduWidth/2,getMeasuredHeight()/12+peakRect.height()+peakRect.height()/3,peakPaint);
                    canvas.drawText(timeAndCount,firstWeizhi + danduWidth * (maxPoint)+peakRect.width()/5+danduWidth/2,getMeasuredHeight()/12+peakRect.height()+peakTimeRect.height()+peakTimeRect.height()*2/3,peakPaint);
                } else if (maxPoint>=6){
                    RectF rectF = new RectF(firstWeizhi + danduWidth * (maxPoint -2), getMeasuredHeight()/12, firstWeizhi + danduWidth * (maxPoint - 2) + getResources().getDimension(R.dimen.dp_70), getMeasuredHeight()/6+getResources().getDimension(R.dimen.dp_15));
                    canvas.drawRoundRect(rectF,10,10,rectPaint);
                    String peak="人数峰值";
                    String timeAndCount=peakTime+" "+timeList.get(maxPoint).getNum()+"人";
                    peakPaint.getTextBounds(timeAndCount,0,timeAndCount.length(),peakTimeRect);
                    peakPaint.getTextBounds(peak,0,peak.length(),peakRect);
                    canvas.drawText(peak,firstWeizhi + danduWidth * (maxPoint-2)+peakRect.width()/5,getMeasuredHeight()/12+peakRect.height()+peakRect.height()/3,peakPaint);
                    canvas.drawText(timeAndCount,firstWeizhi + danduWidth * (maxPoint-2)+peakRect.width()/5,getMeasuredHeight()/12+peakRect.height()+peakTimeRect.height()+peakTimeRect.height()*2/3,peakPaint);
                }
            }
        }
    }

    public void setData(List<PersonalFlowBean.TimeListBean> timeLists, String peakTimes){
        this.peakTime=peakTimes;
        this.timeList.clear();
        this.timeList.addAll(timeLists);
        if (timeList!=null&&timeList.size()!=0){
            int maxNum=timeList.get(0).getNum();
            int minNum=timeList.get(0).getNum();
            for (int i=0;i<timeList.size();i++){
                if (timeList.get(i).getNum()>maxNum){
                    maxNum=timeList.get(i).getNum();
                    maxPoint=i;
                }
                if (timeList.get(i).getNum()<minNum){
                    minNum=timeList.get(i).getNum();
                    mixPoint=i;
                }
            }
            if (maxNum==0){
                percentage=0;
            }else {
                percentage=(getMeasuredHeight()*4/6)/(maxNum);
            }
           postInvalidate();
        }
    }

}
