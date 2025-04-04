package lk.ijse.senturaInterviewTest.senturaInterviewTest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    @NotNull
    private String uid;

    @NotNull
    private String name;

    @Size(min = 2, max = 60, message = "given name can't be more than 60 characters")
    private String given_name;

    @Size(min=2, max=60, message = "middle name can't be mor than 60 characters")
    private String middle_name;

    @Size(min=2, max=60, message = "family name can't be mor than 60 characters")
    private String family_name;

    @Size(min=2, max=60, message = "nickname name can't be mor than 60 characters")
    private String nickname;

    @Email(message = "Invalid email! Please enter valid email or email format")
    private String email;

    @Pattern(regexp = "^(\\+94|0)(7[01245678])\\d{7}$\n", message = "Invalid phone number format")
    private String phone_number;

    @Size(max = 400, message = "Comment cannot be more than 400 characters")
    private String comment;


    private String picture;
    private String directory;
    private Map<String, Object> metadata;
    private List<String> tags;


}
