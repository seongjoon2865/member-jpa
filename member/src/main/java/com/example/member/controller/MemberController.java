package com.example.member.controller;

import com.example.member.dto.MemberRequestDto;
import com.example.member.dto.MemberResponseDto;
import com.example.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberResponseDto> save(@RequestBody MemberRequestDto dto) {
        return ResponseEntity.ok(memberService.save(dto));
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> update(@PathVariable Long id, @RequestBody MemberRequestDto dto) {
        return ResponseEntity.ok(memberService.update(id, dto));
    }

    @DeleteMapping("/members/{id}")
    public void delete(@PathVariable Long id) {
        memberService.deleteById(id);
    }
}