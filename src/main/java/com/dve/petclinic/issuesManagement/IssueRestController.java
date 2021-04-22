package com.dve.petclinic.issuesManagement;

import com.dve.petclinic.issuesManagement.reading.IssueResponseModel;
import com.dve.petclinic.security.AuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueRestController {

    private final IssueService issueService;

    public IssueRestController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public ResponseEntity<List<IssueResponseModel>> getIssues(@AuthenticationPrincipal AuthenticatedUser user) {
        return new ResponseEntity<>(issueService.findIssuesForDoctor(user), HttpStatus.OK);
    }

    @PutMapping("/{issueId}/assign")
    public ResponseEntity<Void> assignIssue(@PathVariable Long issueId,
                                            @AuthenticationPrincipal AuthenticatedUser user) {
        issueService.assignIssueToMe(issueId, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
