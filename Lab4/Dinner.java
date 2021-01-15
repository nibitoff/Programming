public interface Dinner {
    /** 
    interface which works with Eating class
    */
    Mealplace where();
    Mealtime when();
    void changePlace(Mealplace Forest);
    void changeTime(Mealtime Day);


}
