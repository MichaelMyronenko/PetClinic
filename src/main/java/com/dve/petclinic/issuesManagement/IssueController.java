package com.dve.petclinic.issuesManagement;

import com.dve.petclinic.issuesManagement.creation.IssueCreationModel;
import com.dve.petclinic.petsManagement.PetService;
import com.dve.petclinic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.dve.petclinic.entities.user.role.RoleName.DOCTOR;
import static com.dve.petclinic.entities.user.role.RoleName.OWNER;

@Controller
@RequestMapping("/issues")
@AllArgsConstructor
public class IssueController {
    private final IssueService issueService;
    private final PetService petService;

    @GetMapping
    public String pet(@AuthenticationPrincipal AuthenticatedUser user,
                      Model model) {
        model.addAttribute("petList", petService.getPets(user));
        model.addAttribute("issueCreationForm", new IssueCreationModel());
        if (user.hasRole(OWNER)) {
            model.addAttribute("myIssuesList", issueService.findIssuesForOwner(user));
        }
        if (user.hasRole(DOCTOR)) {
            model.addAttribute("issueList", issueService.findIssuesForDoctor(user));
        }
        return "issues";
    }

    @PostMapping
    public String pet(@AuthenticationPrincipal AuthenticatedUser user,
                      @Valid @ModelAttribute IssueCreationModel issueCreationModel,
                      Model model) {

        issueService.createIssue(issueCreationModel, user);
        return "redirect:/issues";
    }
}
