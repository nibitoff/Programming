public interface ForWaiter {
    /**
    interface for Waiter class
    */
    void take();
    void give(Waiter waiter);
    void setName(String name);
    String getName();
    void setJob(String job);
    void setHunger(boolean hunger);
    void setMood(String mood);

}
