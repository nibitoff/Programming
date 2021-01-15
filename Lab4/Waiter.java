public class Waiter extends FairyCharacter implements ForWaiter {
    /**
    Class that represents waiter's actions and values.
    It extends FairyCharacter abstract class.
    */
    Money coins = new Money();
    Food meals = new Food();

    //method that outputs message and food's cost
    public void take() {
            System.out.println("Пожалуйста, заплатите за заказ " + meals.getFoodCost() + ".");
        }

    //method that checks if waiter got necessary value of money
    public void give(Waiter waiter) {
            if (waiter.getCoins() == meals.getFoodCost()) {
                //if true, outputs message and Food.getFood()
                System.out.println("Держите Ваш заказ " + meals.getFood() + ".");
            }
        }

    //getter for waiter's money
    public int getCoins() {
        return coins.getCount();
    }

    //setter for waiter's money
    public void setCoins(int coins) {
        this.coins.setCount(coins);
    }
    //methods methods below override abstract methods from FairyCharacter abstract class
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


}

