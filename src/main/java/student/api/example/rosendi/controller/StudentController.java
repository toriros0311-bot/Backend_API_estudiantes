package student.api.example.rosendi.controller;

import student.api.example.rosendi.dto.StudentDto;
import student.api.example.rosendi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "https://toriros0311-bot.github.io") // <-- Agrega esta línea
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> listarEstudiantes() {
        return ResponseEntity.ok(studentService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> obtenerEstudiantePorId(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> crearEstudiante(@Valid @RequestBody StudentDto studentDto) {
        StudentDto nuevo = studentService.crear(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> actualizarEstudiante(@PathVariable Long id, @Valid @RequestBody StudentDto studentDto) {
        StudentDto actualizado = studentService.actualizar(id, studentDto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        studentService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
