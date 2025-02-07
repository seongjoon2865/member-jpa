package com.example.memberjpa.service;

import com.example.memberjpa.dto.MemberRequestDto;
import com.example.memberjpa.dto.MemberResponseDto;
import com.example.memberjpa.entity.Member;
import com.example.memberjpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(MemberRequestDto dto) {
        Member member = new Member(dto.getContent());
        Member savedMember = memberRepository.save(member);
        return new MemberResponseDto(savedMember.getId(), savedMember.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();

        List<MemberResponseDto> dtos = new ArrayList<>();
        for (Member member : members) {
            dtos.add(new MemberResponseDto(member.getId(), member.getContent()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 멤버가 없습니다.")
        );
        return new MemberResponseDto(member.getId(), member.getContent());
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberRequestDto dto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 멤버가 없습니다.")
        );

        member.update(dto.getContent());
        return new MemberResponseDto(member.getId(), member.getContent());
    }

    @Transactional
    public void deleteById(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("없습니다");
        }
        memberRepository.deleteById(id);
    }
}