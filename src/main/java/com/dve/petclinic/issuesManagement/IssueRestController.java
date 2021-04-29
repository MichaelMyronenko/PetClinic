package com.dve.petclinic.issuesManagement;

import com.dve.petclinic.issuesManagement.reading.IssueResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueRestController {

    private final IssueService issueService;

    public IssueRestController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<IssueResponseModel>> findIssuesForDoctor() {
        return new ResponseEntity<>(issueService.findIssuesForDoctor(), HttpStatus.OK);
    }

    @GetMapping("/owner")
    public ResponseEntity<List<IssueResponseModel>> findIssuesForOwner() {
        return new ResponseEntity<>(issueService.findIssuesForOwner(), HttpStatus.OK);
    }

    @PutMapping("/{issueId}/assign")
    public ResponseEntity<Void> assignIssue(@PathVariable Long issueId) {
        issueService.assignIssueToMe(issueId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
