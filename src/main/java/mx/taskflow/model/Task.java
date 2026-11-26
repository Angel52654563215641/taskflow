package mx.taskflow.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
@Entity @Table(name="tasks")
public class Task {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank(message="El título es obligatorio") @Size(max=100) @Column(nullable=false,length=100) private String title;
 @Size(max=500) @Column(length=500) private String description;
 @Column(nullable=false,length=20) private String status="PENDIENTE";
 @Column(nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
 public Task() {}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
 public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
 public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
 public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
}