public class iOSPhone extends Phone implements printable {
    private String udid;

    public iOSPhone(String name, String imei, String displaySize, String resolution, String operatingSystem,
                    String assignee, boolean assigned, boolean available, String udid) {
        super(name, imei, displaySize, resolution, operatingSystem, assignee, assigned, available);
        this.udid = udid;
    }

    @Override
    public String objectToString() {
        return super.objectToString() + " " + udid;
    }

    @Override
    public void printAble() {
        if (iOSPhone.super.isAssigned()) {
            System.out.println("iphone is printed");
        }
            else System.out.println("iphone is not printed");
        }
    }


