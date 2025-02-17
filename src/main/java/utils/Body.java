package utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import java.util.concurrent.ThreadLocalRandom;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Body {
    private Integer id;
    private String title;
    private Boolean doneStatus;
    private String description;
    private String priority;

    public String toJson() throws Exception {
        return new ObjectMapper().writeValueAsString(this);
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
