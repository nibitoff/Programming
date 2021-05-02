public class Sound {
private boolean stickStatus = false;
    public String isSound(){
        return "Громкий треск.";
    }
    public String taste(){
        return "Вкусно!";
    }
    public void setStickStatus(boolean stickStatus){
        this.stickStatus = stickStatus;
    }

    public boolean getStickStatus(){
        return stickStatus;
    }
}
