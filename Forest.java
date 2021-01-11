public final class Forest {
    private String name = "Forest";
    private String rating = "Низкий";
    private String distance = "Близко";

    public String getForestInfo(){
        return (name + ' ' + ", Рейтинг: "+ rating+ ' ' + ",Расстояние: " + distance);
    }
    public String getForestRating(){
        return rating;
    }

}
