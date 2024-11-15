package com.example.demo.domain.notice.controller;

import com.example.demo.domain.notice.dto.request.NoticeRequestDTO;
import com.example.demo.domain.notice.dto.response.NoticeResponseDTO;
import com.example.demo.domain.notice.entity.Notice;
import com.example.demo.domain.notice.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // 전체 게시글 조회
    @GetMapping
    public ResponseEntity<List<NoticeResponseDTO>> getAllNotices() {
        List<Notice> notices = noticeService.findAll();
        List<NoticeResponseDTO> responseDTOs = notices.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    // 게시글 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponseDTO> getNoticeById(@PathVariable Long id) {
        Notice notice = noticeService.findById(id);
        if (notice == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(toResponseDTO(notice), HttpStatus.OK);
    }

    // 게시글 생성
    @PostMapping
    public ResponseEntity<NoticeResponseDTO> createNotice(@RequestBody NoticeRequestDTO requestDTO) {
        Notice notice = toEntity(requestDTO);
        Notice savedNotice = noticeService.save(notice);
        return new ResponseEntity<>(toResponseDTO(savedNotice), HttpStatus.CREATED);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<NoticeResponseDTO> updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDTO requestDTO) {
        Notice notice = noticeService.findById(id);
        if (notice == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        notice.setTitle(requestDTO.getTitle());
        notice.setContent(requestDTO.getContent());
        notice.setAuthor(requestDTO.getAuthor());
        Notice updatedNotice = noticeService.save(notice);
        return new ResponseEntity<>(toResponseDTO(updatedNotice), HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        noticeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Entity -> ResponseDTO 변환 메서드
    private NoticeResponseDTO toResponseDTO(Notice notice) {
        return new NoticeResponseDTO(
                notice.getId(),
                notice.getTitle(),
                notice.getContent(),
                notice.getAuthor(),
                notice.getCreatedDate(),
                notice.getUpdatedDate()
        );
    }

    // RequestDTO -> Entity 변환 메서드
    private Notice toEntity(NoticeRequestDTO requestDTO) {
        Notice notice = new Notice();
        notice.setTitle(requestDTO.getTitle());
        notice.setContent(requestDTO.getContent());
        notice.setAuthor(requestDTO.getAuthor());
        return notice;
    }
}
