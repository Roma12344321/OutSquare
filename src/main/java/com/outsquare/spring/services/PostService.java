package com.outsquare.spring.services;

import com.outsquare.spring.models.Person;
import com.outsquare.spring.models.Post;
import com.outsquare.spring.repositories.PostRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PersonService personService;
    private final EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Post> getAllPost() {
        Session session = entityManager.unwrap(Session.class);
        List<Post> postList = session.createQuery("select p from Post p left join fetch p.postImages",Post.class).getResultList();
        for (Post post : postList) {
            Long likeCount = session.createQuery("select count(l) from PostLike l where l.post.id=:postId",Long.class)
                    .setParameter("postId",post.getId())
                    .getSingleResultOrNull();
            post.setLikeCount(likeCount);
        }
        return postList;
    }

    @Transactional
    public void createPost(Post post) {
        Person person = personService.getCurrentPerson();
        post.setPerson(person);
        post.setDate(new Date());
        postRepository.save(post);
    }
}
