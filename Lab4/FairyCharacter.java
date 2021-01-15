public abstract class FairyCharacter {
    protected String name;
    protected String mood;
    protected boolean hunger;
    protected String place;
    protected String job;
    protected boolean breath;

   //abstracts methods
    public abstract void setName(String name);
    public abstract String getName();

    public abstract void setMood(String mood);

    public abstract void setHunger(boolean hunger);
    public abstract boolean getHunger();

    public abstract void setJob(String job);
    public abstract String getJob();





}
