package mx.taskflow.controller;
import jakarta.validation.Valid;
import mx.taskflow.model.Task;
import mx.taskflow.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
@RestController @RequestMapping("/api")
public class TaskController {
 private static final Set<String> STATES=Set.of("PENDIENTE","EN PROCESO","TERMINADA");
 private final TaskRepository repo;
 public TaskController(TaskRepository repo){this.repo=repo;}
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","UP","application","TaskFlow");}
 @GetMapping("/tasks") public List<Task> all(){return repo.findAll();}
 @PostMapping("/tasks") @ResponseStatus(HttpStatus.CREATED)
 public Task create(@Valid @RequestBody Task t){t.setId(null);check(t.getStatus());return repo.save(t);}
 @PutMapping("/tasks/{id}") public Task update(@PathVariable Long id,@Valid @RequestBody Task data){
  Task t=repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada"));
  check(data.getStatus());t.setTitle(data.getTitle());t.setDescription(data.getDescription());t.setStatus(data.getStatus());return repo.save(t);
 }
 @DeleteMapping("/tasks/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
 public void delete(@PathVariable Long id){if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada");repo.deleteById(id);}
 private void check(String s){if(s==null||!STATES.contains(s))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Estado no válido");}
}