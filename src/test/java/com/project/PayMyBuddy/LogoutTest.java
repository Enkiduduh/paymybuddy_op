//package com.project.PayMyBuddy;
//
//import com.project.PayMyBuddy.config.SecurityConfig;
//import com.project.PayMyBuddy.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest  // charge uniquement le dispatcher + filtres (incl. security)
//@Import(SecurityConfig.class) // importe votre config Spring Security
//@AutoConfigureMockMvc
//class LogoutTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    // on stub les beans requis par SecurityConfig
//    @MockBean UserService userService;
//    @MockBean PasswordEncoder passwordEncoder;
//
//    @Test
//    void logout_withCsrf_andAuthenticatedUser_redirectsToConnexionLogout() throws Exception {
//        mockMvc.perform(post("/logout")
//                        .with(csrf())
//                        .with(user("joe@test.fr").roles("USER")))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/connexion?logout"));
//    }
//}
