package demo.view.com.viewdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import demo.view.com.viewdemo.view.TodayIncomeRingView;
import demo.view.com.viewdemo.view.viewbean.RingBean;

public class WithoutAnimationActivity extends Activity {
    private TodayIncomeRingView ringView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_withoutanimtion);
        ringView=findViewById(R.id.ringView);
        test1();
    }
    private void test1() {
        ArrayList<RingBean> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            RingBean bean = new RingBean();
            if (i == 0) {
                bean.setBaifenbi(40);
                bean.setColor(Color.parseColor("#FF7328"));
                bean.setStartAngle(0f);
                bean.setEndAngle(40f * (360f / 100f));
            } else if (i == 1) {
                bean.setBaifenbi(60);
                bean.setColor(Color.parseColor("#FAC300"));
                bean.setStartAngle(list.get(i - 1).getEndAngle());
                bean.setEndAngle(list.get(i - 1).getEndAngle() + 60f * (3600f / 100f));
            }
            // bean.setCentetText("总数240\n份");
            list.add(bean);
        }
        ringView.setData(list);
    }
}
