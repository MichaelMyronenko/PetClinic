package com.dve.petclinic.issuesManagement;

import com.dve.petclinic.issuesManagement.creation.IssueCreationModel;
import com.dve.petclinic.issuesManagement.creation.IssueCreationValidator;
import com.dve.petclinic.issuesManagement.reading.IssueResponseModel;
import com.dve.petclinic.issuesManagement.update.IssueUpdateModel;
import com.dve.petclinic.petsManagement.PetService;
import com.dve.petclinic.security.AuthenticatedUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.dve.petclinic.entities.user.role.RoleName.DOCTOR;
import static com.dve.petclinic.entities.user.role.RoleName.OWNER;

@Controller
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;
    private final PetService petService;
    private final IssueCreationValidator creationValidator;


    public IssueController(IssueService issueService, PetService petService, IssueCreationValidator creationValidator) {
        this.issueService = issueService;
        this.petService = petService;
        this.creationValidator = creationValidator;
    }

    @InitBinder("issueCreationForm")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(creationValidator);
    }

    @GetMapping
    public String issues(@AuthenticationPrincipal AuthenticatedUser user,
                         @PageableDefault Pageable pageable,
                         Model model) {
        model.addAttribute("petList", petService.getPets(user));
        model.addAttribute("issueCreationForm", new IssueCreationModel());

        if (user.hasRole(OWNER)) {
            model.addAttribute("myIssuesList", issueService.findIssuesForOwner(pageable));
        }

        if (user.hasRole(DOCTOR)) {
            model.addAttribute("issueList", issueService.findIssuesForDoctor(pageable));
        }
        return "issues";
    }

    @PostMapping
    public String createIssue(@Valid @ModelAttribute IssueCreationModel issueCreationModel,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "issues";
        }

        issueService.createIssue(issueCreationModel);
        return "redirect:/issues";
    }

    @GetMapping("/{issueId}")
    public String issue(@PathVariable Long issueId,
                        Model model) {

        IssueResponseModel responseModel = issueService.getIssue(issueId);

        model.addAttribute("issue", responseModel);
        model.addAttribute("issueUpdate", new IssueUpdateModel());
        return "issue";
    }

    @PostMapping("/{issueId}/edit")
    public String issue(@PathVariable Long issueId,
                        @ModelAttribute @Valid IssueUpdateModel updateModel,
                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "issue";
        }
        issueService.editIssue(issueId, updateModel);
        return "redirect:/issues/" + issueId;
    }
}
