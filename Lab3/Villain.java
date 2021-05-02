public class Villain extends FairyCharacter implements ForVillain {
    private String name = "Неизвестно";
    private String mood = "Неизвестно";
    private boolean hunger = true;
    private String place = "Неизвестно";
    private String job = "Злодей";
    Money coins = new Money();
    Food meals = new Food();
    private boolean isFree = false;
    private final int money = coins.getMoney();
    private final String food = meals.getFood();

    public void pay() {
        if (hunger == true){
            System.out.println("Я готов заплатить " + money + " за обед!");

        }
    }


    public void eat(){
        System.out.println("Ммм, вкусно! Съедено " + food);
    }

    public void imagine(){
        System.out.println ("Как же хочется съесть " + food + "!");
    }


    public void say() {
        System.out.println("Я хочу сделать заказ!");
    }
    public void think() {
        if (isFree == false) {
            System.out.println("Как же хочется на свободу!");
            System.out.println("Я готов отдать за это " + money + " фертингов!");
        } else {
            System.out.println("Чудо! Я на свободе!");
        }
    }

    public void listen(Sound sound) {
        if (sound.getStickStatus() == false ){
            System.out.println("Ничего не слышно!");
        }
        else {
            System.out.println("Здесь кто-то есть!");
        }
    }



    public void broke(Stick stick) {
        if (stick.isCracked() == true) {
            System.out.println("Палка сломана!");
        } else {
            System.out.println("Ни одна ветка не хрустнула.");
        }
    }
    public void taste(Sound sound){
        System.out.println(sound.taste());
    }
    public void setIsFree(boolean isFree){
        this.isFree = isFree;
    }
    public boolean getIsFree(){
        return this.isFree;
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
    
    @Override
    public String toString() {
        return "Info about client: "
                + "Name " + this.getName()
                + ", Job " + this.getJob()
                + ", Hungry? " + this.getHunger();
    }


}
