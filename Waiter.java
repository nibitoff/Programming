public class Waiter extends FairyCharacter implements ForWaiter {
    private String name = "Неизвестно";
    private String mood = "неизвестно";
    private boolean hunger = false;
    private String place = "Неизвестно";
    private String job = "Официант";
    Money coins = new Money();
    Food meals = new Food();
    private final int money = coins.getMoney();
    private final String food = meals.getFood();

    public void take() {
            System.out.println("Пожалуйста, заплатите за заказ " + money + ".");
        }

    public void give() {
        System.out.println("Держите Ваш заказ " + food + ".");
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String getJob() {
        return this.job;
    }

    @Override
    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String getPlace() {
        return this.place;
    }
    @Override
    public void setHunger(boolean hunger) {
        this.hunger = hunger;
    }

    @Override
    public boolean getHunger() {
        return this.hunger;
    }
    @Override
    public void setMood(String mood) {
        this.mood = mood;
    }

    @Override
    public String getMood() {
        return this.mood;
    }

   
}

