public class Eating implements Dinner{
    /**
    class which represents eating time, place and etc
    */
    private Mealplace currentPlace;
    private final String order = "Пирожки";
    private Mealtime currentTime;

    //getter for time
    public Mealtime when(){
        return currentTime;
    }

    //getter for place
    public Mealplace where(){
        return currentPlace;
    }

    //setter for place
    public void changePlace(Mealplace currentPlace){
        this.currentPlace = currentPlace;
    }

    //setter for time
    public void changeTime(Mealtime currentTime){
        this.currentTime = currentTime;
    }

    //overriding method hashcode() from java.lang.Object class
    /*@Override
    public int hashCode(){
        return 13 * order.hashCode();
    }

    //overriding method equals() from java.lang.Object class
    @Override
    public boolean equals(Object otherOb) {
        if (this == otherOb) {
            return true;
        }
        if (otherOb == null) {
            return false;
        }
        if (getClass() != otherOb.getClass()) {
            return false;
        }
        Eating other = (Eating) otherOb;
        return order.equals(other.order);

    } */
}
