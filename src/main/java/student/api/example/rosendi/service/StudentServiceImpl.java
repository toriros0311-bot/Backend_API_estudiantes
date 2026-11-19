package student.api.example.rosendi.service;

import student.api.example.rosendi.dto.StudentDto;
import student.api.example.rosendi.entity.Student;
import student.api.example.rosendi.exception.ResourceNotFoundException;
import student.api.example.rosendi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private StudentDto convertirADto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setAge(student.getAge());
        return dto;
    }

    private Student convertirAEntity(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        return student;
    }

    @Override
    public List<StudentDto> listarTodos() {
        return studentRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto obtenerPorId(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El estudiante con id " + id + " no existe"));
        return convertirADto(student);
    }

    @Override
    public StudentDto crear(StudentDto studentDto) {
        if (studentRepository.existsByEmail(studentDto.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        Student student = convertirAEntity(studentDto);
        Student saved = studentRepository.save(student);
        return convertirADto(saved);
    }

    @Override
    public StudentDto actualizar(Long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El estudiante con id " + id + " no existe"));

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setAge(studentDto.getAge());

        Student updated = studentRepository.save(student);
        return convertirADto(updated);
    }

    @Override
    public void eliminar(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("El estudiante con id " + id + " no existe");
        }
        studentRepository.deleteById(id);
    }
}