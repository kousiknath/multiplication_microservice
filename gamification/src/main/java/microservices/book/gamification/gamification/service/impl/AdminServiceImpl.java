package microservices.book.gamification.gamification.service.impl;

import microservices.book.gamification.gamification.repository.BadgeCardRepository;
import microservices.book.gamification.gamification.repository.ScoreCardRepository;
import microservices.book.gamification.gamification.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final BadgeCardRepository badgeCardRepository;
    private final ScoreCardRepository scoreCardRepository;

    @Autowired
    public AdminServiceImpl(final BadgeCardRepository badgeCardRepository,
                            final ScoreCardRepository scoreCardRepository){
        this.badgeCardRepository = badgeCardRepository;
        this.scoreCardRepository = scoreCardRepository;

    }
    @Override
    public void deleteDatabaseContents() {
        badgeCardRepository.deleteAll();
        scoreCardRepository.deleteAll();
    }
}
