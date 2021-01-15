public class Sound {
private boolean stickStatus = false;

    public String taste(){
        //method that returns message
        return "Вкусно!";
    }
    //setter for stickStatus
    public void setStickStatus(boolean stickStatus){
        this.stickStatus = stickStatus;
    }

    //getter for stickStatus
    public boolean getStickStatus(){
        return stickStatus;
    }
}
