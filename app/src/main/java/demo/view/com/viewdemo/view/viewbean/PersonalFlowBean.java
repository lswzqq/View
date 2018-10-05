package demo.view.com.viewdemo.view.viewbean;

import java.util.List;

public class PersonalFlowBean {


    /**
     * humanTraffic : 2
     * peakNum : 80
     * peakTime : 19:00
     * sexPercentList : [{"num":100,"percent":"60","sex":"male"},{"num":60,"percent":"30","sex":"female"},{"num":10,"percent":"10","sex":"other"}]
     * timeList : [{"num":20,"timeStr":"1-3"},{"num":40,"timeStr":"3-6"},{"num":20,"timeStr":"6-9"},{"num":50,"timeStr":"9-12"},{"num":60,"timeStr":"12-15"},{"num":70,"timeStr":"15-18"},{"num":80,"timeStr":"18-21"},{"num":20,"timeStr":"21-24"}]
     * workNum : 15
     */

    private int humanTraffic;
    private int peakNum;
    private String peakTime;
    private int workNum;
    private List<SexPercentListBean> sexPercentList;
    private List<TimeListBean> timeList;

    public int getHumanTraffic() {
        return humanTraffic;
    }

    public void setHumanTraffic(int humanTraffic) {
        this.humanTraffic = humanTraffic;
    }

    public int getPeakNum() {
        return peakNum;
    }

    public void setPeakNum(int peakNum) {
        this.peakNum = peakNum;
    }

    public String getPeakTime() {
        return peakTime;
    }

    public void setPeakTime(String peakTime) {
        this.peakTime = peakTime;
    }

    public int getWorkNum() {
        return workNum;
    }

    public void setWorkNum(int workNum) {
        this.workNum = workNum;
    }

    public List<SexPercentListBean> getSexPercentList() {
        return sexPercentList;
    }

    public void setSexPercentList(List<SexPercentListBean> sexPercentList) {
        this.sexPercentList = sexPercentList;
    }

    public List<TimeListBean> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<TimeListBean> timeList) {
        this.timeList = timeList;
    }

    public static class SexPercentListBean {
        /**
         * num : 100
         * percent : 60
         * sex : male
         */

        private int num;
        private String percent;
        private String sex;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getPercent() {
            return percent;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

    public static class TimeListBean {
        /**
         * num : 20
         * timeStr : 1-3
         */

        private int num;
        private String timeStr;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getTimeStr() {
            return timeStr;
        }

        public void setTimeStr(String timeStr) {
            this.timeStr = timeStr;
        }
    }
}
