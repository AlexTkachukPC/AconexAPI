package web.util;

import web.domain.Mail;
import web.domain.MailFormField;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XmlGenerator {

    public static String[] identifiers = {
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

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

    public static String[] values = {
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


    };


    public static void main(String[] args) {

        LocalDateTime time = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<MailFormField> mailFormFieldList = new ArrayList<>();
        for(int i = 0; i < values.length; i++)
            mailFormFieldList.add(new MailFormField(identifiers[i], values[i]));

        Mail mail = new Mail(0, "Systems", "Systems", "TTR - General", 268830196L, false, 2,
                time.format(pattern).toString(), false, "subject test", 268977889L, 268436355L);

        try {
            File file = new File("C:\\Users\\AlexT\\Desktop\\AconexTest\\src\\main\\resources\\mail_test.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Mail.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //jaxbMarshaller.marshal(customer, file);
            //jaxbMarshaller.marshal(customer, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
