class Student extends KeyableMap<Module> {

    Student(String key) {
        super(key);
    }

    Student(String key, ImmutableMap<String, Module> modules) {
        super(key, modules);
    }

    @Override
    public Student put(Module module) {
        return new Student(this.getKey(), this.getMap().put(module.getKey(), module));
    }
}

