public class Eating implements Dinner{
    private Mealplace currentPlace;
    private String order = "Пирожки";
    private Mealtime currentTime;

    public Mealtime when(){
        return currentTime;
    }

    public Mealplace where(){
        return currentPlace;
    }
    public void changePlace(Mealplace currentPlace){
        this.currentPlace = currentPlace;
    }

    public void changeTime(Mealtime currentTime){
        this.currentTime = currentTime;
    }

    public String lookOrder(){
       return order;
    }
    @Override
    public int hashCode(){
        return 13 * order.hashCode();
    }
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

    }
}
