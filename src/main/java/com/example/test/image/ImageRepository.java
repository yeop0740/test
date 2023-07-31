package com.example.test.image;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Image image) {

        em.persist(image);

    }

    public Image findOne(Long id) {

        return em.find(Image.class, id);

    }

    public List<Image> findImages() {

        return em.createQuery("select i from Image i", Image.class)
                .getResultList();

    }

    public void deletePostImage(PostImage image) {

        em.remove(image);

    }
}
