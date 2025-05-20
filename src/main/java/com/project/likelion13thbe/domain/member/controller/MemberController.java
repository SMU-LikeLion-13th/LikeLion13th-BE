package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@Tag(name="Member", description="Member 관련 API입니다.")
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    // Create
    @PostMapping
    public ResponseEntity<MemberResDTO.MemberCreateResDTO> createMember(
            @RequestBody MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberCommandService.createMember(memberCreateReqDTO));
    }

    // Read
    @GetMapping
    public ResponseEntity<MemberResDTO.MemberDTO> getMemberById(@RequestParam(name = "id") Long id) {
        MemberResDTO.MemberDTO dto = memberQueryService.getMemberById(id);
        return ResponseEntity.ok(dto);
    }

    // Update
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateMember(
            @PathVariable Long id,
            @RequestBody MemberReqDTO.MemberUpdateReqDTO memberUpdateReqDTO
    ) {
        memberCommandService.updateMember(id, memberUpdateReqDTO);
        return ResponseEntity.ok("Member Updated Successfully.");
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberCommandService.deleteMember(id);
        return ResponseEntity.ok("Member Deleted Successfully.");
    }
}
