package controller.resource;

public enum Resources {
    AVATAR("avatar", "image"),
    PLANT("plant", "image");

    private final String resource;
    private final String type;

    Resources(String resource, String type) {
        this.resource = resource;
        this.type = type;
    }

    public String getResource() {
        return this.resource;
    }

    public String getType() {
        return this.type;
    }

    public static final Resources convertStringToResource(String input) {
        if (input == null) {
            return null;
        }

        for (Resources resource : Resources.values()) {
            if (input.equals(resource.getResource())) {
                return resource;
            }
        }

        return null;
    }
}
