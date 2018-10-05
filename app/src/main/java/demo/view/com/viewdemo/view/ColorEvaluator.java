package demo.view.com.viewdemo.view;

import android.animation.TypeEvaluator;

import demo.view.com.viewdemo.view.viewbean.AngleBean;


public class ColorEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
     AngleBean startangle= (AngleBean) startValue;
     AngleBean endAngle = (AngleBean) endValue;

      float angle=fraction*  (endAngle.getSaoAngle()-startangle.getSaoAngle());
        AngleBean bean = new AngleBean(angle);
        return bean;
    }
}
