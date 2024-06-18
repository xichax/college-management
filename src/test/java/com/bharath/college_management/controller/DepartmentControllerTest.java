//package com.bharath.college_management.controller;
//
//import com.bharath.college_management.config.TestConfig;
//import com.bharath.college_management.config.jwt.JwtUtils;
//import com.bharath.college_management.entity.Department;
//import com.bharath.college_management.service.DepartmentService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.Matchers.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(DepartmentController.class)
//@Import(TestConfig.class)
//@AutoConfigureMockMvc
//public class DepartmentControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private DepartmentService departmentService;
//
//    @MockBean
//    private JwtUtils jwtUtils;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    @WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
//    public void testGetAllDepartments() throws Exception {
//        List<Department> departments = Arrays.asList(
//                new Department("1", "CSE", "Computer Science", true, null, null, null, null),
//                new Department("2", "ECE", "Electronics", false, null, null, null, null)
//        );
//
//        given(departmentService.getAllDepartments()).willReturn(departments);
//
//        mockMvc.perform(get("/cm/get/d/get-all")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].departmentId", is("CSE")))
//                .andExpect(jsonPath("$[1].departmentId", is("ECE")));
//    }
//
//    @Test
//    public void testCreateDepartment() throws Exception {
//        Department department = new Department("3", "ME", "Mechanical Engineering", true, null, null, null, null);
//
//        given(departmentService.saveDepartment(Mockito.any(Department.class))).willReturn(department);
//
//        mockMvc.perform(post("/cm/d/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(department)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.departmentId", is("ME")));
//    }
//
//    @Test
//    public void testUpdateDepartment() throws Exception {
//        Department updatedDepartment = new Department("1", "CSE", "Computer Science", true, null, null, null, null);
//
//        given(departmentService.updateDepartment(Mockito.anyString(), Mockito.any(Department.class))).willReturn(updatedDepartment);
//
//        mockMvc.perform(put("/cm/d/update/{departmentId}", "1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedDepartment)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.departmentId", is("CSE")));
//    }
//
//    @Test
//    public void testDeleteDepartment() throws Exception {
//        doNothing().when(departmentService).deleteDepartment(Mockito.anyString());
//
//        mockMvc.perform(delete("/cm/d/delete/{departmentId}", "1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void testGetDepartmentsInPages() throws Exception {
//        List<Department> departments = Arrays.asList(
//                new Department("1", "CSE", "Computer Science", true, null, null, null, null),
//                new Department("2", "ECE", "Electronics", false, null, null, null, null)
//        );
//
//        given(departmentService.getDepartmentsInPages(Mockito.anyInt(), Mockito.anyInt())).willReturn(departments);
//
//        mockMvc.perform(get("/cm/d/page")
//                        .param("page", "0")
//                        .param("size", "2")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].departmentId", is("CSE")))
//                .andExpect(jsonPath("$[1].departmentId", is("ECE")));
//    }
//}
