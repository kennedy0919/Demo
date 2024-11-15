package com.example.demo.domain.notice.service;

import com.example.demo.domain.notice.entity.Notice;
import com.example.demo.domain.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    @Override
    public Notice findById(Long id) {
        return noticeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Notice save(Notice notice) {
        return noticeRepository.save(notice);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        noticeRepository.deleteById(id);
    }
}
