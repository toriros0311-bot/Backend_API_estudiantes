package student.api.example.rosendi.service;

import student.api.example.rosendi.dto.StudentDto;
import java.util.List;

public interface StudentService {
    List<StudentDto> listarTodos();
    StudentDto obtenerPorId(Long id);
    StudentDto crear(StudentDto studentDto);
    StudentDto actualizar(Long id, StudentDto studentDto);
    void eliminar(Long id);
}