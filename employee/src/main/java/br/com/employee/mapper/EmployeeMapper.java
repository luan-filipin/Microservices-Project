package br.com.employee.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.employee.dto.EmployeeDto;
import br.com.employee.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	EmployeeDto toDto(Employee employee);
	
	Employee toEntity(EmployeeDto employeeDto);
	
	List<EmployeeDto> toDtoList(List<Employee> list);
	
	@Mapping(target = "document", ignore = true)//Faz ignorar o campo document para update.
	void updateFromDto(EmployeeDto dto, @MappingTarget Employee entity);//Faz com que o MapStruct copie todos os campos de Dto para a Entity.

}
