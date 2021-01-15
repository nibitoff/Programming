public class Villain extends FairyCharacter implements ForVillain {
    Money coins = new Money();
    Food meals = new Food();
    private boolean isFree = false;
    private boolean thirst = false;
    private static String insults = "Чтоб вам провалиться!";

    // Static inner class, allows to output insults from Villain
    public static class VillainInsult{
        public static String getVillainInsults(){
           return insults;
        }
    }

    //method that outputs villain's name and message
    public void scream(){
        System.out.println( getName() + " кричит: Сколько вам нужно!?");

        // Local Inner Class
        class Demands{
            //method outputs message
            void require(){
                System.out.println( 1000
                        + " или " + 2000
                        + " или " + 3000
                        + " или " + 9000 + " фертингов!?");
            }

        }
        Demands demands = new Demands();
        demands.require();
    }
    //method that checks villain's hunger
    public void pay(Villain villain, Waiter waiter) {
        if (hunger){
            System.out.println(getName() + " оплачивает " +  meals.getFoodCost() + " за обед!");
            //method that represent paying action (from Money.payMoney())
            coins.payMoney(1000, villain, waiter);
        }
    }

    //method that shows that food was eaten (from Food.getFood())
    public void eat(){
        System.out.println("Ммм, " + meals.getFood() +" съедены");
    }

    //method that output what kind of food villain wants to eat (from Food.getFood())
    public void imagine(){
        System.out.println ("Как же хочется съесть " + meals.getFood() + "!");
    }


    //method that output message
    public void say() {
        System.out.println("Я хочу сделать заказ!");
    }
    //method that check villain's freedom
    public void think() {
        if (!isFree) {
            System.out.println("Как же хочется на свободу!");
            System.out.println("Я готов отдать за это " + 1000 + " фертингов!");
        } else {
            System.out.println("Чудо! Я на свободе!");
        }
    }

    //method that check Sound.stickStatus
    public void listen(Sound sound) {
        if (!sound.getStickStatus()){
            System.out.println("Ничего не слышно!");
        }
        else {
            System.out.println("Здесь кто-то есть!");
        }
    }

    //method that check if villain is thirsty
    public void haveThirst(){
        if(getThirst()){
            System.out.println(getName() + " мучает жажда!");
        }
    }

    //method that check if stick is cracked
    public void broke(Stick stick) {
        if (stick.isCracked()) {
            System.out.println("Палка сломана!");
        } else {
            System.out.println("Ни одна ветка не хрустнула.");
        }
    }

    //method that output method from Sound.taste
    public void taste(Sound sound){
        System.out.println(sound.taste());
    }

    //setter for villain's freedom
    public void setIsFree(boolean isFree){
        this.isFree = isFree;
    }
    //getter for villain's freedom
    public boolean getIsFree(){
        return this.isFree;
    }

    //getter for vilain's money
    public int getCoins() {
        return coins.getCount();
    }

    //setter for villain's money
    public void setCoins(int coins) {
        this.coins.setCount(coins);
    }

    //getter for vilain's thirst
    public boolean getThirst() {
        return thirst;
    }

    //setter for villain's thirst
    public void setThirst(boolean thirst) {
        this.thirst = thirst;
    }

    // methods below override abstract methods from FairyCharacter abstract class
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

    //overriding toString from java.lang.Object class
    @Override
    public String toString() {
        return "Info about client: "
                + "Name " + this.getName()
                + ", Job " + this.getJob()
                + ", Hungry? " + this.getHunger();
    }


}
