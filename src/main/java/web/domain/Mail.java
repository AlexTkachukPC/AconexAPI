package web.domain;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;

@XmlRootElement
public class Mail {
    private byte totalAttachmentCount;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private long ccUserId;
    private boolean confidential;
    private byte responseRequired;
    private LocalDateTime responseRequiredDate;
    private boolean richMailText;
    private String mailSubject;
    private long toUserId;
    private long mailTypeId;
    private List<MailFormField> mailFormFields;

    public Mail() {
    }

    public Mail(byte totalAttachmentCount,
                String attribute1,
                String attribute2,
                String attribute3,
                long ccUserId,
                boolean confidential,
                byte responseRequired,
                LocalDateTime responseRequiredDate,
                boolean richMailText,
                String mailSubject,
                long toUserId,
                long mailTypeId,
                List<MailFormField> mailFormFields) {

        this.totalAttachmentCount = totalAttachmentCount;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.ccUserId = ccUserId;
        this.confidential = confidential;
        this.responseRequired = responseRequired;
        this.responseRequiredDate = responseRequiredDate;
        this.richMailText = richMailText;
        this.mailSubject = mailSubject;
        this.toUserId = toUserId;
        this.mailTypeId = mailTypeId;
        this.mailFormFields = mailFormFields;
    }


    public byte getTotalAttachmentCount() {
        return totalAttachmentCount;
    }

    @XmlElement
    public void setTotalAttachmentCount(byte totalAttachmentCount) {
        this.totalAttachmentCount = totalAttachmentCount;
    }

    public String getAttribute1() {
        return attribute1;
    }

    @XmlElement
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    @XmlElement
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    @XmlElement
    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public long getCcUserId() {
        return ccUserId;
    }

    @XmlElement
    public void setCcUserId(long ccUserId) {
        this.ccUserId = ccUserId;
    }

    public boolean isConfidential() {
        return confidential;
    }

    @XmlElement
    public void setConfidential(boolean confidential) {
        this.confidential = confidential;
    }

    public byte getResponseRequired() {
        return responseRequired;
    }

    @XmlElement
    public void setResponseRequired(byte responseRequired) {
        this.responseRequired = responseRequired;
    }

    public LocalDateTime getResponseRequiredDate() {
        return responseRequiredDate;
    }

    @XmlElement
    public void setResponseRequiredDate(LocalDateTime responseRequiredDate) {
        this.responseRequiredDate = responseRequiredDate;
    }

    public boolean isRichMailText() {
        return richMailText;
    }

    @XmlElement
    public void setRichMailText(boolean richMailText) {
        this.richMailText = richMailText;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    @XmlElement
    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public long getToUserId() {
        return toUserId;
    }

    @XmlElement
    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public long getMailTypeId() {
        return mailTypeId;
    }

    @XmlElement
    public void setMailTypeId(long mailTypeId) {
        this.mailTypeId = mailTypeId;
    }

    public List<MailFormField> getMailFormFields() {
        return mailFormFields;
    }

    @XmlElement
    public void setMailFormFields(List<MailFormField> mailFormFields) {
        this.mailFormFields = mailFormFields;
    }
}

//<TotalAttachmentCount>0</TotalAttachmentCount>
//<Attribute1>Systems</Attribute1>
//<Attribute2>Systems</Attribute2>
//<Attribute3>TTR - General</Attribute3>
//<CcUserId>268830196</CcUserId>
//<Confidential>false</Confidential>
//<ResponseRequired>2</ResponseRequired>
//<ResponseRequiredDate>2020-02-28T00:00:00.000Z</ResponseRequiredDate>
//<RichMailText>false</RichMailText>
//<MailSubject>fghjkl;'</MailSubject>
//<ToUserId>268977889</ToUserId>
//<MailTypeId>268436355</MailTypeId>
//<MailFormFields>
