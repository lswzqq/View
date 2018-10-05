package demo.view.com.viewdemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import demo.view.com.viewdemo.view.viewbean.RingBean;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView withAnimation,withoutAnimation,flowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        withAnimation=findViewById(R.id.withAnimation);
        withoutAnimation=findViewById(R.id.withoutAnimation);
        flowView=findViewById(R.id.flowView);
        withAnimation.setOnClickListener(this);
        withoutAnimation.setOnClickListener(this);
        flowView.setOnClickListener(this);
    }

    private void test1() {
        ArrayList<RingBean> list = new ArrayList<>();
        for (int i=0;i<2;i++){
            RingBean bean = new RingBean();
            if (i==0){
                bean.setBaifenbi(40);
                bean.setColor(Color.parseColor("#FF7328"));
                bean.setStartAngle(0f);
                bean.setEndAngle(40f*(360f/100f));
            }else if (i==1){
                bean.setBaifenbi(60);
                bean.setColor(Color.parseColor("#FAC300"));
                bean.setStartAngle(list.get(i-1).getEndAngle());
                bean.setEndAngle(list.get(i-1).getEndAngle()+60f*(3600f/100f));
            }
            // bean.setCentetText("总数240\n份");
            list.add(bean);
        }
    //    ringViewAnimation.setData(list);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.withAnimation:
                startActivity(new Intent(this,WithAnimationActivity.class));
                break;
            case R.id.withoutAnimation:
                startActivity(new Intent(this,WithoutAnimationActivity.class));
                break;
            case R.id.flowView:
                startActivity(new Intent(this,FlowViewActivity.class));
                break;
        }
    }
}
