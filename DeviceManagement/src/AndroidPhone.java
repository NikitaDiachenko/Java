public class AndroidPhone extends Phone implements printable {
    private boolean rooted;

    public AndroidPhone(String name, String imei, String displaySize, String resolution, String operatingSystem,
                        String assignee, boolean assigned, boolean available, boolean rooted) {
        super(name, imei, displaySize, resolution, operatingSystem, assignee, assigned, available);
        this.rooted = rooted;
    }
    @Override
    public String objectToString() {
        return super.objectToString() + " " + rooted;
    }
    public void printAble() {
        if (AndroidPhone.super.isAssigned()) {
            System.out.println("android is printed");
        }
        else System.out.println("android is not printed");
    }

}

