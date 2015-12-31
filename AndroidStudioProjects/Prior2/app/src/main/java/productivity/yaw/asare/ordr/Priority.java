package productivity.yaw.asare.ordr;

import java.util.Calendar;

/**
 * Created by yaw on 12/6/15.
 */
public class Priority  implements Comparable<Priority>{

    private String mTaskname = "";
    private Calendar mCreated;
    private Calendar mDeadlineDate;
    private int mDeadline = 0;
    private int mDuration = 0;
    private int mImportance =0;
    private int mID = 0;
    private int Completed = 0;
    private String CompletedAt = " 0";

    public float mPriority = 0;

    public Priority(){

    }

    public Priority(String name, int deadline, int duration, int importance){
        setTaskName(name);
        setDeadline(deadline);
        setDuration(duration);
        setImportance(importance);
        mDeadlineDate = Calendar.getInstance();
    }

    public String getTaskname(){
        return  mTaskname;
    }

    public int getDeadline(){
        return mDeadline;
    }

    public int getDuration(){
        return mDuration;
    }

    public int getImportance(){
        return mImportance;
    }

    public int getID(){
        return mID;
    }

    public Calendar getCreated(){
        return mCreated;
    }

    public void setTaskName(String name) {
        this.mTaskname = name;
    }

    public void setDeadline(int deadline){
        this.mDeadline = deadline;
    }

    public void setDuration(int duration){
        this.mDuration = duration;
    }

    public void setImportance(int importance){
        this.mImportance = importance;
    }

    public void setCreated (Calendar c){
        this.mCreated = c;
    }

    public void setID(int id){
        this.mID=id;
    }


    public int getCompleted() {
        return Completed;
    }

    public void setCompleted(int completed) {
        Completed = completed;
    }

    public String getCompletedAt() {
        return CompletedAt;
    }

    public void setCompletedAt(String completedAt) {
        CompletedAt = completedAt;
    }

    public Calendar getDeadlineDate() {
        return mDeadlineDate;
    }

    public void setDeadlineDate(Calendar deadlineDate) {
        mDeadlineDate = deadlineDate;
    }

    public String toString(){
        return mTaskname + " by " + mDeadlineDate.toString();
    }

    public void calculatePriorityLevel(){
        float total =  0;
        total +=  (mImportance)*30;
        total +=  (mDuration)*35;
        Calendar now = Calendar.getInstance();


        long difference =  now.getTime().getTime() - mDeadlineDate.getTime().getTime() ;
        int days = (int) (difference / (1000*60*60*24));
        int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));

        total += ((5-mDeadline)*30);


        mPriority = ((total)/320)*10;
    }

    public int compareTo(Priority priority){
        calculatePriorityLevel();
        priority.calculatePriorityLevel();

        if(mPriority == priority.mPriority)
            return 0;
        else if(mPriority > priority.mPriority) {
            return -1;
        }
        return 1;
    }

    public int getPriorityLevel(){
        calculatePriorityLevel();

        return Math.min(4,Math.round(mPriority-4));
    }


}
