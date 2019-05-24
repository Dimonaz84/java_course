package pl.stqa.pft.addressbook;

public class GroupData {
    private final String name;
    private final String headerName;
    private final String footer;

    public GroupData(String name, String headerName, String footer) {
        this.name = name;
        this.headerName = headerName;
        this.footer = footer;
    }

    public String getName() {
        return name;
    }

    public String getHeaderName() {
        return headerName;
    }

    public String getFooter() {
        return footer;
    }
}
