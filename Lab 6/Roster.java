class Roster extends KeyableMap<Student> {

    Roster(String key) {
        super(key);
    }

    Roster(String key, ImmutableMap<String, Student> students) {
        super(key, students);
    }

    @Override
    public Roster put(Student student) {
        return new Roster(this.getKey(), this.getMap().put(student.getKey(), student));
    }

    public String getGrade(String studentKey, String moduleKey, String assessmentKey) {
        return this.get(studentKey).flatMap(x -> x.get(moduleKey))
            .flatMap(x -> x.get(assessmentKey))
            .map(x -> x.getGrade()).orElse(String.format("No such record: %s %s %s", 
                        studentKey, moduleKey, assessmentKey));
    }

    public Roster add(String studentKey, String moduleKey, String assessmentKey, String grade) {
        Student s = this.get(studentKey).orElse(new Student(studentKey));
        Module m = s.get(moduleKey).orElse(new Module(moduleKey));
        return this.put(s.put(m.put(new Assessment(assessmentKey, grade))));
    } 
}

