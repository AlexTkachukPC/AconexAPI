package web.domain;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class MailFormField {
    private String identifier;
    private String value;

    public MailFormField() {
    }

    public MailFormField(String identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    public String getIdentifier() {
        return identifier;
    }

    @XmlAttribute
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getValue() {
        return value;
    }

    @XmlValue
    public void setValue(String value) {
        this.value = value;
    }
}
