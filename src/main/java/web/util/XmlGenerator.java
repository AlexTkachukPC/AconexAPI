package web.util;

import web.domain.MailFormField;
import web.domain.Mail;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XmlGenerator {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

    private static String[] identifiers = {
            "NcrType_singleSelect",
            "Drawingreference_singleLineText",
            "SpeccontractReference_singleLineText",
            "OpenedBy_singleSelect",
            "ResponsiblePerson_singleLineText",
            "DateOpened_date",
            "NcrStatus_singleSelect",
            "ExpectedClosingDate_date",
            "NcrLevel_singleSelect",
            "ElementNamecode_singleLineText",
            "DescriptionOfTheDeficiency_multiLineText",
            "CauseOfDeficiency_singleSelect",
            "ProposedCorrectiveAction_multiLineText",
            "ProposedDisposition_singleSelect"
    };

    private static String[] values = {
            "Quality",
            "N/A",
            "N/A",
            "QC",
            "N/A",
            dateFormat.format(new Date()),
            "Open",
            dateFormat.format(new Date()),
            "1",
            "N/A",
            "N/A",
            "N/A",
            "N/A",
            "N/A"

    };


    public static byte[] generateXmlMail(int totalAttachmentCount,
                                         String attribute1,
                                         String attribute2,
                                         String attribute3,
                                         long ccUserId,
                                         boolean confidential,
                                         int responseRequired,
                                         Date date,
                                         boolean richMailText,
                                         String mailSubject,
                                         long toUserId,
                                         long mailTypeId) {

        List<MailFormField> mailFormFieldList = new ArrayList<>();
        for (int i = 0; i < values.length; i++)
            mailFormFieldList.add(new MailFormField(identifiers[i], values[i]));

        Mail mail = new Mail(
                totalAttachmentCount,
                attribute1,
                attribute2,
                attribute3,
                ccUserId,
                confidential,
                responseRequired,
                sdf.format(date),
                richMailText,
                mailSubject,
                toUserId,
                mailTypeId,
                mailFormFieldList);

        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Mail.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            byteArrayOutputStream = new ByteArrayOutputStream();
            jaxbMarshaller.marshal(mail, byteArrayOutputStream);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return byteArrayOutputStream.toByteArray();
        }
    }
}
