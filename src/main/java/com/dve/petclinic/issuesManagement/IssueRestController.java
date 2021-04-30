package com.dve.petclinic.issuesManagement;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issues")
public class IssueRestController {

    private final IssueService issueService;

    public IssueRestController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/doctor")
    public ResponseEntity<Object> findIssuesForDoctor(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(issueService.findIssuesForDoctor(pageable));
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> findIssuesForOwner(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(issueService.findIssuesForOwner(pageable));
    }

    @PutMapping("/{issueId}/assign")
    public ResponseEntity<Void> assignIssue(@PathVariable Long issueId) {
        issueService.assignIssueToMe(issueId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
