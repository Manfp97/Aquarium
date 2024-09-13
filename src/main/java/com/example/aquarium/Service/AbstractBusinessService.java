package com.example.aquarium.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public abstract class AbstractBusinessService<E, ID, REPO extends JpaRepository<E, ID>> {
    private final REPO repo;


    protected AbstractBusinessService(REPO repo) {
        this.repo = repo;
    }

    public List<E> SearchForEntities() { return this.repo.findAll(); }
    public Set<E> SearchForEntitiesSet(){
        Set<E> eSet = new HashSet<E>(this.repo.findAll());
        return eSet;
    }

    public Optional<E> findByIdEntity(ID id) {

        return this.repo.findById(id);
    }

    public Optional<E> findById(ID id){
        return this.repo.findById(id);
    }
    public Page<E> findAll(Pageable pageable) {return repo.findAll(pageable);}
    public Set<E> findAllSet(){
        Set<E> eSet = new HashSet<E>(this.repo.findAll());
        return eSet;
    }

    public void keepById(ID id) {this.repo.save(this.repo.getOne(id));}

    public E keep(E entity) throws Exception {
        E entityKept = repo.save(entity);
        return entityKept;
    }

    public void keep(List<E> ents) throws Exception {
        Iterator<E> it = ents.iterator();

        while (it.hasNext()) {
            E e = it.next();
            repo.save(e);
        }
    }

    public void deleteById(ID id) {
        if (this.repo.existsById(id)) {
            this.repo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Entity with id" + id + " not found");
        }
    }

    public REPO getRepo() {return repo;}
}
