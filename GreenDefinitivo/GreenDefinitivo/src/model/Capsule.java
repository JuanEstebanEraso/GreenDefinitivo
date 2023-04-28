package model;

public class Capsule {

    private String id;
    private String description;
    private CapsuleType type;

    private String learnedLesson;

	public enum CapsuleType {
        TECHNICAL,
       	MANAGEMENT,
    	DOMAIN,
		EXPERIENCES
    }


    public Capsule(String id, String description, CapsuleType type, String learnedLesson) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.learnedLesson = learnedLesson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CapsuleType getType() {
        return type;
    }

    public void setType(CapsuleType type) {
        this.type = type;
    }

    public String getLearnedLesson() {
        return learnedLesson;
    }

    public void setLearnedLesson(String learnedLesson) {
        this.learnedLesson = learnedLesson;
    }

    public String toString() {
        return "Capsule{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", learnedLesson='" + learnedLesson + '\'' +
                '}';
    }
}
