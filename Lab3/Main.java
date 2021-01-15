public class Main {
    public static void main(String[] args) {
        Villain villain = new Villain();
        Waiter waiter = new Waiter();
        Money money = new Money();
        Food food = new Food();
        Stick stick = new Stick();
        Sound sound = new Sound();
        Eating eating = new Eating();
        Forest forest = new Forest();
        Canteen canteen = new Canteen();

        villain.setName("Скуперфильд");
        villain.setHunger(false);
        villain.setJob("Банкир");
        villain.setPlace("Forest");
        villain.setMood("Нейтральное");
        villain.setIsFree(false);

        waiter.setName("Вася");
        waiter.setPlace("Canteen");
        waiter.setMood("Нейтральное");
        waiter.setJob("Официант");
        waiter.setHunger(false);

        if (villain.getIsFree() == false) {
            eating.changePlace(Mealplace.FOREST);
            eating.changeTime(Mealtime.BREAKFAST);
            System.out.println("Вы очутились в " + forest.getForestInfo());
            sound.setStickStatus(false);
            if (eating.where() == Mealplace.FOREST) {
                villain.breath = false;
                villain.listen(sound);
                villain.broke(stick);
            }


            if (money.getMoney() == 1000 && eating.where() == Mealplace.FOREST) {
                villain.setMood("Хорошее");
                villain.setHunger(true);
                System.out.println("Хочется есть!");
                villain.imagine();
            }

            eating.changePlace(Mealplace.CANTEEN);
            eating.changeTime(Mealtime.DINNER);

            if (eating.where() == Mealplace.CANTEEN && eating.when() == Mealtime.DINNER) {
                System.out.println("Вы очутились в " + canteen.getCanteenInfo());
                villain.say();
                System.out.println("Официант " + waiter.getName() + " узнает данные о клиенте.");
                System.out.println(waiter.toString(villain));
                villain.pay();
                System.out.println(villain.getName() + " оплачивает обед.");
                waiter.take();
                String r1 = canteen.getCanteenRating();
                String r2 = forest.getForestRating();
                waiter.give();
                villain.taste(sound);
                villain.think();
                System.out.println("Переведем рейтинг " + Mealplace.CANTEEN + " в hashcode: " + r1.hashCode());
                System.out.println("Сравним рейтинги " + Mealplace.CANTEEN + " и " + Mealplace.FOREST + ":" + r1.equals(r2) );

            }

        }
        else{
            villain.think();
        }
    }

    }


