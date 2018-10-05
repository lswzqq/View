package demo.view.com.viewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import demo.view.com.viewdemo.view.TodayFlowBrokenLineView;
import demo.view.com.viewdemo.view.viewbean.PersonalFlowBean;

public class FlowViewActivity extends Activity {
    private List<PersonalFlowBean.TimeListBean> timeList=new ArrayList<>();
    private TodayFlowBrokenLineView flowView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_flowview);
        flowView=findViewById(R.id.flowView);
        test();
    }

    private void test() {
        //假数据，只是为了测试,enen 
        for (int i=1;i<=8;i++){
            PersonalFlowBean.TimeListBean listBean=new PersonalFlowBean.TimeListBean();

            if (i==1){
                listBean.setNum(50);
                listBean.setTimeStr("1-3");
            }else if (i==2){
                listBean.setNum(60);
                listBean.setTimeStr("4-6");
            }else if (i==3){
                listBean.setNum(110);
                listBean.setTimeStr("7-9");
            }else if (i==4){
                listBean.setNum(180);
                listBean.setTimeStr("10-12");
            }else if (i==5){
                listBean.setNum(200);
                listBean.setTimeStr("13-15");
            }else if (i==6){
                listBean.setNum(180);
                listBean.setTimeStr("16-18");
            }else if (i==7){
                listBean.setNum(100);
                listBean.setTimeStr("18-21");
            }else if (i==8){
                listBean.setNum(50);
                listBean.setTimeStr("22-24");
            }

            timeList.add(listBean);
        }
        flowView.setData(timeList,"13-15");
    }
}
