public final class Forest {
    private final String name = "Forest";
    private final String rating = "Низкий";
    private final String distance = "Близко";

    //getter for Forest's info
    public String getForestInfo(){
        return (name + ' ' + ", Рейтинг: "+ rating+ ' ' + ",Расстояние: " + distance);
    }
    //getter for Forest's rating
    public String getForestRating(){
        return rating;
    }

}
