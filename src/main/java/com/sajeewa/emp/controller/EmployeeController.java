package com.sajeewa.emp.controller;

import com.sajeewa.emp.dto.EmployeeDTO;
import com.sajeewa.emp.dto.ResponseDTO;
import com.sajeewa.emp.service.EmployeeService;
import com.sajeewa.emp.util.VarList;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin
@Tag(name = "Employee")//swagger annotaion to name the controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ResponseDTO responseDTO;

    //following line is fo http status codes expected in swagger (note that sample status are added,not accurate ones)
    @Operation(description = "this is for saving employees", summary = "saving an new Employee", responses = {
            @ApiResponse(description = "Sucess", responseCode = "200"),
            @ApiResponse(description = "Dupplicate found", responseCode = "401"),
            @ApiResponse(description = "unknown erorr", responseCode = "403"),
            @ApiResponse(description = "exception occurd", responseCode = "404")
    })

    @PostMapping("/saveEmployee")
    public ResponseEntity<ResponseDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            String res = employeeService.saveEmployee(employeeDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Dupplicate found ");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);

            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("unknown erorr ");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @Hidden//this is used to hide peticular mapping in swagger
    @PutMapping(value = "/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            String res = employeeService.updateEmployee(employeeDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("01")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Not A Registered Employee");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("getAllEmployees")
    public ResponseEntity<ResponseDTO> getAllEmployees() {
        try {
            List<EmployeeDTO> employeeList = employeeService.getAllEmployees();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("success");
            responseDTO.setContent(employeeList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/searchEmployee/{empID}")
    public ResponseEntity searchEmployee(@PathVariable int empID) {
        try {
            EmployeeDTO employeeDTO = employeeService.searchEmployee(empID);
            if (employeeDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEmployee/{empID}")
    public ResponseEntity deleteEmployee(@PathVariable int empID) {
        try {
            String res = employeeService.deleteEmployee(empID);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
