public class Canteen {
    private String name = "Canteen";
    private String rating = "Высокий";
    private String distance = "Далеко";

    public String getCanteenInfo(){
        return (name + ' ' + ", Рейтинг: " + rating+ ' ' + ",Расстояние: " + distance);
    }
    public String getCanteenRating(){
        return rating;
    }
}
