class RecycledLoader extends Loader {
    private static final int m1 = 60;

    public RecycledLoader(int id) {
        super(id);
    } 

    @Override
    public int extraTime() {
        return m1;
    }

    @Override
    public String toString() {
        return "Recycled Loader #" + super.getID();
    }
}


