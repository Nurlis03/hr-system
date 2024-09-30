//package org.example.hrsystem.security;
//
//import lombok.AllArgsConstructor;
//import org.example.hrsystem.entity.Applicant;
//import org.example.hrsystem.entity.User;
//import org.example.hrsystem.enums.Role;
//import org.example.hrsystem.repository.ApplicantRepository;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//    private final ApplicantRepository applicantRepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        String email = oAuth2User.getAttribute("email");
//        Optional<User> userOpt = applicantRepository.findByEmail(email);
//
//        if (userOpt.isEmpty()) {
//            Applicant newApplicant = Applicant.builder()
//                    .role(Role.APPLICANT)
//                    .email(email)
//                    .firstName(oAuth2User.getAttribute("given_name"))
//                    .lastName(oAuth2User.getAttribute("family_name"))
//                    .build();
//            applicantRepository.save(newApplicant);
//        }
//
//        return oAuth2User;
//    }
//}
