public abstract class Phone {
    private String name;
    private String imei;
    private String displaySize;
    private String resolution;
    private String operatingSystem;
    private String assignee;
    private boolean assigned;
    private boolean available;

    public Phone(String name, String imei, String displaySize, String resolution,
                  String operatingSystem, String assignee, boolean assigned, boolean available) {
        this.name = name;
        this.imei = imei;
        this.displaySize = displaySize;
        this.resolution = resolution;
        this.operatingSystem = operatingSystem;
        this.assignee = assignee;
        this.assigned = assigned;
        this.available = available;
        if (assigned){
            this.available = false;
        }
    }
    public String objectToString (){
        return name + " " + imei + " " + displaySize + " " + resolution + " " +
                operatingSystem + " " + assignee + " " + assigned + " " + " " + available;
    }
    public String objectBeautifyOutput (){
        return " Device name: " + name + "\n " + "Device imei: " + imei + "\n " + "Display size: " +  displaySize + "\n " +
                "Display resolution: " + resolution + "\n" + " Operating system: " + operatingSystem +"\n" + " Assignee:  " + assignee + "\n " +
                "Assigned: " + assigned + "\n " + "Available:  " + available;
    }
    public void vibrate (){
        System.out.println("vibrating");
    }

    public String getName() {
        return name;
    }
    public String getDisplaySize() {
        return displaySize;
    }
    public String getOperatingSystem() {
        return operatingSystem;
    }

    public boolean isAssigned() {
        return assigned;
    }
}
