package com.example.springboot.events;

import com.example.springboot.domain.posts.Posts;
import com.example.springboot.service.SequenceGenerator.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostsModelListener extends AbstractMongoEventListener<Posts> {
    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Posts> event) {
//        if (event.getSource().getId() < 1) { // 도큐먼트에 데이터가 없을 시 null point error
            event.getSource().setId(sequenceGenerator.generateSequence(Posts.SEQUENCE_NAME));
//        }
    }
}
