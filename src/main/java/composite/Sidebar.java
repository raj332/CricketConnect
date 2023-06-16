package composite;

/**
 *
 * @author Bhatt Jaimin
 */
public class Sidebar{
    String icon;
    String value;
    String href;
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
   
    public Sidebar(String icon,String value,String href){
        this.icon=icon;
        this.value = value;
        this.href = href;
    }
}