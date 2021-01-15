public final class Canteen {
    private final String name = "Canteen";
    private final String rating = "Высокий";
    private final String distance = "Далеко";
//getter for Canteen's info
    public String getCanteenInfo(){
        return (name + ' ' + ", Рейтинг: " + rating+ ' ' + ",Расстояние: " + distance);
    }
    //getter for  Canteen's rating
    public String getCanteenRating(){
        return rating;
    }
}
