package mx.edu.utez.marketPlace.category.controller;

import mx.edu.utez.marketPlace.category.model.Category;
import mx.edu.utez.marketPlace.category.model.CategoryRepository;
import mx.edu.utez.marketPlace.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    public ResponseEntity<Message> findAll(){
        return new ResponseEntity<>(new Message("Ok!", false, categoryRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(rollbackOn = {SQLException.class})
    public ResponseEntity<Message> save(Category category){
        Optional<Category> existCategory = categoryRepository.findByDescription(category.getDescription());

        if (existCategory.isPresent()){
            return new ResponseEntity<>(new Message("La categoria ya existe", true, null), HttpStatus.BAD_REQUEST);
        }
        Category savedCategory = categoryRepository.saveAndFlush(category);
        return new ResponseEntity<>(new Message("Categoria registrada correctamente", true, savedCategory), HttpStatus.OK);
    }

    @Transactional(rollbackOn = {SQLException.class})
    public ResponseEntity<Message> update(Category category){
        Optional<Category> existCategory = categoryRepository.findByDescription(category.getDescription());

        if (categoryRepository.existsById(category.getId())){
            return new ResponseEntity<>(new Message("Ok", false, categoryRepository.saveAndFlush(category)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("Categoria registrada correctamente", true, null), HttpStatus.BAD_REQUEST);
    }
}
