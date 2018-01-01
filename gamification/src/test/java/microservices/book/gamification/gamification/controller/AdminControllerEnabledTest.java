package microservices.book.gamification.gamification.controller;

import microservices.book.gamification.gamification.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@WebMvcTest(AdminController.class)
public class AdminControllerEnabledTest {
    @MockBean
    private AdminService adminService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void databaseDeletionTest() throws Exception{
        MockHttpServletResponse response = mvc.perform(post("/gamification/admin/delete-db")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(adminService).deleteDatabaseContents();
    }
}
