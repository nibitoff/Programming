public interface Dinner {
    Mealplace where();
    Mealtime when();
    void changePlace(Mealplace Forest);
    void changeTime(Mealtime Day);
    int hashCode();
    boolean equals(Object otherOb);

}
