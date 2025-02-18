package utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.ThreadLocalRandom;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement(localName = "todo")
public class Body {

    @JacksonXmlProperty(localName = "id")
    private Integer id;

    @JacksonXmlProperty(localName = "title")
    private String title;

    @JacksonXmlProperty(localName = "doneStatus")
    private Boolean doneStatus;

    @JacksonXmlProperty(localName = "description")
    private String description;
    private String priority;

    public Body() {}

    public String toJson() throws Exception {
        return new ObjectMapper().writeValueAsString(this);
    }

    public String toXML() throws Exception {
        return new XmlMapper().writeValueAsString(this);
    }

    public static String getRandomString(int number) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < number; i++) {
            int index = random.nextInt(characters.length());
            builder.append(characters.charAt(index));
        }

        return builder.toString();
    }

    public static int getRandomNumber() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(50);
    }

    public static String getRandomBody() throws Exception {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        Body body = new Body();
        body.setTitle(getRandomString(getRandomNumber()));  // Ensure title is not empty
        body.setDescription(getRandomString(getRandomNumber()));  // Ensure description is not empty
        body.setDoneStatus(random.nextBoolean()); // Generate a random boolean
        return body.toJson();
    }
}
