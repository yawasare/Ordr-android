package productivity.yaw.asare.ordr;

/**
 * Created by yaw on 12/6/15.
 */
public class Priority {

    private String mTaskname = "";
    private int mDeadline = 0;
    private int mDuration = 0;
    private int mImportance =0;
    private int mID = 0;

    public Priority(){

    }

    public Priority(String name, int deadline, int duration, int importance){
        setTaskName(name);
        setDeadline(deadline);
        setDuration(duration);
        setImportance(importance);
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

    public void setID(int id){
        this.mID=id;
    }
    @Override
    public String toString(){
        return "Priority: " + mTaskname + "\n Deadline: " + mDeadline + " \n Duration: "+
                mDuration + "\n Importance: " + mImportance;
    }
}
