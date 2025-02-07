package com.example.member.service;

import com.example.member.dto.MemberRequestDto;
import com.example.member.dto.MemberResponseDto;
import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
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
        Member memo = new Member(dto.getContent());
        Member savedMemo = memberRepository.save(memo);
        return new MemberResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> memos = memberRepository.findAll();

        List<MemberResponseDto> dtos = new ArrayList<>();
        for (Member memo : memos) {
            dtos.add(new MemberResponseDto(memo.getId(), memo.getContent()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 메모가 없습니다.")
        );
        return new MemberResponseDto(member.getId(), member.getContent());
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberRequestDto dto) {
        Member memo = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 메모가 없습니다.")
        );

        memo.update(dto.getContent());
        return new MemberResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public void deleteById(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("없음 ㄲㅂ");
        }
        memberRepository.deleteById(id);
    }
}