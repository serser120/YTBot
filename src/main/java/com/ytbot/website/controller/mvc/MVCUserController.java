package com.ytbot.website.controller.mvc;

import com.ytbot.website.dto.*;
import com.ytbot.website.constants.Errors;
import com.ytbot.website.service.ServerService;
import com.ytbot.website.service.UserService;
import com.ytbot.website.service.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.security.auth.message.AuthException;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.ytbot.website.constants.UserRolesConstants.ADMIN;

@Controller
@RequestMapping("users")
@Hidden
@Slf4j
public class MVCUserController {

    private final UserService userService;
    private final ServerService serverService;

    public MVCUserController(UserService userService, ServerService serverService) {
        this.userService = userService;
        this.serverService = serverService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userDTO,
                               BindingResult bindingResult) {
        if (userDTO.getLogin().equalsIgnoreCase(ADMIN) || userService.getUserByLogin(userDTO.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
            return "registration";
        }
        if (userService.getUserByEmail(userDTO.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email", "Такой email уже существует");
            return "registration";
        }
        userService.create(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/remember-password")
    public String rememberPassword() {
        return "users/rememberPassword";
    }

    @PostMapping("/remember-password")
    public String rememberPassword(@ModelAttribute("changePasswordForm") UserDTO userDTO) {
        userDTO = userService.getUserByEmail(userDTO.getEmail());
        if (Objects.isNull(userDTO)) {
            return "redirect:/error";
        } else {
            userService.sendChangePasswordEmail(userDTO);
            return "redirect:/login";
        }
    }

    @GetMapping("/change-password")
    public String changePassword(@PathParam(value = "uuid") String uuid,
                                 Model model) {
        model.addAttribute("uuid", uuid);
        return "users/changePassword";
    }

    @PostMapping("/change-password")
    public String changePassword(@PathParam(value = "uuid") String uuid,
                                 @ModelAttribute("changePasswordForm") UserDTO userDTO) {
        userService.changePassword(uuid, userDTO.getPassword());
        return "redirect:/login";
    }

    @GetMapping("")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "login"));
        Page<UserDTO> userDTOS = userService.getAllUsersWithServers(pageRequest);
        model.addAttribute("users", userDTOS);
        return "users/viewAllUsers";
    }


    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model) throws AuthException {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.isNull(customUserDetails.getUserId())) {
            if (id.intValue() != customUserDetails.getUserId()) {
                if (userService.getUser(customUserDetails.getUserId().longValue()).getRole().getId() == 1) {
                    throw new AuthException(HttpStatus.FORBIDDEN + ": " + Errors.Users.USER_FORBIDDEN_ERROR);
                }
            }
        }
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("servers", userService.getUser(id).getServers());
        return "users/viewUser";
    }

//    @ExceptionHandler(AuthException.class)
//    public RedirectView handleErrorAuth(HttpServletRequest req,
//                                        Exception ex,
//                                        RedirectAttributes redirectAttributes) {
//        log.error("Запрос: " + req.getRequestURL() + " вызвал ошибку " + ex.getMessage());
//        redirectAttributes.addFlashAttribute("exception", ex.getMessage());
//        return new RedirectView("/", true);
//    }


    @PostMapping("/search")
    public String searchUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "5") int pageSize,
                              @ModelAttribute("userSearchForm") UserSearchDTO userSearchDTO,
                              Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "login"));
        model.addAttribute("users", userService.findUsers(userSearchDTO, pageRequest));
        return "users/viewAllUsers";
    }

    @GetMapping("/addServer/{id}")
    public String addServer(@PathVariable Long id, Model model) {
        return "users/addServer";
    }

    @PostMapping("/addServer/{id}")
    public String addServer(@ModelAttribute("serverForm") ServerDTO serverFormDTO,
                            @PathVariable Long id, Model model) {
        ServerDTO serverDTO = serverService.getServerByDiscordID(serverFormDTO.getServerDiscordID());
        if (Objects.isNull(serverDTO)) {
            return "redirect:/error";
        } else {
            userService.sendChangeServerDTO(id, serverDTO);
            return "redirect:/users/" + id;
        }
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("userEditForm", new UserDTO());
        return "users/editUser";
    }

    @PostMapping("edit/{id}")
    public String editUser(@ModelAttribute("userEditForm") UserDTO userFormDTO,
                           @PathVariable Long id, Model model) {
        userService.editUser(userFormDTO, id);
        return "redirect:/users/" + id;
    }

    @GetMapping("/{userId}/delete/{serverId}")
    public String deleteServer(@PathVariable Long userId, @PathVariable Long serverId) {
        userService.deleteServerFromUser(userId, serverId);
        return "redirect:/users/" + userId;
    }

}

