package productivity.yaw.asare.ordr;

import java.util.Calendar;

/**
 * Created by yaw on 12/6/15.
 */
public class Priority {

    private String mTaskname = "";
    private Calendar mCreated;
    private Calendar mDeadlineDate;
    private int mDeadline = 0;
    private int mDuration = 0;
    private int mImportance =0;
    private int mID = 0;
    private int Completed = 0;
    private String CompletedAt = " 0";

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
}
