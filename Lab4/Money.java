public final class Money {
   /**
   class which represents actions with money (like paying and etc)
   */
   private int count;

   //method that realizes money paying,
    public void payMoney(int cost, Villain villain, Waiter waiter) {
        villain.setCoins(villain.getCoins() - cost);
        waiter.setCoins(waiter.getCoins() + cost);
    }

    //setter for count
    public void setCount(int count){
        this.count = count;
    }

    //getter for count
    public int getCount(){
        return count;
    }

    //method that checking if the count is correct
    public boolean checkCount(Villain villain, Waiter waiter) throws MoneyException {
        if (villain.getCoins() < 0 || waiter.getCoins() < 0) {
            throw new MoneyException("Ошибка! Персонажи имеют отрицательный баланс!");
        }
        else {
            return true;
        }
    }
    //method that changing value of count if it's incorrect
    public void improveCount(Villain villain, Waiter waiter){
        if(villain.getCoins() < 0){
            villain.setCoins(0);
            System.out.println("Баланс " + villain.getName() + " равен '0'.");
        }
        if(waiter.getCoins() < 0){
            waiter.setCoins(0);
            System.out.println("Баланс " + waiter.getName() + " равен '0'.");
        }
    }

}
