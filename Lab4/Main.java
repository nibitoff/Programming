public class Main {
    public static void main(String[] args) throws FreedomError{
        /** 
        Main class 
        */
        //creates new objects from different classes
        Villain villain = new Villain();
        Waiter waiter = new Waiter();
        Money money = new Money();
        Stick stick = new Stick();
        Sound sound = new Sound();
        Eating eating = new Eating();
        Forest forest = new Forest();
        Canteen canteen = new Canteen();

        //set villain's characteristics
        villain.setName("Скуперфильд");
        villain.setHunger(true);
        villain.setJob("Банкир");
        villain.setMood("Нейтральное");
        villain.setIsFree(false);
        villain.setCoins(1000);

        //set waiter's characteristics
        waiter.setName("Вася");
        waiter.setMood("Нейтральное");
        waiter.setJob("Официант");
        waiter.setHunger(false);
        waiter.setCoins(0);

        //check if villain is free
        try {
            if (villain.getIsFree()) {
                //if villain is free throws an exception
                throw new FreedomError("Ошибка! Злодей на свободе!");

            }

                eating.changePlace(Mealplace.FOREST);//change place of action
                eating.changeTime(Mealtime.BREAKFAST);//change time of action
                System.out.println("Вы очутились в " + forest.getForestInfo());
                sound.setStickStatus(false);
                if (eating.where() == Mealplace.FOREST) {
                    // checks if place of action is forest
                    villain.breath = false;
                    villain.listen(sound);
                    villain.broke(stick);
                }


                if (villain.getCoins() <= 1000 && eating.where() == Mealplace.FOREST) {
                    //checks if villain has enough money and he is located at Forest
                    villain.setMood("Хорошее");
                    villain.setHunger(true);
                    System.out.println("Хочется есть!");
                    villain.imagine();
                }

                //changes time and place of action
                eating.changePlace(Mealplace.CANTEEN);
                eating.changeTime(Mealtime.DINNER);

                //checks place of action and time
                if (eating.where() == Mealplace.CANTEEN && eating.when() == Mealtime.DINNER) {
                    System.out.println("Вы очутились в " + canteen.getCanteenInfo());
                    villain.say();
                    System.out.println("Официант " + waiter.getName() + " узнает данные о клиенте.");
                    System.out.println(villain.toString());
                    waiter.take();
                    //checking MoneyException, if character's money is less than 0
                    try {
                        money.checkCount(villain, waiter);
                        villain.pay(villain, waiter);//villain pays money
                        System.out.println("Деньги " + waiter.getName() + ": " + waiter.getCoins());
                        System.out.println("Деньги " + villain.getName() + ": " + villain.getCoins());
                        waiter.give(waiter);
                        villain.eat();
                        villain.taste(sound);
                    }
                    //if value of money is not correct we catch and change it's value by 0
                    catch (MoneyException moneyException){
                        System.out.println(moneyException.getMessage());
                        System.out.println("Баланс был принудительно изменен на '0'!");
                        money.improveCount(villain, waiter);
                    }
                    villain.think();
                    villain.scream();

                    //using static class
                    Villain.VillainInsult villainInsult = new Villain.VillainInsult();
                    System.out.println(waiter.getName() + ", " + Villain.VillainInsult.getVillainInsults());


                    //Anonymous inner class
                    GiveRegrets giveRegrets = new GiveRegrets() {
                        @Override
                        public void giveRegrets() {
                            System.out.println("Эхх, цена была слишком высокой...");
                        }
                    };
                    //realization of anonymous class
                    giveRegrets.giveRegrets();
                    //set villain's thirst
                    villain.setThirst(true);
                    villain.haveThirst();
                }

            }
        catch (FreedomError freedomError){
            System.out.println(freedomError.getMessage());
            System.out.println(villain.getName() + " на свободе!");
        }
        }

    }





