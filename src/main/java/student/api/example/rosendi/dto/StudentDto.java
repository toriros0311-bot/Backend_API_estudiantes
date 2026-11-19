package student.api.example.rosendi.dto;
import jakarta.validation.constraints.*;

public class StudentDto {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String firstname;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastname;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    private String email;

    @Min(value = 0, message = "La edad debe ser mayor o igual a 0")
    @Max(value = 120, message = "La edad debe ser menor o igual a 120")
    private int age;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstname; }
    public void setFirstName(String firstName) { this.firstname = firstName; }
    public String getLastName() { return lastname; }
    public void setLastName(String lastName) { this.lastname = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}