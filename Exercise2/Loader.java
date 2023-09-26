class Loader {
    private final int id;

    public Loader(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public int extraTime() {
        return 0;
    }

    @Override
    public String toString() {
        return "Loader #" + this.id;
    }
}
